package T145.magistics.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileInfuser extends TileEntity {
	public static ForgeDirection facing = ForgeDirection.SOUTH;
	public static String nbt_facing = "orientation";

	public ForgeDirection getOrientation() {
		return facing;
	}

	public void setOrientation(ForgeDirection dir) {
		facing = dir;
	}

	public void setOrientation(int dir) {
		facing = ForgeDirection.getOrientation(dir);
	}

	public boolean isSideConnected(int side) {
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		if (nbtTagCompound.hasKey(nbt_facing))
			facing = ForgeDirection.getOrientation(nbtTagCompound.getByte(nbt_facing));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setByte(nbt_facing, (byte) facing.ordinal());
	}
}