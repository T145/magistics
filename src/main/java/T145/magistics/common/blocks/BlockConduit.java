package T145.magistics.common.blocks;

import static T145.magistics.client.lib.Render.W4;
import static T145.magistics.client.lib.Render.W6;

import java.util.EnumMap;

import T145.magistics.api.magic.QuintHelper;
import T145.magistics.common.blocks.base.BlockBase;
import T145.magistics.common.tiles.TileConduit;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockConduit extends BlockBase implements ITileEntityProvider {

	private static final EnumMap<EnumFacing, IProperty<Boolean>> CONNECTIONS = new EnumMap<>(EnumFacing.class);

	static {
		for (EnumFacing facing : EnumFacing.VALUES) {
			CONNECTIONS.put(facing, PropertyBool.create(facing.getName()));
		}
	}

	public static final AxisAlignedBB BOX_CENTER = new AxisAlignedBB(W4, W4, W4, 1D - W4, 1D - W4, 1D - W4);
	public static final AxisAlignedBB BOX_UP = new AxisAlignedBB(W6, W6 + W4, W6, W6 + W4, 1D, W6 + W4);
	public static final AxisAlignedBB BOX_DOWN = new AxisAlignedBB(W6, 0D, W6, W6 + W4, W6, W6 + W4);
	public static final AxisAlignedBB BOX_SOUTH = new AxisAlignedBB(W6, W6, W6 + W4, W6 + W4, W6 + W4, 1D);
	public static final AxisAlignedBB BOX_NORTH = new AxisAlignedBB(W6, W6, 0D, W6 + W4, W6 + W4, W6);
	public static final AxisAlignedBB BOX_EAST = new AxisAlignedBB(W6 + W4, W6, W6, 1.0F, W6 + W4, W6 + W4);
	public static final AxisAlignedBB BOX_WEST = new AxisAlignedBB(0D, W6, W6, W6, W6 + W4, W6 + W4);
	public static final AxisAlignedBB[] BOX_FACES = { BOX_DOWN, BOX_UP, BOX_NORTH, BOX_SOUTH, BOX_WEST, BOX_EAST };

	public BlockConduit() {
		super("conduit", Material.CIRCUITS, false);
		setSoundType(SoundType.WOOD);
		setHardness(1F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileConduit();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		IProperty[] props = new IProperty[CONNECTIONS.size()];
		return new BlockStateContainer(this, CONNECTIONS.values().toArray(props));
	}

	public boolean isConnected(final IBlockState ownState, final EnumFacing side) {
		return ownState.getValue(CONNECTIONS.get(side));
	}

	private boolean canConnectTo(final IBlockState ownState, final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
		return QuintHelper.getConnectedHandler(world, pos, side) != null;
	}

	@Override
	public IBlockState getActualState(IBlockState state, final IBlockAccess world, final BlockPos pos) {
		for (final EnumFacing facing : EnumFacing.VALUES) {
			state = state.withProperty(CONNECTIONS.get(facing), canConnectTo(state, world, pos, facing));
		}
		return state;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOX_CENTER;
	}

	/*
	@Override
	public void addCollisionBoxToList(IBlockState state, final World worldIn, final BlockPos pos, final AxisAlignedBB entityBox, final List<AxisAlignedBB> collidingBoxes, @Nullable final Entity entityIn, final boolean p_185477_7_) {
		final AxisAlignedBB bb = new AxisAlignedBB(PIPE_MIN_POS, PIPE_MIN_POS, PIPE_MIN_POS, PIPE_MAX_POS, PIPE_MAX_POS, PIPE_MAX_POS);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, bb);

		if (!p_185477_7_) {
			state = state.getActualState(worldIn, pos);
		}

		for (final EnumFacing facing : EnumFacing.VALUES) {
			if (isConnected(state, facing)) {
				final AxisAlignedBB axisAlignedBB = CONNECTED_BOUNDING_BOXES.get(facing.getIndex());
				addCollisionBoxToList(pos, entityBox, collidingBoxes, axisAlignedBB);
			}
		}
	}*/
}