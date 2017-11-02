package T145.magistics.tiles.storage;

import javax.annotation.Nullable;

import T145.magistics.Magistics;
import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.network.PacketHandler;
import T145.magistics.network.messages.client.MessageQuintLevel;
import T145.magistics.tiles.base.TileSynchronized;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileQuintTank extends TileSynchronized implements ITickable, IQuintContainer {

	protected final boolean reinforced;
	protected final float maxQuints;
	protected float quints;
	protected int suction;
	private int updateDelay;

	public TileQuintTank(boolean reinforced) {
		this.reinforced = reinforced;
		this.maxQuints = reinforced ? 1000F : 500F;
	}

	public TileQuintTank() {
		this(false);
	}

	public boolean isReinforced() {
		return reinforced;
	}

	public boolean canConnectToTank(EnumFacing facing) {
		TileEntity tile = world.getTileEntity(pos.offset(facing));

		if (tile instanceof TileQuintTank && facing.getAxis() == EnumFacing.Axis.Y) {
			TileQuintTank neighborTank = (TileQuintTank) tile;
			return reinforced == neighborTank.isReinforced();
		}

		return false;
	}
	
	@Override
	public boolean canConnectAtSide(EnumFacing side) {
		return true;
	}

	@Override
	public float getQuints() {
		return quints;
	}

	@Override
	public void setQuints(float quints) {
		this.quints = quints;
	}

	@Override
	public float getCapacity() {
		return maxQuints;
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
	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setFloat("Quints", quints);
		nbt.setInteger("Suction", suction);
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		quints = nbt.getFloat("Quints");
		suction = nbt.getInteger("Suction");
	}

	@Override
	public void update() {
		if (!world.isRemote) {
			--updateDelay;

			if (updateDelay <= 0) {
				//markForUpdate();
				PacketHandler.INSTANCE.sendToAllAround(new MessageQuintLevel(pos, quints, suction), PacketHandler.getTargetPoint(world, pos));

				updateDelay = 10;
				calculateSuction();
				Magistics.LOG.info("Tank Quints: " + quints);
			}

			distributeQuints();
		}
	}

	private void calculateSuction() {
		setSuction(10);
	}

	private void distributeQuints() {
		float tempQuints = quints;
		float tempMaxQuints = getCapacity();
		TileQuintTank tank;
		int offsetY = 1;

		while ((tank = getValidTank(offsetY)) != null) {
			tempQuints += tank.getQuints();
			tempMaxQuints += tank.getCapacity();
			++offsetY;
		}

		for (EnumFacing facing : EnumFacing.HORIZONTALS) {
			IQuintContainer container = QuintHelper.getConnectedContainer(world, pos, facing);

			if (container != null && !(container instanceof TileQuintTank) && tempQuints < tempMaxQuints && suction > container.getSuction()) {
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
			} else if (prevTempQuints <= tank.getCapacity()) {
				tank.setQuints(tempQuints);
				empty = true;
			} else {
				tank.setQuints(tank.getCapacity() * quintRatio);
				tempQuints -= tank.getQuints();
			}

			prevTempQuints = tempQuints;
			++offsetY;
		}
	}

	@Nullable
	protected TileQuintTank getValidTank(int offsetY) {
		TileEntity tile = world.getTileEntity(pos.up(offsetY));

		if (tile instanceof TileQuintTank) {
			TileQuintTank tank = (TileQuintTank) tile;

			if (tank.canConnectToTank(EnumFacing.UP)) {
				return tank;
			}
		}

		return null;
	}
}