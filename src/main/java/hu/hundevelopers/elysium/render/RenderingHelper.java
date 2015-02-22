package hu.hundevelopers.elysium.render;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderingHelper {
	public static void bindTexture(ResourceLocation res) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(res);
	}

	public static void setColor(float r, float g, float b, float a) {
		if (a != 1F) {
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		}
		glColor4f(r, g, b, a);
	}

	public static class Gui {
		public static void drawRect(int x, int y, int width, int height) {
			glBindTexture(GL_TEXTURE_2D, 0);
			glBegin(GL_QUADS);
			glVertex2i(x, y);
			glVertex2i(x, y + height);
			glVertex2i(x + width, y + height);
			glVertex2i(x + width, y);
			glEnd();
		}
		public static void drawTexturedRect(int x, int y, int width, int height, float tx, float ty, float tw, float th) {

			glBegin(GL_QUADS);
			glTexCoord2f(0F, 0F);
			glVertex2i(x, y);
			glTexCoord2f(0F, 1F);
			glVertex2i(x, y + height);
			glTexCoord2f(1F, 1F);
			glVertex2i(x + width, y + height);
			glTexCoord2f(1F, 0F);
			glVertex2i(x + width, y);
			glEnd();
		}

		public static void drawString(int x, int y, int c, String text) {
			Minecraft.getMinecraft().fontRenderer.drawString(text, x, y, c);
		}

		public static void drawStringWithShadow(int x, int y, int c, String text) {
			Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text, x, y, c);
		}

		public static void drawCenteredString(int x, int y, int c, String text) {
			drawString(x - Minecraft.getMinecraft().fontRenderer.getStringWidth(text) / 2, y - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT / 2,
					c, text);
		}
		
		public static void drawCenteredStringWithShadow(int x, int y, int c, String text) {
			drawStringWithShadow(x - Minecraft.getMinecraft().fontRenderer.getStringWidth(text) / 2, y - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT / 2,
					c, text);
		}
	}
}
