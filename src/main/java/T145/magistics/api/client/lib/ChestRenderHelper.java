package T145.magistics.api.client.lib;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

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

	public static void renderChest(TileEntity tile) {
		GL11.glRotatef(90F, 0F, 1F, 0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(tile, 0, 0, 0, 0F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}
}