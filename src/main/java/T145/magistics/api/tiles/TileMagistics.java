package T145.magistics.api.tiles;

import javax.annotation.Nullable;

import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileMagistics extends TileEntity implements IFacing {

	protected int facing = -1;

	public void markDirtyClient() {
		markDirty();

		if (worldObj != null) {
			IBlockState state = worldObj.getBlockState(getPos());
			worldObj.notifyBlockUpdate(getPos(), state, state, 3);
		}
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		writeClientDataToNBT(nbtTag);
		return new SPacketUpdateTileEntity(pos, 1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readClientDataFromNBT(packet.getNbtCompound());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound updateTag = super.getUpdateTag();
		writeClientDataToNBT(updateTag);
		return updateTag;
	}

	public void writeClientDataToNBT(NBTTagCompound tag) {
		writeToNBT(tag);
	}

	public void readClientDataFromNBT(NBTTagCompound tag) {
		readFromNBT(tag);
	}

	public boolean isPowered() {
		return worldObj.isBlockPowered(getPos());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		facing = tag.getInteger("Facing");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("Facing", facing);
		return tag;
	}

	@Override
	public int getFacing() {
		return facing;
	}

	@Override
	public int getFacingFromEntity(EntityLivingBase placer) {
		return BlockPistonBase.getFacingFromEntity(pos, placer).getIndex();
	}

	@Override
	public void setFacing(int front) {
		facing = front;
	}

	@Override
	public void setFacingFromEntity(EntityLivingBase placer) {
		setFacing(getFacingFromEntity(placer));
	}

	public boolean hasFront() {
		return facing > 0;
	}

	public int getFrontAngle(boolean rotateNorthSouth, boolean rotateEastWest) {
		switch (facing) {
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
}