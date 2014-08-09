package T145.magistics.client.renderers.tile;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.common.blocks.BlockChestHungryMod;
import T145.magistics.common.tiles.TileChestHungryMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileChestHungryModRenderer extends TileEntitySpecialRenderer {
	private ModelChest chestModel = new ModelChest();
	private static BlockChestHungryMod.Types types;

	public static ResourceLocation chestModelTexture[] = new ResourceLocation[types.values().length];

	@Override
	public void renderTileEntityAt(TileEntity tile, double i, double j, double k, float mod) {
		renderChest((TileChestHungryMod) tile, i, j, k, mod);
	}

	private void renderChest(TileChestHungryMod tile, double i, double j, double k, float mod) {
		int mark = 0;

		if (tile.hasWorldObj())
			mark = tile.getBlockMetadata();

		chestModelTexture[mark] = new ResourceLocation("magistics", "textures/models/chest_hungry_" + types.values()[mark]);
		bindTexture(chestModelTexture[mark]);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) i, (float) j + 1.0F, (float) k + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		short rotationShift = 0;
		switch (rotationShift) {
		case 2:
			rotationShift = 180;
			break;
		case 3:
			rotationShift = 0;
			break;
		case 4:
			rotationShift = 90;
			break;
		case 5:
			rotationShift = -90;
			break;
		}

		GL11.glRotatef((float) rotationShift, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float f = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * mod;
		f = 1.0F - f;
		f = 1.0F - f * f * f;
		chestModel.chestLid.rotateAngleX = -(f * (float) Math.PI / 2.0F);
		chestModel.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}