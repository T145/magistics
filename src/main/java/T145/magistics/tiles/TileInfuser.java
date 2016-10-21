package T145.magistics.tiles;

import T145.magistics.api.tiles.TileVisUser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileInfuser extends TileVisUser implements ITickable, ISidedInventory {

	protected ItemStack[] inventoryStacks = new ItemStack[8];

	private boolean active = false;
	private boolean crafting = false;

	public int cookTime;
	public int burnTime;

	protected int angle;
	protected int soundDelay;

	private int facing;
	private int boost;
	private int boostDelay = 20;

	public boolean isDark() {
		return false;
	}

	public boolean isActive() {
		return active;
	}

	public boolean isCrafting() {
		return crafting;
	}

	public boolean isDormant() {
		return !active && !crafting;
	}

	public int getDiskAngle() {
		return angle;
	}

	public int getBoost() {
		return boost;
	}

	public void setFacing(int dir) {
		facing = dir;
	}

	public int getFacing() {
		return facing;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		inventoryStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagnbt = nbttaglist.getCompoundTagAt(i);
			int j = nbttagnbt.getByte("Slot");

			if (j >= 0 && j < getSizeInventory()) {
				inventoryStacks[j] = ItemStack.loadItemStackFromNBT(nbttagnbt);
			}
		}

		facing = tag.getInteger("Facing");
		burnTime = tag.getInteger("BurnTime");
		cookTime = tag.getInteger("CookTime");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("Facing", facing);
		tag.setInteger("BurnTime", burnTime);
		tag.setInteger("CookTime", cookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); ++i) {
			if (inventoryStacks[i] != null) {
				NBTTagCompound nbttagnbt = new NBTTagCompound();
				nbttagnbt.setByte("Slot", (byte) i);
				inventoryStacks[i].writeToNBT(nbttagnbt);
				nbttaglist.appendTag(nbttagnbt);
			}
		}

		tag.setTag("Items", nbttaglist);
		return super.writeToNBT(tag);
	}

	@Override
	public int getSizeInventory() {
		return inventoryStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventoryStacks[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(inventoryStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(inventoryStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventoryStacks[index] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(pos) != this ? false : player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return isDark() ? index > 0 : index > 1;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < getSizeInventory(); ++i) {
			inventoryStacks[i] = null;
		}
	}

	@Override
	public String getName() {
		return isDark() ? "Dark Infuser" : "Infuser";
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		switch (side) {
		case UP: case DOWN:
			return new int[] {};
		default:
			return isDark() ? new int[] { 0, 1, 2, 3, 4, 5 } : new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
		return isItemValidForSlot(index, stack);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return !isItemValidForSlot(index, stack);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public int getCookProgressScaled(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getDarkCookProgressScaled(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getBoostScaled() {
		// TODO Auto-generated method stub
		return 0;
	}
}