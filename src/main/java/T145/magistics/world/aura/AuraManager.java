package T145.magistics.world.aura;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import T145.magistics.Magistics;
import T145.magistics.core.ModInit;
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

	public static AuraWorld getAuraWorld(int dim) {
		return auras.get(dim);
	}

	public static AuraChunk getAuraChunk(int dim, ChunkPos pos) {
		if (auras.containsKey(dim)) {
			return auras.get(dim).getAuraChunkAt(pos);
		}

		return null;
	}

	public static void addAuraWorld(int dim) {
		if (!auras.containsKey(dim)) {
			auras.put(dim, new AuraWorld(dim));
			Magistics.LOG.info("Creating aura cache for world " + dim);
		}
	}

	public static void removeAuraWorld(int dim) {
		auras.remove(dim);
		Magistics.LOG.info("Removing aura cache for world " + dim);
	}

	public static void addAuraChunk(int dim, Chunk chunk, short base, float vis, float flux) {
		AuraWorld auraWorld = auras.get(dim);

		if (auraWorld == null) {
			auraWorld = new AuraWorld(dim);
		}

		auraWorld.getAuraChunks().put(new ChunkPos(chunk.x, chunk.z), new AuraChunk(chunk, base, vis, flux));

		auras.put(dim, auraWorld);
	}

	public static void removeAuraChunk(int dim, int x, int y) {
		AuraWorld auraWorld = auras.get(dim);

		if (auraWorld != null) {
			auraWorld.getAuraChunks().remove(new ChunkPos(x, y));
		}
	}

	public static float getTotalAura(World world, BlockPos pos) {
		AuraChunk aura = getAuraChunk(world.provider.getDimension(), world.getChunkFromBlockCoords(pos).getPos());
		return aura != null ? aura.getVis() + aura.getFlux() : 0.0F;
	}

	public static float getFluxSaturation(World world, BlockPos pos) {
		AuraChunk aura = getAuraChunk(world.provider.getDimension(), world.getChunkFromBlockCoords(pos).getPos());
		return aura != null ? aura.getFlux() / aura.getBase() : 0.0F;
	}

	public static float getVis(World world, BlockPos pos) {
		AuraChunk aura = getAuraChunk(world.provider.getDimension(), world.getChunkFromBlockCoords(pos).getPos());
		return aura != null ? aura.getVis() : 0.0F;
	}

	public static float getFlux(World world, BlockPos pos) {
		AuraChunk aura = getAuraChunk(world.provider.getDimension(), world.getChunkFromBlockCoords(pos).getPos());
		return aura != null ? aura.getFlux() : 0.0F;
	}

	public static int getAuraBase(World world, BlockPos pos) {
		AuraChunk aura = getAuraChunk(world.provider.getDimension(), world.getChunkFromBlockCoords(pos).getPos());
		return aura != null ? aura.getBase() : 0;
	}

	public static void addVis(World world, BlockPos pos, float amount) {
		if (amount < 0.0F) {
			return;
		}

		try {
			AuraChunk aura = getAuraChunk(world.provider.getDimension(), world.getChunkFromBlockCoords(pos).getPos());
			modifyVisInChunk(aura, amount, true);
		} catch (Exception err) {
			Magistics.LOG.catching(err);
		}
	}

	public static void addFlux(World world, BlockPos pos, float amount) {
		if (amount < 0.0F) {
			return;
		}

		try {
			AuraChunk aura = getAuraChunk(world.provider.getDimension(), world.getChunkFromBlockCoords(pos).getPos());
			modifyFluxInChunk(aura, amount, true);
		} catch (Exception err) {
			Magistics.LOG.catching(err);
		}
	}

	public static float drainVis(World world, BlockPos pos, float amount, boolean simulate) {
		boolean modified = false;

		try {
			AuraChunk aura = getAuraChunk(world.provider.getDimension(), world.getChunkFromBlockCoords(pos).getPos());

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
			AuraChunk aura = getAuraChunk(world.provider.getDimension(), world.getChunkFromBlockCoords(pos).getPos());

			if (amount > aura.getFlux()) {
				amount = aura.getFlux();
			}

			modified = modifyFluxInChunk(aura, -amount, !simulate);
		} catch (Exception err) {
			Magistics.LOG.catching(err);
		}

		return modified ? amount : 0.0F;
	}

	public static boolean modifyVisInChunk(AuraChunk aura, float amount, boolean doit) {
		if (aura != null) {
			if (doit) {
				aura.setVis(Math.max(0.0F, aura.getVis() + amount));
			}
			return true;
		}
		return false;
	}

	private static boolean modifyFluxInChunk(AuraChunk aura, float amount, boolean doit) {
		if (aura != null) {
			if (doit) {
				aura.setFlux(Math.max(0.0F, aura.getFlux() + amount));
			}
			return true;
		}
		return false;
	}

	public static void generateAura(Chunk chunk, Random rand) {
		Biome biome = chunk.getWorld().getBiome(new BlockPos(chunk.x * 16 + 8, 50, chunk.z * 16 + 8));

		if (ModInit.getBiomeBlacklist(Biome.getIdForBiome(biome)) != -1) {
			return;
		}

		float life = ModInit.getBiomeAuraModifier(biome);

		for (EnumFacing facing : EnumFacing.HORIZONTALS) {
			biome = chunk.getWorld().getBiome(new BlockPos((chunk.x + facing.getFrontOffsetX()) * 16 + 8, 50, (chunk.z + facing.getFrontOffsetZ()) * 16 + 8));
			life += ModInit.getBiomeAuraModifier(biome);
		}

		life /= 5.0F;

		float noise = (float) (1.0D + rand.nextGaussian() * 0.1D);
		short base = (short) (life * 500.0F * noise);
		base = (short) MathHelper.clamp(base, 0, 500);
		addAuraChunk(chunk.getWorld().provider.getDimension(), chunk, base, base, 0.0F);
	}
}