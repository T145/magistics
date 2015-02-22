package hu.hundevelopers.elysium.world.gen.features;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.block.ElysiumBlock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import T145.magistics.common.config.ConfigObjects;

public class ElysiumGenLakes extends WorldGenerator
{
	Block liquid;
	Block lake[] = new Block[2048];

	public ElysiumGenLakes(Block liquid)
	{
		this.liquid = liquid;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		if(random.nextInt((liquid.getMaterial() == Material.water) ? 2 : 16) == 0)
		{
			y = world.getTopSolidOrLiquidBlock(x, z);
		}

		if(y <= 4)
		{
			return false;
		}

		boolean isSurfaceLake = world.canBlockSeeTheSky(x, y, z);

		y -= 4;
		for(int j = 0; j < 8; j++)
		{
			if(world.getBlock(x, y+j, z).getMaterial().isLiquid())
				return false;
		}
		x -= 8;
		z -= 8;

		int l = random.nextInt(4) + 4;

		int cx;
		int cy;
		int cz;

		int i;
		for(i = 0; i < 2048; i++) lake[i] = Blocks.anvil;

		for (i = 0; i < l; i++)
		{
			double d0 = random.nextDouble() * 6.0D + 3.0D;
			double d1 = random.nextDouble() * 4.0D + 2.0D;
			double d2 = random.nextDouble() * 6.0D + 3.0D;
			double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
			double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
			double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

			for (cx = 1; cx < 15; cx++)
			{
				for (cz = 1; cz < 15; cz++)
				{
					for (cy = 1; cy < 7; cy++)
					{
						double d6 = ((double)cx - d3) / (d0 / 2.0D);
						double d7 = ((double)cy - d4) / (d1 / 2.0D);
						double d8 = ((double)cz - d5) / (d2 / 2.0D);
						double d9 = d6 * d6 + d7 * d7 + d8 * d8;

						if (d9 < 1.0D)
						{
							lake[(cx * 16 + cz) * 8 + cy] = liquid;
						}
					}
				}
			}
		}

		boolean flag;

		for (cx = 0; cx < 16; cx++)
		{
			for (cz = 0; cz < 16; cz++)
			{
				for (cy = 0; cy < 8; cy++)
				{
					flag = (lake[(cx * 16 + cz) * 8 + cy] == Blocks.anvil) && (cx < 15 && (lake[((cx + 1) * 16 + cz) * 8 + cy] == liquid) || cx > 0 && (lake[((cx - 1) * 16 + cz) * 8 + cy] == liquid) || cz < 15 && (lake[(cx * 16 + cz + 1) * 8 + cy] == liquid) || cz > 0 && (lake[(cx * 16 + (cz - 1)) * 8 + cy] == liquid) || cy < 7 && (lake[(cx * 16 + cz) * 8 + cy + 1] == liquid) || cy > 0 && (lake[(cx * 16 + cz) * 8 + (cy - 1)] == liquid));

					if (flag)
					{
						Block tempBlock = world.getBlock(x + cx, y + cy, z + cz);
						
						if(tempBlock != null) //FIXME crashes
						{
							if (cy >= 4 && tempBlock.getMaterial().isLiquid())
							{
								return false;
							}
	
							if (cy < 4 && !tempBlock.getMaterial().isSolid() && world.getBlock(x + cx, y + cy, z + cz) != this.liquid)
							{
								return false;
							}
						} else {
							System.out.print("[Elysium] block is null at " + (x + cx) + " : " + (z + cz) + " during lake gen!");
						}
					}
				}
			}
		}

		for (cx = 0; cx < 16; cx++)
		{
			for (cz = 0; cz < 16; cz++)
			{
				for (cy = 0; cy < 8; cy++)
				{
					if (lake[(cx * 16 + cz) * 8 + cy] == liquid)
					{
						if(cy >= 4)
						{
							lake[(cx * 16 + cz) * 8 + cy] = Blocks.air;
						}
					}
				}
			}
		}

		if(this.liquid.getMaterial() == Material.water)
		{
			for (cx = 0; cx < 16; cx++)
			{
				for (cz = 0; cz < 16; cz++)
				{
					for (cy = 0; cy < 8; cy++)
					{
						if(isSurfaceLake && canPlaceLily(cx, cy, cz) && (random.nextInt(16) == 0))
						{
							lake[(cx * 16 + cz) * 8 + cy] = Blocks.waterlily;
						}
					}
				}
			}
		}

		for (cx = 0; cx < 16; cx++)
		{
			for (cz = 0; cz < 16; cz++)
			{
				for (cy = 0; cy < 8; cy++)
				{
					if((lake[(cx * 16 + cz) * 8 + cy] != Blocks.anvil) && (world.getBlock(x+cx, y+cy, z+cz) != Blocks.quartz_ore) && (world.getBlock(x+cx, y+cy, z+cz) != Blocks.gold_block))
					{
						if(world.getBlock(x+cx, y+cy, z+cz) instanceof ElysiumBlock)
						{
							if(((ElysiumBlock)world.getBlock(x+cx, y+cy, z+cz)).canBeReplacedByLake())
							{
								try{
									world.setBlock(x+cx, 
											y+cy, 
											z+cz, 
											lake[(cx * 16 + cz) * 8 + cy]);//FIXME: random crash
								} catch(Exception e){ 
									System.out.println(e.getStackTrace());
								}
							}
						}
						else
						{
							world.setBlock(x+cx, y+cy, z+cz, lake[(cx * 16 + cz) * 8 + cy]);
						}
					}
				}
			}
		}

		if(isSurfaceLake && (this.liquid.getMaterial() == Material.water))
		{
			if(random.nextInt(2) == 0)
			{
				new ElysiumGenSand(Elysium.blockSand, 7).generate(world, random, x+8, y+4, z+8);
			}
			if(random.nextInt(2) == 0)
			{
				new ElysiumGenSand(Elysium.blockRilt, 3).generate(world, random, x+8, y+4, z+8);
			}
			if(random.nextInt(3) == 0)
			{
				int c = random.nextInt(5);
				for(i = 0; i < c; i++)
				{
					new ElysiumGenLakePillar(ConfigObjects.blockBasePillar).generate(world, random, x+random.nextInt(16)-8, y+4, z+random.nextInt(16)-8);
				}
			}
			//System.out.println("Generated lake on surface at: " + (x+8) + " " + (y+4) + " " + (z+8));
		}
		return true;
	}

	boolean canPlaceLily(int cx, int cy, int cz)
	{
		if((cy == 0) || (lake[(cx*16 + cz)*8 + cy-1] != liquid) || (cx == 15) || (lake[((cx+1)*16 + cz)*8 + cy-1] != liquid) || (cx == 0) || (lake[((cx-1)*16 + cz)*8 + cy-1] != liquid) || (cz == 15) || (lake[(cx*16 + cz+1)*8 + cy-1] != liquid) || (cz == 0) || (lake[(cx*16 + cz-1)*8 + cy-1] != liquid))
		{
			return false;
		}

		if((lake[(cx*16 + cz)*8 + cy] != Blocks.air) && (lake[(cx*16 + cz)*8 + cy] != Blocks.anvil))
		{
			return false;
		}

		if((cx == 15) || ((lake[((cx+1)*16 + cz)*8 + cy] != Blocks.air) && (lake[((cx+1)*16 + cz)*8 + cy] != Blocks.anvil)))
		{
			return false;
		}

		if((cx == 0) || ((lake[((cx-1)*16 + cz)*8 + cy] != Blocks.air) && (lake[((cx-1)*16 + cz)*8 + cy] != Blocks.anvil)))
		{
			return false;
		}

		if((cz == 15) || ((lake[(cx*16 + cz+1)*8 + cy] != Blocks.air) && (lake[(cx*16 + cz+1)*8 + cy] != Blocks.anvil)))
		{
			return false;
		}

		if((cz == 0) || ((lake[(cx*16 + cz-1)*8 + cy] != Blocks.air) && (lake[(cx*16 + cz-1)*8 + cy] != Blocks.anvil)))
		{
			return false;
		}

		return true;
	}
}