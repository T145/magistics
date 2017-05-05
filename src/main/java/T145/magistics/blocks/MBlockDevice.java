package T145.magistics.blocks;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.api.logic.IFacing;
import T145.magistics.api.logic.IHorizontalFacing;
import T145.magistics.lib.managers.BlockStateManager;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class MBlockDevice extends MBlock {

	public static final PropertyBool ACTIVE = PropertyBool.create("active");

	public MBlockDevice(String name, Material material) {
		super(name, material);

		IBlockState defaultState = blockState.getBaseState();

		if (this instanceof IFacing) {
			defaultState.withProperty(IFacing.FACING, EnumFacing.UP);
		}

		if (this instanceof IHorizontalFacing) {
			defaultState.withProperty(IHorizontalFacing.FACING, EnumFacing.NORTH);
		}

		setDefaultState(defaultState.withProperty(ACTIVE, false));
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		IBlockState defaultState = getDefaultState();

		if (this instanceof IHorizontalFacing) {
			defaultState.withProperty(IHorizontalFacing.FACING, placer.isSneaking() ? placer.getHorizontalFacing() : placer.getHorizontalFacing().getOpposite());
		}

		if (this instanceof IFacing) {
			defaultState.withProperty(IFacing.FACING, placer.isSneaking() ? EnumFacing.getDirectionFromEntityLiving(pos, placer).getOpposite() : EnumFacing.getDirectionFromEntityLiving(pos, placer));
		}

		return defaultState.withProperty(ACTIVE, false);
	}

	public void updateFacing(World world, BlockPos pos, EnumFacing face) {
		if (this instanceof IFacing || this instanceof IHorizontalFacing) {
			if (face == BlockStateManager.getFacing(world.getBlockState(pos))) {
				return;
			}

			if (this instanceof IHorizontalFacing && face.getHorizontalIndex() >= 0) {
				world.setBlockState(pos, world.getBlockState(pos).withProperty(IHorizontalFacing.FACING, face), 3);
			}

			if (this instanceof IFacing) {
				world.setBlockState(pos, world.getBlockState(pos).withProperty(IFacing.FACING, face), 3);
			}
		}
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		updateFacing(world, pos, EnumFacing.getDirectionFromEntityLiving(pos, placer));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState defaultState = getDefaultState();

		if (this instanceof IHorizontalFacing) {
			defaultState.withProperty(IHorizontalFacing.FACING, BlockStateManager.getFacing(meta));
		}

		if (this instanceof IFacing) {
			defaultState.withProperty(IFacing.FACING, BlockStateManager.getFacing(meta));
		}

		return defaultState.withProperty(ACTIVE, BlockStateManager.isActive(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		byte mod = 0;
		int meta = (this instanceof IFacing) ? mod | state.getValue(IFacing.FACING).getIndex() : (this instanceof IHorizontalFacing) ? mod | state.getValue(IHorizontalFacing.FACING).getIndex() : mod;

		if (!state.getValue(ACTIVE)) {
			meta |= 0x8;
		}

		return meta;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		List<IProperty> properties = new ArrayList<IProperty>();

		if (this instanceof IHorizontalFacing) {
			properties.add(IHorizontalFacing.FACING);
		}

		if (this instanceof IFacing) {
			properties.add(IFacing.FACING);
		}

		properties.add(ACTIVE);
		return properties.size() == 0 ? super.createBlockState() : new BlockStateContainer(this, properties.toArray(new IProperty[properties.size()]));
	}
}