package T145.magistics.common.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import T145.magistics.api.blocks.BlockMagisticsItem;
import T145.magistics.api.client.renderers.block.ChestRenderer;
import T145.magistics.api.client.renderers.block.SortingChestItemRenderer;
import T145.magistics.client.lib.TextureHelper;
import T145.magistics.client.renderers.block.BlockAestheticStructureRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryAlchemicalRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryEnderRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryMetalRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryRenderer;
import T145.magistics.client.renderers.tile.TileSortingChestHungryAlchemicalRenderer;
import T145.magistics.client.renderers.tile.TileSortingChestHungryMetalRenderer;
import T145.magistics.client.renderers.tile.TileSortingChestHungryRenderer;
import T145.magistics.common.CommonProxy;
import T145.magistics.common.blocks.BlockAestheticStructure;
import T145.magistics.common.blocks.BlockChestHungry;
import T145.magistics.common.blocks.BlockChestHungryAlchemical;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.blocks.BlockSortingChestHungry;
import T145.magistics.common.blocks.BlockSortingChestHungryAlchemical;
import T145.magistics.common.blocks.BlockSortingChestHungryMetal;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryAlchemical;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import T145.magistics.common.tiles.TileSortingChestHungry;
import T145.magistics.common.tiles.TileSortingChestHungryAlchemical;
import T145.magistics.common.tiles.TileSortingChestHungryMetal;

import com.pahimar.ee3.item.ItemBlockAlchemicalChest;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks extends CommonProxy {
	public static List<Class> tiles = new ArrayList<Class>();
	public static LinkedHashMap<Block, Class> blocks = new LinkedHashMap<Block, Class>();
	public static LinkedHashMap<Class, TileEntitySpecialRenderer> tileRenderers = new LinkedHashMap<Class, TileEntitySpecialRenderer>();
	public static List<ISimpleBlockRenderingHandler> blockRenderers = new ArrayList<ISimpleBlockRenderingHandler>();
	public static LinkedHashMap<Item, IItemRenderer> itemRenderers = new LinkedHashMap<Item, IItemRenderer>();

	public static Block blockAesthetic, blockAestheticStructure, blockChestHungry, blockChestHungryTrapped, blockChestHungryEnder, blockChestHungryAlchemical, blockChestHungryMetal, blockChestHungryRailcraft, blockSortingChestHungry, blockSortingChestHungryAlchemical, blockSortingChestHungryMetal, blockArcaneRedstoneLamp;

	public static void loadServer() {
		blocks.put(blockAestheticStructure = new BlockAestheticStructure().setBlockName("aesthetic_structure"), BlockMagisticsItem.class);

		if (hungry_chest_override) {
			tiles.add(TileChestHungry.class);
			blocks.put(blockChestHungry = new BlockChestHungry(0).setBlockName("hungry_chest"), null);
			blocks.put(blockChestHungryTrapped = new BlockChestHungry(1).setBlockName("trapped_hungry_chest"), null);
		}

		tiles.add(TileChestHungryEnder.class);
		blocks.put(blockChestHungryEnder = new BlockChestHungryEnder().setBlockName("hungry_ender_chest").setHardness(22.5F).setResistance(1000F).setStepSound(Block.soundTypePiston).setLightLevel(0.5F), null);

		if (Loader.isModLoaded("EE3")) {
			tiles.add(TileChestHungryAlchemical.class);
			blocks.put(blockChestHungryAlchemical = new BlockChestHungryAlchemical().setBlockName("hungry_alchemical_chest"), ItemBlockAlchemicalChest.class);
			supportedMods.add("EE3");
		}

		if (Loader.isModLoaded("IronChest")) {
			tiles.add(TileChestHungryMetal.class);
			blocks.put(blockChestHungryMetal = new BlockChestHungryMetal().setBlockName("hungry_metal_chest").setHardness(3F), BlockMagisticsItem.class);
			supportedMods.add("IronChests");
		}

		if (Loader.isModLoaded("RefinedRelocation")) {
			if (hungry_chest_override) {
				tiles.add(TileSortingChestHungry.class);
				blocks.put(blockSortingChestHungry = new BlockSortingChestHungry().setBlockName("sorting_hungry_chest"), null);
			}

			if (Loader.isModLoaded("EE3")) {
				tiles.add(TileSortingChestHungryAlchemical.class);
				blocks.put(blockSortingChestHungryAlchemical = new BlockSortingChestHungryAlchemical().setBlockName("sorting_hungry_alchemical_chest"), ItemBlockAlchemicalChest.class);
			}

			if (Loader.isModLoaded("IronChest")) {
				tiles.add(TileSortingChestHungryMetal.class);
				blocks.put(blockSortingChestHungryMetal = new BlockSortingChestHungryMetal().setBlockName("sorting_hungry_metal_chest"), BlockMagisticsItem.class);
			}

			supportedMods.add("RefinedRelocation");
		}

		for (Class tile : tiles)
			GameRegistry.registerTileEntity(tile, "magistics:" + tile.getSimpleName());
		for (Block block : blocks.keySet())
			if (blocks.get(block) == null)
				GameRegistry.registerBlock(block.setCreativeTab(tabMagistics), block.getLocalizedName());
			else
				GameRegistry.registerBlock(block.setCreativeTab(tabMagistics), blocks.get(block), block.getLocalizedName());
	}

	public static void loadClient() {
		blockRenderers.add(new BlockAestheticStructureRenderer());

		if (hungry_chest_override) {
			tileRenderers.put(TileChestHungry.class, new TileChestHungryRenderer());
			blockRenderers.add(new ChestRenderer(BlockChestHungry.renderID, new TileChestHungry()));
		}

		tileRenderers.put(TileChestHungryEnder.class, new TileChestHungryEnderRenderer());
		blockRenderers.add(new ChestRenderer(BlockChestHungryEnder.renderID, new TileChestHungryEnder()));

		if (Loader.isModLoaded("EE3")) {
			tileRenderers.put(TileChestHungryAlchemical.class, new TileChestHungryAlchemicalRenderer());
			blockRenderers.add(new ChestRenderer(BlockChestHungryAlchemical.renderID, new ResourceLocation[] {
					new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_small.png"),
					new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_medium.png"),
					new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_large.png")
			}));
		}

		if (Loader.isModLoaded("IronChest")) {
			tileRenderers.put(TileChestHungryMetal.class, new TileChestHungryMetalRenderer());
			blockRenderers.add(new ChestRenderer(BlockChestHungryMetal.renderID, TextureHelper.ironChestTextures));
		}

		if (Loader.isModLoaded("RefinedRelocation")) {
			if (hungry_chest_override) {
				tileRenderers.put(TileSortingChestHungry.class, new TileSortingChestHungryRenderer());
				itemRenderers.put(Item.getItemFromBlock(blockSortingChestHungry), new SortingChestItemRenderer(new ResourceLocation[] {
						new ResourceLocation("thaumcraft", "textures/models/chesthungry.png")
				}));
			}

			if (Loader.isModLoaded("EE3")) {
				tileRenderers.put(TileSortingChestHungryAlchemical.class, new TileSortingChestHungryAlchemicalRenderer());
				itemRenderers.put(Item.getItemFromBlock(blockSortingChestHungryAlchemical), new SortingChestItemRenderer(new ResourceLocation[] {
						new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_small.png"),
						new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_medium.png"),
						new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_large.png")
				}));
			}

			if (Loader.isModLoaded("IronChest")) {
				tileRenderers.put(TileSortingChestHungryMetal.class, new TileSortingChestHungryMetalRenderer());
				itemRenderers.put(Item.getItemFromBlock(blockSortingChestHungryMetal), new SortingChestItemRenderer(TextureHelper.ironChestTextures));
			}
		}
	}
}