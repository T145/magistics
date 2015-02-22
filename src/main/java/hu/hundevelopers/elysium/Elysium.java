package hu.hundevelopers.elysium;

import hu.hundevelopers.elysium.api.Plants;
import hu.hundevelopers.elysium.api.Staff;
import hu.hundevelopers.elysium.block.ElysiumBlock;
import hu.hundevelopers.elysium.block.ElysiumBlockCactus;
import hu.hundevelopers.elysium.block.ElysiumBlockFalling;
import hu.hundevelopers.elysium.block.ElysiumBlockFlower;
import hu.hundevelopers.elysium.block.ElysiumBlockGrass;
import hu.hundevelopers.elysium.block.ElysiumBlockLeaves;
import hu.hundevelopers.elysium.block.ElysiumBlockLog;
import hu.hundevelopers.elysium.block.ElysiumBlockOre;
import hu.hundevelopers.elysium.block.ElysiumBlockPortalCore;
import hu.hundevelopers.elysium.block.ElysiumBlockQuartz;
import hu.hundevelopers.elysium.block.ElysiumBlockQuartzFence;
import hu.hundevelopers.elysium.block.ElysiumBlockQuartzGate;
import hu.hundevelopers.elysium.block.ElysiumBlockQuartzWall;
import hu.hundevelopers.elysium.block.ElysiumBlockRilt;
import hu.hundevelopers.elysium.block.ElysiumBlockSapling;
import hu.hundevelopers.elysium.block.ElysiumBlockTallGrass;
import hu.hundevelopers.elysium.block.ElysiumBlockWood;
import hu.hundevelopers.elysium.block.ElysiumEnergyCrystalBlock;
import hu.hundevelopers.elysium.block.ElysiumEnergyCrystalItemBlock;
import hu.hundevelopers.elysium.block.ElysiumEnergyLiquid;
import hu.hundevelopers.elysium.block.ElysiumFenceItemBlock;
import hu.hundevelopers.elysium.block.ElysiumFloatingBlock;
import hu.hundevelopers.elysium.block.ElysiumFlowerItemBlock;
import hu.hundevelopers.elysium.block.ElysiumGrapesBush;
import hu.hundevelopers.elysium.block.ElysiumLeavesItemBlock;
import hu.hundevelopers.elysium.block.ElysiumLogItemBlock;
import hu.hundevelopers.elysium.block.ElysiumPipeBlock;
import hu.hundevelopers.elysium.block.ElysiumPlanksItemBlock;
import hu.hundevelopers.elysium.block.ElysiumQuartzItemBlock;
import hu.hundevelopers.elysium.block.ElysiumRaspberryBush;
import hu.hundevelopers.elysium.block.ElysiumSaplingItemBlock;
import hu.hundevelopers.elysium.block.ElysiumTallGrassItemBlock;
import hu.hundevelopers.elysium.block.ElysiumWallItemBlock;
import hu.hundevelopers.elysium.block.ElysiumWaterBlock;
import hu.hundevelopers.elysium.entity.EntityCaterPillar;
import hu.hundevelopers.elysium.entity.EntityDeer;
import hu.hundevelopers.elysium.entity.EntityEnderMage;
import hu.hundevelopers.elysium.entity.EntityEvolvedOyster;
import hu.hundevelopers.elysium.entity.EntityHero;
import hu.hundevelopers.elysium.entity.EntityPinkUnicorn;
import hu.hundevelopers.elysium.entity.EntitySwan;
import hu.hundevelopers.elysium.entity.EntityVoidSpecter;
import hu.hundevelopers.elysium.entity.projectile.EntityBlockProjectile;
import hu.hundevelopers.elysium.entity.projectile.EntityEnderRandomProjectile;
import hu.hundevelopers.elysium.entity.projectile.EntityFireballProjectile;
import hu.hundevelopers.elysium.entity.projectile.EntityIceProjectile;
import hu.hundevelopers.elysium.entity.projectile.SentryBehaviorStaff;
import hu.hundevelopers.elysium.event.ElysiumClientHandler;
import hu.hundevelopers.elysium.event.ElysiumHandler;
import hu.hundevelopers.elysium.heat.HeatManager;
import hu.hundevelopers.elysium.item.ElysiumBucket;
import hu.hundevelopers.elysium.item.ElysiumGrapesItem;
import hu.hundevelopers.elysium.item.ElysiumItem;
import hu.hundevelopers.elysium.item.ElysiumItemArmor;
import hu.hundevelopers.elysium.item.ElysiumItemAxe;
import hu.hundevelopers.elysium.item.ElysiumItemDebug;
import hu.hundevelopers.elysium.item.ElysiumItemHoe;
import hu.hundevelopers.elysium.item.ElysiumItemOverkill;
import hu.hundevelopers.elysium.item.ElysiumItemPickaxe;
import hu.hundevelopers.elysium.item.ElysiumItemRaspberry;
import hu.hundevelopers.elysium.item.ElysiumItemShovel;
import hu.hundevelopers.elysium.item.ElysiumItemSword;
import hu.hundevelopers.elysium.item.ElysiumItemWhistle;
import hu.hundevelopers.elysium.item.ElysiumStaffItem;
import hu.hundevelopers.elysium.proxy.CommonProxy;
import hu.hundevelopers.elysium.thaumcraft.ElysiumAspects;
import hu.hundevelopers.elysium.thaumcraft.ElysiumRecipes;
import hu.hundevelopers.elysium.thaumcraft.ElysiumResearch;
import hu.hundevelopers.elysium.thaumcraft.wand.ElysiumWandCap;
import hu.hundevelopers.elysium.thaumcraft.wand.ElysiumWandRod;
import hu.hundevelopers.elysium.thaumcraft.wand.ItemWandCaps;
import hu.hundevelopers.elysium.thaumcraft.wand.ItemWandCores;
import hu.hundevelopers.elysium.tile.ElysianTileEntityPortal;
import hu.hundevelopers.elysium.world.ElysiumWorldProvider;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenBeach;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenCorruption;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenDesert;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenForest;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenOcean;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenPlain;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenRiver;
import hu.hundevelopers.elysium.world.gen.WorldGenElysium;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import T145.magistics.api.FreezerRecipes;
import T145.magistics.api.sentry.SentryBehaviorRegistry;
import T145.magistics.common.config.ConfigObjects;
import coloredlightscore.src.api.CLApi;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Elysium.ID, name=Elysium.NAME, version = Elysium.VERSION, dependencies = "after:Thaumcraft;after:coloredlightscore;required-after:craftingpillars")
public class Elysium
{
    public static final String ID = "elysium";
    public static final String NAME = "The Elysium";
    public static final String VERSION = "1.0";
    
    @Instance(Elysium.ID)
	private static Elysium instance;

	public static Elysium getInstance()
	{
		return instance;
	}

	public static boolean modLights = false;
	public static boolean modThaumcraft = false;
	
	@SidedProxy(clientSide = "hu.hundevelopers.elysium.proxy.ClientProxy", serverSide = "hu.hundevelopers.elysium.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Configuration config;
	public static final CreativeTabs tabElysium = new CreativeTabs("elysium")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(blockPalestone);
		}
	};

	/** Dimension ID **/
	public static int dimensionID;
	public static int maxDragon;
	public static boolean isMenuEnabled;
	public static boolean isMobCrystals;
	public static boolean isPatreonButton;

	//Rendering ids

	//Fluids
	public static Fluid fluidElysiumWater;
	public static Fluid fluidElysiumEnergy;

	private static Fluid elysiumFluidWater;
	private static Fluid elysiumFluidEnergy;

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
	
	public static Block blockPipe;

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
	
	/** Biomes **/
	public static BiomeGenBase biomePlain = null;
	public static BiomeGenBase biomeForest = null;
	public static BiomeGenBase biomeCorruption = null;
	public static BiomeGenBase biomeForestCorrupt = null;
	public static BiomeGenBase biomeOcean = null;
	public static BiomeGenBase biomeRiver = null;
	public static BiomeGenBase biomeDesert = null;
	public static BiomeGenBase biomeBeach = null;
	public static BiomeGenBase biomeDeepOcean = null;

	private int biomeIdPlains;
	private int biomeIdForest;
	private int biomeIdPlainsCorrupt;
	private int biomeIdForestCorrupt;
	private int biomeIdOcean;
	private int biomeIdDeepOcean;
	private int biomeIdRiver;
	private int biomeIdDesert;
	private int biomeIdBeach;
	
	public static final String LABYRINTH_LOOT = "labirinthLootChest";

	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	elysiumFluidWater = new Fluid("elysium_water");
		FluidRegistry.registerFluid(elysiumFluidWater);
		fluidElysiumWater = FluidRegistry.getFluid("elysium_water");
		
		if (fluidElysiumWater.getBlock() == null)
		{
			blockElysiumWater = new ElysiumWaterBlock(fluidElysiumWater, Material.water).setHardness(100.0F).setLightOpacity(3).setBlockName("elysium_water");
			registerBlock(blockElysiumWater);
			fluidElysiumWater.setBlock(blockElysiumWater);
		} else {
			blockElysiumWater = fluidElysiumWater.getBlock();
		}
		
		if (blockElysiumWater != null) {
			itemWaterBucket = new ElysiumBucket(blockElysiumWater).setTextureName("elysium_bucket_water").setUnlocalizedName("elysium_bucket_water");
			itemWaterBucket.setContainerItem(Items.bucket);
			registerItem(itemWaterBucket);
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("elysium_water", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(itemWaterBucket), new ItemStack(Items.bucket));
		}
		
		//Liquids

    	elysiumFluidEnergy = new Fluid("elysium_energy");
    	elysiumFluidEnergy.setDensity(2000).setLuminosity(15).setTemperature(500);
		FluidRegistry.registerFluid(elysiumFluidEnergy);
		fluidElysiumEnergy = FluidRegistry.getFluid("elysium_energy");
		
		if (fluidElysiumEnergy.getBlock() == null)
		{
			blockElysiumEnergyLiquid = new ElysiumEnergyLiquid(fluidElysiumEnergy, Material.water).setHardness(100.0F).setLightOpacity(3).setBlockName("elysium_energy").setCreativeTab(tabElysium);
			registerBlock(blockElysiumEnergyLiquid);
			fluidElysiumEnergy.setBlock(blockElysiumEnergyLiquid);
		} else {
			blockElysiumEnergyLiquid = fluidElysiumEnergy.getBlock();
		}
		
		itemEnergyBucket = new ElysiumBucket(blockElysiumEnergyLiquid).setTextureName("elysium_bucket_energy").setUnlocalizedName("elysium_bucket_energy");
		itemEnergyBucket.setContainerItem(Items.bucket);
		registerItem(itemEnergyBucket);
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("elysium_energy", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(itemEnergyBucket), new ItemStack(Items.bucket));
	
		//
		
		ElysiumHandler.INSTANCE.buckets.put(blockElysiumWater, itemWaterBucket);
		ElysiumHandler.INSTANCE.buckets.put(blockElysiumEnergyLiquid, itemEnergyBucket);

		
    	//Config
		config = new Configuration(new File(event.getModConfigurationDirectory(), "Elysium.cfg"));
		
		try
		{
			config.load();

			Property ELYSIUM_PLAINS = Elysium.config.get("biomeIds", "ELYSIUM_PLAINS", Configs.BIOME_PLAIN);
			biomeIdPlains = ELYSIUM_PLAINS.getInt();

			Property ELYSIUM_FOREST = Elysium.config.get("biomeIds", "ELYSIUM_FOREST", Configs.BIOME_FOREST);
			biomeIdForest = ELYSIUM_FOREST.getInt();

			Property ELYSIUM_PLAINS_CORRUPT = Elysium.config.get("biomeIds", "ELYSIUM_PLAINS_CORRUPT", Configs.BIOME_PLAIN_CORRUPT);
			biomeIdPlainsCorrupt = ELYSIUM_PLAINS_CORRUPT.getInt();

			Property ELYSIUM_DEEP_OCEAN = Elysium.config.get("biomeIds", "ELYSIUM_DEEP_OCEAN", Configs.ELYSIUM_DEEP_OCEAN);
			biomeIdDeepOcean = ELYSIUM_DEEP_OCEAN.getInt();

			Property ELYSIUM_OCEAN = Elysium.config.get("biomeIds", "ELYSIUM_OCEAN", Configs.BIOME_OCEAN);
			biomeIdOcean = ELYSIUM_OCEAN.getInt();

			Property ELYSIUM_RIVER = Elysium.config.get("biomeIds", "ELYSIUM_RIVER", Configs.BIOME_RIVER);
			biomeIdRiver = ELYSIUM_RIVER.getInt();

			Property ELYSIUM_DESERT = Elysium.config.get("biomeIds", "ELYSIUM_DESERT", Configs.BIOME_DESERT);
			biomeIdDesert = ELYSIUM_DESERT.getInt();

			Property ELYSIUM_BEACH = Elysium.config.get("biomeIds", "ELYSIUM_BEACH", Configs.BIOME_BEACH);
			biomeIdBeach = ELYSIUM_BEACH.getInt();
			
			

			Property ELYSIUM_ID = Elysium.config.get("other", "ELYSIUM_ID", DimensionManager.getNextFreeDimId());
			dimensionID = ELYSIUM_ID.getInt();
			
			
			
			Property MAX_DRAGON_IN_END = Elysium.config.get("other", "MAX_DRAGON_IN_END", Configs.MAX_DRAGON_IN_END, "How many dragons can be spawned to the End at the same time!");
			maxDragon = MAX_DRAGON_IN_END.getInt();

			Property MENU_ENABLED = Elysium.config.get("other", "isMenuEnabled", Configs.customGui, "If you want to see the custom The Elysium menu instead of the regular Minecraft menu");
			isMenuEnabled = MENU_ENABLED.getBoolean();

//			Property PATREON_BUTTON = Elysium.config.get("other", "isPatreonButtonEnabled", true);
//			isPatreonButton = PATREON_BUTTON.getBoolean();
			
//			Property MOB_CRYSTAL_RENDERING = Elysium.config.get("other", "isMobCrystals", Configs.isMobCrystals, "If you want to see corrupted crystals on endermen and other mobs");
//			isMobCrystals = MOB_CRYSTAL_RENDERING.getBoolean();
			
//			Property updateCheck = Elysium.config.get(Configuration.CATEGORY_GENERAL, "update.check", true);
//			updateCheck.comment = "set to true for version check on startup";
//			if (updateCheck.getBoolean(true)) {
//				Version.check();
//			}
			
	    }
		finally
		{
			config.save();
		}
		
		MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event)
    {
		if (event.map.getTextureType() == 0)
		{
			elysiumFluidWater.setIcons(blockElysiumWater.getBlockTextureFromSide(1), blockElysiumWater.getBlockTextureFromSide(2));
			elysiumFluidEnergy.setIcons(blockElysiumEnergyLiquid.getBlockTextureFromSide(1), blockElysiumEnergyLiquid.getBlockTextureFromSide(2));
		}
	}
    
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		//Block Registering

		blockPalestone = (new ElysiumBlock(Material.rock)).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone").setBlockTextureName("palestone");
		registerBlock(blockPalestone);
		
		blockDirt = (new ElysiumBlock(Material.ground)).setHardness(0.5F).setStepSound(Block.soundTypeGravel).setBlockName("elysium_dirt").setBlockTextureName("dirt");
		registerBlock(blockDirt);

		blockGrass = (new ElysiumBlockGrass(Material.ground)).setHardness(0.6F).setStepSound(Block.soundTypeGrass).setBlockName("elysium_grass").setBlockTextureName("grass");
		registerBlock(blockGrass);

		blockSand = (new ElysiumBlockFalling(Material.sand)).setHardness(0.5F).setStepSound(Block.soundTypeSand).setBlockName("leucosand").setBlockTextureName("sand");
		registerBlock(blockSand);

		blockRilt = (new ElysiumBlockRilt(Material.sand)).setHardness(0.6F).setStepSound(Block.soundTypeGravel).setBlockTextureName("rilt").setBlockName("rilt");
		registerBlock(blockRilt);
	
		blockSapling = (new ElysiumBlockSapling()).setHardness(0F).setBlockTextureName("elysium_sapling").setBlockName("elysium_sapling");
		registerBlock(blockSapling, ElysiumSaplingItemBlock.class);

		blockLog = (new ElysiumBlockLog()).setHardness(2.0F).setBlockTextureName("elysium_log").setBlockName("elysium_log");
		registerBlock(blockLog, ElysiumLogItemBlock.class);

		blockLeaves = (new ElysiumBlockLeaves()).setLightOpacity(1).setHardness(0.2F).setStepSound(Block.soundTypeGrass).setBlockTextureName("elysium_leaves").setBlockName("elysium_leaves");
		registerBlock(blockLeaves, ElysiumLeavesItemBlock.class);

		blockPlanks = (new ElysiumBlockWood()).setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockTextureName("elysium_planks").setBlockName("elysium_planks");
		registerBlock(blockPlanks, ElysiumPlanksItemBlock.class);

//		blockGastroShell = (new ElysiumBlockGastroShell(idGastroShellBlock.getInt(), Material.rock)).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setBlockName("gastroshell");
//		registerBlock(blockGastroShell, "Gastro Shell");

		blockFlower = (new ElysiumBlockFlower()).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("elysium_flower").setBlockName("elysium_flower");
		registerBlock(blockFlower, ElysiumFlowerItemBlock.class);

		blockTallGrass = new ElysiumBlockTallGrass().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("elysium_tallgrass").setBlockName("elysium_tallgrass");
		registerBlock(blockTallGrass, ElysiumTallGrassItemBlock.class);

		oreSulphure = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreSulphur").setBlockName("oreSulphur");
		registerBlock(oreSulphure);

		oreBeryl = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setLightLevel(1F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreBeryl").setBlockName("oreBeryl");
		registerBlock(oreBeryl);

		oreCobalt = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreCobalt").setBlockName("oreCobalt");
		registerBlock(oreCobalt);

		oreIridium = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreIridium").setBlockName("oreIridium");
		registerBlock(oreIridium);

		oreSilicon = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreSilicon").setBlockName("oreSilicon");
		registerBlock(oreSilicon);

		oreJade = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreJade").setBlockName("oreJade");
		registerBlock(oreJade);

		oreTourmaline = new ElysiumBlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockTextureName("oreTourmaline").setBlockName("oreTourmaline");
		registerBlock(oreTourmaline);
		
		blockPortalCore = new ElysiumBlockPortalCore(Material.glass).setHardness(5F).setStepSound(Block.soundTypeGlass).setBlockName("portalCore");
		registerBlock(blockPortalCore);

		blockFloatingConch = new ElysiumFloatingBlock("conch").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("floating_block_conch").setBlockName("floating_block_conch");
		registerBlock(blockFloatingConch);
		
		blockFloatingShell = new ElysiumFloatingBlock("shell").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("floating_block_shell").setBlockName("floating_block_shell");
		registerBlock(blockFloatingShell);

		Blocks.dragon_egg.setCreativeTab(tabElysium);

		/*
		blockPalestoneBrick = (new ElysiumBlockHeatable(idPalestoneBrickBlock.getInt(), Material.rock, -273, 300)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick");
		registerBlock(blockPalestoneBrick, "Palestone Brick");

		blockPalestoneBrickCracked = (new ElysiumBlockHeatable(idPalestoneBrickCrackedBlock.getInt(), Material.rock, -273, 300)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick_cracked");
		registerBlock(blockPalestoneBrickCracked, "Cracked Palestone Brick");

		blockPalestoneBrickMossy = (new ElysiumBlockHeatable(idPalestoneBrickMossyBlock.getInt(), Material.rock, -273, 300)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick_mossy");
		registerBlock(blockPalestoneBrickMossy, "Mossy Palestone Brick");

		blockPalestoneBrickChiseld = (new ElysiumBlockHeatable(idPalestoneChiseldBrickBlock.getInt(), Material.rock, -273, 300)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone_brick_chiseld");
		registerBlock(blockPalestoneBrickChiseld, "Chiseld Palestone Brick");

		blockPalestonePillar = (new ElysiumBlockPalestonePillar(idPalestonePillarBlock.getInt(), Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("palestone_pillar");
		registerBlock(blockPalestonePillar, "Palestone Pillar");

		*/

		blockSulphure = new ElysiumBlock(Material.rock).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("blockSulphur").setBlockName("blockSulphur");
		registerBlock(blockSulphure);

		blockBeryl = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setLightLevel(0.5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockBeryl").setBlockName("blockBeryl");
		registerBlock(blockBeryl);

		blockCobalt = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockCobalt").setBlockName("blockCobalt");
		registerBlock(blockCobalt);

		blockIridium = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockIridium").setBlockName("blockIridium");
		registerBlock(blockIridium);

		blockSilicon = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("blockSilicon").setBlockName("blockSilicon");
		registerBlock(blockSilicon);

		blockJade = new ElysiumBlock(Material.rock).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("blockJade").setBlockName("blockJade");
		registerBlock(blockJade);

		blockTourmaline = new ElysiumBlock(Material.iron).setHardness(3F).setResistance(5F).setStepSound(Block.soundTypeStone).setBlockTextureName("blockTourmaline").setBlockName("blockTourmaline");
		registerBlock(blockTourmaline);

		blockEnergyCrystal = new ElysiumEnergyCrystalBlock(Material.glass).setHardness(3F).setResistance(4F).setStepSound(Block.soundTypeGlass).setLightLevel(1.0F).setBlockTextureName("energy_crystal").setBlockName("energy_crystal");
		registerBlock(blockEnergyCrystal, ElysiumEnergyCrystalItemBlock.class);

		blockPipe = new ElysiumPipeBlock(Material.rock).setHardness(0.3F).setStepSound(Block.soundTypeStone).setBlockTextureName("palestone").setBlockName("stone_pipe").setCreativeTab(null);
		registerBlock(blockPipe);

		blockCactus = new ElysiumBlockCactus().setHardness(0F).setResistance(1F).setStepSound(Block.soundTypeGrass).setBlockTextureName("hallowedcactus").setBlockName("blockCactus");
		registerBlock(blockCactus);

		blockRaspberryBush = new ElysiumRaspberryBush().setHardness(0F).setResistance(1F).setStepSound(Block.soundTypeGrass).setBlockTextureName("raspberrybushwithoutberries").setBlockName("blockRaspberryBush");
		registerBlock(blockRaspberryBush);


		blockGrapesBush = new ElysiumGrapesBush().setHardness(0F).setResistance(1F).setStepSound(Block.soundTypeGrass).setBlockTextureName("grapes_empty").setBlockName("blockGrapes");
		registerBlock(blockGrapesBush);
		
		blockQuartzBlock = new ElysiumBlockQuartz().setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockTextureName(ID + ":quartz_block").setBlockName("quartz_mossy");
		registerBlock(blockQuartzBlock, ElysiumQuartzItemBlock.class);

		blockQuartzFence = new ElysiumBlockQuartzFence(blockQuartzBlock).setBlockName("quartzFence");
		registerBlock(blockQuartzFence, ElysiumFenceItemBlock.class);

		blockQuartzWall = new ElysiumBlockQuartzWall(blockQuartzBlock).setBlockName("quartzWall");
		registerBlock(blockQuartzWall, ElysiumWallItemBlock.class);
		
		blockQuartzGate = new ElysiumBlockQuartzGate().setBlockName("quartzGate");
		registerBlock(blockQuartzGate);
		
		
		//Items
		itemWhistle = new ElysiumItemWhistle().setTextureName("enderflute").setUnlocalizedName("enderflute");
		registerItem(itemWhistle);

		itemSeedsPepper = new ElysiumItem().setTextureName("seeds_pepper").setUnlocalizedName("seeds_pepper");
		registerItem(itemSeedsPepper);

		itemOverKill = new ElysiumItemOverkill().setTextureName("overkill").setUnlocalizedName("overkill").setCreativeTab(null);
		registerItem(itemOverKill);

		itemAsphodelPetals = new ElysiumItem().setTextureName("asphodelpetal").setUnlocalizedName("asphodelpetal");
		registerItem(itemAsphodelPetals);

		itemDebug = new ElysiumItemDebug().setTextureName("debug").setUnlocalizedName("debug");
		registerItem(itemDebug);

		itemBeryl = new ElysiumItem().setTextureName("beryl").setUnlocalizedName("beryl");
		registerItem(itemBeryl);

		itemIngotCobalt = new ElysiumItem().setTextureName("ingotCobalt").setUnlocalizedName("ingotCobalt");
		registerItem(itemIngotCobalt);

		itemIngotIridium = new ElysiumItem().setTextureName("ingotIridium").setUnlocalizedName("ingotIridium");
		registerItem(itemIngotIridium);

		itemJade = new ElysiumItem().setTextureName("jade").setUnlocalizedName("jade");
		registerItem(itemJade);

		itemSiliconChunk = new ElysiumItem().setTextureName("siliconchunk").setUnlocalizedName("siliconchunk");
		registerItem(itemSiliconChunk);

		itemSulphur = new ElysiumItem().setTextureName("sulphur").setUnlocalizedName("elysium_sulphur");
		registerItem(itemSulphur);

		itemTourmaline = new ElysiumItem().setTextureName("tourmaline").setUnlocalizedName("tourmaline");
		registerItem(itemTourmaline);

		itemSturdyHide = new ElysiumItem().setTextureName("sturdyHide").setUnlocalizedName("sturdyHide");
		registerItem(itemSturdyHide);


		itemStaff = new ElysiumStaffItem().setTextureName("staff").setUnlocalizedName("staff");
		registerItem(itemStaff);

		itemAntler = new ElysiumItem().setTextureName("antler").setUnlocalizedName("antler");
		registerItem(itemAntler);

		itemDeerPelt = new ElysiumItem().setTextureName("deer_pelt").setUnlocalizedName("deer_pelt");
		registerItem(itemDeerPelt);

		itemGrapes = new ElysiumGrapesItem().setTextureName("grapes").setUnlocalizedName("grapes");
		registerItem(itemGrapes);

		itemRaspberry = new ElysiumItemRaspberry(2).setTextureName("raspberry").setUnlocalizedName("raspberry");
		registerItem(itemRaspberry);
		
		itemHardPaw = new ElysiumItem().setTextureName("hard_paw").setUnlocalizedName("hard_paw");
		registerItem(itemHardPaw);

		itemKnife = new ElysiumItem().setTextureName("knife").setMaxStackSize(1).setUnlocalizedName("knife");
		registerItem(itemKnife);
		
		itemToothIngot = new ElysiumItem().setTextureName("ingotTooth").setUnlocalizedName("ingotTooth");
		registerItem(itemToothIngot);

		
		//Tool Registering

		Item.ToolMaterial FOSTIMBER_MAT = EnumHelper.addToolMaterial("FOSTIMBER", 0, 59, 2.0F, 0, 15);

		itemSwordFostimber = new ElysiumItemSword(FOSTIMBER_MAT).setTextureName("swordFostimber").setUnlocalizedName("swordFostimber");
		registerItem(itemSwordFostimber);

		itemPickaxeFostimber = new ElysiumItemPickaxe(FOSTIMBER_MAT).setTextureName("pickaxeFostimber").setUnlocalizedName("pickaxeFostimber");
		registerItem(itemPickaxeFostimber);

		itemAxeFostimber = new ElysiumItemAxe(FOSTIMBER_MAT).setTextureName("axeFostimber").setUnlocalizedName("axeFostimber");
		registerItem(itemAxeFostimber);

		itemShovelFostimber = new ElysiumItemShovel(FOSTIMBER_MAT).setTextureName("shovelFostimber").setUnlocalizedName("shovelFostimber");
		registerItem(itemShovelFostimber);

		itemHoeFostimber = new ElysiumItemHoe(FOSTIMBER_MAT).setTextureName("hoeFostimber").setUnlocalizedName("hoeFostimber");
		registerItem(itemHoeFostimber);

		Item.ToolMaterial STONE_MAT = EnumHelper.addToolMaterial("PALESTONE", 1, 131, 4.0F, 1, 5);

		itemSwordPalestone = new ElysiumItemSword(STONE_MAT).setTextureName("swordPalestone").setUnlocalizedName("swordPalestone");
		registerItem(itemSwordPalestone);

		itemPickaxePalestone = new ElysiumItemPickaxe(STONE_MAT).setTextureName("pickaxePalestone").setUnlocalizedName("pickaxePalestone");
		registerItem(itemPickaxePalestone);

		itemAxePalestone = new ElysiumItemAxe(STONE_MAT).setTextureName("axePalestone").setUnlocalizedName("axePalestone");
		registerItem(itemAxePalestone);

		itemSpadePalestone = new ElysiumItemShovel(STONE_MAT).setTextureName("shovelPalestone").setUnlocalizedName("shovelPalestone");
		registerItem(itemSpadePalestone);

		itemHoePalestone = new ElysiumItemHoe(STONE_MAT).setTextureName("hoePalestone").setUnlocalizedName("hoePalestone");
		registerItem(itemHoePalestone);

		ArmorMaterial TOOTH_ARMORMAT = EnumHelper.addArmorMaterial("TOOTH", 25, new int[] { 2, 6, 5, 2 }, 25);

		itemArmorToothHelmet = new ElysiumItemArmor(TOOTH_ARMORMAT, 4, 0).setTextureName(ID + ":sceptertoothhelmet").setUnlocalizedName("toothHelmet");
		registerItem(itemArmorToothHelmet);
		
		itemArmorToothChestplate = new ElysiumItemArmor(TOOTH_ARMORMAT, 4, 1).setTextureName(ID + ":sceptertoothchestplate").setUnlocalizedName("toothChestplate");
		registerItem(itemArmorToothChestplate);

		itemArmorToothLeggings = new ElysiumItemArmor(TOOTH_ARMORMAT, 4, 2).setTextureName(ID + ":sceptertoothlegs").setUnlocalizedName("toothLeggings");
		registerItem(itemArmorToothLeggings);

		itemArmorToothBoots = new ElysiumItemArmor(TOOTH_ARMORMAT, 4, 3).setTextureName(ID + ":sceptertoothboots").setUnlocalizedName("toothBoots");
		registerItem(itemArmorToothBoots);
		
		
		wandCore = new ItemWandCores().setUnlocalizedName("WandCores");
		registerItem(wandCore);
		wandCap = new ItemWandCaps().setUnlocalizedName("WandCaps");
		registerItem(wandCap);

		wandCap.setCreativeTab(Elysium.tabElysium);
		wandCore.setCreativeTab(Elysium.tabElysium);
		
		
		WAND_ROD_HORN = new ElysiumWandRod("horn", 100, new ItemStack(wandCore, 1, 0), 12, new ResourceLocation(ID, "textures/models/wand_rod_horn.png"));
		
//		STAFF_ROD_HORN = new StaffRod("horn", 50, new ItemStack(wandCore, 1, 0 + 1), 24, new HornStaffUpdate(), new ResourceLocation(MODID, "textures/models/wand_rod_horn.png"));
		WAND_CAP_PURE = new ElysiumWandCap("pure", 0.0F, new ItemStack(wandCap, 1, 0), 2, new ResourceLocation(ID, "textures/models/wand_cap_crystal_pure.png"));
		WAND_CAP_CORRUPTED = new ElysiumWandCap("corrupted", 0.0F, new ItemStack(wandCap, 1, 1), 2, new ResourceLocation(ID, "textures/models/wand_cap_crystal_corrupted.png"));
		// WandCap WAND_CAP_IRON = new WandCap("iron", 1.1f, Arrays.asList(Aspect.ORDER),1, new ItemStack(ConfigItems.itemWandCap,1,0),1);

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

		
		//Thaumcraft
		FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(blockRaspberryBush, 1, 1));
		FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(blockGrapesBush, 1, OreDictionary.WILDCARD_VALUE));
//		FMLInterModComms.sendMessage("Thaumcraft", "dimensionBlacklist", dimensionID+":1");
		
		
		//Crafting Registering

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

		//Ore registry
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
        
        //OreDictionary recipes
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Items.stick, 6), new Object[] {"X", "X", "X", Character.valueOf('X'), "plankWood"}));

		//Smelting Registering

		GameRegistry.addSmelting(oreCobalt, new ItemStack(itemIngotCobalt), 0.7F);
		GameRegistry.addSmelting(oreIridium, new ItemStack(itemIngotIridium), 1.0F);

		//Handlers
		GameRegistry.registerFuelHandler(ElysiumHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ElysiumHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(ElysiumHandler.INSTANCE);
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
			MinecraftForge.EVENT_BUS.register(new ElysiumClientHandler());
		
		//TileEntity
		GameRegistry.registerTileEntity(ElysianTileEntityPortal.class, "ElysianTileEntityPortal");

		/** Register WorldProvider for Dimension **/
		DimensionManager.registerProviderType(dimensionID, ElysiumWorldProvider.class, false);
		DimensionManager.registerDimension(dimensionID, dimensionID);


		biomePlain = new ElysiumBiomeGenPlain(biomeIdPlains).setHeight(new Height(0.25F, 0.3F)).setColor(0x8ec435).setBiomeName("Elysium Plain");
		biomeForest = new ElysiumBiomeGenForest(biomeIdForest).setHeight(new Height(0.15F, 0.2F)).setColor(0x93a66f).setEnableSnow().setTemperatureRainfall(0.0F, 0.5F).setBiomeName("Elysium Forest");
		biomeCorruption = new ElysiumBiomeGenCorruption(biomeIdPlainsCorrupt).setHeight(new Height(0.05F, 0.1F)).setColor(0x987497).func_76733_a(9154376).setTemperatureRainfall(0.8F, 0.9F).setBiomeName("Elysium Corruption");
		
		biomeOcean = new ElysiumBiomeGenOcean(biomeIdOcean).setHeight(new Height(-1.0F, 0.1F)).setColor(0x73c6db).setBiomeName("Elysium Ocean");
		biomeRiver = new ElysiumBiomeGenRiver(biomeIdRiver).setHeight(new Height(-0.5F, 0.0F)).setColor(0x73c6db).setBiomeName("Elysium River");
		biomeDesert = new ElysiumBiomeGenDesert(biomeIdDesert).setHeight(new Height(0.3F, 0.1F)).setTemperatureRainfall(2.0F, 0.2F).setColor(0xc9c8ce).setBiomeName("Elysium Desert");
		biomeBeach = new ElysiumBiomeGenBeach(biomeIdBeach).setHeight(new Height(0.0F, 0.025F)).setColor(16440917).setTemperatureRainfall(0.8F, 0.4F).setBiomeName("Elysium Beach");

//		if(ConfigObjects.winter)
//		{
//			biomePlain.setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
//			biomeForest.setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
//			biomeCorruption.setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
//			
//			biomeOcean.setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
//			biomeRiver.setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
//			biomeDesert.setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
//			biomeBeach.setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
//		}
		
		GameRegistry.registerWorldGenerator(new WorldGenElysium(), 0);

		proxy.registerRenderers();
		
		ElysiumAspects.initAspects();
		
		//Entity Registering
		int caterPillarID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityCaterPillar.class, "ElysiumCaterPillar", caterPillarID, 0x6e6e6e, 0xcccccc);
        EntityRegistry.registerModEntity(EntityCaterPillar.class, "ElysiumCaterPillar", caterPillarID, this, 64, 2, true);

        int evolvedOysterID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityEvolvedOyster.class, "ElysiumEvolvedOyster", evolvedOysterID, 0x645c6a, 0xd893d6);
        EntityRegistry.registerModEntity(EntityEvolvedOyster.class, "ElysiumEvolvedOyster", evolvedOysterID, this, 64, 2, true);

        int swanID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntitySwan.class, "ElysiumSwan", swanID, 0xfafafa, 0xff9600);
        EntityRegistry.registerModEntity(EntitySwan.class, "ElysiumSwan", swanID, this, 64, 2, true);

        int deerID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityDeer.class, "ElysiumDeer", deerID, 0x969690, 0xffff00);
        EntityRegistry.registerModEntity(EntityDeer.class, "ElysiumDeer", deerID, this, 64, 2, true);
        
        int unicornID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityPinkUnicorn.class, "ElysiumUnicorn", unicornID, 0xffa8d4, 0xebebeb);
        EntityRegistry.registerModEntity(EntityPinkUnicorn.class, "ElysiumUnicorn", unicornID, this, 64, 2, true);

        int heroID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityHero.class, "ElysiumHero", heroID, 0xe4a9a4, 0xe4f202);
        EntityRegistry.registerModEntity(EntityHero.class, "ElysiumHero", heroID, this, 128, 3, true);

        int voidspecterID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityVoidSpecter.class, "ElysiumVoidSpecter", voidspecterID, 0x623464, 0x3A2A3A);
        EntityRegistry.registerModEntity(EntityVoidSpecter.class, "ElysiumVoidSpecter", voidspecterID, this, 64, 2, true);

        int endermageID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityEnderMage.class, "ElysiumEnderMage", endermageID, 0x000000, 0x9d8ca0);
        EntityRegistry.registerModEntity(EntityEnderMage.class, "ElysiumEnderMage", endermageID, this, 64, 2, true);

        int blockthrowableID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityBlockProjectile.class, "BlockProjectile", blockthrowableID, this, 64, 1, true);

        int iceprojectileID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityIceProjectile.class, "IceProjectile", iceprojectileID, this, 64, 1, true);

        int fireprojectileID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityFireballProjectile.class, "FireProjectile", fireprojectileID, this, 64, 1, true);

        int enderrandomprojectileID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityEnderRandomProjectile.class, "EnderRandomProjectile", enderrandomprojectileID, this, 64, 1, true);
      
      //Entites
	    ThaumcraftApi.registerEntityTag("ElysiumUnicorn", new AspectList().add(Aspect.MAGIC, 2).add(Aspect.CRYSTAL, 2).add(Aspect.BEAST, 4).add(ElysiumAspects.SANCTUS, 5));
	    ThaumcraftApi.registerEntityTag("ElysiumDeer", new AspectList().add(Aspect.MAGIC, 2).add(Aspect.CLOTH, 1).add(Aspect.BEAST, 4).add(ElysiumAspects.SANCTUS, 1));
	    ThaumcraftApi.registerEntityTag("ElysiumCaterPillar", new AspectList().add(Aspect.TRAVEL, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2).add(ElysiumAspects.SANCTUS, 1));
	    ThaumcraftApi.registerEntityTag("ElysiumSwan", new AspectList().add(Aspect.FLIGHT, 2).add(Aspect.WATER, 2).add(Aspect.BEAST, 2).add(ElysiumAspects.SANCTUS, 1));

	    ThaumcraftApi.registerEntityTag("ElysiumVoidSpecter", new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.ELDRITCH, 4));
	    ThaumcraftApi.registerEntityTag("ElysiumEnderMage", new AspectList().add(Aspect.MAGIC, 4).add(Aspect.ELDRITCH, 4));

	    ThaumcraftApi.registerEntityTag("ElysiumHero", new AspectList().add(Aspect.MAN, 4).add(Aspect.MIND, 4).add(ElysiumAspects.SANCTUS, 1));
	    
	    ThaumcraftApi.registerEntityTag("ElysiumEvolvedOyster", new AspectList().add(Aspect.TRAVEL, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2).add(ElysiumAspects.SANCTUS, 1));

	    
        //Entity Spawn
        EntityRegistry.addSpawn(EntityCaterPillar.class, 5, 3, 5, EnumCreatureType.creature, biomePlain);
        EntityRegistry.addSpawn(EntitySwan.class, 10, 3, 5, EnumCreatureType.creature, biomePlain);
        EntityRegistry.addSpawn(EntityDeer.class, 1, 1, 1, EnumCreatureType.creature, biomeForest);
        EntityRegistry.addSpawn(EntityPinkUnicorn.class, 1, 1, 1, EnumCreatureType.creature, biomeForest);
        EntityRegistry.addSpawn(EntityEnderMage.class, 7, 1, 1, EnumCreatureType.monster, biomeCorruption, biomeDesert);
        EntityRegistry.addSpawn(EntityVoidSpecter.class, 10, 2, 3, EnumCreatureType.monster, biomeCorruption, biomeDesert);
        EntityRegistry.addSpawn(EntityEnderman.class, 10, 2, 3, EnumCreatureType.monster, biomeCorruption, biomeDesert);
        EntityRegistry.addSpawn(EntityEvolvedOyster.class, 8, 2, 4, EnumCreatureType.creature, biomePlain);
        EntityRegistry.addSpawn(EntityHero.class, 10, 2, 5, EnumCreatureType.creature, biomePlain, biomeForest);
//        EntityRegistry.addSpawn(EntityHero.class, 1, 1, 1, EnumCreatureType.monster, biomePlain, biomeForest, biomeCorruption, biomeDesert, biomeOcean);

	    
        //Fire Info
        Blocks.fire.setFireInfo(blockLog, 2, 2);
        Blocks.fire.setFireInfo(blockPlanks, 2, 10);
        Blocks.fire.setFireInfo(blockLeaves, 15, 30);
        Blocks.fire.setFireInfo(oreSulphure, 2, 2);
        Blocks.fire.setFireInfo(blockSulphure, 5, 5);
        
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
        
//		elysiumCave = new CaveTypeElysium("Elysium Cave", blockPalestone);
    }
	
//	public CaveType elysiumCave;

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		GameRegistry.addRecipe(new ItemStack(ConfigObjects.blockBasePillar), new Object[] { "SSS", " S ", "SSS", Character.valueOf('S'), blockPalestone });
		GameRegistry.addRecipe(new ItemStack(ConfigObjects.blockTrashPillar, 1), new Object[] { "SSS", "SLS", "SSS", Character.valueOf('S'), blockPalestone, Character.valueOf('L'), Items.ender_pearl});
		GameRegistry.addRecipe(new ItemStack(ConfigObjects.blockPotPillar), new Object[] { "S", "F", "P", Character.valueOf('S'), blockDirt, Character.valueOf('P'), ConfigObjects.blockBasePillar , Character.valueOf('F'), Items.flower_pot});

		//APIs
		Plants.addGrassPlant(blockTallGrass, 0, 30);
		Plants.addGrassPlant(blockFlower, 0, 10);
		Plants.addGrassSeed(new ItemStack(itemSeedsPepper), 10);
	
		//Vanilla
		Staff.registerThrowableBlock(Blocks.snow, 1F);
		Staff.registerThrowableBlock(Blocks.dirt, 2F);
		Staff.registerThrowableBlock(Blocks.grass, 2F);
		Staff.registerThrowableBlock(Blocks.mycelium, 2F);
		Staff.registerThrowableBlock(Blocks.sand, 2F);
		Staff.registerThrowableBlock(Blocks.soul_sand, 2F);
		Staff.registerThrowableBlock(Blocks.gravel, 2F);
		Staff.registerThrowableBlock(Blocks.clay, 2F);
		Staff.registerThrowableBlock(Blocks.netherrack, 2F);
		Staff.registerThrowableBlock(Blocks.sandstone, 3F);
		Staff.registerThrowableBlock(Blocks.mossy_cobblestone, 3F);
		Staff.registerThrowableBlock(Blocks.stained_hardened_clay, 3F);
		Staff.registerThrowableBlock(Blocks.cobblestone, 4F);
		Staff.registerThrowableBlock(Blocks.end_stone, 4F);
		Staff.registerThrowableBlock(Blocks.stone, 4F);
		Staff.registerThrowableBlock(Blocks.ice, 4F);
		Staff.registerThrowableBlock(Blocks.packed_ice, 6F);
		Staff.registerThrowableBlock(Blocks.obsidian, 8F);

		//Elysium
		Staff.registerThrowableBlock(Elysium.blockDirt, 2F);
		Staff.registerThrowableBlock(Elysium.blockGrass, 2F);
		Staff.registerThrowableBlock(Elysium.blockRilt, 3F);
		Staff.registerThrowableBlock(Elysium.blockSand, 2F);
		Staff.registerThrowableBlock(Elysium.blockPalestone, 4F);
		Staff.registerThrowableBlock(Elysium.blockEnergyCrystal, 10F);
		
		SentryBehaviorRegistry.addBehavior(itemStaff, new SentryBehaviorStaff());
		
		FreezerRecipes.addRecipe(elysiumFluidEnergy, new ItemStack(blockEnergyCrystal, 1, 0));
		FreezerRecipes.addRecipe(elysiumFluidWater, new ItemStack(Blocks.ice, 1, 0));
		
		//Modded APIs
		modLights = Loader.isModLoaded("coloredlightscore");
		modThaumcraft = Loader.isModLoaded("Thaumcraft");
		
		if(modThaumcraft)
		{
			ThaumcraftApi.addShapelessArcaneCraftingRecipe("", new ItemStack(itemToothIngot, 1, 0), new AspectList().add(Aspect.FIRE, 10), new Object[] {new ItemStack(itemHardPaw), ItemApi.getItem("itemResource", 2)});

			ElysiumAspects.addAspects();
			ElysiumRecipes.addRecipes();
			ElysiumResearch.addResearch();

			System.out.println("[Elysium] Thaumcraft compatibility enabled!");
		} else {
			GameRegistry.addShapelessRecipe(new ItemStack(itemToothIngot, 1, 0), new Object[] {new ItemStack(itemHardPaw), new ItemStack(itemIngotIridium)});

		}
		
		if(modLights)
		{
			CLApi.setBlockColorRGB(blockElysiumEnergyLiquid, 1F, 1F, 0F);
			CLApi.setBlockColorRGB(oreBeryl, 0, 8, 15, 1);
		}
		
		/*if(Loader.isModLoaded("noCubes"))
		{
			if(Elysium.proxy.getMinecraftVersion().equals("1.7.2"))
			{
				NoCubes.renderBlockSoft(blockDirt);
				NoCubes.renderBlockSoft(blockGrass);
				NoCubes.renderBlockSoft(blockSand);
				NoCubes.renderBlockSoft(blockPalestone);
				NoCubes.renderBlockSoft(blockRilt);
	
				NoCubes.renderBlockSoft(oreBeryl);
				NoCubes.renderBlockSoft(oreCobalt);
				NoCubes.renderBlockSoft(oreIridium);
				NoCubes.renderBlockSoft(oreJade);
				NoCubes.renderBlockSoft(oreSilicon);
				NoCubes.renderBlockSoft(oreSulphure);
				NoCubes.renderBlockSoft(oreTourmaline);
			} else {

				NoCubes.registerAsNatural(blockDirt);
				NoCubes.registerAsNatural(blockGrass);
				NoCubes.registerAsNatural(blockSand);
				NoCubes.registerAsNatural(blockPalestone);
				NoCubes.registerAsNatural(blockRilt);
	
				NoCubes.registerAsNatural(oreBeryl);
				NoCubes.registerAsNatural(oreCobalt);
				NoCubes.registerAsNatural(oreIridium);
				NoCubes.registerAsNatural(oreJade);
				NoCubes.registerAsNatural(oreSilicon);
				NoCubes.registerAsNatural(oreSulphure);
				NoCubes.registerAsNatural(oreTourmaline);
			}
			
			NoCubes.registerAsLiquid(blockElysiumWater);
			NoCubes.registerAsLiquid(blockElysiumEnergyLiquid);
			
			NoCubes.registerAsLeaves(blockLeaves);
		}*/
		
//		CavesAPI.registerCaveType(elysiumCave);
		
	}
	
    private void registerBlock(Block block)
    {
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
    
	private void registerBlock(Block block, Class<? extends ItemBlock> itemclass)
	{
		GameRegistry.registerBlock(block,  itemclass,  block.getUnlocalizedName());
	}

    private void registerItem(Item item)
    {
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
}
