package T145.magistics.client.lib.craftingpillars;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;

public class PillarParticle extends EntityFX {
	public ResourceLocation resource;
	public int brightness = 200;

	public PillarParticle(World world, double x, double y, double z, double mx, double my, double mz) {
		super(world, x, y, z, mx, my, mz);
		multipleParticleScaleBy(0.1F);
		particleMaxAge = (int) (4F / (rand.nextFloat() * 0.9F + 0.1F));
		motionX = mx;
		motionY = my;
		motionZ = mz;
		setParticleTextureIndex(-1);
	}

	public void setTextureFile(ResourceLocation texture) {
		resource = texture;
	}

	public void setBrightness(int amount) {
		brightness = amount;
	}

	@Override
	public void renderParticle(Tessellator tessellator, float tick, float rotationX, float rotationXZ, float rotationZ, float rotationYZ, float rotationXY) {
		if (resource != null)
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(resource);
		else
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("textures/particle/particles.png"));

		float textureCoordX1 = particleTextureIndexX / 16F, textureCoordX2 = textureCoordX1 + 0.0624375F, textureCoordY1 = particleTextureIndexY / 16F, textureCoordY2 = textureCoordY1 + 0.0624375F;

		if (particleTextureIndexX < 0) {
			textureCoordX1 = 0F;
			textureCoordX2 = 1F;
			textureCoordY1 = 0F;
			textureCoordY2 = 1F;
		}

		if (particleIcon != null) {
			textureCoordX1 = particleIcon.getMinU();
			textureCoordX2 = particleIcon.getMaxU();
			textureCoordY1 = particleIcon.getMinV();
			textureCoordY2 = particleIcon.getMaxV();
		}

		float x = (float) (prevPosX + (posX - prevPosX) * tick - EntityFX.interpPosX), y = (float) (prevPosY + (posY - prevPosY) * tick - EntityFX.interpPosY), z = (float) (prevPosZ + (posZ - prevPosZ) * tick - EntityFX.interpPosZ);

		glColor4f(particleRed, particleGreen, particleBlue, particleAlpha);

		glBegin(GL_QUADS);
		glTexCoord2f(textureCoordX2, textureCoordY2);
		glVertex3f(x - rotationX * particleScale - rotationYZ * particleScale, y - rotationXZ * particleScale, z - rotationZ * particleScale - rotationXY * particleScale);
		glTexCoord2f(textureCoordX2, textureCoordY1);
		glVertex3f(x - rotationX * particleScale + rotationYZ * particleScale, y + rotationXZ * particleScale, z - rotationZ * particleScale + rotationXY * particleScale);
		glTexCoord2f(textureCoordX1, textureCoordY1);
		glVertex3f(x + rotationX * particleScale + rotationYZ * particleScale, y + rotationXZ * particleScale, z + rotationZ * particleScale + rotationXY * particleScale);
		glTexCoord2f(textureCoordX1, textureCoordY2);
		glVertex3f(x + rotationX * particleScale - rotationYZ * particleScale, y - rotationXZ * particleScale, z + rotationZ * particleScale - rotationXY * particleScale);
		glEnd();

		if (resource != null)
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("textures/particle/particles.png"));
	}
}