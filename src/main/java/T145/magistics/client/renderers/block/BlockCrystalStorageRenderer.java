package T145.magistics.client.renderers.block;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemDye;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockCustomOreItem;
import T145.magistics.common.blocks.BlockCrystalStorage;
import T145.magistics.common.blocks.BlockCrystalStorageStructure;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockCrystalStorageRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	private int id = 0;

	public BlockCrystalStorageRenderer(int renderID) {
		id = renderID;
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		drawFaces(renderer, block, block.getIcon(0, 0), false);

		Color c;

		if (block instanceof BlockCrystalStorageStructure) {
			c = new Color(ItemDye.field_150922_c[meta]);
		} else {
			c = new Color(BlockCustomOreItem.colors[meta]);
		}

		GL11.glColor3f(c.getRed() / 255F, c.getGreen() / 255F, c.getBlue() / 255F);
		block.setBlockBounds(0.001F, 0.001F, 0.001F, 0.999F, 0.999F, 0.999F);
		renderer.setRenderBoundsFromBlock(block);
		drawFaces(renderer, block, ((BlockCrystalStorage) block).iconGlow, false);
		GL11.glColor3f(1F, 1F, 1F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		Tessellator t = Tessellator.instance;
		int meta = world.getBlockMetadata(x, y, z);

		if (block instanceof BlockCrystalStorageStructure) {
			t.setColorOpaque_I(ItemDye.field_150922_c[meta]);
		} else {
			t.setColorOpaque_I(BlockCustomOreItem.colors[meta]);
		}

		renderAllSidesInverted(world, x, y, z, block, renderer, ((BlockCrystalStorage) block).iconGlow, false);
		block.setBlockBounds(0.001F, 0.001F, 0.001F, 0.999F, 0.999F, 0.999F);
		renderer.setRenderBoundsFromBlock(block);
		renderAllSides(world, x, y, z, block, renderer, ((BlockCrystalStorage) block).iconGlow, false);
		GL11.glColor3f(1F, 1F, 1F);
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
		return id;
	}
}