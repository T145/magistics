package T145.magistics.client;

import net.minecraft.network.Packet;
import net.minecraft.world.World;
import T145.magistics.common.CommonProxy;
import cpw.mods.fml.client.FMLClientHandler;

public class ClientProxy extends CommonProxy {
	public Object getClient() {
		return FMLClientHandler.instance().getClient();
	}

	public Object getPlayer() {
		return FMLClientHandler.instance().getClient().thePlayer;
	}

	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	public void sendToServer(Packet packet) {
		FMLClientHandler.instance().getClient().getNetHandler().addToSendQueue(packet);
	}

	public String playerName() {
		return FMLClientHandler.instance().getClient().thePlayer.getDisplayName();
	}
}