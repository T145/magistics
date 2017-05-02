package T145.magistics.tiles.devices;

import java.util.List;

import T145.magistics.tiles.MTile;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileElevator extends MTile {

	private boolean blocked;
	private int range;
	private int delay;

	public boolean isBlocked() {
		return blocked;
	}

	public boolean isPowered() {
		return world.isBlockPowered(pos);
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {}

	@Override
	public void update() {
		if (!world.isRemote) {
			if (range == 0) {
				range = world.getActualHeight() / 4;
			}

			double xx = pos.getX() + 0.5D;
			double yy = pos.getY() + 1D;
			double zz = pos.getZ() + 0.5D;

			List<EntityPlayer> targets = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(xx, yy, zz, xx, yy + 1D, zz));

			if (targets.size() == 1) {
				EntityPlayer player = targets.get(0);
				double destY = 0;
				double endY = 0;

				++delay;
				blocked = true;

				if (isPowered()) {
					destY = yy - 1D;
					endY = range - destY;
				} else {
					destY = yy + 1D;
					endY = range + destY;
				}

				if (delay % 45 == 0) {
					delay = 0;

					while (destY != endY) {
						if (isPowered()) {
							--destY;
						} else {
							++destY;
						}

						BlockPos destPos = new BlockPos(pos.getX(), destY, pos.getZ());
						TileEntity tile = world.getTileEntity(destPos);
						TileElevator elevator = (TileElevator) tile;

						if (elevator != null && !elevator.isBlocked() && !elevator.isPowered() && canTeleportTo(destPos)) {
							player.setPositionAndUpdate(player.posX, destY + 1D, player.posZ);
							world.playSound(null, pos.getX(), pos.getY(), pos.getX(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 1.0F, 1.0F);
							return;
						}
					}
				}
			} else {
				blocked = false;
				return;
			}
		}
	}

	private boolean canTeleportTo(BlockPos pos) {
		return canTeleportAt(pos.up()) && canTeleportAt(pos.up(2));
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