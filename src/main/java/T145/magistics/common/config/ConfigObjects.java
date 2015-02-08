package T145.magistics.common.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import thaumcraft.api.ThaumcraftApi;
import T145.magistics.api.blocks.BlockMagisticsItem;
import T145.magistics.api.client.renderers.block.ChestItemRenderer;
import T145.magistics.api.client.renderers.block.ChestRenderer;
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
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockChestHungry;
import T145.magistics.common.blocks.BlockChestHungryAlchemical;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.blocks.BlockSortingChestHungry;
import T145.magistics.common.blocks.BlockSortingChestHungryAlchemical;
import T145.magistics.common.blocks.BlockSortingChestHungryAlchemicalItem;
import T145.magistics.common.blocks.BlockSortingChestHungryMetal;
import T145.magistics.common.blocks.aesthetics.BlockAestheticStructure;
import T145.magistics.common.items.ItemResources;
import T145.magistics.common.items.armor.ItemCruelMask;
import T145.magistics.common.items.baubles.ItemAmuletDismay;
import T145.magistics.common.items.baubles.ItemAmuletLife;
import T145.magistics.common.items.baubles.ItemBeltCleansing;
import T145.magistics.common.items.baubles.ItemBeltVigor;
import T145.magistics.common.items.baubles.ItemRingSouls;
import T145.magistics.common.items.relics.ItemDawnstone;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryAlchemical;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import T145.magistics.common.tiles.TileSortingChestHungry;
import T145.magistics.common.tiles.TileSortingChestHungryAlchemical;
import T145.magistics.common.tiles.TileSortingChestHungryMetal;

import com.dynious.refinedrelocation.lib.Resources;
import com.pahimar.ee3.item.ItemBlockAlchemicalChest;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ConfigObjects extends CommonProxy {
	public static CreativeTabs tabMagistics;
	public static List<String> supportedMods = new ArrayList<String>();

	public static List<Item> items = new ArrayList<Item>();
	public static List<Class> tiles = new ArrayList<Class>();
	public static LinkedHashMap<Block, Class> blocks = new LinkedHashMap<Block, Class>();

	public static LinkedHashMap<Class, TileEntitySpecialRenderer> tileRenderers = new LinkedHashMap<Class, TileEntitySpecialRenderer>();
	public static List<ISimpleBlockRenderingHandler> blockRenderers = new ArrayList<ISimpleBlockRenderingHandler>();
	public static LinkedHashMap<Item, IItemRenderer> itemRenderers = new LinkedHashMap<Item, IItemRenderer>();

	public static Item itemResources, itemAmuletDismay, itemAmuletLife, itemBeltCleansing, itemBeltVigor, itemRingSouls, itemCruelMask, itemDawnstone;
	public static Block blockAesthetic, blockAestheticStructure, blockChestHungry, blockChestHungryTrapped, blockChestHungryEnder, blockChestHungryAlchemical, blockChestHungryMetal, blockChestHungryRailcraft, blockSortingChestHungry, blockSortingChestHungryAlchemical, blockSortingChestHungryMetal, blockArcaneRedstoneLamp;

	public static void loadServer() {
		items.add(itemResources = new ItemResources().setUnlocalizedName("mystic_resources"));
		items.add(itemAmuletDismay = new ItemAmuletDismay().setUnlocalizedName("bauble.amulet_dismay"));
		items.add(itemAmuletLife = new ItemAmuletLife().setUnlocalizedName("bauble.amulet_life"));
		items.add(itemBeltCleansing = new ItemBeltCleansing().setUnlocalizedName("bauble.belt_cleansing"));
		items.add(itemBeltVigor = new ItemBeltVigor().setUnlocalizedName("bauble.belt_vigor"));
		items.add(itemRingSouls = new ItemRingSouls().setUnlocalizedName("bauble.ring_souls"));
		items.add(itemCruelMask = new ItemCruelMask(ThaumcraftApi.armorMatThaumium, 2, 0).setMaxDamage(100).setMaxStackSize(1).setUnlocalizedName("cruel_mask"));
		items.add(itemDawnstone = new ItemDawnstone().setUnlocalizedName("dawnstone"));

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
				blocks.put(blockSortingChestHungryAlchemical = new BlockSortingChestHungryAlchemical().setBlockName("sorting_hungry_alchemical_chest"), BlockSortingChestHungryAlchemicalItem.class);
			}

			if (Loader.isModLoaded("IronChest")) {
				tiles.add(TileSortingChestHungryMetal.class);
				blocks.put(blockSortingChestHungryMetal = new BlockSortingChestHungryMetal().setBlockName("sorting_hungry_metal_chest"), BlockMagisticsItem.class);
			}

			supportedMods.add("RefinedRelocation");
		}
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
				itemRenderers.put(Item.getItemFromBlock(blockSortingChestHungry), new ChestItemRenderer(new ResourceLocation[] {
						new ResourceLocation("thaumcraft", "textures/models/chesthungry.png")
				}, Resources.MODEL_TEXTURE_OVERLAY_CHEST));
			}

			if (Loader.isModLoaded("EE3")) {
				tileRenderers.put(TileSortingChestHungryAlchemical.class, new TileSortingChestHungryAlchemicalRenderer());
				itemRenderers.put(Item.getItemFromBlock(blockSortingChestHungryAlchemical), new ChestItemRenderer(new ResourceLocation[] {
						new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_small.png"),
						new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_medium.png"),
						new ResourceLocation("magistics", "textures/models/chest_hungry/alchemical_large.png")
				}, Resources.MODEL_TEXTURE_OVERLAY_ALCHEMICAL_CHEST));
			}

			if (Loader.isModLoaded("IronChest")) {
				tileRenderers.put(TileSortingChestHungryMetal.class, new TileSortingChestHungryMetalRenderer());
				itemRenderers.put(Item.getItemFromBlock(blockSortingChestHungryMetal), new ChestItemRenderer(TextureHelper.ironChestTextures, Resources.MODEL_TEXTURE_OVERLAY_CHEST));
			}
		}
	}

	public static void registerObjects() {
		if (debug)
			inform("Loading mod objects...");

		loadServer();

		for (Item item : items)
			GameRegistry.registerItem(item.setCreativeTab(tabMagistics), item.getUnlocalizedName());

		for (Class tile : tiles)
			GameRegistry.registerTileEntity(tile, "magistics:" + tile.getSimpleName());
		for (Block block : blocks.keySet())
			if (blocks.get(block) == null)
				GameRegistry.registerBlock(block.setCreativeTab(tabMagistics), block.getLocalizedName());
			else
				GameRegistry.registerBlock(block.setCreativeTab(tabMagistics), blocks.get(block), block.getLocalizedName());

		if (debug) {
			if (!supportedMods.isEmpty())
				for (String supportedMod : supportedMods)
					inform("Registered: " + supportedMod);
			inform("Server-side is up and running!");
		}
	}

	public static void registerRenderers() {
		if (debug)
			inform("Loading renderers...");

		loadClient();

		for (Class tile : tiles)
			if (tileRenderers.get(tile) != null)
				ClientRegistry.bindTileEntitySpecialRenderer(tile, tileRenderers.get(tile));
		for (ISimpleBlockRenderingHandler blockRenderer : blockRenderers)
			RenderingRegistry.registerBlockHandler(blockRenderer);
		for (Item block : itemRenderers.keySet())
			MinecraftForgeClient.registerItemRenderer(block, itemRenderers.get(block));

		if (debug)
			inform("Launched successfully!");
	}

	static {
		tabMagistics = new CreativeTabs(Magistics.modid) {
			@Override
			public Item getTabIconItem() {
				return Item.getItemFromBlock(blockChestHungryEnder);
			}

			@Override
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("magistics.png");
	}
}