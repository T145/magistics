package T145.magistics.api.magic;

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class QuintHelper {

	private QuintHelper() {}

	@Nullable
	public static IQuintHandler getConnectedHandler(World world, BlockPos pos, EnumFacing side) {
		TileEntity currentTile = world.getTileEntity(pos);
		TileEntity neighborTile = world.getTileEntity(pos.offset(side));

		if (currentTile instanceof IQuintHandler && neighborTile instanceof IQuintHandler) {
			IQuintHandler current = (IQuintHandler) currentTile;
			IQuintHandler neighbor = (IQuintHandler) neighborTile;

			if (current.canConnectAtSide(side) && neighbor.canConnectAtSide(side.getOpposite())) {
				return neighbor;
			}
		}

		return null;
	}

	@Nullable
	public static IQuintContainer getConnectedContainer(World world, BlockPos pos, EnumFacing side) {
		IQuintHandler handler = getConnectedHandler(world, pos, side);

		if (handler instanceof IQuintContainer) {
			return (IQuintContainer) handler;
		}

		return null;
	}

	public static float drain(IQuintContainer container, float amount, boolean doDrain) {
		float drainAmount = Math.min(amount, container.getQuints());

		if (drainAmount < 0.001F) {
			drainAmount = 0;
		}

		if (doDrain) {
			container.setQuints(container.getQuints() - drainAmount);
		}

		return drainAmount;
	}

	public static float fill(TileEntity dest, float amount, boolean doDrain) {
		float total = 0F;

		if (dest instanceof IQuintHandler) {
			((IQuintHandler) dest).setSuction(IQuintHandler.MAX_SUCTION);

			for (EnumFacing side : EnumFacing.VALUES) {
				IQuintContainer container = getConnectedContainer(dest.getWorld(), dest.getPos(), side);

				if (container != null) {
					total = drain(container, amount, doDrain);
				}

				if (total >= amount) {
					break;
				}
			}
		}

		return Math.min(amount, total);
	}
}