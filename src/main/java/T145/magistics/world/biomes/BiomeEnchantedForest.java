package T145.magistics.world.biomes;

import java.util.Random;

import T145.magistics.Magistics;
import T145.magistics.world.features.WorldGenGreatwoodTree;
import T145.magistics.world.features.WorldGenSilverwoodTree;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeEnchantedForest extends Biome {

	public BiomeEnchantedForest() {
		super(new BiomeProperties("Enchanted Forest").setWaterColor(6747307).setTemperature(0.7F).setRainfall(0.6F).setBaseHeight(0.2F));
		setRegistryName(Magistics.MODID, getBiomeName());
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 2, 1, 3));
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 2, 1, 3));
		spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWitch.class, 3, 1, 1));
		spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 3, 1, 1));
		decorator.treesPerChunk = 2;
		decorator.flowersPerChunk = 10;
		decorator.grassPerChunk = 12;
		decorator.waterlilyPerChunk = 6;
		decorator.mushroomsPerChunk = 6;
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return rand.nextInt(60) == 3 ? new WorldGenSilverwoodTree(false, 7, 4) : rand.nextInt(25) == 7 ? new WorldGenGreatwoodTree(false) : new WorldGenBigTree(false);
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
	}

	@Override
	public void decorate(World world, Random random, BlockPos pos) {
		super.decorate(world, random, pos);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos) {
		return 5635969;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos) {
		return 6750149;
	}

	@Override
	public int getWaterColorMultiplier() {
		return 30702;
	}

	@Override
	public boolean isMutation() {
		return false;
	}
}