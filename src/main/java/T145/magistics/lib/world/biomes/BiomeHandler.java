package T145.magistics.lib.world.biomes;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.Magistics;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BiomeHandler {

	/*public static enum AuraType {
		HIGH, LOW, GOOD, BAD;

		public static Map<Biome, AuraType> biomeMap = new HashMap<Biome, AuraType>();

		// one value per biome
		public static void registerBiomes() {
			biomeMap.put(Biomes.SWAMPLAND, BAD);
			biomeMap.put(Biomes.JUNGLE, GOOD);
			biomeMap.put(Biomes.JUNGLE_HILLS, GOOD);
			biomeMap.put(Biomes.MUSHROOM_ISLAND, GOOD);
			biomeMap.put(Biomes.MUSHROOM_ISLAND_SHORE, GOOD);
			biomeMap.put(Biomes.DESERT, LOW);
			biomeMap.put(Biomes.DESERT_HILLS, LOW);
			biomeMap.put(Biomes.HELL, LOW);
			biomeMap.put(Biomes.FOREST, HIGH);
			biomeMap.put(Biomes.FOREST_HILLS, HIGH);
			biomeMap.put(Biomes.BIRCH_FOREST, HIGH);
			biomeMap.put(Biomes.BIRCH_FOREST_HILLS, HIGH);
		}
	}

	public static enum OreType {
		AIR, FIRE, WATER, EARTH, MAGIC, TAINT;

		public static Map<Biome, OreType[]> biomeMap = new HashMap<Biome, OreType[]>();

		public static void init() {
		biomeAir.add(Biomes.DESERT);
		biomeAir.add(Biomes.EXTREME_HILLS);
		biomeAir.add(Biomes.ICE_MOUNTAINS);
		biomeAir.add(Biomes.PLAINS);

		biomeFire.add(Biomes.DESERT);
		biomeFire.add(Biomes.HELL);
		biomeFire.add(Biomes.EXTREME_HILLS);
		biomeFire.add(Biomes.DESERT_HILLS);

		biomeWater.add(Biomes.OCEAN);
		biomeWater.add(Biomes.FROZEN_OCEAN);
		biomeWater.add(Biomes.RIVER);
		biomeWater.add(Biomes.ICE_PLAINS);
		biomeWater.add(Biomes.SWAMPLAND);
		biomeWater.add(Biomes.ICE_MOUNTAINS);

		biomeEarth.add(Biomes.EXTREME_HILLS);
		biomeEarth.add(Biomes.TAIGA);
		biomeEarth.add(Biomes.FOREST);
		biomeEarth.add(Biomes.ICE_MOUNTAINS);
		biomeEarth.add(Biomes.JUNGLE);
		biomeEarth.add(Biomes.JUNGLE_HILLS);

		biomeMagic.add(Biomes.PLAINS);
		biomeMagic.add(Biomes.MUSHROOM_ISLAND);
		biomeMagic.add(Biomes.MUSHROOM_ISLAND_SHORE);
		biomeMagic.add(Biomes.JUNGLE);
		biomeMagic.add(Biomes.JUNGLE_HILLS);

		biomeTaint.add(Biomes.MUSHROOM_ISLAND);
		biomeTaint.add(Biomes.MUSHROOM_ISLAND_SHORE);
		biomeTaint.add(Biomes.SWAMPLAND);
	}

		// multiple values per biome
		public static void registerBiomes() {
			biomeMap.put(Biomes.DESERT, new OreType[] { AIR, FIRE });
			biomeMap.put(Biomes.EXTREME_HILLS, new OreType[] { AIR, FIRE, EARTH });
		}

		public static boolean hasType(Biome biome, OreType type) {
			for (OreType ore : biomeMap.get(biome)) {
				if (ore == type) {
					return true;
				}
			}

			return false;
		}
	}*/

	public static List<Biome> biomeLowAura = new ArrayList<Biome>();
	public static List<Biome> biomeHighAura = new ArrayList<Biome>();
	public static List<Biome> biomeGoodAura = new ArrayList<Biome>();
	public static List<Biome> biomeBadAura = new ArrayList<Biome>();

	public static Biome biomeTaint;

	public static void registerBiomes() {
		biomeTaint = registerBiome(new BiomeTaint());
		registerBiomeToDictionary(biomeTaint, Type.MAGICAL, Type.WASTELAND);
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeTaint, 10));
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biomeTaint, 10));

		biomeLowAura.add(Biomes.DESERT);
		biomeLowAura.add(Biomes.DESERT_HILLS);
		biomeLowAura.add(Biomes.HELL);

		biomeHighAura.add(Biomes.TAIGA);
		biomeHighAura.add(Biomes.FOREST);
		biomeHighAura.add(Biomes.FOREST_HILLS);
		biomeHighAura.add(Biomes.TAIGA_HILLS);

		biomeGoodAura.add(Biomes.MUSHROOM_ISLAND);
		biomeGoodAura.add(Biomes.MUSHROOM_ISLAND_SHORE);
		biomeGoodAura.add(Biomes.JUNGLE);
		biomeGoodAura.add(Biomes.JUNGLE_HILLS);

		biomeBadAura.add(Biomes.SWAMPLAND);
	}

	public static int getNextFreeBiomeId() {
		int nextBiomeId = 40;

		for (int id = nextBiomeId; id < 256; ++id) {
			if (Biome.getBiome(id) != null) {
				if (id == 255) {
					Magistics.logger.throwing(new IllegalArgumentException("No biome ID is avaliable!"));
				}
				continue;
			} else {
				nextBiomeId = id + 1;
				return id;
			}
		}

		return -1;
	}

	private static Biome registerBiome(Biome biome) {
		int id = getNextFreeBiomeId();

		if (id > 0) {
			Biome.registerBiome(id, biome.getBiomeName(), biome);
			GameRegistry.register(biome);
			return biome;
		}

		return null;
	}

	private static void registerBiomeToDictionary(Biome biome, BiomeDictionary.Type... types) {
		if (biome != null) {
			BiomeDictionary.registerBiomeType(biome, types);
		}
	}
}