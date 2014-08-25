package T145.magistics.client.renderers.tile;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.lib.UtilsFX;
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
		int meta;
		if (chest.hasWorldObj())
			meta = chest.getBlockMetadata();
		else
			meta = 0;

		UtilsFX.bindTexture("magistics", "textures/models/chest_hungry/ender.png");
		GL11.glPushMatrix();
		GL11.glEnable(32826);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) i, (float) j + 1.0F, (float) k + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		short rotation;
		switch (meta) {
		case 2:
			rotation = 180;
			break;
		case 4:
			rotation = 90;
			break;
		case 5:
			rotation = -90;
			break;
		default:
			rotation = 0;
			break;
		}

		GL11.glRotatef((float) rotation, 0.0F, 1.0F, 0.0F);
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