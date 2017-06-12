package T145.magistics.blocks.cosmetic;

import javax.annotation.Nullable;

import T145.magistics.blocks.MBlock;
import T145.magistics.tiles.cosmetic.TileNitor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockNitor extends MBlock<EnumDyeColor> {

	public BlockNitor() {
		super("nitor", Material.CIRCUITS, EnumDyeColor.class);
		setHardness(0.1F);
		setSoundType(SoundType.CLOTH);
		setLightLevel(1F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileNitor();
	}

	@Override
	public MapColor getMapColor(IBlockState state) {
		return state.getValue(variant).getMapColor();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.33D, 0.33D, 0.33D, 0.66D, 0.66D, 0.66D);
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return null;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}