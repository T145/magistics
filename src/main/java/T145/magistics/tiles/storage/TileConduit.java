package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.IQuintHandler;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.blocks.storage.BlockConduit;
import T145.magistics.network.PacketHandler;
import T145.magistics.network.messages.client.MessageQuintLevel;
import T145.magistics.tiles.base.TileSynchronized;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileConduit extends TileSynchronized implements ITickable, IQuintContainer {

	private float quints;
	private int suction;

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
		nbt.setFloat("Quints", quints);
		nbt.setInteger("Suction", suction);
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		setQuints(nbt.getFloat("Quints"));
		setSuction(nbt.getInteger("Suction"));
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		setSuction(0);

		for (int side = 0; side < EnumFacing.VALUES.length; ++side) {
			boolean connected = getState().getValue(BlockConduit.CONNECTIONS.get(side));

			if (connected) {
				IQuintHandler handler = QuintHelper.getConnectedHandler(world, pos, EnumFacing.getFront(side));

				if (handler != null) {
					int neighborSuction = handler.getSuction() - 1;

					if (suction < neighborSuction) {
						setSuction(neighborSuction);
					}
				}
			}
		}

		if (suction > 0) {
			for (int side = 0; side < EnumFacing.VALUES.length; ++side) {
				boolean connected = getState().getValue(BlockConduit.CONNECTIONS.get(side));

				if (connected) {
					IQuintContainer container = QuintHelper.getConnectedContainer(world, pos, EnumFacing.getFront(side));

					if (container != null) {
						if (suction > container.getSuction()) {
							if (quints < getCapacity() && container.getQuints() > 0) {
								++quints;
								container.setQuints(container.getQuints() - 1);
							}
						} else if (quints > 0 && container.getQuints() < container.getCapacity()) {
							container.setQuints(container.getQuints() + 1);
						}

						PacketHandler.sendToAllAround(new MessageQuintLevel(pos, quints, suction), world, pos);
					}
				}
			}
		}
	}
}