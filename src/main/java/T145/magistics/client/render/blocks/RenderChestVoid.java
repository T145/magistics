package T145.magistics.client.render.blocks;

import T145.magistics.Magistics;
import T145.magistics.client.lib.Render;
import T145.magistics.client.lib.Shaders;
import T145.magistics.tiles.devices.TileChestVoid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChestVoid extends TileEntitySpecialRenderer<TileChestVoid> {

	@Override
	public void renderTileEntityAt(TileChestVoid chest, double x, double y, double z, float partialTicks, int destroyStage) {
		renderChest(chest.getFacing(), chest.lidAngle, chest.prevLidAngle, x, y, z, partialTicks, destroyStage);
	}

	public static void renderChest(EnumFacing facing, float lidAngle, float prevLidAngle, double x, double y, double z, float partialTicks, int destroyStage) {
		Render.chest(new ResourceLocation(Magistics.MODID, "textures/models/void_chest.png"), facing, lidAngle, prevLidAngle, x, y, z, partialTicks, destroyStage);

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