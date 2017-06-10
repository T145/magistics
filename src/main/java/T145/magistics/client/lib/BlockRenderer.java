package T145.magistics.client.lib;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class BlockRenderer {

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
		Tessellator tessellator = Tessellator.getInstance();
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

		quad.draw(tessellator.getBuffer(), scale, brightness, c.getRGB(), opacity);

		GlStateManager.blendFunc(770, 771);

		if (!blendon) {
			GlStateManager.disableBlend();
		}
	}
}