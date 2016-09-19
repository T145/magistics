package T145.magistics.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.common.tiles.TileEldritchObelisk;

public class TileInfuserDark extends TileInfuser {
	protected int[] obeliskQuards = new int[3];
	private boolean nearObelisk = false;

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
		return getCookProgressScaled(time);
	}

	@Override
	public void updateEntity() {
		if (nearObelisk) {
			super.updateEntity();
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