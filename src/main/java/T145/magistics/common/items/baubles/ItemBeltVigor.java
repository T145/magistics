package T145.magistics.common.items.baubles;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBeltVigor extends ItemBauble {
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("magistics:bauble_belt_vigor");
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
		if (player instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) player;

			if (p.getFoodStats().needFood()) {
				p.getFoodStats().setFoodLevel(p.getFoodStats().getFoodLevel() + 1);
				player.worldObj.playSoundAtEntity(player, "random.eat", 0.25F, player.worldObj.rand.nextFloat() * 0.5F + 0.5F);
				is.damageItem(1, player);
			}

			if (player.getAir() < 150) {
				player.setAir(300);
				player.worldObj.playSoundAtEntity(player, "random.breath", 0.8F, 0.5F * ((player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.6F + 2.0F));
				is.damageItem(1, player);
			}
		}
	}
}