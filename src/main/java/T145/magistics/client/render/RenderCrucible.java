package T145.magistics.client.render;

import javax.annotation.Nonnull;

import T145.magistics.client.lib.Render;
import T145.magistics.client.lib.SpriteAtlas;
import T145.magistics.common.tiles.TileCrucible;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrucible extends TileEntitySpecialRenderer<TileCrucible> {

	@Override
	public void render(@Nonnull TileCrucible crucible, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if (/*!crucible.getWorld().getBlockState(crucible.getPos().up()).isOpaqueCube() && */crucible.hasQuints()) {
			float amount = Math.min(crucible.getQuints(), crucible.getCapacity());
			float level = 0.735F * (amount / crucible.getCapacity());

			if (crucible.isOverflowing()) {
				level = (float) (level + 0.26 / 16D);
			}

			double minOffset = 0.015D / 16D;
			double maxOffset = 1 - minOffset;

			GlStateManager.pushMatrix();
			GlStateManager.translate(x, y, z);
			GlStateManager.disableLighting();

			bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Render.face(EnumFacing.UP, SpriteAtlas.quintSprite, minOffset, 4D / 16D, minOffset, maxOffset, 4D / 16D + level, maxOffset);

			if (crucible.isOverflowing()) {
				double off1 = -0.001D / 16D;
				double off2 = 1 - off1;
				AxisAlignedBB box = new AxisAlignedBB(off1, 0, off1, off2, 1 + 0.05 / 16D, off2);

				Render.face(EnumFacing.WEST, SpriteAtlas.overflowSprite, box);
				Render.face(EnumFacing.EAST, SpriteAtlas.overflowSprite, box);
				Render.face(EnumFacing.NORTH, SpriteAtlas.overflowSprite, box);
				Render.face(EnumFacing.SOUTH, SpriteAtlas.overflowSprite, box);
			}

			GlStateManager.enableLighting();
			GlStateManager.color(1F, 1F, 1F, 1F);
			GlStateManager.popMatrix();
		}
	}
}