package T145.magistics.client.renderers.pillars;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import T145.magistics.common.blocks.BlockChristmasLeaves;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChristmasLeaves implements ISimpleBlockRenderingHandler {
	public static void drawFaces(RenderBlocks renderblocks, Block block, IIcon i1) {
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0F, -1F, 0F);
		renderblocks.renderFaceYNeg(block, 0D, 0D, 0D, i1);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0F, 1F, 0F);
		renderblocks.renderFaceYPos(block, 0D, 0D, 0D, i1);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0F, 0F, 1F);
		renderblocks.renderFaceXNeg(block, 0D, 0D, 0D, i1);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0F, 0F, -1F);
		renderblocks.renderFaceXPos(block, 0D, 0D, 0D, i1);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1F, 0F, 0F);
		renderblocks.renderFaceZNeg(block, 0D, 0D, 0D, i1);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0F, 0F);
		renderblocks.renderFaceZPos(block, 0D, 0D, 0D, i1);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		drawFaces(renderer, block, block.getIcon(0, metadata));
		drawFaces(renderer, block, BlockChristmasLeaves.glowing);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		Tessellator t = Tessellator.instance;
		t.setBrightness(160);

		IIcon tex = BlockChristmasLeaves.glowing;

		renderer.renderFaceXPos(block, x, y, z, tex);
		renderer.renderFaceXNeg(block, x, y, z, tex);
		renderer.renderFaceZPos(block, x, y, z, tex);
		renderer.renderFaceZNeg(block, x, y, z, tex);
		renderer.renderFaceYPos(block, x, y, z, tex);
		renderer.renderFaceYNeg(block, x, y, z, tex);

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);

		return true;
	}

	public static int setBrightness(IBlockAccess blockAccess, int i, int j, int k, Block block) {
		int brightness = block.getMixedBrightnessForBlock(blockAccess, i, j, k);

		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess, i, j, k));

		float f = 1F;

		int l = block.colorMultiplier(blockAccess, i, j, k);
		float f1 = (l >> 16 & 255) / 255F, f2 = (l >> 8 & 255) / 255F, f3 = (l & 255) / 255F, f4;

		if (EntityRenderer.anaglyphEnable) {
			float f5 = (f1 * 30F + f2 * 59F + f3 * 11F) / 100F;
			f4 = (f1 * 30F + f2 * 70F) / 100F;
			float f6 = (f1 * 30F + f3 * 70F) / 100F;
			f1 = f5;
			f2 = f4;
			f3 = f6;
		}

		tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
		return brightness;
	}

	@Override
	public boolean shouldRender3DInInventory(int i) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockChristmasLeaves.renderID;
	}
}