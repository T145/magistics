package T145.magistics.common.lib.world;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import T145.magistics.common.config.ConfigObjects;

public class WorldGenMithrilSpikes extends WorldGenerator {
	public boolean generate(World world, Random random, int x, int y, int z) {
		if (world.isAirBlock(x, y, z) && world.getBlock(x, y - 1, z) == Blocks.end_stone) {
			int l = random.nextInt(32) + 6, i1 = random.nextInt(4) + 1;
			for (int j1 = x - i1; j1 <= x + i1; ++j1)
				for (int k1 = z - i1; k1 <= z + i1; ++k1) {
					int l2 = j1 - x, i2 = k1 - z;
					if (l2 * l2 + i2 * i2 <= i1 * i1 + 1 && world.getBlock(j1, y - 1, k1) != Blocks.end_stone)
						return false;
				}
			for (int j1 = y; j1 < y + l && j1 < 256; ++j1)
				for (int k1 = x - i1; k1 <= x + i1; ++k1)
					for (int l2 = z - i1; l2 <= z + i1; ++l2) {
						int i2 = k1 - x, j2 = l2 - z;
						if (i2 * i2 + j2 * j2 <= i1 * i1 + 1)
							if (random.nextInt(10) == 0)
								world.setBlock(k1, j1, l2, ConfigObjects.blockMithrilOre, 0, 2);
							else
								world.setBlock(k1, j1, l2, Blocks.obsidian, 0, 2);
					}
			EntityEnderCrystal entityendercrystal = new EntityEnderCrystal(world);
			entityendercrystal.setLocationAndAngles((double) (x + 0.5f), (double) (y + l), (double) (z + 0.5f), random.nextFloat() * 360.0f, 0.0f);
			world.spawnEntityInWorld((Entity) entityendercrystal);
			world.setBlock(x, y + l, z, Blocks.bedrock, 0, 2);
			return true;
		}
		return false;
	}
}