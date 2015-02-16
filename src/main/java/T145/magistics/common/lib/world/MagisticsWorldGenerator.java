package T145.magistics.common.lib.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class MagisticsWorldGenerator implements IWorldGenerator {
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
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

	public int getBottomSolidBlock(World world, int par1, int par2) {
		Chunk chunk = world.getChunkFromBlockCoords(par1, par2);
		for (int i = 2; i < world.getActualHeight() / 2; i++) {
			Block block = world.getBlock(par1, i, par2);
			if (block.getMaterial().blocksMovement() && block.getMaterial() != Material.leaves)
				return i;
		}
		return -1;
	}
}