package T145.magistics.common.tiles;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileFluidVoid extends TileEntity {
	@Override
	public void updateEntity() {
		for (int i = xCoord - 1; i <= xCoord + 1; i++)
			for (int j = yCoord - 1; j <= yCoord + 1; j++)
				for (int k = zCoord - 1; k <= zCoord + 1; k++)
					if (worldObj.getBlock(i, j, k).getMaterial().isLiquid())
						worldObj.setBlock(i, j, k, Blocks.air);
	}
}