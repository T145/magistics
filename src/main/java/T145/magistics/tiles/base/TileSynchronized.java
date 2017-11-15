package T145.magistics.tiles.base;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import T145.magistics.api.magic.QuintHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileSynchronized extends TileEntity {

	public boolean isConnected(EnumFacing side) {
		return QuintHelper.getConnectedHandler(world, pos, side) != null;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Nonnull
	@Override
	@OverridingMethodsMustInvokeSuper
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagCompound tag = super.writeToNBT(nbt);
		writeCustomNBT(tag);
		return tag;
	}

	@Nonnull
	@Override
	@OverridingMethodsMustInvokeSuper
	public final NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	@OverridingMethodsMustInvokeSuper
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readCustomNBT(nbt);
	}

	public void writeCustomNBT(NBTTagCompound nbt) {}

	public void readCustomNBT(NBTTagCompound nbt) {}

	@Override
	@OverridingMethodsMustInvokeSuper
	public final SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeCustomNBT(tag);
		return new SPacketUpdateTileEntity(pos, -999, tag);
	}

	@Override
	@OverridingMethodsMustInvokeSuper
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		readCustomNBT(packet.getNbtCompound());
	}

	public IBlockState getState() {
		return world.getBlockState(pos);
	}

	public void markForUpdate() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, getState(), getState(), 3);
		world.scheduleBlockUpdate(pos, getBlockType(), 0, 0);
		markDirty();
	}
}