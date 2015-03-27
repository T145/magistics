package T145.magistics.client;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import T145.magistics.api.client.renderers.blocks.ChestRenderer;
import T145.magistics.api.client.renderers.items.ItemChestRenderer;
import T145.magistics.client.config.ConfigRenderers;
import T145.magistics.client.lib.RenderHelper;
import T145.magistics.client.renderers.RenderHungryStrongbox;
import T145.magistics.client.renderers.blocks.BlockCrystalStorageRenderer;
import T145.magistics.client.renderers.blocks.BlockCrystalStorageStructureRenderer;
import T145.magistics.client.renderers.tiles.TileChestHungryAlchemicalRenderer;
import T145.magistics.client.renderers.tiles.TileChestHungryEnderRenderer;
import T145.magistics.client.renderers.tiles.TileChestHungryMetalRenderer;
import T145.magistics.client.renderers.tiles.TileChestHungryRenderer;
import T145.magistics.client.renderers.tiles.TileSortingChestHungryAlchemicalRenderer;
import T145.magistics.client.renderers.tiles.TileSortingChestHungryMetalRenderer;
import T145.magistics.client.renderers.tiles.TileSortingChestHungryRenderer;
import T145.magistics.common.CommonProxy;
import T145.magistics.common.blocks.BlockChestHungry;
import T145.magistics.common.blocks.BlockChestHungryAlchemical;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.blocks.BlockCrystalStorage;
import T145.magistics.common.blocks.BlockCrystalStorageEngineering;
import T145.magistics.common.blocks.BlockCrystalStorageReinforced;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryAlchemical;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import T145.magistics.common.tiles.TileHungryStrongbox;
import T145.magistics.common.tiles.TileHungryStrongboxCreative;
import T145.magistics.common.tiles.TileSortingChestHungry;
import T145.magistics.common.tiles.TileSortingChestHungryAlchemical;
import T145.magistics.common.tiles.TileSortingChestHungryMetal;

import com.dynious.refinedrelocation.lib.Resources;

import cpw.mods.fml.common.Loader;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerObjects() {
		super.registerObjects(); // make the server load first
		ConfigRenderers config = ConfigRenderers.getInstance();

		config.addBlockRenderer(new BlockCrystalStorageRenderer(BlockCrystalStorage.icon, BlockCrystalStorage.renderID));
		config.addBlockRenderer(new BlockCrystalStorageStructureRenderer());
		config.addBlockRenderer(new BlockCrystalStorageRenderer(BlockCrystalStorageReinforced.icon, BlockCrystalStorageReinforced.renderID));
		config.addBlockRenderer(new BlockCrystalStorageRenderer(BlockCrystalStorageEngineering.icon, BlockCrystalStorageEngineering.renderID));

		config.addTileRenderer(TileChestHungry.class, new TileChestHungryRenderer());
		config.addBlockRenderer(new ChestRenderer(BlockChestHungry.renderID, new TileChestHungry()));

		if (Loader.isModLoaded("EE3")) {
			config.addTileRenderer(TileChestHungryAlchemical.class, new TileChestHungryAlchemicalRenderer());
			config.addBlockRenderer(new ChestRenderer(BlockChestHungryAlchemical.renderID, new ResourceLocation[] {
					new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_small.png"),
					new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_medium.png"),
					new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_large.png")
			}));
		}

		config.addTileRenderer(TileChestHungryEnder.class, new TileChestHungryEnderRenderer());
		config.addBlockRenderer(new ChestRenderer(BlockChestHungryEnder.renderID, new TileChestHungryEnder()));

		if (Loader.isModLoaded("IronChest")) {
			config.addTileRenderer(TileChestHungryMetal.class, new TileChestHungryMetalRenderer());
			config.addBlockRenderer(new ChestRenderer(BlockChestHungryMetal.renderID, RenderHelper.getMetalChestTextures()));
		}

		if (Loader.isModLoaded("RefinedRelocation")) {
			config.addTileRenderer(TileSortingChestHungry.class, new TileSortingChestHungryRenderer());
			config.addItemRenderer(Item.getItemFromBlock(blockSortingChestHungry), new ItemChestRenderer(new ResourceLocation[] {
					new ResourceLocation("thaumcraft", "textures/models/chesthungry.png")
			}, Resources.MODEL_TEXTURE_OVERLAY_CHEST));

			if (Loader.isModLoaded("EE3")) {
				config.addItemRenderer(Item.getItemFromBlock(blockSortingChestHungryAlchemical), new ItemChestRenderer(new ResourceLocation[] {
						new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_small.png"),
						new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_medium.png"),
						new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_large.png")
				}, Resources.MODEL_TEXTURE_OVERLAY_ALCHEMICAL_CHEST));
				config.addTileRenderer(TileSortingChestHungryAlchemical.class, new TileSortingChestHungryAlchemicalRenderer());
			}

			if (Loader.isModLoaded("IronChest")) {
				config.addItemRenderer(Item.getItemFromBlock(blockSortingChestHungryMetal), new ItemChestRenderer(RenderHelper.getMetalChestTextures(), Resources.MODEL_TEXTURE_OVERLAY_CHEST));
				config.addTileRenderer(TileSortingChestHungryMetal.class, new TileSortingChestHungryMetalRenderer());
			}
		}

		if (Loader.isModLoaded("ThermalExpansion")) {
			config.addTileRenderer(TileHungryStrongbox.class, new RenderHungryStrongbox());
			config.addTileRenderer(TileHungryStrongboxCreative.class, new RenderHungryStrongbox());
			config.addItemRenderer(Item.getItemFromBlock(blockHungryStrongbox), new RenderHungryStrongbox());
		}

		config.register();
	}
}