package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.world.gen.structures.ElysiumGenDefenceTowerCorrupted;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumBiomeGenPlainCorrupted extends ElysiumBiomeBase
{
	public ElysiumBiomeGenPlainCorrupted(int id)
	{
		super(id);

		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.flowersPerChunk = 1;
		
		this.setDisableRain();
	}

	/**
     * Gets a WorldGen appropriate for this biome.
     */
	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
		return new WorldGenTallGrass(Elysium.blockTallGrass, 0);
    }
	
    @Override
    public void decorate(World world, Random rand, int chunk_X, int chunk_Z)
    {
    	if (rand.nextInt(10) == 0)
        {
            int k = chunk_X + rand.nextInt(16) + 8;
            int l = chunk_Z + rand.nextInt(16) + 8;
            ElysiumGenDefenceTowerCorrupted worldgenpyramid = new ElysiumGenDefenceTowerCorrupted();
            worldgenpyramid.generate(world, rand, k, world.getHeightValue(k, l)-1, l);
        }
    	
		super.decorate(world, rand, chunk_X, chunk_Z);
    }
}
