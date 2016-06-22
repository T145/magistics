package T145.magistics.tiles;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileElevator extends TileEntity {
	private boolean blocked = false;
	private int range = 0;
	private int count = 0;

	public boolean isBlocked() {
		return blocked;
	}

	public boolean isPowered() {
		return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (hasWorldObj()) {
			range = worldObj.getActualHeight() / 4;

			double xx = xCoord + 0.5;
			double yy = yCoord + 1;
			double zz = zCoord + 0.5;

			List<EntityPlayer> targets = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xx, yy, zz, xx, yy + 1, zz));

			if (targets.size() == 1) {
				EntityPlayer player = targets.get(0);
				double destY = 0;
				double endY = 0;

				++count;
				blocked = true;

				if (isPowered()) {
					endY = yy - 1;
					destY = endY - range;
				} else {
					endY = yy + 1;
					destY = range + endY;
				}

				if (count % 45 == 0) {
					count = 0;

					while (destY != endY) {
						if (isPowered()) {
							++destY;
						} else {
							--destY;
						}

						TileEntity tile = worldObj.getTileEntity(xCoord, (int) destY, zCoord);

						if (tile != null && tile instanceof TileElevator) {
							TileElevator elevator = (TileElevator) tile;

							if (!elevator.isBlocked() && !elevator.isPowered() && canTeleportTo(xCoord, (int) destY, zCoord)) {
								player.setPositionAndUpdate(player.posX, destY + 1, player.posZ);
								worldObj.playSoundAtEntity(player, "mob.endermen.portal", 1F, 1F);
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