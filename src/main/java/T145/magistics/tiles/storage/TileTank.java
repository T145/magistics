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
	private float quintessence = 250;

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
		return quintessence;
	}

	@Override
	public float getMaxQuintessence() {
		return 500F;
	}

	@Override
	public void setQuintessence(float amount) {
		quintessence = amount;
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
		if (!world.isRemote) {
			--delay;

			if (delay < 0) {
				delay = 10;
				calculateSuction();

				// explode if certain conditions are met

				equalizeWithNeighbors();
				sendUpdates();
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
				quintessence += tank.getQuintessence();
				tempMax += tank.getMaxQuintessence();
			}
		}

		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintessenceContainer container = QuintessenceHelper.getConnectedContainer(world, pos, facing);

			if (container != null && !(container instanceof TileTank) && quintessence < tempMax && getSuction() > container.getSuction()) {
				float diff = QuintessenceHelper.subtractQuints(container, Math.min(1F, tempMax - quintessence));

				if (getSuction() > container.getSuction()) {
					quintessence += diff;
				} else {
					container.setQuintessence(diff + container.getQuintessence());
				}
			}
		}

		float tempQuints = quintessence;

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
					tank.setQuintessence(quintessence);
					empty = true;
				} else {
					tank.setQuintessence(tank.getMaxQuintessence() * (quintessence / tempQuints));
					quintessence -= tank.getQuintessence();
				}

				tempQuints = quintessence;
			}
		}
	}
}