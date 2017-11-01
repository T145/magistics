package T145.magistics.lib.managers;

import T145.magistics.Magistics;
import T145.magistics.core.Init;
import T145.magistics.tiles.devices.TileChestVoid;
import T145.magistics.world.data.WorldDataVoidChest;
import T145.magistics.world.teleporters.TeleporterVoidWorld;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class TeleportationManager {

	private static void teleportPlayerToVoidChest(EntityPlayerMP player, int coords, boolean isReturning) {
		NBTTagCompound playerNBT = player.getEntityData();

		if (player.dimension != Magistics.CONFIG.voidDimensionId) {
			playerNBT.setInteger("void-oldDimension", player.dimension);
			playerNBT.setDouble("void-oldPosX", player.posX);
			playerNBT.setDouble("void-oldPosY", player.posY);
			playerNBT.setDouble("void-oldPosZ", player.posZ);

			WorldServer voidWorld = Init.getServerVoidWorld();
			PlayerList playerList = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();

			playerList.transferPlayerToDimension(player, Magistics.CONFIG.voidDimensionId, new TeleporterVoidWorld(voidWorld));

			if (playerNBT.hasKey("void-coordHistory")) {
				playerNBT.removeTag("void-coordHistory");
			}
		}

		if (!isReturning) {
			NBTTagList coordHistory;

			if (playerNBT.hasKey("void-coordHistory")) {
				coordHistory = playerNBT.getTagList("void-coordHistory", 10);
			} else {
				coordHistory = new NBTTagList();
			}

			NBTTagCompound toAppend = new NBTTagCompound();
			toAppend.setInteger("coord", coords);
			coordHistory.appendTag(toAppend);
			playerNBT.setTag("void-coordHistory", coordHistory);
		}

		double[] destination = WorldDataVoidChest.INSTANCE.spawnPoints.get(coords);
		player.setPositionAndUpdate(destination[0], destination[1], destination[2]);
	}

	private static void teleportPlayerOutOfVoidChestDimension(EntityPlayerMP player) {
		PlayerList playerList = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();
		NBTTagCompound playerNBT = player.getEntityData();

		if (playerNBT.hasKey("void-oldPosX")) {
			int oldDimension = playerNBT.getInteger("void-oldDimension");
			double oldPosX = playerNBT.getDouble("void-oldPosX");
			double oldPosY = playerNBT.getDouble("void-oldPosY");
			double oldPosZ = playerNBT.getDouble("void-oldPosZ");

			playerList.transferPlayerToDimension(player, oldDimension, new TeleporterVoidWorld(Init.getWorldServerForDimension(oldDimension)));
			player.setPositionAndUpdate(oldPosX, oldPosY, oldPosZ);
		} else {
			// TODO: Find a good nearby spawn position offset by facing
			BlockPos spawnPoint = Init.getWorldServerForDimension(0).provider.getRandomizedSpawnPoint();

			playerList.transferPlayerToDimension(player, 0, new TeleporterVoidWorld(Init.getWorldServerForDimension(0)));
			player.setPositionAndUpdate(spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ());
		}
	}

	public static void teleportPlayerToVoidChest(EntityPlayerMP player, TileChestVoid chest) {
		if (chest.id == -1) {
			StructureManager.generateCubeForVoidChest(chest);

			int size = 12;
			double[] destination = new double[] { chest.id * 1024 + 0.5 + size / 2, 42, 0.5 + size / 2 };
			double x = chest.id * 1024 + 0.5 + size / 2;
			double y = 42;
			double z = 0.5 + size / 2;
			WorldDataVoidChest.INSTANCE.addSpawnPoint(chest.id, x, y, z);
		}

		teleportPlayerToVoidChest(player, chest.id, false);
	}

	public static void teleportPlayerOutOfVoidChest(EntityPlayerMP player) {
		NBTTagCompound playerNBT = player.getEntityData();

		if (!playerNBT.hasKey("void-coordHistory")) {
			return;
		}

		NBTTagList coordHistory = playerNBT.getTagList("void-coordHistory", 10);

		if (coordHistory.tagCount() == 0) {
			teleportPlayerOutOfVoidChestDimension(player);
			return;
		}

		coordHistory.removeTag(coordHistory.tagCount() - 1);

		if (coordHistory.tagCount() == 0) {
			teleportPlayerOutOfVoidChestDimension(player);
			return;
		}

		int coords = coordHistory.getCompoundTagAt(coordHistory.tagCount() - 1).getInteger("coord");
		teleportPlayerToVoidChest(player, coords, true);
	}
}