package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileTank extends MTile implements IQuintContainer {

	private int delay;
	private int suction;
	private float quints;

	public boolean isReinforced() {
		return false;
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
	public float getQuints() {
		return quints;
	}

	@Override
	public float getMaxQuints() {
		return 500F;
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
			--delay;

			if (delay <= 0) {
				refresh();

				delay = 10;
				calculateSuction();
			}

			equalizeWithNeighbors();
		}
	}

	public void calculateSuction() {
		setSuction(10);
	}

	protected void equalizeWithNeighbors() {
		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintContainer container = QuintHelper.getConnectedContainer(world, pos, facing);

			if (container != null && quints < getMaxQuints() && getSuction() > container.getSuction()) {
				if (container instanceof TileTank && facing.getAxis() == EnumFacing.Axis.Y) {
					equalizeWithTanks((TileTank) container, facing);
				} else {
					equalizeWithContainers(container, facing);
				}
			}
		}
	}

	private void equalizeWithTanks(TileTank neighbor, EnumFacing facing) {
	}

	private void equalizeWithContainers(IQuintContainer container, EnumFacing facing) {
		float diff = QuintHelper.subtractQuints(container, Math.min(1F, getMaxQuints() - quints));

		if (getSuction() > container.getSuction()) {
			quints += diff;
		} else {
			container.setQuints(diff + container.getQuints());
		}
	}
}