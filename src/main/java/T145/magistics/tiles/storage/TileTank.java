package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintessenceContainer;
import T145.magistics.api.magic.IQuintessenceManager;
import T145.magistics.api.magic.QuintessenceHelper;
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
	public float getMaxQuintessence() {
		return getBlockMetadata() == 1 ? 1000F : 500F;
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

	public void calculateSuction() {
		this.setSuction(10);

		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintessenceManager manager = QuintessenceHelper.getConnectedTile(this, facing);

			if (manager != null) {
				// if influenced by pump, then suction += 10
			}
		}
	}

	protected void equalizeWithNeighbors() {
	}

	public float subtractQuints(float amount) {
		float quints = amount / 2F;
		float mod = 0F;

		if (!(amount < 0.001F)) {
			if (this.quintessence < quints) {
				quints = this.quintessence;
			}

			if (quints < amount / 2F) {
				quints = Math.min(amount - quints, quintessence);
			}

			this.quintessence -= quints;
			mod = quints;
		}

		return mod;
	}
}