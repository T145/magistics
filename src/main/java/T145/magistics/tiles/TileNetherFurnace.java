package T145.magistics.tiles;

import net.minecraft.world.World;
import T145.magistics.blocks.BlockNetherFurnace;

public class TileNetherFurnace extends TileModifiedFurnace {
	@Override
	public void updateFurnaceBlockState(boolean b, World worldObj, int xCoord, int yCoord, int zCoord) {
		BlockNetherFurnace.updateFurnaceBlockState(b, worldObj, xCoord, yCoord, zCoord);
	}
}