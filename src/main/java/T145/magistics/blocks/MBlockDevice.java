package T145.magistics.blocks;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.api.logic.IFacing;
import T145.magistics.api.logic.IWorker;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class MBlockDevice<T extends Enum<T> & IStringSerializable> extends MBlock<T> {

	public MBlockDevice(String name, Material material, Class variants) {
		super(name, material, variants);

		IBlockState state = getDefaultState();

		if (this instanceof IFacing) {
			IFacing orientable = (IFacing) this;

			if (orientable.isHorizontalFacing()) {
				state = state.withProperty(IFacing.HORIZONTAL_FACING, EnumFacing.NORTH);
			} else {
				state = state.withProperty(IFacing.DIRECTIONAL_FACING, EnumFacing.UP);
			}
		}

		if (this instanceof IWorker) {
			state = state.withProperty(IWorker.WORKING, false);
		}

		setDefaultState(state);
	}

	public MBlockDevice(String name, Material material) {
		this(name, material, Object.class);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		BlockStateContainer variantContainer = super.createBlockState();
		List<IProperty> properties = new ArrayList<IProperty>();

		if (hasVariants()) {
			properties.addAll(variantContainer.getProperties());
		}

		if (this instanceof IFacing) {
			IFacing orientable = (IFacing) this;
			properties.add(orientable.isHorizontalFacing() ? IFacing.HORIZONTAL_FACING : IFacing.DIRECTIONAL_FACING);
		}

		if (this instanceof IWorker) {
			properties.add(IWorker.WORKING);
		}

		return properties.isEmpty() ? super.createBlockState() : new BlockStateContainer(this, properties.toArray(new IProperty[properties.size()]));
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);

		if (this instanceof IFacing && tile instanceof IFacing) {
			IFacing orientableBlock = (IFacing) this;
			IFacing orientableTile = (IFacing) tile;

			if (orientableBlock.isHorizontalFacing() && orientableTile.isHorizontalFacing()) {
				state = state.withProperty(IFacing.HORIZONTAL_FACING, orientableTile.getFacing());
			} else {
				state = state.withProperty(IFacing.DIRECTIONAL_FACING, orientableTile.getFacing());
			}
		}

		if (this instanceof IWorker && tile instanceof IWorker) {
			IWorker worker = (IWorker) tile;
			state = state.withProperty(IWorker.WORKING, worker.isWorking());
		}

		return state;
	}

	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing facing) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof IFacing) {
			IFacing orientable = (IFacing) tile;
			orientable.setFacing(facing);
			return true;
		}

		return false;
	}
}