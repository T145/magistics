package T145.magistics.common.items.baubles;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAmuletLife extends Item implements IBauble {
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("magistics:bauble_amulet_life");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return itemIcon;
	}

	@Override
	public EnumRarity getRarity(ItemStack is) {
		return EnumRarity.rare;
	}

	@Override
	public boolean canEquip(ItemStack is, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack is, EntityLivingBase player) {
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack is) {
		return BaubleType.AMULET;
	}

	@Override
	public void onEquipped(ItemStack is, EntityLivingBase player) {}

	@Override
	public void onUnequipped(ItemStack is, EntityLivingBase player) {}

	@Override
	public void onWornTick(ItemStack is, EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
			if (player.getHealth() < player.getMaxHealth()) {
				player.heal(1);
				player.worldObj.playSoundAtEntity(player, "thaumcraft.heal", 1.0F, 1.0F);
				is.damageItem(1, player);
			}
		}
	}
}