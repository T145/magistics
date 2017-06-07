package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.IQuintManager;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileConduit extends MTile implements IQuintContainer {

	protected final float maxQuints = 4F;
	protected float quints;
	protected int suction;

	@Override
	public boolean canConnect(EnumFacing facing) {
		return true;
	}

	@Override
	public int getSuction() {
		return suction;
	}

	@Override
	public void setSuction(int pressure) {
		suction = pressure;
	}

	@Override
	public float getMaxQuints() {
		return maxQuints;
	}

	@Override
	public float getQuints() {
		return quints;
	}

	@Override
	public float getDisplayQuints() {
		return quints > 0.1F ? quints : 0F;
	}

	@Override
	public void setQuints(float amount) {
		quints = amount;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setFloat("Quints", quints);
		compound.setInteger("Suction", suction);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		quints = compound.getFloat("Quints");
		suction = compound.getInteger("Suction");
	}

	@Override
	public void update() {
		if (!world.isRemote) {
			calculateSuction();

			if (suction > 0) {
				distributeQuints();
			}
		}
	}

	private void calculateSuction() {
		setSuction(0);

		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintManager source = QuintHelper.getConnectedManager(world, pos, facing);

			if (source != null && suction < source.getSuction() - 1) {
				setSuction(source.getSuction() - 1);
			}
		}
	}

	private void distributeQuints() {
		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintContainer container = QuintHelper.getConnectedContainer(world, pos, facing);

			if (container != null && quints < getMaxQuints() && suction > container.getSuction()) {
				float ratio = Math.min(container.getQuints() / getMaxQuints(), getMaxQuints());
				float diff = QuintHelper.subtractQuints(container, Math.min(ratio, getMaxQuints() - quints));

				if (suction > container.getSuction()) {
					quints += diff;
				} else {
					container.setQuints(diff + container.getQuints());
				}
			}
		}
	}
}