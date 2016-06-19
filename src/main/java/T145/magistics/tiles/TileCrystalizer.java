package T145.magistics.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.config.ConfigItems;
import T145.magistics.items.ItemShardDull;
import T145.magistics.lib.InventoryHelper;

public class TileCrystalizer extends TileThaumcraft implements ISidedInventory {
	private ItemStack[] inventoryStacks = new ItemStack[10];
	public float crystalTime = 0F;
	public float maxTime = 30F;
	public int boost = 0;
	private int boostDelay = 20;

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		NBTTagList items = tag.getTagList("Items", 10);
		inventoryStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < items.tagCount(); ++i) {
			NBTTagCompound slot = items.getCompoundTagAt(i);
			byte pos = slot.getByte("Slot");

			if (pos >= 0 && pos < getSizeInventory()) {
				inventoryStacks[pos] = ItemStack.loadItemStackFromNBT(slot);
			}
		}

		crystalTime = tag.getFloat("cystalTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setFloat("crystalTime", crystalTime);

		NBTTagList items = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); ++i) {
			if (inventoryStacks[i] != null) {
				NBTTagCompound slot = new NBTTagCompound();
				slot.setByte("Slot", (byte) i);
				inventoryStacks[i].writeToNBT(slot);
				items.appendTag(slot);
			}
		}

		tag.setTag("Items", items);
	}

	@Override
	public int getSizeInventory() {
		return inventoryStacks.length;
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
	public boolean canUpdate() {
		return true;
	}

	public boolean isCooking() {
		return crystalTime > 0;
	}

	public int getCookProgressScaled(int i) {
		return 0;
	}

	public int getBoostScaled() {
		return 0;
	}

	@Override
	public void updateEntity() {
		if (hasWorldObj()) {
			if (crystalTime > 0F && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
				// consume essentia / begin crafting
			}

			if (crystalTime > 0F && (inventoryStacks[6] == null || inventoryStacks[6].getItem() != ConfigItems.itemShard)) {
				worldObj.playSoundEffect((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D, "random.fizz", 1F, 1.6F);
				crystalTime = 0F;
			}

			if (crystalTime < 0F && inventoryStacks[6] != null && inventoryStacks[6].getItem() == ConfigItems.itemShard) {
				// add crystal
			}

			if (crystalTime == 0F && inventoryStacks[6] != null && inventoryStacks[6].getItem() == ConfigItems.itemShard) {
				if (inventoryStacks[6].isItemEqual(new ItemStack(ItemShardDull.INSTANCE))) {
					crystalTime = maxTime;
				} else {
					crystalTime = maxTime * 2F / 3F;
				}
			}

			if (boostDelay <= 0 || boostDelay == 10) {
				// sus
			}

			if (boostDelay <= 0) {
				if (boost > 0) {
					--boost;
				}

				boostDelay = 20;
			} else {
				--boostDelay;
			}
		}
	}

	private void addCrystal(int metadata) {
		ItemStack shard = new ItemStack(ConfigItems.itemShard, 1, metadata);

		if (inventoryStacks[metadata] == null) {
			inventoryStacks[metadata] = shard.copy();
		} else if (inventoryStacks[metadata].isItemEqual(shard) && inventoryStacks[metadata].stackSize < shard.getMaxStackSize()) {
			inventoryStacks[metadata].stackSize += shard.stackSize;
		}

		--inventoryStacks[6].stackSize;

		if (inventoryStacks[6].stackSize <= 0) {
			inventoryStacks[6] = null;
		}
	}

	@Override
	public String getInventoryName() {
		return "Crystalizer";
	}

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
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot > 0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch (side) {
		case 1:
			return new int[] {};
		case 0:
			return new int[] { 0, 1, 2, 3, 4, 5, 7, 8, 9 };
		default:
			return new int[] { 6 };
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return !isItemValidForSlot(slot, stack);
	}
}