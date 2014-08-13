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
	private ModelChest chest = new ModelChest();

	@Override
	public void renderTileEntityAt(TileEntity tile, double i, double j, double k, float mod) {
		renderChest((TileChestHungryEnder) tile, i, j, k, mod);
	}

	public void renderChest(TileChestHungryEnder tile, double i, double j, double k, float mod) {
		int meta = 0, rotation = 0;

		if (tile.hasWorldObj())
			meta = tile.getBlockMetadata();

		bindTexture(new ResourceLocation("magistics", "textures/models/chest_hungry/ender.png"));
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) i, (float) j + 1.0F, (float) k + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

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
		float f1 = tile.field_145975_i + (tile.field_145972_a - tile.field_145975_i) * mod;
		f1 = 1.0F - f1;
		f1 = 1.0F - f1 * f1 * f1;
		chest.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
		chest.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}