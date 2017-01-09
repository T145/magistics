package T145.magistics.tiles;

import T145.magistics.api.tiles.IVisContainer;
import T145.magistics.api.tiles.IVisManager;
import T145.magistics.api.tiles.TileVisContainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public class TileConduit extends TileVisContainer {

	public static final float MAX_VIS = 4F;
	protected static final float FILL_AMOUNT = 4F;

	public float displayPure;
	public float displayMiasma;
	public float prevDisplayPure;
	public float prevDisplayMiasma;

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		return tag;
	}

	@Override
	public void update() {
		if (hasWorldObj()) {
			// check for connections

			// distribute vis appropriately
			if (prevDisplayPure != displayPure || prevDisplayMiasma != displayMiasma) {
				worldObj.scheduleUpdate(pos, getBlockType(), 0);
				prevDisplayPure = displayPure;
				prevDisplayMiasma = displayMiasma;
			}

			calculateSuction();

			if (getSuction() > 0) {
				equalizeWithNeighbours();
			}

			displayMiasma = Math.max(displayMiasma, MathHelper.clamp_float(vis, 0F, MAX_VIS));
			displayPure = Math.max(displayPure, MathHelper.clamp_float(miasma, 0F, MAX_VIS));

			if (displayMiasma + displayPure < 0.1F) {
				displayMiasma = 0F;
				displayPure = 0F;
			}
		}
	}

	protected void calculateSuction() {
		setSuction(0);

		for (EnumFacing dir : EnumFacing.VALUES) {
			IVisManager manager = getConnectableTile(dir);

			if (manager != null) {
				if (getVisSuction() < manager.getVisSuction() - 1) {
					setVisSuction(manager.getVisSuction() - 1);
				}

				if (getMiasmaSuction() < manager.getMiasmaSuction() - 1) {
					setMiasmaSuction(manager.getMiasmaSuction() - 1);
				}
			}
		}
	}

	protected void equalizeWithNeighbours() {
		for (EnumFacing dir : EnumFacing.VALUES) {
			IVisManager manager = this.getConnectableTile(dir);

			if (manager != null && manager instanceof IVisContainer) {
				IVisContainer container = (IVisContainer) manager;

				if (vis + miasma < MAX_VIS && (getVisSuction() > container.getVisSuction() || getMiasmaSuction() > container.getMiasmaSuction())) {
					float min = Math.min((container.getVis() + container.getMiasma()) / 4F, FILL_AMOUNT);
					float[] total = container.subtractVis(Math.min(min, MAX_VIS - (vis + miasma)));

					if (getVisSuction() > container.getVisSuction()) {
						vis += total[0];
					} else {
						container.setVis(total[0] + container.getVis());
					}

					if (getMiasmaSuction() > container.getMiasmaSuction()) {
						miasma += total[1];
					} else {
						container.setMiasma(total[1] + container.getMiasma());
					}
				}
			}
		}

		vis = MathHelper.clamp_float(vis, 0F, MAX_VIS);
		miasma = MathHelper.clamp_float(miasma, 0F, MAX_VIS);
	}

	@Override
	public boolean getConnectable(EnumFacing facing) {
		return true;
	}
}