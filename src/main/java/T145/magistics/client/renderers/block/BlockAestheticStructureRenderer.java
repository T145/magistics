package T145.magistics.client.renderers.block;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemDye;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.common.blocks.BlockAestheticStructure;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockAestheticStructureRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		BlockRenderer.drawFaces(renderer, block, BlockAestheticStructure.icon, false);
		Color c = new Color(ItemDye.field_150922_c[meta]);
		GL11.glColor3f(c.getRed() / 255F, c.getGreen() / 255F, c.getBlue() / 255F);
		block.setBlockBounds(0.005F, 0.005F, 0.005F, 0.995F, 0.995F, 0.995F);
		renderer.setRenderBoundsFromBlock(block);
		BlockRenderer.drawFaces(renderer, block, BlockAestheticStructure.iconGlow, false);
		GL11.glColor3f(1F, 1F, 1F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int bb = BlockRenderer.setBrightness(world, x, y, z, block);
		int meta = world.getBlockMetadata(x, y, z);
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		Tessellator t = Tessellator.instance;
		t.setColorOpaque_I(ItemDye.field_150922_c[meta]);
		bb = 180;
		t.setBrightness(bb);
		if (block.shouldSideBeRendered(world, x, y + 1, z, 6))
			renderer.renderFaceYPos(block, (double) x, (double) (y - 0.01F), (double) z, BlockAestheticStructure.iconGlow);
		if (block.shouldSideBeRendered(world, x + 1, y, z, 6))
			renderer.renderFaceXPos(block, (double) (x - 0.01F), (double) y, (double) z, BlockAestheticStructure.iconGlow);
		if (block.shouldSideBeRendered(world, x - 1, y, z, 6))
			renderer.renderFaceXNeg(block, (double) (x + 0.01F), (double) y, (double) z, BlockAestheticStructure.iconGlow);
		if (block.shouldSideBeRendered(world, x, y, z + 1, 6))
			renderer.renderFaceZPos(block, (double) x, (double) y, (double) (z - 0.01F), BlockAestheticStructure.iconGlow);
		if (block.shouldSideBeRendered(world, x, y, z - 1, 6))
			renderer.renderFaceZNeg(block, (double) x, (double) y, (double) (z + 0.01F), BlockAestheticStructure.iconGlow);
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
		return BlockAestheticStructure.renderID;
	}
}