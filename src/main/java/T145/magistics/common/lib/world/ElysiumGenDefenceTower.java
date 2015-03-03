package T145.magistics.common.lib.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.pillars.TilePillarSentry;

public class ElysiumGenDefenceTower extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		
		int height = 6;
		
		int i = 0;
		do{
			world.setBlock(x, y-i, z, Blocks.quartz_block);
			world.setBlock(x, y-i, z + 1, Blocks.quartz_block);
			world.setBlock(x, y-i, z - 1, Blocks.quartz_block);
			world.setBlock(x + 1, y-i, z, Blocks.quartz_block);
			world.setBlock(x - 1, y-i, z, Blocks.quartz_block);
			i++;
		}while(world.getBlock(x, y-i, z) == null || world.getBlock(x, y-i, z).getMaterial().isLiquid());
			
		
		for(int j = 1; j <= height; j++)
		{
			world.setBlock(x, y+j, z, Blocks.quartz_block, 2, 1);

			if(j == 1)
			{
				world.setBlock(x, y+j, z + 1, Blocks.quartz_stairs, 3, 1);
				world.setBlock(x, y+j, z - 1, Blocks.quartz_stairs, 2, 1);
				world.setBlock(x + 1, y+j, z, Blocks.quartz_stairs, 1, 1);
				world.setBlock(x - 1, y+j, z, Blocks.quartz_stairs, 0, 1);
			}
			if(j == height)
			{
				world.setBlock(x, y+j, z + 1, Blocks.quartz_stairs, 7, 1);
				world.setBlock(x, y+j, z - 1, Blocks.quartz_stairs, 6, 1);
				world.setBlock(x + 1, y+j, z, Blocks.quartz_stairs, 5, 1);
				world.setBlock(x - 1, y+j, z, Blocks.quartz_stairs, 4, 1);
			}
			
		}
		
		world.setBlock(x, y+height+1, z, ConfigObjects.blockSentryPillar);

		TilePillarSentry tile = new TilePillarSentry();
        tile.setAmmo(getRandomWeapon(rand));

        world.setTileEntity(x, y+height+1, z, tile);
		
		return true;
	}

	private static final ItemStack[] weapons = {
		new ItemStack(Items.arrow, 64),
		new ItemStack(Items.snowball, 64),
		new ItemStack(Items.fire_charge, 64),
		new ItemStack(ConfigObjects.itemStaff, 1, 0),
		new ItemStack(ConfigObjects.itemStaff, 1, 1),
		new ItemStack(ConfigObjects.itemStaff, 1, 2),
		new ItemStack(ConfigObjects.itemStaff, 1, 3)
	};
	
	private static ItemStack getRandomWeapon(Random rand)
	{
		return weapons[rand.nextInt(weapons.length)];
	}
}
