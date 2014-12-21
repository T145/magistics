package T145.magistics.client.lib;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import cpw.mods.ironchest.IronChestType;

public class TextureHelper {
	public static String connected_suffix[] = { "", "1_d", "1_u", "1_l", "1_r", "2_h", "2_v", "2_dl", "2_dr", "2_ul", "2_ur", "3_d", "3_u", "3_l", "3_r", "4" };
	public static Map<IronChestType, ResourceLocation> ironChestTextures;

	public static String getSimpleIronChestName(IronChestType type) {
		return type.name().toLowerCase().replaceAll("[0-9]", "").replaceAll("chest", "");
	}

	static {
		Builder<IronChestType, ResourceLocation> builder = ImmutableMap.<IronChestType, ResourceLocation> builder();
		for (IronChestType type : IronChestType.values())
			if (type != type.WOOD)
				builder.put(type, new ResourceLocation("magistics", "textures/models/chest_hungry/" + getSimpleIronChestName(type) + ".png"));
		ironChestTextures = builder.build();
	}

	public static boolean shouldConnect(IBlockAccess ib, int i, int j, int k, Block blockTo, Block blockFrom, int meta) {
		return blockTo == blockFrom && meta == ib.getBlockMetadata(i, j, k);
	}

	public static IIcon getConnectedTexture(IBlockAccess ib, int i, int j, int k, int side, IIcon[] icon, Block blockFrom) {
		boolean isOpenUp = false, isOpenDown = false, isOpenLeft = false, isOpenRight = false;

		switch (side) {
		case 0:
			if (shouldConnect(ib, i, j, k, ib.getBlock(i - 1, j, k), blockFrom, ib.getBlockMetadata(i - 1, j, k)))
				isOpenDown = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i + 1, j, k), blockFrom, ib.getBlockMetadata(i + 1, j, k)))
				isOpenUp = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j, k - 1), blockFrom, ib.getBlockMetadata(i, j, k - 1)))
				isOpenLeft = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j, k + 1), blockFrom, ib.getBlockMetadata(i, j, k + 1)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
				return icon[15];
			else if (isOpenUp && isOpenDown && isOpenLeft)
				return icon[11];
			else if (isOpenUp && isOpenDown && isOpenRight)
				return icon[12];
			else if (isOpenUp && isOpenLeft && isOpenRight)
				return icon[13];
			else if (isOpenDown && isOpenLeft && isOpenRight)
				return icon[14];
			else if (isOpenDown && isOpenUp)
				return icon[5];
			else if (isOpenLeft && isOpenRight)
				return icon[6];
			else if (isOpenDown && isOpenLeft)
				return icon[8];
			else if (isOpenDown && isOpenRight)
				return icon[10];
			else if (isOpenUp && isOpenLeft)
				return icon[7];
			else if (isOpenUp && isOpenRight)
				return icon[9];
			else if (isOpenDown)
				return icon[3];
			else if (isOpenUp)
				return icon[4];
			else if (isOpenLeft)
				return icon[2];
			else if (isOpenRight)
				return icon[1];
			break;
		case 1:
			if (shouldConnect(ib, i, j, k, ib.getBlock(i - 1, j, k), blockFrom, ib.getBlockMetadata(i - 1, j, k)))
				isOpenDown = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i + 1, j, k), blockFrom, ib.getBlockMetadata(i + 1, j, k)))
				isOpenUp = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j, k - 1), blockFrom, ib.getBlockMetadata(i, j, k - 1)))
				isOpenLeft = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j, k + 1), blockFrom, ib.getBlockMetadata(i, j, k + 1)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
				return icon[15];
			else if (isOpenUp && isOpenDown && isOpenLeft)
				return icon[11];
			else if (isOpenUp && isOpenDown && isOpenRight)
				return icon[12];
			else if (isOpenUp && isOpenLeft && isOpenRight)
				return icon[13];
			else if (isOpenDown && isOpenLeft && isOpenRight)
				return icon[14];
			else if (isOpenDown && isOpenUp)
				return icon[5];
			else if (isOpenLeft && isOpenRight)
				return icon[6];
			else if (isOpenDown && isOpenLeft)
				return icon[8];
			else if (isOpenDown && isOpenRight)
				return icon[10];
			else if (isOpenUp && isOpenLeft)
				return icon[7];
			else if (isOpenUp && isOpenRight)
				return icon[9];
			else if (isOpenDown)
				return icon[3];
			else if (isOpenUp)
				return icon[4];
			else if (isOpenLeft)
				return icon[2];
			else if (isOpenRight)
				return icon[1];
			break;
		case 2:
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j - 1, k), blockFrom, ib.getBlockMetadata(i, j - 1, k)))
				isOpenDown = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j + 1, k), blockFrom, ib.getBlockMetadata(i, j + 1, k)))
				isOpenUp = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i - 1, j, k), blockFrom, ib.getBlockMetadata(i - 1, j, k)))
				isOpenLeft = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i + 1, j, k), blockFrom, ib.getBlockMetadata(i + 1, j, k)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
				return icon[15];
			else if (isOpenUp && isOpenDown && isOpenLeft)
				return icon[13];
			else if (isOpenUp && isOpenDown && isOpenRight)
				return icon[14];
			else if (isOpenUp && isOpenLeft && isOpenRight)
				return icon[11];
			else if (isOpenDown && isOpenLeft && isOpenRight)
				return icon[12];
			else if (isOpenDown && isOpenUp)
				return icon[6];
			else if (isOpenLeft && isOpenRight)
				return icon[5];
			else if (isOpenDown && isOpenLeft)
				return icon[9];
			else if (isOpenDown && isOpenRight)
				return icon[10];
			else if (isOpenUp && isOpenLeft)
				return icon[7];
			else if (isOpenUp && isOpenRight)
				return icon[8];
			else if (isOpenDown)
				return icon[1];
			else if (isOpenUp)
				return icon[2];
			else if (isOpenLeft)
				return icon[4];
			else if (isOpenRight)
				return icon[3];
			break;
		case 3:
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j - 1, k), blockFrom, ib.getBlockMetadata(i, j - 1, k)))
				isOpenDown = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j + 1, k), blockFrom, ib.getBlockMetadata(i, j + 1, k)))
				isOpenUp = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i - 1, j, k), blockFrom, ib.getBlockMetadata(i - 1, j, k)))
				isOpenLeft = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i + 1, j, k), blockFrom, ib.getBlockMetadata(i + 1, j, k)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
				return icon[15];
			else if (isOpenUp && isOpenDown && isOpenLeft)
				return icon[14];
			else if (isOpenUp && isOpenDown && isOpenRight)
				return icon[13];
			else if (isOpenUp && isOpenLeft && isOpenRight)
				return icon[11];
			else if (isOpenDown && isOpenLeft && isOpenRight)
				return icon[12];
			else if (isOpenDown && isOpenUp)
				return icon[6];
			else if (isOpenLeft && isOpenRight)
				return icon[5];
			else if (isOpenDown && isOpenLeft)
				return icon[10];
			else if (isOpenDown && isOpenRight)
				return icon[9];
			else if (isOpenUp && isOpenLeft)
				return icon[8];
			else if (isOpenUp && isOpenRight)
				return icon[7];
			else if (isOpenDown)
				return icon[1];
			else if (isOpenUp)
				return icon[2];
			else if (isOpenLeft)
				return icon[3];
			else if (isOpenRight)
				return icon[4];
			break;
		case 4:
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j - 1, k), blockFrom, ib.getBlockMetadata(i, j - 1, k)))
				isOpenDown = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j + 1, k), blockFrom, ib.getBlockMetadata(i, j + 1, k)))
				isOpenUp = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j, k - 1), blockFrom, ib.getBlockMetadata(i, j, k - 1)))
				isOpenLeft = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j, k + 1), blockFrom, ib.getBlockMetadata(i, j, k + 1)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
				return icon[15];
			else if (isOpenUp && isOpenDown && isOpenLeft)
				return icon[14];
			else if (isOpenUp && isOpenDown && isOpenRight)
				return icon[13];
			else if (isOpenUp && isOpenLeft && isOpenRight)
				return icon[11];
			else if (isOpenDown && isOpenLeft && isOpenRight)
				return icon[12];
			else if (isOpenDown && isOpenUp)
				return icon[6];
			else if (isOpenLeft && isOpenRight)
				return icon[5];
			else if (isOpenDown && isOpenLeft)
				return icon[10];
			else if (isOpenDown && isOpenRight)
				return icon[9];
			else if (isOpenUp && isOpenLeft)
				return icon[8];
			else if (isOpenUp && isOpenRight)
				return icon[7];
			else if (isOpenDown)
				return icon[1];
			else if (isOpenUp)
				return icon[2];
			else if (isOpenLeft)
				return icon[3];
			else if (isOpenRight)
				return icon[4];
			break;
		case 5:
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j - 1, k), blockFrom, ib.getBlockMetadata(i, j - 1, k)))
				isOpenDown = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j + 1, k), blockFrom, ib.getBlockMetadata(i, j + 1, k)))
				isOpenUp = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j, k - 1), blockFrom, ib.getBlockMetadata(i, j, k - 1)))
				isOpenLeft = true;
			if (shouldConnect(ib, i, j, k, ib.getBlock(i, j, k + 1), blockFrom, ib.getBlockMetadata(i, j, k + 1)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
				return icon[15];
			else if (isOpenUp && isOpenDown && isOpenLeft)
				return icon[13];
			else if (isOpenUp && isOpenDown && isOpenRight)
				return icon[14];
			else if (isOpenUp && isOpenLeft && isOpenRight)
				return icon[11];
			else if (isOpenDown && isOpenLeft && isOpenRight)
				return icon[12];
			else if (isOpenDown && isOpenUp)
				return icon[6];
			else if (isOpenLeft && isOpenRight)
				return icon[5];
			else if (isOpenDown && isOpenLeft)
				return icon[9];
			else if (isOpenDown && isOpenRight)
				return icon[10];
			else if (isOpenUp && isOpenLeft)
				return icon[7];
			else if (isOpenUp && isOpenRight)
				return icon[8];
			else if (isOpenDown)
				return icon[1];
			else if (isOpenUp)
				return icon[2];
			else if (isOpenLeft)
				return icon[4];
			else if (isOpenRight)
				return icon[3];
			break;
		}

		return icon[0];
	}
}