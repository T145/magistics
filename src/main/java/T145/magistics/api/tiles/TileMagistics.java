package T145.magistics.api.tiles;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileMagistics extends TileEntity {

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
}