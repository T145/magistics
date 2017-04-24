package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import T145.magistics.api.ModBlocks;
import T145.magistics.client.lib.RenderBlocks;
import T145.magistics.client.render.BlockRenderer;
import T145.magistics.tiles.storage.TileConduit;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderConduit extends TileEntitySpecialRenderer<TileConduit> {

	@Override
	public void renderTileEntityAt(@Nonnull TileConduit conduit, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();

		GlStateManager.translate(x + 0.5F, y, z + 0.5F);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.disableLighting();

		renderConduit(conduit);

		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.popMatrix();
	}

	private void renderConduit(TileConduit conduit) {
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.disableLighting();

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (conduit.isConnected(facing)) {
				RenderBlocks renderBlocks = RenderBlocks.getWorldInstance(conduit.getWorld());
				Tessellator tessellator = Tessellator.getInstance();
				TextureAtlasSprite icon = BlockRenderer.getTextureFromBlock(ModBlocks.conduit, 0);

				switch (facing.getOpposite().ordinal()) {
				case 0:
					renderBlocks.setRenderBounds(BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, 1D, BlockRenderer.W6 + BlockRenderer.W4);
					break;
				case 1:
					renderBlocks.setRenderBounds(BlockRenderer.W6, 0D, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4);
					break;
				case 2:
					renderBlocks.setRenderBounds(BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, 1D);
					break;
				case 3:
					renderBlocks.setRenderBounds(BlockRenderer.W6, BlockRenderer.W6, 0D, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6);
					break;
				case 4:
					renderBlocks.setRenderBounds(BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6, 1.0F, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4);
					break;
				case 5:
					renderBlocks.setRenderBounds(0D, BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4);
					break;
				}

				tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
				bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

				renderBlocks.renderFaceYNeg(-0.5D, 0D, -0.5D, icon, 1F, 1F, 1F, 200);
				renderBlocks.renderFaceYPos(-0.5D, 0D, -0.5D, icon, 1F, 1F, 1F, 200);
				renderBlocks.renderFaceZNeg(-0.5D, 0D, -0.5D, icon, 1F, 1F, 1F, 200);
				renderBlocks.renderFaceZPos(-0.5D, 0D, -0.5D, icon, 1F, 1F, 1F, 200);
				renderBlocks.renderFaceXNeg(-0.5D, 0D, -0.5D, icon, 1F, 1F, 1F, 200);
				renderBlocks.renderFaceXPos(-0.5D, 0D, -0.5D, icon, 1F, 1F, 1F, 200);

				tessellator.draw();
			}
		}

		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
		GlStateManager.color(1F, 1F, 1F, 1F);
	}

	private void renderLiquid(World world, RenderBlocks renderBlocks, double x, double y, double z, Block block, int side, float partialTicks) {
	}
}