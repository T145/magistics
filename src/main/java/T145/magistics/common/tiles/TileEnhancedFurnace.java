package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import T145.magistics.common.blocks.BlockEnhancedFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEnhancedFurnace extends TileEntity implements ISidedInventory {
	private static final int[] slotsTop = new int[] { 0 }, slotsBottom = new int[] { 2, 1 }, slotsSides = new int[] { 1 };
	private ItemStack[] furnaceItemStacks = new ItemStack[3];
	public int furnaceBurnTime, currentItemBurnTime, furnaceCookTime;
	protected static int speedMod = 200;
	private String invName;

	protected static void setSpeedMod(int speed) {
		speedMod = speed;
	}

	protected static int getSpeedMod() {
		return speedMod;
	}

	@Override
	public int getSizeInventory() {
		return furnaceItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return furnaceItemStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slotFrom, int slotTo) {
		if (furnaceItemStacks[slotFrom] != null) {
			ItemStack itemstack;

			if (furnaceItemStacks[slotFrom].stackSize <= slotTo) {
				itemstack = furnaceItemStacks[slotFrom];
				furnaceItemStacks[slotFrom] = null;
				return itemstack;
			} else {
				itemstack = furnaceItemStacks[slotFrom].splitStack(slotTo);

				if (furnaceItemStacks[slotFrom].stackSize == 0)
					furnaceItemStacks[slotFrom] = null;

				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (furnaceItemStacks[slot] != null) {
			ItemStack itemstack = furnaceItemStacks[slot];
			furnaceItemStacks[slot] = null;
			return itemstack;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		furnaceItemStacks[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInventoryName() {
		return hasCustomInventoryName() ? invName : "container.enhancedfurnace";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return invName != null && invName.length() > 0;
	}

	public void setName(String name) {
		invName = name;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList tags = nbt.getTagList("Items", 10);
		furnaceItemStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < tags.tagCount(); ++i) {
			NBTTagCompound tag = tags.getCompoundTagAt(i);
			byte b0 = tag.getByte("Slot");

			if (b0 >= 0 && b0 < furnaceItemStacks.length)
				furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(tag);
		}

		furnaceBurnTime = nbt.getShort("BurnTime");
		furnaceCookTime = nbt.getShort("CookTime");
		currentItemBurnTime = getItemBurnTime(furnaceItemStacks[1]);

		if (nbt.hasKey("CustomName", 8))
			invName = nbt.getString("CustomName");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short) furnaceBurnTime);
		nbt.setShort("CookTime", (short) furnaceCookTime);
		NBTTagList tags = new NBTTagList();

		for (int i = 0; i < furnaceItemStacks.length; ++i) {
			if (furnaceItemStacks[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				furnaceItemStacks[i].writeToNBT(tag);
				tags.appendTag(tag);
			}
		}

		nbt.setTag("Items", tags);

		if (hasCustomInventoryName())
			nbt.setString("CustomName", invName);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int scale) {
		return furnaceCookTime * scale / speedMod;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int p_145955_1_) {
		if (currentItemBurnTime == 0)
			currentItemBurnTime = speedMod;

		return furnaceBurnTime * p_145955_1_ / currentItemBurnTime;
	}

	public boolean isActive() {
		return furnaceBurnTime > 0;
	}

	@Override
	public void updateEntity() {
		boolean active = false;

		if (furnaceBurnTime > 0)
			--furnaceBurnTime;

		if (!worldObj.isRemote) {
			if (furnaceBurnTime != 0 || furnaceItemStacks[1] != null && furnaceItemStacks[0] != null) {
				if (furnaceBurnTime == 0 && canSmelt()) {
					currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[1]);

					if (furnaceBurnTime > 0) {
						active = true;

						if (furnaceItemStacks[1] != null) {
							--furnaceItemStacks[1].stackSize;

							if (furnaceItemStacks[1].stackSize == 0)
								furnaceItemStacks[1] = furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
						}
					}
				}

				if (isActive() && canSmelt()) {
					++furnaceCookTime;

					if (furnaceCookTime == speedMod) {
						furnaceCookTime = 0;
						smeltItem();
						active = true;
					}
				} else
					furnaceCookTime = 0;
			}

			if (isActive() != furnaceBurnTime > 0) {
				active = true;

				if (getBlockType() instanceof BlockEnhancedFurnace)
					BlockEnhancedFurnace.updateState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (active)
			markDirty();
	}

	private boolean canSmelt() {
		if (furnaceItemStacks[0] == null)
			return false;
		else {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(furnaceItemStacks[0]);
			if (itemstack == null)
				return false;
			if (furnaceItemStacks[2] == null)
				return true;
			if (!furnaceItemStacks[2].isItemEqual(itemstack))
				return false;
			int result = furnaceItemStacks[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= furnaceItemStacks[2].getMaxStackSize();
		}
	}

	public void smeltItem() {
		if (canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(furnaceItemStacks[0]);

			if (furnaceItemStacks[2] == null)
				furnaceItemStacks[2] = itemstack.copy();
			else if (furnaceItemStacks[2].getItem() == itemstack.getItem())
				furnaceItemStacks[2].stackSize += itemstack.stackSize;

			--furnaceItemStacks[0].stackSize;

			if (furnaceItemStacks[0].stackSize <= 0)
				furnaceItemStacks[0] = null;
		}
	}

	public static int getItemBurnTime(ItemStack stack) {
		return TileEntityFurnace.getItemBurnTime(stack);
	}

	public static boolean isItemFuel(ItemStack stack) {
		return getItemBurnTime(stack) > 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 2 ? false : (slot == 1 ? isItemFuel(stack) : true);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int slot) {
		return slot == 0 ? slotsBottom : (slot == 1 ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot != 0 || slot != 1 || stack.getItem() == Items.bucket;
	}
}