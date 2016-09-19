package T145.magistics.client.render.blocks;

import T145.magistics.blocks.BlockCrystalizer;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;

@SideOnly(Side.CLIENT)
public class RenderBlockCrystalizer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	public static final ISimpleBlockRenderingHandler INSTANCE = new RenderBlockCrystalizer();

	private void drawFaces(Block block, int metadata, int x, int y, int z, RenderBlocks renderer, boolean inWorld) {
		if (inWorld) {
			renderer.renderStandardBlock(block, x, y, z);
		} else {
			drawFaces(renderer, block, block.getIcon(0, metadata), block.getIcon(1, metadata), block.getIcon(2, metadata), block.getIcon(2, metadata), block.getIcon(2, metadata), block.getIcon(2, metadata), false);
		}
	}

	private boolean renderBlock(Block block, int metadata, int x, int y, int z, RenderBlocks renderer, boolean inWorld) {
		if (!inWorld) {
			block.setBlockBounds(0F, 0F, 0F, 1F, 0.5F + W2, 1F);
			renderer.setRenderBoundsFromBlock(block);
			drawFaces(renderer, block, block.getIcon(0, metadata), block.getIcon(-1, metadata), block.getIcon(2, metadata), block.getIcon(2, metadata), block.getIcon(2, metadata), block.getIcon(2, metadata), true);
		} else {
			block.setBlockBounds(0F, 0F, 0F, 1F, 0.5F + W2, 1F);
			renderer.setRenderBoundsFromBlock(block);
			drawFaces(block, metadata, x, y, z, renderer, inWorld);
			block.setBlockBounds(0F, 0F, 0F, 1F, 0.5F + W2, 1F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderFaceXPos(block, (x - 1) + W3, y, z, block.getIcon(-2, metadata));
			renderer.renderFaceXNeg(block, (x + 1) - W3, y, z, block.getIcon(-2, metadata));
			renderer.renderFaceZPos(block, x, y, (z - 1) + W3, block.getIcon(-2, metadata));
			renderer.renderFaceZNeg(block, x, y, (z + 1) - W3, block.getIcon(-2, metadata));
			renderer.renderFaceYPos(block, x, y - 0.49F, z, block.getIcon(-2, metadata));
			renderer.renderStandardBlock(block, x, y, z);
		}

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		renderBlock(block, metadata, 0, 0, 0, renderer, false);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return renderBlock(block, world.getBlockMetadata(x, y, z), x, y, z, renderer, true);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockCrystalizer.INSTANCE.getRenderType();
	}
}