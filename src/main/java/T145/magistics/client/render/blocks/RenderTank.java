package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.client.lib.ClientBakery;
import T145.magistics.client.lib.RenderCubes;
import T145.magistics.tiles.storage.TileTank;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTank extends TileEntitySpecialRenderer<TileTank> {

	@Override
	public void renderTileEntityAt(@Nonnull TileTank tank, double x, double y, double z, float partialTicks, int destroyStage) {
		if (tank.getQuintessence() > 0) {
			renderLiquid(tank, x, y, z);
		}

		for (EnumFacing facing : EnumFacing.HORIZONTALS) {
			if (tank.isConnected(facing)) {
				renderConnection(tank, x, y, z, facing);
			}
		}
	}

	private void renderConnection(TileTank tank, double x, double y, double z, EnumFacing facing) {
	}

	private void renderLiquid(TileTank tank, double x, double y, double z) {
		Tessellator tess = Tessellator.getInstance();
		RenderCubes render = new RenderCubes();

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.disableCull();
		GlStateManager.disableLighting();

		float mod = 0.003F;
		float level = (1F - mod * 2F) * (tank.getQuintessence() / tank.getMaxQuintessence());
		//int brightness = 20 + (int) (Math.min(1F, conduit.getQuintessence() / conduit.getMaxQuintessence()) * 210F);

		render.setRenderBounds(mod + BlockRenderer.W1, mod, mod + BlockRenderer.W1, 1F - mod - BlockRenderer.W1, mod + level, 1F - mod - BlockRenderer.W1);
		tess.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		render.renderFaces(0D, 0D, 0D, ClientBakery.INSTANCE.quintFluid, 1F, 1F, 1F, 210);
		tess.draw();

		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.popMatrix();
	}
}