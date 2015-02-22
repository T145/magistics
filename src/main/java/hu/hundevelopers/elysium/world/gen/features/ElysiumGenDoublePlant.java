package hu.hundevelopers.elysium.world.gen.features;

import hu.hundevelopers.elysium.Elysium;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumGenDoublePlant extends WorldGenerator
{
	Block block;
	private int bottom;
	private int top;
	
	public ElysiumGenDoublePlant(Block block, int metaBottom, int metaTop)
	{
		this.block = block;
		this.bottom = metaBottom;
		this.top = metaTop;
	}

	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        Block block;

        do
        {
            block = par1World.getBlock(par3, par4, par5);
            if (!(block.isLeaves(par1World, par3, par4, par5) || block.isAir(par1World, par3, par4, par5)))
            {
                break;
            }
            --par4;
        } while (par4 > 0);

        for (int l = 0; l < 10; ++l)
        {
            int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int j1 = par4 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(i1, j1, k1) && par1World.getBlock(i1, j1-1, k1) == Elysium.blockGrass)
            {
                par1World.setBlock(i1, j1, k1, this.block, this.bottom, 2);
                par1World.setBlock(i1, j1+1, k1, this.block, this.top, 2);
            }
        }

        return true;
    }
}
