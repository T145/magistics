package T145.magistics.api.tiles;

import net.minecraft.entity.EntityLivingBase;

public interface IFacing {

	public int getFacing();
	public int getFacingFromEntity(EntityLivingBase placer);

	public void setFacing(int front);
	public void setFacingFromEntity(EntityLivingBase placer);
}