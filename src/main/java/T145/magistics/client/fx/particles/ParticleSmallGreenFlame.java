package T145.magistics.client.fx.particles;

import net.minecraft.world.World;

public class ParticleSmallGreenFlame extends ParticleGreenFlame {

	public ParticleSmallGreenFlame(World world, double xCoord, double yCoord, double zCoord, double xSpeed,
			double ySpeed, double zSpeed) {
		super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
		particleScale = 0.1F;
	}
}