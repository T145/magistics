package T145.magistics.tiles.devices;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import T145.magistics.lib.managers.PlayerManager;
import T145.magistics.tiles.MTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;

public class MTileChunkLoader extends MTile implements ITickable {

	private ForgeChunkManager.Ticket ticket;
	private GameProfile owner;
	private ChunkPos loaded;
	private boolean isLoaded;
	private int tick;
	private boolean dirty = true;

	public MTileChunkLoader() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void setPlayer(EntityPlayer player) {
		if (player != null) {
			owner = player.getGameProfile();
		}
	}

	public void setPlayer(UUID uuid) {
		EntityPlayer player = PlayerManager.getPlayerFromWorld(world, uuid);

		if (player != null) {
			owner = player.getGameProfile();
		}
	}

	public UUID getPlayerID() {
		return owner.getId();
	}

	public GameProfile getPlayerProfile() {
		return owner;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setLong("loaded", PlayerManager.PlayerLoader.serializeChunkPos(loaded));

		if (owner != null) {
			compound.setTag("profile", PlayerManager.profileToNBT(owner));
		}
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		if (compound.hasKey("loaded")) {
			loaded = PlayerManager.PlayerLoader.deserializeChunkPos(compound.getLong("loaded"));
		}

		owner = PlayerManager.profileFromNBT(compound.getCompoundTag("profile"));
	}

	public void setLoading(boolean load) {
		if (load) {
			ForgeChunkManager.Ticket test = PlayerManager.PlayerLoader.getInstance().getPlayerTicket(owner, world);

			if (test != null) {
				ticket = test;
				PlayerManager.PlayerLoader.getInstance().addToTicket(ticket, loaded);
				isLoaded = true;
			}
		} else if (ticket != null) {
			PlayerManager.PlayerLoader.getInstance().removeFromTicket(ticket, loaded);
			isLoaded = false;
		}
	}

	public boolean getLoading() {
		return PlayerManager.PlayerLoader.getInstance().isLoading(ticket, loaded);
	}

	@Override
	public void update() {
		if (!world.isRemote && !getLoading()) {
			setLoading(true);
		}
	}

	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
	}

	@Override
	public void onLoad() {
		super.onLoad();
	}

	public void forceLoad() {
		if (!world.isRemote) {
			setLoading(true);
		}
	}

	public void onBlockPlaced() {
		loaded = PlayerManager.PlayerLoader.getChunkPos(pos);
	}

	public void onBlockBreak() {
		if (ticket != null) {
			PlayerManager.PlayerLoader.getInstance().removeFromTicket(ticket, loaded);
		}
	}
}