package T145.magistics.common.tiles;

import net.minecraft.item.ItemStack;
import cofh.lib.util.helpers.ItemHelper;

public class TileHungryStrongboxCreative extends TileHungryStrongbox {
	public TileHungryStrongboxCreative(int n) {
		super(n);
	}

	@Override
	public ItemStack decrStackSize(int slotFrom, int slotTo) {
		return ItemHelper.cloneStack(inventory[slotFrom], slotTo);
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return ItemHelper.cloneStack(inventory[slot]);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (stack == null)
			return;
		inventory[slot] = stack;
		inventory[slot].stackSize = stack.getMaxStackSize();
	}
}