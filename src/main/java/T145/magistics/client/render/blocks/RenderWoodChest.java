package T145.magistics.client.render.blocks;

import T145.magistics.Magistics;
import T145.magistics.tiles.TileWoodChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderWoodChest extends TileEntitySpecialRenderer<TileWoodChest> {

	private static final ResourceLocation[] TEXTURES = {
			new ResourceLocation(Magistics.MODID, "textures/models/chest_greatwood.png"),
			new ResourceLocation(Magistics.MODID, "textures/models/chest_silverwood.png"),
	};
	private final ModelChest model = new ModelChest();

	@Override
	public void renderTileEntityAt(TileWoodChest chest, double x, double y, double z, float partialTicks, int destroyStage) {
		if (!chest.hasWorldObj()) {
			return;
		}

		if (destroyStage >= 0) {
			bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4F, 4F, 1F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		} else {
			bindTexture(TEXTURES[chest.getBlockMetadata()]);
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.translate(x, y + 1F, z + 1F);
		GlStateManager.scale(1F, -1F, -1F);
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		GlStateManager.rotate(chest.getFrontAngle(true, false), 0F, 1F, 0F);
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		float f = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * partialTicks;
		f = 1F - f;
		f = 1F - f * f * f;
		model.chestLid.rotateAngleX = -(f * ((float) Math.PI / 2F));
		model.renderAll();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		GlStateManager.color(1F, 1F, 1F, 1F);

		if (destroyStage >= 0) {
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}
}