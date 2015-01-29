package T145.magistics.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.config.ConfigBlocks;

public class TileArcaneRedstoneLamp extends TileThaumcraft {
	public ForgeDirection facing = ForgeDirection.getOrientation(0);
	public static boolean active = false;

	@Override
	public boolean canUpdate() {
		return true;
	}

	public int getRandCoord(int coord) {
		return coord + worldObj.rand.nextInt(16) - worldObj.rand.nextInt(16);
	}

	@Override
	public void updateEntity() {
		if (active && !worldObj.isRemote) {
			int x = getRandCoord(xCoord), y = getRandCoord(yCoord), z = getRandCoord(zCoord);
			if (y > worldObj.getHeightValue(x, z) + 4)
				y = worldObj.getHeightValue(x, z) + 4;
			if (y < 5)
				y = 5;
			if (worldObj.isAirBlock(x, y, z) && worldObj.getBlockLightValue(x, y, z) < 9) {
				worldObj.setBlockToAir(x, y, z);
				worldObj.setBlock(x, y, z, ConfigBlocks.blockAiry, 3, 3);
			}
		}
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		facing = ForgeDirection.getOrientation(nbt.getInteger("orientation"));
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setInteger("orientation", facing.ordinal());
	}
}