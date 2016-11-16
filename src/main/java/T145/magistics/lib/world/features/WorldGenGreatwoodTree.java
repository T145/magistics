package T145.magistics.lib.world.features;

import java.util.Random;

import T145.magistics.load.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenGreatwoodTree extends WorldGenLargeTree {

	public WorldGenGreatwoodTree(boolean notify) {
		super(notify, ModBlocks.blockLogs.getStateFromMeta(4), ModBlocks.blockLeaves.getDefaultState(), 19, 24);
	}

	@Override
	public boolean generate(World world, Random random, BlockPos pos) {
		heightAttenuation = 0.3D;
		scaleWidth = 1.0D;
		trunkSize = 2;
		leafDistanceLimit = 4;
		return super.generate(world, random, pos);
	}
}