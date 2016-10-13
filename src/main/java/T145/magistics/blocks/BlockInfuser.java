package T145.magistics.blocks;

import T145.magistics.api.blocks.IBlockMagistics;
import T145.magistics.client.render.BlockRenderer;
import T145.magistics.tiles.TileInfuser;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInfuser extends BlockMagistics implements ITileEntityProvider {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	protected static final AxisAlignedBB INFUSER_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D - BlockRenderer.W1, 1D);

	public BlockInfuser() {
		super(Material.ROCK, InfuserType.class, SoundType.STONE);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return INFUSER_AABB;
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
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileInfuser infuser = (TileInfuser) world.getTileEntity(pos);

		if (infuser != null) {
			infuser.setFacing(BlockPistonBase.getFacingFromEntity(pos, placer).getIndex());
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileInfuser();
	}

	public static enum InfuserType implements IBlockMagistics {
		LIGHT, DARK;

		@Override
		public String getName() {
			return name().toLowerCase();
		}

		@Override
		public String toString() {
			return getName();
		}
	}
}