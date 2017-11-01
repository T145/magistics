package T145.magistics.world.aura;

import java.lang.ref.WeakReference;

import T145.magistics.core.ConfigMain;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;

public class AuraChunk {

	private short base;
	private float vis;
	private float flux;
	private ChunkPos chunkPos;
	private WeakReference<Chunk> chunkRef;

	public AuraChunk(ChunkPos chunkPos) {
		this.chunkPos = chunkPos;
	}

	public AuraChunk(Chunk chunk, short base, float vis, float flux) {
		if (chunk != null) {
			chunkPos = chunk.getPos();
			chunkRef = new WeakReference(chunk);
		}

		this.base = base;
		this.vis = vis;
		this.flux = flux;
	}

	public boolean isModified() {
		return chunkRef != null && chunkRef.get() != null ? chunkRef.get().needsSaving(false) : false;
	}

	public short getBase() {
		return base;
	}

	public void setBase(short base) {
		this.base = base;
	}

	public float getVis() {
		return vis;
	}

	public void setVis(float vis) {
		vis = Math.min(ConfigMain.auraMax, Math.max(0.0F, vis));
	}

	public float getFlux() {
		return flux;
	}

	public void setFlux(float flux) {
		flux = Math.min(ConfigMain.auraMax, Math.max(0.0F, flux));
	}

	public ChunkPos getChunkPos() {
		return chunkPos;
	}

	public void setChunkPos(ChunkPos loc) {
		this.chunkPos = loc;
	}
}