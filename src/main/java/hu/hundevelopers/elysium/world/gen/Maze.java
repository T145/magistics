package hu.hundevelopers.elysium.world.gen;

import java.util.Random;

public class Maze
{
	private int[] sets;
	public boolean[] walls;
	public boolean[] bottom;
	
	private Random rand;
	
	public Maze(Random rand)
	{
		this.rand = rand;
		sets = new int[25];
		walls = new boolean[20];
		bottom = new boolean[20];
		
		for(int i = 0; i < 25; i++)
		{
			sets[i] = i+1;
			if(i < 20)
			{
				walls[i] = bottom[i] = true;
			}
		}
	}
	
	//Eller's algorithm
	public void generate()
	{
		for(int y = 0; y < 5; y++)
		{
			//wall
			for(int x = 0; x < 4; x++)
			{
				if(!connected(y * 5 + x, y * 5 + x + 1) && this.rand.nextInt(2) == 0)
				{
					walls[y * 4 + x] = false;
					union(y * 5 + x, y * 5 + x + 1);
				}
			}
			
			//bottom
			for(int x = 0; x < 5; x++)
			{
				if(y < 4)
				{
					if(getCellNumWithoutBottom(y, sets[y*5+x]) < 1 || this.rand.nextInt(2) == 0)
					{
						bottom[y*5+x] = false;
					}
				}
			}
			
//			for(int x = 0; x < 5; x++)
//			{
//				if(y < 4)
//				{
//					if(getCellNumWithoutBottom(y, sets[y*5+x]) < 1 || this.rand.nextInt(2) == 0)
//					{
//						bottom[y*5+x] = false;
//					}
//				}
//			}

			//new line
			for(int x = 0; x < 5; x++)
			{
				if(y < 4)
				{
					if(!bottom[y*5+x])
					{
						sets[(y+1) * 5 + x] = sets[y * 5 + x];
					}
				}
			}
		}
		
		//finish
		for(int x = 0; x < 4; x++)
		{
			if(!connected(20+x, 21+x))
			{
				walls[16 + x] = false;
				union(20+x, 21+x);
			}
		}
		
	}

	private int getCellNumWithoutBottom(int y, int setId) {
		int num = 0;
		
		for(int i = 0; i < 5; i++)
		{
			if(sets[y*5 + i] == setId && !bottom[y*5+i])
				return 1;//faster, instead of num++
		}
		return num;
	}

	private boolean connected(int a, int b)
	{
		return sets[a] == sets[b];
	}
	
	private void union(int a, int b)
	{
		int aValue = sets[a];
		int bValue = sets[b];
		
		for(int i = 0; i < sets.length; i++)
		{
			if(sets[i] == bValue) sets[i] = aValue;
		}
	}
	
}
