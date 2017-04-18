package T145.magistics.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;

public interface IFacing {

	public EnumFacing getFacing();

	public void setFacing(EnumFacing facing);

	public void setFacingFromEntity(EntityLivingBase placer);
}