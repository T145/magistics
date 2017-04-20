package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintessenceContainer;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileConduit extends MTile implements IQuintessenceContainer {

	@Override
	public boolean canConnect(EnumFacing facing) {
		return true;
	}

	@Override
	public int getSuction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSuction(int suction) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getMaxQuintessence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getQuintessence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setQuintessence(float amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}