package T145.magistics.common.tiles;

import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.common.Thaumcraft;
import T145.magistics.client.fx.FXColoredWisp;

public class TileTintedNitor extends TileEntity {
	private int color = 0;
	
	
	
	@Override
	public boolean canUpdate() {
		return true;
	}

	public static void colorFX(World world, double x, double y, double z, double x2, double y2, double z2, float size, int type, boolean shrink, float gravity, int color) {
		FXColoredWisp fx = new FXColoredWisp(world, x, y, z, x2, y2, z2, size, type, shrink, gravity, color);
		fx.setGravity(gravity);
		fx.shrink = shrink;
		ParticleEngine.instance.addEffect(world, fx);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote) {
			if (super.worldObj.rand.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
				colorFX(super.worldObj, super.xCoord + 0.5f, super.yCoord + 0.5f, super.zCoord + 0.5f, super.xCoord + 0.3f + super.worldObj.rand.nextFloat() * 0.4f, super.yCoord + 0.5f, super.zCoord + 0.3f + super.worldObj.rand.nextFloat() * 0.4f, 0.5f, color, true, -0.025f, color);
			}
			if (super.worldObj.rand.nextInt(15 - Thaumcraft.proxy.particleCount(4)) == 0) {
				colorFX(super.worldObj, super.xCoord + 0.5f, super.yCoord + 0.5f, super.zCoord + 0.5f, super.xCoord + 0.4f + super.worldObj.rand.nextFloat() * 0.2f, super.yCoord + 0.5f, super.zCoord + 0.4f + super.worldObj.rand.nextFloat() * 0.2f, 0.25f, color, true, -0.02f, color);
			}
		}
	}
}