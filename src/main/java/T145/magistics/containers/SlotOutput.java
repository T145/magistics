package T145.magistics.containers;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOutput extends Slot {
	public SlotOutput(IInventory inv, int x, int y, int z) {
		super(inv, x, y, z);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
}