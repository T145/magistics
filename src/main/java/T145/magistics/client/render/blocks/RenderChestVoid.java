package T145.magistics.client.render.blocks;

import org.lwjgl.opengl.ARBShaderObjects;

import T145.magistics.Magistics;
import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.client.lib.ShaderCallback;
import T145.magistics.client.lib.Shaders;
import T145.magistics.tiles.devices.TileChestVoid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChestVoid extends TileEntitySpecialRenderer<TileChestVoid> {

	private final ModelChest modelChest = new ModelChest();
	private final ShaderCallback shaderCallback = new ShaderCallback() {
		public void call(int shader) {
			Minecraft mc = Minecraft.getMinecraft();
			int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
			ARBShaderObjects.glUniform1fARB(x, (float) (mc.player.rotationYaw * 2.0F * Math.PI / 360.0D));
			int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
			ARBShaderObjects.glUniform1fARB(z, -(float) (mc.player.rotationPitch * 2.0F * Math.PI / 360.0D));
		}
	};

	@Override
	public void renderTileEntityAt(TileChestVoid te, double x, double y, double z, float partialTicks, int destroyStage) {
		if (destroyStage >= 0) {
			bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 4.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		} else {
			bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/void_chest.png"));
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate(x, y + 1.0F, z + 1.0F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		BlockRenderer.rotate(te.getFacing(te.getState()));
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
		f = 1.0F - f;
		f = 1.0F - f * f * f;
		modelChest.chestLid.rotateAngleX = -(f * ((float) Math.PI / 2F));
		modelChest.renderAll();
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
		bindTexture(new ResourceLocation("textures/entity/end_portal.png"));
		Shaders.useShader(Shaders.endShader, shaderCallback);
		double mod = 0.0001D; // just here to avoid texture collision
		BlockRenderer.renderCube(BlockRenderer.W1 + mod, BlockRenderer.W1, BlockRenderer.W1 + mod, 1F - BlockRenderer.W1 - mod, BlockRenderer.W10 - mod, 1F -  BlockRenderer.W1 - mod);
		Shaders.releaseShader();
		GlStateManager.popMatrix();
	}
}