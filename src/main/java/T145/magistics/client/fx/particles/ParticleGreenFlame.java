package T145.magistics.client.fx.particles;

import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.world.World;

public class ParticleGreenFlame extends ParticleFlame {

	public ParticleGreenFlame(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed,
			double zSpeed) {
		super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
		particleRed = 0F;
	}
}