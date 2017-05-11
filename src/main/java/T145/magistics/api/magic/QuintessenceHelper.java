package T145.magistics.api.magic;

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class QuintessenceHelper {

	@Nullable
	public static IQuintessenceManager getConnectedManager(World world, BlockPos pos, EnumFacing facing) {
		TileEntity tile = world.getTileEntity(pos);
		TileEntity neighbor = world.getTileEntity(pos.offset(facing));

		if (tile instanceof IQuintessenceManager && neighbor instanceof IQuintessenceManager && ((IQuintessenceManager) tile).canConnect(facing) && ((IQuintessenceManager) neighbor).canConnect(facing.getOpposite())) {
			return (IQuintessenceManager) neighbor;
		}

		return null;
	}

	@Nullable
	public static IQuintessenceContainer getConnectedContainer(World world, BlockPos pos, EnumFacing facing) {
		IQuintessenceManager manager = getConnectedManager(world, pos, facing);

		if (manager instanceof IQuintessenceContainer) {
			return (IQuintessenceContainer) manager;
		}

		return null;
	}

	public static float drainQuints(World world, BlockPos dest, float amount, boolean drain) {
		TileEntity destTile = world.getTileEntity(dest);
		float mod = 0F;

		if (destTile instanceof IQuintessenceManager) {
			IQuintessenceManager manager = (IQuintessenceManager) destTile;
			manager.setSuction(50);

			for (EnumFacing facing : EnumFacing.VALUES) {
				IQuintessenceContainer source = getConnectedContainer(world, dest, facing);

				if (source != null) {
					float quint = Math.min(amount - mod, source.getQuintessence());

					if (quint < 0.001F) {
						quint = 0;
					}

					mod += quint;

					if (drain) {
						source.setQuintessence(source.getQuintessence() - quint);
					}
				}

				if (mod >= amount) {
					break;
				}
			}
		}

		return Math.min(amount, mod);
	}

	public static float subtractQuints(IQuintessenceContainer container, float amount) {
		float quints = amount / 2F;
		float mod = 0F;

		if (amount > 0.001F) {
			if (container.getQuintessence() < quints) {
				quints = container.getQuintessence();
			}

			if (quints < amount / 2F) {
				quints = Math.min(amount - quints, container.getQuintessence());
			}

			container.setQuintessence(container.getQuintessence() - quints);
			mod = quints;
		}

		return mod;
	}
}