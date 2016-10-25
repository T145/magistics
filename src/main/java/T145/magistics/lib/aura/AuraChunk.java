package T145.magistics.lib.aura;

import java.lang.ref.WeakReference;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;

public class AuraChunk {

	private ChunkPos pos;
	private WeakReference<Chunk> ref;
	private float vis;
	private float miasma;

	public AuraChunk(ChunkPos pos) {
		setPos(pos);
	}

	public AuraChunk(ChunkPos pos, float vis, float miasma) {
		this(pos);
		setVis(vis);
		setMiasma(miasma);
	}

	public boolean modified() {
		return ref != null && ref.get() != null && ref.get().needsSaving(false);
	}

	public float getVis() {
		return 0F;
	}

	public void setVis(float vis) {
		this.vis = vis;
	}

	public float getMiasma() {
		return 0F;
	}

	public void setMiasma(float miasma) {
		this.miasma = miasma;
	}

	public ChunkPos getPos() {
		return pos;
	}

	public void setPos(ChunkPos pos) {
		this.pos = pos;
	}
}