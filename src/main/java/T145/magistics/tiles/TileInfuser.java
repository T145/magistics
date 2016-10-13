package T145.magistics.tiles;

import javax.annotation.Nullable;

import T145.magistics.api.tiles.IFacing;
import T145.magistics.api.tiles.TileVisUser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileInfuser extends TileVisUser implements IFacing, ISidedInventory, ITickable {

	private ItemStack[] inventoryStacks = new ItemStack[8];

	private boolean active = false;
	private boolean crafting = false;

	private int burnTime;
	private int itemBurnTime;
	private int cookTime;
	private int totalCookTime;
	private int boost = 0;

	protected int angle = 0;
	protected int soundDelay = 0;

	private int facing = 0;
	private int boostDelay = 20;

	public boolean isDark() {
		return getBlockMetadata() == 1;
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

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		facing = nbt.getByte("facing");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return super.writeToNBT(nbt);
	}

	@Override
	public NBTTagCompound writeCustomNBT(NBTTagCompound nbt) {
		nbt.setByte("facing", (byte) facing);
		return nbt;
	}

	@Override
	public int getFacing() {
		return facing;
	}

	@Override
	public void setFacing(int dir) {
		facing = dir;
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
	public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
		boolean flag = stack != null && stack.isItemEqual(inventoryStacks[index]) && ItemStack.areItemStackTagsEqual(stack, inventoryStacks[index]);
		inventoryStacks[index] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}

		if (index == 0 && !flag) {
			totalCookTime = getCookTime(stack);
			cookTime = 0;
			markDirty();
		}
	}

	public int getCookTime(@Nullable ItemStack stack) {
		return 200;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {
		switch (id) {
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {

		}
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
		return "container.infuser";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		super.update();

		// TODO Implement
	}
}