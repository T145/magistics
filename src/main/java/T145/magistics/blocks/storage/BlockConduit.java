package T145.magistics.blocks.storage;

import java.util.List;

import T145.magistics.api.variants.EnumConduit;
import T145.magistics.blocks.MBlock;
import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.tiles.storage.TileConduit;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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

public class BlockConduit extends MBlock<EnumConduit> {

	public static final AxisAlignedBB BOX_CENTER = new AxisAlignedBB(BlockRenderer.W4, BlockRenderer.W4, BlockRenderer.W4, 1D - BlockRenderer.W4, 1D - BlockRenderer.W4, 1D - BlockRenderer.W4);
	public static final AxisAlignedBB BOX_UP = new AxisAlignedBB(BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, 1D, BlockRenderer.W6 + BlockRenderer.W4);
	public static final AxisAlignedBB BOX_DOWN = new AxisAlignedBB(BlockRenderer.W6, 0D, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4);
	public static final AxisAlignedBB BOX_SOUTH = new AxisAlignedBB(BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, 1D);
	public static final AxisAlignedBB BOX_NORTH = new AxisAlignedBB(BlockRenderer.W6, BlockRenderer.W6, 0D, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6);
	public static final AxisAlignedBB BOX_EAST = new AxisAlignedBB(BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6, BlockRenderer.W6, 1.0F, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4);
	public static final AxisAlignedBB BOX_WEST = new AxisAlignedBB(0D, BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6, BlockRenderer.W6 + BlockRenderer.W4, BlockRenderer.W6 + BlockRenderer.W4);
	public static final AxisAlignedBB[] BOX_FACES = { BOX_DOWN, BOX_UP, BOX_NORTH, BOX_SOUTH, BOX_WEST, BOX_EAST };

	public BlockConduit() {
		super("conduit", Material.CIRCUITS, EnumConduit.class);
		setSoundType(SoundType.WOOD);
		setHardness(1F);
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
		TileConduit conduit = (TileConduit) world.getTileEntity(pos);
		return conduit != null && conduit.canConnect(facing);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entity, boolean isPistonMoving) {
		TileConduit conduit = (TileConduit) world.getTileEntity(pos);

		addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX_CENTER);

		if (conduit != null) {
			for (EnumFacing facing : EnumFacing.VALUES) {
				if (conduit.isConnected(facing)) {
					addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX_FACES[facing.ordinal()]);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos) {
		AxisAlignedBB box = BOX_CENTER;
		TileConduit conduit = (TileConduit) world.getTileEntity(pos);

		if (conduit != null) {
			for (EnumFacing facing : EnumFacing.VALUES) {
				if (conduit.isConnected(facing)) {
					box.intersect(BOX_FACES[facing.ordinal()]);
				}
			}
		}

		return box;
	}
}