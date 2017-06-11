package T145.magistics.init;

import T145.magistics.config.ConfigMain;
import T145.magistics.world.biomes.BiomeEnchantedForest;
import T145.magistics.world.biomes.BiomeTaint;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBiomes {

	public static Biome biomeTaint;
	public static Biome biomeGreatwoodGrove;
	public static Biome biomeEnchantedForest;

	public static void init() {
		GameRegistry.register(biomeTaint = new BiomeTaint());
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeTaint, ConfigMain.taintWeight));
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biomeTaint, ConfigMain.taintWeight));

		GameRegistry.register(biomeEnchantedForest = new BiomeEnchantedForest());
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeEnchantedForest, ConfigMain.enchantedForestWeight));
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biomeEnchantedForest, ConfigMain.enchantedForestWeight));
	}
}