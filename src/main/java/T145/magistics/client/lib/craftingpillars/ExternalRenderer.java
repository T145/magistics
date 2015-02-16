package T145.magistics.client.lib.craftingpillars;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.Color;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;

public class ExternalRenderer extends RenderItem {
	private boolean bob, spread;

	public ExternalRenderer(boolean bobSize, boolean spreadSize) {
		super();
		bob = bobSize;
		spread = spreadSize;
		setRenderManager(RenderManager.instance);
	}

	public static void applyFloatingRotations() {
		glRotatef(-RenderManager.instance.playerViewY, 0F, 1F, 0F);
		glRotatef(RenderManager.instance.playerViewX, 1F, 0F, 0F);
	}

	public static void renderFloatingTextWithBackground(float x, float y, float z, float scale, String text, int color, Color bgColor) {
		FontRenderer fontRenderer = RenderManager.instance.getFontRenderer();
		glPushMatrix();
		glTranslatef(x, y, z);
		applyFloatingRotations();
		glRotatef(180F, 0F, 0F, 1F);
		glScalef(0.05F * scale, 0.05F * scale, 1F);

		int h = fontRenderer.FONT_HEIGHT / 2, w = fontRenderer.getStringWidth(text) / 2;
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glColor4f(bgColor.getRed() / 256F, bgColor.getGreen() / 256F, bgColor.getBlue() / 256F, bgColor.getAlpha() / 256F);
		glBindTexture(GL_TEXTURE_2D, 0);
		glBegin(GL_QUADS);
		glVertex3f(-w - 1, -h - 1, 0.001F);
		glVertex3f(-w - 1, h, 0.001F);
		glVertex3f(w, h, 0.001F);
		glVertex3f(w, -h - 1, 0.001F);
		glEnd();
		glDisable(GL_BLEND);

		fontRenderer.drawString(text, -fontRenderer.getStringWidth(text) / 2, -fontRenderer.FONT_HEIGHT / 2, color);
		glPopMatrix();
	}

	public void render(EntityItem item, float x, float y, float z, boolean showNum) {
		doRender(item, x, y, z, 0F, 0F);

		int number = item.getEntityItem().stackSize;
		if (number > 0 && showNum) {
			glDisable(GL_LIGHTING);
			renderFloatingTextWithBackground(x, y + (bob ? 0.6F : 0.4F), z, 0.4F, "" + number, Color.white.getRGB(), new Color(0F, 0F, 0F, 0.5F));
			glEnable(GL_LIGHTING);
		}
	}

	@Override
	public boolean shouldBob() {
		return bob;
	}

	@Override
	public boolean shouldSpreadItems() {
		return spread;
	}
}