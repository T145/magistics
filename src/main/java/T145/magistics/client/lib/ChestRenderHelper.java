package T145.magistics.client.lib;

import net.minecraft.client.model.ModelChest;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

import thermalexpansion.render.model.ModelStrongbox;
import cofh.core.render.RenderUtils;

public class ChestRenderHelper {
	public static void renderChest(ItemRenderType type) {
		switch (type) {
		case ENTITY:
			renderChest(0.5F, 0.5F, 0.5F);
			break;
		case EQUIPPED:
			renderChest(1F, 1F, 1F);
			break;
		case EQUIPPED_FIRST_PERSON:
			renderChest(1F, 1F, 1F);
			break;
		case INVENTORY:
			renderChest(0F, 0.075F, 0F);
			break;
		default:
			break;
		}
	}

	public static void renderChest(float i, float j, float k) {
		GL11.glPushMatrix();
		GL11.glTranslatef(i, j, k);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		new ModelChest().renderAll();
		GL11.glPopMatrix();
	}

	public static void renderChest() {
		renderChest(0.5F, 0.5F, 0.5F);
	}

	public static void renderStrongbox(int renderParam, int facing, float xRotation, double i, double j, double k) {
		ModelStrongbox model = new ModelStrongbox();
		model.boxLid.rotateAngleX = xRotation;
		GL11.glPushMatrix();
		GL11.glTranslated(i, j + 1, k + 1);
		GL11.glScalef(1F, -1F, -1F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glRotatef((float) RenderUtils.facingAngle[facing], 0F, 1F, 0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		GL11.glEnable(32826);
		model.render(renderParam);
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}
}