package T145.magistics.blocks;

import T145.magistics.api.enums.EnumConduit;
import T145.magistics.client.render.BlockRenderer;
import T145.magistics.tiles.TileConduit;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockConduit extends MBlock<EnumConduit> implements ITileEntityProvider {

	protected static final AxisAlignedBB BLOCK_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D - BlockRenderer.W1, 1D);

	public BlockConduit() {
		super("conduit", Material.CIRCUITS, EnumConduit.class);

		GameRegistry.registerTileEntity(TileConduit.class, TileConduit.class.getSimpleName());
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
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
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileConduit();
	}
}