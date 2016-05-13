package T145.magistics.client.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.config.ConfigBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderBlockLootUrn extends BlockRenderer implements ISimpleBlockRenderingHandler {
	private int id;

	public RenderBlockLootUrn(int renderID) {
		id = renderID;
	}

	private boolean renderBlock(Block block, int x, int y, int z, RenderBlocks renderer, boolean inWorld) {
		block.setBlockBounds(W3, 0F, W3, W13, W1, W13);
		renderer.setRenderBoundsFromBlock(block);

		if (inWorld) {
			renderer.renderStandardBlock(block, x, y, z);
		} else {
			drawFaces(renderer, block, ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(0, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), false);
		}

		block.setBlockBounds(W2, W1, W2, W14, W13, W14);
		renderer.setRenderBoundsFromBlock(block);

		if (inWorld) {
			renderer.renderStandardBlock(block, x, y, z);
		} else {
			drawFaces(renderer, block, ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(0, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), false);
		}

		block.setBlockBounds(W4, W13, W4, W12, 1F, W12);
		renderer.setRenderBoundsFromBlock(block);

		if (inWorld) {
			renderer.renderStandardBlock(block, x, y, z);
		} else {
			drawFaces(renderer, block, ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(0, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), false);
		}

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);

		if (inWorld) {
			renderer.renderStandardBlock(block, x, y, z);
		} else {
			drawFaces(renderer, block, ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(0, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), ConfigBlocks.blockLootUrn.getIcon(2, 0), false);
		}

		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		renderBlock(block, 0, 0, 0, renderer, false);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return renderBlock(block, x, y, z, renderer, true);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return id;
	}
}