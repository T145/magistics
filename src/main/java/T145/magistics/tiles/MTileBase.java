package T145.magistics.tiles;

import T145.magistics.api.magic.QuintHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class MTileBase extends TileEntity {

	public boolean isConnected(EnumFacing side) {
		return QuintHelper.getConnectedManager(world, pos, side) != null;
	}

	public IBlockState getState() {
		return world.getBlockState(pos);
	}

	public void refresh() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, getState(), getState(), 3);
		world.scheduleBlockUpdate(pos, getBlockType(), 0, 0);
		world.notifyNeighborsOfStateChange(pos, blockType, true);
		markDirty();
	}

	public boolean isUsableByPlayer(EntityPlayer player) {
		return world.getTileEntity(pos) != this ? false : player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64D;
	}
}