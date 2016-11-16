package T145.magistics.lib.world.biomes;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.config.ConfigHandler;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class BiomeHandler {

	public static List<Biome> biomeLowAura = new ArrayList<Biome>();
	public static List<Biome> biomeHighAura = new ArrayList<Biome>();
	public static List<Biome> biomeGoodAura = new ArrayList<Biome>();
	public static List<Biome> biomeBadAura = new ArrayList<Biome>();

	public static Biome biomeTaint;
	public static Biome biomeGreatwoodGrove;
	public static Biome biomeEnchantedForest;

	public static void registerBiomes() {
		biomeTaint = registerBiome(new BiomeTaint());
		registerBiomeToDictionary(biomeTaint, Type.MAGICAL, Type.WASTELAND);
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeTaint, ConfigHandler.taintWeight));
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biomeTaint, ConfigHandler.taintWeight));

		biomeEnchantedForest = registerBiome(new BiomeEnchantedForest());
		registerBiomeToDictionary(biomeEnchantedForest, Type.MAGICAL, Type.FOREST);
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeEnchantedForest, ConfigHandler.enchantedForestWeight));
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biomeEnchantedForest, ConfigHandler.enchantedForestWeight));

		loadAuraMapping();
	}

	private static void loadAuraMapping() {
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
		biomeBadAura.add(biomeTaint);
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