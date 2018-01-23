package T145.magistics.common.tiles;

import T145.magistics.api.research.IResearchSentinel;
import T145.magistics.common.tiles.base.TileInventory;
import net.minecraft.item.ItemStack;

public class TilePedestal extends TileInventory implements IResearchSentinel {

	@Override
	public int getInventorySize() {
		return 1;
	}

	@Override
	public ItemStack getObservableStack() {
		return inventory.getStackInSlot(0);
	}

	@Override
	public void setObservableStack(ItemStack stack) {
		inventory.setStackInSlot(0, stack);
	}
}