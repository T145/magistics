package T145.magistics.common.lib.world.biomes;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.lib.world.ElysiumGenDefenceTowerCorrupted;
import T145.magistics.common.lib.world.ElysiumGenEnderPyramid;

public class ElysiumBiomeGenDesert extends ElysiumBiomeBase
{

	public ElysiumBiomeGenDesert(int id)
	{
		super(id);
		
		this.topBlock = ConfigObjects.blockSand;
        this.fillerBlock = ConfigObjects.blockSand;
        this.theBiomeDecorator.deadBushPerChunk = -10;
        this.theBiomeDecorator.grassPerChunk = 1;
        this.theBiomeDecorator.reedsPerChunk = -10;
        this.theBiomeDecorator.cactiPerChunk = -10;
	}
	
	/**
     * Gets a WorldGen appropriate for this biome.
     */
	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
		return new WorldGenTallGrass(ConfigObjects.blockCactus, 0);
    }

	public void decorate(World world, Random rand, int chunkX, int chunkZ)
    {
        super.decorate(world, rand, chunkX, chunkZ);

        if (rand.nextInt(100) == 0)
        {
            int k = chunkX + rand.nextInt(16) + 8;
            int l = chunkZ + rand.nextInt(16) + 8;
            ElysiumGenEnderPyramid worldgenpyramid = new ElysiumGenEnderPyramid();
            worldgenpyramid.generate(world, rand, k, world.getHeightValue(k, l)-1, l);
        }
        if (rand.nextInt(50) == 0)
        {
            int k = chunkX + rand.nextInt(16) + 8;
            int l = chunkZ + rand.nextInt(16) + 8;
            ElysiumGenDefenceTowerCorrupted worldgenpyramid = new ElysiumGenDefenceTowerCorrupted();
            worldgenpyramid.generate(world, rand, k, world.getHeightValue(k, l)-1, l);
        }
    	
    }
}
