package T145.magistics.client.renderers.tile;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileChestHungryEnderRenderer extends TileEntitySpecialRenderer {
	private ModelChest chestModel = new ModelChest();

	@Override
	public void renderTileEntityAt(TileEntity tile, double i, double j, double k, float mod) {
		renderChestAt((TileChestHungryEnder) tile, i, j, k, mod);
	}

	public void renderChestAt(TileChestHungryEnder chest, double i, double j, double k, float mod) {
		bindTexture(new ResourceLocation("magistics", "textures/models/chest_hungry/ender.png"));
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) i, (float) j + 1.0F, (float) k + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		switch (chest.orientation.ordinal()) {
		case 2:
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			break;
		case 3:
			GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
			break;
		case 4:
			GL11.glRotatef(90.0F, 0.0F, 1.0f, 0.0F);
			break;
		case 5:
			GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
			break;
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float lidOpening = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * mod;
		lidOpening = 1.0F - lidOpening;
		lidOpening = 1.0F - lidOpening * lidOpening * lidOpening;
		chestModel.chestLid.rotateAngleX = -(lidOpening * (float) Math.PI / 2.0F);
		chestModel.renderAll();
		GL11.glDisable(32826);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}