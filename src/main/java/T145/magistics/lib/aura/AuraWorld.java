package T145.magistics.lib.aura;

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.util.math.ChunkPos;

public class AuraWorld {

	ConcurrentHashMap<ChunkPos, AuraChunk> auraChunks = new ConcurrentHashMap<ChunkPos, AuraChunk>();

	public ConcurrentHashMap<ChunkPos, AuraChunk> getAuraChunks() {
		return this.auraChunks;
	}

	public void setAuraChunks(ConcurrentHashMap<ChunkPos, AuraChunk> auraChunks) {
		this.auraChunks = auraChunks;
	}

	public AuraChunk getAuraChunkAt(int x, int y) {
		return getAuraChunkAt(new ChunkPos(x, y));
	}

	public AuraChunk getAuraChunkAt(ChunkPos loc) {
		return auraChunks.get(loc);
	}
}