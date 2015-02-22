package hu.hundevelopers.elysium.world.biome;

import T145.magistics.common.config.ConfigObjects;

public class ElysiumBiomeGenOcean extends ElysiumBiomeBase
{
	public ElysiumBiomeGenOcean(int id)
	{
		super(id);
		this.topBlock = ConfigObjects.blockPalestone;
		this.fillerBlock = ConfigObjects.blockPalestone;

	}
}
