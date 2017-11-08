package T145.magistics.api.magic;

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class QuintHelper {

	private QuintHelper() {}

	@Nullable
	public static IQuintContainer getConnectedContainer(World world, BlockPos pos, EnumFacing side) {
		TileEntity currentTile = world.getTileEntity(pos);
		TileEntity neighborTile = world.getTileEntity(pos.offset(side));

		if (currentTile instanceof IQuintContainer && neighborTile instanceof IQuintContainer) {
			IQuintContainer current = (IQuintContainer) currentTile;
			IQuintContainer neighbor = (IQuintContainer) neighborTile;

			if (current.canConnectAtSide(side) && neighbor.canConnectAtSide(side.getOpposite())) {
				return neighbor;
			}
		}

		return null;
	}

	public static float drainQuints(IQuintContainer container, float amount, boolean doDrain) {
		float drainAmount = Math.min(amount, container.getQuints());

		if (drainAmount < 0.001F) {
			drainAmount = 0;
		}

		if (doDrain) {
			container.setQuints(container.getQuints() - drainAmount);
		}

		return drainAmount;
	}

	public static float fillWithQuints(World world, BlockPos pos, float amount, boolean doDrain) {
		TileEntity destTile = world.getTileEntity(pos);
		float total = 0F;

		if (destTile instanceof IQuintContainer) {
			IQuintContainer dest = (IQuintContainer) world.getTileEntity(pos);
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
}