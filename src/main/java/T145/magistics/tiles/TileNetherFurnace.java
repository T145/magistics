package T145.magistics.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import T145.magistics.blocks.BlockNetherFurnace;

public class TileNetherFurnace extends TileModifiedFurnace {
	@Override
	public void updateFurnaceBlockState(boolean b, World worldObj, int xCoord, int yCoord, int zCoord) {
		BlockNetherFurnace.updateFurnaceBlockState(b, worldObj, xCoord, yCoord, zCoord);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if ((furnaceCookTime > 0) && (furnaceCookTime < 199)) {
			furnaceCookTime += 1;
		}
	}
}