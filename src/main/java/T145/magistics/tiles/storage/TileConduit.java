package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.IQuintManager;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileConduit extends MTile implements IQuintContainer {

	protected int suction;
	protected float quints;
	protected float displayQuints;

	public float getDisplayQuints() {
		return displayQuints;
	}

	public void setDisplayQuints(float displayQuints) {
		this.displayQuints = displayQuints;
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
		compound.setFloat("DisplayQuints", displayQuints);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		setQuints(compound.getFloat("Quints"));
		setDisplayQuints(compound.getFloat("DisplayQuints"));
	}

	protected void calculateSuction() {
		setSuction(0);

		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintManager source = QuintHelper.getConnectedManager(world, pos, facing);

			if (source != null && suction < source.getSuction() - 1) {
				setSuction(source.getSuction() - 1);
			}
		}
	}

	protected void equalizeWithNeighbors() {
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

	protected void calculateDisplayQuints() {
		if (quints >= 3.1F) {
			if (displayQuints < getMaxQuints()) {
				displayQuints += 0.2F;
			}
		} else if (quints <= 0.1F) {
			displayQuints = 0F;
		} else {
			displayQuints = quints;
		}

		if (quints < 0F) {
			quints = 0F;
		}
	}

	@Override
	public void update() {
		if (!world.isRemote) {
			refresh();
			calculateSuction();

			if (suction > 0) {
				equalizeWithNeighbors();
				calculateDisplayQuints();
			}
		}
	}
}