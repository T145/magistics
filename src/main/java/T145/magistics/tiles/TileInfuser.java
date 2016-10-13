package T145.magistics.tiles;

import javax.annotation.Nullable;

import T145.magistics.api.tiles.IFacing;
import T145.magistics.api.tiles.TileVisUser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileInfuser extends TileVisUser implements IFacing, ISidedInventory, ITickable {

	private ItemStack[] inventoryStacks = new ItemStack[8];

	private boolean active = false;
	private boolean crafting = false;

	public int burnTime;
	public int itemBurnTime;
	public int cookTime;
	public int totalCookTime;

	protected int angle;
	protected int soundDelay;

	private int facing;
	private int boost;
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

	public int getBoost() {
		return boost;
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

		facing = tag.getInteger("facing");
		burnTime = tag.getInteger("BurnTime");
		cookTime = tag.getInteger("CookTime");
		totalCookTime = tag.getInteger("CookTimeTotal");
		itemBurnTime = getItemBurnTime(inventoryStacks[1]);
	}

	@Override
	public void readClientDataFromNBT(NBTTagCompound tag) {
		facing = tag.getInteger("facing");
		active = tag.getBoolean("active");
		crafting = tag.getBoolean("crafting");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("facing", facing);
		tag.setInteger("BurnTime", burnTime);
		tag.setInteger("CookTime", cookTime);
		tag.setInteger("CookTimeTotal", totalCookTime);
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
	public void writeClientDataToNBT(NBTTagCompound tag) {
		tag.setInteger("facing", facing);
		tag.setBoolean("active", active);
		tag.setBoolean("crafting", crafting);
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
		switch (id) {
		case 0:
			return burnTime;
		case 1:
			return itemBurnTime;
		case 2:
			return cookTime;
		case 3:
			return totalCookTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			burnTime = value;
			break;
		case 1:
			itemBurnTime = value;
			break;
		case 2:
			cookTime = value;
			break;
		case 3:
			totalCookTime = value;
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
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
		super.update();

		// TODO Implement
		if (hasWorldObj()) {
			if (soundDelay > 0) {
				--soundDelay;
			}
		}
	}

	private int getItemBurnTime(ItemStack stack) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int time) {
		return burnTime > 0 ? (cookTime * time) / burnTime : 0;
	}

	@SideOnly(Side.CLIENT)
	public int getBoostScaled() {
		return Math.round(0.1F + (float) boost / 2F) * 6;
	}

	public int getDarkCookProgressScaled(int i) {
		// TODO Auto-generated method stub
		return 0;
	}
}