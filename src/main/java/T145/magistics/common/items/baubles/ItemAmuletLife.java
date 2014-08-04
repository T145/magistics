package T145.magistics.common.items.baubles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import baubles.api.BaubleType;
import baubles.api.IBauble;

public class ItemAmuletLife extends Item implements IBauble {
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
	public void onWornTick(ItemStack is, EntityLivingBase player) {}
}