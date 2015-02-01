package T145.magistics.client.renderers.tile;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.common.tiles.TileChestHungryAlchemical;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileChestHungryAlchemicalRenderer extends TileEntitySpecialRenderer {
	public final ModelChest model = new ModelChest();

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float tick) {
		if (tile instanceof TileChestHungryAlchemical) {
			TileChestHungryAlchemical chest = (TileChestHungryAlchemical) tile;
			ForgeDirection direction = null;

			if (chest.hasWorldObj())
				direction = chest.getOrientation();

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

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1F, 1F, 1F, 1F);
			GL11.glTranslatef((float) x, (float) y + 1F, (float) z + 1F);
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
	}
}