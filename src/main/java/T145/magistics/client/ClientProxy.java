package T145.magistics.client;

import T145.magistics.client.renderers.block.BlockAestheticStructureRenderer;
import T145.magistics.client.renderers.block.BlockChestHungryEnderRenderer;
import T145.magistics.client.renderers.block.BlockChestHungryMetalRenderer;
import T145.magistics.client.renderers.block.BlockChestHungryRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryEnderRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryMetalRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryRenderer;
import T145.magistics.common.CommonProxy;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderInformation() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungry.class, new TileChestHungryRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryEnder.class, new TileChestHungryEnderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryMetal.class, new TileChestHungryMetalRenderer());
		RenderingRegistry.registerBlockHandler(new BlockChestHungryRenderer());
		RenderingRegistry.registerBlockHandler(new BlockChestHungryEnderRenderer());
		RenderingRegistry.registerBlockHandler(new BlockChestHungryMetalRenderer());
		RenderingRegistry.registerBlockHandler(new BlockAestheticStructureRenderer());
	}
}