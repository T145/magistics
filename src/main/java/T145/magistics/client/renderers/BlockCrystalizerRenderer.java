package T145.magistics.client.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.common.blocks.BlockCrystalizer;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockCrystalizerRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	public boolean renderCrystalizer(RenderBlocks renderer, int x, int y, int z, BlockCrystalizer crystalizer, boolean inv) {
		if (crystalizer.getRenderBlockPass() == 0 || inv) {
			crystalizer.setBlockBounds(0F, 0F, 0F, 1F, 0.5F + W2, 1F);
			renderer.setRenderBoundsFromBlock(crystalizer);

			IIcon icon[] = crystalizer.icon, in = crystalizer.inside;

			if (inv) {
				drawFaces(renderer, crystalizer, icon[0], icon[1], icon[2], icon[2], icon[2], icon[2], true);
			} else {
				renderer.renderStandardBlock(crystalizer, x, y, z);
			}

			if (!inv)
			{
				float var9 = 0.1875F;
				//crystalizer.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F + W2, 1.0F);
				/*renderer.renderFaceZNeg(crystalizer, (double)((float)(x - 1) + var9), (double)y, (double)z, in);
				renderer.renderFaceZPos(crystalizer, (double)((float)(x + 1) - var9), (double)y, (double)z, in);
				renderer.renderFaceXPos(crystalizer, (double)x, (double)y, (double)((float)(z - 1) + var9), in);
				renderer.renderFaceXNeg(crystalizer, (double)x, (double)y, (double)((float)(z + 1) - var9), in);*/
				renderer.renderFaceYPos(crystalizer, (double)x, (double)((float)y - 0.49F), (double)z, in);
			}
		}

		renderer.clearOverrideBlockTexture();
		crystalizer.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
		renderCrystalizer(renderer, 0, 0, 0, (BlockCrystalizer) block, true);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return renderCrystalizer(renderer, x, y, z, (BlockCrystalizer) block, false);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockCrystalizer.renderID;
	}
}