package T145.magistics.api.magic;

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class QuintHelper {

	private QuintHelper() {}

	@Nullable
	public static IQuintProxy getConnectedProvider(IBlockAccess world, BlockPos pos, EnumFacing side) {
		TileEntity currentTile = world.getTileEntity(pos);
		TileEntity neighborTile = world.getTileEntity(pos.offset(side));

		if (currentTile instanceof IQuintProxy && neighborTile instanceof IQuintProxy) {
			IQuintProxy current = (IQuintProxy) currentTile;
			IQuintProxy neighbor = (IQuintProxy) neighborTile;

			if (current.canConnectAtSide(side) && neighbor.canConnectAtSide(side.getOpposite())) {
				return neighbor;
			}
		}

		return null;
	}

	@Nullable
	public static IQuintContainer getConnectedContainer(IBlockAccess world, BlockPos pos, EnumFacing side) {
		IQuintProxy provider = getConnectedProvider(world, pos, side);

		if (provider instanceof IQuintContainer) {
			return (IQuintContainer) provider;
		}

		return null;
	}

	public static float fill(TileEntity dest, float amount, boolean doDrain) {
		float total = 0F;

		for (EnumFacing side : EnumFacing.VALUES) {
			IQuintContainer container = getConnectedContainer(dest.getWorld(), dest.getPos(), side);

			if (container != null) {
				total = container.drain(Math.min(amount, container.getQuints()), doDrain);
			}

			if (total >= amount) {
				break;
			}
		}

		return  Math.min(amount, total);
	}
}