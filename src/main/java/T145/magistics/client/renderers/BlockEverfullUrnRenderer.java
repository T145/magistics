package T145.magistics.client.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.common.blocks.BlockEverfullUrn;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockEverfullUrnRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	public boolean renderEverfullUrn(RenderBlocks renderer, int x, int y, int z, Block block, boolean inv) {
		if (block.getRenderBlockPass() == 0 || inv) {
			block.setBlockBounds(W2, 0.0F, W2, 1.0F - W2, 0.5F + W1, 1.0F - W2);
			renderer.setRenderBoundsFromBlock(block);

			IIcon icon[] = ((BlockEverfullUrn) block).icon;

			if (inv) {
				drawFaces(renderer, block, icon[0], icon[1], icon[2], icon[2], icon[2], icon[2], true);
			} else {
				renderer.renderStandardBlock(block, x, y, z);
			}

			block.setBlockBounds(W5, 0.5F + W1, W5, 1.0F - W5, 1.0F - W3, 1.0F - W5);
			renderer.setRenderBoundsFromBlock(block);

			if (inv) {
				drawFaces(renderer, block, icon[0], icon[1], icon[2], icon[2], icon[2], icon[2], true);
			} else {
				renderer.renderStandardBlock(block, x, y, z);
			}

			block.setBlockBounds(W4, 1.0F - W3, W4, 1.0F - W4, 1.0F, 1.0F - W4);
			renderer.setRenderBoundsFromBlock(block);

			if (inv) {
				drawFaces(renderer, block, icon[0], icon[1], icon[2], icon[2], icon[2], icon[2], true);
			} else {
				renderer.renderStandardBlock(block, x, y, z);
			}
		}

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
		renderEverfullUrn(renderer, 0, 0, 0, block, true);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return renderEverfullUrn(renderer, x, y, z, block, false);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockEverfullUrn.renderID;
	}
}