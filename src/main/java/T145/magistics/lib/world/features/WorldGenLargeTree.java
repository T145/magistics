package T145.magistics.lib.world.features;

import java.util.Random;

import T145.magistics.Magistics;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

public class WorldGenLargeTree extends WorldGenAbstractTree {

	private static final byte COORD_PAIRS[] = { 2, 0, 0, 1, 2, 1 };
	private final IBlockState trunkState;
	private final IBlockState leafState;
	private final int heightMin;
	private final int heightMax;
	private World world;
	private Random rand = new Random();
	private int basePos[] = { 0, 0, 0 };
	private int rootRand;
	private int rootAlt;
	private int tapRootRand;
	protected int trunkSize = 1;
	protected int height;
	private int heightLimit;
	protected double heightAttenuation = 0.318D;
	protected double branchDensity = 1D;
	protected double branchSlope = 0.618D;
	protected double scaleWidth = 1D;
	protected double leafDensity = 1D;
	protected int leafDistanceLimit = 4;
	private int[][] leafNodes;

	public WorldGenLargeTree(boolean notify, IBlockState trunkState, IBlockState leafState, int heightMin, int heightMax) {
		super(notify);

		this.trunkState = trunkState;
		this.leafState = leafState;
		this.heightMin = heightMin;
		this.heightMax = heightMax;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		this.world = world;
		long seed = rand.nextLong();
		rand.setSeed(seed);
		basePos[0] = pos.getX();
		basePos[1] = pos.getY();
		basePos[2] = pos.getZ();

		if (!validTreeLocation()) {
			world = null;
			return false;
		}

		int[] heightVector = { heightMin, heightMax - heightMin };
		heightLimit = vary(rand, heightVector);
		rootRand = rand.nextInt(4);

		if (generateLeafNodeList()) {
			generateLeaves();
			generateTrunk();
			generateLeafNodeBases();
			world = null;
			return true;
		} else {
			world = null;
			return false;
		}
	}

	private int vary(Random rand, int[] opt) {
		return rand.nextInt(opt[1]) + opt[0];
	}

	private boolean generateLeafNodeList() {
		height = (int) ((double) heightLimit * heightAttenuation);

		if (height >= heightLimit)
			height = heightLimit - 1;
		if (basePos[1] + heightLimit > 256 - 4) {
			height = height / 2;
			heightLimit = heightLimit / 2;

			if (height >= heightLimit) {
				height = heightLimit - 1;
			}

			if (height < 1 || basePos[1] + heightLimit > 256 - 4) {
				return false;
			}
		}

		int i = (int) (1.382D + Math.pow((leafDensity * (double) heightLimit) / 13D, 2D));

		if (i < 1) {
			i = 1;
		}

		int ai[][] = new int[i * heightLimit][4];
		int j = (basePos[1] + heightLimit) - leafDistanceLimit;
		int k = 1;
		int l = basePos[1] + height;
		int i1 = j - basePos[1];

		// purely vertical "branch"
		ai[0][0] = basePos[0];
		ai[0][1] = j;
		ai[0][2] = basePos[2];
		ai[0][3] = l;
		j--;

		while (i1 >= 0) {
			int j1 = 0;
			float f = layerSize(i1);

			if (k >= i * heightLimit) {
				f = -1.0F;
			}

			if (f < 0.0F) {
				j--;
				i1--;
			} else {
				for (double d0 = 0.5D; j1 < i; j1++) {
					double d1 = scaleWidth * ((double) f * ((double) rand.nextFloat() + 0.328D));
					double d2 = (double) rand.nextFloat() * 2D * Math.PI;
					int k1 = MathHelper.floor_double(d1 * Math.sin(d2) + (double) basePos[0] + d0);
					int l1 = MathHelper.floor_double(d1 * Math.cos(d2) + (double) basePos[2] + d0);
					int ai1[] = { k1, j, l1 };
					int ai2[] = { k1, j + leafDistanceLimit, l1 };

					if (checkBlockLine(ai1, ai2) != -1) {
						continue;
					}

					int ai3[] = { basePos[0], basePos[1], basePos[2] };
					double d3 = Math.sqrt(Math.pow(Math.abs(basePos[0] - ai1[0]), 2D) + Math.pow(Math.abs(basePos[2] - ai1[2]), 2D));
					double d4 = d3 * branchSlope;

					if ((double) ai1[1] - d4 > (double) l) {
						ai3[1] = l;
					} else {
						ai3[1] = (int) ((double) ai1[1] - d4);
					}

					if (checkBlockLine(ai3, ai1) == -1) {
						ai[k][0] = k1;
						ai[k][1] = j;
						ai[k][2] = l1;
						ai[k][3] = ai3[1];
						k++;
					}
				}

				j--;
				i1--;
			}
		}

		leafNodes = new int[k][4];
		System.arraycopy(ai, 0, leafNodes, 0, k);
		return true;
	}

	void generateLeaves(int i, int j, int k, float f, byte byte0) {
		int i1 = (int) ((double) f + 0.618D);
		byte byte1 = COORD_PAIRS[byte0];
		byte byte2 = COORD_PAIRS[byte0 + 3];
		int ai[] = { i, j, k };
		int ai1[] = { 0, 0, 0 };
		int j1 = -i1;
		int k1 = -i1;

		ai1[byte0] = ai[byte0];

		for (; j1 <= i1; j1++) {
			ai1[byte1] = ai[byte1] + j1;
			for (int l1 = -i1; l1 <= i1;) {
				double d = Math.sqrt(Math.pow((double) Math.abs(j1) + 0.5D, 2D) + Math.pow((double) Math.abs(l1) + 0.5D, 2D));

				if (d > (double) f) {
					l1++;
				} else {
					ai1[byte2] = ai[byte2] + l1;
					BlockPos pos = new BlockPos(ai1[0], ai1[1], ai1[2]);
					Block i2 = world.getBlockState(pos).getBlock();

					if (i2 != Blocks.AIR && i2 != Blocks.LEAVES) {
						l1++;
					} else {
						setBlockAndNotifyAdequately(world, pos, leafState);
						l1++;
					}
				}
			}
		}
	}

	float layerSize(int i) {
		if (trunkSize == 0) {
			return heightLimit - rand.nextFloat();
		} else if (trunkSize == 3) {
		} else if (trunkSize > 1) {
			if (rand.nextFloat() > 0.70F) {
				return -1.618F;
			}
		}

		if (trunkSize == 3) {
			if ((double) i < (double) (float) heightLimit * 0.19999999999999999D) {
				return -1.618F;
			}
		}
		if (trunkSize < 3) {
			if ((double) i < (double) (float) heightLimit * 0.29999999999999999D) {
				return -1.618F;
			}
		}
		if (trunkSize == 4) {
			if ((double) i < (double) (float) heightLimit * 0.15999999999999999D) {
				return -1.618F;
			}
		}

		float f = (float) heightLimit / 2.0F;
		float f1 = (float) heightLimit / 2.0F - (float) i;
		float f2;

		if (f1 == 0.0F) {
			f2 = f;
		} else if (Math.abs(f1) >= f) {
			f2 = 0.0F;
		} else {
			f2 = (float) Math.sqrt(Math.pow(Math.abs(f), 2D) - Math.pow(Math.abs(f1), 2D));
		}

		f2 *= 0.5F;
		return f2;
	}

	float leafSize(int i) {
		if (i < 0 || i >= leafDistanceLimit) {
			return -1F;
		}
		return i != 0 && i != leafDistanceLimit - 1 ? 3F : 2.0F;
	}

	void generateLeafNode(int i, int j, int k) {
		int l = j;

		for (int i1 = j + leafDistanceLimit; l < i1; l++) {
			float f = leafSize(l - j);
			generateLeaves(i, l, k, f, (byte) 1);
		}
	}

	void placeBlockLine(int ai[], int ai1[]) {
		int ai2[] = { 0, 0, 0 };
		byte byte0 = 0;
		int j = 0;

		for (; byte0 < 3; byte0++) {
			ai2[byte0] = ai1[byte0] - ai[byte0];
			if (Math.abs(ai2[byte0]) > Math.abs(ai2[j])) {
				j = byte0;
			}
		}

		if (ai2[j] == 0) {
			return;
		}

		byte byte1 = COORD_PAIRS[j];
		byte byte2 = COORD_PAIRS[j + 3];
		byte byte3;

		if (ai2[j] > 0) {
			byte3 = 1;
		} else {
			byte3 = -1;
		}

		double d = (double) ai2[byte1] / (double) ai2[j];
		double d1 = (double) ai2[byte2] / (double) ai2[j];
		int ai3[] = { 0, 0, 0 };
		int k = 0;

		for (int l = ai2[j] + byte3; k != l; k += byte3) {
			ai3[j] = MathHelper.floor_double((double) (ai[j] + k) + 0.5D);
			ai3[byte1] = MathHelper.floor_double((double) ai[byte1] + (double) k * d + 0.5D);
			ai3[byte2] = MathHelper.floor_double((double) ai[byte2] + (double) k * d1 + 0.5D);
			setBlockAndNotifyAdequately(world, new BlockPos(ai3[0], ai3[1], ai3[2]), trunkState);
		}
	}

	void generateLeaves() {
		for (int i = 0; i < leafNodes.length; i++) {
			int k = leafNodes[i][0];
			int l = leafNodes[i][1];
			int i1 = leafNodes[i][2];
			generateLeafNode(k, l, i1);
		}
	}

	boolean leafNodeNeedsBase(int i) {
		if (trunkSize != 2) {
			return true;
		}
		return (double) i >= (double) heightLimit * 0.2D;
	}

	void generateTrunk() {
		int i = basePos[0];
		int j = basePos[1];
		int k = basePos[1] + height + 2;
		int l = basePos[2];
		int ai[] = { i, j, l };
		int ai1[] = { i, k, l };

		if (trunkSize == 1) {
			placeBlockLine(ai, ai1);
		}

		if (trunkSize == 2) {
			rootAlt = 0;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1] - 2, ai[2], 5.0 / 8.0, -1.0 / 16.0);
			rootAlt = 1;
			growRoot(ai[0], ai[1], ai[2], 5.7 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]++;
			ai1[0]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1], ai[2], 6.3 / 8.0, -1.0 / 16.0);
			growRoot(ai[0], ai[1] - 2, ai[2], 7.0 / 8.0, -1.0 / 16.0);
			rootAlt = 1;
			growRoot(ai[0], ai[1], ai[2], 7.7 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[2]++;
			ai1[2]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1], ai[2], 0.3 / 8.0, -1.0 / 16.0);
			growRoot(ai[0], ai[1] - 2, ai[2], 1.0 / 8.0, -1.0 / 16.0);
			rootAlt = 1;
			growRoot(ai[0], ai[1], ai[2], 1.7 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]--;
			ai1[0]--;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1], ai[2], 2.3 / 8.0, -1.0 / 16.0);
			growRoot(ai[0], ai[1] - 2, ai[2], 3.0 / 8.0, -1.0 / 16.0);
			rootAlt = 1;
			growRoot(ai[0], ai[1], ai[2], 3.7 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			growRoot(ai[0], ai[1], ai[2] - 1, 4.3 / 8.0, -1.0 / 16.0);
		}

		if (trunkSize == 3) {
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			placeBlockLine(ai, ai1);
			ai[0]++;
			ai1[0]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			placeBlockLine(ai, ai1);
			ai[2]++;
			ai1[2]++;
			ai[0]--;
			ai1[0]--;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			placeBlockLine(ai, ai1);
			ai[2]--;
			ai1[2]--;
			ai[0]--;
			ai1[0]--;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			placeBlockLine(ai, ai1);
			ai[2]--;
			ai1[2]--;
			ai[0]++;
			ai1[0]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			placeBlockLine(ai, ai1);
		}

		if (trunkSize == 4) {
			rootAlt = 10;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1] + 1, ai[2], 5.0 / 8.0, -1.0 / 16.0);
			ai[0]++;
			ai1[0]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1] + 1, ai[2], 7.0 / 8.0, -1.0 / 16.0);
			ai[2]++;
			ai1[2]++;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1] + 1, ai[2], 1.0 / 8.0, -1.0 / 16.0);
			ai[0]--;
			ai1[0]--;
			growTapRoot(ai[0], ai[1], ai[2], 1.0);
			growRoot(ai[0], ai[1] + 1, ai[2], 3.0 / 8.0, -1.0 / 16.0);
			ai[0]--;
			ai1[0]--;
			ai[2]--;
			ai1[2]--;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 4.4 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]++;
			ai1[0]++;
			ai[2]--;
			ai1[2]--;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 5.6 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]++;
			ai1[0]++;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 6.4 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]++;
			ai1[0]++;
			ai[2]++;
			ai1[2]++;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 7.6 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[2]++;
			ai1[2]++;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 0.4 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]--;
			ai1[0]--;
			ai[2]++;
			ai1[2]++;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 1.6 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]--;
			ai1[0]--;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 2.4 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
			ai[0]--;
			ai1[0]--;
			ai[2]--;
			ai1[2]--;
			growTapRoot(ai[0], ai[1], ai[2], 0.5);
			growRoot(ai[0], ai[1] + 1, ai[2], 3.6 / 8.0, -1.0 / 16.0);
			placeBlockLine(ai, ai1);
		}
	}

	private int getMedium(int i, int j, int k) { // TODO: Add more blocks that make growth permissable
		Block canGrowOpen[] = { Blocks.AIR, Blocks.SAPLING, Blocks.FLOWING_WATER, Blocks.WATER, Blocks.FLOWING_LAVA, Blocks.LAVA, Blocks.LOG, Blocks.LOG2, Blocks.LEAVES, Blocks.LEAVES2 };
		Block canGrowSolid[] = { Blocks.GRASS, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL };
		BlockPos pos = new BlockPos(i, j, k);
		Block qq = world.getBlockState(pos).getBlock();
		int medium = 0;

		for (int m = 0; m < canGrowOpen.length; m++) {
			if (qq == canGrowOpen[m]) {
				medium = 1;
				break;
			}
		}

		if (medium == 0) {
			for (int m = 0; m < canGrowSolid.length; m++) {
				if (qq == canGrowSolid[m]) {
					medium = 2;
					break;
				}
			}
		}

		return medium;
	}

	void growTapRoot(int i, int j, int k, double flen) {
		if (Magistics.config.generateRoots) {
			int med;
			int len = (int) ((6.0 + rand.nextFloat() * 6.0) * flen);

			if (len == tapRootRand || len == tapRootRand + 1 || len == tapRootRand - 1) {
				len = (int) ((6.0 + rand.nextFloat() * 6.0) * flen);
			}

			for (int jj = 1; jj <= len; jj++) {
				med = getMedium(i, j - jj, k);
				if (med == 1) {
					len -= 1;
				} else if (med == 0) {
					len = Math.min(len, jj - 1);
					break;
				}
			}

			tapRootRand = len;

			for (int jj = 1; jj <= len; jj++) {
				setBlockAndNotifyAdequately(world, new BlockPos(i, j - jj, k), trunkState);
			}
		}
	}

	void growRoot(int l, int m, int n, double theta, double phi) {
		if (Magistics.config.generateRoots) {
			if (rootAlt == 1) {
				rootRand = rand.nextInt(2);
				m -= rootRand;
				rootAlt = 2;
			} else if (rootAlt == 2) {
				if (rootRand == 0)
					m -= 1;
				rootAlt = 0;
			} else if (rootAlt == 10) {
				m -= rand.nextInt(2);
			}

			m += 1;
			phi -= (double) rand.nextFloat() * 0.05;
			theta += (double) rand.nextFloat() * 0.1 - 0.05;

			double direction = (2.0 * Math.PI) * theta;
			double curl = rand.nextFloat() * 0.4F - 0.2F;
			double pitch = (2.0 * Math.PI) * phi;
			int length = 2 + (3 * trunkSize) + rand.nextInt(2);
			double x, y, z;

			if (l > 0) {
				x = (double) l + 0.5;
			} else {
				x = (double) l - 0.5;
			}

			y = (double) m + 0.5;

			if (n > 0) {
				z = (double) n + 0.5;
			} else {
				z = (double) n - 0.5;
			}

			double x2, y2, z2, hoz;
			int i = (int) x;
			int j = (int) y;
			int k = (int) z;
			int i2, j2, k2, di, dk;
			int med = getMedium(i, j, k);
			int cnt = 0;

			while (length > 0.0) {
				length--;
				curl = curl + rand.nextFloat() * 0.06F - 0.03F;

				if (med == 1) {
					pitch = (pitch + Math.PI / 2.0) * 0.7 - Math.PI / 2.0;
				} else {
					pitch = (pitch + Math.PI / 2.0) * 0.9 - Math.PI / 2.0;
				}

				hoz = Math.cos(pitch);
				x2 = x + Math.cos(direction) * hoz;
				y2 = y + Math.sin(pitch);
				z2 = z + Math.sin(direction) * hoz;
				i2 = (int) x2;
				j2 = (int) y2;
				k2 = (int) z2;

				if (i2 != i || j2 != j || k2 != k) {
					setBlockAndNotifyAdequately(world, new BlockPos(i, j, k), trunkState);
					cnt++;

					if (cnt < 4) {
						if (j2 != j - 1 || i2 != i || k2 != k) {
							setBlockAndNotifyAdequately(world, new BlockPos(i, j - 1, k), trunkState);
						}
					}

					med = getMedium(i2, j2, k2);

					if (med != 0) {
						x = x2;
						y = y2;
						z = z2;
						i = i2;
						j = j2;
						k = k2;
					} else {
						med = getMedium(i, j - 1, k);

						if (med != 0) {
							y = y - 1.0;
							j = j - 1;
							pitch = -Math.PI / 2.0;
						} else {
							x2 = x + Math.cos(direction);
							z2 = z + Math.sin(direction);
							i2 = (int) x2;
							k2 = (int) z2;
							med = getMedium(i2, j, k2);

							if (med != 0) {
								x = x2;
								z = z2;
								i = i2;
								k = k2;
								pitch = 0.0;
							} else {
								int dir = ((int) (direction * 8.0 / Math.PI));
								if (dir < 0) {
									dir = 15 - (15 - dir) % 16;
								} else {
									dir = dir % 16;
								}

								int pol = dir % 2;
								di = i2 - i;
								dk = k2 - k;
								int[] tdir = { 0, 0, 0, 0 };

								if (di == 0 && dk == 0) {
									if (dir < 1) {
										di = 1;
										dk = 0;
									} else if (dir < 3) {
										di = 1;
										dk = 1;
									} else if (dir < 5) {
										di = 0;
										dk = 1;
									} else if (dir < 7) {
										di = -1;
										dk = 1;
									} else if (dir < 9) {
										di = -1;
										dk = 0;
									} else if (dir < 11) {
										di = -1;
										dk = -1;
									} else if (dir < 13) {
										di = 0;
										dk = -1;
									} else if (dir < 15) {
										di = 1;
										dk = -1;
									} else {
										di = 1;
										dk = 0;
									}
								}
								if (dk == 0) {
									if (di > 0) {
										if (pol == 1) {
											tdir[0] = 2;
											tdir[1] = 14;
											tdir[2] = 4;
											tdir[3] = 12;
										} else {
											tdir[0] = 14;
											tdir[1] = 2;
											tdir[2] = 12;
											tdir[3] = 4;
										}
									} else {
										if (pol == 1) {
											tdir[0] = 6;
											tdir[1] = 10;
											tdir[2] = 4;
											tdir[3] = 12;
										} else {
											tdir[0] = 10;
											tdir[1] = 6;
											tdir[2] = 12;
											tdir[3] = 4;
										}
									}
								} else if (di == 0) {
									if (dk > 0) {
										if (pol == 1) {
											tdir[0] = 2;
											tdir[1] = 6;
											tdir[2] = 0;
											tdir[3] = 8;
										} else {
											tdir[0] = 6;
											tdir[1] = 2;
											tdir[2] = 8;
											tdir[3] = 0;
										}
									} else {
										if (pol == 1) {
											tdir[0] = 10;
											tdir[1] = 14;
											tdir[2] = 8;
											tdir[3] = 0;
										} else {
											tdir[0] = 14;
											tdir[1] = 10;
											tdir[2] = 0;
											tdir[3] = 8;
										}
									}
								} else if (dk > 0) {
									if (di > 0) {
										if (pol == 1) {
											tdir[0] = 0;
											tdir[1] = 4;
											tdir[2] = 14;
											tdir[3] = 6;
										} else {
											tdir[0] = 4;
											tdir[1] = 0;
											tdir[2] = 6;
											tdir[3] = 14;
										}
									} else {
										if (pol == 1) {
											tdir[0] = 4;
											tdir[1] = 8;
											tdir[2] = 2;
											tdir[3] = 10;
										} else {
											tdir[0] = 8;
											tdir[1] = 4;
											tdir[2] = 10;
											tdir[3] = 2;
										}
									}
								} else {
									if (di > 0) {
										if (pol == 1) {
											tdir[0] = 12;
											tdir[1] = 0;
											tdir[2] = 10;
											tdir[3] = 2;
										} else {
											tdir[0] = 0;
											tdir[1] = 12;
											tdir[2] = 2;
											tdir[3] = 10;
										}
									} else {
										if (pol == 1) {
											tdir[0] = 8;
											tdir[1] = 12;
											tdir[2] = 6;
											tdir[3] = 14;
										} else {
											tdir[0] = 12;
											tdir[1] = 8;
											tdir[2] = 14;
											tdir[3] = 6;
										}
									}
								}

								for (int q = 0; q < 4; q++) {
									if (tdir[q] == 0) {
										di = 1;
										dk = 0;
									} else if (tdir[q] == 2) {
										di = 1;
										dk = 1;
									} else if (tdir[q] == 4) {
										di = 0;
										dk = 1;
									} else if (tdir[q] == 6) {
										di = -1;
										dk = 1;
									} else if (tdir[q] == 8) {
										di = -1;
										dk = 0;
									} else if (tdir[q] == 10) {
										di = -1;
										dk = -1;
									} else if (tdir[q] == 12) {
										di = 0;
										dk = -1;
									} else {
										di = 1;
										dk = -1;
									}

									i2 = i + di;
									k2 = k + dk;
									med = getMedium(i2, j, k2);

									if (med != 0) {
										i = i2;
										k = k2;
										x = (double) i + 0.5;
										z = (double) k + 0.5;
										pitch = 0;
										direction = (double) tdir[q] * 2.0 * Math.PI / 16.0;
										break;
									}
								}

								if (med == 0) {
									return; // Root cannot grow any further.
								}
							}
						}
					}
				}
			}
		}
	}

	void generateLeafNodeBases() {
		int i = 0;
		int j = leafNodes.length;
		int ai[] = { basePos[0], basePos[1], basePos[2] };

		for (; i < j; i++) {
			int ai1[] = leafNodes[i];
			int ai2[] = { ai1[0], ai1[1], ai1[2] };
			ai[1] = ai1[3];
			int k = ai[1] - basePos[1];
			if (leafNodeNeedsBase(k)) {
				placeBlockLine(ai, ai2);
			}
		}
	}

	int checkBlockLine(int ai[], int ai1[]) {
		int ai2[] = { 0, 0, 0 };
		byte byte0 = 0;
		int i = 0;

		for (; byte0 < 3; byte0++) {
			ai2[byte0] = ai1[byte0] - ai[byte0];
			if (Math.abs(ai2[byte0]) > Math.abs(ai2[i])) {
				i = byte0;
			}
		}

		if (ai2[i] == 0) {
			return -1;
		}

		byte byte1 = COORD_PAIRS[i];
		byte byte2 = COORD_PAIRS[i + 3];
		byte byte3;

		if (ai2[i] > 0) {
			byte3 = 1;
		} else {
			byte3 = -1;
		}

		double d = (double) ai2[byte1] / (double) ai2[i];
		double d1 = (double) ai2[byte2] / (double) ai2[i];
		int ai3[] = { 0, 0, 0 };
		int j = 0;
		int k = ai2[i] + byte3;

		do {
			if (j == k) {
				break;
			}

			ai3[i] = ai[i] + j;
			ai3[byte1] = MathHelper.floor_double((double) ai[byte1] + (double) j * d);
			ai3[byte2] = MathHelper.floor_double((double) ai[byte2] + (double) j * d1);

			BlockPos pos = new BlockPos(ai3[0], ai3[1], ai3[2]);
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();

			if (block != Blocks.AIR && block != Blocks.LEAVES && block != Blocks.LOG) {
				break;
			}
			j += byte3;
		} while (true);

		if (j == k) {
			return -1;
		} else {
			return Math.abs(j);
		}
	}

	boolean validTreeLocation() {
		BlockPos pos = new BlockPos(basePos[0], basePos[1] - 1, basePos[2]);
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		boolean isSoil = block.canSustainPlant(state, world, pos, EnumFacing.UP, (IPlantable) Blocks.SAPLING);
		return isSoil;
	}
}