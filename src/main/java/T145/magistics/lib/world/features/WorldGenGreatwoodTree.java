package T145.magistics.lib.world.features;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenGreatwoodTree extends WorldGenAbstractTree {

	public WorldGenGreatwoodTree(boolean notify) {
		super(notify);
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		return false;
	}
}