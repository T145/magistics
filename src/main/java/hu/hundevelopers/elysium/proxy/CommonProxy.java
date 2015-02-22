package hu.hundevelopers.elysium.proxy;

import net.minecraft.network.Packet;
import net.minecraft.world.World;
import cpw.mods.fml.common.Loader;

public class CommonProxy {
	public void registerRenderers()
	{

	}
	
	public String getMinecraftVersion() {
		return Loader.instance().getMinecraftModContainer().getVersion();
	}

	/* INSTANCES */
	public Object getClient() {
		return null;
	}

	public World getClientWorld() {
		return null;
	}

	/* SIMULATION */
	public boolean isSimulating(World world) {
		return !world.isRemote;
	}

	public boolean isRenderWorld(World world) {
		return world.isRemote;
	}

//	public void sendToPlayer(EntityPlayer entityplayer, ElysiumPacket packet)
//	{
//		EntityPlayerMP player = (EntityPlayerMP) entityplayer;
//		player.playerNetServerHandler.sendPacketToPlayer(packet.getPacket());
//	}

	public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance)
	{
		System.out.println("packet sending not yet implemented!!!");
//		if(packet != null)
//		{
//			for (int j = 0; j < world.playerEntities.size(); j++)
//			{
//				EntityPlayerMP player = (EntityPlayerMP) world.playerEntities.get(j);
//				if((player.posX - x)*(player.posX - x) + (player.posY - y)*(player.posY - y) + (player.posZ - z)*(player.posZ - z) <= maxDistance*maxDistance)
//				{
//					player.playerNetServerHandler.sendPacketToPlayer(packet);
//				}
//			}
//		}
	}

	public void sendToServer(Packet packet)
	{

	}

	public String playerName() {
		return "";
	}
}
