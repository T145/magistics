package T145.magistics.client.renderers;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.common.tiles.TileSortingChestHungryMetal;

import com.dynious.refinedrelocation.lib.Resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileSortingChestHungryMetalRenderer extends TileChestHungryMetalRenderer {
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		if (MinecraftForgeClient.getRenderPass() == 0)
			super.renderTileEntityAt(tile, x, y, z, partialTick);
		else if (tile instanceof TileSortingChestHungryMetal) {
			TileSortingChestHungryMetal chest = (TileSortingChestHungryMetal) tile;

			bindTexture(Resources.MODEL_TEXTURE_OVERLAY_CHEST);

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
			GL11.glScalef(1.0F, -1F, -1F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);

			int angle = 0, facing = chest.getFacing();
			if (facing == 2)
				angle = 180;
			if (facing == 4)
				angle = 90;
			if (facing == 5)
				angle = -90;

			GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

			float lidangle = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * partialTick;
			lidangle = 1.0F - lidangle;
			lidangle = 1.0F - lidangle * lidangle * lidangle;

			model.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2.0F);
			model.renderAll();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}
}