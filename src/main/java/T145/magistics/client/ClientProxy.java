package T145.magistics.client;

import T145.magistics.client.renderers.block.BlockChestHungryEnderRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryEnderRenderer;
import T145.magistics.common.CommonProxy;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerBlockHandler(new BlockChestHungryEnderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryEnder.class, new TileChestHungryEnderRenderer());
	}
}