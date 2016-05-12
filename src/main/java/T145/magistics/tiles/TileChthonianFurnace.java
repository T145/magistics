package T145.magistics.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileChthonianFurnace extends TileEntity {
	private int facing = 0;

	public int getFacing() {
		return facing;
	}

	public void setFacing(int dir) {
		facing = dir;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		facing = tag.getByte("facing");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setByte("facing", (byte) facing);
	}
}