package T145.magistics.common.tiles;

import com.dynious.refinedrelocation.tileentity.TileSortingIronChest;

import cpw.mods.ironchest.IronChestType;

public class TileSortingChestHungryMetal extends TileSortingIronChest {
	public TileSortingChestHungryMetal(IronChestType type) {
		super(type);
	}

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