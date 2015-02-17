package T145.magistics.common.items.baubles;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import T145.magistics.api.items.baubles.ItemBauble;
import baubles.api.BaubleType;

public class ItemBeltVigor extends ItemBauble {
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