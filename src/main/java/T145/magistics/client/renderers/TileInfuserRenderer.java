package T145.magistics.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.fx.particles.FXWisp;
import thaumcraft.client.lib.UtilsFX;
import T145.magistics.common.tiles.TileInfuser;

public class TileInfuserRenderer extends TileEntitySpecialRenderer {
	private void drawDisk(double x, double y, double z, float rotation, boolean active, boolean isDark) {
		Tessellator t = Tessellator.instance;

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
		GL11.glPushMatrix();
		GL11.glRotatef(rotation, 0F, 1F, 0F);
		GL11.glTranslatef(-0.45F, 0F, -0.45F);
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_BLEND);

		if (active) {
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		} else {
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}

		if (isDark) {
			UtilsFX.bindTexture(new ResourceLocation("magistics", "textures/blocks/dark_infuser_symbol.png"));
		} else {
			UtilsFX.bindTexture(new ResourceLocation("magistics", "textures/blocks/infuser_symbol.png"));
		}

		GL11.glColor4f(1F, 1F, 1F, 1F);
		t.startDrawingQuads();
		t.setBrightness(220);

		if (active) {
			t.setColorRGBA_F(1F, 0.5F, 1F, 1F);
		} else {
			t.setColorRGBA_F(0F, 0F, 0F, 1F);
		}

		t.addVertexWithUV(0D, 0D, 0.8999999761581421D, 0D, 1D);
		t.addVertexWithUV(0.8999999761581421D, 0D, 0.8999999761581421D, 1D, 1D);
		t.addVertexWithUV(0.8999999761581421D, 0D, 0D, 1D, 0D);
		t.addVertexWithUV(0D, 0D, 0D, 0D, 0D);
		t.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	public void renderInfuserAt(TileInfuser infuser, double x, double y, double z) {
		float mod = 0.9475F;

		drawDisk(x, y + (double) mod, z, (float) infuser.angle, infuser.sucked > 0F, infuser.getBlockMetadata() > 0);

		if (infuser.sucked > 0F && infuser.getWorldObj().rand.nextFloat() < infuser.sucked) {
			float modX = (float) infuser.xCoord + 0.5F - (infuser.getWorldObj().rand.nextFloat() - infuser.getWorldObj().rand.nextFloat()) * 0.35F;
			float modY = (float) infuser.yCoord + mod;
			float modZ = (float) infuser.zCoord + 0.5F - (infuser.getWorldObj().rand.nextFloat() - infuser.getWorldObj().rand.nextFloat()) * 0.35F;
			FXWisp fx = new FXWisp(infuser.getWorldObj(), (double) modX, (double) modY, (double) modZ, (double) modX, (double) (modY + infuser.getWorldObj().rand.nextFloat()), (double) modZ, 0.1F, infuser.getBlockMetadata() > 0 ? 5 : infuser.getWorldObj().rand.nextInt(5));
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		if (tile instanceof TileInfuser) {
			TileInfuser infuser = (TileInfuser) tile;

			renderInfuserAt(infuser, x, y, z);
		}
	}
}