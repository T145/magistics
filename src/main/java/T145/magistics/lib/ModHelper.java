package T145.magistics.lib;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class ModHelper {
	public static EntityPlayer findPlayerByUUID(UUID uuid) {
		if (uuid == null) {
			return null;
		}

		MinecraftServer server = MinecraftServer.getServer();
		
		if (server == null) {
			return null;
		}

		List<EntityPlayer> playerList = server.getConfigurationManager().playerEntityList;

		for (EntityPlayer player : playerList) {
			if (player.getUniqueID().equals(uuid) == true) {
				return player;
			}
		}

		return null;
	}

	public static Entity findEntityByUUID(List<Entity> list, UUID uuid) {
		if (uuid == null) {
			return null;
		}

		for (Entity entity : list) {
			if (entity.getUniqueID().equals(uuid)) {
				return entity;
			}
		}

		return null;
	}
}