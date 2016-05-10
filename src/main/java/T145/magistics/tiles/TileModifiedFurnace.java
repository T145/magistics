package T145.magistics.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public abstract class TileModifiedFurnace extends TileEntityFurnace {
	private ItemStack[] furnaceItemStacks = new ItemStack[3];
	private String displayName;

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

				if (furnaceItemStacks[slotFrom].stackSize == 0) {
					furnaceItemStacks[slotFrom] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (furnaceItemStacks[slot] != null) {
			ItemStack itemstack = furnaceItemStacks[slot];
			furnaceItemStacks[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		furnaceItemStacks[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return hasCustomInventoryName() ? displayName : "container.modified_furnace";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return displayName != null && displayName.length() > 0;
	}

	public void setDisplayName(String name) {
		displayName = name;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		NBTTagList items = tag.getTagList("Items", 10);
		furnaceItemStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < items.tagCount(); ++i) {
			NBTTagCompound slot = items.getCompoundTagAt(i);
			byte b0 = slot.getByte("Slot");

			if (b0 >= 0 && b0 < furnaceItemStacks.length) {
				furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(slot);
			}
		}

		furnaceBurnTime = tag.getShort("BurnTime");
		furnaceCookTime = tag.getShort("CookTime");
		currentItemBurnTime = getItemBurnTime(furnaceItemStacks[1]);

		if (tag.hasKey("CustomName", 8)) {
			displayName = tag.getString("CustomName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setShort("BurnTime", (short) furnaceBurnTime);
		tag.setShort("CookTime", (short) furnaceCookTime);
		NBTTagList items = new NBTTagList();

		for (int i = 0; i < furnaceItemStacks.length; ++i) {
			if (furnaceItemStacks[i] != null) {
				NBTTagCompound slot = new NBTTagCompound();
				slot.setByte("Slot", (byte) i);
				furnaceItemStacks[i].writeToNBT(slot);
				items.appendTag(slot);
			}
		}

		tag.setTag("Items", items);

		if (hasCustomInventoryName()) {
			tag.setString("CustomName", displayName);
		}
	}

	@Override
	public void updateEntity() {
		boolean isActive = isBurning();
		boolean active = false;

		if (isBurning()) {
			--furnaceBurnTime;
		}

		if (!worldObj.isRemote) {
			if (furnaceBurnTime != 0 || furnaceItemStacks[1] != null && furnaceItemStacks[0] != null) {
				if (furnaceBurnTime == 0 && canSmelt()) {
					currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[1]);

					if (isBurning()) {
						active = true;

						if (furnaceItemStacks[1] != null) {
							--furnaceItemStacks[1].stackSize;

							if (furnaceItemStacks[1].stackSize == 0) {
								furnaceItemStacks[1] = furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
							}
						}
					}
				}

				if (isBurning() && canSmelt()) {
					++furnaceCookTime;

					if (furnaceCookTime == 200) {
						furnaceCookTime = 0;
						smeltItem();
						active = true;
					}
				} else {
					furnaceCookTime = 0;
				}
			}

			if (isActive != isBurning()) {
				active = true;
				updateFurnaceBlockState(isBurning(), worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (active) {
			markDirty();
		}
	}

	public abstract void updateFurnaceBlockState(boolean b, World worldObj, int xCoord, int yCoord, int zCoord);

	private boolean canSmelt() {
		if (furnaceItemStacks[0] == null) {
			return false;
		} else {
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

	@Override
	public void smeltItem() {
		if (canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(furnaceItemStacks[0]);

			if (furnaceItemStacks[2] == null) {
				furnaceItemStacks[2] = itemstack.copy();
			} else if (furnaceItemStacks[2].getItem() == itemstack.getItem()) {
				furnaceItemStacks[2].stackSize += itemstack.stackSize;
			}

			--furnaceItemStacks[0].stackSize;

			if (furnaceItemStacks[0].stackSize <= 0) {
				furnaceItemStacks[0] = null;
			}
		}
	}
}