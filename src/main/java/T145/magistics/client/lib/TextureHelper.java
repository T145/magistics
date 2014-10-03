package T145.magistics.client.lib;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import T145.magistics.common.blocks.BlockChestHungryMetal;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public class TextureHelper {
	public static String connected_suffix[] = { "1_d", "1_u", "1_l", "1_r", "2_h", "2_v", "2_dl", "2_dr", "2_ul", "2_ur", "3_d", "3_u", "3_l", "3_r", "4" };
	public static Map<BlockChestHungryMetal.Types, ResourceLocation> ironChestTextures;

	static {
		Builder<BlockChestHungryMetal.Types, ResourceLocation> builder = ImmutableMap.<BlockChestHungryMetal.Types, ResourceLocation> builder();
		for (BlockChestHungryMetal.Types type : BlockChestHungryMetal.Types.values())
			builder.put(type, new ResourceLocation("magistics", "textures/models/chest_hungry/" + type.name() + ".png"));
		ironChestTextures = builder.build();
	}

	public boolean shouldConnectToBlock(Block blockFrom, Block blockTo) {
		return blockTo == blockFrom;
	}

	public IIcon getConnectedBlockTexture(Block blockFrom, IBlockAccess ib, int i, int j, int k, int side, IIcon[] icons) {
		boolean isOpenUp = false, isOpenDown = false, isOpenLeft = false, isOpenRight = false;

		switch (side) {
		case 0:
			if (shouldConnectToBlock(blockFrom, ib.getBlock(i - 1, j, k))) {
				isOpenDown = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i + 1, j, k))) {
				isOpenUp = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j, k - 1))) {
				isOpenLeft = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j, k + 1))) {
				isOpenRight = true;
			}

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
				return icons[15];
			} else if (isOpenUp && isOpenDown && isOpenLeft) {
				return icons[11];
			} else if (isOpenUp && isOpenDown && isOpenRight) {
				return icons[12];
			} else if (isOpenUp && isOpenLeft && isOpenRight) {
				return icons[13];
			} else if (isOpenDown && isOpenLeft && isOpenRight) {
				return icons[14];
			} else if (isOpenDown && isOpenUp) {
				return icons[5];
			} else if (isOpenLeft && isOpenRight) {
				return icons[6];
			} else if (isOpenDown && isOpenLeft) {
				return icons[8];
			} else if (isOpenDown && isOpenRight) {
				return icons[10];
			} else if (isOpenUp && isOpenLeft) {
				return icons[7];
			} else if (isOpenUp && isOpenRight) {
				return icons[9];
			} else if (isOpenDown) {
				return icons[3];
			} else if (isOpenUp) {
				return icons[4];
			} else if (isOpenLeft) {
				return icons[2];
			} else if (isOpenRight) {
				return icons[1];
			}
			break;
		case 1:
			if (shouldConnectToBlock(blockFrom, ib.getBlock(i - 1, j, k))) {
				isOpenDown = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i + 1, j, k))) {
				isOpenUp = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j, k - 1))) {
				isOpenLeft = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j, k + 1))) {
				isOpenRight = true;
			}

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
				return icons[15];
			} else if (isOpenUp && isOpenDown && isOpenLeft) {
				return icons[11];
			} else if (isOpenUp && isOpenDown && isOpenRight) {
				return icons[12];
			} else if (isOpenUp && isOpenLeft && isOpenRight) {
				return icons[13];
			} else if (isOpenDown && isOpenLeft && isOpenRight) {
				return icons[14];
			} else if (isOpenDown && isOpenUp) {
				return icons[5];
			} else if (isOpenLeft && isOpenRight) {
				return icons[6];
			} else if (isOpenDown && isOpenLeft) {
				return icons[8];
			} else if (isOpenDown && isOpenRight) {
				return icons[10];
			} else if (isOpenUp && isOpenLeft) {
				return icons[7];
			} else if (isOpenUp && isOpenRight) {
				return icons[9];
			} else if (isOpenDown) {
				return icons[3];
			} else if (isOpenUp) {
				return icons[4];
			} else if (isOpenLeft) {
				return icons[2];
			} else if (isOpenRight) {
				return icons[1];
			}
			break;
		case 2:
			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j - 1, k))) {
				isOpenDown = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j + 1, k))) {
				isOpenUp = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i - 1, j, k))) {
				isOpenLeft = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i + 1, j, k))) {
				isOpenRight = true;
			}

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
				return icons[15];
			} else if (isOpenUp && isOpenDown && isOpenLeft) {
				return icons[13];
			} else if (isOpenUp && isOpenDown && isOpenRight) {
				return icons[14];
			} else if (isOpenUp && isOpenLeft && isOpenRight) {
				return icons[11];
			} else if (isOpenDown && isOpenLeft && isOpenRight) {
				return icons[12];
			} else if (isOpenDown && isOpenUp) {
				return icons[6];
			} else if (isOpenLeft && isOpenRight) {
				return icons[5];
			} else if (isOpenDown && isOpenLeft) {
				return icons[9];
			} else if (isOpenDown && isOpenRight) {
				return icons[10];
			} else if (isOpenUp && isOpenLeft) {
				return icons[7];
			} else if (isOpenUp && isOpenRight) {
				return icons[8];
			} else if (isOpenDown) {
				return icons[1];
			} else if (isOpenUp) {
				return icons[2];
			} else if (isOpenLeft) {
				return icons[4];
			} else if (isOpenRight) {
				return icons[3];
			}
			break;
		case 3:
			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j - 1, k))) {
				isOpenDown = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j + 1, k))) {
				isOpenUp = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i - 1, j, k))) {
				isOpenLeft = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i + 1, j, k))) {
				isOpenRight = true;
			}

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
				return icons[15];
			} else if (isOpenUp && isOpenDown && isOpenLeft) {
				return icons[14];
			} else if (isOpenUp && isOpenDown && isOpenRight) {
				return icons[13];
			} else if (isOpenUp && isOpenLeft && isOpenRight) {
				return icons[11];
			} else if (isOpenDown && isOpenLeft && isOpenRight) {
				return icons[12];
			} else if (isOpenDown && isOpenUp) {
				return icons[6];
			} else if (isOpenLeft && isOpenRight) {
				return icons[5];
			} else if (isOpenDown && isOpenLeft) {
				return icons[10];
			} else if (isOpenDown && isOpenRight) {
				return icons[9];
			} else if (isOpenUp && isOpenLeft) {
				return icons[8];
			} else if (isOpenUp && isOpenRight) {
				return icons[7];
			} else if (isOpenDown) {
				return icons[1];
			} else if (isOpenUp) {
				return icons[2];
			} else if (isOpenLeft) {
				return icons[3];
			} else if (isOpenRight) {
				return icons[4];
			}
			break;
		case 4:
			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j - 1, k))) {
				isOpenDown = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j + 1, k))) {
				isOpenUp = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j, k - 1))) {
				isOpenLeft = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j, k + 1))) {
				isOpenRight = true;
			}

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
				return icons[15];
			} else if (isOpenUp && isOpenDown && isOpenLeft) {
				return icons[14];
			} else if (isOpenUp && isOpenDown && isOpenRight) {
				return icons[13];
			} else if (isOpenUp && isOpenLeft && isOpenRight) {
				return icons[11];
			} else if (isOpenDown && isOpenLeft && isOpenRight) {
				return icons[12];
			} else if (isOpenDown && isOpenUp) {
				return icons[6];
			} else if (isOpenLeft && isOpenRight) {
				return icons[5];
			} else if (isOpenDown && isOpenLeft) {
				return icons[10];
			} else if (isOpenDown && isOpenRight) {
				return icons[9];
			} else if (isOpenUp && isOpenLeft) {
				return icons[8];
			} else if (isOpenUp && isOpenRight) {
				return icons[7];
			} else if (isOpenDown) {
				return icons[1];
			} else if (isOpenUp) {
				return icons[2];
			} else if (isOpenLeft) {
				return icons[3];
			} else if (isOpenRight) {
				return icons[4];
			}
			break;
		case 5:
			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j - 1, k))) {
				isOpenDown = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j + 1, k))) {
				isOpenUp = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j, k - 1))) {
				isOpenLeft = true;
			}

			if (shouldConnectToBlock(blockFrom, ib.getBlock(i, j, k + 1))) {
				isOpenRight = true;
			}

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
				return icons[15];
			} else if (isOpenUp && isOpenDown && isOpenLeft) {
				return icons[13];
			} else if (isOpenUp && isOpenDown && isOpenRight) {
				return icons[14];
			} else if (isOpenUp && isOpenLeft && isOpenRight) {
				return icons[11];
			} else if (isOpenDown && isOpenLeft && isOpenRight) {
				return icons[12];
			} else if (isOpenDown && isOpenUp) {
				return icons[6];
			} else if (isOpenLeft && isOpenRight) {
				return icons[5];
			} else if (isOpenDown && isOpenLeft) {
				return icons[9];
			} else if (isOpenDown && isOpenRight) {
				return icons[10];
			} else if (isOpenUp && isOpenLeft) {
				return icons[7];
			} else if (isOpenUp && isOpenRight) {
				return icons[8];
			} else if (isOpenDown) {
				return icons[1];
			} else if (isOpenUp) {
				return icons[2];
			} else if (isOpenLeft) {
				return icons[4];
			} else if (isOpenRight) {
				return icons[3];
			}
			break;
		}

		return icons[0];
	}
}