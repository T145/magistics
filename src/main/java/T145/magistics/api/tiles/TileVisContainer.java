package T145.magistics.api.tiles;

import T145.magistics.lib.events.WorldEventHandler;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileVisContainer extends TileVisManager implements IVisContainer {

	protected float vis;
	protected float miasma;

	public float[] subtractVis(float amount) {
		float visAmount = amount / 2F;
		float miasmaAmount = amount / 2F;
		float[] result = { 0F, 0F };

		if (amount < 0.001F) {
			return result;
		}

		if (vis < visAmount) {
			visAmount = vis;
		}

		if (miasma < miasmaAmount) {
			miasmaAmount = miasma;
		}

		if (visAmount < amount / 2F && miasmaAmount == amount / 2F) {
			miasmaAmount = Math.min(amount - visAmount, miasma);
		} else if (miasmaAmount < amount / 2F && visAmount == amount / 2F) {
			visAmount = Math.min(amount - miasmaAmount, vis);
		}

		vis -= visAmount;
		miasma -= miasmaAmount;

		result[0] = visAmount;
		result[1] = miasmaAmount;

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