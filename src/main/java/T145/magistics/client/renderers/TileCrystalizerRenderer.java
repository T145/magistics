package T145.magistics.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCrystal;
import T145.magistics.common.tiles.TileCrystalizer;

public class TileCrystalizerRenderer extends TileEntitySpecialRenderer {
	private ModelCrystal model = new ModelCrystal();

	private void drawCrystal(double x, double y, double z, float spinY, float opacity) {
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Tessellator t = Tessellator.instance;
		t.setBrightness(220);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GL11.glRotatef(spinY, 0F, 1F, 0F);
		GL11.glRotatef(25F, 1F, 0F, 0F);
		GL11.glPushMatrix();
		GL11.glColor4f(opacity, opacity, opacity, 1F);
		GL11.glScalef(0.15F, 0.45F, 0.15F);
		model.render();
		GL11.glScalef(1F, 1F, 1F);
		GL11.glPopMatrix();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1F, 1F, 1F, 1F);
	}

	public void renderCrystalizerAt(TileCrystalizer crystalizer, double x, double y, double z) {
		int spin = Minecraft.getMinecraft().thePlayer.ticksExisted;
		float opacity = 0F, spinY = 36F, buf = 72F;

		if (crystalizer.isCooking()) {
			spinY += spin % 360;
			opacity = MathHelper.sin(spin / 5F) * 0.15F + 0.15F;
		}

		UtilsFX.bindTexture("magistics", "textures/blocks/crystal_air.png");
		drawCrystal(x, y, z, spinY, 1F - opacity);
		UtilsFX.bindTexture("magistics", "textures/blocks/crystal_fire.png");
		drawCrystal(x, y, z, spinY, 1F - opacity);
		spinY += buf;
		UtilsFX.bindTexture("magistics", "textures/blocks/crystal_water.png");
		drawCrystal(x, y, z, spinY, 1F - opacity);
		spinY += buf;
		UtilsFX.bindTexture("magistics", "textures/blocks/crystal_earth.png");
		drawCrystal(x, y, z, spinY, 1F - opacity);
		spinY += buf;
		UtilsFX.bindTexture("magistics", "textures/blocks/crystal_order.png");
		drawCrystal(x, y, z, spinY, 1F - opacity);
		spinY += buf;
		UtilsFX.bindTexture("magistics", "textures/blocks/crystal_entropy.png");
		drawCrystal(x, y, z, spinY, 0.4F - opacity);
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		renderCrystalizerAt((TileCrystalizer) tile, x, y, z);
	}
}