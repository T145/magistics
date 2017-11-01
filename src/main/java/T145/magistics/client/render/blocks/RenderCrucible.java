package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import T145.magistics.client.lib.Render;
import T145.magistics.client.lib.SpriteAtlas;
import T145.magistics.tiles.crafting.TileCrucible;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrucible extends TileEntitySpecialRenderer<TileCrucible> {

	@Override
	public void renderTileEntityAt(@Nonnull TileCrucible crucible, double x, double y, double z, float partialTicks, int destroyStage) {
		if (crucible.isNormal() && crucible.getDisplayQuints() > 0F) {
			float level = 0.75F * (crucible.getDisplayQuints() / crucible.getMaxQuints());

			if (crucible.isFull() || crucible.isOverflowing()) {
				level -= 0.001D;
			}

			GlStateManager.pushMatrix();
			GlStateManager.translate(x, y, z);
			GlStateManager.disableLighting();

			bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Render.face(EnumFacing.UP, SpriteAtlas.quintSprite, Render.W1 + 0.001D, Render.W4, Render.W1 + 0.001D, 0.999D - Render.W1, Render.W4 + level, 0.999D - Render.W1);

			GlStateManager.enableLighting();
			GlStateManager.color(1F, 1F, 1F, 1F);
			GlStateManager.popMatrix();
		}
	}
}