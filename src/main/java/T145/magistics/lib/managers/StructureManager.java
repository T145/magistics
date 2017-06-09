package T145.magistics.lib.managers;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.init.ModBlocks;
import T145.magistics.init.ModDimensions;
import T145.magistics.tiles.devices.TileChestVoid;
import T145.magistics.world.data.WorldDataVoidChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StructureManager {

	public static void generateCubeForVoidChest(TileChestVoid chest) {
		if (chest.id != -1) {
			return;
		}

		chest.id = WorldDataVoidChest.reserveVoidChestId();
		chest.markDirty();

		generateCube(chest);
	}

	private static void generateCube(TileChestVoid chest) {
		int size = 12;
		int startX = chest.id * 1024 + size;
		int startY = 40 + size;
		int startZ = size;

		generateCube(ModDimensions.getServerVoidWorld(), new BlockPos(startX, startY, startZ), size, ModBlocks.voidBorder.getDefaultState());
	}

	public static void generateCube(World world, BlockPos cornerPos, int size, IBlockState state) {
		for (BlockPos pos : getCubePositions(cornerPos, size + 1, size + 1, size + 1, true)) {
			world.setBlockState(pos, state);
		}
	}

	public static List<BlockPos> getCubePositions(BlockPos cornerPos, int width, int height, int depth, boolean includeFloor) {
		int minX = Math.min(cornerPos.getX(), cornerPos.getX() - (width - 1));
		int minY = Math.min(cornerPos.getY(), cornerPos.getY() - (height - 1));
		int minZ = Math.min(cornerPos.getZ(), cornerPos.getZ() - (depth - 1));

		int maxX = Math.max(cornerPos.getX(), cornerPos.getX() - (width - 1));
		int maxY = Math.max(cornerPos.getY(), cornerPos.getY() - (height - 1));
		int maxZ = Math.max(cornerPos.getZ(), cornerPos.getZ() - (depth - 1));

		List<BlockPos> list = new ArrayList<>();

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					if (x == minX || z == minZ || x == maxX || y == maxY || z == maxZ || (y == minY && includeFloor)) {
						BlockPos pos = new BlockPos(x, y, z);
						list.add(pos);
					}
				}
			}
		}

		return list;
	}
}