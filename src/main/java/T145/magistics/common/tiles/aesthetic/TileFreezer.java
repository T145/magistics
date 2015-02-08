package T145.magistics.common.tiles.aesthetic;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileFreezer extends TileEntity {
	public boolean isWater(Block block) {
		if (block == Blocks.water || block == Blocks.flowing_water)
			return true;
		else
			return false;
	}

	@Override
	public void updateEntity() {
		for (int i = xCoord - 1; i <= xCoord + 1; i++)
			for (int j = yCoord - 1; j <= yCoord + 1; j++)
				for (int k = zCoord - 1; k <= zCoord + 1; k++)
					if (isWater(worldObj.getBlock(i, j, k)))
						worldObj.setBlock(i, j, k, Blocks.ice);
	}
}