package T145.magistics.common.tiles.pillars;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import T145.magistics.common.Magistics;

public abstract class TileBasePillar extends TileBase implements IInventory, ISidedInventory {
	protected ItemStack[] inventory = new ItemStack[getSizeInventory()];

	public abstract boolean isOnlyDisplaySlot(int i);

	@Override
	public abstract int getSizeInventory();

	public void dropItemFromSlot(int slot, int amount, EntityPlayer player) {
		if (!worldObj.isRemote && getStackInSlot(slot) != null) {
			EntityItem itemEntity = new EntityItem(worldObj, player.posX, player.posY, player.posZ);
			itemEntity.setEntityItemStack(decrStackSize(slot, amount));
			worldObj.spawnEntityInWorld(itemEntity);
			onInventoryChanged();
		}
	}

	@Override
	public void onInventoryChanged() {
		super.onInventoryChanged();
		if (!worldObj.isRemote)
			Magistics.proxy.sendToPlayers(getDescriptionPacket(), worldObj, xCoord, yCoord, zCoord, 64);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		inventory = new ItemStack[getSizeInventory()];
		NBTTagList nbtlist = nbt.getTagList("Items", 10);
		for (int i = 0; i < nbtlist.tagCount(); i++) {
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
			int j = nbtslot.getByte("Slot") & 255;

			if ((j >= 0) && (j < getSizeInventory()))
				inventory[j] = ItemStack.loadItemStackFromNBT(nbtslot);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		NBTTagList nbtlist = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); i++) {
			if (inventory[i] != null) {
				NBTTagCompound nbtslot = new NBTTagCompound();
				nbtslot.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbtslot);
				nbtlist.appendTag(nbtslot);
			}
		}
		nbt.setTag("Items", nbtlist);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return null;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return !isOnlyDisplaySlot(i) && (inventory[i] == null || inventory[i].isItemEqual(itemstack));
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int side) {
		return isItemValidForSlot(i, itemstack) && (inventory[i] == null ? itemstack.stackSize : itemstack.stackSize + inventory[i].stackSize) <= Math.min(getInventoryStackLimit(), itemstack.getMaxStackSize());
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int side) {
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		inventory[i] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
		onInventoryChanged();
	}

	public ItemStack incrStackSize(int i, int amount) {
		if (inventory[i] != null) {
			int j;
			if (inventory[i].stackSize + amount <= Math.min(getInventoryStackLimit(), inventory[i].getMaxStackSize()))
				j = amount;
			else
				j = Math.min(getInventoryStackLimit(), inventory[i].getMaxStackSize()) - inventory[i].stackSize;
			inventory[i].stackSize += j;
			onInventoryChanged();
			return new ItemStack(inventory[i].getItem(), j);
		}
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int amount) {
		ItemStack stack = null;
		if (inventory[i] != null) {
			if (inventory[i].stackSize <= amount) {
				stack = inventory[i];
				inventory[i] = null;
			} else {
				stack = inventory[i].splitStack(amount);
				if (inventory[i].stackSize == 0)
					inventory[i] = null;
			}
			onInventoryChanged();
		}
		return stack;
	}

	public boolean insertStack(int i, ItemStack stack, int side) {
		if (canInsertItem(i, stack, side)) {
			if (inventory[i] == null)
				setInventorySlotContents(i, stack);
			else
				incrStackSize(i, stack.stackSize);
			return true;
		}
		return false;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return getStackInSlot(i);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
}