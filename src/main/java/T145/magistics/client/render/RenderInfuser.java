package T145.magistics.client.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import T145.magistics.tiles.TileInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderInfuser extends TileEntitySpecialRenderer<TileInfuser> {

	@Override
	public void renderTileEntityAt(@Nonnull TileInfuser infuser, double x, double y, double z, float partialTicks, int destroyStage) {
		drawDisk(infuser, x, y + BlockRenderer.W16, z);
	}

	private void drawDisk(TileInfuser infuser, double x, double y, double z) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5D, y, z + 0.5D);
		GlStateManager.pushMatrix();

		if (infuser.isCrafting()) {
			GlStateManager.rotate(infuser.getDiskAngle(), 0F, 1F, 0F);
		} else {
			GlStateManager.rotate(getDefaultAngle(infuser.getFacing()), 0F, 1F, 0F);
		}

		GlStateManager.translate(-0.45D, 0D, -0.45D);
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();

		if (infuser.isCrafting()) {
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		} else {
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}

		if (infuser.isDark()) {
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("magistics", "textures/blocks/dark_infuser_symbol.png"));
		} else {
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("magistics", "textures/blocks/infuser_symbol.png"));
		}

		GlStateManager.color(1F, 1F, 1F, 1F);

		Tessellator tessellator = Tessellator.getInstance();
		tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

		if (infuser.isCrafting()) {
			GlStateManager.color(1F, 0.5F, 1F, 1F);
		} else {
			GlStateManager.color(0F, 0F, 0F, 1F);
		}

		tessellator.getBuffer().pos(0D, 0D, 0.9D).tex(0D, 1D).endVertex();
		tessellator.getBuffer().pos(0.9D, 0D, 0.9D).tex(1D, 1D).endVertex();
		tessellator.getBuffer().pos(0.9D, 0D, 0D).tex(1D, 0D).endVertex();
		tessellator.getBuffer().pos(0D, 0D, 0D).tex(0D, 0D).endVertex();
		tessellator.draw();

		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
		GlStateManager.popMatrix();
		GlStateManager.popMatrix();
	}

	private float getDefaultAngle(int facing) {
		switch (facing) {
		case 2:
			return 180F;
		case 4:
			return -90F;
		case 5:
			return 90F;
		default:
			return 0F;
		}
	}
}