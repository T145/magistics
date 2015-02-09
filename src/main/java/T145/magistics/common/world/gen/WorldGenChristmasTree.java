package T145.magistics.common.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import T145.magistics.common.config.ConfigObjects;

public class WorldGenChristmasTree extends WorldGenerator
{
	private Block leaves, log;
	int logMeta;
	private boolean fromSapling;

	public WorldGenChristmasTree(boolean fromSapling)
	{
		super(fromSapling);
		this.fromSapling = fromSapling;
		this.leaves = ConfigObjects.blockChristmasLeaves;
		this.log = Blocks.log;
		this.logMeta = 1;
	}

//	public ChristmasTreeGen(boolean fromSapling, int stage)
//	{
//		this(fromSapling);
//		this.stage = stage;
//	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		if(!this.fromSapling)
			y += 1;

		//We regenerate it every time so it always look good (player can't really replace it if broken by accident)
//		if(this.stage >= 0)
//		{
			this.addLog(world, x, y, z);
			this.addLeaves(world, x, y+1, z);
			this.addLeaves(world, x+1, y, z);
			this.addLeaves(world, x, y, z+1);
			this.addLeaves(world, x-1, y, z);
			this.addLeaves(world, x, y, z-1);
//		}
//		if(this.stage >= 1)
//		{
			this.addLog(world, x, y+1, z);
			this.addLeaves(world, x, y+2, z);
			this.addLeaves(world, x+1, y+1, z);
			this.addLeaves(world, x, y+1, z+1);
			this.addLeaves(world, x-1, y+1, z);
			this.addLeaves(world, x, y+1, z-1);

			if(world.getBlock(x+1, y, z) == this.leaves)
				world.setBlockToAir(x+1, y, z);
			if(world.getBlock(x, y, z+1) == this.leaves)
				world.setBlockToAir(x, y, z+1);
			if(world.getBlock(x-1, y, z) == this.leaves)
				world.setBlockToAir(x-1, y, z);
			if(world.getBlock(x, y, z-1) == this.leaves)
				world.setBlockToAir(x, y, z-1);
//		}
//		if(this.stage >= 2)
//		{
			//lvl1
			this.addLeaves(world, x+1, y+1, z+1);
			this.addLeaves(world, x-1, y+1, z-1);
			this.addLeaves(world, x+1, y+1, z-1);
			this.addLeaves(world, x-1, y+1, z+1);

			//lvl2
			this.addLeaves(world, x+1, y+2, z);
			this.addLeaves(world, x, y+2, z+1);
			this.addLeaves(world, x-1, y+2, z);
			this.addLeaves(world, x, y+2, z-1);
			this.addLog(world, x, y+2, z);

			//lvl3
			this.addLeaves(world, x, y+3, z);

//		}
//		if(this.stage >= 3)
//		{
			//lvl1
			this.addLeaves(world, x+2, y+1, z);
			this.addLeaves(world, x-2, y+1, z);
			this.addLeaves(world, x, y+1, z-2);
			this.addLeaves(world, x, y+1, z+2);

			//lvl2
			this.addLeaves(world, x+1, y+2, z+1);
			this.addLeaves(world, x-1, y+2, z-1);
			this.addLeaves(world, x+1, y+2, z-1);
			this.addLeaves(world, x-1, y+2, z+1);

			//lvl3
			this.addLeaves(world, x+1, y+3, z);
			this.addLeaves(world, x, y+3, z+1);
			this.addLeaves(world, x-1, y+3, z);
			this.addLeaves(world, x, y+3, z-1);
			this.addLog(world, x, y+3, z);

			//lvl4
			this.addLeaves(world, x, y+4, z);
//		}
//		if(this.stage >= 4)
//		{
			//lvl1
			this.addLeaves(world, x+1, y+1, z+2);
			this.addLeaves(world, x-1, y+1, z+2);
			this.addLeaves(world, x+2, y+1, z+1);
			this.addLeaves(world, x+2, y+1, z-1);
			this.addLeaves(world, x+1, y+1, z-2);
			this.addLeaves(world, x-1, y+1, z-2);
			this.addLeaves(world, x-2, y+1, z+1);
			this.addLeaves(world, x-2, y+1, z-1);

			//lvl2
			this.addLeaves(world, x+2, y+2, z);
			this.addLeaves(world, x, y+2, z+2);
			this.addLeaves(world, x-2, y+2, z);
			this.addLeaves(world, x, y+2, z-2);

			//lvl3
			this.addLeaves(world, x+1, y+3, z+1);
			this.addLeaves(world, x-1, y+3, z-1);
			this.addLeaves(world, x+1, y+3, z-1);
			this.addLeaves(world, x-1, y+3, z+1);

			//lvl4
			this.addLeaves(world, x+1, y+4, z);
			this.addLeaves(world, x, y+4, z+1);
			this.addLeaves(world, x-1, y+4, z);
			this.addLeaves(world, x, y+4, z-1);
			this.addLog(world, x, y+4, z);

			//lvl5
			this.addLeaves(world, x, y+5, z);

			for(int a = 0; a < 5; a++)
			{
				int i = x+random.nextInt(5)-2;
				int k = z+random.nextInt(5)-2;
				int j = y - 5;

				while(j < y + 5)
				{
					Block block = world.getBlock(i, j, k);
					if((block == null || block.canBeReplacedByLeaves(world, i, j, k) || block.isReplaceable(world, i, j, k)))
					{
						break;
					} else {
						j++;
					}
				}

				if(world.getBlock(i, j-1, k) != this.leaves)
				{	 		
					world.setBlock(i, j, k, ConfigObjects.blockChristmasPresent);
					world.setBlockMetadataWithNotify(i, j, k, random.nextInt(2), 2);
				}
			}
//		}

		return true;
	}

	private boolean addLeaves(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		if(block == null || block.canBeReplacedByLeaves(world, x, y, z) || block.isReplaceable(world, x, y, z))
		{	
			world.setBlock(x, y, z, this.leaves);
			return true;
		}
		return false;
	}

	private boolean addLog(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, this.log);
		world.setBlockMetadataWithNotify(x, y, z, this.logMeta, 2);

		return true;
	}
}