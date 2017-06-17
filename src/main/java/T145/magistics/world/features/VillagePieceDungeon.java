package T145.magistics.world.features;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class VillagePieceDungeon extends StructureVillagePieces.Village {

	public VillagePieceDungeon() {}

	public VillagePieceDungeon(StructureVillagePieces.Start start, int type, Random rand, StructureBoundingBox p_i45561_4_, EnumFacing facing) {
		super(start, type);
		this.setCoordBaseMode(facing);
		this.boundingBox = p_i45561_4_;
	}

	public static VillagePieceDungeon createPiece(StructureVillagePieces.Start start, List<StructureComponent> p_175849_1_, Random rand, int p_175849_3_, int p_175849_4_, int p_175849_5_, EnumFacing facing, int p_175849_7_) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175849_3_, p_175849_4_, p_175849_5_, 0, 0, 0, 9, 7, 12, facing);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175849_1_, structureboundingbox) == null ? new VillagePieceDungeon(start, p_175849_7_, rand, structureboundingbox, facing) : null;
	}

	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBoxIn) {
		if (averageGroundLvl < 0) {
			averageGroundLvl = getAverageGroundLevel(world, structureBoundingBoxIn);

			if (averageGroundLvl < 0) {
				return true;
			}

			boundingBox.offset(0, averageGroundLvl - boundingBox.maxY + 7 - 1, 0);
		}

		IBlockState iblockstate = getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
		IBlockState iblockstate1 = getBiomeSpecificBlockState(
				Blocks.BIRCH_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
		IBlockState iblockstate2 = getBiomeSpecificBlockState(
				Blocks.BIRCH_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
		IBlockState iblockstate3 = getBiomeSpecificBlockState(
				Blocks.BIRCH_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
		IBlockState iblockstate4 = getBiomeSpecificBlockState(
				Blocks.BIRCH_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
		IBlockState iblockstate5 = getBiomeSpecificBlockState(
				Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH));
		IBlockState iblockstate6 = getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
		fillWithBlocks(world, structureBoundingBoxIn, 1, 1, 1, 7, 4, 4, Blocks.AIR.getDefaultState(),
				Blocks.AIR.getDefaultState(), false);
		fillWithBlocks(world, structureBoundingBoxIn, 2, 1, 6, 8, 4, 10, Blocks.AIR.getDefaultState(),
				Blocks.AIR.getDefaultState(), false);
		fillWithBlocks(world, structureBoundingBoxIn, 2, 0, 5, 8, 0, 10, iblockstate5, iblockstate5, false);
		fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 1, 7, 0, 4, iblockstate5, iblockstate5, false);
		fillWithBlocks(world, structureBoundingBoxIn, 0, 0, 0, 0, 3, 5, iblockstate, iblockstate, false);
		fillWithBlocks(world, structureBoundingBoxIn, 8, 0, 0, 8, 3, 10, iblockstate, iblockstate, false);
		fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 0, 7, 2, 0, iblockstate, iblockstate, false);
		fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 5, 2, 1, 5, iblockstate, iblockstate, false);
		fillWithBlocks(world, structureBoundingBoxIn, 2, 0, 6, 2, 3, 10, iblockstate, iblockstate, false);
		fillWithBlocks(world, structureBoundingBoxIn, 3, 0, 10, 7, 3, 10, iblockstate, iblockstate, false);
		fillWithBlocks(world, structureBoundingBoxIn, 1, 2, 0, 7, 3, 0, iblockstate5, iblockstate5, false);
		fillWithBlocks(world, structureBoundingBoxIn, 1, 2, 5, 2, 3, 5, iblockstate5, iblockstate5, false);
		fillWithBlocks(world, structureBoundingBoxIn, 0, 4, 1, 8, 4, 1, iblockstate5, iblockstate5, false);
		fillWithBlocks(world, structureBoundingBoxIn, 0, 4, 4, 3, 4, 4, iblockstate5, iblockstate5, false);
		fillWithBlocks(world, structureBoundingBoxIn, 0, 5, 2, 8, 5, 3, iblockstate5, iblockstate5, false);
		setBlockState(world, iblockstate5, 0, 4, 2, structureBoundingBoxIn);
		setBlockState(world, iblockstate5, 0, 4, 3, structureBoundingBoxIn);
		setBlockState(world, iblockstate5, 8, 4, 2, structureBoundingBoxIn);
		setBlockState(world, iblockstate5, 8, 4, 3, structureBoundingBoxIn);
		setBlockState(world, iblockstate5, 8, 4, 4, structureBoundingBoxIn);
		IBlockState iblockstate7 = iblockstate1;
		IBlockState iblockstate8 = iblockstate2;
		IBlockState iblockstate9 = iblockstate4;
		IBlockState iblockstate10 = iblockstate3;

		for (int i = -1; i <= 2; ++i) {
			for (int j = 0; j <= 8; ++j) {
				setBlockState(world, iblockstate7, j, 4 + i, i, structureBoundingBoxIn);

				if ((i > -1 || j <= 1) && (i > 0 || j <= 3) && (i > 1 || j <= 4 || j >= 6)) {
					setBlockState(world, iblockstate8, j, 4 + i, 5 - i, structureBoundingBoxIn);
				}
			}
		}

		fillWithBlocks(world, structureBoundingBoxIn, 3, 4, 5, 3, 4, 10, iblockstate5, iblockstate5, false);
		fillWithBlocks(world, structureBoundingBoxIn, 7, 4, 2, 7, 4, 10, iblockstate5, iblockstate5, false);
		fillWithBlocks(world, structureBoundingBoxIn, 4, 5, 4, 4, 5, 10, iblockstate5, iblockstate5, false);
		fillWithBlocks(world, structureBoundingBoxIn, 6, 5, 4, 6, 5, 10, iblockstate5, iblockstate5, false);
		fillWithBlocks(world, structureBoundingBoxIn, 5, 6, 3, 5, 6, 10, iblockstate5, iblockstate5, false);

		for (int k = 4; k >= 1; --k) {
			setBlockState(world, iblockstate5, k, 2 + k, 7 - k, structureBoundingBoxIn);

			for (int k1 = 8 - k; k1 <= 10; ++k1) {
				setBlockState(world, iblockstate10, k, 2 + k, k1, structureBoundingBoxIn);
			}
		}

		setBlockState(world, iblockstate5, 6, 6, 3, structureBoundingBoxIn);
		setBlockState(world, iblockstate5, 7, 5, 4, structureBoundingBoxIn);
		setBlockState(world, iblockstate4, 6, 6, 4, structureBoundingBoxIn);

		for (int l = 6; l <= 8; ++l) {
			for (int l1 = 5; l1 <= 10; ++l1) {
				setBlockState(world, iblockstate9, l, 12 - l, l1, structureBoundingBoxIn);
			}
		}

		IBlockState windowState = Blocks.GLASS_PANE.getDefaultState();
		setBlockState(world, iblockstate6, 0, 2, 1, structureBoundingBoxIn);
		setBlockState(world, iblockstate6, 0, 2, 4, structureBoundingBoxIn);
		setBlockState(world, windowState, 0, 2, 2, structureBoundingBoxIn);
		setBlockState(world, windowState, 0, 2, 3, structureBoundingBoxIn);
		setBlockState(world, iblockstate6, 4, 2, 0, structureBoundingBoxIn);
		setBlockState(world, windowState, 5, 2, 0, structureBoundingBoxIn);
		setBlockState(world, iblockstate6, 6, 2, 0, structureBoundingBoxIn);
		setBlockState(world, iblockstate6, 8, 2, 1, structureBoundingBoxIn);
		setBlockState(world, windowState, 8, 2, 2, structureBoundingBoxIn);
		setBlockState(world, windowState, 8, 2, 3, structureBoundingBoxIn);
		setBlockState(world, iblockstate6, 8, 2, 4, structureBoundingBoxIn);
		setBlockState(world, iblockstate5, 8, 2, 5, structureBoundingBoxIn);
		setBlockState(world, iblockstate6, 8, 2, 6, structureBoundingBoxIn);
		setBlockState(world, windowState, 8, 2, 7, structureBoundingBoxIn);
		setBlockState(world, windowState, 8, 2, 8, structureBoundingBoxIn);
		setBlockState(world, iblockstate6, 8, 2, 9, structureBoundingBoxIn);
		setBlockState(world, iblockstate6, 2, 2, 6, structureBoundingBoxIn);
		setBlockState(world, windowState, 2, 2, 7, structureBoundingBoxIn);
		setBlockState(world, windowState, 2, 2, 8, structureBoundingBoxIn);
		setBlockState(world, iblockstate6, 2, 2, 9, structureBoundingBoxIn);
		setBlockState(world, iblockstate6, 4, 4, 10, structureBoundingBoxIn);
		setBlockState(world, windowState, 5, 4, 10, structureBoundingBoxIn);
		setBlockState(world, iblockstate6, 6, 4, 10, structureBoundingBoxIn);
		setBlockState(world, iblockstate5, 5, 5, 10, structureBoundingBoxIn);
		setBlockState(world, Blocks.AIR.getDefaultState(), 2, 1, 0, structureBoundingBoxIn);
		setBlockState(world, Blocks.AIR.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
		placeTorch(world, EnumFacing.NORTH, 2, 3, 1, structureBoundingBoxIn);
		createVillageDoor(world, structureBoundingBoxIn, rand, 2, 1, 0, EnumFacing.NORTH);
		fillWithBlocks(world, structureBoundingBoxIn, 1, 0, -1, 3, 2, -1, Blocks.AIR.getDefaultState(),
				Blocks.AIR.getDefaultState(), false);

		if (getBlockStateFromPos(world, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR
				&& getBlockStateFromPos(world, 2, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR) {
			setBlockState(world, iblockstate7, 2, 0, -1, structureBoundingBoxIn);

			if (getBlockStateFromPos(world, 2, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH) {
				setBlockState(world, Blocks.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
			}
		}

		for (int i1 = 0; i1 < 5; ++i1) {
			for (int i2 = 0; i2 < 9; ++i2) {
				clearCurrentPositionBlocksUpwards(world, i2, 7, i1, structureBoundingBoxIn);
				replaceAirAndLiquidDownwards(world, iblockstate, i2, -1, i1, structureBoundingBoxIn);
			}
		}

		for (int j1 = 5; j1 < 11; ++j1) {
			for (int j2 = 2; j2 < 9; ++j2) {
				clearCurrentPositionBlocksUpwards(world, j2, 7, j1, structureBoundingBoxIn);
				replaceAirAndLiquidDownwards(world, iblockstate, j2, -1, j1, structureBoundingBoxIn);
			}
		}

		spawnVillagers(world, structureBoundingBoxIn, 4, 1, 2, 2);
		return true;
	}
}