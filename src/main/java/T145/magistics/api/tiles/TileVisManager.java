package T145.magistics.api.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileVisManager extends TileMagistics implements ITickable, IVisManager {

	private int visSuction = 0;
	private int taintSuction = 0;

	public IVisManager getConnectableTile(EnumFacing facing) {
		BlockPos pos = new BlockPos(getPos().getX() + facing.getFrontOffsetX(), getPos().getY() + facing.getFrontOffsetY(), getPos().getZ() + facing.getFrontOffsetZ());
		TileEntity tile = worldObj.getTileEntity(pos);
		IVisManager visManager = null;

		if (tile instanceof IVisManager) {
			visManager = (IVisManager) tile;
		}

		return visManager;
	}

	public float drainAvailablePureVis(float amount, boolean drain) {
		setVisSuction(50);

		float mod = 0F;

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (getConnectable(facing)) {
				IVisManager visManager = getConnectableTile(facing);

				if (visManager != null && (visManager.isVisConduit() || visManager.isVisSource())) {
					float vis = Math.min(amount - mod, visManager.getPureVis());

					if (vis < 0.001F) {
						vis = 0F;
					}

					mod += vis;

					if (drain) {
						visManager.setPureVis(visManager.getPureVis() - vis);
					}
				}

				if (mod >= amount) {
					break;
				}
			}
		}

		return Math.min(amount, mod);
	}

	public float drainAvailableTaintedVis(float amount, boolean drain) {
		setTaintSuction(50);

		float mod = 0F;

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (getConnectable(facing)) {
				IVisManager visManager = getConnectableTile(facing);

				if (visManager != null && (visManager.isVisConduit() || visManager.isVisSource())) {
					float vis = Math.min(amount - mod, visManager.getTaintedVis());

					if (vis < 0.001F) {
						vis = 0F;
					}

					mod += vis;

					if (drain) {
						visManager.setTaintedVis(visManager.getTaintedVis() - vis);
					}
				}

				if (mod >= amount) {
					break;
				}
			}
		}

		return Math.min(amount, mod);
	}

	@Override
	public boolean getConnectable(EnumFacing facing) {
		return false;
	}

	@Override
	public boolean isVisSource() {
		return false;
	}

	@Override
	public boolean isVisConduit() {
		return false;
	}

	@Override
	public float[] subtractVis(float amount) {
		return new float[] { 0F, 0F };
	}

	@Override
	public float getPureVis() {
		return 0F;
	}

	@Override
	public void setPureVis(float amount) {}

	@Override
	public float getTaintedVis() {
		return 0F;
	}

	@Override
	public void setTaintedVis(float amount) {}

	@Override
	public float getMaxVis() {
		return 0F;
	}

	@Override
	public int getVisSuction(BlockPos pos) {
		return visSuction;
	}

	@Override
	public void setVisSuction(int amount) {
		visSuction = amount;
	}

	@Override
	public int getTaintSuction(BlockPos pos) {
		return taintSuction;
	}

	@Override
	public void setTaintSuction(int amount) {
		taintSuction = amount;
	}

	@Override
	public void setSuction(int pressure) {
		setVisSuction(pressure);
		setTaintSuction(pressure);
	}

	@Override
	public int getSuction(BlockPos amount) {
		return Math.max(visSuction, taintSuction);
	}

	@Override
	public void update() {
		setSuction(0);
	}
}