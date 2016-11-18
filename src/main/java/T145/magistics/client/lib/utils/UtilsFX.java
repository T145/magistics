package T145.magistics.client.lib.utils;

import net.minecraft.client.renderer.GlStateManager;

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
}