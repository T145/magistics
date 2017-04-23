package T145.magistics.api;

import net.minecraft.item.ItemStack;

public class ItemStackUtils {

	public static boolean areItemStacksEqual(ItemStack stackA, ItemStack stackB) {
		return stackA.isEmpty() && stackB.isEmpty() ? true : (!stackA.isEmpty() && !stackB.isEmpty() ? isItemStackEqual(stackA, stackB) : false);
	}

	private static boolean isItemStackEqual(ItemStack first, ItemStack other) {
		return first.getItem() != other.getItem() ? false : (first.getItemDamage() != other.getItemDamage() ? false : (first.getTagCompound() == null && other.getTagCompound() != null ? false : (first.getTagCompound() == null || first.getTagCompound().equals(other.getTagCompound())) && first.areCapsCompatible(other)));
	}
}