package T145.magistics.common.tiles;

import T145.magistics.api.magic.FillPriority;
import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.common.tiles.base.TileBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileConduit extends TileBase implements ITickable, IQuintContainer {

	@Override
	public FillPriority getPriority() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canConnectAtSide(EnumFacing side) {
		return true;
	}

	@Override
	public short fill(short amount, boolean doFill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short drain(short amount, boolean doDrain) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getQuints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}