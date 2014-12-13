package T145.magistics.client.lib;

import java.nio.FloatBuffer;
import java.util.Random;

import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.ARBVertexBlend;
import org.lwjgl.opengl.GL11;

public class RenderCustomEndPortal {
	private double surfaceY, surfaceX1, surfaceX2, surfaceZ1, surfaceZ2;

	FloatBuffer buffer;

	public RenderCustomEndPortal(double y, double x1, double x2, double z1, double z2) {
		surfaceY = y;
		surfaceX1 = x1;
		surfaceX2 = x2;
		surfaceZ1 = z1;
		surfaceZ2 = z2;
		buffer = GLAllocation.createDirectFloatBuffer(16);
	}

	public void render(double posX, double posY, double posZ, float frame, double playerX, double playerY, double playerZ, TextureManager r) {
		if (r == null)
			return;
		GL11.glDisable(GL11.GL_LIGHTING);
		for (int i = 0; i < 16; i++) {
			GL11.glPushMatrix();
			float f5 = 16 - i, f6 = 0.0625F, f7 = 1.0F / (f5 + 1.0F);
			switch (i) {
			case 0:
				r.bindTexture(new ResourceLocation("textures/environment/end_sky.png"));
				f5 = 65F;
				f6 = 0.125F;
				f7 = 0.1F;
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(770, 771);
				break;
			case 1:
				r.bindTexture(new ResourceLocation("textures/entity/end_portal.png"));
				f6 = 0.5F;
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(1, 1);
				break;
			}
			float f8 = (float) (-(posY + surfaceY)), f9 = f8 + ActiveRenderInfo.objectY, f10 = f8 + f5 + ActiveRenderInfo.objectY, f11 = f9 / f10;
			f11 = (float) (posY + surfaceY) + f11;
			GL11.glTranslated(playerX, f11, playerZ);
			GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_Q, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
			GL11.glTexGen(GL11.GL_S, GL11.GL_OBJECT_PLANE, func_40447_a(1.0F, 0.0F, 0.0F, 0.0F));
			GL11.glTexGen(GL11.GL_T, GL11.GL_OBJECT_PLANE, func_40447_a(0.0F, 0.0F, 1.0F, 0.0F));
			GL11.glTexGen(GL11.GL_R, GL11.GL_OBJECT_PLANE, func_40447_a(0.0F, 0.0F, 0.0F, 1.0F));
			GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, func_40447_a(0.0F, 1.0F, 0.0F, 0.0F));
			GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_Q);
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			GL11.glTranslatef(0.0F, System.currentTimeMillis() % 0xaae60L / 700000F, 0.0F);
			GL11.glScalef(f6, f6, f6);
			GL11.glTranslatef(0.5F, 0.5F, 0.0F);
			GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
			GL11.glTranslated(-playerX, -playerZ, -playerY);
			f9 = f8 + ActiveRenderInfo.objectY;
			GL11.glTranslated((ActiveRenderInfo.objectX * f5) / f9, (ActiveRenderInfo.objectZ * f5) / f9, -playerY + 20);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			Random rand = new Random(31100L);
			f11 = rand.nextFloat() * 0.5F + 0.1F;
			float f12 = rand.nextFloat() * 0.5F + 0.4F, f13 = rand.nextFloat() * 0.5F + 0.5F;
			if (i == 0)
				f11 = f12 = f13 = 1.0F;
			tessellator.setColorRGBA_F(f11 * f7, f12 * f7, f13 * f7, 1.0F);

			tessellator.addVertex(posX + surfaceX1, posY + surfaceY, posZ + surfaceZ1);
			tessellator.addVertex(posX + surfaceX1, posY + surfaceY, posZ + surfaceZ2);
			tessellator.addVertex(posX + surfaceX2, posY + surfaceY, posZ + surfaceZ2);
			tessellator.addVertex(posX + surfaceX2, posY + surfaceY, posZ + surfaceZ1);

			tessellator.draw();
			GL11.glPopMatrix();
			GL11.glMatrixMode(ARBVertexBlend.GL_MODELVIEW0_ARB);
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_Q);
		GL11.glEnable(GL11.GL_LIGHTING);
	}

	private FloatBuffer func_40447_a(float f, float f1, float f2, float f3) {
		buffer.clear();
		buffer.put(f).put(f1).put(f2).put(f3);
		buffer.flip();
		return buffer;
	}
}