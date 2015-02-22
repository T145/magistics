package hu.hundevelopers.elysium.world.gen.features;

import hu.hundevelopers.elysium.Elysium;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenCorruptFostimber extends WorldGenerator
{
	Block log;
	private int logMeta = 0;

	public ElysiumGenCorruptFostimber(Block log)
	{
		super(false);
		this.log = log;
	}
	
	public ElysiumGenCorruptFostimber(Block log, int logMeta)
	{
		this(log);
		this.logMeta = logMeta;
	}
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{

		Block top = world.getBlock(x, y + 1, z);
		if(top.getMaterial().isLiquid() || top == Elysium.blockLeaves || top == Elysium.blockLog)
			return false;
		
		int treeHeight = 5 + random.nextInt(7);
	
		for(int i = 0; i < treeHeight; i++)
		{
				world.setBlock(x, y + i, z, log, logMeta, 2);
				makeVines(world, random, x + random.nextInt(3) - 1, y+i, z + random.nextInt(3) - 1);
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
}
