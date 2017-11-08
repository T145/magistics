package T145.magistics.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import T145.magistics.Magistics;
import T145.magistics.api.magic.Aspect;
import T145.magistics.blocks.cosmetic.BlockCandle;
import T145.magistics.blocks.cosmetic.BlockFloatingCandle;
import T145.magistics.blocks.cosmetic.BlockNitor;
import T145.magistics.blocks.cosmetic.BlockVoidBorder;
import T145.magistics.blocks.crafting.BlockCrucible;
import T145.magistics.blocks.crafting.BlockCrucible.CrucibleType;
import T145.magistics.blocks.crafting.BlockForge;
import T145.magistics.blocks.crafting.BlockForge.ForgeType;
import T145.magistics.blocks.crafting.BlockInfuser;
import T145.magistics.blocks.crafting.BlockInfuser.InfuserType;
import T145.magistics.blocks.devices.BlockChestHungry;
import T145.magistics.blocks.devices.BlockChestHungry.HungryChestType;
import T145.magistics.blocks.devices.BlockChestHungryMetal;
import T145.magistics.blocks.devices.BlockChestVoid;
import T145.magistics.blocks.devices.BlockElevator;
import T145.magistics.blocks.nature.BlockCrystalOre;
import T145.magistics.blocks.nature.BlockLeaves;
import T145.magistics.blocks.nature.BlockLogs;
import T145.magistics.blocks.nature.BlockPlanks;
import T145.magistics.blocks.nature.BlockPlanks.WoodType;
import T145.magistics.blocks.nature.BlockSaplings;
import T145.magistics.blocks.storage.BlockConduit;
import T145.magistics.blocks.storage.BlockQuintTank;
import T145.magistics.blocks.storage.BlockQuintTank.TankType;
import T145.magistics.client.render.blocks.RenderChestHungry;
import T145.magistics.client.render.blocks.RenderChestHungryMetal;
import T145.magistics.client.render.blocks.RenderChestVoid;
import T145.magistics.client.render.blocks.RenderConduit;
import T145.magistics.client.render.blocks.RenderCrucible;
import T145.magistics.client.render.blocks.RenderFloatingCandle;
import T145.magistics.client.render.blocks.RenderInfuser;
import T145.magistics.client.render.blocks.RenderTank;
import T145.magistics.client.render.blocks.RenderVoidBorder;
import T145.magistics.items.ItemDiscovery;
import T145.magistics.items.ItemDiscovery.ResearchType;
import T145.magistics.items.ItemShard;
import T145.magistics.items.ItemShard.ShardType;
import T145.magistics.items.MItem;
import T145.magistics.tiles.cosmetic.TileFloatingCandle;
import T145.magistics.tiles.cosmetic.TileVoidBorder;
import T145.magistics.tiles.crafting.TileCrucible;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.devices.TileChestHungry;
import T145.magistics.tiles.devices.TileChestHungryMetal;
import T145.magistics.tiles.devices.TileChestVoid;
import T145.magistics.tiles.storage.TileConduit;
import T145.magistics.tiles.storage.TileQuintTank;
import T145.magistics.world.biomes.BiomeBlightland;
import T145.magistics.world.biomes.BiomeEnchantedForest;
import T145.magistics.world.providers.WorldProviderVoid;
import cpw.mods.ironchest.common.blocks.chest.IronChestType;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

//@ObjectHolder(Magistics.MODID) // uncomment in 1.12
public class ModInit {

	public static final BlockCrucible CRUCIBLE = new BlockCrucible();
	public static final BlockConduit CONDUIT = new BlockConduit();
	public static final BlockInfuser INFUSER = new BlockInfuser();
	public static final BlockQuintTank TANK = new BlockQuintTank();
	public static final BlockElevator ELEVATOR = new BlockElevator();
	public static final BlockForge FORGE = new BlockForge();
	public static final BlockChestHungry CHEST_HUNGRY = new BlockChestHungry();
	public static BlockChestHungryMetal blockChestHungryMetal;
	public static final BlockChestVoid CHEST_VOID = new BlockChestVoid();
	public static final BlockVoidBorder VOID_BORDER = new BlockVoidBorder();
	public static final BlockSaplings SAPLINGS = new BlockSaplings();
	public static final BlockPlanks PLANKS = new  BlockPlanks();
	public static final BlockLogs LOGS = new BlockLogs();
	public static final BlockLeaves LEAVES = new BlockLeaves();

	public static final BlockCrystalOre OVERWORLD_CRYSTAL_ORE = new BlockCrystalOre("ore_overworld");
	public static final BlockCrystalOre NETHER_CRYSTAL_ORE = new BlockCrystalOre("ore_nether");
	public static final BlockCrystalOre END_CRYSTAL_ORE = new BlockCrystalOre("ore_end");

	public static final BlockCandle CANDLE = new BlockCandle();
	public static final BlockNitor NITOR = new BlockNitor();
	public static final BlockFloatingCandle FLOATING_CANDLE = new BlockFloatingCandle();

	public static final ItemShard CRYSTAL_SHARD = new ItemShard("shard", true);
	public static final ItemShard CRYSTAL_SHARD_FRAGMENT = new ItemShard("shard.fragment", false);
	public static final MItem RESEARCH_NOTE = new MItem("research_note", ResearchType.getTypes());
	public static final MItem RESEARCH_THEORY = new MItem("research_theory", ResearchType.getTypes());
	public static final ItemDiscovery RESEARCH_DISCOVERY = new ItemDiscovery();

	public static final Biome BLIGHTLAND = new BiomeBlightland();
	public static final Biome MAGICAL_FOREST = new BiomeEnchantedForest();

	public static final DimensionType VOID_TYPE = DimensionType.register("The Void", "void", ConfigMain.voidDimensionId, WorldProviderVoid.class, false);

	public static final SoundEvent SOUND_ATTACH = registerSound("attach");
	public static final SoundEvent SOUND_BEAMLOOP = registerSound("beamloop");
	public static final SoundEvent SOUND_BUBBLING = registerSound("bubbling");
	public static final SoundEvent SOUND_CREAKING1 = registerSound("creaking1");
	public static final SoundEvent SOUND_CREAKING2 = registerSound("creaking2");
	public static final SoundEvent SOUND_ELECLOOP = registerSound("elecloop");
	public static final SoundEvent SOUND_FIRELOOP = registerSound("fireloop");
	public static final SoundEvent SOUND_GORE1 = registerSound("gore1");
	public static final SoundEvent SOUND_GORE2 = registerSound("gore2");
	public static final SoundEvent SOUND_HEAL = registerSound("heal");
	public static final SoundEvent SOUND_INFUSER = registerSound("infuser");
	public static final SoundEvent SOUND_INFUSER_DARK = registerSound("infuserdark");
	public static final SoundEvent SOUND_LEARN = registerSound("learn");
	public static final SoundEvent SOUND_MONOLITH = registerSound("monolith");
	public static final SoundEvent SOUND_MONOLITH_FOUND = registerSound("monolithfound");
	public static final SoundEvent SOUND_PAGE1 = registerSound("page1");
	public static final SoundEvent SOUND_PAGE2 = registerSound("page2");
	public static final SoundEvent SOUND_PORTAL_CLOSE = registerSound("pclose");
	public static final SoundEvent SOUND_PLACE = registerSound("place");
	public static final SoundEvent SOUND_POD_BURST = registerSound("podburst");
	public static final SoundEvent SOUND_PORTAL_OPEN = registerSound("popen");
	public static final SoundEvent SOUND_RECOVER = registerSound("recover");
	public static final SoundEvent SOUND_ROOTS = registerSound("roots");
	public static final SoundEvent SOUND_RUMBLE = registerSound("rumble");
	public static final SoundEvent SOUND_RUNE_SET = registerSound("rune_set");
	public static final SoundEvent SOUND_SCRIBBLE = registerSound("scribble");
	public static final SoundEvent SOUND_SHOCK1 = registerSound("shock1");
	public static final SoundEvent SOUND_SHOCK2 = registerSound("shock2");
	public static final SoundEvent SOUND_SINGULARITY = registerSound("singularity");
	public static final SoundEvent SOUND_STOMP1 = registerSound("stomp1");
	public static final SoundEvent SOUND_STOMP2 = registerSound("stomp2");
	public static final SoundEvent SOUND_STONE_CLOSE = registerSound("stoneclose");
	public static final SoundEvent SOUND_STONE_OPEN = registerSound("stoneopen");
	public static final SoundEvent SOUND_SUCK = registerSound("suck");
	public static final SoundEvent SOUND_SWING1 = registerSound("swing1");
	public static final SoundEvent SOUND_SWING2 = registerSound("swing2");
	public static final SoundEvent SOUND_TINKERING = registerSound("tinkering");
	public static final SoundEvent SOUND_TOOL1 = registerSound("tool1");
	public static final SoundEvent SOUND_TOOL2 = registerSound("tool2");
	public static final SoundEvent SOUND_UPGRADE = registerSound("upgrade");
	public static final SoundEvent SOUND_WHISPER = registerSound("whisper");
	public static final SoundEvent SOUND_WIND1 = registerSound("wind1");
	public static final SoundEvent SOUND_WIND2 = registerSound("wind2");
	public static final SoundEvent SOUND_ZAP1 = registerSound("zap1");
	public static final SoundEvent SOUND_ZAP2 = registerSound("zap2");

	public static WorldServer getWorldServerForDimension(int dim) {
		return FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dim);
	}

	public static WorldServer getServerVoidWorld() {
		return getWorldServerForDimension(ConfigMain.voidDimensionId);
	}

	private static final SoundEvent registerSound(String soundName) {
		ResourceLocation resource = new ResourceLocation(Magistics.MODID, soundName);
		SoundEvent event = new SoundEvent(resource);
		return GameRegistry.register(event, resource);
	}

	public static HashMap<BiomeDictionary.Type, List> biomeInfo = new HashMap();
	public static HashMap<Integer, Integer> dimensionBlacklist = new HashMap();
	public static HashMap<Integer, Integer> biomeBlacklist = new HashMap();

	public static void registerBiomeInfo(BiomeDictionary.Type type, float auraLevel, Aspect tag, boolean greatwood, float greatwoodchance) {
		biomeInfo.put(type, Arrays.asList(new Object[] { auraLevel, tag, greatwood, greatwoodchance }));
	}

	public static float getBiomeAuraModifier(Biome biome) {
		try {
			Set<Type> types = BiomeDictionary.getTypes(biome);
			float sum = 0.0F;
			int count = 0;

			for (BiomeDictionary.Type type : types) {
				sum += (float) biomeInfo.get(type).get(0);
				count++;
			}

			return sum / count;
		} catch (Exception err) {
			Magistics.LOG.catching(err);
		}

		return 0.5F;
	}

	public static Aspect getRandomBiomeTag(int biomeId, Random random) {
		try {
			Set<Type> types = BiomeDictionary.getTypes(Biome.getBiome(biomeId));
			return (Aspect) biomeInfo.get(random.nextInt(types.size())).get(1);
		} catch (Exception err) {
			Magistics.LOG.catching(err);
		}

		return null;
	}

	public static float getBiomeSupportsGreatwood(int biomeId) {
		try {
			Set<Type> types = BiomeDictionary.getTypes(Biome.getBiome(biomeId));

			for (BiomeDictionary.Type type : types) {
				if ((boolean) biomeInfo.get(type).get(2)) {
					return (float) biomeInfo.get(type).get(3);
				}
			}
		} catch (Exception err) {
			Magistics.LOG.catching(err);
		}

		return 0.0F;
	}

	public static void addDimBlacklist(int dim, int level) {
		dimensionBlacklist.put(dim, level);
	}

	public static int getDimBlacklist(int dim) {
		if (!dimensionBlacklist.containsKey(dim)) {
			return -1;
		}

		return dimensionBlacklist.get(dim);
	}

	public static void addBiomeBlacklist(int biome, int level) {
		biomeBlacklist.put(biome, level);
	}

	public static int getBiomeBlacklist(int biome) {
		if (!biomeBlacklist.containsKey(biome)) {
			return -1;
		}

		return biomeBlacklist.get(biome);
	}

	static {
		if (Loader.isModLoaded("ironchest")) {
			blockChestHungryMetal = new BlockChestHungryMetal();
		}
	}

	@EventBusSubscriber(modid = Magistics.MODID)
	public static class RegistrationHandler {

		static { // register everything that doesn't need a registry event here
			DimensionManager.registerDimension(ConfigMain.voidDimensionId, VOID_TYPE);
		}

		// uncomment in 1.12
		/*@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();
			registry.register(CRUCIBLE);
			registry.register(CONDUIT);
			registry.register(INFUSER);
			registry.register(TANK);
			registry.register(ELEVATOR);
			registry.register(FORGE);
			registry.register(CHEST_HUNGRY);
			registry.register(CHEST_VOID);
			registry.register(VOID_BORDER);
			registry.register(SAPLINGS);
			registry.register(PLANKS);
			registry.register(LOGS);
			registry.register(LEAVES);
			registry.register(CANDLE);
			registry.register(NITOR);
			registry.register(FLOATING_CANDLE);
			Magistics.LOG.info("Registered Blocks!");
			registerTileEntities();
		}

		private static void registerTileEntities() {
			registerTileEntity(TileCrucible.class);
			registerTileEntity(TileConduit.class);
			registerTileEntity(TileInfuser.class);
			registerTileEntity(TileQuintTank.class);
			registerTileEntity(TileElevator.class);
			registerTileEntity(TileForge.class);
			registerTileEntity(TileChestHungry.class);
			registerTileEntity(TileChestVoid.class);
			registerTileEntity(TileVoidBorder.class);
			registerTileEntity(TileNitor.class);
			registerTileEntity(TileFloatingCandle.class);
			Magistics.LOG.info("Registered Tile Entities!");
		}

		private static void registerTileEntity(Class tileClass) {
			GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
		}

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			registerItemBlock(registry, CRUCIBLE, CrucibleType.class);
			registerItemBlock(registry, CONDUIT);
			registerItemBlock(registry, INFUSER, InfuserType.class);
			registerItemBlock(registry, TANK, TankType.class);
			registerItemBlock(registry, ELEVATOR);
			registerItemBlock(registry, FORGE, ForgeType.class);
			registerItemBlock(registry, CHEST_HUNGRY, HungryChestType.class);
			registerItemBlock(registry, CHEST_VOID);
			registerItemBlock(registry, VOID_BORDER);
			registerItemBlock(registry, SAPLINGS, WoodType.class);
			registerItemBlock(registry, PLANKS, WoodType.class);
			registerItemBlock(registry, LOGS, WoodType.class);
			registerItemBlock(registry, LEAVES, WoodType.class);
			registerItemBlock(registry, CANDLE, EnumDyeColor.class);
			registerItemBlock(registry, NITOR, EnumDyeColor.class);
			registerItemBlock(registry, FLOATING_CANDLE, EnumDyeColor.class);
			Magistics.LOG.info("Registered ItemBlocks!");

			registry.register(CRYSTAL_SHARD);
			registry.register(RESEARCH_NOTE);
			registry.register(RESEARCH_THEORY);
			registry.register(RESEARCH_DISCOVERY);
			Magistics.LOG.info("Registered Items!");
		}

		private static void registerItemBlock(IForgeRegistry<Item> registry, Block block) {
			registry.register(new MBlockItem(block).setRegistryName(block.getRegistryName()));
		}

		private static void registerItemBlock(IForgeRegistry<Item> registry, Block block, Class types) {
			registry.register(new MBlockItem(block, types).setRegistryName(block.getRegistryName()));
		}*/

		@SubscribeEvent
		public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
			final IForgeRegistry<Biome> registry = event.getRegistry();
			registry.register(BLIGHTLAND);
			registry.register(MAGICAL_FOREST);

			BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(MAGICAL_FOREST, ConfigMain.enchantedForestWeight));
			BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(MAGICAL_FOREST, ConfigMain.enchantedForestWeight));

			// TODO: Figure out how to register BiomeTypes properly
			//BiomeDictionary.registerBiomeType(BLIGHTLAND, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.WASTELAND });
			//BiomeDictionary.registerBiomeType(MAGICAL_FOREST, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.FOREST });

			registerBiomeInfo(BiomeDictionary.Type.WATER, 0.33F, Aspect.WATER, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.OCEAN, 0.33F, Aspect.WATER, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.RIVER, 0.4F, Aspect.WATER, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.WET, 0.4F, Aspect.WATER, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.LUSH, 0.5F, Aspect.WATER, true, 0.5F);

			registerBiomeInfo(BiomeDictionary.Type.HOT, 0.33F, Aspect.FIRE, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.DRY, 0.25F, Aspect.FIRE, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.NETHER, 0.125F, Aspect.FIRE, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.MESA, 0.33F, Aspect.FIRE, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.SPOOKY, 0.5F, Aspect.FIRE, false, 0.0F);

			registerBiomeInfo(BiomeDictionary.Type.DENSE, 0.4F, Aspect.MAGIC, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.SNOWY, 0.25F, Aspect.MAGIC, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.COLD, 0.25F, Aspect.MAGIC, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.MUSHROOM, 0.75F, Aspect.MAGIC, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.MAGICAL, 0.75F, Aspect.MAGIC, true, 1.0F);

			registerBiomeInfo(BiomeDictionary.Type.CONIFEROUS, 0.33F, Aspect.EARTH, true, 0.2F);
			registerBiomeInfo(BiomeDictionary.Type.FOREST, 0.5F, Aspect.EARTH, true, 1.0F);
			registerBiomeInfo(BiomeDictionary.Type.SANDY, 0.25F, Aspect.EARTH, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.BEACH, 0.3F, Aspect.EARTH, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.JUNGLE, 0.6F, Aspect.EARTH, false, 0.0F);

			registerBiomeInfo(BiomeDictionary.Type.SAVANNA, 0.25F, Aspect.AIR, true, 0.2F);
			registerBiomeInfo(BiomeDictionary.Type.MOUNTAIN, 0.3F, Aspect.AIR, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.HILLS, 0.33F, Aspect.AIR, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.PLAINS, 0.3F, Aspect.AIR, true, 0.2F);
			registerBiomeInfo(BiomeDictionary.Type.END, 0.125F, Aspect.AIR, false, 0.0F);

			registerBiomeInfo(BiomeDictionary.Type.DRY, 0.125F, Aspect.VOID, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.SPARSE, 0.2F, Aspect.VOID, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.SWAMP, 0.5F, Aspect.VOID, true, 0.2F);
			registerBiomeInfo(BiomeDictionary.Type.WASTELAND, 0.125F, Aspect.VOID, false, 0.0F);
			registerBiomeInfo(BiomeDictionary.Type.DEAD, 0.1F, Aspect.VOID, false, 0.0F);

			Magistics.LOG.info("Registered Biomes!");
		}
	}

	@EventBusSubscriber(modid = Magistics.MODID)
	public static class ClientRegistrationHandler {

		public static String getVariantName(IStringSerializable variant) {
			return "variant=" + variant.getName();
		}

		public static void registerBlockModel(Block block, int meta, String path, String variant) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Magistics.MODID + ":" + path, variant));
		}

		public static void registerBlockModel(Block block, int meta, String variant) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), variant));
		}

		public static void registerBlockModel(Block block, int meta, IStringSerializable variant) {
			registerBlockModel(block, meta, "variant=" + variant.getName());
		}

		public static void registerItemModel(Item item, int meta, String path) {
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Magistics.MODID + ":" + path, "inventory"));
		}

		public static void registerItemModel(Item item, int meta) {
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			registerFluidRenderers();
			registerEntityRenderers();
			registerDisplayInfo();
			registerTileRenderers();
			registerItemRenderers();
		}

		private static void registerFluidRenderers() {
			// TODO Register any fluids
		}

		private static void registerEntityRenderers() {
			// TODO Register any entity renders
		}

		// NOTE: ALWAYS REGISTER THE LAST MODEL TO BE RENDERED IN THE INVENTORY
		private static void registerDisplayInfo() {
			ModelLoader.setCustomStateMapper(INFUSER, INFUSER.getStateMap());
			ModelLoader.setCustomStateMapper(TANK, TANK.getStateMap());

			for (InfuserType type : InfuserType.values()) {
				registerBlockModel(INFUSER, type.ordinal(), type.getName() + "_infuser", "inventory");
			}

			for (TankType type : TankType.values()) {
				registerBlockModel(TANK, type.ordinal(), type.getName() + "_tank", "inventory");
			}

			registerBlockModel(CONDUIT, 0, "inventory");

			for (CrucibleType type : CrucibleType.values()) {
				registerBlockModel(CRUCIBLE, type.ordinal(), getVariantName(type) + ",working=false");
			}

			registerBlockModel(ELEVATOR, 0, "normal");

			for (ForgeType type : ForgeType.values()) {
				registerBlockModel(FORGE, type.ordinal(), "inventory," + getVariantName(type));
			}

			for (HungryChestType type : HungryChestType.values()) {
				registerBlockModel(CHEST_HUNGRY, type.ordinal(), type);
			}

			registerBlockModel(CHEST_VOID, 0, "inventory");
			registerBlockModel(VOID_BORDER, 0, "inventory");

			for (WoodType type : WoodType.values()) {
				registerBlockModel(LEAVES, type.ordinal(), type);
				registerBlockModel(LOGS, type.ordinal(), "axis=y,variant=" + type.getName());
				registerBlockModel(PLANKS, type.ordinal(), type);
				registerBlockModel(SAPLINGS, type.ordinal(), type);
			}

			for (EnumDyeColor type : EnumDyeColor.values()) {
				registerBlockModel(NITOR, type.ordinal(), "inventory");
				registerBlockModel(CANDLE, type.ordinal(), "inventory");
				registerBlockModel(FLOATING_CANDLE, type.ordinal(), "inventory");
			}

			if (Loader.isModLoaded("ironchest")) {
				for (IronChestType type : IronChestType.values()) {
					registerBlockModel(blockChestHungryMetal, type.ordinal(), ClientRegistrationHandler.getVariantName(type));
				}
			}

			for (ShardType type : ShardType.values()) {
				registerBlockModel(OVERWORLD_CRYSTAL_ORE, type.ordinal(), type);
				registerBlockModel(NETHER_CRYSTAL_ORE, type.ordinal(), type);
				registerBlockModel(END_CRYSTAL_ORE, type.ordinal(), type);
			}

			Magistics.LOG.info("Registered Block Rendering!");
		}

		private static void registerTileRenderers() {
			ClientRegistry.bindTileEntitySpecialRenderer(TileCrucible.class, new RenderCrucible());
			ClientRegistry.bindTileEntitySpecialRenderer(TileInfuser.class, new RenderInfuser());
			ClientRegistry.bindTileEntitySpecialRenderer(TileConduit.class, new RenderConduit());
			ClientRegistry.bindTileEntitySpecialRenderer(TileQuintTank.class, new RenderTank());
			ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungry.class, new RenderChestHungry());
			ClientRegistry.bindTileEntitySpecialRenderer(TileChestVoid.class, new RenderChestVoid());
			ClientRegistry.bindTileEntitySpecialRenderer(TileVoidBorder.class, new RenderVoidBorder());
			ClientRegistry.bindTileEntitySpecialRenderer(TileFloatingCandle.class, new RenderFloatingCandle());

			if (Loader.isModLoaded("ironchest")) {
				ClientRegistry.bindTileEntitySpecialRenderer(TileChestHungryMetal.class, new RenderChestHungryMetal());
			}

			Magistics.LOG.info("Registered Tile Rendering!");
		}

		private static void registerItemRenderers() {
			for (int meta = 0; meta <= ShardType.values().length; ++meta) {
				registerItemModel(CRYSTAL_SHARD, meta, "shards/dull");
				registerItemModel(CRYSTAL_SHARD_FRAGMENT, meta, "shards/fragment");
			}

			for (ResearchType type : ResearchType.values()) {
				registerItemModel(RESEARCH_NOTE, type.ordinal(), "research/note/" + type.getName());
				registerItemModel(RESEARCH_THEORY, type.ordinal(), "research/theory/" + type.getName());
				registerItemModel(RESEARCH_DISCOVERY, type.ordinal(), "research/discovery/" + type.getName());
			}
		}
	}
}