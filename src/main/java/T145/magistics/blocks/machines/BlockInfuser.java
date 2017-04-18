package T145.magistics.blocks.machines;

import T145.magistics.Magistics;
import T145.magistics.api.variants.EnumInfuser;
import T145.magistics.blocks.MBlock;
import T145.magistics.client.render.BlockRenderer;
import T145.magistics.tiles.machines.TileInfuser;
import T145.magistics.tiles.machines.TileInfuserDark;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInfuser extends MBlock<EnumInfuser> {

	protected static final AxisAlignedBB INFUSER_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D - BlockRenderer.W1, 1D);

	public BlockInfuser() {
		super("infuser", Material.ROCK, EnumInfuser.class);
		setSoundType(SoundType.STONE);
		setHardness(2F);
		setResistance(15F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return meta == 1 ? new TileInfuserDark() : new TileInfuser();
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
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileInfuser infuser = (TileInfuser) world.getTileEntity(pos);

		if (infuser != null) {
			infuser.setFacingFromEntity(placer);
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && hand.equals(EnumHand.MAIN_HAND)) {
			TileInfuser infuser = (TileInfuser) world.getTileEntity(pos);

			if (infuser != null) {
				player.openGui(Magistics.MODID, getMetaFromState(state), world, pos.getX(), pos.getY(), pos.getZ());
			}
		}

		return true;
	}
}