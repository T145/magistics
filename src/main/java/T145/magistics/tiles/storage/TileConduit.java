package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.IQuintManager;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileConduit extends MTile implements IQuintContainer {

	private int suction;
	private float quints;

	public boolean hasQuints() {
		return quints > 0;
	}

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
	public float getMaxQuints() {
		return 4F;
	}

	@Override
	public float getQuints() {
		return quints;
	}

	@Override
	public void setQuints(float amount) {
		quints = amount;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setFloat("Quints", quints);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		setQuints(compound.getFloat("Quints"));
	}

	@Override
	public void update() {
		if (!world.isRemote) {
			refresh();
			calculateSuction();

			if (suction > 0) {
				equalizeWithNeighbors();
			}
		}
	}

	protected void calculateSuction() {
		setSuction(0);

		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintManager source = QuintHelper.getConnectedManager(world, pos, facing);

			if (source != null && getSuction() < source.getSuction() - 1) {
				setSuction(source.getSuction() - 1);
			}
		}
	}

	protected void equalizeWithNeighbors() {
		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintContainer container = QuintHelper.getConnectedContainer(world, pos, facing);

			if (container != null && quints < getMaxQuints() && suction > container.getSuction()) {
				float ratio = Math.min(container.getQuints() / getMaxQuints(), 4F);
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