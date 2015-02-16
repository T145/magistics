package T145.magistics.common.tiles.pillars;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import T145.magistics.common.Magistics;

public class TilePillarTurntable extends TileBase {
	private ItemStack record;
	public boolean showNum = false, isEmpty = true;
	public float rot = 0F;

	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			rot += 4F;
			if (rot >= 360F)
				rot -= 360F;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbtlist = nbt.getTagList("Items", 10);

		for (int i = 0; i < nbtlist.tagCount(); i++) {
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
			int j = nbtslot.getByte("Slot") & 255;

			if ((j >= 0)) {
				record = ItemStack.loadItemStackFromNBT(nbtslot);
				if (record != null)
					isEmpty = false;
				else
					isEmpty = true;
			}
		}
		isEmpty = nbt.getBoolean("isEmpty");
		showNum = nbt.getBoolean("showNum");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList nbtlist = new NBTTagList();

		if (getDisk() != null) {
			NBTTagCompound nbtslot = new NBTTagCompound();
			nbtslot.setByte("Slot", (byte) 0);
			getDisk().writeToNBT(nbtslot);
			nbtlist.appendTag(nbtslot);
		}

		nbt.setTag("Items", nbtlist);
		nbt.setBoolean("isEmpty", isEmpty);
		nbt.setBoolean("showNum", showNum);
	}

	@Override
	public void onInventoryChanged() {
		super.onInventoryChanged();
		if (worldObj != null && !worldObj.isRemote)
			Magistics.proxy.sendToPlayers(getDescriptionPacket(), worldObj, xCoord, yCoord, zCoord, 64);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return isUseableByPlayer(player);
	}

	public ItemStack getDisk() {
		return isEmpty ? null : record;
	}

	public void setDisk(ItemStack item) {
		if (item == null)
			isEmpty = true;
		else
			isEmpty = false;
		record = item;

		onInventoryChanged();
	}
}