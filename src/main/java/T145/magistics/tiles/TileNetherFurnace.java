package T145.magistics.tiles;

import net.minecraft.world.World;
import T145.magistics.blocks.BlockNetherFurnace;

public class TileNetherFurnace extends TileModifiedFurnace {
	public TileNetherFurnace() {
		setDisplayName("Netherrack Furnace");
	}

	@Override
	public void updateFurnaceBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord) {
		BlockNetherFurnace.updateFurnaceBlockState(active, worldObj, xCoord, yCoord, zCoord);
	}

	private void speedUpSmelting() {
		if ((furnaceCookTime > 0) && (furnaceCookTime < 199)) {
			furnaceCookTime += 1;
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		speedUpSmelting();
	}
}