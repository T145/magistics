package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.client.lib.ClientBakery;
import T145.magistics.client.lib.RenderCubes;
import T145.magistics.tiles.storage.TileTank;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTank extends TileEntitySpecialRenderer<TileTank> {

	@Override
	public void renderTileEntityAt(@Nonnull TileTank tank, double x, double y, double z, float partialTicks, int destroyStage) {
		if (tank.getQuintessence() > 0) {
			renderLiquid(tank, x, y, z, partialTicks);
		}
	}

	private void renderLiquid(TileTank tank, double x, double y, double z, float partialTicks) {
		float level = tank.getQuintessence() / tank.getMaxQuintessence();

		if (level == 1F) {
			level -= 0.02D;
		}

		Tessellator tess = Tessellator.getInstance();
		RenderCubes render = new RenderCubes();
		TextureAtlasSprite icon = ClientBakery.INSTANCE.quintFluid;

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5D, y, z + 0.5D);
		GlStateManager.disableCull();
		GlStateManager.disableLighting();

		render.setRenderBounds(BlockRenderer.W1 + 0.001D, 0.001D, BlockRenderer.W1 + 0.001D, 0.999D - BlockRenderer.W1, level, 0.999D - BlockRenderer.W1);
		tess.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		render.renderFaces(-0.5D, 0D, -0.5D, icon, 1F, 1F, 1F, 210);
		tess.draw();

		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.popMatrix();
	}
}