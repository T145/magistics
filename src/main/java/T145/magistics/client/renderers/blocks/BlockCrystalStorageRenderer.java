package T145.magistics.client.renderers.blocks;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.client.lib.RenderHelper;
import T145.magistics.common.blocks.BlockCrystalStorage;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockCrystalStorageRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	public IIcon textures[];
	public int id;

	public BlockCrystalStorageRenderer(IIcon[] resources, int renderID) {
		textures = resources;
		id = renderID;
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);

		if (meta > 5)
			drawFaces(renderer, block, textures[1], false);
		else
			drawFaces(renderer, block, textures[0], false);

		Color c = RenderHelper.getCrystalColors().get(meta);
		GL11.glColor3f(c.getRed() / 255F, c.getGreen() / 255F, c.getBlue() / 255F);
		block.setBlockBounds(0.005F, 0.005F, 0.005F, 0.995F, 0.995F, 0.995F);
		renderer.setRenderBoundsFromBlock(block);
		drawFaces(renderer, block, BlockCrystalStorage.iconGlow, false);
		GL11.glColor3f(1F, 1F, 1F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		Tessellator t = Tessellator.instance;
		t.setColorOpaque_I(RenderHelper.getCrystalColorCodes().get(world.getBlockMetadata(x, y, z)));
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
		return id;
	}
}