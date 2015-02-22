package hu.hundevelopers.elysium.world.gen.features;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.block.ElysiumBlockSapling;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenDarkFostimber extends WorldGenerator
{
	Block leaves,log;
	boolean fromSapling;
	private int logMeta = 0;
	private int leavesMeta = 0;
	
	public ElysiumGenDarkFostimber(Block leaves, Block log, boolean fromSapling, boolean isCorrupted)
	{
		this.leaves = leaves;
		this.log = log;
		this.fromSapling = fromSapling;
		this.isCorrupted = isCorrupted;
	}
	
	public ElysiumGenDarkFostimber(Block leaves, Block log, int leavesMeta, int logMeta, boolean fromSapling, boolean isCorrupted)
	{
		this(leaves, log, fromSapling, isCorrupted);
		this.leavesMeta = leavesMeta;
		this.logMeta = logMeta;
	}
	boolean isCorrupted = false;

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		int cap = random.nextInt(2) + 2;
		int trunk = 3;
		int minTreeHeight = 6;
	
		int treeHeight = trunk + minTreeHeight;
	
		if(!((ElysiumBlockSapling)Elysium.blockSapling).canBlockStay(world, x, y-1, z))
			return false;
	
		if(fromSapling)
		{
			for(int j = 1; j <= treeHeight; j++)
			{
				if(!world.isAirBlock(x, y+j, z))
				return false;
			}
		}
		else
		{
			for(int i = -2; i <= 2; i++)
			{
				for(int k = -2; k <= 2; k++)
				{
					for(int j = 1; j <= treeHeight; j++)
					{
						if(!world.isAirBlock(x+i, y+j, z+k))
							return false;
					}
				}
	        }
		}
	
		int h1 = treeHeight - random.nextInt(2);
		int h2 = treeHeight - random.nextInt(2);
		int h3 = treeHeight - random.nextInt(2);
		int h4 = treeHeight - random.nextInt(2);
	
		int hMin = Math.min(h1, Math.min(h2, Math.min(h3, h4)));
		
		for(int i = 0; i < treeHeight + cap; i++)
		{
	
			if(i < treeHeight)
			{
				world.setBlock(x, y + i, z, log, logMeta, 3);
			}
	
			if(i >= trunk && i < treeHeight + 1)
			{
		    	addLeaves(world, x + 1, y+i, z);
		    	addLeaves(world, x - 1, y+i, z);
		    	addLeaves(world, x, y+i, z + 1);
		    	addLeaves(world, x, y+i, z - 1);
			}
	
			if(i > trunk && i < h1)
		    	addLeaves(world, x + 1, y+i, z + 1);
			if(i > trunk && i < h2)
				addLeaves(world, x - 1, y+i, z - 1);
			if(i > trunk && i < h3)
				addLeaves(world, x - 1, y+i, z + 1);
			if(i > trunk && i < h4)
				addLeaves(world, x + 1, y+i, z - 1);

			if(i == hMin-1)
			{
				addLeaves(world, x + 2, y+i, z);
		    	addLeaves(world, x - 2, y+i, z);
		    	addLeaves(world, x, y+i, z + 2);
		    	addLeaves(world, x, y+i, z - 2);
			}
			

			if(i == hMin-2)
			{
		    	addLeaves(world, x + 2, y+i, z-1);
		    	addLeaves(world, x - 2, y+i, z-1);
		    	addLeaves(world, x-1, y+i, z + 2);
		    	addLeaves(world, x-1, y+i, z - 2);
		    	
				addLeaves(world, x + 3, y+i, z);
		    	addLeaves(world, x - 3, y+i, z);
		    	addLeaves(world, x, y+i, z + 3);
		    	addLeaves(world, x, y+i, z - 3);
			}
			
			if(i == hMin-3 || i == hMin-2)
			{
				addLeaves(world, x + 2, y+i, z);
		    	addLeaves(world, x - 2, y+i, z);
		    	addLeaves(world, x, y+i, z + 2);
		    	addLeaves(world, x, y+i, z - 2);
			}
			
			if(i >= treeHeight)
			{
				addLeaves(world, x, y+i, z);
			}
			
			for(int l = 0; l < 50; l++)
			{
				if(isCorrupted)
					makeVines(world, random, x + random.nextInt(7) - 3, y+i, z + random.nextInt(7) - 3);
			}
		}
	
		return true;
	}

	private void makeVines(World world, Random random, int i, int j, int k)
	{
		if (world.isAirBlock(i, j, k))
        {
            for (int j1 = 2; j1 <= 5; ++j1)
            {
                if (Blocks.vine.canPlaceBlockOnSide(world, i, j, k, j1))
                {
                	for(int l = 0; l < (world.getTopSolidOrLiquidBlock(i, k) - i - random.nextInt(3)); l++)
                	{
                		if(world.isAirBlock(i, j - l, k))
                			world.setBlock(i, j - l, k, Blocks.vine, 1 << Direction.facingToDirection[Facing.oppositeSide[j1]], 2);
                	}
                	break;
                }
            }
        }
	}
	private boolean addLeaves(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		if(block == null || block.canBeReplacedByLeaves(world, x, y, z))
		{
			world.setBlock(x, y, z, leaves, leavesMeta, 3);
			return true;
		}
		return false;
	}
}
