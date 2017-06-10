package T145.magistics.client.render.blocks;

import org.lwjgl.opengl.ARBShaderObjects;

import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.client.lib.ShaderCallback;
import T145.magistics.client.lib.Shaders;
import T145.magistics.tiles.devices.TileVoidBorder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVoidBorder extends TileEntitySpecialRenderer<TileVoidBorder> {

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
	public void renderTileEntityAt(TileVoidBorder te, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();

		bindTexture(new ResourceLocation("textures/entity/end_portal.png"));
		Shaders.useShader(Shaders.endShader, shaderCallback);
		GlStateManager.translate(x + 0.5D, y + 0.5D, z + 0.5D);

		for (EnumFacing face : EnumFacing.VALUES) {
			if (te.getBlockType().doesSideBlockRendering(te.getState(), te.getWorld(), te.getPos(), face)) {
				GlStateManager.pushMatrix();
				GlStateManager.rotate(90.0F, -face.getFrontOffsetY(), face.getFrontOffsetX(), -face.getFrontOffsetZ());

				if (face.getFrontOffsetZ() < 0) {
					GlStateManager.translate(0.0D, 0.0D, -0.5D);
					GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
				} else {
					GlStateManager.translate(0.0D, 0.0D, 0.5D);
				}

				GlStateManager.rotate(90.0F, 0.0F, 0.0F, -1.0F);
				BlockRenderer.renderQuadCentered();
				GlStateManager.popMatrix();
			}
		}

		Shaders.releaseShader();
		GlStateManager.popMatrix();
	}
}