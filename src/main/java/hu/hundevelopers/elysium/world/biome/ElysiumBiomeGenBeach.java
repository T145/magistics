package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;

public class ElysiumBiomeGenBeach extends ElysiumBiomeBase
{

	public ElysiumBiomeGenBeach(int id)
	{
		super(id);

        this.topBlock = Elysium.blockSand;
        this.fillerBlock = Elysium.blockSand;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 0;
        this.theBiomeDecorator.reedsPerChunk = 0;
        this.theBiomeDecorator.cactiPerChunk = 3;
		
	}
}
