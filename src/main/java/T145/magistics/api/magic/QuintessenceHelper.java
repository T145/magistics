package T145.magistics.api.magic;

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class QuintessenceHelper {

	@Nullable
	public static IQuintessenceManager getConnectedTile(TileEntity source, EnumFacing facing) {
		if (source != null && source instanceof IQuintessenceManager) {
			BlockPos destPos = new BlockPos(source.getPos().getX() + facing.getFrontOffsetX(), source.getPos().getY() + facing.getFrontOffsetY(), source.getPos().getZ() + facing.getFrontOffsetZ());
			TileEntity dest = source.getWorld().getTileEntity(destPos);
			IQuintessenceManager connected = (IQuintessenceManager) dest;

			if (connected != null && ((IQuintessenceManager) source).hasConnection(facing) && connected.hasConnection(facing.getOpposite())) {
				return connected;
			}
		}

		return null;
	}

	public static float drainQuintessence(TileEntity dest, float amount, boolean drain) {
		if (dest != null && dest instanceof IQuintessenceManager) {
			IQuintessenceManager manager = (IQuintessenceManager) dest;
			float mod = 0F;

			manager.setSuction(50);

			for (EnumFacing facing : EnumFacing.VALUES) {
				IQuintessenceManager source = getConnectedTile(dest, facing);

				if (source != null && source.isContainer()) {
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

			return Math.min(amount, mod);
		}

		return 0F;
	}
}