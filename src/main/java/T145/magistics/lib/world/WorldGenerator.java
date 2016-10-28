package T145.magistics.lib.world;

import java.util.Random;

import T145.magistics.Magistics;
import T145.magistics.config.ConfigHandler;
import T145.magistics.load.ModBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WorldGenerator implements IWorldGenerator {

	public void load() {
		GameRegistry.registerWorldGenerator(this, 1);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		generateWorld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider, true);
	}

	private void generateWorld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, boolean newGen) {
		int dimension = world.provider.getDimension();

		if (!ConfigHandler.isDimensionBlacklisted(dimension)) {
			switch (dimension) {
			case -1:
				generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider, newGen);
				break;
			case 1:
				generateEnd(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider, newGen);
				break;
			default:
				generateSurface(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider, newGen);
				break;
			}
		}
	}

	private void generateSurface(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, boolean newGen) {
		if (newGen) {
			Magistics.logger.info("Beginning Overworld generation...");

			BlockPos pos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

			for (int rarity = 0; rarity < 8; ++rarity) {
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
					new WorldGenMinable(ModBlocks.blockOre.getStateFromMeta(meta), 6, BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos.add(random.nextInt(16), random.nextInt(108) + 10, random.nextInt(16)));
					Magistics.logger.info("Generating InfusedOre:Meta{" + meta + "} at: " + pos);
				} catch (Exception err) {
					Magistics.logger.catching(err);
				}
			}
		}
	}

	private void generateNether(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, boolean newGen) {
		if (newGen && chunkGenerator instanceof ChunkProviderHell) {
			Magistics.logger.info("Beginning Nether generation...");

			BlockPos pos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

			for (int rarity = 0; rarity < 4; ++rarity) {
				int meta = random.nextInt(6);

				try {
					new WorldGenMinable(ModBlocks.blockNetherOre.getStateFromMeta(meta), 6, BlockMatcher.forBlock(Blocks.NETHERRACK)).generate(world, random, pos.add(random.nextInt(16), random.nextInt(108) + 10, random.nextInt(16)));
					Magistics.logger.info("Generating NetherInfusedOre:Meta{" + meta + "} at: " + pos);
				} catch (Exception err) {
					Magistics.logger.catching(err);
				}
			}
		}
	}

	private void generateEnd(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, boolean newGen) {
		if (newGen && chunkGenerator instanceof ChunkProviderEnd) {
			Magistics.logger.info("Beginning End generation...");

			BlockPos pos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

			for (int rarity = 0; rarity < 4; ++rarity) {
				int meta = random.nextInt(6);

				try {
					new WorldGenMinable(ModBlocks.blockEndOre.getStateFromMeta(meta), 6, BlockMatcher.forBlock(Blocks.END_STONE)).generate(world, random, pos.add(random.nextInt(16), random.nextInt(108) + 10, random.nextInt(16)));
					Magistics.logger.info("Generating EndInfusedOre:Meta{" + meta + "} at: " + pos);
				} catch (Exception err) {
					Magistics.logger.catching(err);
				}
			}
		}
	}
}