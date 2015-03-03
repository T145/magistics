package T145.magistics.common.lib.world.biomes;

import java.util.Random;

import net.minecraft.world.World;
import T145.magistics.common.lib.world.ElysiumGenDefenceTower;

public class ElysiumBiomeGenForest extends ElysiumBiomeBase
{

	public ElysiumBiomeGenForest(int id)
	{
		super(id);
//		this.temperature = 1.5F;
//		this.rainfall = 0.8F;
	}

    @Override
    public void decorate(World world, Random rand, int chunk_X, int chunk_Z)
    {
		super.decorate(world, rand, chunk_X, chunk_Z);

		if (rand.nextInt(10) == 0)
        {
            int k = chunk_X + rand.nextInt(16) + 8;
            int l = chunk_Z + rand.nextInt(16) + 8;
            ElysiumGenDefenceTower worldgenpyramid = new ElysiumGenDefenceTower();
            worldgenpyramid.generate(world, rand, k, world.getHeightValue(k, l)-1, l);
        }
    }
}
