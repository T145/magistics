package T145.magistics.api.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileMagistics extends TileEntity {

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readCustomNBT(nbt);
	}

	public void readCustomNBT(NBTTagCompound nbt) {}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		return writeCustomNBT(nbt);
	}

	public NBTTagCompound writeCustomNBT(NBTTagCompound nbt) {
		return nbt;
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		return new SPacketUpdateTileEntity(getPos(), -999, writeCustomNBT(nbt));
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		readCustomNBT(pkt.getNbtCompound());
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	/*public EnumFacing getFacing() {
		try {
			return EnumFacing.getFront(getBlockMetadata() & 7);
		} catch (Exception err) {}

		return EnumFacing.UP;
	}*/

	public boolean isPowered() {
		return worldObj.isBlockPowered(getPos());
	}
}