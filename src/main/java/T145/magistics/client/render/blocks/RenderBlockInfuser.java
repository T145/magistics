package T145.magistics.client.render.blocks;

import T145.magistics.blocks.BlockInfuser;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;

@SideOnly(Side.CLIENT)
public class RenderBlockInfuser extends BlockRenderer implements ISimpleBlockRenderingHandler {
	public static final ISimpleBlockRenderingHandler INSTANCE = new RenderBlockInfuser();

	private void drawFaces(Block block, int metadata, int x, int y, int z, RenderBlocks renderer, boolean inWorld) {
		if (inWorld) {
			renderer.renderStandardBlock(block, x, y, z);
		} else {
			drawFaces(renderer, block, block.getIcon(0, metadata), block.getIcon(1, metadata), block.getIcon(2, metadata), block.getIcon(2, metadata), block.getIcon(2, metadata), block.getIcon(2, metadata), false);
		}
	}

	private boolean renderBlock(Block block, int metadata, int x, int y, int z, RenderBlocks renderer, boolean inWorld) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F - W1, 1F);
		renderer.setRenderBoundsFromBlock(block);
		drawFaces(BlockInfuser.INSTANCE, metadata, x, y, z, renderer, inWorld);
		block.setBlockBounds(0F, 1F - W1, 0F, W3, 1F, W3);
		renderer.setRenderBoundsFromBlock(block);
		drawFaces(BlockInfuser.INSTANCE, metadata, x, y, z, renderer, inWorld);
		block.setBlockBounds(0F, 1F - W1, 1F - W3, W3, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		drawFaces(BlockInfuser.INSTANCE, metadata, x, y, z, renderer, inWorld);
		block.setBlockBounds(1F - W3, 1F - W1, 0F, 1F, 1F, W3);
		renderer.setRenderBoundsFromBlock(block);
		drawFaces(BlockInfuser.INSTANCE, metadata, x, y, z, renderer, inWorld);
		block.setBlockBounds(1F - W3, 1F - W1, 1F - W3, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		drawFaces(BlockInfuser.INSTANCE, metadata, x, y, z, renderer, inWorld);
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
		return BlockInfuser.INSTANCE.getRenderType();
	}
}