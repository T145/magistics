package T145.magistics.client.fx;

import net.minecraft.client.particle.Particle;

public class ParticleDelay {

	Particle particle;
	int dim;
	int level;
	int delay;

	public ParticleDelay(Particle particle, int dim, int delay) {
		this.dim = dim;
		this.particle = particle;
		this.delay = delay;
	}
}