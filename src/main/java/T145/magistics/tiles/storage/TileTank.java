package T145.magistics.tiles.storage;

import javax.annotation.Nullable;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileTank extends MTile implements IQuintContainer {

	protected final boolean reinforced;
	protected final float maxQuints;
	protected float quints;
	protected int suction;
	private int updateDelay;

	public TileTank(boolean reinforced) {
		this.reinforced = reinforced;
		maxQuints = reinforced ? 1000F : 500F;
	}

	public boolean isReinforced() {
		return reinforced;
	}

	@Override
	public boolean canConnect(EnumFacing facing) {
		TileEntity tile = world.getTileEntity(pos.offset(facing));

		if (tile instanceof TileTank && facing.getAxis() == EnumFacing.Axis.Y) {
			TileTank neighborTank = (TileTank) tile;
			return reinforced == neighborTank.isReinforced();
		}

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
			--updateDelay;

			if (updateDelay <= 0) {
				refresh();

				updateDelay = 10;
				calculateSuction();
			}

			distributeQuints();
		}
	}

	private void calculateSuction() {
		setSuction(10);
	}

	private void distributeQuints() {
		float tempQuints = quints;
		float tempMaxQuints = getMaxQuints();
		TileTank tank;
		int offsetY = 1;

		while ((tank = getValidTank(offsetY)) != null) {
			tempQuints += tank.getQuints();
			tempMaxQuints += tank.getMaxQuints();
			++offsetY;
		}

		for (EnumFacing facing : EnumFacing.HORIZONTALS) {
			IQuintContainer container = QuintHelper.getConnectedContainer(world, pos, facing);

			if (container != null && !(container instanceof TileTank) && tempQuints < tempMaxQuints && suction > container.getSuction()) {
				float diff = QuintHelper.subtractQuints(container, Math.min(1F, tempMaxQuints - tempQuints));

				if (suction > container.getSuction()) {
					tempQuints += diff;
				} else {
					container.setQuints(diff + container.getQuints());
				}
			}
		}

		float prevTempQuints = tempQuints;

		if (Math.round(prevTempQuints) >= tempMaxQuints) {
			setSuction(0);
		}

		float quintRatio = tempQuints / prevTempQuints;
		boolean empty = false;
		offsetY = 0;

		while ((tank = getValidTank(offsetY)) != null) {
			if (empty) {
				tank.setQuints(0F);
			} else if (prevTempQuints <= tank.getMaxQuints()) {
				tank.setQuints(tempQuints);
				empty = true;
			} else {
				tank.setQuints(tank.getMaxQuints() * quintRatio);
				tempQuints -= tank.getQuints();
			}

			prevTempQuints = tempQuints;
			++offsetY;
		}
	}

	@Nullable
	protected TileTank getValidTank(int offsetY) {
		TileEntity tile = world.getTileEntity(pos.up(offsetY));

		if (tile instanceof TileTank) {
			TileTank tank = (TileTank) tile;

			if (tank.canConnect(EnumFacing.UP)) {
				return tank;
			}
		}

		return null;
	}
}