package T145.magistics.client.renderers.tile;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import T145.magistics.common.config.MagisticsConfig;
import T145.magistics.common.tiles.TileChestHungryEnder;

public class ChestHungryEnderRenderHelper extends TileEntityRendererChestHelper {
	@Override
	public void renderChest(Block block, int i, float f) {
		if (block == MagisticsConfig.blocks[0])
			TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileChestHungryEnder(), 0.0D, 0.0D, 0.0D, 0.0F);
		else
			super.renderChest(block, i, f);
	}
}