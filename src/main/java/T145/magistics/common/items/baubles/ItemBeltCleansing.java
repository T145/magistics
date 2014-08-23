package T145.magistics.common.items.baubles;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBeltCleansing extends ItemBauble {
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("magistics:bauble_belt_cleansing");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return itemIcon;
	}

	@Override
	public BaubleType getBaubleType(ItemStack is) {
		return BaubleType.BELT;
	}

	@Override
	public void onWornTick(ItemStack is, EntityLivingBase player) {
		boolean cleansed = false;

		if (player.isBurning()) {
			player.extinguish();
			cleansed = true;
		} else if (player.getActivePotionEffect(Potion.blindness) != null) {
			player.removePotionEffect(Potion.blindness.getId());
			cleansed = true;
		} else if (player.getActivePotionEffect(Potion.confusion) != null) {
			player.removePotionEffect(Potion.confusion.getId());
			cleansed = true;
		} else if (player.getActivePotionEffect(Potion.poison) != null) {
			player.removePotionEffect(Potion.poison.getId());
			cleansed = true;
		} else if (player.getActivePotionEffect(Potion.weakness) != null) {
			player.removePotionEffect(Potion.weakness.getId());
			cleansed = true;
		}

		if (cleansed) {
			is.damageItem(1, player);
			player.worldObj.playSoundAtEntity(player, "magistics.heal", 1.0F, 1.0F);
		}
	}
}