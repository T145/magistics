package T145.magistics.tiles.devices;

import java.util.List;

import T145.magistics.tiles.MTile;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileElevator extends MTile {

	private final int range = world.getActualHeight() / 4;
	private int delay;
	private boolean blocked;

	public boolean isBlocked() {
		return blocked;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {}

	@Override
	public void update() {
		if (!world.isRemote) {
			double xx = pos.getX() + 0.5D;
			double yy = pos.getY() + 1D;
			double zz = pos.getZ() + 0.5D;

			List<EntityPlayer> targets = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(xx, yy, zz, xx, yy + 1D, zz));

			if (targets.size() == 1) {
				EntityPlayer player = targets.get(0);
				double destY;
				double endY;
				++delay;
				blocked = true;

				if (world.isBlockPowered(pos)) {
					destY = yy - 1;
					endY = range - destY;
				} else {
					destY = yy + 1;
					endY = range + destY;
				}

				if (delay % 45 == 0) {
					delay = 0;

					while (destY != endY) {
						if (world.isBlockPowered(pos)) {
							--destY;
						} else {
							++destY;
						}

						TileElevator elevator = (TileElevator) world.getTileEntity(new BlockPos(pos.getX(), destY, pos.getZ()));

						if (elevator != null && !elevator.isBlocked() && !world.isBlockPowered(elevator.getPos()) && canTeleportTo(pos.getX(), destY, pos.getZ())) {
							player.setPositionAndUpdate(player.posX, destY + 1D, player.posZ);
							world.playSound(player, pos.getX(), pos.getY(), pos.getX(), SoundEvents.ENTITY_ENDERMEN_AMBIENT, SoundCategory.AMBIENT, 1F, 1F);
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

	private boolean canTeleportTo(double x, double y, double z) {
		return canTeleportAt(x, y + 1D, z) && canTeleportAt(x, y + 2D, z);
	}

	private boolean canTeleportAt(double x, double y, double z) {
		BlockPos destPos = new BlockPos(x, y, z);
		IBlockState state = world.getBlockState(destPos);
		Block block = state.getBlock();

		if (block == null || block.isAir(state, world, destPos)) {
			return true;
		}

		AxisAlignedBB boundingBox = block.getCollisionBoundingBox(state, world, destPos);
		return boundingBox == null || boundingBox.getAverageEdgeLength() < 0.7D;
	}
}