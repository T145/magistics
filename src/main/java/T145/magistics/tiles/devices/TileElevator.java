package T145.magistics.tiles.devices;

import java.util.Stack;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileElevator extends TileEntity implements ITickable {

	private final int RANGE = 14;
	private int delay;
	private boolean clear;

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public boolean isClear() {
		return clear;
	}

	public boolean isPowered() {
		return world.isBlockPowered(pos);
	}

	public Stack<Entity> getEntitiesAbove() {
		double xx = pos.getX() + 0.5D;
		double yy = pos.getY() + 1D;
		double zz = pos.getZ() + 0.5D;
		AxisAlignedBB boxAbove = new AxisAlignedBB(xx, yy, zz, xx, yy + 1D, zz);
		Stack<Entity> entitiesAbove = new Stack<Entity>();
		entitiesAbove.addAll(world.getEntitiesWithinAABB(Entity.class, boxAbove));
		return entitiesAbove;
	}

	@Override
	public void update() { // "signal strength" can also be weakened by subtracting consecutive delay
		Stack<Entity> entitiesAbove = getEntitiesAbove();

		if (!(clear = entitiesAbove.isEmpty())) {
			Entity target = entitiesAbove.pop();

			if (++delay % 45 == 0) {
				delay = 0;

				for (int offsetY = 1; offsetY <= RANGE; ++offsetY) {
					BlockPos destPos = pos.offset(isPowered() ? EnumFacing.DOWN : EnumFacing.UP, offsetY);

					if (destPos.getY() < 0) {
						destPos = new BlockPos(pos.getX(), 0, pos.getZ());
					} else if (destPos.getY() > world.getActualHeight()) {
						destPos = pos;
					}

					TileEntity tile = world.getTileEntity(destPos);

					if (tile instanceof TileElevator && tile != this) {
						TileElevator dest = (TileElevator) tile;

						if (dest.canTeleportTo()) {
							target.setPositionAndUpdate(target.posX, destPos.getY() + 1D, target.posZ);
							world.playSound(null, target.posX, destPos.getY() + 1D, target.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 1F, 1F);
							delay -= 20;
							dest.setDelay(delay);
							break;
						}
					}
				}
			}
		}
	}

	public boolean canTeleportTo() {
		return clear && !isPowered() && canTeleportAt(pos.up()) && canTeleportAt(pos.up(2));
	}

	private boolean canTeleportAt(BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (block == null || block.isAir(state, world, pos)) {
			return true;
		}

		final AxisAlignedBB box = block.getCollisionBoundingBox(state, world, pos);
		return box == null || box.getAverageEdgeLength() < 0.7;
	}
}