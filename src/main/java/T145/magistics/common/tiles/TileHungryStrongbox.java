package T145.magistics.common.tiles;

import cofh.thermalexpansion.block.strongbox.BlockStrongbox;
import cofh.thermalexpansion.block.strongbox.TileStrongbox;

public class TileHungryStrongbox extends TileStrongbox {
	public TileHungryStrongbox(int n) {
		super(n);
	}

	@Override
	public boolean receiveClientEvent(int id, int data) {
		switch (id) {
		case 0:
			numUsingPlayers = data;
			return true;
		case 1:
			if (lidAngle < data / 10F)
				lidAngle = data / 10F;
			return true;
		default:
			return false;
		}
	}

	@Override
	public String getName() {
		return "tile.magistics.strongbox." + BlockStrongbox.NAMES[getType()] + ".name";
	}
}