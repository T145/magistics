package T145.magistics.tiles.machines;

import T145.magistics.api.magic.IQuintessenceContainer;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileCrucible extends MTile implements IQuintessenceContainer {

	private float quints;
	private float maxQuints;
	private float conversion;
	private float speed;

	public TileCrucible(int tier) {
		switch (tier) {
		case 1:
			maxQuints = 600F;
			conversion = 0.6F;
			speed = 0.5F;
			break;
		case 2:
			maxQuints = 750F;
			conversion = 0.7F;
			speed = 0.75F;
			break;
		case 3:
			maxQuints = 750F;
			conversion = 0.4F;
			speed = 0.75F;
			break;
		default:
			maxQuints = 500F;
			conversion = 0.5F;
			speed = 0.25F;
			break;
		}
	}

	@Override
	public boolean canConnect(EnumFacing facing) {
		return facing.getAxis() != EnumFacing.Axis.Y;
	}

	@Override
	public int getSuction() {
		return 0;
	}

	@Override
	public void setSuction(int suction) {}

	@Override
	public float getMaxQuintessence() {
		return maxQuints;
	}

	@Override
	public float getQuintessence() {
		return quints;
	}

	@Override
	public void setQuintessence(float amount) {
		quints = amount;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setFloat("Quintessence", quints);
		compound.setFloat("MaxQuints", maxQuints);
		compound.setFloat("ConversionRate", conversion);
		compound.setFloat("Speed", speed);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		quints = compound.getFloat("Quintessence");
		maxQuints = compound.getFloat("MaxQuints");
		conversion = compound.getFloat("ConversionRate");
		speed = compound.getFloat("Speed");
	}

	@Override
	public void update() {
		// TODO Implement
	}
}