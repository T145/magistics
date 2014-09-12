package T145.magistics.common.items.baubles;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import T145.magistics.api.items.ItemBauble;
import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAmuletLife extends ItemBauble {
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
	public BaubleType getBaubleType(ItemStack is) {
		return BaubleType.AMULET;
	}

	@Override
	public void onWornTick(ItemStack is, EntityLivingBase user) {
		if (user.getHealth() < user.getMaxHealth()) {
			user.heal(1);
			user.worldObj.playSoundAtEntity(user, "thaumcraft.heal", 1.0F, 1.0F);
			is.damageItem(1, user);
		}
	}
}