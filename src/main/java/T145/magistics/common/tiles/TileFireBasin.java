package T145.magistics.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileFireBasin extends TileEntity {
	public static ForgeDirection facing = ForgeDirection.NORTH;
	public static boolean active = false;

	public boolean isPowered() {
		return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}

	private void ignite(ForgeDirection dir) {
		if (isPowered() && worldObj.isAirBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ))
			worldObj.setBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, Blocks.fire);
	}

	private void extinguish(ForgeDirection dir) {
		Block target = worldObj.getBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
		if (!isPowered() && (target == Blocks.fire || target == Blocks.portal))
			worldObj.setBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, Blocks.air);
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote)
			if (isPowered() && !active)
				active = true;
			else if (active && !isPowered())
				active = false;

		if (active)
			ignite(facing);
		else
			extinguish(facing);
	}
}