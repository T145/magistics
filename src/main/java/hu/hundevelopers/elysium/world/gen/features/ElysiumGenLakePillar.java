package hu.hundevelopers.elysium.world.gen.features;

import hu.hundevelopers.elysium.Elysium;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenLakePillar extends WorldGenerator
{
	Block pillarBlock;
	
	public ElysiumGenLakePillar(Block block)
	{
		this.pillarBlock = block;
	}
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		y = world.getTopSolidOrLiquidBlock(x, z);

		if(world.getBlock(x, y, z) != Elysium.blockElysiumWater)
			return false;

		int h = y + random.nextInt(6)+1;

		int j;
		for(j = y; world.getBlock(x, j, z) == Elysium.blockElysiumWater; j--)
		{
			world.setBlock(x, j, z, pillarBlock);
			world.setBlockMetadataWithNotify(x, j, z, 0, 1);
		}

		for(j = y+1; world.isAirBlock(x, j, z) && (j <= h); j++)
		{
			world.setBlock(x, j, z, pillarBlock);
			world.setBlockMetadataWithNotify(x, j, z, 0, 1);
		}

		if(j == h+1)
		{
			//System.out.println("yay");
			int w1 = random.nextInt(4);
			int w2 = w1 + random.nextInt(3)-1;

			if(random.nextBoolean())
			{
				for(int i = 1; (i <= w1) && world.isAirBlock(x+i, j, z); i++)
				{
					world.setBlock(x+i, j, z, pillarBlock);
					world.setBlockMetadataWithNotify(x+i, j, z, 2, 1);
				}

				for(int i = 0; (i <= w2) && world.isAirBlock(x-i, j, z); i++)
				{
					world.setBlock(x-i, j, z, pillarBlock);
					world.setBlockMetadataWithNotify(x-i, j, z, 2, 1);
				}
			}
			else
			{
				for(int i = 1; (i <= w1) && world.isAirBlock(x, j, z+i); i++)
				{
					world.setBlock(x, j, z+i, pillarBlock);
					world.setBlockMetadataWithNotify(x, j, z+i, 1, 1);
				}

				for(int i = 0; (i <= w2) && world.isAirBlock(x, j, z-i); i++)
				{
					world.setBlock(x, j, z-i, pillarBlock);
					world.setBlockMetadataWithNotify(x, j, z-i, 1, 1);
				}
			}
		}

		//spawn mobs
		
//		System.out.println("Generated pillar at: " + (x) + " " + (j+1) + " " + (z));
		return true;
	}
}
