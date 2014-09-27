package T145.magistics.common.tiles;

import net.minecraft.tileentity.TileEntityEnderChest;

public class TileChestHungryEnder extends TileEntityEnderChest {
	@Override
	public boolean receiveClientEvent(int eventID, int recievedData) {
		switch (eventID) {
		case 1:
			field_145973_j = recievedData;
			return true;
		case 2:
			if (field_145972_a < recievedData / 10.0F)
				field_145972_a = recievedData / 10.0F;
			return true;
		default:
			return tileEntityInvalid;
		}
	}

	@Override
	public void func_145969_a() {
		++field_145973_j;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 1, field_145973_j);
	}

	@Override
	public void func_145970_b() {
		--field_145973_j;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 1, field_145973_j);
	}
}