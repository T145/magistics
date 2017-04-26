package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import T145.magistics.api.ModBlocks;
import T145.magistics.client.lib.RenderBlocks;
import T145.magistics.client.render.BlockRenderer;
import T145.magistics.tiles.storage.TileConduit;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderConduit extends TileEntitySpecialRenderer<TileConduit> {

	@Override
	public void renderTileEntityAt(@Nonnull TileConduit conduit, double x, double y, double z, float partialTicks, int destroyStage) {
		renderConduit(conduit, x, y, z, partialTicks);

		if (conduit.getQuintessence() > 0) {
			renderLiquid(conduit, x, y, z, partialTicks);
		}
	}

	private void renderConduit(TileConduit conduit, double x, double y, double z, float partialTicks) {
		for (EnumFacing facing : EnumFacing.VALUES) {
			if (conduit.isConnected(facing)) {
				Tessellator tess = Tessellator.getInstance();
				RenderBlocks render = new RenderBlocks();
				TextureAtlasSprite icon = BlockRenderer.getTextureFromBlock(ModBlocks.conduit, 0);

				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5F, y, z + 0.5F);

				switch (facing.getOpposite().ordinal()) {
				case 0:
					render.setRenderBounds(BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, 1D, BlockRenderer.W6 + BlockRenderer.W4);
					break;
				case 1:
					render.setRenderBounds(BlockRenderer.W6, 0D, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4);
					break;
				case 2:
					render.setRenderBounds(BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, 1D);
					break;
				case 3:
					render.setRenderBounds(BlockRenderer.W6, BlockRenderer.W6, 0D, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6);
					break;
				case 4:
					render.setRenderBounds(BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6, 1.0F, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4);
					break;
				case 5:
					render.setRenderBounds(0D, BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4);
					break;
				}

				tess.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
				bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				render.renderFaces(tess, -0.5D, 0D, -0.5D, icon, 1F, 1F, 1F, 210);
				tess.draw();

				GlStateManager.popMatrix();
			}
		}
	}

	private void renderLiquid(TileConduit conduit, double x, double y, double z, float partialTicks) {
		// TODO Auto-generated method stub

	}
}