package T145.magistics.client.renderers.blocks;

import java.awt.Color;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.client.lib.RenderHelper;
import T145.magistics.common.blocks.BlockCrystalStorage;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockCrystalStorageRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);

		if (meta > 5)
			BlockRenderer.drawFaces(renderer, block, BlockCrystalStorage.icon[1], false);
		else
			BlockRenderer.drawFaces(renderer, block, BlockCrystalStorage.icon[0], false);

		List<Color> colors = RenderHelper.getColors();
		Color c = colors.get(meta);
		GL11.glColor3f(c.getRed() / 255F, c.getGreen() / 255F, c.getBlue() / 255F);
		block.setBlockBounds(0.005F, 0.005F, 0.005F, 0.995F, 0.995F, 0.995F);
		renderer.setRenderBoundsFromBlock(block);
		BlockRenderer.drawFaces(renderer, block, BlockCrystalStorage.iconGlow, false);
		GL11.glColor3f(1F, 1F, 1F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		Tessellator t = Tessellator.instance;
		List<Color> colors = RenderHelper.getColors();
		Color c = colors.get(world.getBlockMetadata(x, y, z));
		t.setColorOpaque_I(RenderHelper.getIntFromColor(c.getRed(), c.getGreen(), c.getBlue()));
		t.setBrightness(180);

		renderAllSidesInverted(world, x, y, z, block, renderer, BlockCrystalStorage.iconGlow, false);
		block.setBlockBounds(0.005F, 0.005F, 0.005F, 0.995F, 0.995F, 0.995F);
		renderer.setRenderBoundsFromBlock(block);
		renderAllSides(world, x, y, z, block, renderer, BlockCrystalStorage.iconGlow, false);
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
		return BlockCrystalStorage.renderID;
	}
}