package T145.magistics.client.render.tiles;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import T145.magistics.tiles.TileInfuser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderInfuser extends TileEntitySpecialRenderer {
	public static final TileEntitySpecialRenderer INSTANCE = new RenderInfuser();

	private void drawDisk(TileInfuser infuser, double x, double y, double z, boolean isDark) {
		Tessellator t = Tessellator.instance;

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
		GL11.glPushMatrix();

		switch (infuser.getFacing()) {
		case 2:
			GL11.glRotatef(180, 0F, 1F, 0F);
			break;
		case 4:
			GL11.glRotatef(-90, 0F, 1F, 0F);
			break;
		case 5:
			GL11.glRotatef(90, 0F, 1F, 0F);
			break;
		}

		GL11.glTranslatef(-0.45F, 0F, -0.45F);
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_BLEND);

		if (infuser.isCrafting()) {
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

		if (infuser.isCrafting()) {
			t.setColorRGBA_F(1F, 0.5F, 1F, 1F);
		} else {
			t.setColorRGBA_F(0F, 0F, 0F, 1F);
		}

		t.addVertexWithUV(0D, 0D, 0.9D, 0D, 1D);
		t.addVertexWithUV(0.9D, 0D, 0.9D, 1D, 1D);
		t.addVertexWithUV(0.9D, 0D, 0D, 1D, 0D);
		t.addVertexWithUV(0D, 0D, 0D, 0D, 0D);
		t.draw();

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	public void renderInfuserAt(TileInfuser infuser, double x, double y, double z) {
		float offsetY = 0.9475F;
		drawDisk(infuser, x, y + offsetY, z, infuser.getBlockMetadata() == 1);

		if (infuser.isCrafting() && infuser.getWorldObj().rand.nextFloat() < infuser.infuserCookTime) {
			float xx = infuser.xCoord + 0.5F - (infuser.getWorldObj().rand.nextFloat() - infuser.getWorldObj().rand.nextFloat()) * 0.35F;
			float yy = infuser.yCoord + offsetY;
			float zz = infuser.zCoord + 0.5F - (infuser.getWorldObj().rand.nextFloat() - infuser.getWorldObj().rand.nextFloat()) * 0.35F;
			Thaumcraft.proxy.wispFX3(infuser.getWorldObj(), xx, yy, zz, xx, yy + infuser.getWorldObj().rand.nextFloat(), zz, 0.1F, infuser.getBlockMetadata() == 2 ? 5 : infuser.getWorldObj().rand.nextInt(5), false, 0);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		renderInfuserAt((TileInfuser) tile, x, y, z);
	}
}