package T145.magistics.blocks.storage;

import T145.magistics.api.variants.blocks.EnumTank;
import T145.magistics.blocks.MBlock;
import T145.magistics.client.lib.Render;
import T145.magistics.tiles.storage.TileTank;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTank extends MBlock<EnumTank> {

	public static final AxisAlignedBB TANK_AABB = new AxisAlignedBB(Render.W1, 0D, Render.W1, 1D - Render.W1, 1D, 1D - Render.W1);

	public BlockTank() {
		super("tank", Material.GLASS, EnumTank.class);
		setSoundType(SoundType.GLASS);
		setHardness(1F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileTank(meta == 1);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return TANK_AABB;
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

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess world, BlockPos pos, EnumFacing side) {
		if (side.getAxis() == EnumFacing.Axis.Y) {
			IBlockState stateBelow = world.getBlockState(pos.offset(side));
			return !(stateBelow.getBlock() instanceof BlockTank && stateBelow == blockState);
		} else {
			return true;
		}
	}
}