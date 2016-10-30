package T145.magistics.lib.aura;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import T145.magistics.Magistics;
import T145.magistics.config.ConfigHandler;
import T145.magistics.lib.world.BiomeHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeHell;
import net.minecraft.world.chunk.Chunk;

public class AuraHandler {

	private static ConcurrentHashMap<Integer, AuraWorld> auras = new ConcurrentHashMap();

	public static void generateAura(Chunk chunk, Random random) {
		Biome dest = chunk.getWorld().getBiomeForCoordsBody(new BlockPos(chunk.xPosition * 16 + 8, 50, chunk.zPosition * 16 + 8));
		boolean moreMiasma = false;
		float auraCeiling = ConfigHandler.auraMax / 3;
		float auraFloor = ConfigHandler.auraMax / 5;

		if (BiomeHandler.biomeLowAura.contains(dest)) {
			auraCeiling = ConfigHandler.auraMax / 8;
			auraFloor = ConfigHandler.auraMax / 20;

			if (dest instanceof BiomeHell) {
				moreMiasma = true;
			}
		} else if (BiomeHandler.biomeHighAura.contains(dest)) {
			auraCeiling = ConfigHandler.auraMax * 0.6F;
			auraFloor = ConfigHandler.auraMax / 3;
		} else if (BiomeHandler.biomeGoodAura.contains(dest)) {
			auraCeiling = ConfigHandler.auraMax * 0.7F;
			auraFloor = ConfigHandler.auraMax / 2;
		} else if (BiomeHandler.biomeBadAura.contains(dest)) {
			auraCeiling = ConfigHandler.auraMax * 0.5F;
			auraFloor = ConfigHandler.auraMax / 3;
			moreMiasma = true;
		}

		float auraVis = auraFloor + random.nextInt((int) (auraCeiling - auraFloor));
		float auraMiasma = auraVis / 3;
		int taintChance = ConfigHandler.taintRarity == 2 ? 300 : 2200;

		if (ConfigHandler.taintRarity > 0 && random.nextInt(taintChance) == 0) {
			auraVis = auraFloor + random.nextInt((int) (auraCeiling - auraFloor)) / 2;
			auraMiasma = ConfigHandler.auraMax * (ConfigHandler.taintRarity == 2 ? 0.8F : 0.5F) + random.nextInt((int) (ConfigHandler.auraMax * 0.2F));
			generateTaintedArea(chunk, random, auraMiasma);
		}

		if (moreMiasma) {
			auraMiasma *= 1.5F;
		}

		addAuraChunk(chunk, auraVis, auraMiasma);
	}

	private static void generateTaintedArea(Chunk chunk, Random random, float auraTaint) {}

	public static void addAuraChunk(Chunk chunk, float auraVis, float auraMiasma) {
		int dimension = chunk.getWorld().provider.getDimension();
		AuraWorld world = auras.get(dimension);

		if (world == null) {
			addAuraWorld(dimension);
		}

		ChunkPos pos = chunk.getChunkCoordIntPair();
		world.getAuraChunks().put(pos, new AuraChunk(pos, auraVis, auraMiasma));
		auras.put(dimension, world);
	}

	public static void removeAuraChunk(ChunkPos pos, int dimension) {
		AuraWorld world = auras.get(dimension);

		if (world != null) {
			world.getAuraChunks().remove(pos);
		}
	}

	public static AuraChunk getAuraChunk(ChunkPos pos, int dimension) {
		if (auras.containsKey(dimension)) {
			return auras.get(dimension).getAuraChunkAt(pos);
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