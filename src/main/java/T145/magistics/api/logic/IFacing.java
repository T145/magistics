package T145.magistics.api.logic;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;

public interface IFacing {

	public static final PropertyDirection FACING = BlockDirectional.FACING;

	public EnumFacing getFacing();

	public void setFacing(EnumFacing facing);
}