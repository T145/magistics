package T145.magistics.common.lib.world.biomes;

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
