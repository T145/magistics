package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.Elysium;

public class ElysiumBiomeGenOcean extends ElysiumBiomeBase
{
	public ElysiumBiomeGenOcean(int id)
	{
		super(id);
		this.topBlock = Elysium.blockPalestone;
		this.fillerBlock = Elysium.blockPalestone;

	}
}
