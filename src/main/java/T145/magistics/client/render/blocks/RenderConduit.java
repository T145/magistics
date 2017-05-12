package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import T145.magistics.blocks.storage.BlockConduit;
import T145.magistics.client.lib.ClientBakery;
import T145.magistics.client.lib.RenderCubes;
import T145.magistics.tiles.storage.TileConduit;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderConduit extends TileEntitySpecialRenderer<TileConduit> {

	public static final int CONDUIT_BRIGHTNESS = 245;

	@Override
	public void renderTileEntityAt(@Nonnull TileConduit conduit, double x, double y, double z, float partialTicks, int destroyStage) {
		Tessellator tess = Tessellator.getInstance();
		VertexBuffer buffer = tess.getBuffer();
		RenderCubes part = new RenderCubes();
		RenderCubes fluid = new RenderCubes();
		TextureAtlasSprite partSprite = ClientBakery.INSTANCE.conduitPart;
		TextureAtlasSprite fluidSprite = ClientBakery.INSTANCE.quintFluid;
		float mod = 0.38125F;
		float amount = Math.min(conduit.getQuints(), conduit.getMaxQuints());
		float level = (1F - mod * 2F) * (amount / conduit.getMaxQuints());

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.disableCull();
		GlStateManager.disableLighting();

		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);

		if (conduit.hasQuints()) {
			fluid.setRenderBounds(mod, mod, mod, 1F - mod, mod + level, 1F - mod);
			fluid.renderNormalFaces(fluidSprite, 210);
		}

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (conduit.isConnected(facing)) {
				part.setRenderBounds(BlockConduit.BOX_FACES[facing.getIndex()]);
				part.renderNormalFaces(partSprite, CONDUIT_BRIGHTNESS);

				if (conduit.hasQuints()) {
					switch (facing) {
					case UP:
						fluid.setRenderBounds(0.5F - level / 2F, mod + level, 0.5F - level / 2F, 0.5F + level / 2F, 1F, 0.5F + level / 2F);
						break;
					case DOWN:
						fluid.setRenderBounds(0.5F - level / 2F, 0F, 0.5F - level / 2F, 0.5F + level / 2F, mod, 0.5F + level / 2F);
						break;
					case SOUTH:
						fluid.setRenderBounds(mod, mod, 1F - mod, 1F - mod, mod + level, 1F);
						break;
					case NORTH:
						fluid.setRenderBounds(mod, mod, 0F, 1F - mod, mod + level, mod);
						break;
					case EAST:
						fluid.setRenderBounds(1F - mod, mod, mod, 1F, mod + level, 1F - mod);
						break;
					case WEST:
						fluid.setRenderBounds(0F, mod, mod, mod, mod + level, 1F - mod);
						break;
					}

					fluid.renderNormalFaces(fluidSprite, 210);
				}
			}
		}

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		tess.draw();

		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.popMatrix();
	}
}