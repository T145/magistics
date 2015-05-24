package T145.magistics.client.renderers.block;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockCustomOreItem;
import T145.magistics.common.blocks.BlockMysticFarmland;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockMysticFarmlandRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	int mod = 4;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		drawFaces(renderer, block, ((BlockMysticFarmland) block).icons[0],
				((BlockMysticFarmland) block).icons[1],
				((BlockMysticFarmland) block).icons[2],
				((BlockMysticFarmland) block).icons[2],
				((BlockMysticFarmland) block).icons[2],
				((BlockMysticFarmland) block).icons[2], false);

		Color c = new Color(BlockCustomOreItem.colors[mod]);
		GL11.glColor3f(c.getRed() / 255F, c.getGreen() / 255F, c.getBlue() / 255F);
		block.setBlockBounds(0.001F, 0.001F, 0.001F, 0.999F, 0.999F, 0.999F);
		renderer.setRenderBoundsFromBlock(block);
		drawFaces(renderer, block, ((BlockMysticFarmland) block).iconGlow, false);
		GL11.glColor3f(1F, 1F, 1F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		Tessellator t = Tessellator.instance;
		t.setColorOpaque_I(BlockCustomOreItem.colors[mod]);

		renderAllSidesInverted(world, x, y, z, block, renderer, ((BlockMysticFarmland) block).iconGlow, false);
		block.setBlockBounds(0.001F, 0.001F, 0.001F, 0.999F, 0.999F, 0.999F);
		renderer.setRenderBoundsFromBlock(block);
		renderAllSides(world, x, y, z, block, renderer, ((BlockMysticFarmland) block).iconGlow, false);
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
		return BlockMysticFarmland.renderID;
	}
}