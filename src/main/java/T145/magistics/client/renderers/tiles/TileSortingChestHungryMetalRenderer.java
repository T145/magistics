package T145.magistics.client.renderers.tiles;

import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.dynious.refinedrelocation.lib.Resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.TileEntityIronChest;

@SideOnly(Side.CLIENT)
public class TileSortingChestHungryMetalRenderer extends TileChestHungryMetalRenderer {
	@Override
	public void render(TileEntityIronChest tile, double x, double y, double z, float partialTick) {
		if (MinecraftForgeClient.getRenderPass() == 0)
			super.render(tile, x, y, z, partialTick);
		else {
			if (tile == null)
				return;
			int facing = tile.getFacing();

			bindTexture(Resources.MODEL_TEXTURE_OVERLAY_CHEST);

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
			GL11.glScalef(1.0F, -1F, -1F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			int k = 0;
			if (facing == 2)
				k = 180;
			if (facing == 4)
				k = 90;
			if (facing == 5)
				k = -90;
			GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float lidangle = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialTick;
			lidangle = 1.0F - lidangle;
			lidangle = 1.0F - lidangle * lidangle * lidangle;
			model.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2.0F);
			model.renderAll();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}
}