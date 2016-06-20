package T145.magistics.tiles;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.TileThaumcraft;

public class TileElevator extends TileThaumcraft {
	public static final int RANGE = 10;
	private boolean blocked = false;
	private int count = 0;

	public boolean isBlocked() {
		return blocked;
	}

	public boolean isPowered() {
		return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		blocked = tag.getBoolean("blocked");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setBoolean("blocked", blocked);
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		++count;

		if (hasWorldObj() && count % 40 == 0) {
			count = 0;

			double xx = xCoord + 0.5;
			double yy = yCoord + 1;
			double zz = zCoord + 0.5;

			List<EntityPlayer> targets = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xx, yy, zz, xx, yy + RANGE, zz));

			if (targets.size() == 1) {
				EntityPlayer player = targets.get(0);

				blocked = true;

				if (isPowered()) {
					for (int destY = yCoord - 1; destY > yCoord - 1 - RANGE; --destY) {
						TileEntity tile = worldObj.getTileEntity(xCoord, destY, zCoord);

						if (tile instanceof TileElevator) {
							TileElevator elevator = (TileElevator) tile;

							if (!elevator.isBlocked() && canTeleportTo(xCoord, destY, zCoord)) {
								player.setPositionAndUpdate(xx, destY + 1, zz);
								return;
							}
						}
					}
				} else {
					for (int destY = RANGE; destY > 0; --destY) {
						TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + destY, zCoord);

						if (tile instanceof TileElevator) {
							TileElevator elevator = (TileElevator) tile;

							if (!elevator.isBlocked() && !elevator.isPowered() && canTeleportTo(xCoord, yCoord + destY, zCoord)) {
								player.setPositionAndUpdate(xx, yy + destY, zz);
								return;
							}
						}
					}
				}
			} else {
				blocked = false;
				return;
			}
		}
	}

	private boolean canTeleportTo(int x, int y, int z) {
		return canTeleportAt(x, y + 1, z) && canTeleportAt(x, y + 2, z);
	}

	private boolean canTeleportAt(int x, int y, int z) {
		Block block = worldObj.getBlock(x, y, z);

		if (block == null || block.isAir(worldObj, x, y, z)) {
			return true;
		}

		final AxisAlignedBB box = block.getCollisionBoundingBoxFromPool(worldObj, x, y, z);
		return box == null || box.getAverageEdgeLength() < 0.7;
	}
}