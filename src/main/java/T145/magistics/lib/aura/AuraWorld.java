package T145.magistics.lib.aura;

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.util.math.ChunkPos;

public class AuraWorld {

	ConcurrentHashMap<ChunkPos, AuraChunk> auraChunks = new ConcurrentHashMap();
	int dimension;

	public AuraWorld(int dimension) {
		this.dimension = dimension;
	}

	public ConcurrentHashMap<ChunkPos, AuraChunk> getAuraChunks() {
		return auraChunks;
	}

	public void setAuraChunks(ConcurrentHashMap<ChunkPos, AuraChunk> auraChunks) {
		this.auraChunks = auraChunks;
	}

	public AuraChunk getAuraChunkAt(int chunkX, int chunkY) {
		return getAuraChunkAt(new ChunkPos(chunkX, chunkY));
	}

	public AuraChunk getAuraChunkAt(ChunkPos pos) {
		return auraChunks.get(pos);
	}
}