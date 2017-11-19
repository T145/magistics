package T145.magistics.common.tiles.base;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileBase extends TileEntity {

	public static final int PACKET_ID = -999;

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Nonnull
	@Override
	@OverridingMethodsMustInvokeSuper
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		NBTTagCompound nbt = super.writeToNBT(tag);
		writeCustomNBT(nbt);
		return nbt;
	}

	@Nonnull
	@Override
	@OverridingMethodsMustInvokeSuper
	public final NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		readCustomNBT(tag);
	}

	public void writeCustomNBT(NBTTagCompound tag) {}

	public void readCustomNBT(NBTTagCompound tag) {}

	@Override
	@OverridingMethodsMustInvokeSuper
	public final SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeCustomNBT(tag);
		return new SPacketUpdateTileEntity(pos, PACKET_ID, tag);
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
}