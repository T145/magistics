package T145.magistics.tiles;

import javax.annotation.Nullable;

import T145.magistics.api.tiles.IFacing;
import T145.magistics.api.tiles.TileMagistics;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileInfuser extends TileMagistics implements IFacing, ITickable, ISidedInventory {

	private ItemStack[] inventoryStacks = new ItemStack[8];

	private boolean active = false;
	private boolean crafting = false;

	public int infuserCookTime;
	public int infuserBurnTime;
	public int boost = 0;

	protected int angle = 0;
	protected int soundDelay = 0;
	private int facing = 0;
	private int boostDelay = 20;

	@Override
	public int getFacing() {
		return facing;
	}

	@Override
	public void setFacing(int dir) {
		facing = dir;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		return tag;
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
	}

	@Override
	public NBTTagCompound writeCustomNBT(NBTTagCompound tag) {
		return tag;
	}

	@Override
	public int getSizeInventory() {
		return inventoryStacks.length;
	}

	@Override
	@Nullable
	public ItemStack getStackInSlot(int index) {
		return inventoryStacks[index];
	}

	@Override
	@Nullable
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(inventoryStacks, index, count);
	}

	@Override
	@Nullable
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(inventoryStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
	}

	@Override
	public int getInventoryStackLimit() {
		return 0;
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
	}

	@Override
	public String getName() {
		return "container.infuser";
	}

	@Override
	public boolean hasCustomName() {
		return true;
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
	}
}