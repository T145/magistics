package T145.magistics.client.render.tiles;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXEssentiaTrail;
import T145.magistics.tiles.TileEverfullUrn;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEverfullUrn extends TileEntitySpecialRenderer {
	public static final TileEntitySpecialRenderer INSTANCE = new RenderEverfullUrn();

	public void spawnWaterAtLocation(World worldObj, double xCoord, double yCoord, double zCoord, double dX, double dY, double dZ) {
		FXEssentiaTrail fx = new FXEssentiaTrail(worldObj, xCoord + 0.5F, yCoord + 1.1F, zCoord + 0.5F, dX + 0.5F, dY + 1.1F, dZ + 0.5F, 5, Aspect.TOOL.getColor(), 1.0F);
		ParticleEngine.instance.addEffect(worldObj, fx);
	}

	public void spawnRandomWaterFountain(World worldObj, int xCoord, int yCoord, int zCoord) {
		spawnWaterAtLocation(worldObj, xCoord, yCoord, zCoord, xCoord + 0.5F + ((Math.random()) - 0.5), yCoord + 2.1F, zCoord + 0.5F + ((Math.random()) - 0.5));
	}

	public void spawnWaterOnPlayer(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player) {
		spawnWaterAtLocation(worldObj, xCoord, yCoord, zCoord, player.posX, player.posY, player.posZ);
	}

	private void renderUrn(TileEverfullUrn urn) {
		if (urn.isActive()) {
			spawnRandomWaterFountain(urn.getWorldObj(), urn.xCoord, urn.yCoord, urn.zCoord);

			if (urn.isFilling()) {
				spawnWaterAtLocation(urn.getWorldObj(), urn.xCoord, urn.yCoord, urn.zCoord, urn.getTargetX(), urn.getTargetY(), urn.getTargetZ());
			}
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		renderUrn((TileEverfullUrn) tile);
	}
}