package T145.magistics.blocks;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.api.logic.IDirectionalFacing;
import T145.magistics.api.logic.IFacing;
import T145.magistics.api.logic.IHorizontalFacing;
import T145.magistics.api.variants.IVariant;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class MBlockDevice<T extends Enum<T> & IVariant> extends MBlock<T> {

	public static final PropertyBool ACTIVE = PropertyBool.create("active");

	public MBlockDevice(String name, Material material, Class variants) {
		super(name, material, variants);
		setDefaultState(blockState.getBaseState().withProperty(ACTIVE, false));
	}

	public MBlockDevice(String name, Material material) {
		super(name, material);
	}

	public EnumFacing getFacing(IBlockAccess world, BlockPos pos) {
		final TileEntity tile = world.getTileEntity(pos);
		return tile instanceof IFacing ? ((IFacing) tile).getFacing() : EnumFacing.NORTH;
	}

	public void setFacing(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		final TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof IFacing) {
			((IFacing) tile).setFacing(facing);
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		BlockStateContainer variantContainer = super.createBlockState();
		List<IProperty> properties = new ArrayList<IProperty>();

		if (hasVariants()) {
			properties.addAll(variantContainer.getProperties());
		}

		if (this instanceof IHorizontalFacing) {
			properties.add(IHorizontalFacing.FACING);
		}

		if (this instanceof IDirectionalFacing) {
			properties.add(IDirectionalFacing.FACING);
		}

		properties.add(ACTIVE);

		return properties.isEmpty() ? super.createBlockState() : new BlockStateContainer(this, properties.toArray(new IProperty[properties.size()]));
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		if (this instanceof IHorizontalFacing) {
			state = state.withProperty(IHorizontalFacing.FACING, getFacing(world, pos));
		}

		if (this instanceof IDirectionalFacing) {
			state = state.withProperty(IDirectionalFacing.FACING, getFacing(world, pos));
		}

		return state;
	}

	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
		final EnumFacing facing = getFacing(world, pos);
		setFacing(world, pos, facing.rotateAround(axis.getAxis()));
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		setFacing(world, pos, EnumFacing.getDirectionFromEntityLiving(pos, placer));
	}
}