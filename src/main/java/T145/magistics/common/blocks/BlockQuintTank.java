package T145.magistics.common.blocks;

import static T145.magistics.client.lib.Render.W1;

import T145.magistics.common.blocks.base.BlockBase;
import T145.magistics.common.tiles.TileQuintTank;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
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

public class BlockQuintTank extends BlockBase implements ITileEntityProvider {

	public static final AxisAlignedBB TANK_AABB = new AxisAlignedBB(W1, 0D, W1, 1D - W1, 1D, 1D - W1);
	public static final PropertyBool JOINED_ABOVE = PropertyBool.create("joined_above");
	public static final PropertyBool JOINED_BELOW = PropertyBool.create("joined_below");
	public static final PropertyBool INBETWEEN = PropertyBool.create("inbetween");

	public BlockQuintTank() {
		super("tank", Material.GLASS, false);
		setSoundType(SoundType.GLASS);
		setHardness(1F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileQuintTank();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, JOINED_ABOVE, JOINED_BELOW, INBETWEEN);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileQuintTank tank = (TileQuintTank) world.getTileEntity(pos);

		if (tank != null) {
			boolean joinedAbove = tank.canConnectToTank(EnumFacing.UP);
			boolean joinedBelow = tank.canConnectToTank(EnumFacing.DOWN);
			state = state.withProperty(JOINED_ABOVE, joinedAbove).withProperty(JOINED_BELOW, joinedBelow).withProperty(INBETWEEN, joinedAbove && joinedBelow);
		}

		return state;
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		return 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return TANK_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return side.getAxis() != EnumFacing.Axis.Y || !(world.getBlockState(pos.offset(side)).getBlock() instanceof BlockQuintTank);
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
		TileQuintTank tank = (TileQuintTank) world.getTileEntity(pos);

		if (tank != null) {
			return (int) (tank.getQuints() * 15 / tank.getCapacity());
		}

		return 0;
	}
}