package T145.magistics.client.lib;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class BlockRenderer {

	public static final VertexFormatElement NORMAL_3F = new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.NORMAL, 3);
	public static final VertexFormat POSITION_NORMALF = new VertexFormat().addElement(DefaultVertexFormats.POSITION_3F).addElement(NORMAL_3F);
	public static final VertexFormat POSITION_TEX_NORMALF = new VertexFormat().addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(NORMAL_3F);

	public static double W1 = 0.0625D;
	public static double W2 = 0.125D;
	public static double W3 = 0.1875D;
	public static double W4 = 0.25D;
	public static double W5 = 0.3125D;
	public static double W6 = 0.375D;
	public static double W7 = 0.4375D;
	public static double W8 = 0.5D;
	public static double W9 = 0.5625D;
	public static double W10 = 0.625D;
	public static double W11 = 0.6875D;
	public static double W12 = 0.75D;
	public static double W13 = 0.8125D;
	public static double W14 = 0.875D;
	public static double W15 = 0.9375D;
	public static double W16 = 0.9475D;

	public static void rotate(EnumFacing facing) {
		switch (facing) {
		case NORTH:
			GlStateManager.rotate(180F, 0F, 1F, 0F);
			break;
		case SOUTH:
			GlStateManager.rotate(0F, 0F, 1F, 0F);
			break;
		case WEST:
			GlStateManager.rotate(90F, 0F, 1F, 0F);
			break;
		case EAST:
			GlStateManager.rotate(270F, 0F, 1F, 0F);
			break;
		default:
			GlStateManager.rotate(0F, 0F, 1F, 0F);
			break;
		}
	}

	public static TextureAtlasSprite getTextureFromBlock(Block block, int meta) {
		return getTextureFromBlockstate(block.getStateFromMeta(meta));
	}

	public static TextureAtlasSprite getTextureFromBlockstate(IBlockState state) {
		return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(state);
	}

	public static void renderQuadCentered(ResourceLocation texture, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderQuadCentered(1, 1, 0, scale, red, green, blue, brightness, blend, opacity);
	}

	public static void renderQuadCentered(ResourceLocation texture, int gridX, int gridY, int frame, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		renderQuadCentered(gridX, gridY, frame, scale, red, green, blue, brightness, blend, opacity);
	}

	public static void renderQuadCentered() {
		renderQuadCentered(1, 1, 0, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
	}

	public static void renderQuadCentered(int gridX, int gridY, int frame, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
		Tessellator buffers = Tessellator.getInstance();
		boolean blendon = GL11.glIsEnabled(3042);

		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, blend);

		int xm = frame % gridX;
		int ym = frame / gridY;
		float f1 = xm / gridX;
		float f2 = f1 + 1.0F / gridX;
		float f3 = ym / gridY;
		float f4 = f3 + 1.0F / gridY;
		Color c = new Color(red, green, blue);
		TexturedQuad quad = new TexturedQuad(new PositionTextureVertex[] { new PositionTextureVertex(-0.5F, 0.5F, 0.0F, f2, f4), new PositionTextureVertex(0.5F, 0.5F, 0.0F, f2, f3), new PositionTextureVertex(0.5F, -0.5F, 0.0F, f1, f3), new PositionTextureVertex(-0.5F, -0.5F, 0.0F, f1, f4) });

		quad.draw(buffers.getBuffer(), scale, brightness, c.getRGB(), opacity);

		GlStateManager.blendFunc(770, 771);

		if (!blendon) {
			GlStateManager.disableBlend();
		}
	}

	public static void renderCube(TextureAtlasSprite icon, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		for (EnumFacing facing : EnumFacing.VALUES) {
			renderFace(facing, icon, minX, minY, minZ, maxX, maxY, maxZ);
		}
	}

	public static void renderFace(EnumFacing facing, TextureAtlasSprite icon, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		Tessellator tess = Tessellator.getInstance();
		VertexBuffer buffer = tess.getBuffer();

		switch (facing) {
		case NORTH:
			buffer.begin(GL11.GL_QUADS, POSITION_TEX_NORMALF);
			buffer.pos(minX, minY, minZ).tex(icon.getMinU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			buffer.pos(minX, maxY, minZ).tex(icon.getMinU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(maxX, maxY, minZ).tex(icon.getMaxU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(maxX, minY, minZ).tex(icon.getMaxU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			tess.draw();
			break;
		case SOUTH:
			buffer.begin(GL11.GL_QUADS, POSITION_TEX_NORMALF);
			buffer.pos(minX, minY, maxZ).tex(icon.getMinU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			buffer.pos(maxX, minY, maxZ).tex(icon.getMinU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(maxX, maxY, maxZ).tex(icon.getMaxU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(minX, maxY, maxZ).tex(icon.getMaxU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			tess.draw();
			break;
		case WEST:
			buffer.begin(GL11.GL_QUADS, POSITION_TEX_NORMALF);
			buffer.pos(minX, minY, minZ).tex(icon.getMinU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			buffer.pos(minX, minY, maxZ).tex(icon.getMinU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(minX, maxY, maxZ).tex(icon.getMaxU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(minX, maxY, minZ).tex(icon.getMaxU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			tess.draw();
			break;
		case EAST:
			buffer.begin(GL11.GL_QUADS, POSITION_TEX_NORMALF);
			buffer.pos(maxX, minY, minZ).tex(icon.getMinU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			buffer.pos(maxX, maxY, minZ).tex(icon.getMinU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(maxX, maxY, maxZ).tex(icon.getMaxU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(maxX, minY, maxZ).tex(icon.getMaxU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			tess.draw();
			break;
		case DOWN:
			buffer.begin(GL11.GL_QUADS, POSITION_TEX_NORMALF);
			buffer.pos(minX, minY, minZ).tex(icon.getMinU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			buffer.pos(maxX, minY, minZ).tex(icon.getMinU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(maxX, minY, maxZ).tex(icon.getMaxU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(minX, minY, maxZ).tex(icon.getMaxU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			tess.draw();
			break;
		case UP:
			buffer.begin(GL11.GL_QUADS, POSITION_TEX_NORMALF);
			buffer.pos(minX, maxY, minZ).tex(icon.getMinU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			buffer.pos(minX, maxY, maxZ).tex(icon.getMinU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(maxX, maxY, maxZ).tex(icon.getMaxU(), icon.getMaxV()).normal(0, -1, 0).endVertex();
			buffer.pos(maxX, maxY, minZ).tex(icon.getMaxU(), icon.getMinV()).normal(0, -1, 0).endVertex();
			tess.draw();
			break;
		default: // null
			break;
		}
	}
}