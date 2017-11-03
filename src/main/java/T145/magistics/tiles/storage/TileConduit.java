package T145.magistics.tiles.storage;

import T145.magistics.Magistics;
import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.network.PacketHandler;
import T145.magistics.network.messages.client.MessageQuintLevel;
import T145.magistics.tiles.base.TileSynchronized;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileConduit extends TileSynchronized implements ITickable, IQuintContainer {

	private ConduitPhase currentPhase;
	private float quints;
	private int suction;
	private int faceIndex;

	public TileConduit() {
		this.currentPhase = ConduitPhase.CALCULATING;
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
		nbt.setString("Phase", currentPhase.toString());
		nbt.setFloat("Quints", quints);
		nbt.setInteger("Suction", suction);
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		currentPhase = ConduitPhase.valueOf(nbt.getString("Phase"));
		setQuints(nbt.getFloat("Quints"));
		setSuction(nbt.getInteger("Suction"));
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		IQuintContainer neighbor = QuintHelper.getConnectedContainer(world, pos, EnumFacing.getFront(faceIndex));

		Magistics.LOG.info("Phase: " + currentPhase.name());
		Magistics.LOG.info("Suction: " + suction);

		if (neighbor != null && faceIndex < 6) {
			if (currentPhase.isCalculation() && suction < neighbor.getSuction() - 1) {
				setSuction(neighbor.getSuction() - 1);
			}

			if (currentPhase.isDistribution()) {
				if (suction > neighbor.getSuction()) {
					if (quints < getCapacity() && neighbor.getQuints() > 0) {
						++quints;
						neighbor.setQuints(neighbor.getQuints() - 1);
					}
				} else if (quints > 0 && neighbor.getQuints() < neighbor.getCapacity()) {
					neighbor.setQuints(neighbor.getQuints() + 1);
				}
			}
		} else if (faceIndex == 6) {
			faceIndex = 0;

			if (currentPhase.isDistribution()) {
				setSuction(0);
				PacketHandler.sendToAllAround(new MessageQuintLevel(pos, quints, suction), world, pos);
			}

			currentPhase = currentPhase.shift();
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