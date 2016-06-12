package T145.magistics.tiles;

import net.minecraft.tileentity.TileEntity;
import thaumcraft.common.tiles.TileEldritchObelisk;

public class TileInfuserDark extends TileInfuser {
	@Override
	public boolean isDark() {
		return true;
	}

	public boolean isNearObelisk() {
		boolean nearObelisk = false;
		int range = 6;

		for (int x = -range; x <= range; x++) {
			for (int y = -range; y <= range; y++) {
				for (int z = -range; z <= range; z++) {
					TileEntity tile = worldObj.getTileEntity(x, y, z);

					if (tile != null && tile instanceof TileEldritchObelisk) {
						nearObelisk = true;
					}
				}
			}
		}

		return nearObelisk;
	}

	public int getDarkCookProgressScaled(int time) {
		return 0;
	}

	@Override
	public String getInventoryName() {
		return "Dark Infuser";
	}
}