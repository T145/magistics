package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintessenceContainer;
import T145.magistics.api.magic.QuintessenceHelper;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
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
		compound.setInteger("Suction", suction);
		compound.setFloat("Quintessence", quints);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		setSuction(compound.getInteger("Suction"));
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
		TileEntity tile;
		int yOffset;

		for (yOffset = 1; (tile = world.getTileEntity(pos.offset(EnumFacing.UP, yOffset))) instanceof TileTank; ++yOffset) {
			TileTank tank = (TileTank) tile;

			if (isReinforced() == tank.isReinforced()) {
				quints += tank.getQuintessence();
				tempMax += tank.getMaxQuintessence();
			}
		}

		for (EnumFacing facing : EnumFacing.VALUES) {
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

		float tempQuints = quints;

		if (Math.round(tempQuints) >= tempMax) {
			setSuction(0);
		}

		yOffset = 0;

		for (boolean empty = false; (tile = world.getTileEntity(pos.offset(EnumFacing.UP, yOffset))) instanceof TileTank; ++yOffset) {
			TileTank tank = (TileTank) tile;

			if (isReinforced() == tank.isReinforced()) {
				if (empty) {
					tank.setQuintessence(0F);
				} else if (tempQuints <= tank.getMaxQuintessence()) {
					tank.setQuintessence(quints);
					empty = true;
				} else {
					tank.setQuintessence(tank.getMaxQuintessence() * (quints / tempQuints));
					quints -= tank.getQuintessence();
				}

				tempQuints = quints;
			}
		}
	}
}