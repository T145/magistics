package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import T145.magistics.api.ModBlocks;
import T145.magistics.blocks.storage.BlockConduit;
import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.client.lib.RenderCubes;
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
		for (EnumFacing facing : EnumFacing.VALUES) {
			if (conduit.isConnected(facing)) {
				renderConduitPart(conduit, x, y, z, facing);

				if (conduit.getQuintessence() > 0) {
					renderLiquid(conduit, x, y, z, facing);
				}
			}
		}
	}

	private void renderConduitPart(TileConduit conduit, double x, double y, double z, EnumFacing facing) {
		Tessellator tess = Tessellator.getInstance();
		RenderCubes render = new RenderCubes();
		TextureAtlasSprite icon = BlockRenderer.getTextureFromBlock(ModBlocks.conduit, 0);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5F, y, z + 0.5F);

		render.setRenderBounds(BlockConduit.BOX_FACES[facing.ordinal()]);
		tess.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		render.renderFaces(-0.5D, 0D, -0.5D, icon, 1F, 1F, 1F, 210);
		tess.draw();

		GlStateManager.popMatrix();
	}

	private void renderLiquid(TileConduit conduit, double x, double y, double z, EnumFacing facing) {
	}
}