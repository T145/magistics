package hu.hundevelopers.elysium.world.gen.features;

import hu.hundevelopers.elysium.Elysium;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenCrystalSpikes extends WorldGenerator
{

	public ElysiumGenCrystalSpikes(int meta)
	{
		this.meta = meta;
	}

	private int meta = 0;

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		y = world.getTopSolidOrLiquidBlock(x, z);
		if (world.getBlock(x, y - 1, z).getMaterial().isLiquid() || world.getBlock(x, y - 1, z) != Elysium.blockGrass)
		{
			return false;
		}

		int directionX = 0;
		int directionZ = 0;
		while (directionZ == 0 && directionX == 0) {
			directionX = 1 - rand.nextInt(3);
			directionZ = 1 - rand.nextInt(3);
		}

		int length = 5 + rand.nextInt(4);
		float radius = length / 2F;

		for (int j = 0; j < length; j++) {
			for (int i = (int) -radius; i < radius; i++) {
				for (int k = (int) -radius; k < radius; k++) {
					if (i * i + k * k < radius) {
						Block old = world.getBlock(x + i + directionX * j, y
								+ j, z + k + directionZ * j);
						if (old != Blocks.quartz_block
								&& old != Elysium.blockQuartzBlock
								&& old != Blocks.gold_block
								&& old != Elysium.blockPortalCore) {
							world.setBlock(x + i + directionX * j, y + j, z + k
									+ directionZ * j,
									Elysium.blockEnergyCrystal, meta, 2);
						}

						old = world.getBlock(x - i - directionX * j, y - j, z
								- k - directionZ * j);
						if (old != Blocks.quartz_block
								&& old != Elysium.blockQuartzBlock
								&& old != Blocks.gold_block
								&& old != Elysium.blockPortalCore) {
							world.setBlock(x - i - directionX * j, y - j, z - k
									- directionZ * j,
									Elysium.blockEnergyCrystal, meta, 2);

						}
					}
				}
			}
			radius -= 0.5F;
		}

		return true;
	}
}
