package T145.magistics.lib.world.biomes;

import java.util.Random;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeEnchantedForest extends Biome {

	private final static int COLOR = 6728396;

	public BiomeEnchantedForest() {
		super(new BiomeProperties("Enchanted Forest").setWaterColor(COLOR).setTemperature(0.7F).setRainfall(0.6F).setBaseHeight(0.2F));
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 2, 1, 3));
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 2, 1, 3));
		spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWitch.class, 3, 1, 1));
		spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 3, 1, 1));
		theBiomeDecorator.treesPerChunk = 2;
		theBiomeDecorator.flowersPerChunk = 10;
		theBiomeDecorator.grassPerChunk = 12;
		theBiomeDecorator.waterlilyPerChunk = 6;
		theBiomeDecorator.mushroomsPerChunk = 6;
	}

	@Override
	public void decorate(World world, Random random, BlockPos pos) {}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos) {
		return COLOR;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos) {
		return 7851246;
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