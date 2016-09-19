package T145.magistics.client.render.blocks;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.blocks.BlockCrystalCore;
import T145.magistics.tiles.TileCrystalCore;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;

@SideOnly(Side.CLIENT)
public class RenderBlockCrystalCore extends BlockRenderer implements ISimpleBlockRenderingHandler {
	public static final ISimpleBlockRenderingHandler INSTANCE = new RenderBlockCrystalCore();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		GL11.glRotatef(90F, 0F, 1.0F, 0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		TileCrystalCore core = new TileCrystalCore();
		core.blockMetadata = 0;
		TileEntityRendererDispatcher.instance.renderTileEntityAt(core, 0, 0, 0, 0F);

		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockCrystalCore.INSTANCE.getRenderType();
	}
}