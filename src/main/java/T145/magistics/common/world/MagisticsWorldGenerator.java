package T145.magistics.common.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ModBlocks;
import T145.magistics.common.world.feature.WorldGenCustomSpikes;
import T145.magistics.common.world.feature.WorldGenEndStalacites;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;

public class MagisticsWorldGenerator implements IWorldGenerator {
	@Override
	public void generate(Random random, int x, int z, World world, IChunkProvider generator, IChunkProvider provider) {
		switch (world.provider.dimensionId) {
		case 1:
			generateEnd(world, random, x * 16, z * 16);
			break;
		}
	}

	public int getBottomSolidBlock(World world, int x, int z) {
		Chunk chunk = world.getChunkFromBlockCoords(x, z);
		for (int i = 2; i < world.getActualHeight() / 2; ++i) {
			Block block = world.getBlock(x, i, z);
			if (block.getMaterial().blocksMovement() && block.getMaterial() != Material.leaves)
				return i;
		}
		return -1;
	}

	public void generateEnd(World world, Random rand, int chunkX, int chunkZ) {
		if (!Loader.isModLoaded("HEE")) {
			if (rand.nextInt(5) == 2) {
				int x = chunkX + rand.nextInt(16) + 8, z = chunkZ + rand.nextInt(16) + 8, y = world.getTopSolidOrLiquidBlock(x, z);
				new WorldGenCustomSpikes(new ItemStack(ModBlocks.blockEridium, 1, 0)).generate(world, rand, x, y, z);
			}
		} else // TODO: Generate custom versions of HEE's spikes
			Magistics.proxy.supportedMods.add("HEE");
		if (rand.nextInt(3) == 0) {
			int x = chunkX + rand.nextInt(16) + 8, z = chunkZ + rand.nextInt(16) + 8, y = getBottomSolidBlock(world, x, z);
			if (y != -1)
				new WorldGenEndStalacites().generate(world, rand, x, y - 1, z);
		}
	}
}