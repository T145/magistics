package T145.magistics.common.tiles;

import T145.magistics.api.research.IResearchApparatus;
import T145.magistics.common.Magistics;
import T145.magistics.common.tiles.base.TileInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class TileResearchApparatus extends TileInventory implements IResearchApparatus, ITickable {

	public static final short RANGE = 5;

	private final boolean isSentinel;

	public TileResearchApparatus(boolean isSentinel) {
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
		Vec3i r = new Vec3i(RANGE, 0, RANGE);
		return new AxisAlignedBB(pos.subtract(r), pos.add(r));
	}

	@Override
	public void update() {
		if (world.isRemote || !isSentinel) {
			return;
		}

		for (double z = getEffectBounds().minZ; z <= getEffectBounds().maxZ; ++z) {
			for (double x = getEffectBounds().minX; x <= getEffectBounds().maxX; ++x) {
				BlockPos curr = new BlockPos(x, pos.getY(), z);
				TileEntity te = world.getTileEntity(curr);

				if (!curr.equals(pos) && te instanceof IResearchApparatus) {
					IResearchApparatus tile = (IResearchApparatus) te;
					ItemStack stack = tile.getObservableStack();

					if (!stack.isEmpty()) {
						Magistics.LOG.info(tile.getObservableStack().getDisplayName());
					}
				}
			}
		}
	}
}