package T145.magistics.api.tiles;

import net.minecraft.entity.player.EntityPlayer;

public interface IOwned {
	public EntityPlayer getOwner();
	public void setOwner(EntityPlayer player);
	public boolean isOwned();
	public boolean isOwnedBy(EntityPlayer player);
}