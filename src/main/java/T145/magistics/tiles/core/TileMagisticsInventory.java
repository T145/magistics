package T145.magistics.tiles.core;

import T145.magistics.api.tiles.TileMagistics;
import T145.magistics.lib.utils.InventoryHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class TileMagisticsInventory extends TileMagistics implements IInventory {
	protected ItemStack[] inventoryStacks;
	protected int customInvSize = 0;

	public TileMagisticsInventory(int invSize, boolean canFace, boolean canOwn) {
		inventoryStacks = new ItemStack[invSize];
		canBeFacing = canFace;
		canBeOwned = canOwn;
	}

	public TileMagisticsInventory(int invSize) {
		this(invSize, false, false);
	}

	public TileMagisticsInventory(int invSize, int customSize, boolean canFace, boolean canOwn) {
		inventoryStacks = new ItemStack[invSize];
		customInvSize = customSize;
		canBeFacing = canFace;
		canBeOwned = canOwn;
	}

	public TileMagisticsInventory(int invSize, int customSize) {
		this(invSize, customSize, false, false);
	}

	@Override
	public int getSizeInventory() {
		return customInvSize > 0 ? customInvSize : inventoryStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventoryStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		return InventoryHelper.decrStackSize(this, slot, size);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return InventoryHelper.getStackInSlotOnClosing(this, slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		InventoryHelper.setInventorySlotContents(this, inventoryStacks, slot, stack);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		NBTTagList items = tag.getTagList("Items", 10);
		inventoryStacks = new ItemStack[getSizeInventory()];

		for (int slot = 0; slot < items.tagCount(); ++slot) {
			NBTTagCompound itemNBT = items.getCompoundTagAt(slot);
			int i = itemNBT.getByte("Slot") & 0xFF;

			if (i >= 0 && i < inventoryStacks.length) {
				inventoryStacks[i] = ItemStack.loadItemStackFromNBT(itemNBT);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		NBTTagList items = new NBTTagList();

		for (int slot = 0; slot < inventoryStacks.length; ++slot) {
			if (inventoryStacks[slot] != null) {
				NBTTagCompound itemNBT = new NBTTagCompound();
				itemNBT.setByte("Slot", (byte) slot);
				inventoryStacks[slot].writeToNBT(itemNBT);
				items.appendTag(itemNBT);
			}
		}

		tag.setTag("Items", items);
	}

	@Override
	public abstract String getInventoryName();

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public void invalidate() {
		updateContainingBlockInfo();
		super.invalidate();
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}
}