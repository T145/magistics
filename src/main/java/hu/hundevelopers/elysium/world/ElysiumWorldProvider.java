package hu.hundevelopers.elysium.world;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.render.CloudRendererElysium;
import hu.hundevelopers.elysium.render.SkyRendererElysium;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenPlain;
import hu.hundevelopers.elysium.world.gen.ChunkProviderElysium;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumWorldProvider extends WorldProvider
{
	private float[] colorsSunriseSunset = new float[4];

	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new ElysiumChunkManager(this.worldObj);
		this.dimensionId = Elysium.dimensionID;
		this.hasNoSky = false;

		if(worldObj.isRemote)
		{
			this.setSkyRenderer(new SkyRendererElysium());
			this.setCloudRenderer(new CloudRendererElysium());
		}
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderElysium(this.worldObj, this.worldObj.getSeed(), false/*terrainfeatures*/);
	}

	@Override
	public int getAverageGroundLevel()
	{
		return 64;
	}

	@Override
	public String getDimensionName()
	{
		return "The Elysium";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored()
	{
		return true;
	}

	@Override
	public boolean canRespawnHere()
	{
		return true;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return true;
	}
	
    /**
     * returns true if this dimension is supposed to display void particles and pull in the far plane based on the
     * user's Y offset.
     */
    @SideOnly(Side.CLIENT)
    @Override
	public boolean getWorldHasVoidParticles()
    {
        return false;
    }

    /**
     * Returns a double value representing the Y value relative to the top of the map at which void fog is at its
     * maximum. The default factor of 0.03125 relative to 256, for example, means the void fog will be at its maximum at
     * (256*0.03125), or 8.
     */
    @SideOnly(Side.CLIENT)
    @Override
	public double getVoidFogYFactor()
    {
        return 0.03125;
    }
    
    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    @SideOnly(Side.CLIENT)
    @Override
	public boolean doesXZShowFog(int par1, int par2)
    {
        return false;
    }

	/**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
	@Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        return this.worldObj.getBiomeGenForCoords(par1, par2) instanceof ElysiumBiomeGenPlain && this.worldObj.getTopBlock(par1, par2) == Elysium.blockGrass;
    }

	@Override
	public ChunkCoordinates getEntrancePortalLocation()
	{
		return new ChunkCoordinates(50, 5, 0);
	}

	@Override
	protected void generateLightBrightnessTable()
	{
		float f = 0.0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

	@Override
	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage()
	{
		return "Entering The Elysium";
	}

	/**
    * A Message to display to the user when they transfer out of this dismension.
    *
    * @return The message to be displayed
    */
	@Override
	public String getDepartMessage()
    {
		return "Leaving The Elysium";
    }

	@Override
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float par1, float par2) {
		float f2 = 0.4F;
		float f3 = MathHelper.cos(par1 * 3.141593F * 2.0F) - 0.0F;
		float f4 = -0.0F;
		if ((f3 >= f4 - f2) && (f3 <= f4 + f2)) {
			float f5 = (f3 - f4) / f2 * 0.5F + 0.5F;
			float f6 = 1.0F - (1.0F - MathHelper.sin(f5 * 3.141593F)) * 0.99F;
			f6 *= f6;
			this.colorsSunriseSunset[0] = (f5 * 0.3F + 0.7F);
			this.colorsSunriseSunset[1] = (f5 * f5 * 0.7F + 0.2F);
			this.colorsSunriseSunset[2] = (f5 * f5 * 0.0F + 0.2F);
			this.colorsSunriseSunset[3] = f6;
			return this.colorsSunriseSunset;
		}
		return null;
	}
    
	/**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
	@Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        int j = (int)(par1 % 24000L);
        float f1 = ((float)j + par3) / 24000.0F - 0.25F;

        if (f1 < 0.0F)
        {
            ++f1;
        }

        if (f1 > 1.0F)
        {
            --f1;
        }

        float f2 = f1;
        f1 = 1.0F - (float)((Math.cos((double)f1 * Math.PI) + 1.0D) / 2.0D);
        f1 = f2 + (f1 - f2) / 3.0F;
        return f1;
    }


    /**
     * Return Vec3D with biome specific fog color
     */
	@SideOnly(Side.CLIENT)
	@Override
	public Vec3 getFogColor(float celsianAngle, float tick)
    {
        float f2 = MathHelper.cos(celsianAngle * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        float f3 = 0.7529412F;
        float f4 = 0.84705883F;
        float f5 = 1.0F;
        f3 *= f2 * 0.94F + 0.06F;
        f4 *= f2 * 0.94F + 0.06F;
        f5 *= f2 * 0.91F + 0.09F;
//        
//		int i = 0xeff1f2;
//		float f2 = MathHelper.cos(celsianAngle * 3.141593F * 2.0F) * 2.0F + 0.5F;
//		if (f2 < 0.0F) {
//			f2 = 0.0F;
//		}
//		if (f2 > 1.0F) {
//			f2 = 1.0F;
//		}
//		float f3 = (i >> 16 & 0xFF) / 255.0F;
//		float f4 = (i >> 8 & 0xFF) / 255.0F;
//		float f5 = (i & 0xFF) / 255.0F;
//		f3 *= (f2 * 0.0F + 0.15F);
//		f4 *= (f2 * 0.0F + 0.15F);
//		f5 *= (f2 * 0.0F + 0.15F);
		
        return Vec3.createVectorHelper((double)f3, (double)f4, (double)f5);
    }

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
	{
		double bright = 1D;
		return Vec3.createVectorHelper(1.17D*bright, 2.27D*bright, 2.55D*bright);
	}
}
