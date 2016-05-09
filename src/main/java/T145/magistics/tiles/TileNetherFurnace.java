package T145.magistics.tiles;

import T145.magistics.blocks.BlockNetherFurnace;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileNetherFurnace extends TileModifiedFurnace
{
	@Override
	public void updateFurnaceBlockState(boolean b, World worldObj, int xCoord, int yCoord, int zCoord) {
		BlockNetherFurnace.updateFurnaceBlockState(b, worldObj, xCoord, yCoord, zCoord);
	}
}