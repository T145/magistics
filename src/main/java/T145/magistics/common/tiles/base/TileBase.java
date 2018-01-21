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
	@OverridingMethodsMustInvokeSuper
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagCompound tag = super.writeToNBT(nbt);
		writePacketNBT(tag);
		return tag;
	}

	@Nonnull
	@OverridingMethodsMustInvokeSuper
	@Override
	public final NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@OverridingMethodsMustInvokeSuper
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readPacketNBT(nbt);
	}

	public void writePacketNBT(NBTTagCompound nbt) {}

	public void readPacketNBT(NBTTagCompound nbt) {}

	@OverridingMethodsMustInvokeSuper
	@Override
	public final SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writePacketNBT(tag);
		return new SPacketUpdateTileEntity(pos, PACKET_ID, tag);
	}

	@OverridingMethodsMustInvokeSuper
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		readPacketNBT(packet.getNbtCompound());
	}
}