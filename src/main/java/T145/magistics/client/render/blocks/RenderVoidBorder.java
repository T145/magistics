package T145.magistics.client.render.blocks;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;

import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.client.lib.ShaderCallback;
import T145.magistics.client.lib.Shaders;
import T145.magistics.tiles.devices.TileVoidBorder;
import net.minecraft.client.Minecraft;
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
		GL11.glPushMatrix();
		bindTexture(new ResourceLocation("textures/entity/end_portal.png"));
		Shaders.useShader(Shaders.endShader, shaderCallback);
		GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);

		for (EnumFacing face : EnumFacing.values()) {
			GL11.glPushMatrix();
			GL11.glRotatef(90.0F, -face.getFrontOffsetY(), face.getFrontOffsetX(), -face.getFrontOffsetZ());

			if (face.getFrontOffsetZ() < 0) {
				GL11.glTranslated(0.0D, 0.0D, -0.5D);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			} else {
				GL11.glTranslated(0.0D, 0.0D, 0.5D);
			}

			GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
			BlockRenderer.renderQuadCentered();
			GL11.glPopMatrix();
		}

		Shaders.releaseShader();
		GL11.glPopMatrix();
	}
}