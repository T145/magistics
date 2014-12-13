package T145.magistics.client.lib;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.lib.UtilsFX;

public class SealRenderHelper {
	public static int[] colors = new int[] { 13532671, 16777088, 8421631, 8454016, 16744576, 4194368 };

	public void translateFromOrientation(double x, double y, double z, int orientation) {
		GL11.glPushMatrix();

		switch (orientation) {
		case 0:
			GL11.glTranslatef((float) x, (float) y + 1F, (float) z + 1F);
			GL11.glRotatef(-90F, 1F, 0F, 0F);
			break;
		case 1:
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glRotatef(90F, 1F, 0F, 0F);
			break;
		case 3:
			GL11.glTranslatef((float) x + 1F, (float) y, (float) z);
			GL11.glRotatef(180F, 0F, 1F, 0F);
			break;
		case 4:
			GL11.glTranslatef((float) x + 1F, (float) y, (float) z + 1F);
			GL11.glRotatef(90F, 0F, 1F, 0F);
			break;
		case 5:
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glRotatef(-90F, 0F, 1F, 0F);
			break;
		default:
			GL11.glTranslatef((float) x, (float) y, (float) z + 1F);
			break;
		}

		GL11.glPushMatrix();
	}

	public void drawSeal(float var1, int var2, int color) {
		Tessellator var4 = Tessellator.instance;
		GL11.glRotatef(90F, -1F, 0F, 0F);
		GL11.glRotatef(var1, 0F, 1F, 0F);
		GL11.glTranslatef(-0.5F, 0F, -0.5F);
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

		if (var2 != 2)
			UtilsFX.bindTexture(new ResourceLocation("magistics", "textures/seals/s" + var2 + "_" + color + ".png"));
		else
			UtilsFX.bindTexture(new ResourceLocation("magistics", "textures/seals/seal5.png"));

		GL11.glColor4f(1F, 1F, 1F, 1F);
		var4.startDrawingQuads();
		var4.setBrightness(220);

		if (var2 == 2)
			var4.setColorRGBA_I(colors[color], 255);

		var4.addVertexWithUV(0D, 0D, 1D, 0D, 1D);
		var4.addVertexWithUV(1D, 0D, 1D, 1D, 1D);
		var4.addVertexWithUV(1D, 0D, 0D, 1D, 0D);
		var4.addVertexWithUV(0D, 0D, 0D, 0D, 0D);
		var4.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}