package T145.magistics.client.render.blocks;

import T145.magistics.blocks.BlockEverfullUrn;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;

@SideOnly(Side.CLIENT)
public class RenderBlockEverfullUrn extends BlockRenderer implements ISimpleBlockRenderingHandler {
	public static final ISimpleBlockRenderingHandler INSTANCE = new RenderBlockEverfullUrn();

	private void drawFaces(RenderBlocks renderer, int x, int y, int z, BlockEverfullUrn urn, boolean inWorld) {
		IIcon bottom = urn.getIcon(0, 0);
		IIcon top = urn.getIcon(1, 0);
		IIcon side = urn.getIcon(2, 0);

		if (inWorld) {
			renderer.renderStandardBlock(urn, x, y, z);
		} else {
			drawFaces(renderer, urn, bottom, top, side, side, side, side, true);
		}
	}

	public boolean renderEverfullUrn(RenderBlocks renderer, int x, int y, int z, Block block, boolean inWorld) {
		if (block instanceof BlockEverfullUrn) {
			BlockEverfullUrn urn = (BlockEverfullUrn) block;

			block.setBlockBounds(W2, 0F, W2, 1F - W2, 0.5F + W1, 1F - W2);
			renderer.setRenderBoundsFromBlock(block);
			drawFaces(renderer, x, y, z, urn, inWorld);
			block.setBlockBounds(W5, 0.5F + W1, W5, 1F - W5, 1F - W3, 1F - W5);
			renderer.setRenderBoundsFromBlock(block);
			drawFaces(renderer, x, y, z, urn, inWorld);
			block.setBlockBounds(W4, 1F - W3, W4, 1F - W4, 1F, 1F - W4);
			renderer.setRenderBoundsFromBlock(block);
			drawFaces(renderer, x, y, z, urn, inWorld);
			renderer.clearOverrideBlockTexture();
			block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		}

		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
		renderEverfullUrn(renderer, 0, 0, 0, block, false);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return renderEverfullUrn(renderer, x, y, z, block, true);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockEverfullUrn.INSTANCE.getRenderType();
	}
}