package T145.magistics.lib.world;

import java.util.Random;

import T145.magistics.load.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OtherDimWorldGen implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case -1:
			generateNether(world, random, chunkX * 16 + random.nextInt(16), chunkZ * 16 + random.nextInt(16));
		case 1:
			generateEnd(world, random, chunkX * 16 + random.nextInt(16), chunkZ * 16 + random.nextInt(16));
		}
	}

	private void generateEnd(World world, Random random, int x, int z) {
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockEndOre.getStateFromMeta(0), 6)).generate(world, random, pos);
		}
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockEndOre.getStateFromMeta(1), 6)).generate(world, random, pos);
		}
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockEndOre.getStateFromMeta(2), 6)).generate(world, random, pos);
		}
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockEndOre.getStateFromMeta(3), 6)).generate(world, random, pos);
		}
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockEndOre.getStateFromMeta(4), 6)).generate(world, random, pos);
		}
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockEndOre.getStateFromMeta(5), 6)).generate(world, random, pos);
		}

	}

	private void generateNether(World world, Random random, int x, int z) {
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockNetherOre.getStateFromMeta(0), 6)).generate(world, random, pos);
		}
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockNetherOre.getStateFromMeta(1), 6)).generate(world, random, pos);
		}
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockNetherOre.getStateFromMeta(2), 6)).generate(world, random, pos);
		}
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockNetherOre.getStateFromMeta(3), 6)).generate(world, random, pos);
		}
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockNetherOre.getStateFromMeta(4), 6)).generate(world, random, pos);
		}
		for (int k = 0; k < 4; k++) {
			int Xcoord = x + random.nextInt(16);
			int Ycoord = 10 + random.nextInt(128);
			int Zcoord = z + random.nextInt(16);
			BlockPos pos = new BlockPos(Xcoord, Ycoord, Zcoord);

			(new WorldGenMinable(ModBlocks.blockNetherOre.getStateFromMeta(5), 6)).generate(world, random, pos);
		}
	}
}