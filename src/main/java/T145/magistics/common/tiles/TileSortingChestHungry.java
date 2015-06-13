package T145.magistics.common.tiles;

import com.dynious.refinedrelocation.tileentity.TileSortingChest;

public class TileSortingChestHungry extends TileSortingChest {
	@Override
	public boolean receiveClientEvent(int id, int data) {
		switch (id) {
		case 2:
			if (lidAngle < data / 10F)
				lidAngle = data / 10F;
			return true;
		default:
			return false;
		}
	}
}