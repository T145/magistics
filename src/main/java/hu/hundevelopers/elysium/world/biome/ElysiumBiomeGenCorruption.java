package hu.hundevelopers.elysium.world.biome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import T145.magistics.common.config.ConfigObjects;

public class ElysiumBiomeGenCorruption extends ElysiumBiomeBase
{
	public ElysiumBiomeGenCorruption(int id)
	{
		super(id);

		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.flowersPerChunk = 1;
		
		this.enableRain = true;
	}

	/**
     * Gets a WorldGen appropriate for this biome.
     */
	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
		return rand.nextInt(3) > 0 ? new WorldGenTallGrass(ConfigObjects.blockTallGrass, 0) : new WorldGenTallGrass(Blocks.fire, 0);
    }
	
    @Override
    public void decorate(World currentWorld, Random randomGenerator, int chunk_X, int chunk_Z)
    {
		super.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z);
		
		
    }
}
