package T145.magistics.common.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thaumcraft.api.ThaumcraftApi;
import T145.magistics.api.CraftingPillarAPI;
import T145.magistics.api.blocks.BlockMagisticsItem;
import T145.magistics.api.client.renderers.block.ChestItemRenderer;
import T145.magistics.api.client.renderers.block.ChestRenderer;
import T145.magistics.api.sentry.SentryBehaviorArrow;
import T145.magistics.api.sentry.SentryBehaviorEgg;
import T145.magistics.api.sentry.SentryBehaviorFireball;
import T145.magistics.api.sentry.SentryBehaviorPotion;
import T145.magistics.api.sentry.SentryBehaviorRegistry;
import T145.magistics.api.sentry.SentryBehaviorSnowball;
import T145.magistics.client.lib.TextureHelper;
import T145.magistics.client.renderers.block.BlockAestheticStructureRenderer;
import T145.magistics.client.renderers.pillars.RenderBrewingPillar;
import T145.magistics.client.renderers.pillars.RenderCraftingPillar;
import T145.magistics.client.renderers.pillars.RenderDisplayPillar;
import T145.magistics.client.renderers.pillars.RenderExtendPillar;
import T145.magistics.client.renderers.pillars.RenderFreezerPillar;
import T145.magistics.client.renderers.pillars.RenderFurnacePillar;
import T145.magistics.client.renderers.pillars.RenderMithrilPillar;
import T145.magistics.client.renderers.pillars.RenderPotPillar;
import T145.magistics.client.renderers.pillars.RenderSentryPillar;
import T145.magistics.client.renderers.pillars.RenderTankPillar;
import T145.magistics.client.renderers.pillars.RenderTrashPillar;
import T145.magistics.client.renderers.pillars.RenderTurntablePillar;
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
import T145.magistics.common.blocks.BlockMithrilOre;
import T145.magistics.common.blocks.BlockMithrilStorage;
import T145.magistics.common.blocks.BlockMithrilStorageItem;
import T145.magistics.common.blocks.BlockSortingChestHungry;
import T145.magistics.common.blocks.BlockSortingChestHungryAlchemical;
import T145.magistics.common.blocks.BlockSortingChestHungryAlchemicalItem;
import T145.magistics.common.blocks.BlockSortingChestHungryMetal;
import T145.magistics.common.blocks.pillars.BlockMithrilPillar;
import T145.magistics.common.blocks.pillars.BlockMithrilPillarItem;
import T145.magistics.common.blocks.pillars.BlockPillarBrewing;
import T145.magistics.common.blocks.pillars.BlockPillarCrafting;
import T145.magistics.common.blocks.pillars.BlockPillarDisplay;
import T145.magistics.common.blocks.pillars.BlockPillarExtend;
import T145.magistics.common.blocks.pillars.BlockPillarFreezer;
import T145.magistics.common.blocks.pillars.BlockPillarFurnace;
import T145.magistics.common.blocks.pillars.BlockPillarPot;
import T145.magistics.common.blocks.pillars.BlockPillarSentry;
import T145.magistics.common.blocks.pillars.BlockPillarTank;
import T145.magistics.common.blocks.pillars.BlockPillarTrash;
import T145.magistics.common.blocks.pillars.BlockPillarTurntable;
import T145.magistics.common.items.ElysiumRecord;
import T145.magistics.common.items.ItemResources;
import T145.magistics.common.items.armor.ItemCruelMask;
import T145.magistics.common.items.armor.MithrilArmor;
import T145.magistics.common.items.baubles.EnderNecklace;
import T145.magistics.common.items.baubles.ItemAmuletDismay;
import T145.magistics.common.items.baubles.ItemAmuletLife;
import T145.magistics.common.items.baubles.ItemBeltCleansing;
import T145.magistics.common.items.baubles.ItemBeltVigor;
import T145.magistics.common.items.baubles.ItemRingSouls;
import T145.magistics.common.items.baubles.MithrilRing;
import T145.magistics.common.items.baubles.MithrilWitherRing;
import T145.magistics.common.items.equipment.MithrilAxe;
import T145.magistics.common.items.equipment.MithrilBow;
import T145.magistics.common.items.equipment.MithrilHoe;
import T145.magistics.common.items.equipment.MithrilShovel;
import T145.magistics.common.items.equipment.MithrilSword;
import T145.magistics.common.items.equipment.MthrilPickaxe;
import T145.magistics.common.items.relics.ItemDawnstone;
import T145.magistics.common.items.relics.MithrilFlute;
import T145.magistics.common.lib.events.EventHandlerCraftingPillars;
import T145.magistics.common.lib.events.EventHandlerMithril;
import T145.magistics.common.lib.world.MagisticsWorldGenerator;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryAlchemical;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import T145.magistics.common.tiles.TileSortingChestHungry;
import T145.magistics.common.tiles.TileSortingChestHungryAlchemical;
import T145.magistics.common.tiles.TileSortingChestHungryMetal;
import T145.magistics.common.tiles.pillars.TilePillarBrewing;
import T145.magistics.common.tiles.pillars.TilePillarCrafting;
import T145.magistics.common.tiles.pillars.TilePillarDisplay;
import T145.magistics.common.tiles.pillars.TilePillarExtend;
import T145.magistics.common.tiles.pillars.TilePillarFreezer;
import T145.magistics.common.tiles.pillars.TilePillarFurnace;
import T145.magistics.common.tiles.pillars.TilePillarMithril;
import T145.magistics.common.tiles.pillars.TilePillarPot;
import T145.magistics.common.tiles.pillars.TilePillarSentry;
import T145.magistics.common.tiles.pillars.TilePillarTank;
import T145.magistics.common.tiles.pillars.TilePillarTrash;
import T145.magistics.common.tiles.pillars.TilePillarTurntable;

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

	public static Item itemDiscElysium;
	public static Block blockBasePillar, blockDisplayPillar, blockCraftingPillar, blockFurnacePillar, blockAnvilPillar, blockTankPillar, blockBrewingPillar, blockDiskPlayerPillar, blockFreezerPillar, blockPotPillar, blockSentryPillar, blockTrashPillar, blockPumpPillar;
	public static Block blockChristmasLeaves, blockChristmasTreeSapling, blockChristmasPresent, blockChristmasLight;

	public static Item itemMithrilSword, itemMithrilSpade, itemMithrilPickaxe, itemMithrilAxe, itemMithrilHoe, itemMithrilBow, itemMithrilIngot, itemMithrilNugget, itemQuartzRod, itemMithrilChest, itemMithrilHelmet, itemMithrilLeggings, itemMithrilBoots, itemEnderNecklace, itemMithrilRing, itemWitherRing, itemFlute;
	public static Block blockMithrilOre, blockMithrilStorage, blockMithrilPillar;

	public static int mithrilArmorID = RenderingRegistry.addNewArmourRendererPrefix("mithril");
	public static ToolMaterial ToolMaterialMithril = EnumHelper.addToolMaterial("MITHRIL", 3, 2084, 12F, 10F, 22);
	public static ArmorMaterial ArmorMaterialMithril = EnumHelper.addArmorMaterial("MITHRIL", 66, new int[] { 3, 8, 6, 3 }, 25);

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
		items.add(itemAmuletDismay = new ItemAmuletDismay().setTextureName("magistics:bauble_amulet_dismay").setUnlocalizedName("bauble.amulet_dismay"));
		items.add(itemAmuletLife = new ItemAmuletLife().setTextureName("magistics:bauble_amulet_life").setUnlocalizedName("bauble.amulet_life"));
		items.add(itemBeltCleansing = new ItemBeltCleansing().setTextureName("magistics:bauble_belt_cleansing").setUnlocalizedName("bauble.belt_cleansing"));
		items.add(itemBeltVigor = new ItemBeltVigor().setTextureName("magistics:bauble_belt_vigor").setUnlocalizedName("bauble.belt_vigor"));
		items.add(itemRingSouls = new ItemRingSouls().setTextureName("magistics:bauble_ring_souls").setUnlocalizedName("bauble.ring_souls"));
		items.add(itemCruelMask = new ItemCruelMask(ThaumcraftApi.armorMatThaumium, 2, 0).setMaxDamage(100).setMaxStackSize(1).setTextureName("magistics:cruel_mask").setUnlocalizedName("cruel_mask"));
		items.add(itemDawnstone = new ItemDawnstone().setTextureName("magistics:dawnstone").setUnlocalizedName("dawnstone"));

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

		items.add(itemDiscElysium = new ElysiumRecord("UranusParadise").setUnlocalizedName("record").setTextureName("magistics:elysium_disk"));

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

		blocks.put(blockBasePillar = (new BlockPillarExtend(Material.rock)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("extendPillar"), null);
		blocks.put(blockDisplayPillar = (new BlockPillarDisplay(Material.rock)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("displayPillar"), null);
		blocks.put(blockCraftingPillar = (new BlockPillarCrafting(Material.rock)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("craftingPillar"), null);
		blocks.put(blockFurnacePillar = (new BlockPillarFurnace(Material.rock)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("furnacePillar"), null);
		blocks.put(blockTankPillar = (new BlockPillarTank(Material.glass)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("tankPillar"), null);
		blocks.put(blockBrewingPillar = (new BlockPillarBrewing(Material.iron)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("brewingPillar"), null);
		blocks.put(blockDiskPlayerPillar = (new BlockPillarTurntable(Material.iron)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("turntablePillar"), null);
		blocks.put(blockFreezerPillar = (new BlockPillarFreezer(Material.glass)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("freezerPillar"), null);
		blocks.put(blockPotPillar = (new BlockPillarPot(Material.rock)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("potPillar"), null);
		blocks.put(blockSentryPillar = (new BlockPillarSentry(Material.rock)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("sentryPillar"), null);
		blocks.put(blockTrashPillar = (new BlockPillarTrash(Material.rock)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("trashPillar"), null);

		items.add(itemMithrilSword = new MithrilSword(ToolMaterialMithril).setUnlocalizedName("mithril_sword").setTextureName("magistics:mithril/sword"));
		items.add(itemMithrilPickaxe = new MthrilPickaxe(ToolMaterialMithril).setUnlocalizedName("mithril_pickaxe").setTextureName("magistics:mithril/pickaxe"));
		items.add(itemMithrilAxe = new MithrilAxe(ToolMaterialMithril).setUnlocalizedName("mithril_axe").setTextureName("magistics:mithril/axe"));
		items.add(itemMithrilSpade = new MithrilShovel(ToolMaterialMithril).setUnlocalizedName("mithril_spade").setTextureName("magistics:mithril/spade"));
		items.add(itemMithrilHoe = new MithrilHoe(ToolMaterialMithril).setUnlocalizedName("mithril_hoe").setTextureName("magistics:mithril/hoe"));
		items.add(itemMithrilBow = new MithrilBow().setUnlocalizedName("mithril_mithrilBow"));
		items.add(itemMithrilIngot = new Item().setUnlocalizedName("mithril_ingot").setTextureName("magistics:mithril/ingot"));
		items.add(itemMithrilNugget = new Item().setUnlocalizedName("mithril_chunk").setTextureName("magistics:mithril/nugget"));
		items.add(itemQuartzRod = new Item().setUnlocalizedName("quartz_rod").setTextureName("magistics:mithril/quartz_rod"));
		items.add(itemMithrilHelmet = new MithrilArmor(ArmorMaterialMithril, mithrilArmorID, 0).setUnlocalizedName("mithril_helmet").setTextureName("magistics:mithril/helmet"));
		items.add(itemMithrilChest = new MithrilArmor(ArmorMaterialMithril, mithrilArmorID, 1).setUnlocalizedName("mithril_chestplate").setTextureName("magistics:mithril/chestplate"));
		items.add(itemMithrilLeggings = new MithrilArmor(ArmorMaterialMithril, mithrilArmorID, 2).setUnlocalizedName("mithril_leggings").setTextureName("magistics:mithril/leggings"));
		items.add(itemMithrilBoots = new MithrilArmor(ArmorMaterialMithril, mithrilArmorID, 3).setUnlocalizedName("mithril_boots").setTextureName("magistics:mithril/boots"));
		items.add(itemEnderNecklace = new EnderNecklace().setUnlocalizedName("ender_amulet").setTextureName("magistics:mithril/ender_necklace"));
		items.add(itemMithrilRing = new MithrilRing().setUnlocalizedName("mithril_ring").setTextureName("magistics:mithril/ring1"));
		items.add(itemWitherRing = new MithrilWitherRing().setUnlocalizedName("mithril_wither_ring").setTextureName("magistics:mithril/ring2"));
		items.add(itemFlute = new MithrilFlute().setUnlocalizedName("mithril_flute").setTextureName("magistics:mithril/flute"));

		tiles.add(TilePillarMithril.class);

		blocks.put(blockMithrilOre = new BlockMithrilOre(Material.rock).setHardness(50F).setResistance(2000F).setStepSound(Block.soundTypeStone).setBlockName("mithril_ore"), null);
		blocks.put(blockMithrilStorage = new BlockMithrilStorage(Material.iron).setHardness(5F).setResistance(200F).setStepSound(Block.soundTypeMetal).setBlockName("mithril_block").setBlockTextureName("mithril:mithril_block"), (Class)BlockMithrilStorageItem.class);
		blocks.put(blockMithrilPillar = new BlockMithrilPillar(Material.iron).setHardness(5F).setResistance(200F).setStepSound(Block.soundTypeMetal).setBlockName("mithril_pillar"), (Class)BlockMithrilPillarItem.class);
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

		RenderExtendPillar renderExtendPillar = new RenderExtendPillar("pillar_extend", "pillar_frozen_extend");
		tileRenderers.put(TilePillarExtend.class, renderExtendPillar);
		blockRenderers.add(renderExtendPillar);

		RenderDisplayPillar renderDisplayPillar = new RenderDisplayPillar("pillar_display", "pillar_frozen_display");
		tileRenderers.put(TilePillarDisplay.class, renderDisplayPillar);
		blockRenderers.add(renderDisplayPillar);

		RenderCraftingPillar renderCraftingPillar = new RenderCraftingPillar("pillar_crafting", "pillar_frozen_crafting");
		tileRenderers.put(TilePillarCrafting.class, renderCraftingPillar);
		blockRenderers.add(renderCraftingPillar);

		RenderFurnacePillar renderFurnacePillar = new RenderFurnacePillar("pillar_furnace", "pillar_frozen_furnace");
		tileRenderers.put(TilePillarFurnace.class, renderFurnacePillar);
		blockRenderers.add(renderFurnacePillar);

		RenderTankPillar renderTankPillar = new RenderTankPillar("pillar_freezer", "pillar_frozen_freezer");
		tileRenderers.put(TilePillarTank.class, renderTankPillar);
		blockRenderers.add(renderTankPillar);

		RenderBrewingPillar renderBrewingPillar = new RenderBrewingPillar("pillar_brewing", "pillar_frozen_brewing");
		tileRenderers.put(TilePillarBrewing.class, renderBrewingPillar);
		blockRenderers.add(renderBrewingPillar);

		RenderTurntablePillar renderTurntablePillar = new RenderTurntablePillar("pillar_turntable", "pillar_frozen_turntable");
		tileRenderers.put(TilePillarTurntable.class, renderTurntablePillar);
		blockRenderers.add(renderTurntablePillar);

		RenderFreezerPillar renderFreezerPillar = new RenderFreezerPillar("pillar_frozen_freezer");
		tileRenderers.put(TilePillarFreezer.class, renderFreezerPillar);
		blockRenderers.add(renderFreezerPillar);

		RenderPotPillar renderPotPillar = new RenderPotPillar("pillar_display", "pillar_frozen_display");
		tileRenderers.put(TilePillarPot.class, renderPotPillar);
		blockRenderers.add(renderPotPillar);

		RenderSentryPillar renderSentryPillar = new RenderSentryPillar("pillar_sentry", "pillar_frozen_sentry");
		tileRenderers.put(TilePillarSentry.class, renderSentryPillar);
		blockRenderers.add(renderSentryPillar);

		RenderTrashPillar renderTrashPillar = new RenderTrashPillar("pillar_display", "pillar_display_frozen");
		tileRenderers.put(TilePillarTrash.class, renderTrashPillar);
		blockRenderers.add(renderTrashPillar);

		tileRenderers.put(TilePillarMithril.class, new RenderMithrilPillar());
		blockRenderers.add(new RenderMithrilPillar());
	}

	public static void registerHandlers() {
		MinecraftForge.EVENT_BUS.register(new EventHandlerCraftingPillars());
		MinecraftForge.EVENT_BUS.register(new EventHandlerMithril());
		FMLCommonHandler.instance().bus().register(new EventHandlerCraftingPillars());
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

		writeOreDictionary();
		addRecipes();

		GameRegistry.registerWorldGenerator(new MagisticsWorldGenerator(), 0);

		registerHandlers();
		addAchievements();

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

		ItemStack mithrilSword = new ItemStack(itemMithrilSword);
		mithrilSword.addEnchantment(Enchantment.knockback, 3);
		mithrilSword.addEnchantment(Enchantment.looting, 2);
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilSword, new Object[] { "L M", " M ", "S  ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilSword, new Object[] { " LM", " M ", "S  ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilSword, new Object[] { "  M", "LM ", "S  ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilSword, new Object[] { "  M", " ML", "S  ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilSword, new Object[] { "  M", " M ", "SL ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilSword, new Object[] { "  M", " M ", "S L", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		ItemStack mithrilPick = new ItemStack(itemMithrilPickaxe);
		mithrilPick.addEnchantment(Enchantment.fortune, 2);
		mithrilPick.addEnchantment(Enchantment.unbreaking, 1);
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilPick, new Object[] { "MMM", "LS ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilPick, new Object[] { "MMM", " SL", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilPick, new Object[] { "MMM", " S ", "LS ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilPick, new Object[] { "MMM", " S ", " SL", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		ItemStack axeStack = new ItemStack(itemMithrilAxe);
		axeStack.addEnchantment(Enchantment.sharpness, 2);
		axeStack.addEnchantment(Enchantment.fortune, 3);
		GameRegistry.addRecipe(new ShapedOreRecipe(axeStack, new Object[] { "MML", "MS ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(axeStack, new Object[] { "MM ", "MSL", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(axeStack, new Object[] { "MM ", "MS ", "LS ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(axeStack, new Object[] { "MM ", "MS ", " SL", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		ItemStack mithrilShovel = new ItemStack(itemMithrilSpade);
		mithrilShovel.addEnchantment(Enchantment.silkTouch, 1);
		mithrilShovel.addEnchantment(Enchantment.unbreaking, 3);
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilShovel, new Object[] { "LM ", " S ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilShovel, new Object[] { " ML", " S ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilShovel, new Object[] { " M ", "LS ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilShovel, new Object[] { " M ", " SL", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilShovel, new Object[] { " M ", " S ", "LS ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilShovel, new Object[] { " M ", " S ", " SL", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		ItemStack hoeStack = new ItemStack(itemMithrilHoe);
		hoeStack.addEnchantment(Enchantment.unbreaking, 127);
		GameRegistry.addRecipe(new ShapedOreRecipe(hoeStack, new Object[] { "MML", " S ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(hoeStack, new Object[] { "MM ", "LS ", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(hoeStack, new Object[] { "MM ", " SL", " S ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(hoeStack, new Object[] { "MM ", " S ", "LS ", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(hoeStack, new Object[] { "MM ", " S ", " SL", 'S', "stickQuartz", 'M', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ItemStack(itemQuartzRod), new Object[] { "Q", "Q", "Q", 'Q', Items.quartz });
		ItemStack mithrilBow = new ItemStack(itemMithrilBow);
		mithrilBow.addEnchantment(Enchantment.sharpness, 8);
		mithrilBow.addEnchantment(Enchantment.infinity, 1);
		GameRegistry.addRecipe(new ShapedOreRecipe(mithrilBow, new Object[] { "QMM", "M S", "MSE", 'M', "stickQuartz", 'Q', "ingotMithril", 'E', Items.ender_pearl, 'S', Items.string }));
		ItemStack helmet = new ItemStack(itemMithrilHelmet);
		helmet.addEnchantment(Enchantment.respiration, 3);
		helmet.addEnchantment(Enchantment.aquaAffinity, 1);
		GameRegistry.addRecipe(new ShapedOreRecipe(helmet, new Object[] { "LIL", "I I", "DLD", 'D', "gemDiamond", 'I', "ingotMithril", 'L', "leather" }));
		ItemStack chest = new ItemStack(itemMithrilChest);
		chest.addEnchantment(Enchantment.thorns, 4);
		GameRegistry.addRecipe(new ShapedOreRecipe(chest, new Object[] { "I I", "ILI", "LDL", 'D', "gemDiamond", 'I', "ingotMithril", 'L', "leather" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemMithrilLeggings), new Object[] { "III", "I I", 'I', "ingotMithril" }));
		ItemStack boots = new ItemStack(itemMithrilBoots);
		boots.addEnchantment(Enchantment.featherFalling, 127);
		GameRegistry.addRecipe(new ShapedOreRecipe(boots, new Object[] { "I I", "I I", 'I', "ingotMithril" }));
		GameRegistry.addSmelting(blockMithrilOre, new ItemStack(itemMithrilIngot), 2F);
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(itemMithrilNugget, 9), new Object[] { "ingotMithril" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemMithrilIngot), new Object[] { "AAA", "AAA", "AAA", 'A', "nuggetMithril" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemEnderNecklace), new Object[] { "M M", " M ", " E ", 'M', "ingotMithril", 'E', Blocks.dragon_egg }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemMithrilRing), new Object[] { " M ", "M M", " M ", 'M', "ingotMithril" }));
		GameRegistry.addShapelessRecipe(new ItemStack(itemWitherRing), new Object[] { itemMithrilRing, Items.nether_star });
		GameRegistry.addRecipe(new ShapedOreRecipe(itemFlute, new Object[] { "M  ", " M ", "  M", 'M', "ingotMithril" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockMithrilStorage, 1, 0), new Object[] { "II", "II", 'I', "ingotMithril" }));
		GameRegistry.addShapelessRecipe(new ItemStack(itemMithrilIngot, 4), new Object[] { new ItemStack(blockMithrilStorage, 1, 0) });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockMithrilStorage, 4, 1), new Object[] { "II", "II", 'I', new ItemStack(blockMithrilStorage, 1, 0) }));
		GameRegistry.addShapelessRecipe(new ItemStack(blockMithrilStorage, 1, 2), new Object[] { new ItemStack(blockMithrilStorage, 1, 1) });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockMithrilPillar, 1, 0), new Object[] { "III", " Q ", "III", 'I', "ingotMithril", 'Q', Items.quartz }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockMithrilPillar, 1, 1), new Object[] { "III", " I ", "III", 'I', "ingotMithril" }));
	}

	public static void addAchievements() {
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
		OreDictionary.registerOre("oreMithril", blockMithrilOre);
		OreDictionary.registerOre("ingotMithril", itemMithrilIngot);
		OreDictionary.registerOre("nuggetMithril", itemMithrilNugget);
		OreDictionary.registerOre("stickQuartz", itemQuartzRod);
		OreDictionary.registerOre("leather", Items.leather);

		if (winter) {
			OreDictionary.registerOre("treeSapling", blockChristmasTreeSapling);
			OreDictionary.registerOre("treeLeaves",  blockChristmasLeaves);
		}
	}

	public static void initAPI() {
		CraftingPillarAPI.addDiskTexture(itemDiscElysium, "magistics:textures/models/pillars/disk_elysium.png");

		CraftingPillarAPI.addDiskTexture(Items.record_13, "magistics:textures/models/pillars/disk_13.png");
		CraftingPillarAPI.addDiskTexture(Items.record_cat, "magistics:textures/models/pillars/disk_cat.png");
		CraftingPillarAPI.addDiskTexture(Items.record_blocks, "magistics:textures/models/pillars/disk_blocks.png");
		CraftingPillarAPI.addDiskTexture(Items.record_chirp, "magistics:textures/models/pillars/disk_chirp.png");
		CraftingPillarAPI.addDiskTexture(Items.record_far, "magistics:textures/models/pillars/disk_far.png");
		CraftingPillarAPI.addDiskTexture(Items.record_mall, "magistics:textures/models/pillars/disk_mall.png");
		CraftingPillarAPI.addDiskTexture(Items.record_mellohi, "magistics:textures/models/pillars/disk_mellohi.png");
		CraftingPillarAPI.addDiskTexture(Items.record_stal, "magistics:textures/models/pillars/disk_stal.png");
		CraftingPillarAPI.addDiskTexture(Items.record_strad, "magistics:textures/models/pillars/disk_strad.png");
		CraftingPillarAPI.addDiskTexture(Items.record_ward, "magistics:textures/models/pillars/disk_ward.png");
		CraftingPillarAPI.addDiskTexture(Items.record_11, "magistics:textures/models/pillars/disk_11.png");
		CraftingPillarAPI.addDiskTexture(Items.record_wait, "magistics:textures/models/pillars/disk_wait.png");

		SentryBehaviorRegistry.addBehavior(Items.arrow, new SentryBehaviorArrow());
		SentryBehaviorRegistry.addBehavior(Items.snowball, new SentryBehaviorSnowball());
		SentryBehaviorRegistry.addBehavior(Items.fire_charge, new SentryBehaviorFireball());
		SentryBehaviorRegistry.addBehavior(Items.potionitem, new SentryBehaviorPotion());
		SentryBehaviorRegistry.addBehavior(Items.egg, new SentryBehaviorEgg());
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
		}.setBackgroundImageName("magistics.png").setNoTitle();
	}
}