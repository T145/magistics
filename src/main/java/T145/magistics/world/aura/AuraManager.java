package T145.magistics.world.aura;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import T145.magistics.Magistics;
import T145.magistics.core.Init;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

public class AuraManager {

	public static final int AURA_CEILING = 500;
	public static ConcurrentHashMap<Integer, CopyOnWriteArrayList<ChunkPos>> dirtyChunks = new ConcurrentHashMap();
	public static ConcurrentHashMap<Integer, BlockPos> blightTrigger = new ConcurrentHashMap();
	static ConcurrentHashMap<Integer, AuraWorld> auras = new ConcurrentHashMap();

	public static AuraWorld getAuraWorld(int dimID) {
		return auras.get(dimID);
	}

	public static AuraChunk getAuraChunk(int dimID, int x, int y) {
		if (auras.containsKey(dimID)) {
			return auras.get(dimID).getAuraChunkAt(x, y);
		}

		return null;
	}

	public static void addAuraWorld(int dimID) {
		if (!auras.containsKey(dimID)) {
			auras.put(dimID, new AuraWorld(dimID));
			Magistics.LOG.info("Creating aura cache for world " + dimID);
		}
	}

	public static void removeAuraWorld(int dimID) {
		auras.remove(dimID);
		Magistics.LOG.info("Removing aura cache for world " + dimID);
	}

	public static void addAuraChunk(int dimID, Chunk chunk, short base, float vis, float flux) {
		AuraWorld auraWolrd = auras.get(dimID);

		if (auraWolrd == null) {
			auraWolrd = new AuraWorld(dimID);
		}

		auraWolrd.getAuraChunks().put(new ChunkPos(chunk.x, chunk.z), new AuraChunk(chunk, base, vis, flux));

		auras.put(dimID, auraWolrd);
	}

	public static void removeAuraChunk(int dimID, int x, int y) {
		AuraWorld auraWolrd = auras.get(dimID);

		if (auraWolrd != null) {
			auraWolrd.getAuraChunks().remove(new ChunkPos(x, y));
		}
	}

	public static float getTotalAura(World world, BlockPos pos) {
		AuraChunk auraChunk = getAuraChunk(world.provider.getDimension(), pos.getX() >> 4, pos.getZ() >> 4);
		return auraChunk != null ? auraChunk.getVis() + auraChunk.getFlux() : 0.0F;
	}

	public static float getFluxSaturation(World world, BlockPos pos) {
		AuraChunk auraChunk = getAuraChunk(world.provider.getDimension(), pos.getX() >> 4, pos.getZ() >> 4);
		return auraChunk != null ? auraChunk.getFlux() / auraChunk.getBase() : 0.0F;
	}

	public static float getVis(World world, BlockPos pos) {
		AuraChunk auraChunk = getAuraChunk(world.provider.getDimension(), pos.getX() >> 4, pos.getZ() >> 4);
		return auraChunk != null ? auraChunk.getVis() : 0.0F;
	}

	public static float getFlux(World world, BlockPos pos) {
		AuraChunk auraChunk = getAuraChunk(world.provider.getDimension(), pos.getX() >> 4, pos.getZ() >> 4);
		return auraChunk != null ? auraChunk.getFlux() : 0.0F;
	}

	public static int getAuraBase(World world, BlockPos pos) {
		AuraChunk auraChunk = getAuraChunk(world.provider.getDimension(), pos.getX() >> 4, pos.getZ() >> 4);
		return auraChunk != null ? auraChunk.getBase() : 0;
	}

	public static void addVis(World world, BlockPos pos, float amount) {
		if (amount < 0.0F) {
			return;
		}

		try {
			AuraChunk auraChunk = getAuraChunk(world.provider.getDimension(), pos.getX() >> 4, pos.getZ() >> 4);
			modifyVisInChunk(auraChunk, amount, true);
		} catch (Exception err) {
			Magistics.LOG.catching(err);
		}
	}

	public static void addFlux(World world, BlockPos pos, float amount) {
		if (amount < 0.0F) {
			return;
		}

		try {
			AuraChunk auraChunk = getAuraChunk(world.provider.getDimension(), pos.getX() >> 4, pos.getZ() >> 4);
			modifyFluxInChunk(auraChunk, amount, true);
		} catch (Exception err) {
			Magistics.LOG.catching(err);
		}
	}

	public static float drainVis(World world, BlockPos pos, float amount, boolean simulate) {
		boolean modified = false;

		try {
			AuraChunk aura = getAuraChunk(world.provider.getDimension(), pos.getX() >> 4, pos.getZ() >> 4);

			if (amount > aura.getVis()) {
				amount = aura.getVis();
			}

			modified = modifyVisInChunk(aura, -amount, !simulate);
		} catch (Exception err) {
			Magistics.LOG.catching(err);
		}

		return modified ? amount : 0.0F;
	}

	public static float drainFlux(World world, BlockPos pos, float amount, boolean simulate) {
		boolean modified = false;

		try {
			AuraChunk auraChunk = getAuraChunk(world.provider.getDimension(), pos.getX() >> 4, pos.getZ() >> 4);

			if (amount > auraChunk.getFlux()) {
				amount = auraChunk.getFlux();
			}

			modified = modifyFluxInChunk(auraChunk, -amount, !simulate);
		} catch (Exception err) {
			Magistics.LOG.catching(err);
		}

		return modified ? amount : 0.0F;
	}

	public static boolean modifyVisInChunk(AuraChunk auraChunk, float amount, boolean doit) {
		if (auraChunk != null) {
			if (doit) {
				auraChunk.setVis(Math.max(0.0F, auraChunk.getVis() + amount));
			}

			return true;
		}

		return false;
	}

	private static boolean modifyFluxInChunk(AuraChunk auraChunk, float amount, boolean doit) {
		if (auraChunk != null) {
			if (doit) {
				auraChunk.setFlux(Math.max(0.0F, auraChunk.getFlux() + amount));
			}

			return true;
		}

		return false;
	}

	public static void generateAura(Chunk chunk, Random rand) {
		Biome biome = chunk.getWorld().getBiome(new BlockPos(chunk.x * 16 + 8, 50, chunk.z * 16 + 8));

		if (Init.getBiomeBlacklist(Biome.getIdForBiome(biome)) != -1) {
			return;
		}

		float life = Init.getBiomeAuraModifier(biome);

		for (EnumFacing facing : EnumFacing.HORIZONTALS) {
			biome = chunk.getWorld().getBiome(new BlockPos((chunk.x + facing.getFrontOffsetX()) * 16 + 8, 50, (chunk.z + facing.getFrontOffsetZ()) * 16 + 8));
			life += Init.getBiomeAuraModifier(biome);
		}

		life /= 5.0F;

		float noise = (float) (1.0D + rand.nextGaussian() * 0.1D);
		short base = (short) (life * 500.0F * noise);
		base = (short) MathHelper.clamp(base, 0, 500);
		addAuraChunk(chunk.getWorld().provider.getDimension(), chunk, base, base, 0.0F);
	}
}