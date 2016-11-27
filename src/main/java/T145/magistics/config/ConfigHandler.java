package T145.magistics.config;

import T145.magistics.Magistics;
import T145.magistics.api.MagisticsApi;
import T145.magistics.api.objects.IModel;
import T145.magistics.api.objects.ITile;
import T145.magistics.api.objects.ModBlocks;
import T145.magistics.api.objects.ModItems;
import T145.magistics.blocks.BlockCrucible;
import T145.magistics.blocks.BlockInfusedOre;
import T145.magistics.blocks.BlockInfuser;
import T145.magistics.blocks.BlockLeaves;
import T145.magistics.blocks.BlockLogs;
import T145.magistics.blocks.BlockPlanks;
import T145.magistics.blocks.BlockSaplings;
import T145.magistics.client.render.entities.RenderColoredSlime.RenderColoredSlimeFactory;
import T145.magistics.entities.EntityVisSlime;
import T145.magistics.items.ItemShard;
import T145.magistics.lib.world.WorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ConfigHandler {

	public static final String CATEGORY_WORLDGEN = "worldgen";
	public static final String CATEGORY_BIOMES = "biomes";

	public static int auraMax = 15000;
	public static int[] dimensionBlacklist;

	public static int taintSeverity = 1;
	public static int taintWeight = 4;
	public static int enchantedForestWeight = 2;
	public static boolean generateRoots = true;

	private Configuration config;

	public Configuration get() {
		return config;
	}

	private void sync() {
		auraMax = config.get(config.CATEGORY_GENERAL, "Max Aura", auraMax).getInt();
		dimensionBlacklist = config.get(CATEGORY_WORLDGEN, "Dimension Blacklist", new int[] {}, "Add dimension ids that you don't want Magistics worldgen applied to").getIntList();

		taintSeverity = config.getInt("Taint Severity", CATEGORY_BIOMES, 1, 0, 2, "How harsh the taint biomes are (0 for ok, 1 for normal, 2 for world eating)");
		taintWeight = config.getInt("Taint Weight", CATEGORY_BIOMES, 4, 1, 10, "How often taint biomes are generated");
		enchantedForestWeight = config.getInt("Enchanted Forest Weight", CATEGORY_BIOMES, 2, 1, 5, "How often enchanted forests are generated");
		generateRoots = config.getBoolean("Generate Roots", CATEGORY_BIOMES, true, "Whether or not to generate roots on certain trees");
	}

	public static boolean isDimensionWhitelisted(int id) {
		if (dimensionBlacklist.length > 0) {
			for (int i = 0; i < dimensionBlacklist.length; ++i) {
				if (dimensionBlacklist[i] == id) {
					return false;
				}
			}
		}

		return true;
	}

	private void save() {
		if (config.getConfigFile().isFile() && config.hasChanged()) {
			config.save();
		}
	}

	public void update() {
		sync();
		save();
	}

	@SubscribeEvent
	public void update(OnConfigChangedEvent event) {
		if (event.getModID().equals(Magistics.MODID)) {
			update();
		}
	}

	public void preInit(FMLPreInitializationEvent event) {
		try {
			config = new Configuration(event.getSuggestedConfigurationFile());
			config.addCustomCategoryComment(CATEGORY_WORLDGEN, "Configure world generation");
			config.addCustomCategoryComment(CATEGORY_BIOMES, "Biome gen configurations");
			config.load();
			update();
			preInit();
		} catch (Throwable err) {
			Magistics.logger.catching(err);
		} finally {
			save();
		}
	}

	private int entityId;

	public void preInit() {
		ModBlocks.crucible = new BlockCrucible("crucible");
		ModBlocks.infuser = new BlockInfuser("infuser");

		ModBlocks.infusedOre = new BlockInfusedOre("ore_overworld");
		ModBlocks.infusedOreNether = new BlockInfusedOre("ore_nether");
		ModBlocks.infusedOreEnd = new BlockInfusedOre("ore_end");

		ModBlocks.logs = new BlockLogs("log");
		ModBlocks.planks = new BlockPlanks("planks");
		ModBlocks.leaves = new BlockLeaves("leaves");
		ModBlocks.saplings = new BlockSaplings("saplings");

		ModItems.shard = new ItemShard("shard", true);
		ModItems.shardFragment = new ItemShard("fragment", false);

		EntityRegistry.registerModEntity(EntityVisSlime.class, "slime.vis", entityId++, Magistics.instance, 55, 1, true, ItemShard.COLORS[4], 11126094);
	}

	@SideOnly(Side.CLIENT)
	public void initClient() {
		((IModel) ModBlocks.crucible).registerModel();
		((ITile) ModBlocks.crucible).registerRenderer();

		((IModel) ModBlocks.infuser).registerModel();
		((ITile) ModBlocks.infuser).registerRenderer();

		((IModel) ModBlocks.infusedOre).registerModel();
		((IModel) ModBlocks.infusedOreNether).registerModel();
		((IModel) ModBlocks.infusedOreEnd).registerModel();

		((IModel) ModBlocks.logs).registerModel();
		((IModel) ModBlocks.planks).registerModel();
		((IModel) ModBlocks.leaves).registerModel();
		((IModel) ModBlocks.saplings).registerModel();

		((IModel) ModItems.shard).registerModel();
		((IModel) ModItems.shardFragment).registerModel();

		RenderingRegistry.registerEntityRenderingHandler(EntityVisSlime.class, new RenderColoredSlimeFactory(0xFFFF00FF));
	}

	public void init(FMLInitializationEvent event) {
		WorldGenerator.init();

		OreDictionary.registerOre("shardAir", new ItemStack(ModItems.shard, 1, 0));
		OreDictionary.registerOre("shardFire", new ItemStack(ModItems.shard, 1, 1));
		OreDictionary.registerOre("shardWater", new ItemStack(ModItems.shard, 1, 2));
		OreDictionary.registerOre("shardEarth", new ItemStack(ModItems.shard, 1, 3));
		OreDictionary.registerOre("shardOrder", new ItemStack(ModItems.shard, 1, 4));
		OreDictionary.registerOre("shardEntropy", new ItemStack(ModItems.shard, 1, 5));
		OreDictionary.registerOre("shardTainted", new ItemStack(ModItems.shard, 1, 6));
	}

	public void postInit(FMLPostInitializationEvent event) {
		registerCrucibleRecipes();
		registerInfuserRecipes();
	}

	private void registerCrucibleRecipes() {
		MagisticsApi.addCrucibleRecipe(Items.STICK, 0.25F);
		MagisticsApi.addCrucibleRecipe(Items.CLAY_BALL, 1F);
		MagisticsApi.addCrucibleRecipe(Items.BRICK, 2F);
		MagisticsApi.addCrucibleRecipe(Items.FLINT, 1F);
		MagisticsApi.addCrucibleRecipe(Items.COAL, 2F);
		MagisticsApi.addCrucibleRecipe(Items.SNOWBALL, 0.25F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 0), 4F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 2), 4F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 3), 25F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 4), 9F);
		MagisticsApi.addCrucibleRecipe(Items.IRON_INGOT, 5F);
		MagisticsApi.addCrucibleRecipe(Items.BEETROOT_SEEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Items.MELON_SEEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Items.PUMPKIN_SEEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Items.WHEAT_SEEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Items.FEATHER, 4F);
		MagisticsApi.addCrucibleRecipe(Items.BONE, 4F);
		MagisticsApi.addCrucibleRecipe(Items.MELON, 2F);
		MagisticsApi.addCrucibleRecipe(Items.COOKED_BEEF, 5F);
		MagisticsApi.addCrucibleRecipe(Items.BEEF, 4F);
		MagisticsApi.addCrucibleRecipe(Items.COOKED_CHICKEN, 5F);
		MagisticsApi.addCrucibleRecipe(Items.CHICKEN, 4F);
		MagisticsApi.addCrucibleRecipe(Items.COOKED_PORKCHOP, 5F);
		MagisticsApi.addCrucibleRecipe(Items.PORKCHOP, 4F);
		MagisticsApi.addCrucibleRecipe(Items.COOKED_FISH, 5F);
		MagisticsApi.addCrucibleRecipe(Items.FISH, 4F);
		MagisticsApi.addCrucibleRecipe(Items.NETHER_WART, 4F);
		MagisticsApi.addCrucibleRecipe(Items.ROTTEN_FLESH, 4F);
		MagisticsApi.addCrucibleRecipe(Items.STRING, 4F);
		MagisticsApi.addCrucibleRecipe(Items.LEATHER, 4F);
		MagisticsApi.addCrucibleRecipe(Items.WHEAT, 4F);
		MagisticsApi.addCrucibleRecipe(Items.REEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Items.GUNPOWDER, 10F);
		MagisticsApi.addCrucibleRecipe(Items.GLOWSTONE_DUST, 9F);
		MagisticsApi.addCrucibleRecipe(Items.REDSTONE, 6F);
		MagisticsApi.addCrucibleRecipe(Items.MILK_BUCKET, 18F);
		MagisticsApi.addCrucibleRecipe(Items.WATER_BUCKET, 16F);
		MagisticsApi.addCrucibleRecipe(Items.LAVA_BUCKET, 17F);
		MagisticsApi.addCrucibleRecipe(Items.EGG, 5F);
		MagisticsApi.addCrucibleRecipe(Items.GOLD_INGOT, 27F);
		MagisticsApi.addCrucibleRecipe(Items.GOLD_NUGGET, 3F);
		MagisticsApi.addCrucibleRecipe(Items.SLIME_BALL, 25F);
		MagisticsApi.addCrucibleRecipe(Items.APPLE, 4F);
		MagisticsApi.addCrucibleRecipe(Items.DIAMOND, 64F);
		MagisticsApi.addCrucibleRecipe(Items.ENDER_PEARL, 64F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_13, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_CAT, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_11, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_CHIRP, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_FAR, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_MALL, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_MELLOHI, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_STAL, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_STRAD, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_WARD, 100F);
		MagisticsApi.addCrucibleRecipe(Items.BLAZE_ROD, 36F);
		MagisticsApi.addCrucibleRecipe(Items.GHAST_TEAR, 64F);
		MagisticsApi.addCrucibleRecipe(Items.SPIDER_EYE, 9F);
		MagisticsApi.addCrucibleRecipe(Items.SADDLE, 64F);

		MagisticsApi.addCrucibleRecipe(Blocks.COBBLESTONE, 0.1F);
		MagisticsApi.addCrucibleRecipe(Blocks.PLANKS, 0.5F);
		MagisticsApi.addCrucibleRecipe(Blocks.MOSSY_COBBLESTONE, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.SAND, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.SANDSTONE, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.RED_SANDSTONE, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.DIRT, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.GRASS, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.GLASS, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.ICE, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.GRAVEL, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.STONE, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.WATERLILY, 3F);
		MagisticsApi.addCrucibleRecipe(Blocks.WEB, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.NETHER_BRICK, 2F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Blocks.STONEBRICK, 1, 1), 1F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Blocks.STONEBRICK, 1, 2), 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.NETHERRACK, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.SOUL_SAND, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.COAL_ORE, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.WOOL, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.LOG, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.LEAVES, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.TALLGRASS, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.DEADBUSH, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.CACTUS, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.SAPLING, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.MYCELIUM, 3F);
		MagisticsApi.addCrucibleRecipe(Blocks.IRON_ORE, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.YELLOW_FLOWER, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.RED_FLOWER, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.BROWN_MUSHROOM_BLOCK, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.RED_MUSHROOM_BLOCK, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.VINE, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.PUMPKIN, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.REEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.REDSTONE_ORE, 16F);
		MagisticsApi.addCrucibleRecipe(Blocks.LAPIS_ORE, 9F);
		MagisticsApi.addCrucibleRecipe(Blocks.GOLD_ORE, 25F);
		MagisticsApi.addCrucibleRecipe(Blocks.OBSIDIAN, 16F);
		MagisticsApi.addCrucibleRecipe(Blocks.DIAMOND_ORE, 64F);
	}

	private void registerInfuserRecipes() {
		MagisticsApi.addInfuserRecipe(new ItemStack(Blocks.STONE), new ItemStack[] { new ItemStack(Blocks.DIRT), new ItemStack(Blocks.SAND) }, 20F);
	}
}