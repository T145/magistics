package T145.magistics.common.items.baubles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import T145.magistics.api.items.baubles.ItemBauble;
import baubles.api.BaubleType;

public class EnderNecklace extends ItemBauble {
	@Override
	public EnumRarity getRarity(ItemStack is) {
		return EnumRarity.rare;
	}

	@Override
	public BaubleType getBaubleType(ItemStack is) {
		return BaubleType.AMULET;
	}

	@Override
	public void onWornTick(ItemStack is, EntityLivingBase player) {
		if (is.getItemDamage() == 0) {
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 20, 0));
			player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 20, 0));
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20, 1));
		}
	}
}