package T145.magistics.init;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import T145.magistics.Magistics;
import T145.magistics.api.variants.Aspect;
import T145.magistics.config.ConfigMain;
import T145.magistics.world.biomes.BiomeBlightland;
import T145.magistics.world.biomes.BiomeEnchantedForest;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBiomes {

	public static final Biome BLIGHTLAND = new BiomeBlightland();
	public static final Biome MAGICAL_FOREST = new BiomeEnchantedForest();

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
			Magistics.LOGGER.catching(err);
		}

		return 0.5F;
	}

	public static Aspect getRandomBiomeTag(int biomeId, Random random) {
		try {
			Set<Type> types = BiomeDictionary.getTypes(Biome.getBiome(biomeId));
			return (Aspect) biomeInfo.get(random.nextInt(types.size())).get(1);
		} catch (Exception err) {
			Magistics.LOGGER.catching(err);
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
			Magistics.LOGGER.catching(err);
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

	public static void init() {
		GameRegistry.register(BLIGHTLAND);
		GameRegistry.register(MAGICAL_FOREST);

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
	}
}