package T145.magistics.blocks.storage;

import T145.magistics.api.variants.EnumConduit;
import T145.magistics.blocks.MBlock;
import T145.magistics.client.render.BlockRenderer;
import T145.magistics.tiles.storage.TileConduit;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockConduit extends MBlock<EnumConduit> {

	protected static final AxisAlignedBB CONDUIT_AABB = new AxisAlignedBB(BlockRenderer.W4, BlockRenderer.W4, BlockRenderer.W4, 1D - BlockRenderer.W4, 1D - BlockRenderer.W4, 1D - BlockRenderer.W4);

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
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}