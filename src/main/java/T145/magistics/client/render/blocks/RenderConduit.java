package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import T145.magistics.api.ModBlocks;
import T145.magistics.blocks.storage.BlockConduit;
import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.client.lib.ClientBakery;
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
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.disableCull();

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (conduit.isConnected(facing)) {
				renderConduitPart(conduit, x, y, z, facing);

				if (conduit.getQuintessence() > 0) {
					renderLiquid(conduit, x, y, z, facing);
				}
			}
		}

		GlStateManager.enableCull();
		GlStateManager.popMatrix();
	}

	private void renderConduitPart(TileConduit conduit, double x, double y, double z, EnumFacing facing) {
		Tessellator tess = Tessellator.getInstance();
		RenderCubes render = new RenderCubes();
		TextureAtlasSprite icon = BlockRenderer.getTextureFromBlock(ModBlocks.conduit, 0);

		render.setRenderBounds(BlockConduit.BOX_FACES[facing.ordinal()]);
		tess.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		render.renderFaces(0D, 0D, 0D, icon, 1F, 1F, 1F, 210);
		tess.draw();
	}

	private void renderLiquid(TileConduit conduit, double x, double y, double z, EnumFacing facing) {
		Tessellator tess = Tessellator.getInstance();
		RenderCubes render = new RenderCubes();
		TextureAtlasSprite icon = ClientBakery.INSTANCE.quintFluid;

		float mod = 0.38125F;
		float level = (1F - mod * 2F) * (Math.min(conduit.getQuintessence(), conduit.getMaxQuintessence()) / conduit.getMaxQuintessence());
		//int brightness = 20 + (int) (Math.min(1F, conduit.getQuintessence() / conduit.getMaxQuintessence()) * 210F);

		switch (facing) {
		case UP:
			render.setRenderBounds(0.5F - level / 2F, mod + level, 0.5F - level / 2F, 0.5F + level / 2F, 1F, 0.5F + level / 2F);
			break;
		case DOWN:
			render.setRenderBounds(0.5F - level / 2F, 0F, 0.5F - level / 2F, 0.5F + level / 2F, mod, 0.5F + level / 2F);
			break;
		case SOUTH:
			render.setRenderBounds(mod, mod, 1F - mod, 1F - mod, mod + level, 1F);
			break;
		case NORTH:
			render.setRenderBounds(mod, mod, 0F, 1F - mod, mod + level, mod);
			break;
		case EAST:
			render.setRenderBounds(1F - mod, mod, mod, 1F, mod + level, 1F - mod);
			break;
		case WEST:
			render.setRenderBounds(0F, mod, mod, mod, mod + level, 1F - mod);
			break;
		}

		tess.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		render.renderFaces(0D, 0D, 0D, icon, 1F, 1F, 1F, 210);
		tess.draw();
	}
}