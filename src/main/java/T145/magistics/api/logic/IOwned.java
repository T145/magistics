package T145.magistics.api.logic;

import net.minecraft.entity.player.EntityPlayer;

public interface IOwned {

	EntityPlayer getOwner();

	void setOwner(EntityPlayer player);

	boolean isOwned();

	boolean isOwnedBy(EntityPlayer player);
}