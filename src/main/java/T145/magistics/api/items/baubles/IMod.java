package T145.magistics.api.items.baubles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public interface IMod
{
	public EnumChatFormatting getColor(ItemStack gem);
	
	public void onWornTick(ItemStack ring, ItemStack gem, EntityLivingBase player);

	public void onEquipped(ItemStack ring, EntityLivingBase player);

	public void onUnequipped(ItemStack ring, EntityLivingBase player);

	public boolean canEquip(ItemStack ring, EntityLivingBase player);

	public boolean canUnequip(ItemStack ring, EntityLivingBase player);
}
