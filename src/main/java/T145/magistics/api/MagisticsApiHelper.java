package T145.magistics.api;

import net.minecraft.item.ItemStack;

public class MagisticsApiHelper {

	public static boolean areItemStacksEqual(ItemStack first, ItemStack other) {
		return first.isEmpty() && other.isEmpty() ? true : (!first.isEmpty() && !other.isEmpty() ? isItemStackEqual(first, other) : false);
	}

	private static boolean isItemStackEqual(ItemStack first, ItemStack other) {
		return first.getItem() != other.getItem() ? false : (first.getItemDamage() != other.getItemDamage() ? false : (first.getTagCompound() == null && other.getTagCompound() != null ? false : (first.getTagCompound() == null || first.getTagCompound().equals(other.getTagCompound())) && first.areCapsCompatible(other)));
	}
}