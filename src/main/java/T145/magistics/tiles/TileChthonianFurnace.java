package T145.magistics.tiles;

import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.TileThaumcraft;

public class TileChthonianFurnace extends TileThaumcraft {
	public int facing = 0;

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		facing = tag.getByte("facing");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setByte("facing", (byte) facing);
	}
}