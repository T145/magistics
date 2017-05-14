package T145.magistics.api.magic;

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class QuintHelper {

	@Nullable
	public static IQuintManager getConnectedManager(World world, BlockPos pos, EnumFacing facing) {
		TileEntity tile = world.getTileEntity(pos);
		TileEntity neighbor = world.getTileEntity(pos.offset(facing));

		if (tile instanceof IQuintManager && neighbor instanceof IQuintManager && ((IQuintManager) tile).canConnect(facing) && ((IQuintManager) neighbor).canConnect(facing.getOpposite())) {
			return (IQuintManager) neighbor;
		}

		return null;
	}

	@Nullable
	public static IQuintContainer getConnectedContainer(World world, BlockPos pos, EnumFacing facing) {
		IQuintManager manager = getConnectedManager(world, pos, facing);

		if (manager instanceof IQuintContainer) {
			return (IQuintContainer) manager;
		}

		return null;
	}

	public static float drainQuints(World world, BlockPos dest, float amount, boolean drain) {
		TileEntity destTile = world.getTileEntity(dest);
		float mod = 0F;

		if (destTile instanceof IQuintManager) {
			IQuintManager manager = (IQuintManager) destTile;
			manager.setSuction(50);

			for (EnumFacing facing : EnumFacing.VALUES) {
				IQuintContainer source = getConnectedContainer(world, dest, facing);

				if (source != null) {
					float quint = Math.min(amount - mod, source.getQuints());

					if (quint < 0.001F) {
						quint = 0;
					}

					mod += quint;

					if (drain) {
						source.setQuints(source.getQuints() - quint);
					}
				}

				if (mod >= amount) {
					break;
				}
			}
		}

		return Math.min(amount, mod);
	}

	public static float subtractQuints(IQuintContainer source, float amount) {
		float magicAmount = amount / 2F;
		float voidAmount = amount / 2F;
		float[] diff = new float[] { 0F, 0F };

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
			diff[0] = magicAmount;
			diff[1] = voidAmount;
			return magicAmount;
		}
	}
}