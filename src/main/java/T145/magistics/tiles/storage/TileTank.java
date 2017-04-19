package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintessenceContainer;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileTank extends MTile implements IQuintessenceContainer {

	private int suction;
	private float quintessence;

	@Override
	public boolean canConnect(EnumFacing facing) {
		return true;
	}

	@Override
	public int getSuction() {
		return suction;
	}

	@Override
	public void setSuction(int suction) {
		this.suction = suction;
	}

	@Override
	public float getQuintessence() {
		return this.quintessence;
	}

	@Override
	public void setQuintessence(float amount) {
		this.quintessence = amount;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setInteger("Suction", suction);
		compound.setFloat("Quintessence", quintessence);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		setSuction(compound.getInteger("Suction"));
		setQuintessence(compound.getFloat("Quintessence"));
	}

	@Override
	public void update() {
		// TODO Implement
	}
}