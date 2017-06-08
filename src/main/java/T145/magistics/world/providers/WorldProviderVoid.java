package T145.magistics.world.providers;

import javax.annotation.Nullable;

import T145.magistics.init.ModDimensions;
import T145.magistics.world.generators.ChunkGeneratorVoid;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldProviderVoid extends WorldProvider {

	@Override
	public DimensionType getDimensionType() {
		return ModDimensions.voidDimensionType;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorVoid(world);
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		return new Vec3d(0D, 0D, 0D);
	}

	@Override
	public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
		return new Vec3d(0D, 0D, 0D);
	}

	@Override
	public long getWorldTime() {
		return 6000;
	}

	@Override
	public boolean isDaytime() {
		return true;
	}

	@Nullable
	@Override
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		return null;
	}
}