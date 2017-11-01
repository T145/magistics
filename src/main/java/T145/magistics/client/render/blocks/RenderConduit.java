package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import T145.magistics.client.lib.Render;
import T145.magistics.client.lib.SpriteAtlas;
import T145.magistics.tiles.storage.TileConduit;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderConduit extends TileEntitySpecialRenderer<TileConduit> {

	@Override
	public void renderTileEntityAt(@Nonnull TileConduit conduit, double x, double y, double z, float partialTicks, int destroyStage) {
		TextureAtlasSprite fluidSprite = SpriteAtlas.quintSprite;
		float mod = 0.38125F;
		float amount = Math.min(conduit.getDisplayQuints(), conduit.getMaxQuints());
		float level = (1F - mod * 2F) * (amount / conduit.getMaxQuints());

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.disableCull();
		GlStateManager.disableLighting();

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		if (conduit.getDisplayQuints() > 0F) {
			Render.cube(fluidSprite, mod, mod, mod, 1F - mod, mod + level, 1F - mod);
		}

		for (EnumFacing facing : EnumFacing.VALUES) {
			if (conduit.isConnected(facing) && conduit.getDisplayQuints() > 0F) {
				switch (facing) {
				case UP:
					Render.cube(fluidSprite, 0.5F - level / 2F, mod + level, 0.5F - level / 2F, 0.5F + level / 2F, 1F, 0.5F + level / 2F);
					break;
				case DOWN:
					Render.cube(fluidSprite, 0.5F - level / 2F, 0F, 0.5F - level / 2F, 0.5F + level / 2F, mod, 0.5F + level / 2F);
					break;
				case SOUTH:
					Render.cube(fluidSprite, 0.5F - level / 2F, 0F, 0.5F - level / 2F, 0.5F + level / 2F, mod, 0.5F + level / 2F);
					break;
				case NORTH:
					Render.cube(fluidSprite, mod, mod, 0F, 1F - mod, mod + level, mod);
					break;
				case EAST:
					Render.cube(fluidSprite, 1F - mod, mod, mod, 1F, mod + level, 1F - mod);
					break;
				case WEST:
					Render.cube(fluidSprite, 0F, mod, mod, mod, mod + level, 1F - mod);
					break;
				}
			}
		}

		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.popMatrix();
	}
}