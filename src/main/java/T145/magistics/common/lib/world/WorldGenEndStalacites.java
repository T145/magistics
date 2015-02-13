package T145.magistics.common.lib.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenEndStalacites extends WorldGenerator {
	float radius = 2F;
	int maxLength = 5,  minLength = 3;

	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (!world.isAirBlock(x, y, z) || world.getBlock(x, y + 1, z) != Blocks.end_stone)
			return false;
		world.setBlock(x, y, z, Blocks.end_stone, 0, 2);
		int length = rand.nextInt(maxLength - minLength) + minLength;
		for (int i = -2; i < 2; ++i)
			for (int j = -2; j < 2; ++j)
				for (int k = -2; k <= (int) (1F / Math.max(0.5F, i * i + j * j) * length + rand.nextInt(2)); k++)
					if (i * i + j * j < radius * radius)
						world.setBlock(x + i, y - k, z + j, Blocks.end_stone);
		return true;
	}
}