package T145.magistics.api.tiles;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileVisUser extends TileMagistics implements ITickable, IConnection {

	private int visSuction = 0;
	private int taintSuction = 0;

	public IConnection getConnectableTile(EnumFacing facing) {
		BlockPos pos = new BlockPos(getPos().getX() + facing.getFrontOffsetX(), getPos().getY() + facing.getFrontOffsetY(), getPos().getZ() + facing.getFrontOffsetZ());
		IConnection connection = (IConnection) worldObj.getTileEntity(pos);
		return connection;
	}

	public boolean getExactPureVis(float amount) {
		setVisSuction(50);

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (getConnectable(facing)) {
				IConnection connection = getConnectableTile(facing);

				if (connection != null && (connection.isVisConduit() || connection.isVisSource())) {
					connection.setPureVis(connection.getPureVis() - amount);
					return true;
				}
			}
		}

		return false;
	}

	public float getAvailablePureVis(float amount) {
		setVisSuction(50);

		float mod = 0F;

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (getConnectable(facing)) {
				IConnection connection = getConnectableTile(facing);

				if (connection != null && (connection.isVisConduit() || connection.isVisSource())) {
					float vis = Math.min(amount - mod, connection.getPureVis());

					if (vis < 0.001F) {
						vis = 0F;
					}

					mod += vis;
					connection.setPureVis(connection.getPureVis() - vis);
				}

				if (mod >= amount) {
					break;
				}
			}
		}

		return Math.min(amount, mod);
	}

	public float getAvailableTaintedVis(float amount) {
		setTaintSuction(50);

		float mod = 0F;

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (getConnectable(facing)) {
				IConnection connection = getConnectableTile(facing);

				if (connection != null && (connection.isVisConduit() || connection.isVisSource())) {
					float vis = Math.min(amount - mod, connection.getTaintedVis());

					if (vis < 0.001F) {
						vis = 0F;
					}

					mod += vis;
					connection.setTaintedVis(connection.getTaintedVis() - vis);
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