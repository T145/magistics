package T145.magistics.tiles.devices;

import java.util.Stack;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

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

	@Nullable
	private BlockPos getDestination() {
		BlockPos destPos = null;

		for (int offsetY = 1; offsetY <= RANGE; ++offsetY) {
			destPos = pos.offset(isPowered() ? EnumFacing.DOWN : EnumFacing.UP, offsetY);

			if (destPos.getY() < 0) {
				destPos = new BlockPos(pos.getX(), 0, pos.getZ());
			} else if (destPos.getY() > world.getActualHeight()) {
				destPos = pos;
			}

			TileEntity tile = world.getTileEntity(destPos);

			if (tile instanceof TileElevator && tile != this) {
				return destPos;
			}
		}

		return destPos;
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

	@Override
	public void update() {
		if (!world.isRemote) {
			Stack<Entity> entitiesAbove = getEntitiesAbove();

			if (!(clear = entitiesAbove.isEmpty())) {
				Entity target = entitiesAbove.pop();

				if (++delay % 45 == 0) {
					delay = 0;

					BlockPos destPos = getDestination();
					TileEntity destTile = world.getTileEntity(destPos);

					if (destPos != null && destTile instanceof TileElevator) {
						TileElevator dest = (TileElevator) destTile;

						if (dest.canTeleportTo()) {
							teleportEntity((WorldServer) world, target, target.posX, destPos.getY() + 1D, target.posZ);
							world.playSound(null, target.posX, destPos.getY() + 1D, target.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 1F, 1F);
							delay -= 20;
							dest.setDelay(delay);
						}
					}
				}
			}
		}
	}

	private void teleportEntity(WorldServer world, Entity entity, double x, double y, double z) {
		double prevPosX = entity.posX;
		double prevPosY = entity.posY;
		double prevPosZ = entity.posZ;

		entity.setPositionAndUpdate(x, y, z);

		for (int j = 0; j < 128; ++j) {
			double d6 = j / 127.0D;
			float f = (world.rand.nextFloat() - 0.5F) * 0.2F;
			float f1 = (world.rand.nextFloat() - 0.5F) * 0.2F;
			float f2 = (world.rand.nextFloat() - 0.5F) * 0.2F;
			double d3 = prevPosX + (entity.posX - prevPosX) * d6 + (world.rand.nextDouble() - 0.5D) * entity.width * 2.0D;
			double d4 = prevPosY + (entity.posY - prevPosY) * d6 + world.rand.nextDouble() * entity.height;
			double d5 = prevPosZ + (entity.posZ - prevPosZ) * d6 + (world.rand.nextDouble() - 0.5D) * entity.width * 2.0D;
			world.spawnParticle(EnumParticleTypes.PORTAL, false, d3, d4, d5, 1, f, f1, f2, 0D);
		}

		if (entity instanceof EntityCreature) {
			((EntityCreature) entity).getNavigator().clearPathEntity();
		}
	}
}