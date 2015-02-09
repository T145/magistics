package T145.magistics.client.lib;

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

public class CustomParticle extends EntityFX
{
	ResourceLocation resource = null;
	int brightness = 200;

	public CustomParticle(World world, double x, double y, double z, double mx, double my, double mz)
	{
		super(world, x, y, z, mx, my, mz);
		this.multipleParticleScaleBy(0.1F);
		this.particleMaxAge = (int) (4.0F / (this.rand.nextFloat() * 0.9F + 0.1F));
		this.motionX = mx;
		this.motionY = my;
		this.motionZ = mz;
		this.setParticleTextureIndex(-1);
	}

	public void setTextureFile(ResourceLocation resource)
	{
		this.resource = resource;
	}

	/**
	 * Sets the particles brightness for rendering. The maximum value is about
	 * 240.
	 * 
	 * @param amount
	 */
	public void setBrightness(int amount)
	{
		this.brightness = amount;
	}

	@Override
	public void renderParticle(Tessellator tessellator, float tick, float rotationX, float rotationXZ, float rotationZ, float rotationYZ, float rotationXY)
	{
		if(this.resource != null)
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.resource);
		else
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("textures/particle/particles.png"));

		float textureCoordX1 = this.particleTextureIndexX / 16.0F;
		float textureCoordX2 = textureCoordX1 + 0.0624375F;
		float textureCoordY1 = this.particleTextureIndexY / 16.0F;
		float textureCoordY2 = textureCoordY1 + 0.0624375F;

		if(this.particleTextureIndexX < 0)
		{
			textureCoordX1 = 0F;
			textureCoordX2 = 1F;
			textureCoordY1 = 0F;
			textureCoordY2 = 1F;
		}

		if(this.particleIcon != null)
		{
			textureCoordX1 = this.particleIcon.getMinU();
			textureCoordX2 = this.particleIcon.getMaxU();
			textureCoordY1 = this.particleIcon.getMinV();
			textureCoordY2 = this.particleIcon.getMaxV();
		}

		float x = (float) (this.prevPosX + (this.posX - this.prevPosX) * tick - EntityFX.interpPosX);
		float y = (float) (this.prevPosY + (this.posY - this.prevPosY) * tick - EntityFX.interpPosY);
		float z = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * tick - EntityFX.interpPosZ);

		/*
		 * tessellator.setBrightness(this.brightness); if(this.particleRed >= 0)
		 * tessellator.setColorRGBA_F(this.particleRed * colorMultiplier,
		 * this.particleGreen * colorMultiplier, this.particleBlue *
		 * colorMultiplier, this.particleAlpha);
		 * tessellator.addVertexWithUV((double)(x - rotationX * scaleAmount -
		 * rotationYZ * scaleAmount), (double)(y - rotationXZ * scaleAmount),
		 * (double)(z - rotationZ * scaleAmount - rotationXY * scaleAmount),
		 * (double)textureCoordX2, (double)textureCoordY2);
		 * tessellator.addVertexWithUV((double)(x - rotationX * scaleAmount +
		 * rotationYZ * scaleAmount), (double)(y + rotationXZ * scaleAmount),
		 * (double)(z - rotationZ * scaleAmount + rotationXY * scaleAmount),
		 * (double)textureCoordX2, (double)textureCoordY1);
		 * tessellator.addVertexWithUV((double)(x + rotationX * scaleAmount +
		 * rotationYZ * scaleAmount), (double)(y + rotationXZ * scaleAmount),
		 * (double)(z + rotationZ * scaleAmount + rotationXY * scaleAmount),
		 * (double)textureCoordX1, (double)textureCoordY1);
		 * tessellator.addVertexWithUV((double)(x + rotationX * scaleAmount -
		 * rotationYZ * scaleAmount), (double)(y - rotationXZ * scaleAmount),
		 * (double)(z + rotationZ * scaleAmount - rotationXY * scaleAmount),
		 * (double)textureCoordX1, (double)textureCoordY2);
		 */

		glColor4f(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);

		glBegin(GL_QUADS);
		glTexCoord2f(textureCoordX2, textureCoordY2);
		glVertex3f(x - rotationX * this.particleScale - rotationYZ * this.particleScale, y - rotationXZ * this.particleScale, z - rotationZ * this.particleScale - rotationXY * this.particleScale);
		glTexCoord2f(textureCoordX2, textureCoordY1);
		glVertex3f(x - rotationX * this.particleScale + rotationYZ * this.particleScale, y + rotationXZ * this.particleScale, z - rotationZ * this.particleScale + rotationXY * this.particleScale);
		glTexCoord2f(textureCoordX1, textureCoordY1);
		glVertex3f(x + rotationX * this.particleScale + rotationYZ * this.particleScale, y + rotationXZ * this.particleScale, z + rotationZ * this.particleScale + rotationXY * this.particleScale);
		glTexCoord2f(textureCoordX1, textureCoordY2);
		glVertex3f(x + rotationX * this.particleScale - rotationYZ * this.particleScale, y - rotationXZ * this.particleScale, z + rotationZ * this.particleScale - rotationXY * this.particleScale);
		glEnd();

		if(this.resource != null)
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("textures/particle/particles.png"));
	}
}
