package T145.magistics.client.render.tiles;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXEssentiaTrail;
import thaumcraft.common.Thaumcraft;
import T145.magistics.tiles.TileEverfullUrn;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEverfullUrn extends TileEntitySpecialRenderer {
	public static TileEntitySpecialRenderer INSTANCE = new RenderEverfullUrn();

	public void spawnRandomWaterFountain(TileEverfullUrn urn) {
		FXEssentiaTrail fx = new FXEssentiaTrail(urn.getWorldObj(), urn.xCoord + 0.5F, urn.yCoord + 1.1F, urn.zCoord + 0.5F, urn.xCoord + 0.5F + (Math.random() - 0.5F), urn.yCoord + 2.1F, urn.zCoord + 0.5F + (Math.random() - 0.5F), 5, Aspect.TOOL.getColor(), 1F);
		ParticleEngine.instance.addEffect(urn.getWorldObj(), fx);
	}

	private void renderUrn(TileEverfullUrn urn) {
		if (urn.isActive()) {
			spawnRandomWaterFountain(urn);

			if (urn.isFilling()) {
				Thaumcraft.proxy.essentiaTrailFx(urn.getWorldObj(), urn.xCoord, urn.yCoord + 1, urn.zCoord, urn.getTargetX(), urn.getTargetY(), urn.getTargetZ(), 5, Aspect.TOOL.getColor(), 1F);
			}
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		renderUrn((TileEverfullUrn) tile);
	}
}