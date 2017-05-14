package T145.magistics.lib.managers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.authlib.GameProfile;

import T145.magistics.Magistics;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PlayerManager {

	public static boolean doesPlayerExist(World world, UUID player) {
		if (world == null || player == null || world.getMinecraftServer() == null) {
			return false;
		}

		return world.getMinecraftServer().getPlayerList().getPlayerByUUID(player) != null;
	}

	public static EntityPlayer getPlayerFromWorld(World world, UUID player) {
		if (world == null || player == null || world.getMinecraftServer() == null) {
			return null;
		}

		return world.getMinecraftServer().getPlayerList().getPlayerByUUID(player);
	}

	public static boolean doesPlayerExistClient(World world, UUID player) {
		if (player == null) {
			return false;
		}

		List<EntityPlayer> players = world.playerEntities;

		for (EntityPlayer entityPlayer : players) {
			if (entityPlayer.getUniqueID().compareTo(player) == 0) {
				return true;
			}
		}

		return false;
	}

	public static EntityPlayer getPlayerFromWorldClient(World world, UUID player) {
		if (player == null) {
			return null;
		}

		List<EntityPlayer> players = world.playerEntities;

		for (EntityPlayer entityPlayer : players) {
			if (entityPlayer.getUniqueID().compareTo(player) == 0) {
				return entityPlayer;
			}
		}

		return null;
	}

	public static NBTTagCompound profileToNBT(GameProfile profile) {
		NBTTagCompound tag = new NBTTagCompound();

		tag.setString("Name", profile.getName());

		UUID id = profile.getId();

		if (id != null) {
			tag.setLong("UUIDL", id.getLeastSignificantBits());
			tag.setLong("UUIDU", id.getMostSignificantBits());
		}

		return tag;
	}

	public static void writeProfileToNBT(GameProfile profile, NBTTagCompound tag) {
		tag.setString("Name", profile.getName());
		UUID id = profile.getId();

		if (id != null) {
			tag.setLong("UUIDL", id.getLeastSignificantBits());
			tag.setLong("UUIDU", id.getMostSignificantBits());
		}
	}

	public static GameProfile profileFromNBT(NBTTagCompound tag) {
		String name = tag.getString("Name");
		UUID uuid = null;

		if (tag.hasKey("UUIDL")) {
			uuid = new UUID(tag.getLong("UUIDU"), tag.getLong("UUIDL"));
		} else if (name.equals("")) {
			return null;
		}

		return new GameProfile(uuid, name);
	}

	public static class PlayerLoader implements ForgeChunkManager.LoadingCallback, ForgeChunkManager.PlayerOrderedLoadingCallback {
		private static Map<World, Map<GameProfile, ForgeChunkManager.Ticket>> playerTickets = new HashMap();
		private boolean dirty = false;

		private PlayerLoader() {
			MinecraftForge.EVENT_BUS.register(this);
			ForgeChunkManager.setForcedChunkLoadingCallback(Magistics.instance, this);
		}

		public static PlayerLoader getInstance() {
			return new PlayerLoader();
		}

		public ListMultimap<String, ForgeChunkManager.Ticket> playerTicketsLoaded(ListMultimap<String, ForgeChunkManager.Ticket> tickets, World world) {
			return tickets;
		}

		public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) {
			dirty = true;

			HashMap<GameProfile, ForgeChunkManager.Ticket> cache = new HashMap();

			playerTickets.put(world, cache);

			for (ForgeChunkManager.Ticket ticket : tickets) {
				NBTTagCompound data = ticket.getModData();
				GameProfile prof = profileFromNBT(data);

				cache.put(prof, ticket);

				NBTTagList list = ticket.getModData().getTagList("chunks", 4);

				for (int i = 0; i < list.tagCount(); i++) {
					NBTTagLong pos = (NBTTagLong) list.get(i);
					ChunkPos cp = deserializeChunkPos(pos.getLong());
					ForgeChunkManager.forceChunk(ticket, cp);
				}
			}
		}

		private Map<GameProfile, ForgeChunkManager.Ticket> getWorldTickets(World world) {
			if (world.isRemote) {
				return null;
			}

			if (!playerTickets.containsKey(world)) {
				playerTickets.put(world, new HashMap());
			}

			return playerTickets.get(world);
		}

		@SubscribeEvent
		public void unloadWorldEvent(WorldEvent.Unload event) {
			if (event.getWorld().isRemote) {
				return;
			}

			if (playerTickets.containsKey(event.getWorld())) {
				playerTickets.remove(event.getWorld());
			}
		}

		@SubscribeEvent
		public void serverTick(TickEvent.ServerTickEvent event) {
			if (dirty) {
				for (Map<GameProfile, ForgeChunkManager.Ticket> map : playerTickets.values()) {
					for (Iterator iterator = map.values().iterator(); iterator.hasNext();) {
						ForgeChunkManager.Ticket tick = (ForgeChunkManager.Ticket) iterator.next();
						ImmutableSet<ChunkPos> chunkList = tick.getChunkList();

						for (UnmodifiableIterator localUnmodifiableIterator = chunkList.iterator(); localUnmodifiableIterator.hasNext();) {
							ChunkPos chunkPos = (ChunkPos) localUnmodifiableIterator.next();
							ForgeChunkManager.forceChunk(tick, chunkPos);
						}
					}
				}

				dirty = false;
			}
		}

		public ForgeChunkManager.Ticket getPlayerTicket(GameProfile profile, World world) {
			HashMap<GameProfile, ForgeChunkManager.Ticket> tickMap = (HashMap) playerTickets.get(world);

			if (tickMap == null) {
				playerTickets.put(world, tickMap = new HashMap());
			}

			ForgeChunkManager.Ticket tick = tickMap.get(profile);

			if (tick == null) {
				tick = ForgeChunkManager.requestPlayerTicket(Magistics.instance, profile.getName(), world, ForgeChunkManager.Type.NORMAL);
				writeProfileToNBT(profile, tick.getModData());
				tickMap.put(profile, tick);
			}

			return tick;
		}

		public void addToTicket(ForgeChunkManager.Ticket tick, ChunkPos chunk) {
			if (tick.getChunkList().contains(chunk)) {
				return;
			}

			ForgeChunkManager.forceChunk(tick, chunk);

			NBTTagList list = tick.getModData().getTagList("chunks", 4);

			if (!tick.getModData().hasKey("chunks", 9)) {
				tick.getModData().setTag("chunks", list);
			}

			tick.getModData().setTag("chunks", list);
			list.appendTag(new NBTTagLong(serializeChunkPos(chunk)));
		}

		public void removeFromTicket(ForgeChunkManager.Ticket tick, ChunkPos chunk) {
			ForgeChunkManager.unforceChunk(tick, chunk);
			NBTTagList list = tick.getModData().getTagList("chunks", 4);
			long chunkPos = serializeChunkPos(chunk);

			for (int i = 0; i < list.tagCount(); i++) {
				NBTTagLong pos = (NBTTagLong) list.get(i);
				if (pos.getLong() == chunkPos) {
					list.removeTag(i--);
				}
			}
		}

		public void removeTicket(ForgeChunkManager.Ticket tick) {
			NBTTagList list = tick.getModData().getTagList("chunks", 4);

			if (list.tagCount() > 0) {
				return;
			}

			ForgeChunkManager.releaseTicket(tick);
			getWorldTickets(tick.world).remove(tick);
		}

		public boolean isLoading(ForgeChunkManager.Ticket tick, ChunkPos chunk) {
			if (tick == null || chunk == null) {
				return false;
			}

			NBTTagList list = tick.getModData().getTagList("chunks", 4);
			long chunkPos = serializeChunkPos(chunk);

			for (int i = 0; i < list.tagCount(); i++) {
				NBTTagLong pos = (NBTTagLong) list.get(i);

				if (pos.getLong() == chunkPos) {
					return true;
				}
			}

			return false;
		}

		public static long serializeChunkPos(ChunkPos chunk) {
			return chunk.chunkXPos & 0xFFFFFFFF | (chunk.chunkZPos & 0xFFFFFFFF) << 32;
		}

		public static ChunkPos deserializeChunkPos(long value) {
			return new ChunkPos((int) (value & 0xFFFFFFFF), (int) (value >> 32));
		}

		public static ChunkPos getChunkPos(BlockPos pos) {
			return new ChunkPos(pos.getX() >> 4, pos.getZ() >> 4);
		}
	}
}