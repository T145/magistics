package T145.magistics.common.items.baubles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import T145.magistics.api.items.baubles.IMod;

public class ModEnderPearl implements IMod {
	@Override
	public EnumChatFormatting getColor(ItemStack gem) {
		return EnumChatFormatting.DARK_PURPLE;
	}

	@Override
	public void onWornTick(ItemStack ring, ItemStack gem, EntityLivingBase player) {}

	@Override
	public void onEquipped(ItemStack ring, EntityLivingBase player) {}

	@Override
	public void onUnequipped(ItemStack ring, EntityLivingBase player) {}

	@Override
	public boolean canEquip(ItemStack ring, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack ring, EntityLivingBase player) {
		return true;
	}
}