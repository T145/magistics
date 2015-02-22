package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.heat.HeatManager;
import hu.hundevelopers.elysium.world.ElysiumTeleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ElysiumItemDebug extends ElysiumItem
{

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		//world.spawnEntityInWorld(new EntityDrachma(world, x+hitX, y+hitY, z+hitZ, new Random().nextInt(10)+10));

		if(!world.isRemote)
		{
			player.addChatMessage(new ChatComponentText("Block: "+world.getBlock(x, y, z).getUnlocalizedName()));
			player.addChatMessage(new ChatComponentText("Meta: "+world.getBlockMetadata(x, y, z)));
		} else {
			player.addChatMessage(new ChatComponentText("Heat: "+HeatManager.getInstance().getHeatAt(world, x, y, z)));
		}

        return false;
    }

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		/*if(world.isRemote)
		{
			int randomNum = new Random().nextInt();
			
			player.sendChatToPlayer("Sending packet to server: " + randomNum);
			PacketDispatcher.sendPacketToServer(new PacketRandom(randomNum).getPacket());
		}*/

		if(player.isSneaking() && (player instanceof EntityPlayerMP))
		{
			EntityPlayerMP playermp = (EntityPlayerMP)player;
			if(playermp.dimension == Elysium.dimensionID)
			{
				playermp.mcServer.getConfigurationManager().transferPlayerToDimension(playermp, 0, new ElysiumTeleporter(playermp.mcServer.worldServerForDimension(0)));
			}
			else
			{
				playermp.mcServer.getConfigurationManager().transferPlayerToDimension(playermp, Elysium.dimensionID, new ElysiumTeleporter(playermp.mcServer.worldServerForDimension(Elysium.dimensionID)));
			}
		}
		return stack;
    }
}
