package T145.magistics.tiles;

import T145.magistics.api.tiles.IVisContainer;
import T145.magistics.api.tiles.IVisManager;
import T145.magistics.api.tiles.TileVisContainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class TileConduit extends TileVisContainer {

	protected static int[] connections = new int[EnumFacing.VALUES.length];

	public float maxVis = 4F;
	private float fillAmount = 4F;
	public float displayPure;
	public float displayMiasma;
	public float prevDisplayPure;
	public float prevDisplayMiasma;

	public static boolean hasConnection(EnumFacing facing) {
		return connections[facing.getIndex()] > 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		connections = tag.getIntArray("Connections");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setIntArray("Connections", connections);

		return tag;
	}

	@Override
	public void update() {
		if (hasWorldObj()) {
			// check for connections
			for (EnumFacing dir : EnumFacing.VALUES) {
				IVisManager manager = getConnectableTile(dir);

				if (manager != null && manager.getConnectable(dir)) {
					connections[dir.getIndex()] = 1;
				} else {
					connections[dir.getIndex()] = 0;
				}
			}

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

			displayMiasma = Math.max(displayMiasma, MathHelper.clamp_float(vis, 0F, maxVis));
			displayPure = Math.max(displayPure, MathHelper.clamp_float(miasma, 0F, maxVis));

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

				if (vis + miasma < maxVis && (getVisSuction() > container.getVisSuction() || getMiasmaSuction() > container.getMiasmaSuction())) {
					float min = Math.min((container.getVis() + container.getMiasma()) / 4F, fillAmount);
					float[] total = container.subtractVis(Math.min(min, maxVis - (vis + miasma)));

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

		vis = MathHelper.clamp_float(vis, 0F, maxVis);
		miasma = MathHelper.clamp_float(miasma, 0F, maxVis);
	}

	@Override
	public boolean getConnectable(EnumFacing facing) {
		return true;
	}
}