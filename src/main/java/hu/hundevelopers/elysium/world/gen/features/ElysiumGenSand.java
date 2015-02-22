package hu.hundevelopers.elysium.world.gen.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import T145.magistics.common.config.ConfigObjects;

public class ElysiumGenSand extends WorldGenerator
{
    Block sand;
    int radius;

    public ElysiumGenSand(Block sand, int radius)
    {
        this.sand = sand;
        this.radius = radius;
    }
    
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
    	y = world.getTopSolidOrLiquidBlock(x, z);
    	
        if (world.getBlock(x, y, z).getMaterial() != Material.water)
        {
            return false;
        }
        else
        {
            int randradius = random.nextInt(this.radius - 2) + 2;
            byte b0 = 2;
            
            boolean placedSand = false;
            
            for (int cx = x - randradius; cx <= x + randradius; ++cx)
            {
                for (int cz = z - randradius; cz <= z + randradius; ++cz)
                {
                    int dx = cx - x;
                    int dz = cz - z;

                    if (dx * dx + dz * dz <= randradius * randradius)
                    {
                        for (int cy = y - b0; cy <= y + b0; ++cy)
                        {
                            Block block = world.getBlock(cx, cy, cz);

                            if (block == ConfigObjects.blockDirt || block == ConfigObjects.blockGrass)
                            {
                                world.setBlock(cx, cy, cz, this.sand, 0, 2);
                                placedSand = true;
                            }
                            
                            if((block == ConfigObjects.blockGrass) && (this.sand == ConfigObjects.blockSand) && (world.getBlock(cx, cy+1, cz) == Blocks.air) &&
                            		(world.getBlock(cx+1, cy+1, cz) == Blocks.air) && (world.getBlock(cx-1, cy+1, cz) == Blocks.air) && (world.getBlock(cx, cy+1, cz+1) == Blocks.air) && 
                            		(world.getBlock(cx, cy+1, cz-1) == Blocks.air) && (random.nextInt(8) == 0))
                            {
                            	if(random.nextInt(2) == 0)
                            	{
                            		world.setBlock(cx, cy+1, cz, ConfigObjects.blockFloatingConch);
                            	}
                            	else
                            	{
                            		world.setBlock(cx, cy+1, cz, ConfigObjects.blockFloatingShell);
                            	}
                            }
                        }
                    }
                }
            }
            if(placedSand)
            {
            	//System.out.println("Generated liquid side at: "+x+" "+y+" "+z);
            }
            return true;
        }
    }
}
