package T145.magistics.lib.world;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class BiomeHandler {

	public static List<Biome> biomeAir = new ArrayList<Biome>();
	public static List<Biome> biomeFire = new ArrayList<Biome>();
	public static List<Biome> biomeWater = new ArrayList<Biome>();
	public static List<Biome> biomeEarth = new ArrayList<Biome>();
	public static List<Biome> biomeMagic = new ArrayList<Biome>();
	public static List<Biome> biomeTaint = new ArrayList<Biome>();

	// just in case I want specific biome generation catering
	public static List<Biome> biomeLowAura = new ArrayList<Biome>();
	public static List<Biome> biomeHighAura = new ArrayList<Biome>();
	public static List<Biome> biomeGoodAura = new ArrayList<Biome>();
	public static List<Biome> biomeBadAura = new ArrayList<Biome>();

	public static void loadBiomeData() {
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
}