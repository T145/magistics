package T145.magistics.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;

public class TileLiquidDetector extends TileCrystalMachine {
	private boolean powerState = false;

	@Override
	public boolean canRotate() {
		return true;
	}

	public boolean canPower() {
		return powerState;
	}

	public void setState(boolean state) {
		powerState = state;
	}

	@Override
	public void update() {
		for (int i = xCoord - 1; i <= xCoord + 1; ++i)
			for (int j = yCoord - 1; j <= yCoord + 1; ++j)
				for (int k = zCoord - 1; k <= zCoord + 1; ++k) {
					Block block = worldObj.getBlock(i, j, k);

					if (block instanceof BlockLiquid) {
						setState(true);
					}
				}
	}
}