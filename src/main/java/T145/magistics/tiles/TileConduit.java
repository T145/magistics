package T145.magistics.tiles;

import T145.magistics.api.tiles.IConnection;
import T145.magistics.api.tiles.TileVisUser;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public class TileConduit extends TileVisUser {

	public static final float MAX_VIS = 4F;
	private float fillAmount = MAX_VIS;

	public float pureVis = 0F;
	public float taintedVis = 0F;

	public float displayPure;
	public float displayTaint;
	public float prevDisplayPure;
	public float prevDisplayTaint;

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		pureVis = tag.getFloat("pureVis");
		taintedVis = tag.getFloat("taintedVis");
	}

	@Override
	public void readClientDataFromNBT(NBTTagCompound tag) {
		pureVis = tag.getFloat("pureVis");
		taintedVis = tag.getFloat("taintedVis");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setFloat("pureVis", pureVis);
		tag.setFloat("taintedVis", taintedVis);
		return super.writeToNBT(tag);
	}

	@Override
	public void writeClientDataToNBT(NBTTagCompound tag) {
		tag.setFloat("pureVis", pureVis);
		tag.setFloat("taintedVis", taintedVis);
	}

	@Override
	public void update() {
		super.update();

		if (hasWorldObj()) {
			if (prevDisplayPure != displayPure || prevDisplayTaint != displayTaint) {
				markDirtyClient();
				prevDisplayPure = displayPure;
				prevDisplayTaint = displayTaint;
			}

			calculateSuction();

			if (getSuction(getPos()) > 0) {
				equalizeWithNeighbors();
			}

			displayTaint = Math.max(displayTaint, MathHelper.clamp_float(taintedVis, 0F, MAX_VIS));
			displayPure = Math.max(displayPure, MathHelper.clamp_float(pureVis, 0F, MAX_VIS));

			if (displayTaint + displayPure < 0.1F) {
				displayTaint = 0F;
				displayPure = 0F;
			}
		}
	}

	protected void calculateSuction() {
		setSuction(0);

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (getConnectable(facing)) {
				IConnection connection = getConnectableTile(facing);

				if (connection != null) {
					if (getVisSuction(getPos()) < connection.getVisSuction(getPos()) - 1) {
						setVisSuction(connection.getVisSuction(getPos()) - 1);
					}

					if (getTaintSuction(getPos()) < connection.getTaintSuction(getPos()) - 1) {
						setTaintSuction(connection.getTaintSuction(getPos()) - 1);
					}
				}
			}
		}
	}

	protected void equalizeWithNeighbors() {
		for (EnumFacing facing : EnumFacing.VALUES) {
			if (getConnectable(facing)) {
				IConnection connection = getConnectableTile(facing);

				if (connection != null) {
					if (pureVis + taintedVis < MAX_VIS && (getVisSuction(getPos()) > connection.getVisSuction(getPos()) || getTaintSuction(getPos()) > connection.getTaintSuction(getPos()))) {
						float amount = Math.min((connection.getPureVis() + connection.getTaintedVis()) / MAX_VIS, fillAmount);
						float[] visAmount = connection.subtractVis(Math.min(amount, MAX_VIS - (pureVis + taintedVis)));

						if (getVisSuction(getPos()) > connection.getVisSuction(getPos())) {
							pureVis += visAmount[0];
						} else {
							connection.setPureVis(visAmount[0] + connection.getPureVis());
						}

						if (getTaintSuction(getPos()) > connection.getTaintSuction(getPos())) {
							taintedVis += visAmount[1];
						} else {
							connection.setTaintedVis(visAmount[1] + connection.getTaintedVis());
						}
					}
				}
			}

			pureVis = MathHelper.clamp_float(pureVis, 0F, MAX_VIS);
			taintedVis = MathHelper.clamp_float(taintedVis, 0F, MAX_VIS);
		}
	}

	@Override
	public boolean getConnectable(EnumFacing facing) {
		return true;
	}

	@Override
	public boolean isVisSource() {
		return false;
	}

	@Override
	public boolean isVisConduit() {
		return true;
	}

	@Override
	public float[] subtractVis(float amount) {
		float pureAmount = amount / 2F;
		float taintedAmount = amount / 2F;
		float[] visAmount = new float[] { 0F, 0F };

		if (amount < 0.001F) {
			return visAmount;
		} else {
			if (pureVis < pureAmount) {
				pureAmount = pureVis;
			}

			if (taintedVis < taintedAmount) {
				taintedAmount = taintedVis;
			}

			if (pureAmount < amount / 2F && taintedAmount == amount / 2F) {
				taintedAmount = Math.min(amount - pureAmount, taintedVis);
			} else if (taintedAmount < amount / 2F && pureAmount == amount / 2F) {
				pureAmount = Math.min(amount - taintedAmount, pureVis);
			}

			pureVis -= pureAmount;
			taintedVis -= taintedAmount;
			visAmount[0] = pureAmount;
			visAmount[1] = taintedAmount;
			return visAmount;
		}
	}

	@Override
	public float getPureVis() {
		return pureVis;
	}

	@Override
	public void setPureVis(float amount) {
		pureVis = amount;
	}

	@Override
	public float getTaintedVis() {
		return taintedVis;
	}

	@Override
	public void setTaintedVis(float amount) {
		taintedVis = amount;
	}

	@Override
	public float getMaxVis() {
		return MAX_VIS;
	}
}