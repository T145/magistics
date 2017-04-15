package T145.magistics.api;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class InventoryHelper {

	public static boolean areStacksEqual(ItemStack stack1, ItemStack stack2) {
		return stack1 != null && stack2 != null && stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public static void setInventorySlotContents(NonNullList<ItemStack> contents, ItemStack stack, int stackLimit, int slot) {
		contents.add(slot, stack);

		if (stack != null && stack.getCount() > stackLimit) {
			stack.setCount(stackLimit);
		}
	}
}