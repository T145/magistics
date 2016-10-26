package T145.magistics.lib.world;

import java.util.Random;

import T145.magistics.Magistics;
import T145.magistics.load.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WorldGenerator implements IWorldGenerator {

	public void load() {
		GameRegistry.registerWorldGenerator(this, 1);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		generateWorld(random, chunkX, chunkZ, world, true);
	}

	private void generateWorld(Random random, int chunkX, int chunkZ, World world, boolean newGen) {
		switch (world.provider.getDimension()) {
		case -1:
			generateNether(world, random, chunkX, chunkZ, newGen);
			break;
		case 1:
			generateEnd(world, random, chunkX, chunkZ, newGen);
			break;
		default:
			generateSurface(world, random, chunkX, chunkZ, newGen);
			break;
		}
	}

	private void generateSurface(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
		if (newGen) {
			for (int i = 0; i < 8; i++) {
				int randPosX = chunkX * 16 + random.nextInt(16);
				int randPosZ = chunkZ * 16 + random.nextInt(16);
				int randPosY = random.nextInt(Math.max(5, world.getHeightmapHeight(randPosX, randPosZ) - 5));
				BlockPos pos = new BlockPos(randPosX, randPosY, randPosZ);
				int meta = random.nextInt(6);

				if (random.nextInt(3) == 0) {
					Biome dest = world.getBiomeForCoordsBody(pos);

					switch (dest.getTempCategory()) {
					case OCEAN:
						meta = 2;
						break;
					case COLD:
						meta = 3;
						break;
					case MEDIUM:
						meta = 0;
						break;
					case WARM:
						meta = 1;
						break;
					default:
						break;
					}
				}

				try {
					new WorldGenMinable(ModBlocks.blockOre.getStateFromMeta(meta), 6).generate(world, random, pos);
					Magistics.logger.info("Generating InfusedOre:Meta{" + meta + "} at: " + pos);
				} catch (Exception err) {
					Magistics.logger.catching(err);
				}
			}
		}
	}

	private void generateNether(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
	}

	private void generateEnd(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
	}
}