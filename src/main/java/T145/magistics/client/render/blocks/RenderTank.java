package T145.magistics.client.render.blocks;

import java.awt.Color;

import javax.annotation.Nonnull;

import T145.magistics.api.ModBlocks;
import T145.magistics.client.lib.IconAtlas;
import T145.magistics.client.lib.RenderCubes;
import T145.magistics.client.render.BlockRenderer;
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
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();

		GlStateManager.translate(x + 0.5F, y + 0.01F, z + 0.5F);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.disableLighting();

		if (tank.getQuintessence() > 0) {
			renderLiquid(tank, x, y, z, partialTicks);
		}

		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.popMatrix();
	}

	private void renderLiquid(TileTank tank, double x, double y, double z, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.disableLighting();

		RenderCubes renderBlocks = RenderCubes.getInstance();
		Tessellator tessellator = Tessellator.getInstance();
		float level = tank.getQuintessence() / tank.getMaxQuintessence() * 0.625F;

		renderBlocks.setRenderBounds(BlockRenderer.W1 + 0.01D, 0.0625D, BlockRenderer.W1 + 0.01D, 0.99D - BlockRenderer.W1, 0.0625D + level, 0.99D - BlockRenderer.W1);
		tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);

		TextureAtlasSprite icon = IconAtlas.INSTANCE.quintFluid;
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		// Color co = new Color(16761087);
		Color co = new Color(0xFFFFFF);

		renderBlocks.renderFaceYNeg(ModBlocks.tank, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
		renderBlocks.renderFaceYPos(ModBlocks.tank, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
		renderBlocks.renderFaceZNeg(ModBlocks.tank, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
		renderBlocks.renderFaceZPos(ModBlocks.tank, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
		renderBlocks.renderFaceXNeg(ModBlocks.tank, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
		renderBlocks.renderFaceXPos(ModBlocks.tank, -0.5D, 0.0D, -0.5D, icon, co.getRed() / 255.0F, co.getGreen() / 255.0F, co.getBlue() / 255.0F, 200);
		tessellator.draw();

		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
		GlStateManager.color(1F, 1F, 1F, 1F);
	}
}