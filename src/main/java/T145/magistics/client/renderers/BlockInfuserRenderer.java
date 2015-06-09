package T145.magistics.client.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.common.blocks.BlockInfuser;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockInfuserRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	public boolean renderInfuser(RenderBlocks renderer, int x, int y, int z, Block block, int meta, boolean inv) {
		if (block.getRenderBlockPass() == 0 || inv) {
			block.setBlockBounds(0F, 0F, 0F, 1F, 1F - W1, 1F);
			renderer.setRenderBoundsFromBlock(block);

			BlockInfuser infuser = (BlockInfuser) block;
			IIcon infuser_icon[] = infuser.infuser_icon, dark_infuser_icon[] = infuser.dark_infuser_icon;

			if (inv) {
				if (infuser.isDark(meta)) {
					drawFaces(renderer, block, dark_infuser_icon[0], dark_infuser_icon[4], dark_infuser_icon[2], dark_infuser_icon[2], dark_infuser_icon[2], dark_infuser_icon[2], false);
				} else {
					drawFaces(renderer, block, infuser_icon[0], infuser_icon[4], infuser_icon[2], infuser_icon[2], infuser_icon[2], infuser_icon[2], false);
				}
			} else {
				renderer.renderStandardBlock(block, x, y, z);
			}

			block.setBlockBounds(0F, 1F - W1, 0F, W3, 1F, W3);
			renderer.setRenderBoundsFromBlock(block);

			if (inv) {
				if (infuser.isDark(meta)) {
					drawFaces(renderer, block, dark_infuser_icon[0], dark_infuser_icon[4], dark_infuser_icon[2], dark_infuser_icon[2], dark_infuser_icon[2], dark_infuser_icon[2], false);
				} else {
					drawFaces(renderer, block, infuser_icon[0], infuser_icon[4], infuser_icon[2], infuser_icon[2], infuser_icon[2], infuser_icon[2], false);
				}
			} else {
				renderer.renderStandardBlock(block, x, y, z);
			}

			block.setBlockBounds(0F, 1F - W1, 1F - W3, W3, 1F, 1F);
			renderer.setRenderBoundsFromBlock(block);

			if (inv) {
				if (infuser.isDark(meta)) {
					drawFaces(renderer, block, dark_infuser_icon[0], dark_infuser_icon[4], dark_infuser_icon[2], dark_infuser_icon[2], dark_infuser_icon[2], dark_infuser_icon[2], false);
				} else {
					drawFaces(renderer, block, infuser_icon[0], infuser_icon[4], infuser_icon[2], infuser_icon[2], infuser_icon[2], infuser_icon[2], false);
				}
			} else {
				renderer.renderStandardBlock(block, x, y, z);
			}

			block.setBlockBounds(1F - W3, 1F - W1, 0F, 1F, 1F, W3);
			renderer.setRenderBoundsFromBlock(block);

			if (inv) {
				if (infuser.isDark(meta)) {
					drawFaces(renderer, block, dark_infuser_icon[0], dark_infuser_icon[4], dark_infuser_icon[2], dark_infuser_icon[2], dark_infuser_icon[2], dark_infuser_icon[2], false);
				} else {
					drawFaces(renderer, block, infuser_icon[0], infuser_icon[4], infuser_icon[2], infuser_icon[2], infuser_icon[2], infuser_icon[2], false);
				}
			} else {
				renderer.renderStandardBlock(block, x, y, z);
			}

			block.setBlockBounds(1F - W3, 1F - W1, 1F - W3, 1F, 1F, 1F);
			renderer.setRenderBoundsFromBlock(block);

			if (inv) {
				if (infuser.isDark(meta)) {
					drawFaces(renderer, block, dark_infuser_icon[0], dark_infuser_icon[4], dark_infuser_icon[2], dark_infuser_icon[2], dark_infuser_icon[2], dark_infuser_icon[2], false);
				} else {
					drawFaces(renderer, block, infuser_icon[0], infuser_icon[4], infuser_icon[2], infuser_icon[2], infuser_icon[2], infuser_icon[2], false);
				}
			} else {
				renderer.renderStandardBlock(block, x, y, z);
			}
		}

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
		renderInfuser(renderer, 0, 0, 0, block, meta, true);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return renderInfuser(renderer, x, y, z, block, world.getBlockMetadata(x, y, z), false);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockInfuser.renderID;
	}
}