package T145.magistics.world.aura;

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.util.math.ChunkPos;

public class AuraWorld {

	private final int dim;
	private ConcurrentHashMap<ChunkPos, AuraChunk> auraChunks = new ConcurrentHashMap();

	public AuraWorld(int dim) {
		this.dim = dim;
	}

	public int getDimension() {
		return dim;
	}

	public ConcurrentHashMap<ChunkPos, AuraChunk> getAuraChunks() {
		return auraChunks;
	}

	public void setAuraChunks(ConcurrentHashMap<ChunkPos, AuraChunk> auraChunks) {
		this.auraChunks = auraChunks;
	}

	public AuraChunk getAuraChunkAt(ChunkPos loc) {
		return auraChunks.get(loc);
	}

	public AuraChunk getAuraChunkAt(int x, int z) {
		return getAuraChunkAt(new ChunkPos(x, z));
	}
}