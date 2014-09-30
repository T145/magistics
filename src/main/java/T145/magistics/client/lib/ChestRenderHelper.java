package T145.magistics.client.lib;

import net.minecraft.client.model.ModelChest;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

public class ChestRenderHelper {
	public static void renderChest(ItemRenderType type, ModelChest model) {
		switch (type) {
		case ENTITY: {
			renderChest(0.5F, 0.5F, 0.5F, model);
			break;
		}
		case EQUIPPED: {
			renderChest(1.0F, 1.0F, 1.0F, model);
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			renderChest(1.0F, 1.0F, 1.0F, model);
			break;
		}
		case INVENTORY: {
			renderChest(0.0F, 0.075F, 0.0F, model);
			break;
		}
		default:
			break;
		}
	}

	private static void renderChest(float x, float y, float z, ModelChest model) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		model.renderAll();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}