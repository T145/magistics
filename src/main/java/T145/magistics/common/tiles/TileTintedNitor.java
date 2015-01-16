package T145.magistics.common.tiles;

import java.awt.Color;

import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXWisp;
import thaumcraft.common.Thaumcraft;

public class TileTintedNitor extends TileEntity {
	public boolean canUpdate() {
		return true;
	}

	public int getColor(int meta) {
		return ItemDye.field_150922_c[meta];
	}

	public void updateEntity() {
		super.updateEntity();
		Color tint = new Color(getColor(getBlockMetadata()));
		if (worldObj.isRemote) {
			if (worldObj.rand.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
				//Thaumcraft.proxy.wispFX3(worldObj, xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, xCoord + 0.3F + worldObj.rand.nextFloat() * 0.4F, yCoord + 0.5F, zCoord + 0.3F + worldObj.rand.nextFloat() * 0.4F, 0.5F, 4, true, -0.025F);
				FXWisp fx = new FXWisp(worldObj, xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, xCoord + 0.3F + worldObj.rand.nextFloat() * 0.4F, yCoord + 0.5F, zCoord + 0.3F + worldObj.rand.nextFloat() * 0.4F, 0.5F, tint.getRed(), tint.getGreen(), tint.getBlue());
				fx.setGravity(-0.025F);
				fx.shrink = true;
				ParticleEngine.instance.addEffect(worldObj, fx);
			}
			if (worldObj.rand.nextInt(15 - Thaumcraft.proxy.particleCount(4)) == 0) {
				//Thaumcraft.proxy.wispFX3(worldObj, xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, xCoord + 0.4F + worldObj.rand.nextFloat() * 0.2F, yCoord + 0.5F, zCoord + 0.4F + worldObj.rand.nextFloat() * 0.2F, 0.25F, 1, true, -0.02F);
				FXWisp fx = new FXWisp(worldObj, xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, xCoord + 0.4F + worldObj.rand.nextFloat() * 0.2F, yCoord + 0.5F, zCoord + 0.4F + worldObj.rand.nextFloat() * 0.2F, 0.25F, tint.getRed(), tint.getGreen(), tint.getBlue());
				fx.setGravity(-0.02F);
				fx.shrink = true;
				ParticleEngine.instance.addEffect(worldObj, fx);
			}
		}
	}
}