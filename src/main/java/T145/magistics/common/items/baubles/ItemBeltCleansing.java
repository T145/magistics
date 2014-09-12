package T145.magistics.common.items.baubles;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import T145.magistics.api.items.ItemBauble;
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
	public void onWornTick(ItemStack is, EntityLivingBase user) {
		boolean cleansed = false;

		if (user.isBurning()) {
			user.extinguish();
			cleansed = true;
		} else if (user.getActivePotionEffect(Potion.blindness) != null) {
			user.removePotionEffect(Potion.blindness.getId());
			cleansed = true;
		} else if (user.getActivePotionEffect(Potion.confusion) != null) {
			user.removePotionEffect(Potion.confusion.getId());
			cleansed = true;
		} else if (user.getActivePotionEffect(Potion.poison) != null) {
			user.removePotionEffect(Potion.poison.getId());
			cleansed = true;
		} else if (user.getActivePotionEffect(Potion.weakness) != null) {
			user.removePotionEffect(Potion.weakness.getId());
			cleansed = true;
		}

		if (cleansed) {
			is.damageItem(1, user);
			user.worldObj.playSoundAtEntity(user, "magistics.heal", 1.0F, 1.0F);
		}
	}
}