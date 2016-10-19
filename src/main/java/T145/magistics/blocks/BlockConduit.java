package T145.magistics.blocks;

import T145.magistics.client.render.BlockRenderer;
import T145.magistics.tiles.TileConduit;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockConduit extends BlockContainer {

	protected static final AxisAlignedBB CONDUIT_AABB = new AxisAlignedBB(BlockRenderer.W4, BlockRenderer.W4, BlockRenderer.W4, 1F - BlockRenderer.W4, 1F - BlockRenderer.W4, 1F - BlockRenderer.W4);

	public BlockConduit() {
		super(Material.CIRCUITS);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CONDUIT_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileConduit();
	}
}