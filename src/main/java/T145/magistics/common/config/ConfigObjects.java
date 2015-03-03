package T145.magistics.common.config;

import hu.hundevelopers.elysium.heat.HeatManager;
import hu.hundevelopers.elysium.item.ElysiumBucket;
import hu.hundevelopers.elysium.item.ElysiumGrapesItem;
import hu.hundevelopers.elysium.item.ElysiumItem;
import hu.hundevelopers.elysium.item.ElysiumItemDebug;
import hu.hundevelopers.elysium.item.ElysiumItemRaspberry;
import hu.hundevelopers.elysium.model.ModelPinkUnicorn;
import hu.hundevelopers.elysium.render.ElysiumTileEntityPortalRenderer;
import hu.hundevelopers.elysium.render.RenderBlockProjectile;
import hu.hundevelopers.elysium.render.RenderCaterPillar;
import hu.hundevelopers.elysium.render.RenderDeer;
import hu.hundevelopers.elysium.render.RenderEnderMage;
import hu.hundevelopers.elysium.render.RenderEvolvedOyster;
import hu.hundevelopers.elysium.render.RenderFireballProjectile;
import hu.hundevelopers.elysium.render.RenderHero;
import hu.hundevelopers.elysium.render.RenderPinkUnicorn;
import hu.hundevelopers.elysium.render.RenderSwan;
import hu.hundevelopers.elysium.render.RenderVoidSpecter;
import hu.hundevelopers.elysium.render.StaffRenderer;
import hu.hundevelopers.elysium.thaumcraft.ElysiumAspects;
import hu.hundevelopers.elysium.thaumcraft.ElysiumResearch;
import hu.hundevelopers.elysium.thaumcraft.wand.ItemWandCaps;
import hu.hundevelopers.elysium.thaumcraft.wand.ItemWandCores;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import T145.magistics.api.DiskRegistry;
import T145.magistics.api.ElysiumPlants;
import T145.magistics.api.ElysiumStaff;
import T145.magistics.api.FreezerRecipes;
import T145.magistics.api.blocks.BlockMagisticsItem;
import T145.magistics.api.client.renderers.block.ChestItemRenderer;
import T145.magistics.api.client.renderers.block.ChestRenderer;
import T145.magistics.api.sentry.SentryBehaviorArrow;
import T145.magistics.api.sentry.SentryBehaviorEgg;
import T145.magistics.api.sentry.SentryBehaviorFireball;
import T145.magistics.api.sentry.SentryBehaviorPotion;
import T145.magistics.api.sentry.SentryBehaviorRegistry;
import T145.magistics.api.sentry.SentryBehaviorSnowball;
import T145.magistics.api.sentry.SentryBehaviorStaff;
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
import T145.magistics.common.blocks.elysium.ElysiumBlock;
import T145.magistics.common.blocks.elysium.ElysiumBlockCactus;
import T145.magistics.common.blocks.elysium.ElysiumBlockFalling;
import T145.magistics.common.blocks.elysium.ElysiumBlockFlower;
import T145.magistics.common.blocks.elysium.ElysiumBlockGrass;
import T145.magistics.common.blocks.elysium.ElysiumBlockLeaves;
import T145.magistics.common.blocks.elysium.ElysiumBlockLog;
import T145.magistics.common.blocks.elysium.ElysiumBlockOre;
import T145.magistics.common.blocks.elysium.ElysiumBlockPortalCore;
import T145.magistics.common.blocks.elysium.ElysiumBlockQuartz;
import T145.magistics.common.blocks.elysium.ElysiumBlockQuartzFence;
import T145.magistics.common.blocks.elysium.ElysiumBlockQuartzGate;
import T145.magistics.common.blocks.elysium.ElysiumBlockQuartzWall;
import T145.magistics.common.blocks.elysium.ElysiumBlockRilt;
import T145.magistics.common.blocks.elysium.ElysiumBlockSapling;
import T145.magistics.common.blocks.elysium.ElysiumBlockTallGrass;
import T145.magistics.common.blocks.elysium.ElysiumBlockWood;
import T145.magistics.common.blocks.elysium.ElysiumEnergyCrystalBlock;
import T145.magistics.common.blocks.elysium.ElysiumEnergyLiquid;
import T145.magistics.common.blocks.elysium.ElysiumFenceItemBlock;
import T145.magistics.common.blocks.elysium.ElysiumFloatingBlock;
import T145.magistics.common.blocks.elysium.ElysiumFlowerItemBlock;
import T145.magistics.common.blocks.elysium.ElysiumGrapesBush;
import T145.magistics.common.blocks.elysium.ElysiumPlanksItemBlock;
import T145.magistics.common.blocks.elysium.ElysiumQuartzItemBlock;
import T145.magistics.common.blocks.elysium.ElysiumRaspberryBush;
import T145.magistics.common.blocks.elysium.ElysiumTallGrassItemBlock;
import T145.magistics.common.blocks.elysium.ElysiumWallItemBlock;
import T145.magistics.common.blocks.elysium.ElysiumWaterBlock;
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
import T145.magistics.common.entities.EntityCaterPillar;
import T145.magistics.common.entities.EntityDeer;
import T145.magistics.common.entities.EntityEnderMage;
import T145.magistics.common.entities.EntityEvolvedOyster;
import T145.magistics.common.entities.EntityHero;
import T145.magistics.common.entities.EntityPinkUnicorn;
import T145.magistics.common.entities.EntitySwan;
import T145.magistics.common.entities.EntityVoidSpecter;
import T145.magistics.common.entities.projectiles.EntityBlockProjectile;
import T145.magistics.common.entities.projectiles.EntityEnderRandomProjectile;
import T145.magistics.common.entities.projectiles.EntityFireballProjectile;
import T145.magistics.common.entities.projectiles.EntityIceProjectile;
import T145.magistics.common.items.ItemMagisticsRecord;
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
import T145.magistics.common.items.equipment.ElysiumItemArmor;
import T145.magistics.common.items.equipment.ElysiumItemAxe;
import T145.magistics.common.items.equipment.ElysiumItemHoe;
import T145.magistics.common.items.equipment.ElysiumItemPickaxe;
import T145.magistics.common.items.equipment.ElysiumItemShovel;
import T145.magistics.common.items.equipment.ElysiumItemSword;
import T145.magistics.common.items.equipment.MithrilAxe;
import T145.magistics.common.items.equipment.MithrilBow;
import T145.magistics.common.items.equipment.MithrilHoe;
import T145.magistics.common.items.equipment.MithrilShovel;
import T145.magistics.common.items.equipment.MithrilSword;
import T145.magistics.common.items.equipment.MthrilPickaxe;
import T145.magistics.common.items.relics.ItemDawnstone;
import T145.magistics.common.items.relics.ItemElysiumStaff;
import T145.magistics.common.items.relics.MithrilFlute;
import T145.magistics.common.lib.events.ElysiumClientHandler;
import T145.magistics.common.lib.events.ElysiumHandler;
import T145.magistics.common.lib.events.EventHandlerClient;
import T145.magistics.common.lib.events.EventHandlerPlayer;
import T145.magistics.common.lib.world.MagisticsWorldGenerator;
import T145.magistics.common.lib.world.biomes.ElysiumBiomeGenBeach;
import T145.magistics.common.lib.world.biomes.ElysiumBiomeGenCorruption;
import T145.magistics.common.lib.world.biomes.ElysiumBiomeGenDesert;
import T145.magistics.common.lib.world.biomes.ElysiumBiomeGenForest;
import T145.magistics.common.lib.world.biomes.ElysiumBiomeGenOcean;
import T145.magistics.common.lib.world.biomes.ElysiumBiomeGenPlain;
import T145.magistics.common.lib.world.biomes.ElysiumBiomeGenRiver;
import T145.magistics.common.lib.world.dim.ElysiumWorldProvider;
import T145.magistics.common.tiles.TileChestHungry;
import T145.magistics.common.tiles.TileChestHungryAlchemical;
import T145.magistics.common.tiles.TileChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryMetal;
import T145.magistics.common.tiles.TileElysianPortal;
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
import coloredlightscore.src.api.CLApi;

import com.dynious.refinedrelocation.lib.Resources;
import com.pahimar.ee3.item.ItemBlockAlchemicalChest;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

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

	public static Item itemMithrilSword, itemMithrilSpade, itemMithrilPickaxe, itemMithrilAxe, itemMithrilHoe, itemMithrilBow, itemMithrilIngot, itemMithrilNugget, itemQuartzRod, itemMithrilChest, itemMithrilHelmet, itemMithrilLeggings, itemMithrilBoots, itemEnderNecklace, itemMithrilRing, itemWitherRing, itemFlute;
	public static Block blockMithrilOre, blockMithrilStorage, blockMithrilPillar;

	public static int mithrilArmorID = RenderingRegistry.addNewArmourRendererPrefix("mithril");
	public static ToolMaterial ToolMaterialMithril = EnumHelper.addToolMaterial("MITHRIL", 3, 2084, 12F, 10F, 22);
	public static ArmorMaterial ArmorMaterialMithril = EnumHelper.addArmorMaterial("MITHRIL", 66, new int[] { 3, 8, 6, 3 }, 25);

	public static Fluid fluidElysiumWater;
	public static Fluid fluidElysiumEnergy;

	public static Fluid elysiumFluidWater;
	public static Fluid elysiumFluidEnergy;

	//Rendering Ids
	public static int pipeStoneReinderingID;

	//Blocks
	public static Block blockPalestone;

	public static Block blockDirt;
	public static Block blockGrass;
	public static Block blockSand;
	public static Block blockRilt;
	public static Block blockLog;
	public static Block blockLeaves;
	//	public static Block blockGastroShell;
	public static Block blockSapling;
	public static Block blockPlanks;
	public static Block blockFlower;
	public static Block blockTallGrass;

	public static Block oreSulphure;
	public static Block oreCobalt;
	public static Block oreIridium;
	public static Block oreSilicon;
	public static Block oreJade;
	public static Block oreTourmaline;
	public static Block oreBeryl;

	public static Block blockElysiumWater;
	public static Block blockElysiumEnergyLiquid;

	public static Block blockFloatingShell;
	public static Block blockFloatingConch;

	public static Block blockEnergyCrystal;

	public static Block blockPortalCore;

	public static Block blockSulphure;
	public static Block blockCobalt;
	public static Block blockIridium;
	public static Block blockSilicon;
	public static Block blockJade;
	public static Block blockTourmaline;
	public static Block blockBeryl;

	public static Block blockCactus;
	public static Block blockRaspberryBush;
	public static Block blockGrapesBush;

	public static Block blockQuartzBlock;
	public static Block blockQuartzFence;
	public static Block blockQuartzWall;
	public static Block blockQuartzGate;

	//Items

	public static Item itemWhistle;

	public static Item itemSeedsPepper;
	public static Item itemAsphodelPetals;

	public static Item itemBeryl;
	public static Item itemIngotCobalt;
	public static Item itemIngotIridium;
	public static Item itemJade;
	public static Item itemSiliconChunk;
	public static Item itemSturdyHide;
	public static Item itemSulphur;
	public static Item itemTourmaline;

	public static Item itemStaff;

	public static Item itemDeerPelt;
	public static Item itemAntler;
	public static Item itemGrapes;
	public static Item itemRaspberry;
	public static Item itemHardPaw;
	public static Item itemKnife;

	public static Item itemToothIngot;

	public static Item itemSwordFostimber;
	public static Item itemPickaxeFostimber;
	public static Item itemAxeFostimber;
	public static Item itemShovelFostimber;
	public static Item itemHoeFostimber;

	public static Item itemSwordPalestone;
	public static Item itemPickaxePalestone;
	public static Item itemAxePalestone;
	public static Item itemSpadePalestone;
	public static Item itemHoePalestone;

	public static Item itemOverKill;
	public static Item itemDebug;
	public static Item itemWaterBucket;
	public static Item itemEnergyBucket;

	public static Item itemArmorToothHelmet;
	public static Item itemArmorToothChestplate;
	public static Item itemArmorToothLeggings;
	public static Item itemArmorToothBoots;

	public static Item wandCore;
	public static Item wandCap;

	public static WandRod WAND_ROD_HORN;
	//	public static StaffRod STAFF_ROD_HORN;
	public static WandCap WAND_CAP_PURE;
	public static WandCap WAND_CAP_CORRUPTED;

	public static final String LABYRINTH_LOOT = "labirinthLootChest";

	public static Item itemRecordElysium;

	public static ItemStack[] present_loot = new ItemStack[] {
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
		new ItemStack(itemRecordElysium, 1, 0)
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

		//items.add(itemRecordElysium = new ElysiumRecord("UranusParadise").setUnlocalizedName("record").setTextureName("magistics:elysium_disk"));
		items.add(itemRecordElysium = new ItemMagisticsRecord("UranusParadise").setUnlocalizedName("record.elysium").setTextureName("magistics:record_elysium"));

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
		blocks.put(blockMithrilStorage = new BlockMithrilStorage(Material.iron).setHardness(5F).setResistance(200F).setStepSound(Block.soundTypeMetal).setBlockName("mithril_block").setBlockTextureName("mithril:mithril_block"), BlockMithrilStorageItem.class);
		blocks.put(blockMithrilPillar = new BlockMithrilPillar(Material.iron).setHardness(5F).setResistance(200F).setStepSound(Block.soundTypeMetal).setBlockName("mithril_pillar"), BlockMithrilPillarItem.class);

		elysiumFluidWater = new Fluid("elysium_water");
		FluidRegistry.registerFluid(elysiumFluidWater);
		fluidElysiumWater = FluidRegistry.getFluid("elysium_water");

		if (fluidElysiumWater.getBlock() == null)
		{
			blocks.put(blockElysiumWater = new ElysiumWaterBlock(fluidElysiumWater, Material.water).setHardness(100F).setLightOpacity(3).setBlockName("elysium_water"), null);
			fluidElysiumWater.setBlock(blockElysiumWater);
		} else {
			blockElysiumWater = fluidElysiumWater.getBlock();
		}

		if (blockElysiumWater != null) {
			items.add(itemWaterBucket = new ElysiumBucket(blockElysiumWater).setTextureName("elysium_bucket_water").setUnlocalizedName("elysium_bucket_water").setContainerItem(Items.bucket));
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("elysium_water", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(itemWaterBucket), new ItemStack(Items.bucket));
		}

		//Liquids

		elysiumFluidEnergy = new Fluid("elysium_energy");
		elysiumFluidEnergy.setDensity(2000).setLuminosity(15).setTemperature(500);
		FluidRegistry.registerFluid(elysiumFluidEnergy);
		fluidElysiumEnergy = FluidRegistry.getFluid("elysium_energy");

		if (fluidElysiumEnergy.getBlock() == null)
		{
			blocks.put(blockElysiumEnergyLiquid = new ElysiumEnergyLiquid(fluidElysiumEnergy, Material.water).setHardness(100F).setLightOpacity(3).setBlockName("elysium_energy"), null);
			fluidElysiumEnergy.setBlock(blockElysiumEnergyLiquid);
		} else {
			blockElysiumEnergyLiquid = fluidElysiumEnergy.getBlock();
		}

		items.add(itemEnergyBucket = new ElysiumBucket(blockElysiumEnergyLiquid).setTextureName("elysium_bucket_energy").setUnlocalizedName("elysium_bucket_energy").setContainerItem(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("elysium_energy", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(itemEnergyBucket), new ItemStack(Items.bucket));

		ElysiumHandler.INSTANCE.buckets.put(blockElysiumWater, itemWaterBucket);
		ElysiumHandler.INSTANCE.buckets.put(blockElysiumEnergyLiquid, itemEnergyBucket);

		blocks.put(blockPalestone = (new ElysiumBlock(Material.rock)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("palestone").setBlockTextureName("palestone"), null);
		blocks.put(blockDirt = (new ElysiumBlock(Material.ground)).setHardness(0.5F).setStepSound(Block.soundTypeGravel).setBlockName("elysium_dirt").setBlockTextureName("dirt"), null);
		blocks.put(blockGrass = (new ElysiumBlockGrass(Material.ground)).setHardness(0.6F).setStepSound(Block.soundTypeGrass).setBlockName("elysium_grass").setBlockTextureName("grass"), null);
		blocks.put(blockSand = (new ElysiumBlockFalling(Material.sand)).setHardness(0.5F).setStepSound(Block.soundTypeSand).setBlockName("leucosand").setBlockTextureName("sand"), null);
		blocks.put(blockRilt = (new ElysiumBlockRilt(Material.sand)).setHardness(0.6F).setStepSound(Block.soundTypeGravel).setBlockTextureName("rilt").setBlockName("rilt"), null);
		blocks.put(blockSapling = (new ElysiumBlockSapling()).setHardness(0F).setBlockTextureName("elysium_sapling").setBlockName("elysium_sapling"), null);
		blocks.put(blockLog = (new ElysiumBlockLog()).setHardness(2F).setBlockTextureName("elysium_log").setBlockName("elysium_log"), null);
		blocks.put(blockLeaves = (new ElysiumBlockLeaves()).setLightOpacity(1).setHardness(0.2F).setStepSound(Block.soundTypeGrass).setBlockTextureName("elysium_leaves").setBlockName("elysium_leaves"), null);
		blocks.put(blockPlanks = (new ElysiumBlockWood()).setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockTextureName("elysium_planks").setBlockName("elysium_planks"), ElysiumPlanksItemBlock.class);
		//registerBlock(blockPlanks, ElysiumPlanksItemBlock.class);

		//blockGastroShell = (new ElysiumBlockGastroShell(idGastroShellBlock.getInt(), Material.rock)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setBlockName("gastroshell");
		//registerBlock(blockGastroShell, "Gastro Shell");

		blocks.put(blockFlower = (new ElysiumBlockFlower()).setHardness(0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("elysium_flower").setBlockName("elysium_flower"), ElysiumFlowerItemBlock.class);
		blocks.put(blockTallGrass = new ElysiumBlockTallGrass().setHardness(0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("elysium_tallgrass").setBlockName("elysium_tallgrass"), ElysiumTallGrassItemBlock.class);

		blocks.put(oreSulphure = new ElysiumBlockOre().setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreSulphur").setBlockName("oreSulphur"), null);
		blocks.put(oreBeryl = new ElysiumBlockOre().setHardness(3F).setResistance(5F).setLightLevel(1F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreBeryl").setBlockName("oreBeryl"), null);
		blocks.put(oreCobalt = new ElysiumBlockOre().setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreCobalt").setBlockName("oreCobalt"), null);
		blocks.put(oreIridium = new ElysiumBlockOre().setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreIridium").setBlockName("oreIridium"), null);
		blocks.put(oreSilicon = new ElysiumBlockOre().setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreSilicon").setBlockName("oreSilicon"), null);
		blocks.put(oreJade = new ElysiumBlockOre().setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreJade").setBlockName("oreJade"), null);
		blocks.put(oreTourmaline = new ElysiumBlockOre().setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreTourmaline").setBlockName("oreTourmaline"), null);
		blocks.put(blockPortalCore = new ElysiumBlockPortalCore(Material.glass).setHardness(5F).setStepSound(Block.soundTypeGlass).setBlockName("portalCore"), null);
		blocks.put(blockFloatingConch = new ElysiumFloatingBlock("conch").setHardness(0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("floating_block_conch").setBlockName("floating_block_conch"), null);
		blocks.put(blockFloatingShell = new ElysiumFloatingBlock("shell").setHardness(0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("floating_block_shell").setBlockName("floating_block_shell"), null);
		/*
		blockPalestoneBrick = (new ElysiumBlockHeatable(idPalestoneBrickBlock.getInt(), Material.rock, -273, 300)).setHardness(2F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick");
		registerBlock(blockPalestoneBrick, "Palestone Brick");

		blockPalestoneBrickCracked = (new ElysiumBlockHeatable(idPalestoneBrickCrackedBlock.getInt(), Material.rock, -273, 300)).setHardness(2F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick_cracked");
		registerBlock(blockPalestoneBrickCracked, "Cracked Palestone Brick");

		blockPalestoneBrickMossy = (new ElysiumBlockHeatable(idPalestoneBrickMossyBlock.getInt(), Material.rock, -273, 300)).setHardness(2F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick_mossy");
		registerBlock(blockPalestoneBrickMossy, "Mossy Palestone Brick");

		blockPalestoneBrickChiseld = (new ElysiumBlockHeatable(idPalestoneChiseldBrickBlock.getInt(), Material.rock, -273, 300)).setHardness(2F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick_chiseld");
		registerBlock(blockPalestoneBrickChiseld, "Chiseld Palestone Brick");

		blockPalestonePillar = (new ElysiumBlockPalestonePillar(idPalestonePillarBlock.getInt(), Material.rock)).setHardness(2F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("palestone_pillar");
		registerBlock(blockPalestonePillar, "Palestone Pillar");
		 */

		blocks.put(blockSulphure = new ElysiumBlock(Material.rock).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("blockSulphur").setBlockName("blockSulphur"), null);
		blocks.put(blockBeryl = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setLightLevel(0.5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockBeryl").setBlockName("blockBeryl"), null);
		blocks.put(blockCobalt = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockCobalt").setBlockName("blockCobalt"), null);
		blocks.put(blockIridium = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockIridium").setBlockName("blockIridium"), null);
		blocks.put(blockSilicon = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockSilicon").setBlockName("blockSilicon"), null);
		blocks.put(blockJade = new ElysiumBlock(Material.rock).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("blockJade").setBlockName("blockJade"), null);
		blocks.put(blockTourmaline = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("blockTourmaline").setBlockName("blockTourmaline"), null);
		blocks.put(blockEnergyCrystal = new ElysiumEnergyCrystalBlock(Material.glass).setHardness(3F).setResistance(4F).setStepSound(Block.soundTypeGlass).setLightLevel(1F).setBlockTextureName("energy_crystal").setBlockName("energy_crystal"), null);

		blocks.put(blockCactus = new ElysiumBlockCactus().setHardness(0F).setResistance(1F).setStepSound(Block.soundTypeGrass).setBlockTextureName("hallowedcactus").setBlockName("blockCactus"), null);
		blocks.put(blockRaspberryBush = new ElysiumRaspberryBush().setHardness(0F).setResistance(1F).setStepSound(Block.soundTypeGrass).setBlockTextureName("raspberrybushwithoutberries").setBlockName("blockRaspberryBush"), null);
		blocks.put(blockGrapesBush = new ElysiumGrapesBush().setHardness(0F).setResistance(1F).setStepSound(Block.soundTypeGrass).setBlockTextureName("grapes_empty").setBlockName("blockGrapes"), null);

		blocks.put(blockQuartzBlock = new ElysiumBlockQuartz().setHardness(2F).setResistance(10F).setStepSound(Block.soundTypePiston).setBlockTextureName("elysium:quartz_block").setBlockName("quartz_mossy"), ElysiumQuartzItemBlock.class);
		blocks.put(blockQuartzFence = new ElysiumBlockQuartzFence(blockQuartzBlock).setBlockName("quartzFence"), ElysiumFenceItemBlock.class);
		blocks.put(blockQuartzWall = new ElysiumBlockQuartzWall(blockQuartzBlock).setBlockName("quartzWall"), ElysiumWallItemBlock.class);
		blocks.put(blockQuartzGate = new ElysiumBlockQuartzGate().setBlockName("quartzGate"), null);

		Blocks.dragon_egg.setCreativeTab(tabMagistics);

		//Items
		// Added by the Mithril part
		//itemWhistle = new ElysiumItemWhistle().setTextureName("enderflute").setUnlocalizedName("enderflute");
		items.add(itemSeedsPepper = new ElysiumItem().setTextureName("seeds_pepper").setUnlocalizedName("seeds_pepper"));
		//itemOverKill = new ElysiumItemOverkill().setTextureName("overkill").setUnlocalizedName("overkill").setCreativeTab(null);
		items.add(itemAsphodelPetals = new ElysiumItem().setTextureName("asphodelpetal").setUnlocalizedName("asphodelpetal"));
		items.add(itemDebug = new ElysiumItemDebug().setTextureName("debug").setUnlocalizedName("debug"));
		items.add(itemBeryl = new ElysiumItem().setTextureName("beryl").setUnlocalizedName("beryl"));
		items.add(itemIngotCobalt = new ElysiumItem().setTextureName("ingotCobalt").setUnlocalizedName("ingotCobalt"));
		items.add(itemIngotIridium = new ElysiumItem().setTextureName("ingotIridium").setUnlocalizedName("ingotIridium"));
		items.add(itemJade = new ElysiumItem().setTextureName("jade").setUnlocalizedName("jade"));
		items.add(itemSiliconChunk = new ElysiumItem().setTextureName("siliconchunk").setUnlocalizedName("siliconchunk"));
		items.add(itemSulphur = new ElysiumItem().setTextureName("sulphur").setUnlocalizedName("elysium_sulphur"));
		items.add(itemTourmaline = new ElysiumItem().setTextureName("tourmaline").setUnlocalizedName("tourmaline"));
		items.add(itemSturdyHide = new ElysiumItem().setTextureName("sturdyHide").setUnlocalizedName("sturdyHide"));

		items.add(itemStaff = new ItemElysiumStaff().setTextureName("staff").setUnlocalizedName("staff"));
		items.add(itemAntler = new ElysiumItem().setTextureName("antler").setUnlocalizedName("antler"));
		items.add(itemDeerPelt = new ElysiumItem().setTextureName("deer_pelt").setUnlocalizedName("deer_pelt"));
		items.add(itemGrapes = new ElysiumGrapesItem().setTextureName("grapes").setUnlocalizedName("grapes"));
		items.add(itemRaspberry = new ElysiumItemRaspberry(2).setTextureName("raspberry").setUnlocalizedName("raspberry"));
		items.add(itemHardPaw = new ElysiumItem().setTextureName("hard_paw").setUnlocalizedName("hard_paw"));
		items.add(itemKnife = new ElysiumItem().setTextureName("knife").setMaxStackSize(1).setUnlocalizedName("knife"));
		items.add(itemToothIngot = new ElysiumItem().setTextureName("ingotTooth").setUnlocalizedName("ingotTooth"));

		//Tool Registering

		Item.ToolMaterial FOSTIMBER_MAT = EnumHelper.addToolMaterial("FOSTIMBER", 0, 59, 2F, 0, 15);

		items.add(itemSwordFostimber = new ElysiumItemSword(FOSTIMBER_MAT).setTextureName("swordFostimber").setUnlocalizedName("swordFostimber"));
		items.add(itemPickaxeFostimber = new ElysiumItemPickaxe(FOSTIMBER_MAT).setTextureName("pickaxeFostimber").setUnlocalizedName("pickaxeFostimber"));
		items.add(itemAxeFostimber = new ElysiumItemAxe(FOSTIMBER_MAT).setTextureName("axeFostimber").setUnlocalizedName("axeFostimber"));
		items.add(itemShovelFostimber = new ElysiumItemShovel(FOSTIMBER_MAT).setTextureName("shovelFostimber").setUnlocalizedName("shovelFostimber"));
		items.add(itemHoeFostimber = new ElysiumItemHoe(FOSTIMBER_MAT).setTextureName("hoeFostimber").setUnlocalizedName("hoeFostimber"));
		Item.ToolMaterial STONE_MAT = EnumHelper.addToolMaterial("PALESTONE", 1, 131, 4F, 1, 5);

		items.add(itemSwordPalestone = new ElysiumItemSword(STONE_MAT).setTextureName("swordPalestone").setUnlocalizedName("swordPalestone"));
		items.add(itemPickaxePalestone = new ElysiumItemPickaxe(STONE_MAT).setTextureName("pickaxePalestone").setUnlocalizedName("pickaxePalestone"));
		items.add(itemAxePalestone = new ElysiumItemAxe(STONE_MAT).setTextureName("axePalestone").setUnlocalizedName("axePalestone"));
		items.add(itemSpadePalestone = new ElysiumItemShovel(STONE_MAT).setTextureName("shovelPalestone").setUnlocalizedName("shovelPalestone"));
		items.add(itemHoePalestone = new ElysiumItemHoe(STONE_MAT).setTextureName("hoePalestone").setUnlocalizedName("hoePalestone"));

		ArmorMaterial TOOTH_ARMORMAT = EnumHelper.addArmorMaterial("TOOTH", 25, new int[] { 2, 6, 5, 2 }, 25);

		items.add(itemArmorToothHelmet = new ElysiumItemArmor(TOOTH_ARMORMAT, 4, 0).setTextureName("elysium:sceptertoothhelmet").setUnlocalizedName("toothHelmet"));
		items.add(itemArmorToothChestplate = new ElysiumItemArmor(TOOTH_ARMORMAT, 4, 1).setTextureName("elysium:sceptertoothchestplate").setUnlocalizedName("toothChestplate"));
		items.add(itemArmorToothLeggings = new ElysiumItemArmor(TOOTH_ARMORMAT, 4, 2).setTextureName("elysium:sceptertoothlegs").setUnlocalizedName("toothLeggings"));
		items.add(itemArmorToothBoots = new ElysiumItemArmor(TOOTH_ARMORMAT, 4, 3).setTextureName("elysium:sceptertoothboots").setUnlocalizedName("toothBoots"));
		items.add(wandCore = new ItemWandCores().setUnlocalizedName("WandCores"));
		items.add(wandCap = new ItemWandCaps().setUnlocalizedName("WandCaps"));

		//WAND_ROD_HORN = new ElysiumWandRod("horn", 100, new ItemStack(wandCore, 1, 0), 12, new ResourceLocation("elysium", "textures/models/wand_rod_horn.png"));

		//STAFF_ROD_HORN = new StaffRod("horn", 50, new ItemStack(wandCore, 1, 0 + 1), 24, new HornStaffUpdate(), new ResourceLocation(MODID, "textures/models/wand_rod_horn.png"));
		//WAND_CAP_PURE = new ElysiumWandCap("pure", 0F, new ItemStack(wandCap, 1, 0), 2, new ResourceLocation("elysium", "textures/models/wand_cap_crystal_pure.png"));
		//WAND_CAP_CORRUPTED = new ElysiumWandCap("corrupted", 0F, new ItemStack(wandCap, 1, 1), 2, new ResourceLocation("elysium", "textures/models/wand_cap_crystal_corrupted.png"));
		//WandCap WAND_CAP_IRON = new WandCap("iron", 1.1f, Arrays.asList(Aspect.ORDER),1, new ItemStack(ConfigItems.itemWandCap,1,0),1);

		oreSulphure.setHarvestLevel("pickaxe", 0);
		oreCobalt.setHarvestLevel("pickaxe", 1);
		oreSilicon.setHarvestLevel("pickaxe", 2);
		oreIridium.setHarvestLevel("pickaxe", 2);
		oreJade.setHarvestLevel("pickaxe", 2);
		oreBeryl.setHarvestLevel("pickaxe", 2);
		oreTourmaline.setHarvestLevel("pickaxe", 3);

		blockSulphure.setHarvestLevel("pickaxe", 0);
		blockCobalt.setHarvestLevel("pickaxe", 1);
		blockSilicon.setHarvestLevel("pickaxe", 2);
		blockIridium.setHarvestLevel("pickaxe", 2);
		blockJade.setHarvestLevel("pickaxe", 2);
		blockBeryl.setHarvestLevel("pickaxe", 2);
		blockTourmaline.setHarvestLevel("pickaxe", 3);

		blockEnergyCrystal.setHarvestLevel("pickaxe", 1);

		blockGrass.setHarvestLevel("shovel", 0);
		blockDirt.setHarvestLevel("shovel", 0);
		blockLog.setHarvestLevel("axe", 0);
		blockPlanks.setHarvestLevel("axe", 0);

		tiles.add(TileElysianPortal.class);
		//GameRegistry.registerTileEntity(ElysianTileEntityPortal.class, "ElysianTileEntityPortal");

		DimensionManager.registerProviderType(dimensionID, ElysiumWorldProvider.class, false);
		DimensionManager.registerDimension(dimensionID, dimensionID);

		biomePlain = new ElysiumBiomeGenPlain(biomeIdPlains).setHeight(new Height(0.25F, 0.3F)).setColor(0x8ec435).setBiomeName("Elysium Plain");
		biomeForest = new ElysiumBiomeGenForest(biomeIdForest).setHeight(new Height(0.15F, 0.2F)).setColor(0x93a66f).setEnableSnow().setTemperatureRainfall(0F, 0.5F).setBiomeName("Elysium Forest");
		biomeCorruption = new ElysiumBiomeGenCorruption(biomeIdPlainsCorrupt).setHeight(new Height(0.05F, 0.1F)).setColor(0x987497).func_76733_a(9154376).setTemperatureRainfall(0.8F, 0.9F).setBiomeName("Elysium Corruption");

		biomeOcean = new ElysiumBiomeGenOcean(biomeIdOcean).setHeight(new Height(-1F, 0.1F)).setColor(0x73c6db).setBiomeName("Elysium Ocean");
		biomeRiver = new ElysiumBiomeGenRiver(biomeIdRiver).setHeight(new Height(-0.5F, 0F)).setColor(0x73c6db).setBiomeName("Elysium River");
		biomeDesert = new ElysiumBiomeGenDesert(biomeIdDesert).setHeight(new Height(0.3F, 0.1F)).setTemperatureRainfall(2F, 0.2F).setColor(0xc9c8ce).setBiomeName("Elysium Desert");
		biomeBeach = new ElysiumBiomeGenBeach(biomeIdBeach).setHeight(new Height(0F, 0.025F)).setColor(16440917).setTemperatureRainfall(0.8F, 0.4F).setBiomeName("Elysium Beach");

		ElysiumAspects.initAspects();

		int caterPillarID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityCaterPillar.class, "ElysiumCaterPillar", caterPillarID, 0x6e6e6e, 0xcccccc);
		EntityRegistry.registerModEntity(EntityCaterPillar.class, "ElysiumCaterPillar", caterPillarID, Magistics.modid, 64, 2, true);

		int evolvedOysterID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityEvolvedOyster.class, "ElysiumEvolvedOyster", evolvedOysterID, 0x645c6a, 0xd893d6);
		EntityRegistry.registerModEntity(EntityEvolvedOyster.class, "ElysiumEvolvedOyster", evolvedOysterID, Magistics.modid, 64, 2, true);

		int swanID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntitySwan.class, "ElysiumSwan", swanID, 0xfafafa, 0xff9600);
		EntityRegistry.registerModEntity(EntitySwan.class, "ElysiumSwan", swanID, Magistics.modid, 64, 2, true);

		int deerID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityDeer.class, "ElysiumDeer", deerID, 0x969690, 0xffff00);
		EntityRegistry.registerModEntity(EntityDeer.class, "ElysiumDeer", deerID, Magistics.modid, 64, 2, true);

		int unicornID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityPinkUnicorn.class, "ElysiumUnicorn", unicornID, 0xffa8d4, 0xebebeb);
		EntityRegistry.registerModEntity(EntityPinkUnicorn.class, "ElysiumUnicorn", unicornID, Magistics.modid, 64, 2, true);

		int heroID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityHero.class, "ElysiumHero", heroID, 0xe4a9a4, 0xe4f202);
		EntityRegistry.registerModEntity(EntityHero.class, "ElysiumHero", heroID, Magistics.modid, 128, 3, true);

		int voidspecterID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityVoidSpecter.class, "ElysiumVoidSpecter", voidspecterID, 0x623464, 0x3A2A3A);
		EntityRegistry.registerModEntity(EntityVoidSpecter.class, "ElysiumVoidSpecter", voidspecterID, Magistics.modid, 64, 2, true);

		int endermageID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityEnderMage.class, "ElysiumEnderMage", endermageID, 0x000000, 0x9d8ca0);
		EntityRegistry.registerModEntity(EntityEnderMage.class, "ElysiumEnderMage", endermageID, Magistics.modid, 64, 2, true);

		int blockthrowableID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityBlockProjectile.class, "BlockProjectile", blockthrowableID, Magistics.modid, 64, 1, true);

		int iceprojectileID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityIceProjectile.class, "IceProjectile", iceprojectileID, Magistics.modid, 64, 1, true);

		int fireprojectileID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityFireballProjectile.class, "FireProjectile", fireprojectileID, Magistics.modid, 64, 1, true);

		int enderrandomprojectileID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityEnderRandomProjectile.class, "EnderRandomProjectile", enderrandomprojectileID, Magistics.modid, 64, 1, true);

		ThaumcraftApi.registerEntityTag("ElysiumUnicorn", new AspectList().add(Aspect.MAGIC, 2).add(Aspect.CRYSTAL, 2).add(Aspect.BEAST, 4).add(ElysiumAspects.SANCTUS, 5));
		ThaumcraftApi.registerEntityTag("ElysiumDeer", new AspectList().add(Aspect.MAGIC, 2).add(Aspect.CLOTH, 1).add(Aspect.BEAST, 4).add(ElysiumAspects.SANCTUS, 1));
		ThaumcraftApi.registerEntityTag("ElysiumCaterPillar", new AspectList().add(Aspect.TRAVEL, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2).add(ElysiumAspects.SANCTUS, 1));
		ThaumcraftApi.registerEntityTag("ElysiumSwan", new AspectList().add(Aspect.FLIGHT, 2).add(Aspect.WATER, 2).add(Aspect.BEAST, 2).add(ElysiumAspects.SANCTUS, 1));

		ThaumcraftApi.registerEntityTag("ElysiumVoidSpecter", new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.ELDRITCH, 4));
		ThaumcraftApi.registerEntityTag("ElysiumEnderMage", new AspectList().add(Aspect.MAGIC, 4).add(Aspect.ELDRITCH, 4));

		ThaumcraftApi.registerEntityTag("ElysiumHero", new AspectList().add(Aspect.MAN, 4).add(Aspect.MIND, 4).add(ElysiumAspects.SANCTUS, 1));

		ThaumcraftApi.registerEntityTag("ElysiumEvolvedOyster", new AspectList().add(Aspect.TRAVEL, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2).add(ElysiumAspects.SANCTUS, 1));

		EntityRegistry.addSpawn(EntityCaterPillar.class, 5, 3, 5, EnumCreatureType.creature, biomePlain);
		EntityRegistry.addSpawn(EntitySwan.class, 10, 3, 5, EnumCreatureType.creature, biomePlain);
		EntityRegistry.addSpawn(EntityDeer.class, 1, 1, 1, EnumCreatureType.creature, biomeForest);
		EntityRegistry.addSpawn(EntityPinkUnicorn.class, 1, 1, 1, EnumCreatureType.creature, biomeForest);
		EntityRegistry.addSpawn(EntityEnderMage.class, 7, 1, 1, EnumCreatureType.monster, biomeCorruption, biomeDesert);
		EntityRegistry.addSpawn(EntityVoidSpecter.class, 10, 2, 3, EnumCreatureType.monster, biomeCorruption, biomeDesert);
		EntityRegistry.addSpawn(EntityEnderman.class, 10, 2, 3, EnumCreatureType.monster, biomeCorruption, biomeDesert);
		EntityRegistry.addSpawn(EntityEvolvedOyster.class, 8, 2, 4, EnumCreatureType.creature, biomePlain);
		EntityRegistry.addSpawn(EntityHero.class, 10, 2, 5, EnumCreatureType.creature, biomePlain, biomeForest);
		//EntityRegistry.addSpawn(EntityHero.class, 1, 1, 1, EnumCreatureType.monster, biomePlain, biomeForest, biomeCorruption, biomeDesert, biomeOcean);

		//Fire Info
		/*Blocks.fire.setFireInfo(blockLog, 2, 2);
		Blocks.fire.setFireInfo(blockPlanks, 2, 10);
		Blocks.fire.setFireInfo(blockLeaves, 15, 30);
		Blocks.fire.setFireInfo(oreSulphure, 2, 2);
		Blocks.fire.setFireInfo(blockSulphure, 5, 5);*/

		HeatManager.getInstance().registerBlock(Blocks.lava, 5000F);
		HeatManager.getInstance().registerBlock(Blocks.water, 10F);
		HeatManager.getInstance().registerBlock(Blocks.ice, -10F);
		HeatManager.getInstance().registerBlock(Blocks.snow, 0F);

		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(itemGrapes, 0, 1, 3, 5));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(itemGrapes, 1, 1, 3, 5));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(itemRaspberry, 0, 1, 3, 10));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(Item.getItemFromBlock(blockEnergyCrystal), 0, 1, 4, 10));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(Item.getItemFromBlock(blockEnergyCrystal), 1, 1, 4, 10));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(Item.getItemFromBlock(blockRaspberryBush), 0, 1, 4, 10));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(Item.getItemFromBlock(blockGrapesBush), 0, 1, 4, 10));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(Item.getItemFromBlock(blockSulphure), 0, 1, 4, 10));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(itemDeerPelt, 0, 1, 2, 4));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(itemHardPaw, 0, 1, 3, 3));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(Items.diamond, 0, 1, 1, 5));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.dragon_egg), 0, 1, 1, 1));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(itemAntler, 0, 1, 3, 2));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(wandCore, 0, 1, 1, 5));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(itemStaff, 0, 1, 1, 2));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(itemStaff, 1, 1, 1, 1));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(itemStaff, 2, 1, 1, 2));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(itemStaff, 3, 1, 1, 3));
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

		ClientRegistry.bindTileEntitySpecialRenderer(TileElysianPortal.class, new ElysiumTileEntityPortalRenderer());

		MinecraftForgeClient.registerItemRenderer(ConfigObjects.itemStaff, new StaffRenderer());

		RenderingRegistry.registerEntityRenderingHandler(EntityCaterPillar.class, new RenderCaterPillar());
		RenderingRegistry.registerEntityRenderingHandler(EntitySwan.class, new RenderSwan());
		RenderingRegistry.registerEntityRenderingHandler(EntityDeer.class, new RenderDeer());
		RenderingRegistry.registerEntityRenderingHandler(EntityEvolvedOyster.class, new RenderEvolvedOyster());
		RenderingRegistry.registerEntityRenderingHandler(EntityPinkUnicorn.class, new RenderPinkUnicorn(new ModelPinkUnicorn()));
		RenderingRegistry.registerEntityRenderingHandler(EntityVoidSpecter.class, new RenderVoidSpecter());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderMage.class, new RenderEnderMage());
		RenderingRegistry.registerEntityRenderingHandler(EntityHero.class, new RenderHero());

		RenderingRegistry.registerEntityRenderingHandler(EntityBlockProjectile.class, new RenderBlockProjectile());
		RenderingRegistry.registerEntityRenderingHandler(EntityIceProjectile.class, new RenderSnowball(Items.snowball));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireballProjectile.class, new RenderFireballProjectile(0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderRandomProjectile.class, new RenderSnowball(Items.ender_pearl));
	}

	public static void registerHandlers() {
		MinecraftForge.EVENT_BUS.register(new EventHandlerClient());
		MinecraftForge.EVENT_BUS.register(new EventHandlerPlayer());

		GameRegistry.registerFuelHandler(ElysiumHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ElysiumHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(ElysiumHandler.INSTANCE);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
			MinecraftForge.EVENT_BUS.register(new ElysiumClientHandler());
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
		GameRegistry.addRecipe(new ItemStack(blockTrashPillar, 1), new Object[] { "SSS", "SLS", "SSS", Character.valueOf('S'), Blocks.stone, Character.valueOf('L'), Items.ender_pearl});

		GameRegistry.addRecipe(new ItemStack(blockBasePillar), new Object[] { "SSS", " S ", "SSS", Character.valueOf('S'), blockPalestone });
		GameRegistry.addRecipe(new ItemStack(blockTrashPillar, 1), new Object[] { "SSS", "SLS", "SSS", Character.valueOf('S'), blockPalestone, Character.valueOf('L'), Items.ender_pearl});
		GameRegistry.addRecipe(new ItemStack(blockPotPillar), new Object[] { "S", "F", "P", Character.valueOf('S'), blockDirt, Character.valueOf('P'), blockBasePillar , Character.valueOf('F'), Items.flower_pot});

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

		GameRegistry.addRecipe(new ItemStack(itemPickaxeFostimber), new Object[] {"WW "," SW","S W", Character.valueOf('S'), Items.stick, Character.valueOf('W'), new ItemStack(blockPlanks, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(itemPickaxePalestone), new Object[] {"WW "," SW","S W", Character.valueOf('S'), Items.stick, Character.valueOf('W'), blockPalestone});
		GameRegistry.addRecipe(new ItemStack(itemShovelFostimber), new Object[] {" WW"," SW","S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), new ItemStack(blockPlanks, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(itemSpadePalestone), new Object[] {" WW"," SW","S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), blockPalestone});
		GameRegistry.addRecipe(new ItemStack(itemHoeFostimber), new Object[] {"WWW"," S ","S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), new ItemStack(blockPlanks, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(itemHoePalestone), new Object[] {"WWW"," S ","S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), blockPalestone});
		GameRegistry.addRecipe(new ItemStack(itemAxeFostimber), new Object[] {"WW ","WS ", "S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), new ItemStack(blockPlanks, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(itemAxePalestone), new Object[] {"WW ","WS ", "S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), blockPalestone});
		GameRegistry.addRecipe(new ItemStack(itemSwordFostimber), new Object[] {"  W"," W ", "S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), new ItemStack(blockPlanks, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(itemSwordPalestone), new Object[] {"  W"," W ", "S  ", Character.valueOf('S'), Items.stick, Character.valueOf('W'), blockPalestone});

		GameRegistry.addRecipe(new ItemStack(itemArmorToothHelmet, 1, 0), new Object[] { "SSS", "S S", Character.valueOf('S'), itemToothIngot});
		GameRegistry.addRecipe(new ItemStack(itemArmorToothChestplate, 1, 0), new Object[] { "S S", "SSS", "SSS", Character.valueOf('S'), itemToothIngot});
		GameRegistry.addRecipe(new ItemStack(itemArmorToothLeggings, 1, 0), new Object[] { "SSS", "S S", "S S", Character.valueOf('S'), itemToothIngot});
		GameRegistry.addRecipe(new ItemStack(itemArmorToothBoots, 1, 0), new Object[] { "S S",  "S S", Character.valueOf('S'), itemToothIngot});

		GameRegistry.addRecipe(new ItemStack(itemWhistle), new Object[] {" EO"," O ", "O  ", Character.valueOf('O'), Blocks.obsidian, Character.valueOf('E'), Items.ender_eye});
		GameRegistry.addRecipe(new ItemStack(itemWhistle), new Object[] {"E O"," O ", "O  ", Character.valueOf('O'), Blocks.obsidian, Character.valueOf('E'), Items.ender_eye});
		GameRegistry.addRecipe(new ItemStack(itemWhistle), new Object[] {"  O","EO ", "O  ", Character.valueOf('O'), Blocks.obsidian, Character.valueOf('E'), Items.ender_eye});
		GameRegistry.addRecipe(new ItemStack(itemWhistle), new Object[] {"  O"," OE", "O  ", Character.valueOf('O'), Blocks.obsidian, Character.valueOf('E'), Items.ender_eye});
		GameRegistry.addRecipe(new ItemStack(itemWhistle), new Object[] {"  O"," O ", "OE ", Character.valueOf('O'), Blocks.obsidian, Character.valueOf('E'), Items.ender_eye});
		GameRegistry.addRecipe(new ItemStack(itemWhistle), new Object[] {"  O"," O ", "O E", Character.valueOf('O'), Blocks.obsidian, Character.valueOf('E'), Items.ender_eye});

		GameRegistry.addRecipe(new ItemStack(blockSulphure), new Object[] {"XX", "XX", Character.valueOf('X'), itemSulphur});
		GameRegistry.addShapelessRecipe(new ItemStack(itemSulphur, 4), new Object[] {blockSulphure});
		GameRegistry.addRecipe(new ItemStack(blockBeryl), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemBeryl});
		GameRegistry.addShapelessRecipe(new ItemStack(itemBeryl, 9), new Object[] {blockBeryl});
		GameRegistry.addRecipe(new ItemStack(blockCobalt), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemIngotCobalt});
		GameRegistry.addShapelessRecipe(new ItemStack(itemIngotCobalt, 9), new Object[] {blockCobalt});
		GameRegistry.addRecipe(new ItemStack(blockIridium), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemIngotIridium});
		GameRegistry.addShapelessRecipe(new ItemStack(itemIngotIridium, 9), new Object[] {blockIridium});
		GameRegistry.addRecipe(new ItemStack(blockSilicon), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemSiliconChunk});
		GameRegistry.addShapelessRecipe(new ItemStack(itemSiliconChunk, 9), new Object[] {blockSilicon});
		GameRegistry.addRecipe(new ItemStack(blockJade), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemJade});
		GameRegistry.addShapelessRecipe(new ItemStack(itemJade, 9), new Object[] {blockJade});
		GameRegistry.addRecipe(new ItemStack(blockTourmaline), new Object[] {"XXX", "XXX", "XXX", Character.valueOf('X'), itemTourmaline});
		GameRegistry.addShapelessRecipe(new ItemStack(itemTourmaline, 9), new Object[] {blockTourmaline});

		GameRegistry.addShapelessRecipe(new ItemStack(itemAsphodelPetals, 2), new Object[] {new ItemStack(blockFlower, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_nugget, 2), new Object[] {new ItemStack(blockFlower, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(blockPlanks, 4, 0), new Object[] {new ItemStack(blockLog, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(blockPlanks, 4, 1), new Object[] {new ItemStack(blockLog, 1, 1)});

		GameRegistry.addRecipe(new ItemStack(itemStaff, 1, 0), new Object[] {" CA", " S ", "S  ", Character.valueOf('C'), new ItemStack(blockEnergyCrystal, 1, 0), Character.valueOf('S'), Items.stick, Character.valueOf('A'), itemAntler});
		GameRegistry.addRecipe(new ItemStack(itemStaff, 1, 1), new Object[] {" CA", " S ", "S  ", Character.valueOf('C'), Blocks.ice, Character.valueOf('S'), Items.stick, Character.valueOf('A'), itemAntler});
		GameRegistry.addRecipe(new ItemStack(itemStaff, 1, 2), new Object[] {" CA", " S ", "S  ", Character.valueOf('C'), Items.ender_eye, Character.valueOf('S'), Items.stick, Character.valueOf('A'), itemAntler});
		GameRegistry.addRecipe(new ItemStack(itemStaff, 1, 3), new Object[] {" CA", " S ", "S  ", Character.valueOf('C'), Items.fire_charge, Character.valueOf('S'), Items.stick, Character.valueOf('A'), itemAntler});

		GameRegistry.addRecipe(new ItemStack(Items.saddle), new Object[] {"HHH", "HSH", " I ", Character.valueOf('H'), itemSturdyHide, Character.valueOf('S'), Items.string, Character.valueOf('I'), Items.iron_ingot});

		GameRegistry.addShapelessRecipe(new ItemStack(itemSturdyHide), new Object[] {new ItemStack(itemDeerPelt), new ItemStack(itemKnife)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemSturdyHide), new Object[] {new ItemStack(Items.leather), new ItemStack(itemKnife)});
		GameRegistry.addRecipe(new ItemStack(itemKnife), new Object[] { " I", "S ", Character.valueOf('S'), Items.stick, Character.valueOf('I'), Items.iron_ingot});

		GameRegistry.addShapelessRecipe(new ItemStack(blockQuartzBlock, 1, 0), new Object[] {new ItemStack(Blocks.quartz_block, 1, 0), new ItemStack(Blocks.vine)});
		GameRegistry.addShapelessRecipe(new ItemStack(blockQuartzBlock, 1, 1), new Object[] {new ItemStack(Blocks.quartz_block, 1, 1), new ItemStack(Blocks.vine)});
		GameRegistry.addShapelessRecipe(new ItemStack(blockQuartzBlock, 1, 2), new Object[] {new ItemStack(Blocks.quartz_block, 1, 2), new ItemStack(Blocks.vine)});

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.quartz_block, 1, 0), new Object[] {new ItemStack(blockQuartzBlock, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.quartz_block, 1, 1), new Object[] {new ItemStack(blockQuartzBlock, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.quartz_block, 1, 2), new Object[] {new ItemStack(blockQuartzBlock, 1, 2)});

		GameRegistry.addRecipe(new ItemStack(blockQuartzFence, 2), new Object[] { "SSS", "SSS", Character.valueOf('S'), Items.quartz});
		GameRegistry.addRecipe(new ItemStack(blockQuartzWall, 6, 1), new Object[] { "SSS", "SSS", Character.valueOf('S'), blockQuartzBlock});
		GameRegistry.addRecipe(new ItemStack(blockQuartzWall, 6, 0), new Object[] { "SSS", "SSS", Character.valueOf('S'), Blocks.quartz_block});

		GameRegistry.addShapelessRecipe(new ItemStack(blockQuartzFence, 1, 1), new Object[] {new ItemStack(blockQuartzFence, 1, 0), new ItemStack(Blocks.vine)});
		GameRegistry.addShapelessRecipe(new ItemStack(blockQuartzWall, 1, 1), new Object[] {new ItemStack(blockQuartzWall, 1, 0), new ItemStack(Blocks.vine)});

		GameRegistry.addShapelessRecipe(new ItemStack(blockQuartzFence, 1, 0), new Object[] {new ItemStack(blockQuartzFence, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(blockQuartzWall, 1, 0), new Object[] {new ItemStack(blockQuartzWall, 1, 1)});

		GameRegistry.addRecipe(new ItemStack(blockQuartzGate, 1), new Object[] { "SQS", "SQS", Character.valueOf('Q'), Blocks.quartz_block, Character.valueOf('S'), Items.quartz});
		GameRegistry.addRecipe(new ItemStack(blockQuartzGate, 1), new Object[] { "SQS", "SQS", Character.valueOf('Q'), blockQuartzBlock, Character.valueOf('S'), Items.quartz});

		GameRegistry.addSmelting(oreCobalt, new ItemStack(itemIngotCobalt), 0.7F);
		GameRegistry.addSmelting(oreIridium, new ItemStack(itemIngotIridium), 1F);
	}

	public static void addAchievements() {
		achievementGettingStarted = new Achievement("achievement.gettingstarted", "gettingstarted", 0, 0, blockBasePillar, null).registerStat();

		achievementRecursion = new Achievement("achievement.recursion", "recursion", 1, 1, blockCraftingPillar, achievementGettingStarted).registerStat();
		achievementShowoff = new Achievement("achievement.showoff", "showoff", 3, 1, blockDisplayPillar, achievementRecursion).registerStat();
		//achievementRecursion3 = new Achievement("achievement.recursion3", "recursion3", 5, 1, blockChristmasPresent, achievementShowoff).registerStat();
		achievementCompressingLiquids = new Achievement("achievement.liquids", "liquids", 1, 2, blockFreezerPillar, achievementGettingStarted).registerStat();
		achievementDisc = new Achievement("achievement.elysiandisc", "elysiandisc", 4, 4, itemRecordElysium, achievementChristmas).setSpecial().registerStat();

		if (winter)
			achievementPage = new AchievementPage(Magistics.modid, achievementGettingStarted, achievementRecursion, achievementShowoff, achievementCompressingLiquids, achievementRecursion3, achievementChristmas, achievementDisc);
		else
			achievementPage = new AchievementPage(Magistics.modid, achievementGettingStarted, achievementRecursion, achievementShowoff, achievementCompressingLiquids, achievementRecursion3, achievementDisc);

		AchievementPage.registerAchievementPage(achievementPage);
	}

	public static void writeOreDictionary() {
		OreDictionary.registerOre("record", itemRecordElysium);
		OreDictionary.registerOre("oreMithril", blockMithrilOre);
		OreDictionary.registerOre("ingotMithril", itemMithrilIngot);
		OreDictionary.registerOre("nuggetMithril", itemMithrilNugget);
		OreDictionary.registerOre("stickQuartz", itemQuartzRod);
		OreDictionary.registerOre("leather", Items.leather);

		OreDictionary.registerOre("dyePink", itemAsphodelPetals);
		OreDictionary.registerOre("logWood", new ItemStack(blockLog, 1, 0));
		OreDictionary.registerOre("logWood", new ItemStack(blockLog, 1, 1));
		OreDictionary.registerOre("logWood", new ItemStack(blockLog, 1, 2));
		OreDictionary.registerOre("plankWood", new ItemStack(blockPlanks, 1, 0));
		OreDictionary.registerOre("plankWood", new ItemStack(blockPlanks, 1, 1));
		OreDictionary.registerOre("treeSapling", new ItemStack(blockSapling, 1, 0));
		OreDictionary.registerOre("treeSapling", new ItemStack(blockSapling, 1, 1));
		OreDictionary.registerOre("treeLeaves", new ItemStack(blockLeaves, 1, 0));
		OreDictionary.registerOre("treeLeaves", new ItemStack(blockLeaves, 1, 1));

		OreDictionary.registerOre("oreIridium", oreIridium);
		OreDictionary.registerOre("oreSulphure", oreSulphure);
		OreDictionary.registerOre("oreBeryl", oreBeryl);
		OreDictionary.registerOre("oreCobalt", oreCobalt);
		OreDictionary.registerOre("oreJade", oreJade);
		OreDictionary.registerOre("oreSilicon", oreSilicon);
		OreDictionary.registerOre("oreTourmaline", oreTourmaline);

		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.stick, 6), new Object[] {"X", "X", "X", Character.valueOf('X'), "plankWood"}));
	}

	public static void initAPI() {
		DiskRegistry.addDiskTexture(itemRecordElysium, "magistics:textures/models/pillars/disk_elysium.png");

		DiskRegistry.addDiskTexture(Items.record_13, "magistics:textures/models/pillars/disk_13.png");
		DiskRegistry.addDiskTexture(Items.record_cat, "magistics:textures/models/pillars/disk_cat.png");
		DiskRegistry.addDiskTexture(Items.record_blocks, "magistics:textures/models/pillars/disk_blocks.png");
		DiskRegistry.addDiskTexture(Items.record_chirp, "magistics:textures/models/pillars/disk_chirp.png");
		DiskRegistry.addDiskTexture(Items.record_far, "magistics:textures/models/pillars/disk_far.png");
		DiskRegistry.addDiskTexture(Items.record_mall, "magistics:textures/models/pillars/disk_mall.png");
		DiskRegistry.addDiskTexture(Items.record_mellohi, "magistics:textures/models/pillars/disk_mellohi.png");
		DiskRegistry.addDiskTexture(Items.record_stal, "magistics:textures/models/pillars/disk_stal.png");
		DiskRegistry.addDiskTexture(Items.record_strad, "magistics:textures/models/pillars/disk_strad.png");
		DiskRegistry.addDiskTexture(Items.record_ward, "magistics:textures/models/pillars/disk_ward.png");
		DiskRegistry.addDiskTexture(Items.record_11, "magistics:textures/models/pillars/disk_11.png");
		DiskRegistry.addDiskTexture(Items.record_wait, "magistics:textures/models/pillars/disk_wait.png");

		SentryBehaviorRegistry.addBehavior(Items.arrow, new SentryBehaviorArrow());
		SentryBehaviorRegistry.addBehavior(Items.snowball, new SentryBehaviorSnowball());
		SentryBehaviorRegistry.addBehavior(Items.fire_charge, new SentryBehaviorFireball());
		SentryBehaviorRegistry.addBehavior(Items.potionitem, new SentryBehaviorPotion());
		SentryBehaviorRegistry.addBehavior(Items.egg, new SentryBehaviorEgg());

		ElysiumPlants.addGrassPlant(blockTallGrass, 0, 30);
		ElysiumPlants.addGrassPlant(blockFlower, 0, 10);
		ElysiumPlants.addGrassSeed(new ItemStack(itemSeedsPepper), 10);

		ElysiumStaff.registerThrowableBlock(Blocks.snow, 1F);
		ElysiumStaff.registerThrowableBlock(Blocks.dirt, 2F);
		ElysiumStaff.registerThrowableBlock(Blocks.grass, 2F);
		ElysiumStaff.registerThrowableBlock(Blocks.mycelium, 2F);
		ElysiumStaff.registerThrowableBlock(Blocks.sand, 2F);
		ElysiumStaff.registerThrowableBlock(Blocks.soul_sand, 2F);
		ElysiumStaff.registerThrowableBlock(Blocks.gravel, 2F);
		ElysiumStaff.registerThrowableBlock(Blocks.clay, 2F);
		ElysiumStaff.registerThrowableBlock(Blocks.netherrack, 2F);
		ElysiumStaff.registerThrowableBlock(Blocks.sandstone, 3F);
		ElysiumStaff.registerThrowableBlock(Blocks.mossy_cobblestone, 3F);
		ElysiumStaff.registerThrowableBlock(Blocks.stained_hardened_clay, 3F);
		ElysiumStaff.registerThrowableBlock(Blocks.cobblestone, 4F);
		ElysiumStaff.registerThrowableBlock(Blocks.end_stone, 4F);
		ElysiumStaff.registerThrowableBlock(Blocks.stone, 4F);
		ElysiumStaff.registerThrowableBlock(Blocks.ice, 4F);
		ElysiumStaff.registerThrowableBlock(Blocks.packed_ice, 6F);
		ElysiumStaff.registerThrowableBlock(Blocks.obsidian, 8F);

		ElysiumStaff.registerThrowableBlock(blockDirt, 2F);
		ElysiumStaff.registerThrowableBlock(blockGrass, 2F);
		ElysiumStaff.registerThrowableBlock(blockRilt, 3F);
		ElysiumStaff.registerThrowableBlock(blockSand, 2F);
		ElysiumStaff.registerThrowableBlock(blockPalestone, 4F);
		ElysiumStaff.registerThrowableBlock(blockEnergyCrystal, 10F);

		SentryBehaviorRegistry.addBehavior(itemStaff, new SentryBehaviorStaff());

		FreezerRecipes.addRecipe(elysiumFluidEnergy, new ItemStack(blockEnergyCrystal, 1, 0));
		FreezerRecipes.addRecipe(elysiumFluidWater, new ItemStack(Blocks.ice, 1, 0));
		ThaumcraftApi.addShapelessArcaneCraftingRecipe("", new ItemStack(itemToothIngot, 1, 0), new AspectList().add(Aspect.FIRE, 10), new Object[] {new ItemStack(itemHardPaw), ItemApi.getItem("itemResource", 2)});

		ElysiumAspects.addAspects();
		//ElysiumRecipes.addRecipes();
		ElysiumResearch.addResearch();

		if(Loader.isModLoaded("coloredlightscore"))
		{
			CLApi.setBlockColorRGB(blockElysiumEnergyLiquid, 1F, 1F, 0F);
			CLApi.setBlockColorRGB(oreBeryl, 0, 8, 15, 1);
		}
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