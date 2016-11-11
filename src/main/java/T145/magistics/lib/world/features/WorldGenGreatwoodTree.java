package T145.magistics.lib.world.features;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import T145.magistics.load.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenGreatwoodTree extends WorldGenAbstractTree {
	private Random rand;
	private World world;
	private BlockPos basePos = BlockPos.ORIGIN;
	private int heightLimit;
	private int height;
	private int trunkSize = 2;
	private int heightLimitLimit = 12;
	private int leafDistanceLimit = 4;
	private double heightAttenuation = 0.618D;
	private double branchSlope = 0.381D;
	private double scaleWidth = 1.0D;
	private double leafDensity = 1.0D;
	private List<WorldGenGreatwoodTree.FoliageCoordinates> foliageCoords;

	public WorldGenGreatwoodTree(boolean notify) {
		super(notify);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		world = worldIn;
		basePos = position;
		rand = new Random(rand.nextLong());

		if (heightLimit == 0) {
			heightLimit = 5 + rand.nextInt(heightLimitLimit);
		}

		if (!validTreeLocation()) {
			world = null;
			return false;
		} else {
			generateLeafNodeList();
			generateLeaves();
			generateTrunk();
			generateLeafNodeBases();
			world = null;
			return true;
		}
	}

	public void generateLeafNodeList() {
		height = (int) (heightLimit * heightAttenuation);

		if (height >= heightLimit) {
			height = heightLimit - 1;
		}

		int i = (int) (1.382D + Math.pow(leafDensity *  heightLimit / 13D, 2D));

		if (i < 1) {
			i = 1;
		}

		int j = basePos.getY() + height;
		int k = heightLimit - leafDistanceLimit;

		foliageCoords = Lists.<WorldGenGreatwoodTree.FoliageCoordinates>newArrayList();
		foliageCoords.add(new WorldGenGreatwoodTree.FoliageCoordinates(basePos.up(k), j));

		for (; k >= 0; --k) {
			float f = layerSize(k);

			if (f >= 0.0F) {
				for (int l = 0; l < i; ++l) {
					double d0 = scaleWidth *  f * ( rand.nextFloat() + 0.328D);
					double d1 =  (rand.nextFloat() * 2.0F) * Math.PI;
					double d2 = d0 * Math.sin(d1) + 0.5D;
					double d3 = d0 * Math.cos(d1) + 0.5D;
					BlockPos blockpos = basePos.add(d2,  (k - 1), d3);
					BlockPos blockpos1 = blockpos.up(leafDistanceLimit);

					if (checkBlockLine(blockpos, blockpos1) == -1) {
						int i1 = basePos.getX() - blockpos.getX();
						int j1 = basePos.getZ() - blockpos.getZ();
						double d4 = blockpos.getY() - Math.sqrt( (i1 * i1 + j1 * j1)) * branchSlope;
						int k1 = d4 >  j ? j : (int) d4;
						BlockPos blockpos2 = new BlockPos(basePos.getX(), k1, basePos.getZ());

						if (checkBlockLine(blockpos2, blockpos) == -1) {
							foliageCoords.add(new WorldGenGreatwoodTree.FoliageCoordinates(blockpos, blockpos2.getY()));
						}
					}
				}
			}
		}
	}

	public void crossSection(BlockPos pos, float p_181631_2_, IBlockState p_181631_3_) {
		int i = (int) (p_181631_2_ + 0.618D);

		for (int j = -i; j <= i; ++j) {
			for (int k = -i; k <= i; ++k) {
				if (Math.pow( Math.abs(j) + 0.5D, 2.0D)
						+ Math.pow( Math.abs(k) + 0.5D, 2.0D) <=  (p_181631_2_ * p_181631_2_)) {
					BlockPos blockpos = pos.add(j, 0, k);
					IBlockState state = world.getBlockState(blockpos);

					if (state.getBlock().isAir(state, world, blockpos)
							|| state.getBlock().isLeaves(state, world, blockpos)) {
						setBlockAndNotifyAdequately(world, blockpos, p_181631_3_);
					}
				}
			}
		}
	}

	private float layerSize(int y) {
		if (y < heightLimit * 0.3F) {
			return -1.0F;
		} else {
			float f = heightLimit / 2.0F;
			float f1 = f - y;
			float f2 = MathHelper.sqrt_float(f * f - f1 * f1);

			if (f1 == 0.0F) {
				f2 = f;
			} else if (Math.abs(f1) >= f) {
				return 0.0F;
			}

			return f2 * 0.5F;
		}
	}

	private float leafSize(int y) {
		return y >= 0 && y < leafDistanceLimit ? (y != 0 && y != leafDistanceLimit - 1 ? 3.0F : 2.0F) : -1.0F;
	}

	public void generateLeafNode(BlockPos pos) {
		for (int i = 0; i < leafDistanceLimit; ++i) {
			crossSection(pos.up(i), leafSize(i), ModBlocks.blockLeaves.getDefaultState().withProperty(ModBlocks.blockLeaves.CHECK_DECAY, false));
		}
	}

	private void limb(BlockPos p_175937_1_, BlockPos p_175937_2_, Block log) {
		BlockPos blockpos = p_175937_2_.add(-p_175937_1_.getX(), -p_175937_1_.getY(), -p_175937_1_.getZ());
		int i = getGreatestDistance(blockpos);
		float f = blockpos.getX() / i;
		float f1 = blockpos.getY() / i;
		float f2 = blockpos.getZ() / i;

		for (int j = 0; j <= i; ++j) {
			BlockPos blockpos1 = p_175937_1_.add( (0.5F + j * f),  (0.5F + j * f1), (0.5F + j * f2));
			BlockLog.EnumAxis blocklog$enumaxis = getLogAxis(p_175937_1_, blockpos1);
			setBlockAndNotifyAdequately(world, blockpos1, log.getDefaultState().withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
		}
	}

	private int getGreatestDistance(BlockPos posIn) {
		int i = MathHelper.abs_int(posIn.getX());
		int j = MathHelper.abs_int(posIn.getY());
		int k = MathHelper.abs_int(posIn.getZ());
		return k > i && k > j ? k : (j > i ? j : i);
	}

	private BlockLog.EnumAxis getLogAxis(BlockPos p_175938_1_, BlockPos p_175938_2_) {
		BlockLog.EnumAxis blocklog$enumaxis = BlockLog.EnumAxis.Y;
		int i = Math.abs(p_175938_2_.getX() - p_175938_1_.getX());
		int j = Math.abs(p_175938_2_.getZ() - p_175938_1_.getZ());
		int k = Math.max(i, j);

		if (k > 0) {
			if (i == k) {
				blocklog$enumaxis = BlockLog.EnumAxis.X;
			} else if (j == k) {
				blocklog$enumaxis = BlockLog.EnumAxis.Z;
			}
		}

		return blocklog$enumaxis;
	}

	private void generateLeaves() {
		for (WorldGenGreatwoodTree.FoliageCoordinates worldgenbigtree$foliagecoordinates : foliageCoords) {
			generateLeafNode(worldgenbigtree$foliagecoordinates);
		}
	}

	private boolean leafNodeNeedsBase(int p_76493_1_) {
		return  p_76493_1_ >=  heightLimit * 0.2D;
	}

	private void generateTrunk() {
		BlockPos blockpos = basePos;
		BlockPos blockpos1 = basePos.up(height);
		Block block = ModBlocks.blockLogs;
		limb(blockpos, blockpos1, block);

		if (trunkSize == 2) {
			limb(blockpos.east(), blockpos1.east(), block);
			limb(blockpos.east().south(), blockpos1.east().south(), block);
			limb(blockpos.south(), blockpos1.south(), block);
		}
	}

	private void generateLeafNodeBases() {
		for (WorldGenGreatwoodTree.FoliageCoordinates worldgenbigtree$foliagecoordinates : foliageCoords) {
			int i = worldgenbigtree$foliagecoordinates.getBranchBase();
			BlockPos blockpos = new BlockPos(basePos.getX(), i, basePos.getZ());

			if (!blockpos.equals(worldgenbigtree$foliagecoordinates) && leafNodeNeedsBase(i - basePos.getY())) {
				limb(blockpos, worldgenbigtree$foliagecoordinates, ModBlocks.blockLogs);
			}
		}
	}

	private int checkBlockLine(BlockPos posOne, BlockPos posTwo) {
		BlockPos blockpos = posTwo.add(-posOne.getX(), -posOne.getY(), -posOne.getZ());
		int i = getGreatestDistance(blockpos);
		float f = blockpos.getX() / i;
		float f1 = blockpos.getY() / i;
		float f2 = blockpos.getZ() / i;

		if (i == 0) {
			return -1;
		} else {
			for (int j = 0; j <= i; ++j) {
				BlockPos blockpos1 = posOne.add( (0.5F + j * f),  (0.5F + j * f1), (0.5F + j * f2));

				if (!isReplaceable(world, blockpos1)) {
					return j;
				}
			}

			return -1;
		}
	}

	public void setDecorationDefaults() {
		leafDistanceLimit = 5;
	}

	public boolean validTreeLocation() {
		BlockPos down = basePos.down();
		net.minecraft.block.state.IBlockState state = world.getBlockState(down);
		boolean isSoil = state.getBlock().canSustainPlant(state, world, down, net.minecraft.util.EnumFacing.UP,
				((net.minecraft.block.BlockSapling) Blocks.SAPLING));

		if (!isSoil) {
			return false;
		} else {
			int i = checkBlockLine(basePos, basePos.up(heightLimit - 1));

			if (i == -1) {
				return true;
			} else if (i < 6) {
				return false;
			} else {
				heightLimit = i;
				return true;
			}
		}
	}

	public static class FoliageCoordinates extends BlockPos {
		private final int branchBase;

		public FoliageCoordinates(BlockPos pos, int p_i45635_2_) {
			super(pos.getX(), pos.getY(), pos.getZ());
			branchBase = p_i45635_2_;
		}

		public int getBranchBase() {
			return branchBase;
		}
	}
}