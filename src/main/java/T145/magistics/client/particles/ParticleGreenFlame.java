package T145.magistics.client.particles;

import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleGreenFlame extends ParticleFlame {

	public ParticleGreenFlame(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
		particleRed = 0F;
	}

	public void setScale(float scale) {
		this.particleScale = scale;
	}
}