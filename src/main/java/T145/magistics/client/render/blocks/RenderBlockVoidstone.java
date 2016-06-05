package T145.magistics.client.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import T145.magistics.blocks.BlockVoidstone;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderBlockVoidstone implements ISimpleBlockRenderingHandler {
	public static final ISimpleBlockRenderingHandler INSTANCE = new RenderBlockVoidstone();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return BlockVoidstone.INSTANCE.getRenderType();
	}
}