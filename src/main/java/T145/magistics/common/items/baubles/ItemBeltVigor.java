package T145.magistics.common.items.baubles;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import T145.magistics.api.items.baubles.ItemBauble;
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
	public void onWornTick(ItemStack is, EntityLivingBase user) {
		Random rand = new Random();
		if (user instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) user;

			if (rand.nextInt(14) == 0) {
				if (player.getFoodStats().needFood()) {
					player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 1);
					user.worldObj.playSoundAtEntity(user, "random.eat", 0.25F, user.worldObj.rand.nextFloat() * 0.5F + 0.5F);
				}
				if (user.getAir() < 150) {
					user.setAir(300);
					user.worldObj.playSoundAtEntity(user, "random.breath", 0.8F, 0.5F * ((user.worldObj.rand.nextFloat() - user.worldObj.rand.nextFloat()) * 0.6F + 2F));
				}
			}
		}
	}
}