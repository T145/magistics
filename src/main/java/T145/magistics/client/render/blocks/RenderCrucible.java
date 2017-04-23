package T145.magistics.client.render.blocks;

import java.awt.Color;

import javax.annotation.Nonnull;

import T145.magistics.client.lib.ClientBakery;
import T145.magistics.client.lib.RenderCubes;
import T145.magistics.client.render.BlockRenderer;
import T145.magistics.tiles.machines.TileCrucible;
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
public class RenderCrucible extends TileEntitySpecialRenderer<TileCrucible> {

	@Override
	public void renderTileEntityAt(@Nonnull TileCrucible crucible, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();

		GlStateManager.translate(x + 0.5F, y + 0.01F, z + 0.5F);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.disableLighting();

		if (crucible.getQuintessence() > 0) {
			renderLiquid(crucible, x, y, z, partialTicks);
		}

		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.popMatrix();
	}

	private void renderLiquid(TileCrucible crucible, double x, double y, double z, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.disableLighting();

		RenderCubes renderBlocks = RenderCubes.getInstance();
		Tessellator tessellator = Tessellator.getInstance();
		float level = crucible.getQuintessence() / crucible.getMaxQuintessence();
		TextureAtlasSprite icon = ClientBakery.INSTANCE.quintFluid;
		Color co = new Color(0xFFFFFF);

		renderBlocks.setRenderBounds(BlockRenderer.W1 + 0.001D, 0.001D, BlockRenderer.W1 + 0.001D, 0.999D - BlockRenderer.W1, level - 0.02D, 0.999D - BlockRenderer.W1);
		tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		renderBlocks.renderFaceYNeg(-0.5D, 0D, -0.5D, icon, co.getRed() / 255F, co.getGreen() / 255F, co.getBlue() / 255F, 200);
		renderBlocks.renderFaceYPos(-0.5D, 0D, -0.5D, icon, co.getRed() / 255F, co.getGreen() / 255F, co.getBlue() / 255F, 200);
		renderBlocks.renderFaceZNeg(-0.5D, 0D, -0.5D, icon, co.getRed() / 255F, co.getGreen() / 255F, co.getBlue() / 255F, 200);
		renderBlocks.renderFaceZPos(-0.5D, 0D, -0.5D, icon, co.getRed() / 255F, co.getGreen() / 255F, co.getBlue() / 255F, 200);
		renderBlocks.renderFaceXNeg(-0.5D, 0D, -0.5D, icon, co.getRed() / 255F, co.getGreen() / 255F, co.getBlue() / 255F, 200);
		renderBlocks.renderFaceXPos(-0.5D, 0D, -0.5D, icon, co.getRed() / 255F, co.getGreen() / 255F, co.getBlue() / 255F, 200);
		tessellator.draw();

		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
		GlStateManager.color(1F, 1F, 1F, 1F);
	}
}