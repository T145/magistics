package T145.magistics.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.tiles.TileThaumcraft;

public class TileInfuser extends TileThaumcraft {
	public ForgeDirection orientation = ForgeDirection.getOrientation(2);

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		orientation = ForgeDirection.getOrientation(nbt.getInteger("orientation"));
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setInteger("orientation", orientation.ordinal());
	}
}