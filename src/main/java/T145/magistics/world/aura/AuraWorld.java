package T145.magistics.world.aura;

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.util.math.ChunkPos;

public class AuraWorld {

	private final int dimID;
	private ConcurrentHashMap<ChunkPos, AuraChunk> auraChunks = new ConcurrentHashMap();

	public AuraWorld(int dimID) {
		this.dimID = dimID;
	}

	public int getDimensionId() {
		return dimID;
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
}