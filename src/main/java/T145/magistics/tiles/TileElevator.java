package T145.magistics.tiles;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import T145.magistics.Magistics;

import com.google.common.base.Preconditions;

public class TileElevator extends TileThaumcraft {
	private boolean canTeleportPlayer(int x, int y, int z) {
		return this.worldObj.getBlock(x, y, z).getMaterial() == Material.air;
	}

	private int findLevel(ForgeDirection direction) {
		Preconditions.checkArgument(direction == ForgeDirection.UP
				|| direction == ForgeDirection.DOWN, "Must be either up or down... for now");

		int blocksInTheWay = 0;
		final int delta = direction.offsetY;
		for (int i = 0, y = yCoord + 1; i < this.worldObj.getActualHeight() - 1; i++) {
			y += delta;

			Magistics.logger.info("Step 1");
			if (!worldObj.blockExists(xCoord, y, zCoord)) break;

			Magistics.logger.info("Step 2");
			if (worldObj.isAirBlock(xCoord, y, zCoord)) continue;

			Block block = worldObj.getBlock(xCoord, y, zCoord);

			if (block.isOpaqueCube()) {
				Magistics.logger.info("Step 3");
				if (canTeleportPlayer(xCoord, y + 1, zCoord) &&
						canTeleportPlayer(xCoord, y + 2, zCoord)) return y;
			}

			/*if (block == OpenBlocks.Blocks.elevator) {
				TileEntity otherBlock = worldObj.getTileEntity(xCoord, y, zCoord);
				if (otherBlock instanceof TileEntityElevator) {
					final int otherColor = otherBlock.getBlockMetadata();
					if (otherColor == thisColor &&
							canTeleportPlayer(xCoord, y + 1, zCoord) &&
							canTeleportPlayer(xCoord, y + 2, zCoord)) return y;
				}
			}

			ElevatorBlockRules.Action action = ElevatorBlockRules.instance.getActionForBlock(block);
			switch (action) {
				case ABORT:
					return -1;
				case IGNORE:
					continue;
				case INCREMENT:
				default:
					break;
			}*/

			//if (++blocksInTheWay > Config.elevatorMaxBlockPassCount) break;
		}

		return -1;
	}

	private void activate(EntityPlayer player, ForgeDirection dir) {
		int level = findLevel(dir);
		Magistics.logger.info("Level: " + level);
		if (level >= 0) {
			int distance = (int)Math.abs(player.posY - level);
			player.setPositionAndUpdate(xCoord + 0.5, level + 1.1, zCoord + 0.5);
		}
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (this.hasWorldObj()) {
			List<Entity> targets = this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord + 1, this.zCoord, this.xCoord + 1, this.yCoord + 1 + this.worldObj.getActualHeight() - 1, this.zCoord + 1));

			for (Entity target : targets) {
				if (target instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) target;

					if (player.isSneaking()) {
						activate(player, ForgeDirection.DOWN);
					}
				}
			}
		}
	}
}