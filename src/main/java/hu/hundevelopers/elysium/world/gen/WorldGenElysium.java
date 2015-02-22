package hu.hundevelopers.elysium.world.gen;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.world.ElysiumWorldProvider;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenPlain;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenLakes;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenMazeBase;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenElysium implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider instanceof ElysiumWorldProvider)
		{
			generateElysium(world, rand, chunkX * 16, chunkZ * 16);
		}
//		if(world.provider.dimensionId == 0)
//		{
//			generateOverworld(world, rand, chunkX * 16, chunkZ * 16);
//		}
	}

	private void generateOverworld(World world, Random random, int blockX, int blockZ)
	{
		
	}

	private void generateElysium(World world, Random random, int blockX, int blockZ)
	{
		
		for(int i = 0; i < 8; i++)
		{
			new WorldGenMinable(Elysium.oreSulphure, 16, Elysium.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(128), blockZ + random.nextInt(16));
		}

		for(int i = 0; i < 8; i++)
		{
			new WorldGenMinable(Elysium.oreCobalt, 8, Elysium.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(64), blockZ + random.nextInt(16));
		}

		for(int i = 0; i < 2; i++)
		{
			new WorldGenMinable(Elysium.oreIridium, 8, Elysium.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(32), blockZ + random.nextInt(16));
		}

		for(int i = 0; i < 3; i++)
		{
			new WorldGenMinable(Elysium.oreSilicon, 8, Elysium.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(16), blockZ + random.nextInt(16));
		}

		new WorldGenMinable(Elysium.oreJade, 8, Elysium.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(32), blockZ + random.nextInt(16));

		new WorldGenMinable(Elysium.oreTourmaline, 8, Elysium.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(16), blockZ + random.nextInt(16));

		if(world.getBiomeGenForCoords(blockX, blockZ) == Elysium.biomeOcean || world.getBiomeGenForCoords(blockX, blockZ) == Elysium.biomeRiver)
		{
			int x = blockX + random.nextInt(16);
			int z = blockZ + random.nextInt(16);

			int y;
			for(y = world.getTopSolidOrLiquidBlock(x, z); world.getBlock(x, y, z) == Elysium.blockElysiumWater; y--);

			if(world.getBlock(x, y+1, z) == Elysium.blockElysiumWater)
			{
				world.setBlock(x, y, z, Elysium.oreBeryl);
			}
		}

		if(world.getBiomeGenForCoords(blockX, blockZ) instanceof ElysiumBiomeGenPlain && random.nextInt(3) == 0)
		{
			int x = blockX + random.nextInt(16);
			int z = blockX + random.nextInt(16);
			int y = world.getTopSolidOrLiquidBlock(x, z);
	
			
			new ElysiumGenLakes(Elysium.blockElysiumEnergyLiquid).generate(world, random, x, y, z);
		}

//		for(int j = 0; j < 256; j++) FIXME TODO
//		{
//			int meta = TemperatureManager.getBlockMetadataFromTemperature(Elysium.blockPalestone.blockID, TemperatureManager.getTemperatureForHeight(j));
//			for(int i = 0; i < 16; i++)
//				for(int k = 0; k < 16; k++)
//					if(world.getBlockId(blockX+i, j, blockZ+k) == Elysium.blockPalestone.blockID)
//						world.setBlockMetadataWithNotify(blockX+i, j, blockZ+k, meta, 0);
//		}

	}

}
