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

	public static short drain(IQuintContainer container, float amount, boolean doDrain) {
		return container.drain((short) Math.min(amount, container.getQuints()), doDrain);
	}

	public static short fill(TileEntity dest, float amount, boolean doDrain) {
		short total = 0;

		for (EnumFacing side : EnumFacing.VALUES) {
			IQuintContainer container = getConnectedContainer(dest.getWorld(), dest.getPos(), side);

			if (container != null) {
				total = drain(container, amount, doDrain);
			}

			if (total >= amount) {
				break;
			}
		}

		return (short) Math.min(amount, total);
	}
}