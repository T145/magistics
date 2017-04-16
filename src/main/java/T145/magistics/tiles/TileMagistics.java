package T145.magistics.tiles;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileMagistics extends TileEntity implements ITickable {

	private EnumFacing facing = EnumFacing.SOUTH;

	public void setFacing(EnumFacing facing) {
		this.facing = facing;
	}

	public EnumFacing getFacing() {
		return facing;
	}

	public int getFrontAngle(boolean rotateNorthSouth, boolean rotateEastWest) {
		switch (facing.ordinal()) {
		case 2:
			return rotateEastWest ? -180 : 180;
		case 4:
			return rotateNorthSouth ? 90 : -90;
		case 5:
			return rotateNorthSouth ? -90 : 90;
		default:
			return 0;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("facing", facing.ordinal());
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		facing = EnumFacing.getFront(compound.getInteger("facing"));
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound compound = getUpdateTag();
		return new SPacketUpdateTileEntity(getPos(), 0, compound);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound compound = new NBTTagCompound();
		writeToNBT(compound);
		return compound;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public void update() {
		//Magistics.LOGGER.info("Facing: " + facing.ordinal());
	}
}