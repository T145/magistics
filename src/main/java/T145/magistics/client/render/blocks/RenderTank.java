package T145.magistics.client.render.blocks;

import javax.annotation.Nonnull;

import T145.magistics.client.lib.Render;
import T145.magistics.client.lib.SpriteAtlas;
import T145.magistics.core.ModInit;
import T145.magistics.tiles.storage.TileQuintTank;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTank extends TileEntitySpecialRenderer<TileQuintTank> {

	@Override
	public void renderTileEntityAt(@Nonnull TileQuintTank tank, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.disableCull();
		GlStateManager.disableLighting();

		if (tank.getQuints() > 0F) {
			renderLiquid(tank, x, y, z);
		}

		for (EnumFacing facing : EnumFacing.HORIZONTALS) {
			if (tank.isConnected(facing) && !(tank.getWorld().getTileEntity(tank.getPos().offset(facing)) instanceof TileQuintTank)) {
				renderConnection(tank, x, y, z, facing);
			}
		}

		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.popMatrix();
	}

	private void renderConnection(TileQuintTank tank, double x, double y, double z, EnumFacing facing) {
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		TextureAtlasSprite icon = Render.getTextureFromBlock(ModInit.CONDUIT, 0);

		switch (facing) {
		case NORTH:
			Render.cube(icon, 0.5F - Render.W2, 0.5F - Render.W2, 0F, 0.5F + Render.W2, 0.5F + Render.W2, Render.W1);
			break;
		case SOUTH:
			Render.cube(icon, 0.5F - Render.W2, 0.5F - Render.W2, 1F - Render.W1, 0.5F + Render.W2, 0.5F + Render.W2, 1F);
			break;
		case EAST:
			Render.cube(icon, 1F - Render.W1, 0.5F - Render.W2, 0.5F - Render.W2, 1F, 0.5F + Render.W2, 0.5F + Render.W2);
			break;
		case WEST:
			Render.cube(icon, 0F, 0.5F - Render.W2, 0.5F - Render.W2, Render.W1, 0.5F + Render.W2, 0.5F + Render.W2);
			break;
		default:
			break;
		}
	}

	private void renderLiquid(TileQuintTank tank, double x, double y, double z) {
		float mod = 0.003F;
		float level = (1F - mod * 2F) * (tank.getQuints() / tank.getCapacity());

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Render.cube(SpriteAtlas.quintSprite, mod + Render.W1, mod, mod + Render.W1, 1F - mod - Render.W1, mod + level, 1F - mod - Render.W1);
	}
}