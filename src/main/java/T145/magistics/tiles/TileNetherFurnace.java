package T145.magistics.tiles;

import T145.magistics.api.tiles.IFacing;
import T145.magistics.api.tiles.TileFurnace;
import T145.magistics.blocks.BlockNetherFurnace;
import net.minecraft.world.World;

public class TileNetherFurnace extends TileFurnace implements IFacing {
	public TileNetherFurnace() {
		super(true, false);
	}

	@Override
	public int getFacing() {
		return getBlockMetadata();
	}

	@Override
	public void setFacing(int dir) {
		blockMetadata = dir;
	}

	@Override
	public String getInventoryName() {
		return "Netherrack Furnace";
	}

	@Override
	public void updateFurnaceBlockState(boolean isActive, World world, int x, int y, int z) {
		BlockNetherFurnace.updateFurnaceBlockState(isActive, worldObj, xCoord, yCoord, zCoord);
	}

	private void speedUpSmelting() {
		if (furnaceCookTime > 0 && furnaceCookTime < 199) {
			furnaceCookTime += 1;
		}
	}

	@Override
	public void updateEntity() {
		speedUpSmelting();
		super.updateEntity();
	}
}