package T145.magistics.client.renderers.block;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockCustomOreItem;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockAestheticRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	public int id = 0;
	public IIcon icon[], iconGlow;

	public BlockAestheticRenderer(int renderID, IIcon[] textures, IIcon glow) {
		id = renderID;
		icon = textures;
		iconGlow = glow;
	}

	public int getColor(int meta) {
		int color = BlockCustomOreItem.colors[5];
		for (int a = 1; a < 6; a++)
			if (meta == 6)
				color = BlockCustomOreItem.colors[(a == 5) ? 6 : a];
			else
				color = BlockCustomOreItem.colors[meta + 1];
		return color;
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		BlockRenderer.drawFaces(renderer, block, icon[meta], false);
		Color c = new Color(getColor(meta));
		GL11.glColor3f(c.getRed() / 255F, c.getGreen() / 255F, c.getBlue() / 255F);
		block.setBlockBounds(0.005F, 0.005F, 0.005F, 0.995F, 0.995F, 0.995F);
		renderer.setRenderBoundsFromBlock(block);
		BlockRenderer.drawFaces(renderer, block, iconGlow, false);
		GL11.glColor3f(1F, 1F, 1F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		Tessellator t = Tessellator.instance;
		t.setColorOpaque_I(getColor(world.getBlockMetadata(x, y, z)));
		t.setBrightness(180);
		renderAllSidesInverted(world, x, y, z, block, renderer, iconGlow, false);
		block.setBlockBounds(0.005F, 0.005F, 0.005F, 0.995F, 0.995F, 0.995F);
		renderer.setRenderBoundsFromBlock(block);
		renderAllSides(world, x, y, z, block, renderer, iconGlow, false);
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