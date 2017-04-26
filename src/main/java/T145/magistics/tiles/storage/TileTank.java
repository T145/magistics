package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintessenceContainer;
import T145.magistics.api.magic.QuintessenceHelper;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileTank extends MTile implements IQuintessenceContainer {

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
	public float getQuintessence() {
		return quints;
	}

	@Override
	public float getMaxQuintessence() {
		return 500F;
	}

	@Override
	public void setQuintessence(float amount) {
		quints = amount;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setFloat("Quintessence", quints);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		setQuintessence(compound.getFloat("Quintessence"));
	}

	@Override
	public void update() {
		if (!world.isRemote) {
			--delay;

			if (delay < 0) {
				delay = 10;
				calculateSuction();

				// explode if certain conditions are met

				equalizeWithNeighbors();
				refresh();
			}
		}
	}

	protected void calculateSuction() {
		setSuction(10);
	}

	protected void equalizeWithNeighbors() {
		float tempMax = getMaxQuintessence();

		for (EnumFacing facing : EnumFacing.HORIZONTALS) {
			IQuintessenceContainer container = QuintessenceHelper.getConnectedContainer(world, pos, facing);

			if (container != null && !(container instanceof TileTank) && quints < tempMax && getSuction() > container.getSuction()) {
				float diff = QuintessenceHelper.subtractQuints(container, Math.min(1F, tempMax - quints));

				if (getSuction() > container.getSuction()) {
					quints += diff;
				} else {
					container.setQuintessence(diff + container.getQuintessence());
				}
			}
		}
	}
}