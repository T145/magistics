package T145.magistics.api.tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileOwned extends TileMagistics {

	public String owner = "";
	public List<String> accessList = new ArrayList<String>();
	public boolean safeToRemove = false;

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		owner = tag.getString("owner");

		NBTTagList accessTags = tag.getTagList("access", 10);
		accessList = new ArrayList<String>();

		for (int i = 0; i < accessTags.tagCount(); ++i) {
			NBTTagCompound accessTag = accessTags.getCompoundTagAt(i);
			accessList.add(accessTag.getString("name"));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		NBTTagList accessTags = new NBTTagList();

		for (int i = 0; i < accessList.size(); ++i) {
			NBTTagCompound accessTag = new NBTTagCompound();
			accessTag.setString("name", accessList.get(i));
			accessTags.appendTag(accessTag);
		}

		tag.setTag("access", accessTags);

		return tag;
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		owner = tag.getString("owner");
	}

	@Override
	public NBTTagCompound writeCustomNBT(NBTTagCompound tag) {
		tag.setString("owner", owner);
		return tag;
	}
}