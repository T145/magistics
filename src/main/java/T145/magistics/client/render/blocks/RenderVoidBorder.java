package T145.magistics.client.render.blocks;

import org.lwjgl.opengl.ARBShaderObjects;

import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.client.lib.ShaderCallback;
import T145.magistics.client.lib.Shaders;
import T145.magistics.tiles.devices.TileVoidBorder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
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
		GlStateManager.translate(x, y, z);
		bindTexture(new ResourceLocation("textures/entity/end_portal.png"));
		Shaders.useShader(Shaders.endShader, shaderCallback);
		BlockRenderer.renderCube();
		Shaders.releaseShader();
		GlStateManager.popMatrix();
	}
}