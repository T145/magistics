package T145.magistics.client.fx.particles;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleWisp extends Particle {

	public boolean shrink = false;
	float moteParticleScale;
	int moteHalfLife;
	public boolean tinkle = false;
	public int blendmode = 1;
	private Entity target;

	public void setGravity(float gravity) {
		particleGravity = gravity;
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
		setNoClip(false);
		setSize(0.1F, 0.1F);

		int visibleDistance = 50;
		if (!FMLClientHandler.instance().getClient().gameSettings.fancyGraphics) {
			visibleDistance = 25;
		}

		Entity entity = FMLClientHandler.instance().getClient().getRenderViewEntity();
		if (entity != null && entity.getDistance(posX, posY, posZ) > visibleDistance) {
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

	public void setNoClip(boolean noClip) {
		canCollide = noClip;
	}

	@Override
	public void renderParticle(VertexBuffer buffer, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
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

		buffer.pos(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10).tex(var9, var11).color(particleRed, particleBlue, particleGreen, 0.5F).lightmap(j, k).endVertex();
		buffer.pos(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10).tex(var9, var10).color(particleRed, particleBlue, particleGreen, 0.5F).lightmap(j, k).endVertex();
		buffer.pos(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10).tex(var8, var10).color(particleRed, particleBlue, particleGreen, 0.5F).lightmap(j, k).endVertex();
		buffer.pos(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10).tex(var8, var11).color(particleRed, particleBlue, particleGreen, 0.5F).lightmap(j, k).endVertex();
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
			// worldObj.playSoundAtEntity(this, "random.orb", 0.02F, 0.5F * ((worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.6F + 2.0F));
		}

		if (particleAge++ >= particleMaxAge) {
			setExpired();
		}

		motionY -= 0.04D * particleGravity;

		if (!canCollide) {
			pushOutOfBlocks(posX, posY, posZ);
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

			motionX = MathHelper.clamp((float) motionX, -0.2F, 0.2F);
			motionY = MathHelper.clamp((float) motionY, -0.2F, 0.2F);
			motionZ = MathHelper.clamp((float) motionZ, -0.2F, 0.2F);
		} else {
			motionX *= 0.9800000190734863D;
			motionY *= 0.9800000190734863D;
			motionZ *= 0.9800000190734863D;

			if (canCollide) {
				motionX *= 0.699999988079071D;
				motionZ *= 0.699999988079071D;
			}
		}
	}

	protected boolean pushOutOfBlocks(double x, double y, double z) {
		int xx = MathHelper.floor(x);
		int yy = MathHelper.floor(y);
		int zz = MathHelper.floor(z);
		double diffX = x - xx;
		double diffY = y - yy;
		double diffZ = z - zz;

		if (!world.isAirBlock(new BlockPos(xx, yy, zz)) && world.isBlockNormalCube(new BlockPos(xx, yy, zz), true) && !world.containsAnyLiquid(getBoundingBox())) {
			boolean var16 = !world.isBlockNormalCube(new BlockPos(xx - 1, yy, zz), true);
			boolean var17 = !world.isBlockNormalCube(new BlockPos(xx + 1, yy, zz), true);
			boolean var18 = !world.isBlockNormalCube(new BlockPos(xx, yy - 1, zz), true);
			boolean var19 = !world.isBlockNormalCube(new BlockPos(xx, yy + 1, zz), true);
			boolean var20 = !world.isBlockNormalCube(new BlockPos(xx, yy, zz - 1), true);
			boolean var21 = !world.isBlockNormalCube(new BlockPos(xx, yy, zz + 1), true);

			byte var22 = -1;
			double var23 = 9999.0D;

			if ((var16) && (diffX < var23)) {
				var23 = diffX;
				var22 = 0;
			}
			if ((var17) && (1.0D - diffX < var23)) {
				var23 = 1.0D - diffX;
				var22 = 1;
			}
			if ((var18) && (diffY < var23)) {
				var23 = diffY;
				var22 = 2;
			}
			if ((var19) && (1.0D - diffY < var23)) {
				var23 = 1.0D - diffY;
				var22 = 3;
			}
			if ((var20) && (diffZ < var23)) {
				var23 = diffZ;
				var22 = 4;
			}
			if ((var21) && (1.0D - diffZ < var23)) {
				var23 = 1.0D - diffZ;
				var22 = 5;
			}

			float var25 = rand.nextFloat() * 0.05F + 0.025F;
			float var26 = (rand.nextFloat() - rand.nextFloat()) * 0.1F;

			if (var22 == 0) {
				motionX = (-var25);
				motionY = (motionZ = var26);
			}
			if (var22 == 1) {
				motionX = var25;
				motionY = (motionZ = var26);
			}
			if (var22 == 2) {
				motionY = (-var25);
				motionX = (motionZ = var26);
			}
			if (var22 == 3) {
				motionY = var25;
				motionX = (motionZ = var26);
			}
			if (var22 == 4) {
				motionZ = (-var25);
				motionY = (motionX = var26);
			}
			if (var22 == 5) {
				motionZ = var25;
				motionY = (motionX = var26);
			}
			return true;
		}
		return false;
	}
}