package T145.magistics.client.lib;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author T145
 * A simple library to assist in inventory rendering
 */
@SideOnly(Side.CLIENT)
public class InventoryRenderHelper {
	private static final ModelChest chestModel = new ModelChest();

	public static void renderTile(TileEntity tile) {
		GL11.glPushMatrix();
		GL11.glRotatef(90F, 0F, 1F, 0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(tile, 0, 0, 0, 0F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	public static void renderChestAtPos(float x, float y, float z) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		chestModel.renderAll();
		GL11.glPopMatrix();
	}

	public static void renderChestNormally() {
		renderChestAtPos(0.5F, 0.5F, 0.5F);
	}

	public static void renderChestFully(ItemRenderType type) {
		switch (type) {
		case ENTITY:
			renderChestNormally();
			break;
		case INVENTORY:
			renderChestAtPos(0F, 0.075F, 0F);
			break;
		default:
			renderChestAtPos(1F, 1F, 1F);
			break;
		}
	}

	// with alpha

	public static void renderChestAtPosWithAlpha(float x, float y, float z, boolean alpha) {
		GL11.glPushMatrix();

		if (alpha)
			GL11.glEnable(GL11.GL_ALPHA_TEST);

		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		chestModel.renderAll();

		if (alpha)
			GL11.glDisable(GL11.GL_ALPHA_TEST);

		GL11.glPopMatrix();
	}

	public static void renderChestNormallyWithAlpha(boolean alpha) {
		renderChestAtPosWithAlpha(0.5F, 0.5F, 0.5F, alpha);
	}

	public static void renderChestFullyWithAlpha(ItemRenderType type, boolean alpha) {
		switch (type) {
		case ENTITY:
			renderChestNormallyWithAlpha(alpha);
			break;
		case INVENTORY:
			renderChestAtPosWithAlpha(0F, 0.075F, 0F, alpha);
			break;
		default:
			renderChestAtPosWithAlpha(1F, 1F, 1F, alpha);
			break;
		}
	}
}