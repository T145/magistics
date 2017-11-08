package T145.magistics.world.features;

import java.util.Random;

import T145.magistics.core.ModInit;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

public class WorldGenSilverwoodTree extends WorldGenAbstractTree {

	private final int minTreeHeight;
	private final int randomTreeHeight;
	private boolean generateFlowers;

	public WorldGenSilverwoodTree(boolean notify, int minTreeHeight, int randomTreeHeight) {
		super(notify);
		generateFlowers = !notify;
		this.minTreeHeight = minTreeHeight;
		this.randomTreeHeight = randomTreeHeight;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int height = rand.nextInt(randomTreeHeight) + minTreeHeight;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		boolean canFitTree = true;

		if (y >= 1 && y + height + 1 <= 256) {
			for (int i = y; i <= y + 1 + height; ++i) {
				byte spread = 1;

				if (i == y) {
					spread = 0;
				}

				if (i >= y + 1 + height - 2) {
					spread = 3;
				}

				for (int j = x - spread; j <= x + spread && canFitTree; ++j) {
					for (int k = z - spread; k <= z + spread && canFitTree; ++k) {
						if (i >= 0 && i < 256) {
							BlockPos dest = new BlockPos(i, j, k);
							IBlockState state = world.getBlockState(dest);
							Block block = state.getBlock();

							if (i > y && !block.isAir(state, world, dest) && !block.isLeaves(state, world, dest) && !block.isReplaceable(world, dest)) {
								canFitTree = false;
							}
						} else {
							canFitTree = false;
						}
					}
				}
			}

			if (!canFitTree) {
				return false;
			}

			BlockPos dirtPos = new BlockPos(x, y - 1, z);
			IBlockState dirtState = world.getBlockState(dirtPos);
			Block dirt = dirtState.getBlock();
			boolean canGrow = dirt.canSustainPlant(dirtState, world, dirtPos, EnumFacing.UP, (IPlantable) Blocks.SAPLING) && y < 256 - height - 1;

			if (canGrow) {
				dirt.onPlantGrow(dirtState, world, dirtPos, pos);

				int j;

				for (j = y + height - 5; j <= y + height + 3 + rand.nextInt(3); j++) {
					int yy = MathHelper.clamp(j, y + height - 3, y + height);

					for (int xx = x - 5; xx <= x + 5; xx++) {
						for (int zz = z - 5; zz <= z + 5; zz++) {
							BlockPos nextPos = new BlockPos(xx, j, zz);
							IBlockState nextState = world.getBlockState(nextPos);
							Block nextBlock = nextState.getBlock();
							double offsetX = xx - x;
							double offsetY = j - yy;
							double offsetZ = zz - z;
							double distance = offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ;

							if (distance < 10 + rand.nextInt(8) && nextBlock.canBeReplacedByLeaves(nextState, world, nextPos)) {
								setBlockAndNotifyAdequately(world, new BlockPos(xx, j, zz), ModInit.LEAVES.getStateFromMeta(1));
							}
						}
					}
				}

				for (j = 0; j < height; j++) {
					BlockPos nextPos = new BlockPos(x, y + j, z);
					IBlockState nextState = world.getBlockState(nextPos);
					Block nextBlock = nextState.getBlock();

					if (nextBlock.isAir(nextState, world, nextPos) || nextBlock.isLeaves(nextState, world, nextPos) || nextBlock.isReplaceable(world, nextPos)) {
						setBlockAndNotifyAdequately(world, new BlockPos(x, y + j, z), ModInit.LOGS.getStateFromMeta(5));
						setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + j, z), ModInit.LOGS.getStateFromMeta(5));
						setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + j, z), ModInit.LOGS.getStateFromMeta(5));
						setBlockAndNotifyAdequately(world, new BlockPos(x, y + j, z - 1), ModInit.LOGS.getStateFromMeta(5));
						setBlockAndNotifyAdequately(world, new BlockPos(x, y + j, z + 1), ModInit.LOGS.getStateFromMeta(5));
					}
				}

				setBlockAndNotifyAdequately(world, new BlockPos(x, y + j, z), ModInit.LOGS.getStateFromMeta(5));
				setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y, z - 1), ModInit.LOGS.getStateFromMeta(5));
				setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y, z + 1), ModInit.LOGS.getStateFromMeta(5));
				setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y, z + 1), ModInit.LOGS.getStateFromMeta(5));
				setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y, z - 1), ModInit.LOGS.getStateFromMeta(5));

				if (rand.nextInt(3) != 0) {
					setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + 1, z - 1), ModInit.LOGS.getStateFromMeta(5));
				}

				if (rand.nextInt(3) != 0) {
					setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + 1, z + 1), ModInit.LOGS.getStateFromMeta(5));
				}

				if (rand.nextInt(3) != 0) {
					setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + 1, z + 1), ModInit.LOGS.getStateFromMeta(5));
				}

				if (rand.nextInt(3) != 0) {
					setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + 1, z - 1), ModInit.LOGS.getStateFromMeta(5));
				}

				setBlockAndNotifyAdequately(world, new BlockPos(x - 2, y, z), ModInit.LOGS.getStateFromMeta(1));
				setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y, z), ModInit.LOGS.getStateFromMeta(1));
				setBlockAndNotifyAdequately(world, new BlockPos(x, y, z - 2), ModInit.LOGS.getStateFromMeta(9));
				setBlockAndNotifyAdequately(world, new BlockPos(x, y, z + 2), ModInit.LOGS.getStateFromMeta(9));
				setBlockAndNotifyAdequately(world, new BlockPos(x - 2, y - 1, z), ModInit.LOGS.getStateFromMeta(5));
				setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y - 1, z), ModInit.LOGS.getStateFromMeta(5));
				setBlockAndNotifyAdequately(world, new BlockPos(x, y - 1, z - 2), ModInit.LOGS.getStateFromMeta(5));
				setBlockAndNotifyAdequately(world, new BlockPos(x, y - 1, z + 2), ModInit.LOGS.getStateFromMeta(5));
				setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + (height - 4), z - 1), ModInit.LOGS.getStateFromMeta(5));
				setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + (height - 4), z + 1), ModInit.LOGS.getStateFromMeta(5));
				setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + (height - 4), z + 1), ModInit.LOGS.getStateFromMeta(5));
				setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + (height - 4), z - 1), ModInit.LOGS.getStateFromMeta(5));

				if (rand.nextInt(3) == 0) {
					setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + (height - 5), z - 1), ModInit.LOGS.getStateFromMeta(5));
				}

				if (rand.nextInt(3) == 0) {
					setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + (height - 5), z + 1), ModInit.LOGS.getStateFromMeta(5));
				}

				if (rand.nextInt(3) == 0) {
					setBlockAndNotifyAdequately(world, new BlockPos(x - 1, y + (height - 5), z + 1), ModInit.LOGS.getStateFromMeta(5));
				}

				if (rand.nextInt(3) == 0) {
					setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + (height - 5), z - 1), ModInit.LOGS.getStateFromMeta(5));
				}

				setBlockAndNotifyAdequately(world, new BlockPos(x - 2, y + (height - 4), z), ModInit.LOGS.getStateFromMeta(1));
				setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y + (height - 4), z), ModInit.LOGS.getStateFromMeta(1));
				setBlockAndNotifyAdequately(world, new BlockPos(x, y + (height - 4), z - 2), ModInit.LOGS.getStateFromMeta(9));
				setBlockAndNotifyAdequately(world, new BlockPos(x, y + (height - 4), z + 2), ModInit.LOGS.getStateFromMeta(9));

				if (generateFlowers) {
					// do it
				}
				return true;
			}
			return false;
		}
		return false;
	}
}