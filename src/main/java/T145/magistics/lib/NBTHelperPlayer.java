package T145.magistics.lib;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public class NBTHelperPlayer {
	public long playerUUIDMost;
	public long playerUUIDLeast;
	public String playerName;
	public boolean isPublic;
	public UUID playerUUID;

	public NBTHelperPlayer() {
		this.playerUUIDMost = 0;
		this.playerUUIDLeast = 0;
		this.playerName = "";
		this.isPublic = false;
	}

	public static boolean nbtHasPlayerTag(NBTTagCompound nbt) {
		if (nbt == null || nbt.hasKey("Player", Constants.NBT.TAG_COMPOUND) == false) {
			return false;
		}

		NBTTagCompound tag = nbt.getCompoundTag("Player");

		if (tag != null && tag.hasKey("UUIDM", Constants.NBT.TAG_LONG) && tag.hasKey("UUIDL", Constants.NBT.TAG_LONG) && tag.hasKey("Name", Constants.NBT.TAG_STRING)) {
			return true;
		}

		return false;
	}

	public static boolean itemHasPlayerTag(ItemStack stack) {
		return (stack != null && nbtHasPlayerTag(stack.getTagCompound()));
	}

	public NBTTagCompound readFromNBT(NBTTagCompound nbt) {
		if (nbtHasPlayerTag(nbt) == false) {
			return null;
		}

		NBTTagCompound tag = nbt.getCompoundTag("Player");
		this.playerUUIDMost = tag.getLong("UUIDM");
		this.playerUUIDLeast = tag.getLong("UUIDL");
		this.playerName = tag.getString("Name");
		this.isPublic = tag.getBoolean("Public");
		this.playerUUID = new UUID(this.playerUUIDMost, this.playerUUIDLeast);

		return tag;
	}

	public String getPlayerName() {
		if (this.playerName == null) {
			this.playerName = "";
		}

		// FIXME we should get the player name from the UUID
		return this.playerName;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return writePlayerTagToNBT(nbt, this.playerUUIDMost, this.playerUUIDLeast, this.playerName, this.isPublic);
	}

	public boolean writeToItem(ItemStack stack) {
		if (stack != null) {
			stack.setTagCompound(this.writeToNBT(stack.getTagCompound()));
			return true;
		}

		return false;
	}

	public static NBTTagCompound writePlayerTagToNBT(NBTTagCompound nbt, long most, long least, String name,
			boolean isPublic) {
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}

		NBTTagCompound tag = new NBTTagCompound();
		tag.setLong("UUIDM", most);
		tag.setLong("UUIDL", least);
		tag.setString("Name", name);
		tag.setBoolean("Public", isPublic);
		nbt.setTag("Player", tag);

		return nbt;
	}

	public static NBTTagCompound writePlayerTagToNBT(NBTTagCompound nbt, EntityPlayer player, boolean isPublic) {
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}

		if (player == null) {
			nbt.removeTag("Player");
			return nbt;
		}

		return writePlayerTagToNBT(nbt, player.getUniqueID().getMostSignificantBits(), player.getUniqueID().getLeastSignificantBits(), player.getName(), isPublic);
	}

	public static NBTTagCompound writePlayerTagToNBT(NBTTagCompound nbt, EntityPlayer player) {
		return writePlayerTagToNBT(nbt, player, true);
	}

	public static void writePlayerTagToItem(ItemStack stack, EntityPlayer player, boolean isPublic) {
		if (stack != null) {
			stack.setTagCompound(writePlayerTagToNBT(stack.getTagCompound(), player, isPublic));
		}
	}

	public static NBTHelperPlayer getPlayerDataFromNBT(NBTTagCompound nbt) {
		NBTHelperPlayer player = new NBTHelperPlayer();
		if (player.readFromNBT(nbt) != null) {
			return player;
		}

		return null;
	}

	public static NBTHelperPlayer getPlayerDataFromItem(ItemStack stack) {
		if (stack != null) {
			return getPlayerDataFromNBT(stack.getTagCompound());
		}

		return null;
	}

	public boolean isOwner(EntityPlayer player) {
		if (player == null) {
			return false;
		}

		// FIXME verify that this would work: if (this.playerUUID != null &&  this.playerUUID.equals(player.getUniqueID()))
		if (this.playerUUIDMost == player.getUniqueID().getMostSignificantBits() && this.playerUUIDLeast == player.getUniqueID().getLeastSignificantBits()) {
			return true;
		}

		return false;
	}

	public boolean canAccess(EntityPlayer player) {
		return (this.isPublic || this.isOwner(player));
	}

	public static boolean isOwnerOfItem(ItemStack stack, EntityPlayer player) {
		if (stack != null && stack.getTagCompound() != null) {
			NBTHelperPlayer playerData = new NBTHelperPlayer();
			if (playerData.readFromNBT(stack.getTagCompound()) != null) {
				return playerData.isOwner(player);
			}
		}

		return false;
	}

	/**
	 * Check if the given player is allowed to access this item. Returns true if
	 * there is no player information stored yet, or if the privacy mode is set
	 * to Public, or if the given player is the owner.
	 */
	public static boolean canAccessItem(ItemStack stack, EntityPlayer player) {
		if (stack == null) {
			return false;
		}

		if (stack.getTagCompound() == null) {
			return true;
		}

		NBTHelperPlayer playerData = getPlayerDataFromItem(stack);
		return (playerData == null || playerData.isPublic  || playerData.isOwner(player));
	}
}