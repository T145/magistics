package T145.magistics.common.items.relics;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import T145.magistics.common.config.Settings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDawnstone extends Item {
	@Override
	public EnumRarity getRarity(ItemStack is) {
		if (Settings.colored_names)
			return EnumRarity.uncommon;
		else
			return EnumRarity.common;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("magistics:dawnstone");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return itemIcon;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (world.getWorldTime() % 24000L >= 6000L) {
			long time = world.getWorldTime() + 24000L, destDawn = 0;
			destDawn = time - time % 24000L;
			for (int j = 0; j < MinecraftServer.getServer().worldServers.length; j++)
				MinecraftServer.getServer().worldServers[j].setWorldTime(destDawn);
			--is.stackSize;
			world.playSoundAtEntity(player, "thaumcraft.recover", 1.0F, 1.0F);
		}
		return super.onItemRightClick(is, world, player);
	}
}