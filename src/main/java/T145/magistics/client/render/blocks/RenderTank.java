package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.client.lib.ClientBakery;
import T145.magistics.client.lib.RenderCubes;
import T145.magistics.init.ModBlocks;
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
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.disableCull();
		GlStateManager.disableLighting();

		if (tank.getQuintessence() > 0) {
			renderLiquid(tank, x, y, z);
		}

		for (EnumFacing facing : EnumFacing.HORIZONTALS) {
			if (tank.isConnected(facing) && !(tank.getWorld().getTileEntity(tank.getPos().offset(facing)) instanceof TileTank)) {
				renderConnection(tank, x, y, z, facing);
			}
		}

		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.popMatrix();
	}

	private void renderConnection(TileTank tank, double x, double y, double z, EnumFacing facing) {
		Tessellator tess = Tessellator.getInstance();
		RenderCubes render = new RenderCubes();

		switch (facing) {
		case NORTH:
			render.setRenderBounds(0.5F - BlockRenderer.W2, 0.5F - BlockRenderer.W2, 0F, 0.5F + BlockRenderer.W2, 0.5F + BlockRenderer.W2, BlockRenderer.W1);
			break;
		case SOUTH:
			render.setRenderBounds(0.5F - BlockRenderer.W2, 0.5F - BlockRenderer.W2, 1F - BlockRenderer.W1, 0.5F + BlockRenderer.W2, 0.5F + BlockRenderer.W2, 1F);
			break;
		case EAST:
			render.setRenderBounds(1F - BlockRenderer.W1, 0.5F - BlockRenderer.W2, 0.5F - BlockRenderer.W2, 1F, 0.5F + BlockRenderer.W2, 0.5F + BlockRenderer.W2);
			break;
		case WEST:
			render.setRenderBounds(0F, 0.5F - BlockRenderer.W2, 0.5F - BlockRenderer.W2, BlockRenderer.W1, 0.5F + BlockRenderer.W2, 0.5F + BlockRenderer.W2);
			break;
		default:
			break;
		}

		tess.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		render.renderFaces(0D, 0D, 0D, BlockRenderer.getTextureFromBlock(ModBlocks.conduit, 0), 1F, 1F, 1F, 210);
		tess.draw();
	}

	private void renderLiquid(TileTank tank, double x, double y, double z) {
		Tessellator tess = Tessellator.getInstance();
		RenderCubes render = new RenderCubes();

		float mod = 0.003F;
		float level = (1F - mod * 2F) * (tank.getQuintessence() / tank.getMaxQuintessence());
		//int brightness = 20 + (int) (Math.min(1F, conduit.getQuintessence() / conduit.getMaxQuintessence()) * 210F);

		render.setRenderBounds(mod + BlockRenderer.W1, mod, mod + BlockRenderer.W1, 1F - mod - BlockRenderer.W1, mod + level, 1F - mod - BlockRenderer.W1);
		tess.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		render.renderFaces(0D, 0D, 0D, ClientBakery.INSTANCE.quintFluid, 1F, 1F, 1F, 210);
		tess.draw();
	}
}