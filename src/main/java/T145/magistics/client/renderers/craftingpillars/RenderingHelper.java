package T145.magistics.client.renderers.craftingpillars;

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
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.Color;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.ResourceLocation;

public class RenderingHelper
{
	public static class ItemRender extends RenderItem
	{
		private boolean bob, spread;

		public ItemRender(boolean bob, boolean spread)
		{
			super();
			this.bob = bob;
			this.spread = spread;
			this.setRenderManager(RenderManager.instance);
		}

		public void render(EntityItem item, float x, float y, float z, boolean showNum)
		{	
			this.doRender(item, x, y, z, 0F, 0F);

			int number = item.getEntityItem().stackSize;
			if(number > 0 && showNum)
			{
				glDisable(GL_LIGHTING);
				renderFloatingTextWithBackground(x, y+(this.bob ? 0.6F : 0.4F), z, 0.4F, ""+number, Color.white.getRGB(), new Color(0F, 0F, 0F, 0.5F));
				glEnable(GL_LIGHTING);
			}
		}

		@Override
		public boolean shouldBob()
		{
			return this.bob;
		}

		@Override
		public boolean shouldSpreadItems()
		{
			return this.spread;
		}
	}

	public static void applyFloatingRotations()
	{
		glRotatef(-RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
		glRotatef(RenderManager.instance.playerViewX, 1.0F, 0.0F, 0.0F);
	}

	public static void renderFloatingText(float x, float y, float z, float scale, String text, int color)
	{
		FontRenderer fontRenderer = RenderManager.instance.getFontRenderer();
		glPushMatrix();
		glTranslatef(x, y, z);
		applyFloatingRotations();
		glRotatef(180F, 0.0F, 0.0F, 1.0F);
		glScalef(0.05F*scale, 0.05F*scale, 1F);
		fontRenderer.drawString(text, -fontRenderer.getStringWidth(text)/2, -fontRenderer.FONT_HEIGHT/2, color);
		glPopMatrix();
	}

	public static void renderFloatingRect(float x, float y, float z, float w, float h, Color c)
	{
//		FontRenderer fontRenderer = RenderManager.instance.getFontRenderer();
		glPushMatrix();
		glTranslatef(x, y, z);
		applyFloatingRotations();
		glScalef(0.05F, 0.05F, 1F);

		glColor4f(c.getRed()/256F, c.getGreen()/256F, c.getBlue()/256F, c.getAlpha()/256F);
		glBindTexture(GL_TEXTURE_2D, 0);
		glBegin(GL_QUADS);
		glVertex3f(-w-1, -h-1, 0.001F);
		glVertex3f(-w-1, h, 0.001F);
		glVertex3f(w, h, 0.001F);
		glVertex3f(w, -h-1, 0.001F);
		glEnd();
		glDisable(GL_BLEND);
		glPopMatrix();
	}

	public static void renderFloatingTextWithBackground(float x, float y, float z, float scale, String text, int color, Color bgColor)
	{
		FontRenderer fontRenderer = RenderManager.instance.getFontRenderer();
		glPushMatrix();
		glTranslatef(x, y, z);
		applyFloatingRotations();
		glRotatef(180F, 0.0F, 0.0F, 1.0F);
		glScalef(0.05F*scale, 0.05F*scale, 1F);

		int h = fontRenderer.FONT_HEIGHT/2;
		int w = fontRenderer.getStringWidth(text)/2;
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glColor4f(bgColor.getRed()/256F, bgColor.getGreen()/256F, bgColor.getBlue()/256F, bgColor.getAlpha()/256F);
		glBindTexture(GL_TEXTURE_2D, 0);
		glBegin(GL_QUADS);
		glVertex3f(-w-1, -h-1, 0.001F);
		glVertex3f(-w-1, h, 0.001F);
		glVertex3f(w, h, 0.001F);
		glVertex3f(w, -h-1, 0.001F);
		glEnd();
		glDisable(GL_BLEND);

		fontRenderer.drawString(text, -fontRenderer.getStringWidth(text)/2, -fontRenderer.FONT_HEIGHT/2, color);
		glPopMatrix();
	}
	
	public static void renderFacingQuad(float x, float y, float z, float scale, ResourceLocation texture, Color c)
	{
		glPushMatrix();
		glTranslatef(x, y, z);
		applyFloatingRotations();
		glScalef(0.1F * scale, 0.1F * scale, 1F);

		int h = 1;
		int w = 1;
		glDisable(GL_LIGHTING);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glColor4f(c.getRed()/256F, c.getGreen()/256F, c.getBlue()/256F, 0.75F);
		
		RenderManager.instance.renderEngine.bindTexture(texture);
		
		glBegin(GL_QUADS);
		glTexCoord2f(0F, 1F);
		glVertex3f(-w, -h, 0.001F);
		glTexCoord2f(0F, 0F);
		glVertex3f(-w, h, 0.001F);
		glTexCoord2f(1F, 0F);
		glVertex3f(w, h, 0.001F);
		glTexCoord2f(1F, 1F);
		glVertex3f(w, -h, 0.001F);
		glEnd();
		glDisable(GL_BLEND);
		glEnable(GL_LIGHTING);
		glPopMatrix();
	}
}
