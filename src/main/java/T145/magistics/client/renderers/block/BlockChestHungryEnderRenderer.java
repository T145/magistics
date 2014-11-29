package T145.magistics.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockChestHungryEnderRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity) new TileChestHungryEnder(), 0.0, 0.0, 0.0, 0.0F);
		GL11.glEnable(32826);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockChestHungryEnder.renderID;
	}
}