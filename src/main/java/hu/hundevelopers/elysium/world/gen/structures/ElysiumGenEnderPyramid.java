package hu.hundevelopers.elysium.world.gen.structures;

import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenDesert;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenEnderPyramid extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		
		int height = 8 + rand.nextInt(4);
		
		for(int i = -height; i <= height; i++)
		{
			for(int k = height; k <= height; k++)
			{
				if(!(world.getBiomeGenForCoords(x+i, z+k) instanceof ElysiumBiomeGenDesert))
					return false;
			}
		}
		
		for(int j = 0; j < height; j++)
		{
			for(int i = -(height - j); i <= (height - j); i++)
			{
				for(int k = -(height - j); k <= (height - j); k++)
				{
					if(k == -(height - j) || k == (height - j) || i == -(height - j) || i == (height - j) || j == 0)
						world.setBlock(x+i, y+j, z+k, Blocks.end_stone);
				}
			}
		}
		
		world.setBlock(x, y+height, z, Blocks.end_stone);
		world.setBlock(x, y+1, z, Blocks.diamond_block);
		world.setBlock(x, y+2, z, Blocks.mob_spawner);
        
        TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(x, y+2, z);

        if (tileentitymobspawner != null)
        {
            tileentitymobspawner.func_145881_a().setEntityName("Enderman");
        }
        
		return true;
	}

}
