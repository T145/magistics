package T145.magistics.client.lib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class ConnectedTextureHelper {
	public static String connected_suffix[] = { "0", "1_d", "1_u", "1_l", "1_r", "2_h", "2_v", "2_dl", "2_dr", "2_ul", "2_ur", "3_d", "3_u", "3_l", "3_r", "4" };

	public static boolean shouldConnect(IBlockAccess world, int x, int y, int z, Block blockTo, Block blockFrom, int metadata, boolean fuzzy) {
		if (blockTo == blockFrom) {
			return fuzzy ? true : metadata == world.getBlockMetadata(x, y, z);
		} else {
			return false;
		}
	}

	public static IIcon getConnectedTexture(IBlockAccess world, int x, int y, int z, int side, IIcon[] icon, Block blockFrom, boolean fuzzy) {
		boolean isOpenUp = false, isOpenDown = false, isOpenLeft = false, isOpenRight = false;

		switch (side) {
		case 0:
			if (shouldConnect(world, x, y, z, world.getBlock(x - 1, y, z), blockFrom, world.getBlockMetadata(x - 1, y, z), fuzzy))
				isOpenDown = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x + 1, y, z), blockFrom, world.getBlockMetadata(x + 1, y, z), fuzzy))
				isOpenUp = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y, z - 1), blockFrom, world.getBlockMetadata(x, y, z - 1), fuzzy))
				isOpenLeft = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y, z + 1), blockFrom, world.getBlockMetadata(x, y, z + 1), fuzzy))
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
			if (shouldConnect(world, x, y, z, world.getBlock(x - 1, y, z), blockFrom, world.getBlockMetadata(x - 1, y, z), fuzzy))
				isOpenDown = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x + 1, y, z), blockFrom, world.getBlockMetadata(x + 1, y, z), fuzzy))
				isOpenUp = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y, z - 1), blockFrom, world.getBlockMetadata(x, y, z - 1), fuzzy))
				isOpenLeft = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y, z + 1), blockFrom, world.getBlockMetadata(x, y, z + 1), fuzzy))
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
			if (shouldConnect(world, x, y, z, world.getBlock(x, y - 1, z), blockFrom, world.getBlockMetadata(x, y - 1, z), fuzzy))
				isOpenDown = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y + 1, z), blockFrom, world.getBlockMetadata(x, y + 1, z), fuzzy))
				isOpenUp = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x - 1, y, z), blockFrom, world.getBlockMetadata(x - 1, y, z), fuzzy))
				isOpenLeft = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x + 1, y, z), blockFrom, world.getBlockMetadata(x + 1, y, z), fuzzy))
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
			if (shouldConnect(world, x, y, z, world.getBlock(x, y - 1, z), blockFrom, world.getBlockMetadata(x, y - 1, z), fuzzy))
				isOpenDown = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y + 1, z), blockFrom, world.getBlockMetadata(x, y + 1, z), fuzzy))
				isOpenUp = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x - 1, y, z), blockFrom, world.getBlockMetadata(x - 1, y, z), fuzzy))
				isOpenLeft = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x + 1, y, z), blockFrom, world.getBlockMetadata(x + 1, y, z), fuzzy))
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
			if (shouldConnect(world, x, y, z, world.getBlock(x, y - 1, z), blockFrom, world.getBlockMetadata(x, y - 1, z), fuzzy))
				isOpenDown = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y + 1, z), blockFrom, world.getBlockMetadata(x, y + 1, z), fuzzy))
				isOpenUp = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y, z - 1), blockFrom, world.getBlockMetadata(x, y, z - 1), fuzzy))
				isOpenLeft = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y, z + 1), blockFrom, world.getBlockMetadata(x, y, z + 1), fuzzy))
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
			if (shouldConnect(world, x, y, z, world.getBlock(x, y - 1, z), blockFrom, world.getBlockMetadata(x, y - 1, z), fuzzy))
				isOpenDown = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y + 1, z), blockFrom, world.getBlockMetadata(x, y + 1, z), fuzzy))
				isOpenUp = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y, z - 1), blockFrom, world.getBlockMetadata(x, y, z - 1), fuzzy))
				isOpenLeft = true;
			if (shouldConnect(world, x, y, z, world.getBlock(x, y, z + 1), blockFrom, world.getBlockMetadata(x, y, z + 1), fuzzy))
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