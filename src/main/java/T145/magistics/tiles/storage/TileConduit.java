package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.IQuintHandler;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.network.PacketHandler;
import T145.magistics.network.messages.client.MessageQuintLevel;
import T145.magistics.tiles.base.TileSynchronized;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileConduit extends TileSynchronized implements ITickable, IQuintContainer {

	private ConduitPhase phase;
	private float quints;
	private int suction;
	private int faceIndex;

	public TileConduit() {
		this.phase = ConduitPhase.CALCULATING;
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
		return 4F;
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
		nbt.setString("Phase", phase.toString());
		nbt.setFloat("Quints", quints);
		nbt.setInteger("Suction", suction);
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		phase = ConduitPhase.valueOf(nbt.getString("Phase"));
		setQuints(nbt.getFloat("Quints"));
		setSuction(nbt.getInteger("Suction"));
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		IQuintHandler handler = QuintHelper.getConnectedHandler(world, pos, EnumFacing.getFront(faceIndex));

		if (handler != null) {
			int neighborSuction = handler.getSuction() - 1;

			if (phase.isCalculation() && suction < neighborSuction) {
				setSuction(neighborSuction);
			}

			if (phase.isDistribution() && handler instanceof IQuintContainer) {
				IQuintContainer neighbor = (IQuintContainer) handler;

				if (suction > neighbor.getSuction()) {
					if (quints < getCapacity() && neighbor.getQuints() > 0) {
						++quints;
						neighbor.setQuints(neighbor.getQuints() - 1);
					}
				} else if (quints > 0 && neighbor.getQuints() < neighbor.getCapacity()) {
					neighbor.setQuints(neighbor.getQuints() + 1);
				}
			}
		}

		if (faceIndex == 6) {
			faceIndex = -1;

			if (phase.isDistribution()) {
				setSuction(0);
				PacketHandler.sendToAllAround(new MessageQuintLevel(pos, quints, suction), world, pos);
			}

			phase = phase.shift();
		}

		++faceIndex;
	}

	private static enum ConduitPhase {
		CALCULATING,
		DISTRIBUTING;

		public boolean isCalculation() {
			return this == CALCULATING;
		}

		public boolean isDistribution() {
			return this == DISTRIBUTING;
		}

		public ConduitPhase shift() {
			return this == CALCULATING ? DISTRIBUTING : CALCULATING;
		}
	}
}