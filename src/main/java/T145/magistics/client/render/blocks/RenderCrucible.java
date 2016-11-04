package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

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

		float totalVis = crucible.vis + crucible.miasma;
		if (totalVis > 0F) {
			renderVis(crucible, totalVis);
		}

		GlStateManager.popMatrix();
	}

	private void renderVis(TileCrucible crucible, float totalVis) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.5, getVisLevel(crucible, totalVis), 0.5);

		TextureAtlasSprite sprite = IconAtlas.INSTANCE.spriteVis;
		double imageSize = 0.4D;
		double uMin = sprite.getMinU();
		double uMax = sprite.getMaxU();
		double vMin = sprite.getMinV();
		double vMax = sprite.getMaxV();

		float tint = Math.min(1F, crucible.vis / totalVis);
		int lightValue = 20 + (int) (tint * 210F);
		int brightness = getWorld().getCombinedLight(crucible.getPos(), lightValue);
		int lightx = brightness >> 0x10 & 0xFFFF;
		int lighty = brightness & 0xFFFF;

		GlStateManager.disableCull();
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(imageSize, 0D, imageSize).tex(uMax, vMax).endVertex();
		buffer.pos(imageSize, 0D, -imageSize).tex(uMax, vMin).endVertex();
		buffer.pos(-imageSize, 0D, -imageSize).tex(uMin, vMin).endVertex();
		buffer.pos(-imageSize, 0D, imageSize).tex(uMin, vMax).endVertex();
		tessellator.draw();

		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.enableCull();

		GlStateManager.popMatrix();
	}

	private double getVisLevel(TileCrucible crucible, float totalVis) {
		float height = Math.min(totalVis, crucible.getMaxVis());
		float level = 0.75F * (height / crucible.getMaxVis());

		if (crucible.getMaxVis() == totalVis) {
			level -= 0.001F;
		}

		return 0.25F + level;
	}
}