package T145.magistics.blocks.storage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;

import T145.magistics.blocks.MBlock;
import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.tiles.storage.TileConduit;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockConduit extends MBlock {

	public static final ImmutableList<IProperty<Boolean>> CONNECTIONS = ImmutableList.copyOf(Stream.of(EnumFacing.VALUES).map(facing -> PropertyBool.create(facing.getName())).collect(Collectors.toList()));
	public static final AxisAlignedBB BOX_CENTER = new AxisAlignedBB(BlockRenderer.W4, BlockRenderer.W4, BlockRenderer.W4, 1D - BlockRenderer.W4, 1D - BlockRenderer.W4, 1D - BlockRenderer.W4);
	public static final AxisAlignedBB BOX_UP = new AxisAlignedBB(BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, 1D, BlockRenderer.W6 + BlockRenderer.W4);
	public static final AxisAlignedBB BOX_DOWN = new AxisAlignedBB(BlockRenderer.W6, 0D, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4);
	public static final AxisAlignedBB BOX_SOUTH = new AxisAlignedBB(BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, 1D);
	public static final AxisAlignedBB BOX_NORTH = new AxisAlignedBB(BlockRenderer.W6, BlockRenderer.W6, 0D, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6);
	public static final AxisAlignedBB BOX_EAST = new AxisAlignedBB(BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6, 1.0F, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4);
	public static final AxisAlignedBB BOX_WEST = new AxisAlignedBB(0D, BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4);
	public static final AxisAlignedBB[] BOX_FACES = { BOX_DOWN, BOX_UP, BOX_NORTH, BOX_SOUTH, BOX_WEST, BOX_EAST };

	public BlockConduit() {
		super("conduit", Material.CIRCUITS);
		setSoundType(SoundType.WOOD);
		setHardness(1F);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CONNECTIONS.toArray(new IProperty[CONNECTIONS.size()]));
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof TileConduit) {
			TileConduit conduit = (TileConduit) tile;

			for (EnumFacing facing : EnumFacing.VALUES) {
				state = state.withProperty(CONNECTIONS.get(facing.getIndex()), conduit.isConnected(facing));
			}
		}

		return state;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileConduit();
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
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.CUTOUT_MIPPED || layer == BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		TileEntity tile = world.getTileEntity(pos);
		return tile instanceof TileConduit && ((TileConduit) tile).canConnect(facing);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entity, boolean isPistonMoving) {
		AxisAlignedBB box = BOX_CENTER;
		TileConduit conduit = (TileConduit) world.getTileEntity(pos);

		if (conduit != null) {
			for (EnumFacing facing : EnumFacing.VALUES) {
				if (conduit.isConnected(facing)) {
					box = box.union(BOX_FACES[facing.ordinal()]);
				}
			}
		}

		addCollisionBoxToList(pos, entityBox, collidingBoxes, box);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos) {
		AxisAlignedBB box = BOX_CENTER;
		TileConduit conduit = (TileConduit) world.getTileEntity(pos);

		if (conduit != null) {
			for (EnumFacing facing : EnumFacing.VALUES) {
				if (conduit.isConnected(facing)) {
					box = box.union(BOX_FACES[facing.ordinal()]);
				}
			}
		}

		return box;
	}
}