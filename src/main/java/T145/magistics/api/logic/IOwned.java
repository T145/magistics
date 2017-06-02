package T145.magistics.api.logic;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayer;

public interface IOwned {

	EntityPlayer getOwner();

	GameProfile getOwnerProfile();

	void setOwner(EntityPlayer player);

	boolean isOwned();

	boolean isOwnedBy(EntityPlayer player);
}