package T145.magistics.client;

import net.minecraft.util.ResourceLocation;
import T145.magistics.api.client.renderers.block.ChestRenderer;
import T145.magistics.client.lib.TextureHelper;
import T145.magistics.client.renderers.tile.TileChestHungryAlchemicalRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryEnderRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryMetalRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryRenderer;
import T145.magistics.common.CommonProxy;
import T145.magistics.common.blocks.BlockChestHungry;
import T145.magistics.common.blocks.BlockChestHungryAlchemical;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryAlchemical;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {	
	@Override
	public void postInit() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungry.class, new TileChestHungryRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryEnder.class, new TileChestHungryEnderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryMetal.class, new TileChestHungryMetalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryAlchemical.class, new TileChestHungryAlchemicalRenderer());

		RenderingRegistry.registerBlockHandler(new ChestRenderer(BlockChestHungryAlchemical.renderID, new ResourceLocation[] {
				new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_small.png"),
				new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_medium.png"),
				new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_large.png")
		}));
		RenderingRegistry.registerBlockHandler(new ChestRenderer(BlockChestHungryEnder.renderID, new TileChestHungryEnder()));
		RenderingRegistry.registerBlockHandler(new ChestRenderer(BlockChestHungryMetal.renderID, TextureHelper.ironChestTextures));
		RenderingRegistry.registerBlockHandler(new ChestRenderer(BlockChestHungry.renderID, new TileChestHungry()));
	}
}