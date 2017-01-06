package T145.magistics.client.lib.utils;

import T145.magistics.client.lib.StructUV;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UtilsFX {

	public static void setColorRGB(int color) {
		setColorRGBA(color | 0xff000000);
	}

	public static void setColorRGBA(int color) {
		float a = alpha(color) / 255F;
		float r = red(color) / 255F;
		float g = green(color) / 255F;
		float b = blue(color) / 255F;

		GlStateManager.color(r, g, b, a);
	}

	public static int compose(int r, int g, int b, int a) {
		int rgb = a;
		rgb = (rgb << 8) + r;
		rgb = (rgb << 8) + g;
		rgb = (rgb << 8) + b;
		return rgb;
	}

	public static int alpha(int c) {
		return (c >> 24) & 0xFF;
	}

	public static int red(int c) {
		return (c >> 16) & 0xFF;
	}

	public static int green(int c) {
		return (c >> 8) & 0xFF;
	}

	public static int blue(int c) {
		return (c) & 0xFF;
	}

	public static void addBox(VertexBuffer buffer, double x1, double y1, double z1, double x2, double y2, double z2, StructUV[] textures, int[] inversions) {
		buffer.pos(x1, y1, z1).tex(textures[0].minU, textures[0].minV).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();
		buffer.pos(x1, y1, z2).tex(textures[0].maxU, textures[0].minV).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();
		buffer.pos(x2, y1, z2).tex(textures[0].maxU, textures[0].maxV).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();
		buffer.pos(x2, y1, z1).tex(textures[0].minU, textures[0].maxV).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();

		buffer.pos(x1, y2, z1).tex(textures[1].minU, textures[1].minV).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();
		buffer.pos(x1, y2, z2).tex(textures[1].maxU, textures[1].minV).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();
		buffer.pos(x2, y2, z2).tex(textures[1].maxU, textures[1].maxV).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();
		buffer.pos(x2, y2, z1).tex(textures[1].minU, textures[1].maxV).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();

		buffer.pos(x1, y1, z1).tex(textures[2].minU, textures[2].minV).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();
		buffer.pos(x2, y1, z1).tex(textures[2].maxU, textures[2].minV).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();
		buffer.pos(x2, y2, z1).tex(textures[2].maxU, textures[2].maxV).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();
		buffer.pos(x1, y2, z1).tex(textures[2].minU, textures[2].maxV).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();

		buffer.pos(x1, y1, z2).tex(textures[3].minU, textures[3].minV).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();
		buffer.pos(x2, y1, z2).tex(textures[3].maxU, textures[3].minV).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();
		buffer.pos(x2, y2, z2).tex(textures[3].maxU, textures[3].maxV).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();
		buffer.pos(x1, y2, z2).tex(textures[3].minU, textures[3].maxV).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();

		buffer.pos(x1, y1, z1).tex(textures[4].minU, textures[4].minV).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();
		buffer.pos(x1, y1, z2).tex(textures[4].maxU, textures[4].minV).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();
		buffer.pos(x1, y2, z2).tex(textures[4].maxU, textures[4].maxV).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();
		buffer.pos(x1, y2, z1).tex(textures[4].minU, textures[4].maxV).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();

		buffer.pos(x2, y1, z1).tex(textures[5].minU, textures[5].minV).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
		buffer.pos(x2, y1, z2).tex(textures[5].maxU, textures[5].minV).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
		buffer.pos(x2, y2, z2).tex(textures[5].maxU, textures[5].maxV).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
		buffer.pos(x2, y2, z1).tex(textures[5].minU, textures[5].maxV).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
	}

	public static void addBoxWithSprite(VertexBuffer buffer, double x1, double y1, double z1, double x2, double y2, double z2, TextureAtlasSprite sprite, StructUV[] textures, int[] inversions) {
		float spriteW = sprite.getMaxU() - sprite.getMinU();
		float spriteH = sprite.getMaxV() - sprite.getMinV();

		buffer.pos(x1, y1, z1).tex(sprite.getMinU() + textures[0].minU * spriteW, sprite.getMinV() + textures[0].minV * spriteH).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();
		buffer.pos(x1, y1, z2).tex(sprite.getMinU() + textures[0].maxU * spriteW, sprite.getMinV() + textures[0].minV * spriteH).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();
		buffer.pos(x2, y1, z2).tex(sprite.getMinU() + textures[0].maxU * spriteW, sprite.getMinV() + textures[0].maxV * spriteH).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();
		buffer.pos(x2, y1, z1).tex(sprite.getMinU() + textures[0].minU * spriteW, sprite.getMinV() + textures[0].maxV * spriteH).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();

		buffer.pos(x1, y2, z1).tex(sprite.getMinU() + textures[1].minU * spriteW, sprite.getMinV() + textures[1].minV * spriteH).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();
		buffer.pos(x1, y2, z2).tex(sprite.getMinU() + textures[1].maxU * spriteW, sprite.getMinV() + textures[1].minV * spriteH).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();
		buffer.pos(x2, y2, z2).tex(sprite.getMinU() + textures[1].maxU * spriteW, sprite.getMinV() + textures[1].maxV * spriteH).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();
		buffer.pos(x2, y2, z1).tex(sprite.getMinU() + textures[1].minU * spriteW, sprite.getMinV() + textures[1].maxV * spriteH).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();

		buffer.pos(x1, y1, z1).tex(sprite.getMinU() + textures[2].minU * spriteW, sprite.getMinV() + textures[2].minV * spriteH).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();
		buffer.pos(x2, y1, z1).tex(sprite.getMinU() + textures[2].maxU * spriteW, sprite.getMinV() + textures[2].minV * spriteH).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();
		buffer.pos(x2, y2, z1).tex(sprite.getMinU() + textures[2].maxU * spriteW, sprite.getMinV() + textures[2].maxV * spriteH).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();
		buffer.pos(x1, y2, z1).tex(sprite.getMinU() + textures[2].minU * spriteW, sprite.getMinV() + textures[2].maxV * spriteH).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();

		buffer.pos(x1, y1, z2).tex(sprite.getMinU() + textures[3].minU * spriteW, sprite.getMinV() + textures[3].minV * spriteH).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();
		buffer.pos(x2, y1, z2).tex(sprite.getMinU() + textures[3].maxU * spriteW, sprite.getMinV() + textures[3].minV * spriteH).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();
		buffer.pos(x2, y2, z2).tex(sprite.getMinU() + textures[3].maxU * spriteW, sprite.getMinV() + textures[3].maxV * spriteH).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();
		buffer.pos(x1, y2, z2).tex(sprite.getMinU() + textures[3].minU * spriteW, sprite.getMinV() + textures[3].maxV * spriteH).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();

		buffer.pos(x1, y1, z1).tex(sprite.getMinU() + textures[4].minU * spriteW, sprite.getMinV() + textures[4].minV * spriteH).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();
		buffer.pos(x1, y1, z2).tex(sprite.getMinU() + textures[4].maxU * spriteW, sprite.getMinV() + textures[4].minV * spriteH).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();
		buffer.pos(x1, y2, z2).tex(sprite.getMinU() + textures[4].maxU * spriteW, sprite.getMinV() + textures[4].maxV * spriteH).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();
		buffer.pos(x1, y2, z1).tex(sprite.getMinU() + textures[4].minU * spriteW, sprite.getMinV() + textures[4].maxV * spriteH).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();

		buffer.pos(x2, y1, z1).tex(sprite.getMinU() + textures[5].minU * spriteW, sprite.getMinV() + textures[5].minV * spriteH).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
		buffer.pos(x2, y1, z2).tex(sprite.getMinU() + textures[5].maxU * spriteW, sprite.getMinV() + textures[5].minV * spriteH).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
		buffer.pos(x2, y2, z2).tex(sprite.getMinU() + textures[5].maxU * spriteW, sprite.getMinV() + textures[5].maxV * spriteH).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
		buffer.pos(x2, y2, z1).tex(sprite.getMinU() + textures[5].minU * spriteW, sprite.getMinV() + textures[5].maxV * spriteH).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
	}

	public static void addBoxExt(VertexBuffer buffer, double x1, double y1, double z1, double x2, double y2, double z2, StructUV[] textures, int[] inversions, boolean[] faceToggles) {
		if (faceToggles[0]) {
			buffer.pos(x1, y1, z1).tex(textures[0].minU, textures[0].minV).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();
			buffer.pos(x1, y1, z2).tex(textures[0].maxU, textures[0].minV).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();
			buffer.pos(x2, y1, z2).tex(textures[0].maxU, textures[0].maxV).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();
			buffer.pos(x2, y1, z1).tex(textures[0].minU, textures[0].maxV).color(255, 255, 255, 255).normal(0, -1 * inversions[0], 0).endVertex();
		}

		if (faceToggles[1]) {
			buffer.pos(x1, y2, z1).tex(textures[1].minU, textures[1].minV).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();
			buffer.pos(x1, y2, z2).tex(textures[1].maxU, textures[1].minV).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();
			buffer.pos(x2, y2, z2).tex(textures[1].maxU, textures[1].maxV).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();
			buffer.pos(x2, y2, z1).tex(textures[1].minU, textures[1].maxV).color(255, 255, 255, 255).normal(0, 1 * inversions[1], 0).endVertex();
		}

		if (faceToggles[2]) {
			buffer.pos(x1, y1, z1).tex(textures[2].minU, textures[2].minV).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();
			buffer.pos(x2, y1, z1).tex(textures[2].maxU, textures[2].minV).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();
			buffer.pos(x2, y2, z1).tex(textures[2].maxU, textures[2].maxV).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();
			buffer.pos(x1, y2, z1).tex(textures[2].minU, textures[2].maxV).color(255, 255, 255, 255).normal(0, 0, -1 * inversions[2]).endVertex();
		}

		if (faceToggles[3]) {
			buffer.pos(x1, y1, z2).tex(textures[3].minU, textures[3].minV).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();
			buffer.pos(x2, y1, z2).tex(textures[3].maxU, textures[3].minV).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();
			buffer.pos(x2, y2, z2).tex(textures[3].maxU, textures[3].maxV).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();
			buffer.pos(x1, y2, z2).tex(textures[3].minU, textures[3].maxV).color(255, 255, 255, 255).normal(0, 0, 1 * inversions[3]).endVertex();
		}

		if (faceToggles[4]) {
			buffer.pos(x1, y1, z1).tex(textures[4].minU, textures[4].minV).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();
			buffer.pos(x1, y1, z2).tex(textures[4].maxU, textures[4].minV).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();
			buffer.pos(x1, y2, z2).tex(textures[4].maxU, textures[4].maxV).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();
			buffer.pos(x1, y2, z1).tex(textures[4].minU, textures[4].maxV).color(255, 255, 255, 255).normal(-1 * inversions[4], 0, 0).endVertex();
		}

		if (faceToggles[5]) {
			buffer.pos(x2, y1, z1).tex(textures[5].minU, textures[5].minV).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
			buffer.pos(x2, y1, z2).tex(textures[5].maxU, textures[5].minV).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
			buffer.pos(x2, y2, z2).tex(textures[5].maxU, textures[5].maxV).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
			buffer.pos(x2, y2, z1).tex(textures[5].minU, textures[5].maxV).color(255, 255, 255, 255).normal(1 * inversions[5], 0, 0).endVertex();
		}
	}

	@SideOnly(Side.CLIENT)
	public static void drawQuadGui(VertexBuffer buffer, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, int minU, int minV, int maxU, int maxV) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;

		buffer.pos(x1 + 0.0F, y1 + 0.0F, 0).tex(minU, maxV).endVertex();
		buffer.pos(x2 + 0.0F, y2 + 0.0F, 0).tex(maxU, maxV).endVertex();
		buffer.pos(x3 + 0.0F, y3 + 0.0F, 0).tex(maxU, minV).endVertex();
		buffer.pos(x4 + 0.0F, y4 + 0.0F, 0).tex(minU, minV).endVertex();
	}

	@SideOnly(Side.CLIENT)
	public static void drawQuadGuiExt(VertexBuffer buffer, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, int minU, int minV, int maxU, int maxV, int texW, int texH) {
		float mU = (float) minU / (float) texW;
		float mV = (float) minV / (float) texH;
		float xU = (float) maxU / (float) texW;
		float xV = (float) maxV / (float) texH;

		buffer.pos(x1 + 0.0F, y1 + 0.0F, 0).tex(mU, xV).endVertex();
		buffer.pos(x2 + 0.0F, y2 + 0.0F, 0).tex(xU, xV).endVertex();
		buffer.pos(x3 + 0.0F, y3 + 0.0F, 0).tex(xU, mV).endVertex();
		buffer.pos(x4 + 0.0F, y4 + 0.0F, 0).tex(mU, mV).endVertex();
	}
}