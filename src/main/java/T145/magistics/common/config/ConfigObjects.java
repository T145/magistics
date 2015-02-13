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
import T145.magistics.client.renderers.craftingpillars.RenderBrewingPillar;
import T145.magistics.client.renderers.craftingpillars.RenderChristmasLeaves;
import T145.magistics.client.renderers.craftingpillars.RenderChristmasLight;
import T145.magistics.client.renderers.craftingpillars.RenderChristmasPresent;
import T145.magistics.client.renderers.craftingpillars.RenderCraftingPillar;
import T145.magistics.client.renderers.craftingpillars.RenderDisplayPillar;
import T145.magistics.client.renderers.craftingpillars.RenderExtendPillar;
import T145.magistics.client.renderers.craftingpillars.RenderFreezerPillar;
import T145.magistics.client.renderers.craftingpillars.RenderFurnacePillar;
import T145.magistics.client.renderers.craftingpillars.RenderPotPillar;
import T145.magistics.client.renderers.craftingpillars.RenderSentryPillar;
import T145.magistics.client.renderers.craftingpillars.RenderTankPillar;
import T145.magistics.client.renderers.craftingpillars.RenderTrashPillar;
import T145.magistics.client.renderers.craftingpillars.RenderTurntablePillar;
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
import T145.magistics.common.blocks.craftingpillars.BlockChristmasLeaves;
import T145.magistics.common.blocks.craftingpillars.BlockChristmasLight;
import T145.magistics.common.blocks.craftingpillars.BlockChristmasPresent;
import T145.magistics.common.blocks.craftingpillars.BlockChristmasTreeSapling;
import T145.magistics.common.blocks.craftingpillars.BlockPillarBrewing;
import T145.magistics.common.blocks.craftingpillars.BlockPillarCrafting;
import T145.magistics.common.blocks.craftingpillars.BlockPillarDisplay;
import T145.magistics.common.blocks.craftingpillars.BlockPillarExtend;
import T145.magistics.common.blocks.craftingpillars.BlockPillarFreezer;
import T145.magistics.common.blocks.craftingpillars.BlockPillarFurnace;
import T145.magistics.common.blocks.craftingpillars.BlockPillarPot;
import T145.magistics.common.blocks.craftingpillars.BlockPillarSentry;
import T145.magistics.common.blocks.craftingpillars.BlockPillarTank;
import T145.magistics.common.blocks.craftingpillars.BlockPillarTrash;
import T145.magistics.common.blocks.craftingpillars.BlockPillarTurntable;
import T145.magistics.common.blocks.craftingpillars.sentry.SentryBehaviorArrow;
import T145.magistics.common.blocks.craftingpillars.sentry.SentryBehaviorEgg;
import T145.magistics.common.blocks.craftingpillars.sentry.SentryBehaviorFireball;
import T145.magistics.common.blocks.craftingpillars.sentry.SentryBehaviorPotion;
import T145.magistics.common.blocks.craftingpillars.sentry.SentryBehaviorSnowball;
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
import T145.magistics.common.tiles.craftingpillars.TileChristmasLight;
import T145.magistics.common.tiles.craftingpillars.TileChristmasPresent;
import T145.magistics.common.tiles.craftingpillars.TilePillarBrewing;
import T145.magistics.common.tiles.craftingpillars.TilePillarCrafting;
import T145.magistics.common.tiles.craftingpillars.TilePillarDisplay;
import T145.magistics.common.tiles.craftingpillars.TilePillarExtend;
import T145.magistics.common.tiles.craftingpillars.TilePillarFreezer;
import T145.magistics.common.tiles.craftingpillars.TilePillarFurnace;
import T145.magistics.common.tiles.craftingpillars.TilePillarPot;
import T145.magistics.common.tiles.craftingpillars.TilePillarSentry;
import T145.magistics.common.tiles.craftingpillars.TilePillarTank;
import T145.magistics.common.tiles.craftingpillars.TilePillarTrash;
import T145.magistics.common.tiles.craftingpillars.TilePillarTurntable;

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

	public static Block blockBasePillar, blockDisplayPillar, blockCraftingPillar, blockFurnacePillar, blockAnvilPillar, blockTankPillar, blockBrewingPillar, blockDiskPlayerPillar, blockFreezerPillar, blockPotPillar, blockSentryPillar, blockTrashPillar, blockPumpPillar;

	public static Block blockChristmasLeaves,  blockChristmasTreeSapling,  blockChristmasPresent, blockChristmasLight;

	public static Item itemDiscElysium;

	public static ItemStack[] present_loot = new ItemStack[] {
		new ItemStack(blockChristmasPresent, 1, 0),
		new ItemStack(blockChristmasPresent, 1, 1),
		new ItemStack(blockCraftingPillar, 1, 0),
		new ItemStack(blockAnvilPillar, 1, 0),
		new ItemStack(blockFurnacePillar, 1, 0),
		new ItemStack(blockFreezerPillar, 1, 0),
		new ItemStack(blockDisplayPillar, 1, 0),
		new ItemStack(blockPotPillar, 1, 0),
		new ItemStack(blockBrewingPillar, 1, 0),
		new ItemStack(blockDiskPlayerPillar, 1, 0),
		new ItemStack(blockSentryPillar, 1, 0),
		new ItemStack(blockTankPillar, 1, 0),
		new ItemStack(blockTrashPillar, 1, 0),
		new ItemStack(blockBasePillar, 3, 0),
		new ItemStack(itemDiscElysium, 1, 0)
	};

	public static AchievementPage achievementPage;
	public static Achievement achievementGettingStarted, achievementChristmas,  achievementRecursion,  achievementCompressingLiquids, achievementShowoff, achievementDisc, achievementRecursion3;

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

		tiles.add(TilePillarExtend.class);
		tiles.add(TilePillarDisplay.class);
		tiles.add(TilePillarCrafting.class);
		tiles.add(TilePillarFurnace.class);
		tiles.add(TilePillarTank.class);
		tiles.add(TilePillarBrewing.class);
		tiles.add(TilePillarTurntable.class);
		tiles.add(TilePillarFreezer.class);
		tiles.add(TilePillarPot.class);
		tiles.add(TilePillarSentry.class);
		tiles.add(TilePillarTrash.class);

		blocks.put(blockBasePillar = (new BlockPillarExtend(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("extendPillar"), null);
		blocks.put(blockDisplayPillar = (new BlockPillarDisplay(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("displayPillar"), null);
		blocks.put(blockCraftingPillar = (new BlockPillarCrafting(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("craftingPillar"), null);
		blocks.put(blockFurnacePillar = (new BlockPillarFurnace(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("furnacePillar"), null);
		blocks.put(blockTankPillar = (new BlockPillarTank(Material.glass)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("tankPillar"), null);
		blocks.put(blockBrewingPillar = (new BlockPillarBrewing(Material.iron)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("brewingPillar"), null);
		blocks.put(blockDiskPlayerPillar = (new BlockPillarTurntable(Material.iron)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("turntablePillar"), null);
		blocks.put(blockFreezerPillar = (new BlockPillarFreezer(Material.glass)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("freezerPillar"), null);
		blocks.put(blockPotPillar = (new BlockPillarPot(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("potPillar"), null);
		blocks.put(blockSentryPillar = (new BlockPillarSentry(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("sentryPillar"), null);
		blocks.put(blockTrashPillar = (new BlockPillarTrash(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("trashPillar"), null);

		if (winter) {
			tiles.add(TileChristmasPresent.class);
			tiles.add(TileChristmasLight.class);

			blocks.put(blockChristmasLeaves = (new BlockChristmasLeaves(Material.leaves)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("christmas_tree_leaves"), null);
			blocks.put(blockChristmasTreeSapling = (new BlockChristmasTreeSapling()).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("sapling").setBlockName("christmas_tree_sapling"), null);
			blocks.put(blockChristmasPresent = (new BlockChristmasPresent(Material.cloth)).setHardness(1.0F).setStepSound(Block.soundTypeCloth).setBlockName("present"), null);
			blocks.put(blockChristmasLight = (new BlockChristmasLight(Material.glass)).setHardness(0.1F).setStepSound(Block.soundTypeGlass).setBlockName("christmas_light"), null);
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

		RenderExtendPillar renderExtendPillar = new RenderExtendPillar("extendPillar", "frozenExtendPillar");
		tileRenderers.put(TilePillarExtend.class, renderExtendPillar);
		blockRenderers.add(renderExtendPillar);

		RenderDisplayPillar renderDisplayPillar = new RenderDisplayPillar("displayPillar", "displayPillarFrozen");
		tileRenderers.put(TilePillarDisplay.class, renderDisplayPillar);
		blockRenderers.add(renderDisplayPillar);

		RenderCraftingPillar renderCraftingPillar = new RenderCraftingPillar("craftingPillar", "craftingPillarFrozen");
		tileRenderers.put(TilePillarCrafting.class, renderCraftingPillar);
		blockRenderers.add(renderCraftingPillar);

		RenderFurnacePillar renderFurnacePillar = new RenderFurnacePillar("furnacePillar", "frozenFurnacePillar");
		tileRenderers.put(TilePillarFurnace.class, renderFurnacePillar);
		blockRenderers.add(renderFurnacePillar);

		RenderTankPillar renderTankPillar = new RenderTankPillar("freezerPillar", "freezerPillarFrozen");
		tileRenderers.put(TilePillarTank.class, renderTankPillar);
		blockRenderers.add(renderTankPillar);

		RenderBrewingPillar renderBrewingPillar = new RenderBrewingPillar("brewingPillar", "brewingPillarFrozen");
		tileRenderers.put(TilePillarBrewing.class, renderBrewingPillar);
		blockRenderers.add(renderBrewingPillar);

		RenderTurntablePillar renderTurntablePillar = new RenderTurntablePillar("diskPillar", "diskPillarFrozen");
		tileRenderers.put(TilePillarTurntable.class, renderTurntablePillar);
		blockRenderers.add(renderTurntablePillar);

		RenderFreezerPillar renderFreezerPillar = new RenderFreezerPillar("freezerPillarFrozen");
		tileRenderers.put(TilePillarFreezer.class, renderFreezerPillar);
		blockRenderers.add(renderFreezerPillar);

		RenderPotPillar renderPotPillar = new RenderPotPillar("displayPillar", "frozenDisplayPillar");
		tileRenderers.put(TilePillarPot.class, renderPotPillar);
		blockRenderers.add(renderPotPillar);

		RenderSentryPillar renderSentryPillar = new RenderSentryPillar("sentryPillar", "frozenSentryPillar");
		tileRenderers.put(TilePillarSentry.class, renderSentryPillar);
		blockRenderers.add(renderSentryPillar);

		RenderTrashPillar renderTrashPillar = new RenderTrashPillar("displayPillar", "frozenDisplayPillar");
		tileRenderers.put(TilePillarTrash.class, renderTrashPillar);
		blockRenderers.add(renderTrashPillar);

		RenderChristmasPresent renderChristmasPresent = new RenderChristmasPresent();
		RenderChristmasLight renderChristmasLight = new RenderChristmasLight();

		if (winter) {
			blockRenderers.add(new RenderChristmasLeaves());
			tileRenderers.put(TileChristmasPresent.class, renderChristmasPresent);
			blockRenderers.add(renderChristmasPresent);
			tileRenderers.put(TileChristmasLight.class, renderChristmasLight);
			blockRenderers.add(renderChristmasLight);
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
		GameRegistry.addShapelessRecipe(new ItemStack(blockDisplayPillar), new ItemStack(Items.item_frame), new ItemStack(blockBasePillar));
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
		achievementShowoff = new Achievement("achievement.showoff", "showoff", 3, 1, blockDisplayPillar, achievementRecursion).registerStat();
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

		BlockPillarSentry.addBehavior(Items.arrow, new SentryBehaviorArrow());
		BlockPillarSentry.addBehavior(Items.snowball, new SentryBehaviorSnowball());
		BlockPillarSentry.addBehavior(Items.fire_charge, new SentryBehaviorFireball());
		BlockPillarSentry.addBehavior(Items.potionitem, new SentryBehaviorPotion());
		BlockPillarSentry.addBehavior(Items.egg, new SentryBehaviorEgg());
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