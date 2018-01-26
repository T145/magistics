package T145.magistics.client.particles;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleWisp extends Particle {

	private Entity target;
	public boolean shrink;
	private float moteParticleScale;
	private int moteHalfLife;
	public boolean tinkle;
	public int blendmode = 1;

	public void setGravity(float particleGravity) {
		this.particleGravity = particleGravity;
	}

	public void canCollide(boolean canCollide) {
		this.canCollide = canCollide;
	}

	public ParticleWisp(World world, double d, double d1, double d2, float f, float f1, float f2) {
		this(world, d, d1, d2, 1.0F, f, f1, f2);
	}

	public ParticleWisp(World world, double d, double d1, double d2, float f, float red, float green, float blue) {
		super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);

		if (red == 0.0F) {
			red = 1.0F;
		}

		particleRed = red;
		particleGreen = green;
		particleBlue = blue;
		particleGravity = 0.0F;
		motionX = (motionY = motionZ = 0.0D);
		particleScale *= f;
		moteParticleScale = particleScale;
		particleMaxAge = ((int) (36.0D / (Math.random() * 0.3D + 0.7D)));
		moteHalfLife = (particleMaxAge / 2);
		canCollide = true;
		setSize(0.1F, 0.1F);

		Entity renderentity = FMLClientHandler.instance().getClient().getRenderViewEntity();
		int visibleDistance = 50;

		if (!FMLClientHandler.instance().getClient().gameSettings.fancyGraphics) {
			visibleDistance = 25;
		}

		if (renderentity.getDistance(posX, posY, posZ) > visibleDistance) {
			particleMaxAge = 0;
		}

		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
	}

	public ParticleWisp(World world, double d, double d1, double d2, float f, int type) {
		this(world, d, d1, d2, f, 0.0F, 0.0F, 0.0F);

		switch (type) {
		case 0:
			particleRed = (0.75F + world.rand.nextFloat() * 0.25F);
			particleGreen = (0.25F + world.rand.nextFloat() * 0.25F);
			particleBlue = (0.75F + world.rand.nextFloat() * 0.25F);
			break;
		case 1:
			particleRed = (0.5F + world.rand.nextFloat() * 0.3F);
			particleGreen = (0.5F + world.rand.nextFloat() * 0.3F);
			particleBlue = 0.2F;
			break;
		case 2:
			particleRed = 0.2F;
			particleGreen = 0.2F;
			particleBlue = (0.7F + world.rand.nextFloat() * 0.3F);
			break;
		case 3:
			particleRed = 0.2F;
			particleGreen = (0.7F + world.rand.nextFloat() * 0.3F);
			particleBlue = 0.2F;
			break;
		case 4:
			particleRed = (0.7F + world.rand.nextFloat() * 0.3F);
			particleGreen = 0.2F;
			particleBlue = 0.2F;
			break;
		case 5:
			blendmode = 771;
			particleRed = (world.rand.nextFloat() * 0.1F);
			particleGreen = (world.rand.nextFloat() * 0.1F);
			particleBlue = (world.rand.nextFloat() * 0.1F);
			break;
		case 6:
			particleRed = (0.8F + world.rand.nextFloat() * 0.2F);
			particleGreen = (0.8F + world.rand.nextFloat() * 0.2F);
			particleBlue = (0.8F + world.rand.nextFloat() * 0.2F);
			break;
		case 7:
			particleRed = (0.7F + world.rand.nextFloat() * 0.3F);
			particleGreen = (0.5F + world.rand.nextFloat() * 0.2F);
			particleBlue = (0.3F + world.rand.nextFloat() * 0.1F);
			break;
		}
	}

	public ParticleWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, int type) {
		this(world, d, d1, d2, f, type);

		if (particleMaxAge > 0) {
			double dx = x - posX;
			double dy = y - posY;
			double dz = z - posZ;

			motionX = (dx / particleMaxAge);
			motionY = (dy / particleMaxAge);
			motionZ = (dz / particleMaxAge);
		}
	}

	public ParticleWisp(World world, double d, double d1, double d2, Entity tar, int type) {
		this(world, d, d1, d2, 0.4F, type);
	}

	public ParticleWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, float red, float green, float blue) {
		this(world, d, d1, d2, f, red, green, blue);

		if (particleMaxAge > 0) {
			double dx = x - posX;
			double dy = y - posY;
			double dz = z - posZ;

			motionX = (dx / particleMaxAge);
			motionY = (dy / particleMaxAge);
			motionZ = (dz / particleMaxAge);
		}
	}

	@Override
	public void renderParticle(BufferBuilder wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		float agescale = 0.0F;

		if (shrink) {
			agescale = (particleMaxAge - particleAge) / particleMaxAge;
		} else {
			agescale = particleAge / moteHalfLife;
			if (agescale > 1.0F) {
				agescale = 2.0F - agescale;
			}
		}

		particleScale = (moteParticleScale * agescale);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);

		float f10 = 0.5F * particleScale;
		float f11 = (float) (prevPosX + (posX - prevPosX) * f - interpPosX);
		float f12 = (float) (prevPosY + (posY - prevPosY) * f - interpPosY);
		float f13 = (float) (prevPosZ + (posZ - prevPosZ) * f - interpPosZ);

		float var8 = 0.0F;
		float var9 = 0.125F;
		float var10 = 0.875F;
		float var11 = 1.0F;

		int i = 240;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		wr.pos(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10).tex(var9, var11).color(particleRed, particleGreen, particleBlue, 0.5F).lightmap(j, k).endVertex();
		wr.pos(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10).tex(var9, var10).color(particleRed, particleGreen, particleBlue, 0.5F).lightmap(j, k).endVertex();
		wr.pos(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10).tex(var8, var10).color(particleRed, particleGreen, particleBlue, 0.5F).lightmap(j, k).endVertex();
		wr.pos(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10).tex(var8, var11).color(particleRed, particleGreen, particleBlue, 0.5F).lightmap(j, k).endVertex();
	}

	@Override
	public int getFXLayer() {
		return blendmode == 1 ? 0 : 1;
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge == 0 && tinkle && world.rand.nextInt(3) == 0) {
			world.playSound(posX, posY, posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.AMBIENT, 0.02F, 0.5F * ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.6F + 2.0F), false);
		}

		if (particleAge++ >= particleMaxAge) {
			setExpired();
		}

		motionY -= 0.04D * particleGravity;

		if (canCollide) {
			setPosition(posX, posY, posZ);
		}

		move(motionX, motionY, motionZ);

		if (target != null) {
			motionX *= 0.985D;
			motionY *= 0.985D;
			motionZ *= 0.985D;

			double dx = target.posX - posX;
			double dy = target.posY + target.height / 2.0F - posY;
			double dz = target.posZ - posZ;
			double d13 = 0.2D;
			double d11 = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);

			dx /= d11;
			dy /= d11;
			dz /= d11;

			motionX += dx * d13;
			motionY += dy * d13;
			motionZ += dz * d13;

			motionX = MathHelper.clamp(motionX, -0.2F, 0.2F);
			motionY = MathHelper.clamp(motionY, -0.2F, 0.2F);
			motionZ = MathHelper.clamp(motionZ, -0.2F, 0.2F);
		} else {
			motionX *= 0.98D;
			motionY *= 0.98D;
			motionZ *= 0.98D;

			if (onGround) {
				motionX *= 0.7D;
				motionZ *= 0.7D;
			}
		}
	}
}