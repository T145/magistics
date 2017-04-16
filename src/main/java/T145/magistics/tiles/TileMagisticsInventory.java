package T145.magistics.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileMagisticsInventory extends TileMagistics implements ISidedInventory {

	protected final int invSize;
	protected NonNullList<ItemStack> inventoryStacks;
	protected String customName;

	public TileMagisticsInventory(int invSize) {
		this.invSize = invSize;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		inventoryStacks = NonNullList.<ItemStack>withSize(invSize, ItemStack.EMPTY);

		ItemStackHelper.loadAllItems(compound, inventoryStacks);

		if (compound.hasKey("CustomName", 8)) {
			customName = compound.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		ItemStackHelper.saveAllItems(compound, inventoryStacks);

		if (hasCustomName()) {
			compound.setString("CustomName", customName);
		}

		return compound;
	}

	@Override
	public int getSizeInventory() {
		return invSize;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack stack : inventoryStacks) {
			if (!stack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventoryStacks.get(index);
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
		ItemStack itemstack = inventoryStacks.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);

		inventoryStacks.set(index, stack);

		if (stack.getCount() > getInventoryStackLimit()) {
			stack.setCount(getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			markDirty();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return world.getTileEntity(getPos()) == this;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		inventoryStacks.clear();
	}

	@Override
	public String getName() {
		return hasCustomName() ? customName : "container.magistics";
	}

	@Override
	public boolean hasCustomName() {
		return customName != null && customName.length() > 0;
	}

	@Override
	public ITextComponent getDisplayName() {
		return hasCustomName() ? new TextComponentTranslation(getName(), new Object[0]) : new TextComponentTranslation(getName(), new Object[0]);
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] { 0 };
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
		return isItemValidForSlot(index, stack);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return true;
	}
}