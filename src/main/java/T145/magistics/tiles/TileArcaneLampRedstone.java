package T145.magistics.tiles;

import thaumcraft.common.tiles.TileArcaneLamp;
import T145.magistics.blocks.BlockArcaneLampRedstone;

public class TileArcaneLampRedstone extends TileArcaneLamp {
	@Override
	public void updateEntity() {
		if (getBlockType() == BlockArcaneLampRedstone.ACTIVE) {
			super.updateEntity();
		} else {
			removeLights();
		}
	}
}