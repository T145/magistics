package T145.magistics.api.tiles;

import javax.annotation.Nullable;

import T145.magistics.lib.events.WorldEventHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class TileVisManager extends TileMagistics implements ITickable, IVisManager {

	protected float vis;
	protected float miasma;

	private int visSuction = 0;
	private int miasmaSuction = 0;

	@Nullable
	public IVisManager getConnectableTile(EnumFacing facing) {
		BlockPos pos = new BlockPos(getPos().getX() + facing.getFrontOffsetX(), getPos().getY() + facing.getFrontOffsetY(), getPos().getZ() + facing.getFrontOffsetZ());
		TileEntity tile = worldObj.getTileEntity(pos);
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
			if (getConnectable(facing)) {
				IVisManager visManager = getConnectableTile(facing);

				if (visManager != null) {
					float vis = Math.min(amount - mod, visManager.getVis());

					if (vis < 0.001F) {
						vis = 0F;
					}

					mod += vis;

					if (drain) {
						visManager.setVis(visManager.getVis() - vis);
					}
				}

				if (mod >= amount) {
					break;
				}
			}
		}

		return Math.min(amount, mod);
	}

	public float drainAvailableMiasma(float amount, boolean drain) {
		setMiasmaSuction(50);

		float mod = 0F;

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (getConnectable(facing)) {
				IVisManager visManager = getConnectableTile(facing);

				if (visManager != null) {
					float vis = Math.min(amount - mod, visManager.getMiasma());

					if (vis < 0.001F) {
						vis = 0F;
					}

					mod += vis;

					if (drain) {
						visManager.setMiasma(visManager.getMiasma() - vis);
					}
				}

				if (mod >= amount) {
					break;
				}
			}
		}

		return Math.min(amount, mod);
	}

	public float[] subtractVis(float amount) {
		float pureAmount = amount / 2F;
		float taintAmount = amount / 2F;
		float[] result = { 0F, 0F };

		if (amount < 0.001F) {
			return result;
		}

		if (vis < pureAmount) {
			pureAmount = vis;
		}

		if (miasma < taintAmount) {
			taintAmount = miasma;
		}

		if (pureAmount < amount / 2F && taintAmount == amount / 2F) {
			taintAmount = Math.min(amount - pureAmount, miasma);
		} else if (taintAmount < amount / 2F && pureAmount == amount / 2F) {
			pureAmount = Math.min(amount - taintAmount, vis);
		}

		vis -= pureAmount;
		miasma -= taintAmount;

		result[0] = pureAmount;
		result[1] = taintAmount;

		return result;
	}

	@Override
	public abstract boolean getConnectable(EnumFacing facing);

	@Override
	public float getVis() {
		return vis;
	}

	@Override
	public float getMiasma() {
		return miasma;
	}

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
	public void setVis(float amount) {
		vis = amount;
	}

	@Override
	public void setMiasma(float amount) {
		miasma = amount;
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
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		vis = tag.getFloat(WorldEventHandler.KEY_VIS);
		miasma = tag.getFloat(WorldEventHandler.KEY_MIASMA);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setFloat(WorldEventHandler.KEY_VIS, vis);
		tag.setFloat(WorldEventHandler.KEY_MIASMA, miasma);
		return tag;
	}

	@Override
	public void update() {
		setSuction(0);
	}
}