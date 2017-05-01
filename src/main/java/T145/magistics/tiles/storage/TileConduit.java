package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintessenceContainer;
import T145.magistics.api.magic.IQuintessenceManager;
import T145.magistics.api.magic.QuintessenceHelper;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileConduit extends MTile implements IQuintessenceContainer {

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
	public float getMaxQuintessence() {
		return 4F;
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
		compound.setFloat("Quints", quints);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		setQuintessence(compound.getFloat("Quints"));
	}

	@Override
	public void update() {
		if (!world.isRemote) {
			calculateSuction();

			if (suction > 0) {
				equalizeWithNeighbors();
				refresh();
			}
		}
	}

	protected void calculateSuction() {
		setSuction(0);

		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintessenceManager source = QuintessenceHelper.getConnectedManager(world, pos, facing);

			if (source != null && getSuction() < source.getSuction() - 1) {
				setSuction(source.getSuction() - 1);
			}
		}
	}

	protected void equalizeWithNeighbors() {
		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintessenceContainer container = QuintessenceHelper.getConnectedContainer(world, pos, facing);

			if (container != null && quints < getMaxQuintessence() && getSuction() > container.getSuction()) {
				float diff = QuintessenceHelper.subtractQuints(container, Math.min(1F, getMaxQuintessence() - quints));

				if (getSuction() > container.getSuction()) {
					quints += diff;
				} else {
					container.setQuintessence(diff + container.getQuintessence());
				}
			}
		}
	}
}