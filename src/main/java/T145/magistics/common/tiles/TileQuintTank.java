package T145.magistics.common.tiles;

import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.common.network.PacketHandler;
import T145.magistics.common.network.client.MessageUpdateContainer;
import T145.magistics.common.tiles.base.TileBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileQuintTank extends TileBase implements ITickable, IQuintContainer {

	private float quints;
	private int suction;

	public boolean canConnectToTank(EnumFacing facing) {
		TileEntity tile = world.getTileEntity(pos.offset(facing));
		return tile instanceof TileQuintTank && facing.getAxis() == EnumFacing.Axis.Y;
	}

	public boolean isFull() {
		return quints == getCapacity();
	}

	@Override
	public boolean canConnectAtSide(EnumFacing side) {
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
	public float getQuints() {
		return suction;
	}

	@Override
	public void setQuints(float quints) {
		this.quints = quints;
	}

	@Override
	public float getCapacity() {
		return 50F;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setFloat("Quints", quints);
		tag.setInteger("Suction", suction);
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		quints = tag.getFloat("Quints");
		suction = tag.getInteger("Suction");
	}

	public void updateQuintLevel() {
		PacketHandler.sendToAllAround(new MessageUpdateContainer(pos, quints, suction), world, pos);
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		setSuction(10);
	}
}