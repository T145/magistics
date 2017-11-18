package T145.magistics.common.tiles;

import T145.magistics.api.magic.FillPriority;
import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.common.tiles.base.TileBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileConduit extends TileBase implements ITickable, IQuintContainer {

	private int quints;

	@Override
	public FillPriority getPriority() {
		return FillPriority.MEDIUM;
	}

	@Override
	public boolean canConnectAtSide(EnumFacing side) {
		return true;
	}

	@Override
	public int fill(int amount, boolean doFill) {
		return 0;
	}

	@Override
	public int drain(int amount, boolean doDrain) {
		return 0;
	}

	@Override
	public int getQuints() {
		return quints;
	}

	@Override
	public void setQuints(int quints) {
		this.quints = quints;
	}

	@Override
	public int getCapacity() {
		return 4;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setInteger("Quints", quints);
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		quints = tag.getInteger("Quints");
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}
	}
}