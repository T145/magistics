package T145.magistics.lib.world;

import java.util.Random;

import T145.magistics.Magistics;
import T145.magistics.api.objects.ModBlocks;
import T145.magistics.config.Config;
import T145.magistics.lib.aura.AuraHandler;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WorldGenerator implements IWorldGenerator {

	public static void init() {
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 1);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		generateWorld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		AuraHandler.generateAura(world.getChunkFromChunkCoords(chunkX, chunkZ), random);
	}

	private void generateWorld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int dimension = world.provider.getDimension();

		if (Config.isDimensionWhitelisted(dimension)) {
			switch (dimension) {
			case -1:
				generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
				break;
			case 1:
				generateEnd(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
				break;
			default:
				generateSurface(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
				break;
			}
		}
	}

	private void generateSurface(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		for (int rarity = 0; rarity < 8; ++rarity) {
			int randX = chunkX * 16 + random.nextInt(16);
			int randZ = chunkZ * 16 + random.nextInt(16);
			int randY = random.nextInt(Math.max(5, world.getHeightmapHeight(randX, randZ) - 5));
			BlockPos pos = new BlockPos(randX, randY, randZ);
			int meta = random.nextInt(6);

			if (random.nextInt(3) == 0) {
				Biome biome = world.getBiomeForCoordsBody(pos);

				// decorate biome
			}

			try {
				new WorldGenMinable(ModBlocks.infusedOre.getStateFromMeta(meta), 6, BlockMatcher.forBlock(Blocks.STONE)).generate(world, random, pos);
			} catch (Exception err) {
				Magistics.logger.catching(err);
			}
		}
	}

	private void generateNether(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		for (int rarity = 0; rarity < 4; ++rarity) {
			int randX = chunkX * 16 + random.nextInt(16);
			int randZ = chunkZ * 16 + random.nextInt(16);
			int randY = random.nextInt(Math.max(5, world.getHeightmapHeight(randX, randZ) - 5));
			BlockPos pos = new BlockPos(randX, randY, randZ);
			int meta = random.nextInt(6);

			try {
				new WorldGenMinable(ModBlocks.infusedOreNether.getStateFromMeta(meta), 6, BlockMatcher.forBlock(Blocks.NETHERRACK)).generate(world, random, pos);
			} catch (Exception err) {
				Magistics.logger.catching(err);
			}
		}
	}

	private void generateEnd(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		for (int rarity = 0; rarity < 4; ++rarity) {
			int randX = chunkX * 16 + random.nextInt(16);
			int randZ = chunkZ * 16 + random.nextInt(16);
			int randY = random.nextInt(108) + 10;
			BlockPos pos = new BlockPos(randX, randY, randZ);
			int meta = random.nextInt(6);

			try {
				new WorldGenMinable(ModBlocks.infusedOreEnd.getStateFromMeta(meta), 6, BlockMatcher.forBlock(Blocks.END_STONE)).generate(world, random, pos);
			} catch (Exception err) {
				Magistics.logger.catching(err);
			}
		}
	}
}