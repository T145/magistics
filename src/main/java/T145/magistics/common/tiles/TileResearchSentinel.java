package T145.magistics.common.tiles;

import T145.magistics.api.research.IResearchSentinel;
import T145.magistics.common.tiles.base.TileInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;

public class TileResearchSentinel extends TileInventory implements IResearchSentinel {

	public static final short RANGE = 5;

	private final boolean isSentinel;

	public TileResearchSentinel(boolean isSentinel) {
		this.isSentinel = isSentinel;
	}

	public boolean isSentinel() {
		return isSentinel;
	}

	@Override
	public ItemStack getObservableStack() {
		return inventory.getStackInSlot(0);
	}

	@Override
	public void setObservableStack(ItemStack stack) {
		inventory.setStackInSlot(0, stack);
	}

	@Override
	public int getInventorySize() {
		return 1;
	}

	public AxisAlignedBB getEffectBounds() {
		return new AxisAlignedBB(getPos().add(-RANGE, 0, -RANGE), getPos().add(RANGE, 0, RANGE));
	}
}