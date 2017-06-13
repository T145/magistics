package T145.magistics.client.render.blocks;

import T145.magistics.Magistics;
import T145.magistics.client.lib.Render;
import T145.magistics.client.lib.Shaders;
import T145.magistics.tiles.devices.TileChestVoid;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChestVoid extends TileEntitySpecialRenderer<TileChestVoid> {

	private static final ModelChest MODEL = new ModelChest();

	@Override
	public void renderTileEntityAt(TileChestVoid chest, double x, double y, double z, float partialTicks, int destroyStage) {
		renderChest(chest.getFacing(), chest.lidAngle, chest.prevLidAngle, x, y, z, partialTicks, destroyStage);
	}

	public static void renderChest(EnumFacing facing, float lidAngle, float prevLidAngle, double x, double y, double z, float partialTicks, int destroyStage) {
		if (destroyStage >= 0) {
			Render.bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 4.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		} else {
			Render.bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/void_chest.png"));
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate(x, y + 1.0F, z + 1.0F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		Render.rotate(facing);
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		float f = prevLidAngle + (lidAngle - prevLidAngle) * partialTicks;
		f = 1.0F - f;
		f = 1.0F - f * f * f;
		MODEL.chestLid.rotateAngleX = -(f * ((float) Math.PI / 2F));
		MODEL.renderAll();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		if (destroyStage >= 0) {
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		Render.bindTexture(new ResourceLocation("textures/entity/end_portal.png"));
		Shaders.useShader(Shaders.endShader, Shaders.shaderCallback);
		double mod = 0.0001D; // just here to avoid texture collision
		Render.cube(Render.W1 + mod, Render.W1, Render.W1 + mod, 1F - Render.W1 - mod, Render.W10 - mod, 1F -  Render.W1 - mod);
		Shaders.releaseShader();
		GlStateManager.popMatrix();
	}
}