package T145.magistics.common.tiles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.common.Thaumcraft;

public class TileTintedNitor extends TileEntity {
	private static List<Color> colors = new ArrayList<Color>();

	public TileTintedNitor() {
		for (int code : ItemDye.field_150922_c)
			colors.add(new Color(code));
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote) {
			if (worldObj.rand.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
				//Thaumcraft.proxy.wispFX3(worldObj, xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f, xCoord + 0.3f + worldObj.rand.nextFloat() * 0.4f, yCoord + 0.5f, zCoord + 0.3f + worldObj.rand.nextFloat() * 0.4f, 0.5f, 4, true, -0.025f);
			}
			if (worldObj.rand.nextInt(15 - Thaumcraft.proxy.particleCount(4)) == 0) {
				//Thaumcraft.proxy.wispFX3(worldObj, xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f, xCoord + 0.4f + worldObj.rand.nextFloat() * 0.2f, yCoord + 0.5f, zCoord + 0.4f + worldObj.rand.nextFloat() * 0.2f, 0.25f, 1, true, -0.02f);
			}
		}
	}
}