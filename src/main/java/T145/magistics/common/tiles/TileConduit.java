package T145.magistics.common.tiles;

import T145.magistics.api.magic.FillPriority;
import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.common.tiles.base.TileBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileConduit extends TileBase implements ITickable, IQuintContainer {

	private float quints;

	@Override
	public FillPriority getPriority() {
		return FillPriority.MEDIUM;
	}

	@Override
	public boolean canConnectAtSide(EnumFacing side) {
		return true;
	}

	@Override
	public float fill(float amount, boolean doFill) {
		return 0;
	}

	@Override
	public float drain(float amount, boolean doDrain) {
		return 0;
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
		return 4;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setFloat("Quints", quints);
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		quints = tag.getFloat("Quints");
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}
	}
}