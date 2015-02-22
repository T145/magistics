package hu.hundevelopers.elysium.world.biome;

import T145.magistics.common.config.ConfigObjects;

public class ElysiumBiomeGenBeach extends ElysiumBiomeBase
{

	public ElysiumBiomeGenBeach(int id)
	{
		super(id);

        this.topBlock = ConfigObjects.blockSand;
        this.fillerBlock = ConfigObjects.blockSand;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 0;
        this.theBiomeDecorator.reedsPerChunk = 0;
        this.theBiomeDecorator.cactiPerChunk = 3;
		
	}
}
