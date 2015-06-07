package T145.magistics.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class TileFreezer extends TileCrystalMachine {
	@Override
	public boolean canRotate() {
		return false;
	}

	@Override
	public void update() {
		for (int i = xCoord - 1; i <= xCoord + 1; ++i)
			for (int j = yCoord - 1; j <= yCoord + 1; ++j)
				for (int k = zCoord - 1; k <= zCoord + 1; ++k) {
					Block block = worldObj.getBlock(i, j, k);

					if (block.equals(Blocks.flowing_water) || block.equals(Blocks.water)) {
						worldObj.setBlock(i, j, k, Blocks.ice);
					}
				}
	}
}