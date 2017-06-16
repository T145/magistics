package T145.magistics.world.generators;

import java.util.Random;

import T145.magistics.world.aura.AuraManager;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorAura implements IWorldGenerator {

	public static final WorldGeneratorAura INSTANCE = new WorldGeneratorAura();

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		AuraManager.generateAura(chunkProvider.provideChunk(chunkX, chunkZ), rand);
	}
}