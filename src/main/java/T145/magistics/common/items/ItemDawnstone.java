package T145.magistics.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ItemDawnstone extends Item {
	@Override
	public EnumRarity getRarity(ItemStack is) {
		return EnumRarity.uncommon;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (world.getWorldTime() % 24000L >= 6000L) {
			long time = world.getWorldTime() + 24000L, destDawn = time - time % 24000L;
			for (int j = 0; j < MinecraftServer.getServer().worldServers.length; j++)
				MinecraftServer.getServer().worldServers[j].setWorldTime(destDawn);
			--is.stackSize;
			world.playSoundAtEntity(player, "thaumcraft.recover", 1F, 1F);
		}
		return super.onItemRightClick(is, world, player);
	}
}