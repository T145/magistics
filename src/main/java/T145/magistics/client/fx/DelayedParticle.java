package T145.magistics.client.fx;

import net.minecraft.client.particle.Particle;

public class DelayedParticle {

	private final Particle effect;
	private final int dimID;
	int time;

	public DelayedParticle(Particle effect, int dimID, int time) {
		this.effect = effect;
		this.dimID = dimID;
		this.time = time;
	}

	public Particle getEffect() {
		return effect;
	}

	public int getDimensionId() {
		return dimID;
	}
}