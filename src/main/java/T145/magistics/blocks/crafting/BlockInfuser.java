package T145.magistics.blocks.crafting;

import T145.magistics.Magistics;
import T145.magistics.api.logic.IFacing;
import T145.magistics.api.variants.blocks.EnumInfuser;
import T145.magistics.blocks.MBlock;
import T145.magistics.client.lib.BlockRenderer;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.crafting.TileInfuserDark;
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

	public static final AxisAlignedBB INFUSER_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D - BlockRenderer.W1, 1D);

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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && hand.equals(EnumHand.MAIN_HAND)) {
			TileInfuser infuser = (TileInfuser) world.getTileEntity(pos);

			if (infuser != null) {
				player.openGui(Magistics.MODID, 0, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile != null && tile instanceof IFacing) {
			((IFacing) tile).setFacing(EnumFacing.getDirectionFromEntityLiving(pos, placer));
		}
	}
}