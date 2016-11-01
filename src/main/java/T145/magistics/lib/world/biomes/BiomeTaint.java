package T145.magistics.lib.world.biomes;

import java.util.Random;

import T145.magistics.Magistics;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeTaint extends Biome {

	public BiomeTaint() {
		super(new BiomeProperties("Tainted Land").setWaterColor(7160201));
		setRegistryName(Magistics.MODID, getBiomeName());
		theBiomeDecorator.treesPerChunk = 2;
		theBiomeDecorator.flowersPerChunk = 64537;
		theBiomeDecorator.grassPerChunk = 2;
		theBiomeDecorator.reedsPerChunk = 64537;
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableWaterCreatureList.clear();
	}

	@Override
	public void decorate(World world, Random random, BlockPos pos) {}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos) {
		return 7160201;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos) {
		return 8154503;
	}

	@Override
	public int getSkyColorByTemp(float temp) {
		return 8144127;
	}

	@Override
	public int getWaterColorMultiplier() {
		return 13373832;
	}

	@Override
	public boolean isMutation() {
		return false;
	}
}