package T145.magistics.common.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCustomSpikes extends WorldGenerator {
	private Block ore;

	public WorldGenCustomSpikes(Block oreBlock) {
		ore = oreBlock;
	}

	public WorldGenCustomSpikes(ItemStack oreStack) {
		ore = Block.getBlockFromItem(oreStack.getItem());
	}

	@Override
	public boolean generate(World world, Random rand, int i, int j, int k) {
		if (world.isAirBlock(i, j, k) && world.getBlock(i, j - 1, k) == Blocks.end_stone) {
			int l = rand.nextInt(32) + 6, i1 = rand.nextInt(4) + 1, j1, k1,  l1, i2;

			for (j1 = i - i1; j1 <= i + i1; j1++)
				for (k1 = k - i1; k1 <= k + i1; k1++) {
					l1 = j1 - i;
					i2 = k1 - k;

					if (l1 * l1 + i2 * i2 <= i1 * i1 + 1 && world.getBlock(j1, j - 1, k1) != Blocks.end_stone)
						return false;
				}

			for (j1 = j; j1 < j + l && j1 < 256; j1++)
				for (k1 = i - i1; k1 <= i + i1; k1++)
					for (l1 = k - i1; l1 <= k + i1; l1++) {
						i2 = k1 - i;
						int j2 = l1 - k;

						if (i2 * i2 + j2 * j2 <= i1 * i1 + 1)
							if (rand.nextInt(10) == 0)
								world.setBlock(k1, j1, l1, ore, 0, 2);
							else
								world.setBlock(k1, j1, l1, Blocks.obsidian, 0, 2);
					}

			EntityEnderCrystal entityendercrystal = new EntityEnderCrystal(	world);
			entityendercrystal.setLocationAndAngles((double) ((float) i + 0.5F), (double) (j + l), (double) ((float) k + 0.5F), rand.nextFloat() * 360F, 0F);
			world.spawnEntityInWorld(entityendercrystal);
			world.setBlock(i, j + l, k, Blocks.bedrock, 0, 2);
			return true;
		} else
			return false;
	}
}