package T145.magistics.blocks;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.api.logic.IFacing;
import T145.magistics.api.logic.IWorker;
import T145.magistics.api.variants.IVariant;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
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

	public MBlockDevice(String name, Material material, Class variants) {
		super(name, material, variants);
	}

	public MBlockDevice(String name, Material material) {
		super(name, material);
	}

	public EnumFacing getFacing(TileEntity tile) {
		return tile instanceof IFacing ? ((IFacing) tile).getFacing(blockState.getBaseState()) : EnumFacing.NORTH;
	}

	public void setFacing(TileEntity tile, EnumFacing facing) {
		if (tile instanceof IFacing) {
			((IFacing) tile).setFacing(blockState.getBaseState(), facing);
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		BlockStateContainer variantContainer = super.createBlockState();
		List<IProperty> properties = new ArrayList<IProperty>();

		if (hasVariants()) {
			properties.addAll(variantContainer.getProperties());
		}

		if (this instanceof IFacing) {
			if (((IFacing) this).isHorizontalFacing()) {
				properties.add(IFacing.HORIZONTAL_FACING);
			} else {
				properties.add(IFacing.DIRECTIONAL_FACING);
			}
		}

		if (this instanceof IWorker) {
			properties.add(IWorker.WORKING);
		}

		return properties.isEmpty() ? super.createBlockState() : new BlockStateContainer(this, properties.toArray(new IProperty[properties.size()]));
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);

		if (this instanceof IFacing) {
			if (((IFacing) this).isHorizontalFacing()) {
				state = state.withProperty(IFacing.HORIZONTAL_FACING, getFacing(tile));
			} else {
				state = state.withProperty(IFacing.DIRECTIONAL_FACING, getFacing(tile));
			}
		}

		if (this instanceof IWorker) {
			state = state.withProperty(IWorker.WORKING, ((IWorker) tile).isWorking(blockState.getBaseState()));
		}

		return state;
	}

	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
		TileEntity tile = world.getTileEntity(pos);
		EnumFacing facing = getFacing(tile);
		setFacing(tile, facing.rotateAround(axis.getAxis()));
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		setFacing(world.getTileEntity(pos), EnumFacing.getDirectionFromEntityLiving(pos, placer));
	}
}