package T145.magistics.client.render.blocks;

import org.lwjgl.opengl.GL11;

import T145.magistics.blocks.BlockInfusionWorkbench;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;

public class RenderBlockInfusionWorkbench extends BlockRenderer implements ISimpleBlockRenderingHandler {
	public static final ISimpleBlockRenderingHandler INSTANCE = new RenderBlockInfusionWorkbench();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if (metadata == 0) {
			block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
			renderer.setRenderBoundsFromBlock(block);
			BlockRenderer.drawFaces(renderer, block, block.getBlockTextureFromSide(0), false);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int metadata = world.getBlockMetadata(x, y, z);

		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		if (metadata != 0 && metadata != 7) {
			Tessellator t = Tessellator.instance;
			t.setColorOpaque_I(12583104);
			t.setBrightness(180);
			BlockRenderer.renderAllSidesInverted(world, x, y, z, block, renderer, ((BlockInfusionWorkbench) block).iconGlow, false);
			block.setBlockBounds(0.02F, 0.02F, 0.02F, 0.98F, 0.98F, 0.98F);
			renderer.setRenderBoundsFromBlock(block);
			BlockRenderer.renderAllSides(world, x, y, z, block, renderer, ((BlockInfusionWorkbench) block).iconGlow, false);
			GL11.glColor3f(1F, 1F, 1F);
		}

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockInfusionWorkbench.INSTANCE.getRenderType();
	}
}