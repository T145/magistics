package T145.magistics.api.logic;

import net.minecraft.util.EnumFacing;

public interface IFacing {

	boolean isHorizontalFacing();

	EnumFacing getFacing();

	void setFacing(EnumFacing side);
}