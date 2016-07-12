package T145.magistics.api.tiles;

import java.util.ArrayList;
import java.util.UUID;

import T145.magistics.lib.NBTHelperPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.TileThaumcraft;

public class TileMagistics extends TileThaumcraft {
	protected boolean canBeFacing;
	protected boolean canBeOwned;

	public int facing = 0;
	public String ownerName = "";
	public UUID ownerUUID = null;
	public ArrayList<String> accessList = new ArrayList();
	public boolean isPublic = false;

	public TileMagistics(boolean canFace, boolean canOwn) {
		canBeFacing = canFace;
		canBeOwned = canOwn;
	}

	public TileMagistics() {
		this(false, false);
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		ownerName = tag.getString("owner");
		NBTTagList owners = tag.getTagList("access", 10);
		accessList = new ArrayList();

		for (int i = 0; i < owners.tagCount(); ++i) {
			NBTTagCompound ownerNBT = owners.getCompoundTagAt(i);
			accessList.add(ownerNBT.getString("name"));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		NBTTagList owners = new NBTTagList();

		for (int i = 0; i < accessList.size(); ++i) {
			NBTTagCompound ownerNBT = new NBTTagCompound();
			ownerNBT.setString("name", accessList.get(i));
			owners.appendTag(ownerNBT);
		}

		tag.setTag("access", owners);
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		if (canBeFacing) {
			facing = tag.getByte("facing");
		}

		if (canBeOwned) {
			NBTHelperPlayer playerData = NBTHelperPlayer.getPlayerDataFromNBT(tag);

			if (playerData != null) {
				ownerUUID = new UUID(playerData.playerUUIDMost, playerData.playerUUIDLeast);
				ownerName = playerData.playerName;
				isPublic = playerData.isPublic;
			}
		}
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		if (canBeFacing) {
			tag.setByte("facing", (byte) facing);
		}

		if (canBeOwned && ownerUUID != null && ownerName != null) {
			NBTHelperPlayer.writePlayerTagToNBT(tag, ownerUUID.getMostSignificantBits(), ownerUUID.getLeastSignificantBits(), ownerName, isPublic);
		}
	}
}