package T145.magistics.common.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thaumcraft.api.ThaumcraftApi;
import T145.magistics.api.CraftingPillarAPI;
import T145.magistics.api.blocks.BlockMagisticsItem;
import T145.magistics.api.client.renderers.block.ChestItemRenderer;
import T145.magistics.api.client.renderers.block.ChestRenderer;
import T145.magistics.client.lib.TextureHelper;
import T145.magistics.client.renderers.block.BlockAestheticStructureRenderer;
import T145.magistics.client.renderers.craftingpillars.RenderAnvilPillar;
import T145.magistics.client.renderers.craftingpillars.RenderBrewingPillar;
import T145.magistics.client.renderers.craftingpillars.RenderChristmasLeaves;
import T145.magistics.client.renderers.craftingpillars.RenderCraftingPillar;
import T145.magistics.client.renderers.craftingpillars.RenderDiskPillar;
import T145.magistics.client.renderers.craftingpillars.RenderExtendPillar;
import T145.magistics.client.renderers.craftingpillars.RenderFreezerPillar;
import T145.magistics.client.renderers.craftingpillars.RenderFurnacePillar;
import T145.magistics.client.renderers.craftingpillars.RenderLight;
import T145.magistics.client.renderers.craftingpillars.RenderPotPillar;
import T145.magistics.client.renderers.craftingpillars.RenderPresent;
import T145.magistics.client.renderers.craftingpillars.RenderPumpPillar;
import T145.magistics.client.renderers.craftingpillars.RenderSentryPillar;
import T145.magistics.client.renderers.craftingpillars.RenderShowOffPillar;
import T145.magistics.client.renderers.craftingpillars.RenderTankPillar;
import T145.magistics.client.renderers.craftingpillars.RenderTrashPillar;
import T145.magistics.client.renderers.tile.TileChestHungryAlchemicalRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryEnderRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryMetalRenderer;
import T145.magistics.client.renderers.tile.TileChestHungryRenderer;
import T145.magistics.client.renderers.tile.TileSortingChestHungryAlchemicalRenderer;
import T145.magistics.client.renderers.tile.TileSortingChestHungryMetalRenderer;
import T145.magistics.client.renderers.tile.TileSortingChestHungryRenderer;
import T145.magistics.common.CommonProxy;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockAestheticStructure;
import T145.magistics.common.blocks.BlockChestHungry;
import T145.magistics.common.blocks.BlockChestHungryAlchemical;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.blocks.BlockSortingChestHungry;
import T145.magistics.common.blocks.BlockSortingChestHungryAlchemical;
import T145.magistics.common.blocks.BlockSortingChestHungryAlchemicalItem;
import T145.magistics.common.blocks.BlockSortingChestHungryMetal;
import T145.magistics.common.blocks.craftingpillars.BlockPillarAnvil;
import T145.magistics.common.blocks.craftingpillars.BlockPillarBrewing;
import T145.magistics.common.blocks.craftingpillars.BlockPillarCrafting;
import T145.magistics.common.blocks.craftingpillars.BlockPillarDisplay;
import T145.magistics.common.blocks.craftingpillars.BlockPillarExtend;
import T145.magistics.common.blocks.craftingpillars.BlockPillarFreezer;
import T145.magistics.common.blocks.craftingpillars.BlockPillarFurnace;
import T145.magistics.common.blocks.craftingpillars.BlockPillarPot;
import T145.magistics.common.blocks.craftingpillars.BlockPillarPump;
import T145.magistics.common.blocks.craftingpillars.BlockPillarSentry;
import T145.magistics.common.blocks.craftingpillars.BlockPillarTank;
import T145.magistics.common.blocks.craftingpillars.BlockPillarTrash;
import T145.magistics.common.blocks.craftingpillars.BlockPillarTurntable;
import T145.magistics.common.blocks.craftingpillars.ChristmasLeavesBlock;
import T145.magistics.common.blocks.craftingpillars.ChristmasLightBlock;
import T145.magistics.common.blocks.craftingpillars.ChristmasPresentBlock;
import T145.magistics.common.blocks.craftingpillars.ChristmasTreeSapling;
import T145.magistics.common.blocks.craftingpillars.sentry.SentryBehaviorArrow;
import T145.magistics.common.blocks.craftingpillars.sentry.SentryBehaviorEgg;
import T145.magistics.common.blocks.craftingpillars.sentry.SentryBehaviorFireball;
import T145.magistics.common.blocks.craftingpillars.sentry.SentryBehaviorPotion;
import T145.magistics.common.blocks.craftingpillars.sentry.SentryBehaviorSnowball;
import T145.magistics.common.blocks.craftingpillars.sentry.SentryBehaviors;
import T145.magistics.common.items.ItemResources;
import T145.magistics.common.items.armor.ItemCruelMask;
import T145.magistics.common.items.baubles.ItemAmuletDismay;
import T145.magistics.common.items.baubles.ItemAmuletLife;
import T145.magistics.common.items.baubles.ItemBeltCleansing;
import T145.magistics.common.items.baubles.ItemBeltVigor;
import T145.magistics.common.items.baubles.ItemRingSouls;
import T145.magistics.common.items.craftingpillars.PillarRecord;
import T145.magistics.common.items.relics.ItemDawnstone;
import T145.magistics.common.lib.handlers.PillarEventHandler;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryAlchemical;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import T145.magistics.common.tiles.TileSortingChestHungry;
import T145.magistics.common.tiles.TileSortingChestHungryAlchemical;
import T145.magistics.common.tiles.TileSortingChestHungryMetal;
import T145.magistics.common.tiles.craftingpillars.TileEntityAnvilPillar;
import T145.magistics.common.tiles.craftingpillars.TileEntityBrewingPillar;
import T145.magistics.common.tiles.craftingpillars.TileEntityChristmasPresent;
import T145.magistics.common.tiles.craftingpillars.TileEntityCraftingPillar;
import T145.magistics.common.tiles.craftingpillars.TileEntityDiskPlayerPillar;
import T145.magistics.common.tiles.craftingpillars.TileEntityExtendPillar;
import T145.magistics.common.tiles.craftingpillars.TileEntityFreezerPillar;
import T145.magistics.common.tiles.craftingpillars.TileEntityFurnacePillar;
import T145.magistics.common.tiles.craftingpillars.TileEntityLight;
import T145.magistics.common.tiles.craftingpillars.TileEntityPotPillar;
import T145.magistics.common.tiles.craftingpillars.TileEntityPumpPillar;
import T145.magistics.common.tiles.craftingpillars.TileEntitySentryPillar;
import T145.magistics.common.tiles.craftingpillars.TileEntityShowOffPillar;
import T145.magistics.common.tiles.craftingpillars.TileEntityTankPillar;
import T145.magistics.common.tiles.craftingpillars.TileEntityTrashPillar;

import com.dynious.refinedrelocation.lib.Resources;
import com.pahimar.ee3.item.ItemBlockAlchemicalChest;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
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

	public static Block blockBasePillar;
	public static Block blockShowOffPillar;
	public static Block blockCraftingPillar;
	public static Block blockFurnacePillar;
	public static Block blockAnvilPillar;
	public static Block blockTankPillar;
	public static Block blockBrewingPillar;
	public static Block blockDiskPlayerPillar;
	public static Block blockFreezerPillar;
	public static Block blockPotPillar;
	public static Block blockSentryPillar;
	public static Block blockTrashPillar;
	public static Block blockPumpPillar;

	public static Block blockChristmasLeaves;
	public static Block blockChristmasTreeSapling;
	public static Block blockChristmasPresent;
	public static Block blockChristmasLight;

	public static Item itemDiscElysium;

	public static ItemStack[] present_loot = new ItemStack[] {
		new ItemStack(blockChristmasPresent, 1, 0),
		new ItemStack(blockChristmasPresent, 1, 1),
		new ItemStack(blockCraftingPillar, 1, 0),
		new ItemStack(blockAnvilPillar, 1, 0),
		new ItemStack(blockFurnacePillar, 1, 0),
		new ItemStack(blockFreezerPillar, 1, 0),
		new ItemStack(blockShowOffPillar, 1, 0),
		new ItemStack(blockPotPillar, 1, 0),
		new ItemStack(blockBrewingPillar, 1, 0),
		new ItemStack(blockDiskPlayerPillar, 1, 0),
		new ItemStack(blockSentryPillar, 1, 0),
		new ItemStack(blockTankPillar, 1, 0),
		new ItemStack(blockTrashPillar, 1, 0),
		new ItemStack(blockBasePillar, 3, 0),
		new ItemStack(itemDiscElysium, 1, 0)
	};

	public static boolean floatingItems = true;
	public static boolean rayTrace = false;
	public static boolean renderHitBoxes = true;

	public static AchievementPage achievementPage;

	public static Achievement achievementGettingStarted;
	public static Achievement achievementChristmas;
	public static Achievement achievementRecursion;
	public static Achievement achievementCompressingLiquids;

	public static Achievement achievementShowoff;
	public static Achievement achievementDisc;
	public static Achievement achievementRecursion3;

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

		tiles.add(TileEntityExtendPillar.class);
		tiles.add(TileEntityShowOffPillar.class);
		tiles.add(TileEntityCraftingPillar.class);
		tiles.add(TileEntityFurnacePillar.class);
		tiles.add(TileEntityAnvilPillar.class);
		tiles.add(TileEntityTankPillar.class);
		// tiles.add(TileEntityEnchantmentPillar.class);
		tiles.add(TileEntityBrewingPillar.class);
		tiles.add(TileEntityDiskPlayerPillar.class);
		tiles.add(TileEntityFreezerPillar.class);
		tiles.add(TileEntityPotPillar.class);
		tiles.add(TileEntitySentryPillar.class);
		tiles.add(TileEntityTrashPillar.class);
		tiles.add(TileEntityPumpPillar.class);

		tiles.add(TileEntityChristmasPresent.class);
		tiles.add(TileEntityLight.class);

		// Crafting Pillars
		blocks.put(blockBasePillar = (new BlockPillarExtend(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("extendPillar"), null);
		blocks.put(blockShowOffPillar = (new BlockPillarDisplay(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("showOffPillar"), null);
		blocks.put(blockCraftingPillar = (new BlockPillarCrafting(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("craftingPillar"), null);
		blocks.put(blockFurnacePillar = (new BlockPillarFurnace(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("furnacePillar"), null);
		blocks.put(blockAnvilPillar = (new BlockPillarAnvil(Material.anvil)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("anvilPillar"), null);
		blocks.put(blockTankPillar = (new BlockPillarTank(Material.glass)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("tankPillar"), null);
		blocks.put(blockBrewingPillar = (new BlockPillarBrewing(Material.iron)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("brewingPillar"), null);
		blocks.put(blockDiskPlayerPillar = (new BlockPillarTurntable(Material.iron)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("diskPillar"), null);
		blocks.put(blockFreezerPillar = (new BlockPillarFreezer(Material.glass)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("freezerPillar"), null);
		blocks.put(blockPotPillar = (new BlockPillarPot(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("potPillar"), null);
		blocks.put(blockSentryPillar = (new BlockPillarSentry(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("sentryPillar"), null);
		blocks.put(blockTrashPillar = (new BlockPillarTrash(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("trashPillar"), null);
		blocks.put(blockPumpPillar = (new BlockPillarPump(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("pumpPillar"), null);

		if (winter) {
			blocks.put(blockChristmasLeaves = (new ChristmasLeavesBlock(Material.leaves)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("xmasLeaves"), null);
			blocks.put(blockChristmasTreeSapling = (new ChristmasTreeSapling()).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("sapling").setBlockName("ChristmasTreeSapling"), null);
			blocks.put(blockChristmasPresent = (new ChristmasPresentBlock(Material.cloth)).setHardness(1.0F).setStepSound(Block.soundTypeCloth).setBlockName("present"), null);
			blocks.put(blockChristmasLight = (new ChristmasLightBlock(Material.glass)).setHardness(0.1F).setStepSound(Block.soundTypeGlass).setBlockName("christmas_light"), null);
		}

		items.add(itemDiscElysium = new PillarRecord("UranusParadise").setUnlocalizedName("record").setTextureName("craftingpillars:ElysiumDisk"));
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

		tileRenderers.put(TileEntityExtendPillar.class, new RenderExtendPillar());
		tileRenderers.put(TileEntityShowOffPillar.class, new RenderShowOffPillar());
		tileRenderers.put(TileEntityCraftingPillar.class, new RenderCraftingPillar());
		tileRenderers.put(TileEntityFurnacePillar.class, new RenderFurnacePillar());
		tileRenderers.put(TileEntityAnvilPillar.class, new RenderAnvilPillar());
		tileRenderers.put(TileEntityTankPillar.class, new RenderTankPillar());
		tileRenderers.put(TileEntityBrewingPillar.class, new RenderBrewingPillar());
		tileRenderers.put(TileEntityDiskPlayerPillar.class, new RenderDiskPillar());
		tileRenderers.put(TileEntityFreezerPillar.class, new RenderFreezerPillar());
		tileRenderers.put(TileEntityPotPillar.class, new RenderPotPillar());
		tileRenderers.put(TileEntitySentryPillar.class, new RenderSentryPillar());
		tileRenderers.put(TileEntityTrashPillar.class, new RenderTrashPillar());
		tileRenderers.put(TileEntityPumpPillar.class, new RenderPumpPillar());

		tileRenderers.put(TileEntityChristmasPresent.class, new RenderPresent());
		tileRenderers.put(TileEntityLight.class, new RenderLight());

		RenderingRegistry.registerBlockHandler(new RenderExtendPillar());
		RenderingRegistry.registerBlockHandler(new RenderShowOffPillar());
		RenderingRegistry.registerBlockHandler(new RenderCraftingPillar());
		RenderingRegistry.registerBlockHandler(new RenderFurnacePillar());
		RenderingRegistry.registerBlockHandler(new RenderAnvilPillar());
		RenderingRegistry.registerBlockHandler(new RenderTankPillar());
		RenderingRegistry.registerBlockHandler(new RenderBrewingPillar());
		RenderingRegistry.registerBlockHandler(new RenderDiskPillar());
		RenderingRegistry.registerBlockHandler(new RenderFreezerPillar());
		RenderingRegistry.registerBlockHandler(new RenderPotPillar());
		RenderingRegistry.registerBlockHandler(new RenderSentryPillar());
		RenderingRegistry.registerBlockHandler(new RenderTrashPillar());
		RenderingRegistry.registerBlockHandler(new RenderPumpPillar());

		if (winter) {
			RenderingRegistry.registerBlockHandler(new RenderChristmasLeaves());
			RenderingRegistry.registerBlockHandler(new RenderPresent());
			RenderingRegistry.registerBlockHandler(new RenderLight());
		}
	}

	public static void registerHandlers() {
		MinecraftForge.EVENT_BUS.register(new PillarEventHandler());
		FMLCommonHandler.instance().bus().register(new PillarEventHandler());
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

		addRecipes();
		registerHandlers();
		addAchievementsAndCreativeTab();

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

	public static void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(blockBasePillar), new Object[] { "SSS", " S ", "SSS", Character.valueOf('S'), Blocks.stone });
		GameRegistry.addRecipe(new ItemStack(blockFreezerPillar), new Object[] { "SSS", "GPG", "SSS", Character.valueOf('S'), Blocks.snow, Character.valueOf('P'), blockBasePillar, Character.valueOf('G'), Blocks.glass_pane});
		GameRegistry.addShapelessRecipe(new ItemStack(blockShowOffPillar), new ItemStack(Items.item_frame), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockCraftingPillar), new ItemStack(Blocks.crafting_table), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockFurnacePillar), new ItemStack(Blocks.furnace), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockBrewingPillar), new ItemStack(Items.brewing_stand), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockDiskPlayerPillar), new ItemStack(Blocks.jukebox), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockSentryPillar), new ItemStack(Blocks.dispenser), new ItemStack(blockBasePillar));
		GameRegistry.addShapelessRecipe(new ItemStack(blockAnvilPillar), new ItemStack(Blocks.anvil), new ItemStack(blockBasePillar));
		GameRegistry.addRecipe(new ItemStack(blockPotPillar), new Object[] { "S", "F", "P", Character.valueOf('S'), Blocks.dirt, Character.valueOf('P'), blockBasePillar , Character.valueOf('F'), Items.flower_pot});
		GameRegistry.addRecipe(new ItemStack(blockChristmasLight, 3), new Object[] { "G", "L", Character.valueOf('G'), Items.gold_ingot, Character.valueOf('L'), Blocks.glowstone});
		GameRegistry.addRecipe(new ItemStack(blockTrashPillar, 1), new Object[] { "SSS", "SLS", "SSS", Character.valueOf('S'), Blocks.stone, Character.valueOf('L'), Items.ender_pearl});
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(blockChristmasTreeSapling), "treeSapling", new ItemStack(blockChristmasLight)));
	}

	public static void addAchievementsAndCreativeTab() {
		achievementGettingStarted = new Achievement("achievement.gettingstarted", "gettingstarted", 0, 0, blockBasePillar, null).registerStat();

		achievementRecursion = new Achievement("achievement.recursion", "recursion", 1, 1, blockCraftingPillar, achievementGettingStarted).registerStat();
		achievementShowoff = new Achievement("achievement.showoff", "showoff", 3, 1, blockShowOffPillar, achievementRecursion).registerStat();
		achievementRecursion3 = new Achievement("achievement.recursion3", "recursion3", 5, 1, blockChristmasPresent, achievementShowoff).registerStat();

		achievementCompressingLiquids = new Achievement("achievement.liquids", "liquids", 1, 2, blockFreezerPillar, achievementGettingStarted).registerStat();

		achievementChristmas = new Achievement("achievement.christmaspillar", "christmaspillar", 0, 4, blockChristmasTreeSapling, null).setSpecial().initIndependentStat().registerStat();
		achievementDisc = new Achievement("achievement.elysiandisc", "elysiandisc", 4, 4, itemDiscElysium, achievementChristmas).setSpecial().registerStat();

		if (winter)
			achievementPage = new AchievementPage(Magistics.modid, achievementGettingStarted, achievementRecursion, achievementShowoff, achievementCompressingLiquids, achievementRecursion3, achievementChristmas, achievementDisc);
		else
			achievementPage = new AchievementPage(Magistics.modid, achievementGettingStarted, achievementRecursion, achievementShowoff, achievementCompressingLiquids, achievementRecursion3, achievementDisc);

		AchievementPage.registerAchievementPage(achievementPage);
	}

	public static void writeOreDictionary() {
		OreDictionary.registerOre("record", itemDiscElysium);
		OreDictionary.registerOre("treeSapling", blockChristmasTreeSapling);
		OreDictionary.registerOre("treeLeaves",  blockChristmasLeaves);
	}

	public static void initAPI() {
		CraftingPillarAPI.addDiskTexture(itemDiscElysium, "craftingpillars:textures/models/disk_elysium.png");

		CraftingPillarAPI.addDiskTexture(Items.record_13, "craftingpillars:textures/models/disk_13.png");
		CraftingPillarAPI.addDiskTexture(Items.record_cat, "craftingpillars:textures/models/disk_cat.png");
		CraftingPillarAPI.addDiskTexture(Items.record_blocks, "craftingpillars:textures/models/disk_blocks.png");
		CraftingPillarAPI.addDiskTexture(Items.record_chirp, "craftingpillars:textures/models/disk_chirp.png");
		CraftingPillarAPI.addDiskTexture(Items.record_far, "craftingpillars:textures/models/disk_far.png");
		CraftingPillarAPI.addDiskTexture(Items.record_mall, "craftingpillars:textures/models/disk_mall.png");
		CraftingPillarAPI.addDiskTexture(Items.record_mellohi, "craftingpillars:textures/models/disk_mellohi.png");
		CraftingPillarAPI.addDiskTexture(Items.record_stal, "craftingpillars:textures/models/disk_stal.png");
		CraftingPillarAPI.addDiskTexture(Items.record_strad, "craftingpillars:textures/models/disk_strad.png");
		CraftingPillarAPI.addDiskTexture(Items.record_ward, "craftingpillars:textures/models/disk_ward.png");
		CraftingPillarAPI.addDiskTexture(Items.record_11, "craftingpillars:textures/models/disk_11.png");
		CraftingPillarAPI.addDiskTexture(Items.record_wait, "craftingpillars:textures/models/disk_wait.png");

		SentryBehaviors.add(Items.arrow, new SentryBehaviorArrow());
		SentryBehaviors.add(Items.snowball, new SentryBehaviorSnowball());
		SentryBehaviors.add(Items.fire_charge, new SentryBehaviorFireball());
		SentryBehaviors.add(Items.potionitem, new SentryBehaviorPotion());
		SentryBehaviors.add(Items.egg, new SentryBehaviorEgg());
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