package T145.magistics.api.logic;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;

public interface IFacing {

	static final PropertyDirection HORIZONTAL_FACING = BlockHorizontal.FACING;
	static final PropertyDirection DIRECTIONAL_FACING = BlockDirectional.FACING;

	boolean isHorizontalFacing();

	EnumFacing getFacing();

	void setFacing(EnumFacing facing);
}