package T145.magistics.client.renderers.tile;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.common.blocks.BlockChestHungry;
import T145.magistics.common.config.Log;
import T145.magistics.common.tiles.TileChestHungry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileChestHungryRenderer extends TileEntitySpecialRenderer {
	private ModelChest chestModel = new ModelChest(), largeChestModel = new ModelLargeChest();

	public void render(TileChestHungry tile, double i, double j, double k, float partialTick) {
		int meta;

		if (tile.hasWorldObj()) {
			Block block = tile.getBlockType();
			meta = tile.getBlockMetadata();

			if (block instanceof BlockChestHungry && meta == 0) {
				try {
					((BlockChestHungry) block).func_149954_e(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord);
				} catch (ClassCastException e) {
					Log.error("A false render attempt was made for an overriden Hungry Chest at its quardinates: %dX %dY %dZ", tile.xCoord, tile.yCoord, tile.zCoord);
				}
				meta = tile.getBlockMetadata();
			}

			tile.checkForAdjacentChests();
		} else
			meta = 0;

		if (tile.adjacentChestZNeg == null && tile.adjacentChestXNeg == null) {
			ModelChest model;

			if (tile.adjacentChestXPos == null && tile.adjacentChestZPos == null) {
				model = chestModel;

				if (tile.func_145980_j() == 1)
					bindTexture(new ResourceLocation("magistics", "textures/models/chest_hungry/trapped.png"));
				else
					bindTexture(new ResourceLocation("thaumcraft", "textures/models/chesthungry.png"));
			} else {
				model = largeChestModel;

				if (tile.func_145980_j() == 1)
					bindTexture(new ResourceLocation("magistics", "textures/models/chest_hungry/trapped_double.png"));
				else
					bindTexture(new ResourceLocation("magistics", "textures/models/chest_hungry/normal_double.png"));
			}

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1F, 1F, 1F, 1F);
			GL11.glTranslatef((float) i, (float) j + 1F, (float) k + 1F);
			GL11.glScalef(1F, -1F, -1F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			short short1 = 0;

			if (meta == 2)
				short1 = 180;
			if (meta == 3)
				short1 = 0;
			if (meta == 4)
				short1 = 90;
			if (meta == 5)
				short1 = -90;
			if (meta == 2 && tile.adjacentChestXPos != null)
				GL11.glTranslatef(1F, 0F, 0F);
			if (meta == 5 && tile.adjacentChestZPos != null)
				GL11.glTranslatef(0F, 0F, -1F);

			GL11.glRotatef((float) short1, 0F, 1F, 0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float f1 = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialTick;
			float f2;

			if (tile.adjacentChestZNeg != null) {
				f2 = tile.adjacentChestZNeg.prevLidAngle + (tile.adjacentChestZNeg.lidAngle - tile.adjacentChestZNeg.prevLidAngle) * partialTick;

				if (f2 > f1)
					f1 = f2;
			}
			if (tile.adjacentChestXNeg != null) {
				f2 = tile.adjacentChestXNeg.prevLidAngle + (tile.adjacentChestXNeg.lidAngle - tile.adjacentChestXNeg.prevLidAngle) * partialTick;

				if (f2 > f1)
					f1 = f2;
			}

			f1 = 1F - f1;
			f1 = 1F - f1 * f1 * f1;
			model.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2F);
			model.renderAll();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1F, 1F, 1F, 1F);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double i, double j, double k, float partialTick) {
		render((TileChestHungry) tile, i, j, k, partialTick);
	}
}