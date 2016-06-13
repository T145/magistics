package T145.magistics.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.tiles.TileEldritchObelisk;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileInfuserDark extends TileInfuser {
	private TileEldritchObelisk obelisk = null;
	private boolean nearObelisk = false;
	private int sparkDelay = 40;

	@Override
	public boolean isDark() {
		return true;
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		nearObelisk = tag.getBoolean("nearObelisk");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setBoolean("nearObelisk", nearObelisk);
	}

	@SideOnly(Side.CLIENT)
	public int getDarkCookProgressScaled(int time) {
		return 0;
	}

	@Override
	public void updateEntity() {
		if (nearObelisk) {
			super.updateEntity();

			if (sparkDelay <= 0) {
				PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockZap(obelisk.xCoord + 0.5F, obelisk.yCoord + 0.5F, obelisk.zCoord + 0.5F, xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 32.0D));
				sparkDelay = 80;
			} else {
				--sparkDelay;
			}
		} else {
			int range = 6;

			for (int x = -range; x <= range; ++x) {
				for (int y = -range; y <= range; ++y) {
					for (int z = -range; z <= range; ++z) {
						TileEntity tile = worldObj.getTileEntity(xCoord + x, yCoord + y, zCoord + z);

						if (tile != null && tile instanceof TileEldritchObelisk) {
							obelisk = (TileEldritchObelisk) tile;
							nearObelisk = true;
							return;
						}
					}
				}
			}

			obelisk = null;
			nearObelisk = false;
		}
	}

	@Override
	public String getInventoryName() {
		return "Dark Infuser";
	}
}