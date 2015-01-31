package T145.magistics.client.renderers.tile;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.common.tiles.TileSortingChestHungryAlchemical;

import com.dynious.refinedrelocation.lib.Resources;

import cpw.mods.fml.client.FMLClientHandler;

public class TileSortingChestHungryAlchemicalRenderer extends TileEntitySpecialRenderer {
	public final ModelChest model = new ModelChest();

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float tick) {
		if (MinecraftForgeClient.getRenderPass() == 0) {
			if (tile instanceof TileSortingChestHungryAlchemical) {
				TileSortingChestHungryAlchemical chest = (TileSortingChestHungryAlchemical) tile;
				ForgeDirection direction = null;

				if (chest.getWorldObj() != null)
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
		} else if (tile instanceof TileSortingChestHungryAlchemical) {
			TileSortingChestHungryAlchemical chest = (TileSortingChestHungryAlchemical) tile;
			ForgeDirection direction = null;

			if (chest.getWorldObj() != null)
				direction = chest.getOrientation();

			FMLClientHandler.instance().getClient().renderEngine.bindTexture(Resources.MODEL_TEXTURE_OVERLAY_ALCHEMICAL_CHEST);

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			short angle = 0;

			if (direction != null) {
				if (direction == ForgeDirection.NORTH) {
					angle = 180;
				} else if (direction == ForgeDirection.SOUTH) {
					angle = 0;
				} else if (direction == ForgeDirection.WEST) {
					angle = 90;
				} else if (direction == ForgeDirection.EAST) {
					angle = -90;
				}
			}

			GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float adjustedLidAngle = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * tick;
			adjustedLidAngle = 1.0F - adjustedLidAngle;
			adjustedLidAngle = 1.0F - adjustedLidAngle * adjustedLidAngle * adjustedLidAngle;
			model.chestLid.rotateAngleX = -(adjustedLidAngle * (float) Math.PI / 2.0F);
			model.renderAll();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}