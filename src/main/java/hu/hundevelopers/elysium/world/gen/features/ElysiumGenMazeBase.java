package hu.hundevelopers.elysium.world.gen.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;


public class ElysiumGenMazeBase extends WorldGenerator
{
	private Block wall;

	private int[] sets = new int[8];
	private boolean[] bottom = new boolean[8];
	private boolean[] walls = new boolean[7];
	
	public ElysiumGenMazeBase(Block wall)
	{
		this.wall = wall;
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		
		Block block = Blocks.air;

		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				for(int k = 0; k < 3; k++)
				{
					if(i == 0 || i == 15 || j == 0 || j == 15)
						block = wall;
					else if(i % 3 == 0 && j % 3 == 0)
						block = wall;
					else
						block = Blocks.air;
					
					world.setBlock(x + i, y + k, y + j, block);
				}
			}
		}
		System.out.println("Chunk gen maze");
		
//		for(int row = 0; row < 8; row++)
//		{
//			//data
//			
//			for(int i = 0; i < 8; i++)
//			{		
//				
//				if(row == 0)
//				{
//					sets[i] = i+1;
//				} else {
//					if(bottom[i])
//					{//assign new set
//						int set = 1;
//						while(isConnected(i, set))
//						{
//							set++;
//						}
//						sets[i] = set;
//					}
//				}
//				
//				if(i != 7) walls[i] = false;
//				bottom[i] = false;
//
//			}
//			
//				for (int i = 0; i < 7; i++)
//				{
//					if (isConnected(i,  i+1))
//					{
//						walls[i] = true;
//					}
//					else if (rand.nextInt(3) == 0)
//					{
//						walls[i] = true;
//					}
//					else
//					{
//						unite(i, i+1);
//					}
//				}
//			if(row == 7)
//			{//last row only one set
//				for (int i = 0; i < 7; i++)
//				{
//					if (!isConnected(i,  i+1))
//					{
//						walls[i] = false;
//						unite(i, i+1);
//					}
//				}
//			}
//			
//			for(int i = 0; i < 8; i++)
//			{
//				if((i >= 1 && isConnected(i, i-1)) || row == 7)
//				{
//					bottom[i] = true;
//				}
//			}
//			
//			
//			//build
//			
//			//build end
//			
//		}
		
		return true;
	}

	private boolean isConnected(int a, int b)
	{
		return sets[a] == sets[b];
	}
	
	private void unite(int a, int b)
	{
		for(int i = 0; i < 8; i++)
		{
			if(sets[i] == sets[a])
				sets[i] = sets[b];
		}
	}

}
