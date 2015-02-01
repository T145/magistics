package T145.magistics.client.renderers.tile;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.common.tiles.TileSortingChestHungryAlchemical;

import com.dynious.refinedrelocation.lib.Resources;

import cpw.mods.fml.client.FMLClientHandler;

public class TileSortingChestHungryAlchemicalRenderer extends TileEntitySpecialRenderer {
	public final ModelChest model = new ModelChest();

	public void render(TileSortingChestHungryAlchemical chest, double i, double j, double k, float tick, int pass) {
		ForgeDirection direction = null;

		if (chest.hasWorldObj())
			direction = chest.getOrientation();

		if (pass == 0)
			switch (chest.getState()) {
			case 0:
				bindTexture(new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_small.png"));
				break;
			case 1:
				bindTexture(new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_medium.png"));
				break;
			case 2:
				bindTexture(new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_large.png"));
				break;
			}
		else
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(Resources.MODEL_TEXTURE_OVERLAY_ALCHEMICAL_CHEST);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glTranslatef((float) i, (float) j + 1F, (float) k + 1F);
		GL11.glScalef(1F, -1F, -1F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		short angle = 0;
		if (direction != null)
			switch (direction) {
			case NORTH:
				angle = 180;
				break;
			case EAST:
				angle = -90;
				break;
			case WEST:
				angle = 90;
				break;
			default:
				angle = 0;
				break;
			}

		GL11.glRotatef(angle, 0F, 1F, 0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float adjustedLidAngle = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * tick;
		adjustedLidAngle = 1F - adjustedLidAngle;
		adjustedLidAngle = 1F - adjustedLidAngle * adjustedLidAngle * adjustedLidAngle;
		model.chestLid.rotateAngleX = -(adjustedLidAngle * (float) Math.PI / 2F);
		model.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1F, 1F, 1F, 1F);
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float tick) {
		if (tile instanceof TileSortingChestHungryAlchemical)
			for (int pass = 0; pass < 2; pass++)
				render((TileSortingChestHungryAlchemical) tile, x, y, z, tick, pass);
	}
}