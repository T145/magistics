package T145.magistics.lib.world.biomes;

import T145.magistics.Magistics;
import T145.magistics.api.MagisticsApi;
import T145.magistics.api.MagisticsApi.AuraType;
import T145.magistics.config.ConfigHandler;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class BiomeHandler {

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

		MagisticsApi.registerBiomeAura(Biomes.DESERT, AuraType.LOW);
		MagisticsApi.registerBiomeAura(Biomes.DESERT_HILLS, AuraType.LOW);
		MagisticsApi.registerBiomeAura(Biomes.HELL, AuraType.LOW);

		MagisticsApi.registerBiomeAura(Biomes.TAIGA, AuraType.HIGH);
		MagisticsApi.registerBiomeAura(Biomes.TAIGA_HILLS, AuraType.HIGH);
		MagisticsApi.registerBiomeAura(Biomes.FOREST, AuraType.HIGH);
		MagisticsApi.registerBiomeAura(Biomes.FOREST_HILLS, AuraType.HIGH);
		MagisticsApi.registerBiomeAura(biomeEnchantedForest, AuraType.HIGH);

		MagisticsApi.registerBiomeAura(Biomes.MUSHROOM_ISLAND, AuraType.GOOD);
		MagisticsApi.registerBiomeAura(Biomes.MUSHROOM_ISLAND_SHORE, AuraType.GOOD);
		MagisticsApi.registerBiomeAura(Biomes.JUNGLE, AuraType.GOOD);
		MagisticsApi.registerBiomeAura(Biomes.JUNGLE_HILLS, AuraType.GOOD);

		MagisticsApi.registerBiomeAura(Biomes.SWAMPLAND, AuraType.BAD);
		MagisticsApi.registerBiomeAura(biomeTaint, AuraType.BAD);
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