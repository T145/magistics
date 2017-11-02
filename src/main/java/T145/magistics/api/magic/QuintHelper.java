package T145.magistics.api.magic;

import javax.annotation.Nullable;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class QuintHelper {

	private QuintHelper() {}

	@Nullable
	public static IQuintContainer getConnectedContainer(World world, BlockPos pos, EnumFacing side) {
		IQuintContainer current = (IQuintContainer) world.getTileEntity(pos);
		IQuintContainer neighbor = (IQuintContainer) world.getTileEntity(pos.offset(side));

		if (current != null && neighbor != null && current.canConnectAtSide(side) && neighbor.canConnectAtSide(side.getOpposite())) {
			return neighbor;
		}

		return null;
	}

	public static float drainQuints(IQuintContainer container, float amount, boolean doDrain) {
		float total = 0F;
		float drainAmount = Math.min(amount - total, container.getQuints());

		if (drainAmount < 0.001F) {
			drainAmount = 0;
		}

		total += drainAmount;

		if (doDrain) {
			container.setQuints(container.getQuints() - drainAmount);
		}

		return total;
	}

	public static float fillWithQuints(World world, BlockPos pos, float amount, boolean doDrain) {
		float total = 0F;

		IQuintContainer dest = (IQuintContainer) world.getTileEntity(pos);

		if (dest != null) {
			dest.setSuction(dest.MAX_SUCTION);

			for (EnumFacing side : EnumFacing.VALUES) {
				IQuintContainer container = getConnectedContainer(world, pos, side);

				if (container != null) {
					total = drainQuints(container, amount, doDrain);
				}

				if (total >= amount) {
					break;
				}
			}
		}

		return Math.min(amount, total);
	}

	public static float subtractQuints(IQuintContainer source, float amount) {
		float magicAmount = amount / 2F;
		float voidAmount = amount / 2F;

		if (amount < 0.001F) {
			return 0F;
		} else {
			if (source.getQuints() < magicAmount) {
				magicAmount = source.getQuints();
			}

			if (source.getQuints() < voidAmount) {
				voidAmount = source.getQuints();
			}

			if (magicAmount < amount / 2F && voidAmount == amount / 2F) {
				voidAmount = Math.min(amount - magicAmount, source.getQuints());
			} else if (voidAmount < amount / 2F && magicAmount == amount / 2F) {
				magicAmount = Math.min(amount - voidAmount, source.getQuints());
			}

			source.setQuints(source.getQuints() - magicAmount);
			return magicAmount;
		}
	}
}