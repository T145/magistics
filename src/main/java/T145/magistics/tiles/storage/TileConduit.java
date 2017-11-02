package T145.magistics.tiles.storage;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.magic.QuintHelper;
import T145.magistics.network.PacketHandler;
import T145.magistics.network.messages.client.MessageQuintLevel;
import T145.magistics.tiles.base.TileSynchronized;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileConduit extends TileSynchronized implements ITickable, IQuintContainer {

	protected float quints;
	protected int suction;

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
		quints = nbt.getFloat("Quints");
		suction = nbt.getInteger("Suction");
	}

	public void updateQuintLevel() {
		PacketHandler.INSTANCE.sendToAllAround(new MessageQuintLevel(pos, quints, suction), PacketHandler.getTargetPoint(world, pos));
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		calculateSuction();

		if (suction > 0) {
			distributeQuints();
			updateQuintLevel();
		}
	}

	private void calculateSuction() {
		setSuction(0);

		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintContainer source = QuintHelper.getConnectedContainer(world, pos, facing);

			if (source != null && suction < source.getSuction() - 1) {
				setSuction(source.getSuction() - 1);
			}
		}
	}

	private void distributeQuints() {
		for (EnumFacing facing : EnumFacing.VALUES) {
			IQuintContainer container = QuintHelper.getConnectedContainer(world, pos, facing);

			if (container != null && quints < getCapacity() && suction > container.getSuction()) {
				float ratio = Math.min(container.getQuints() / getCapacity(), getCapacity());
				float diff = QuintHelper.subtractQuints(container, Math.min(ratio, getCapacity() - quints));

				if (suction > container.getSuction()) {
					quints += diff;
				} else {
					container.setQuints(diff + container.getQuints());
				}
			}
		}
	}
}