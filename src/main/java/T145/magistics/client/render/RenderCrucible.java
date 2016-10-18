package T145.magistics.client.render;

import javax.annotation.Nonnull;

import T145.magistics.client.lib.events.IconAtlas;
import T145.magistics.tiles.TileCrucible;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrucible extends TileEntitySpecialRenderer<TileCrucible> {

	@Override
	public void renderTileEntityAt(@Nonnull TileCrucible crucible, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);

		float totalVis = crucible.pureVis + crucible.taintedVis;
		if (totalVis > 0.1F) {
			renderVis(crucible, totalVis);
		}

		GlStateManager.popMatrix();
	}

	private void renderVis(TileCrucible crucible, float totalVis) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.5, getLevel(crucible, totalVis), 0.5);

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();

		float size = 0.8F;
		TextureAtlasSprite icon = IconAtlas.INSTANCE.spriteVis;

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.color(1F, 1F, 1F, 1F);

		double uMin = (double) icon.getMinU();
		double uMax = (double) icon.getMaxU();
		double vMin = (double) icon.getMinV();
		double vMax = (double) icon.getMaxV();

		float tint = Math.min(1F, crucible.pureVis / totalVis);
		int brightness = 20 + (int) (tint * 210F);

		buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(size / 2F, 0, size / 2F).tex(uMax, vMax).endVertex();
		buffer.pos(size / 2F, 0, -size / 2F).tex(uMax, vMin).endVertex();
		buffer.pos(-size / 2F, 0, -size / 2F).tex(uMin, vMin).endVertex();
		buffer.pos(-size / 2F, 0, size / 2F).tex(uMin, vMax).endVertex();
		tessellator.draw();

		GlStateManager.popMatrix();
	}

	private double getLevel(TileCrucible crucible, float totalVis) {
		float height = Math.min(totalVis, crucible.getMaxVis());
		float level = 0.75F * (height / crucible.getMaxVis());

		if (crucible.getMaxVis() == totalVis) {
			level -= 0.001F;
		}

		return 0.25F + level;
	}
}