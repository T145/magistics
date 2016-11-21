package T145.magistics.api.tiles;

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class TileVisManager extends TileMagistics implements ITickable, IVisManager {

	private int visSuction = 0;
	private int miasmaSuction = 0;

	@Nullable
	public IVisManager getConnectableTile(EnumFacing facing) {
		BlockPos dest = new BlockPos(pos.getX() + facing.getFrontOffsetX(), pos.getY() + facing.getFrontOffsetY(), pos.getZ() + facing.getFrontOffsetZ());
		TileEntity tile = worldObj.getTileEntity(dest);
		IVisManager visManager = null;

		if (tile instanceof IVisManager && tile != null && ((IVisManager) tile).getConnectable(facing)) {
			visManager = (IVisManager) tile;
		}

		return visManager;
	}

	public float drainAvailableVis(float amount, boolean drain) {
		setVisSuction(50);

		float mod = 0F;

		for (EnumFacing facing : EnumFacing.VALUES) {
			IVisManager visManager = getConnectableTile(facing);

			if (visManager != null && visManager instanceof IVisContainer) {
				IVisContainer visContainer = (IVisContainer) visManager;
				float vis = Math.min(amount - mod, visContainer.getVis());

				if (vis < 0.001F) {
					vis = 0F;
				}

				mod += vis;

				if (drain) {
					visContainer.setVis(visContainer.getVis() - vis);
				}
			}

			if (mod >= amount) {
				break;
			}
		}

		return Math.min(amount, mod);
	}

	public float drainAvailableMiasma(float amount, boolean drain) {
		setMiasmaSuction(50);

		float mod = 0F;

		for (EnumFacing facing : EnumFacing.VALUES) {
			IVisManager visManager = getConnectableTile(facing);

			if (visManager != null && visManager instanceof IVisContainer) {
				IVisContainer visContainer = (IVisContainer) visManager;
				float vis = Math.min(amount - mod, visContainer.getMiasma());

				if (vis < 0.001F) {
					vis = 0F;
				}

				mod += vis;

				if (drain) {
					visContainer.setMiasma(visContainer.getMiasma() - vis);
				}
			}

			if (mod >= amount) {
				break;
			}
		}

		return Math.min(amount, mod);
	}

	@Override
	public abstract boolean getConnectable(EnumFacing facing);

	@Override
	public int getVisSuction() {
		return visSuction;
	}

	@Override
	public int getMiasmaSuction() {
		return miasmaSuction;
	}

	@Override
	public int getSuction() {
		return Math.max(visSuction, miasmaSuction);
	}

	@Override
	public void setVisSuction(int pressure) {
		visSuction = pressure;
	}

	@Override
	public void setMiasmaSuction(int pressure) {
		miasmaSuction = pressure;
	}

	@Override
	public void setSuction(int pressure) {
		setVisSuction(pressure);
		setMiasmaSuction(pressure);
	}

	@Override
	public void update() {
		setSuction(0);
	}
}