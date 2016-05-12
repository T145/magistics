package T145.magistics.client.lib;

import net.minecraft.client.model.ModelChest;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * @author T145
 * 
 * A simple library designed to assist in normal chest rendering in the world.
 */
public class WorldChestRenderHelper {
	private static final ModelChest MODEL = new ModelChest();

	public static void preRenderChest(float x, float y, float z) {
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glTranslatef(x, y + 1F, z + 1F);
		GL11.glScalef(1F, -1F, -1F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	public static void rotateChest(int facing) {
		switch (facing) {
		case 2:
			GL11.glRotatef(180, 0F, 1F, 0F);
			break;
		case 4:
			GL11.glRotatef(90, 0F, 1F, 0F);
			break;
		case 5:
			GL11.glRotatef(-90, 0F, 1F, 0F);
			break;
		}
	}

	public static void postRenderChest(float prevLidAngle, float lidAngle, float partialTick) {
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float lidOpening = prevLidAngle + (lidAngle - prevLidAngle) * partialTick;
		lidOpening = 1F - lidOpening;
		lidOpening = 1F - lidOpening * lidOpening * lidOpening;
		MODEL.chestLid.rotateAngleX = -(lidOpening * (float) Math.PI / 2F);
		MODEL.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1F, 1F, 1F, 1F);
	}

	/*private static void renderChest(int facing, float x, float y, float z, float prevLidAngle, float lidAngle, float partialTick) {
		preRenderChest(x, y, z);
		rotateChest(facing);
		postRenderChest(prevLidAngle, lidAngle, partialTick);
	}

	public static void renderChest(ResourceLocation texture, int facing, float x, float y, float z, float prevLidAngle, float lidAngle, float partialTick) {
		UtilsFX.bindTexture(texture);
		renderChest(facing, x, y, z, prevLidAngle, lidAngle, partialTick);
	}*/
}