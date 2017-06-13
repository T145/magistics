package T145.magistics.client.render.blocks;

import T145.magistics.client.lib.Render;
import T145.magistics.client.lib.Shaders;
import T145.magistics.tiles.cosmetic.TileVoidBorder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVoidBorder extends TileEntitySpecialRenderer<TileVoidBorder> {

	@Override
	public void renderTileEntityAt(TileVoidBorder te, double x, double y, double z, float partialTicks, int destroyStage) {
		renderBlock(x, y, z);
	}

	public static void renderBlock(double x, double y, double z) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		Render.bindTexture(new ResourceLocation("textures/entity/end_portal.png"));
		Shaders.useShader(Shaders.endShader, Shaders.shaderCallback);
		Render.cube();
		Shaders.releaseShader();
		GlStateManager.popMatrix();
	}
}