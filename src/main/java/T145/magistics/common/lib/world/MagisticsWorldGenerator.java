package T145.magistics.common.lib.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.lib.world.biomes.ElysiumBiomeGenPlain;
import T145.magistics.common.lib.world.dim.ElysiumGenLakes;
import T145.magistics.common.lib.world.dim.ElysiumWorldProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class MagisticsWorldGenerator implements IWorldGenerator {
	public int getBottomSolidBlock(World world, int par1, int par2) {
		Chunk chunk = world.getChunkFromBlockCoords(par1, par2);
		for (int i = 2; i < world.getActualHeight() / 2; i++) {
			Block block = world.getBlock(par1, i, par2);
			if (block.getMaterial().blocksMovement() && block.getMaterial() != Material.leaves)
				return i;
		}
		return -1;
	}

	private void generateEnd(World world, Random rand, int chunk_X, int chunk_Z) {
		if (rand.nextInt(5) == 2) {
			int x = chunk_X + rand.nextInt(16) + 8;
			int z = chunk_Z + rand.nextInt(16) + 8;
			int y = world.getTopSolidOrLiquidBlock(x, z);
			new WorldGenMithrilSpikes().generate(world, rand, x, y, z);
		}
		if (rand.nextInt(3) == 0) {
			int x = chunk_X + rand.nextInt(16) + 8;
			int z = chunk_Z + rand.nextInt(16) + 8;
			int y = getBottomSolidBlock(world, x, z);
			if (y != -1)
				new WorldGenEndStalacites().generate(world, rand, x, y - 1, z);
		}
	}

	private void generateElysium(World world, Random random, int blockX, int blockZ) {
		for (int i = 0; i < 8; i++)
			new WorldGenMinable(ConfigObjects.oreSulphure, 16, ConfigObjects.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(128), blockZ + random.nextInt(16));
		for (int i = 0; i < 8; i++)
			new WorldGenMinable(ConfigObjects.oreCobalt, 8, ConfigObjects.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(64), blockZ + random.nextInt(16));
		for (int i = 0; i < 2; i++)
			new WorldGenMinable(ConfigObjects.oreIridium, 8, ConfigObjects.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(32), blockZ + random.nextInt(16));
		for (int i = 0; i < 3; i++)
			new WorldGenMinable(ConfigObjects.oreSilicon, 8, ConfigObjects.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(16), blockZ + random.nextInt(16));
		new WorldGenMinable(ConfigObjects.oreJade, 8, ConfigObjects.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(32), blockZ + random.nextInt(16));
		new WorldGenMinable(ConfigObjects.oreTourmaline, 8, ConfigObjects.blockPalestone).generate(world, random, blockX + random.nextInt(16), random.nextInt(16), blockZ + random.nextInt(16));

		if (world.getBiomeGenForCoords(blockX, blockZ) == ConfigObjects.biomeOcean || world.getBiomeGenForCoords(blockX, blockZ) == ConfigObjects.biomeRiver) {
			int x = blockX + random.nextInt(16);
			int z = blockZ + random.nextInt(16);

			int y;
			for (y = world.getTopSolidOrLiquidBlock(x, z); world.getBlock(x, y, z) == ConfigObjects.blockElysiumWater; y--);

			if (world.getBlock(x, y + 1, z) == ConfigObjects.blockElysiumWater) {
				world.setBlock(x, y, z, ConfigObjects.oreBeryl);
			}
		}

		if (world.getBiomeGenForCoords(blockX, blockZ) instanceof ElysiumBiomeGenPlain && random.nextInt(3) == 0) {
			int x = blockX + random.nextInt(16);
			int z = blockX + random.nextInt(16);
			int y = world.getTopSolidOrLiquidBlock(x, z);

			new ElysiumGenLakes(ConfigObjects.blockElysiumEnergyLiquid).generate(world, random, x, y, z);
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}

		if (world.provider instanceof ElysiumWorldProvider)
			generateElysium(world, random, chunkX * 16, chunkZ * 16);
	}
}