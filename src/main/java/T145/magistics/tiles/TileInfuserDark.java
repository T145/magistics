package T145.magistics.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.tiles.TileEldritchObelisk;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileInfuserDark extends TileInfuser {
	protected int[] obeliskQuards = new int[3];
	private boolean nearObelisk = false;
	private int sparkDelay = 60;

	public TileInfuserDark() {
		inventoryStacks = new ItemStack[6];
	}

	@Override
	public boolean isDark() {
		return true;
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		super.readCustomNBT(tag);
		obeliskQuards = tag.getIntArray("obeliskQuards");
		nearObelisk = tag.getBoolean("nearObelisk");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		super.writeCustomNBT(tag);
		tag.setIntArray("obeliskQuards", obeliskQuards);
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
				PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockZap(obeliskQuards[0] + 0.5F, obeliskQuards[1] + 0.5F, obeliskQuards[2] + 0.5F, xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 32.0D));
				sparkDelay = 100;
			} else {
				--sparkDelay;
			}
		} else {
			int range = 6;

			for (int x = -range; x <= range; ++x) {
				for (int y = -range; y <= range; ++y) {
					for (int z = -range; z <= range; ++z) {
						int xx = xCoord + x;
						int yy = yCoord + y;
						int zz = zCoord + z;
						TileEntity tile = worldObj.getTileEntity(xx, yy, zz);

						if (tile != null && tile instanceof TileEldritchObelisk) {
							obeliskQuards[0] = xx;
							obeliskQuards[1] = yy;
							obeliskQuards[2] = zz;
							nearObelisk = true;
							return;
						}
					}
				}
			}

			nearObelisk = false;
		}
	}

	@Override
	public String getInventoryName() {
		return "Dark Infuser";
	}
}