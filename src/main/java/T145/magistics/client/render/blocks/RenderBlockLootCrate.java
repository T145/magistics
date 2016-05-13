package T145.magistics.client.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.config.ConfigBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlockLootCrate extends BlockRenderer implements ISimpleBlockRenderingHandler {
	private int id;

	public RenderBlockLootCrate(int renderID) {
		id = renderID;
	}

	private boolean renderBlock(Block block, int x, int y, int z, RenderBlocks renderer, boolean inWorld) {
		block.setBlockBounds(W1, 0F, W1, W15, W14, W15);
		renderer.setRenderBoundsFromBlock(block);

		if (inWorld) {
			renderer.renderStandardBlock(block, x, y, z);
		} else {
			drawFaces(renderer, block, ConfigBlocks.blockLootCrate.getIcon(0, 0), false);
		}

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
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