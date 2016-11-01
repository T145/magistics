package T145.magistics.lib.aura;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import T145.magistics.Magistics;
import T145.magistics.config.ConfigHandler;
import T145.magistics.lib.world.biomes.BiomeHandler;
import T145.magistics.lib.world.biomes.BiomeTaint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeHell;
import net.minecraft.world.chunk.Chunk;

public class AuraHandler {

	private static ConcurrentHashMap<Integer, AuraWorld> auras = new ConcurrentHashMap<Integer, AuraWorld>();

	public static void generateAura(Chunk chunk, Random random) {
		World world = chunk.getWorld();
		BlockPos pos = new BlockPos(chunk.xPosition * 16 + 8, 50, chunk.zPosition * 16 + 8);
		Biome biome = world.getBiomeForCoordsBody(pos);
		float ceiling = ConfigHandler.auraMax / 3;
		float floor = ConfigHandler.auraMax / 5;
		boolean discharge = false;
		boolean infected = false;

		if (BiomeHandler.biomeLowAura.contains(biome)) {
			ceiling = ConfigHandler.auraMax / 8;
			floor = ConfigHandler.auraMax / 20;
			discharge = biome instanceof BiomeHell;
		} else if (BiomeHandler.biomeHighAura.contains(biome)) {
			ceiling = ConfigHandler.auraMax * 0.6F;
			floor = ConfigHandler.auraMax / 3;
		} else if (BiomeHandler.biomeGoodAura.contains(biome)) {
			ceiling = ConfigHandler.auraMax * 0.7F;
			floor = ConfigHandler.auraMax / 2;
		} else if (BiomeHandler.biomeBadAura.contains(biome)) {
			ceiling = ConfigHandler.auraMax * 0.5F;
			floor = ConfigHandler.auraMax / 3;
			discharge = true;
			infected = biome instanceof BiomeTaint;
		}

		float auraVis = floor + random.nextInt((int) (ceiling - floor));
		float auraMiasma = auraVis / 3;

		// do this for the tainted biome (generateTaintedArea should be handled by taint biome's decorator)

		/*int taintChance = ConfigHandler.taintRarity == 2 ? 300 : 2200;

		if (ConfigHandler.taintRarity > 0 && random.nextInt(taintChance) == 0) {
			auraVis = auraFloor + random.nextInt((int) (auraCeiling - auraFloor)) / 2;
			auraMiasma = ConfigHandler.auraMax * (ConfigHandler.taintRarity == 2 ? 0.8F : 0.5F) + random.nextInt((int) (ConfigHandler.auraMax * 0.2F));
			generateTaintedArea(chunk, random, auraMiasma);
		}*/

		if (infected) {
			auraVis = floor + random.nextInt((int) (ceiling - floor)) / 2;
			auraMiasma = ConfigHandler.auraMax * (ConfigHandler.taintRarity == 2 ? 0.8F : 0.5F) + random.nextInt((int) (ConfigHandler.auraMax * 0.2F));
		}

		if (discharge) {
			auraMiasma *= 1.5F;
		}

		addAuraChunk(chunk, auraVis, auraMiasma);
	}

	public static void addAuraChunk(Chunk chunk, float auraVis, float auraMiasma) {
		int dimension = chunk.getWorld().provider.getDimension();
		AuraWorld world = auras.get(dimension);

		if (world == null) {
			world = new AuraWorld(dimension);
		}

		ChunkPos pos = chunk.getChunkCoordIntPair();
		world.getAuraChunks().put(pos, new AuraChunk(pos, auraVis, auraMiasma));
		auras.put(dimension, world);
	}

	public static void removeAuraChunk(Chunk chunk) {
		AuraWorld world = auras.get(chunk.getWorld().provider.getDimension());

		if (world != null) {
			world.getAuraChunks().remove(chunk.getChunkCoordIntPair());
		}
	}

	public static AuraChunk getAuraChunk(Chunk chunk) {
		int dimension = chunk.getWorld().provider.getDimension();

		if (auras.containsKey(dimension)) {
			return auras.get(dimension).getAuraChunkAt(chunk.getChunkCoordIntPair());
		}

		return null;
	}

	public static void addAuraWorld(int dimension) {
		if (!auras.containsKey(dimension)) {
			auras.put(dimension, new AuraWorld(dimension));
			Magistics.logger.info("Creating aura cache for world " + dimension);
		}
	}

	public static void removeAuraWorld(int dimension) {
		auras.remove(dimension);
		Magistics.logger.info("Removing aura cache for world " + dimension);
	}

	public static AuraWorld getAuraWorld(int dimension) {
		return auras.get(dimension);
	}
}