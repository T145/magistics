package T145.magistics.world.aura;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import T145.magistics.Magistics;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class AuraThread implements Runnable {

	private final int dimID;
	private final long INTERVAL = 1000L;
	private boolean stop = false;
	private Random rand = new Random(System.currentTimeMillis());

	public AuraThread(int dimID) {
		this.dimID = dimID;
	}

	@Override
	public void run() {
		Magistics.LOG.info("Starting aura thread for dim " + dimID);

		while (!stop) {
			if (AuraManager.auras.isEmpty()) {
				Magistics.LOG.warn("No auras found!");
				break;
			}

			long startTime = System.currentTimeMillis();
			int phaseTime = 0;
			AuraWorld auraWorld = AuraManager.getAuraWorld(dimID);

			if (auraWorld != null) {
				World world = DimensionManager.getWorld(dimID);

				if (world != null) {
					phaseTime = world.provider.getMoonPhase(world.getWorldInfo().getWorldTime());
					phaseTime = Math.abs(phaseTime - 4) * 50;
				}

				for (AuraChunk auraChunk : auraWorld.auraChunks.values()) {
					processAuraChunk(auraWorld, auraChunk);
				}
			} else {
				stop();
			}

			long executionTime = System.currentTimeMillis() - startTime;

			try {
				if (executionTime > 1000L) {
					Magistics.LOG.warn("AURAS TAKING " + (executionTime - 1000L) + " ms LONGER THAN NORMAL IN DIM " + dimID);
				}

				Thread.sleep(Math.max(1L, 1000L - executionTime - phaseTime));
			} catch (InterruptedException err) {
				Magistics.LOG.catching(err);
			}
		}

		Magistics.LOG.info("Stopping aura thread for dim " + dimID);
		AuraEvents.auraThreads.remove(dimID);
	}

	private void processAuraChunk(AuraWorld auraWorld, AuraChunk auraChunk) {
		List<EnumFacing> facings = Arrays.asList(EnumFacing.HORIZONTALS);
		Collections.shuffle(facings, rand);

		short base = auraChunk.getBase();
		boolean dirty = false;
		float currentVis = auraChunk.getVis();
		float currentFlux = auraChunk.getFlux();
		float lowestVisFlux = Float.MAX_VALUE;
		float lowestVis = Float.MAX_VALUE;
		float lowestFlux = Float.MAX_VALUE;
		boolean shared = false;
		AuraChunk neighborVis = null;
		AuraChunk neighborFlux = null;

		for (EnumFacing facing : facings) {
			AuraChunk borderingAura = auraWorld.getAuraChunkAt(auraChunk.getChunkPos().x + facing.getFrontOffsetX(), auraChunk.getChunkPos().z + facing.getFrontOffsetZ());

			if (borderingAura != null) {
				if ((neighborVis == null || lowestVis > borderingAura.getVis()) && borderingAura.getVis() + borderingAura.getFlux() < borderingAura.getBase()) {
					neighborVis = borderingAura;
					lowestVis = borderingAura.getVis();
					lowestVisFlux = borderingAura.getFlux();
				}

				if (neighborFlux == null || lowestFlux > borderingAura.getFlux()) {
					neighborFlux = borderingAura;
					lowestFlux = borderingAura.getFlux();
				}
			}
		}

		if (neighborVis != null && lowestVis < currentVis && lowestVis / currentVis < 0.75D) {
			currentVis -= 1F;
			neighborVis.setVis(lowestVis + 1F);
			dirty = true;
			markChunkAsDirty(neighborVis, auraWorld.getDimensionId());
			shared = true;
		}

		if (neighborFlux != null && lowestFlux < currentFlux && lowestFlux / currentFlux < 0.5D) {
			currentFlux -= 1F;
			neighborFlux.setFlux(lowestFlux + 1F);
			dirty = true;
			markChunkAsDirty(neighborFlux, auraWorld.getDimensionId());
		}

		if (currentVis + currentFlux > base) {
			currentVis -= 1F;
			dirty = true;

			if (!shared && neighborVis != null && lowestVis < currentVis + currentFlux && ((lowestVis + lowestVisFlux) / (currentVis + currentFlux) < 0.75D)) {
				neighborVis.setVis(lowestVis + 1F);
				markChunkAsDirty(neighborVis, auraWorld.getDimensionId());
			}
		}

		if (currentVis > base * 1.1D || (currentVis <= base * 0.1D && currentVis >= currentFlux && rand.nextFloat() < 0.1D)) {
			currentFlux += 1F;
			dirty = true;
		}

		if (currentVis + currentFlux < base) {
			currentVis += 1F;
			dirty = true;
		}

		if (dirty) {
			auraChunk.setVis(currentVis);
			auraChunk.setFlux(currentFlux);
			markChunkAsDirty(auraChunk, auraWorld.getDimensionId());
		}
	}

	private void markChunkAsDirty(AuraChunk chunk, int dim) {
		if (chunk.isModified()) {
			return;
		}

		if (!AuraManager.dirtyChunks.containsKey(dim)) {
			AuraManager.dirtyChunks.put(dim, new CopyOnWriteArrayList());
		}

		CopyOnWriteArrayList<ChunkPos> dc = AuraManager.dirtyChunks.get(dim);

		if (!dc.contains(chunk.getChunkPos())) {
			dc.add(chunk.getChunkPos());
		}
	}

	public void stop() {
		stop = true;
	}
}