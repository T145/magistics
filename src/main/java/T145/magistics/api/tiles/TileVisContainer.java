package T145.magistics.api.tiles;

import T145.magistics.lib.events.WorldEventHandler;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileVisContainer extends TileVisManager implements IVisContainer {

	protected float vis;
	protected float miasma;

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
	public float getVis() {
		return vis;
	}

	@Override
	public float getMiasma() {
		return miasma;
	}

	@Override
	public void setVis(float amount) {
		vis = amount;
	}

	@Override
	public void setMiasma(float amount) {
		miasma = amount;
	}
}