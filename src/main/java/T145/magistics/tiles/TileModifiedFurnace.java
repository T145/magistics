package T145.magistics.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class TileModifiedFurnace extends TileEntity implements
ISidedInventory {
	public static final int[] slotsTop = new int[] { 0 };
	public static final int[] slotsBottom = new int[] { 2, 1 };
	public static final int[] slotsSides = new int[] { 1 };

	public ItemStack[] furnaceItemStacks = new ItemStack[3];

	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public int furnaceCookTime;

	@Override
	public int getSizeInventory() {
		return furnaceItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int p_70301_1_) {
		return furnaceItemStacks[p_70301_1_];
	}

	@Override
	public ItemStack decrStackSize(int slotFrom, int slotTo) {
		if (furnaceItemStacks[slotFrom] != null) {
			ItemStack stack;

			if (furnaceItemStacks[slotFrom].stackSize <= slotTo) {
				stack = furnaceItemStacks[slotFrom];
				furnaceItemStacks[slotFrom] = null;
				return stack;
			} else {
				stack = furnaceItemStacks[slotFrom].splitStack(slotTo);

				if (furnaceItemStacks[slotFrom].stackSize == 0) {
					furnaceItemStacks[slotFrom] = null;
				}

				return stack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (furnaceItemStacks[slot] != null) {
			ItemStack stack = furnaceItemStacks[slot];
			furnaceItemStacks[slot] = null;
			return stack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		furnaceItemStacks[p_70299_1_] = p_70299_2_;

		if (p_70299_2_ != null && p_70299_2_.stackSize > getInventoryStackLimit()) {
			p_70299_2_.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagList taglist = tag.getTagList("Items", 10);
		furnaceItemStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < taglist.tagCount(); ++i) {
			NBTTagCompound slot = taglist.getCompoundTagAt(i);
			byte b0 = slot.getByte("Slot");

			if (b0 >= 0 && b0 < furnaceItemStacks.length) {
				furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(slot);
			}
		}

		furnaceBurnTime = tag.getShort("BurnTime");
		furnaceCookTime = tag.getShort("CookTime");
		currentItemBurnTime = getItemBurnTime(furnaceItemStacks[1]);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setShort("BurnTime", (short) furnaceBurnTime);
		tag.setShort("CookTime", (short) furnaceCookTime);

		NBTTagList taglist = new NBTTagList();

		for (int i = 0; i < furnaceItemStacks.length; ++i) {
			if (furnaceItemStacks[i] != null) {
				NBTTagCompound slot = new NBTTagCompound();
				slot.setByte("Slot", (byte) i);
				furnaceItemStacks[i].writeToNBT(slot);
				taglist.appendTag(slot);
			}
		}

		tag.setTag("Items", taglist);
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int time) {
		return furnaceCookTime * time / 200;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int time) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = 200;
		}

		return furnaceBurnTime * time / currentItemBurnTime;
	}

	public boolean isBurning() {
		return furnaceBurnTime > 0;
	}

	@Override
	public void updateEntity() {
		boolean flag = furnaceBurnTime > 0;
		boolean flag1 = false;

		if (furnaceBurnTime > 0) {
			--furnaceBurnTime;
		}

		if (!worldObj.isRemote) {
			if (furnaceBurnTime != 0 || furnaceItemStacks[1] != null && furnaceItemStacks[0] != null) {
				if (furnaceBurnTime == 0 && canSmelt()) {
					currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[1]);

					if (furnaceBurnTime > 0) {
						flag1 = true;

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
						flag1 = true;
					}
				} else {
					furnaceCookTime = 0;
				}
			}

			if (flag != furnaceBurnTime > 0) {
				flag1 = true;
				updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (flag1) {
			markDirty();
		}
	}

	public abstract void updateFurnaceBlockState(boolean isActive, World worldObj, int xCoord, int yCoord, int zCoord);

	public boolean canSmelt() {
		if (furnaceItemStacks[0] == null) {
			return false;
		} else {
			ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(furnaceItemStacks[0]);
			if (stack == null)
				return false;
			if (furnaceItemStacks[2] == null)
				return true;
			if (!furnaceItemStacks[2].isItemEqual(stack))
				return false;
			int result = furnaceItemStacks[2].stackSize + stack.stackSize;
			return result <= getInventoryStackLimit() && result <= furnaceItemStacks[2].getMaxStackSize();
		}
	}

	public void smeltItem() {
		if (canSmelt()) {
			ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(furnaceItemStacks[0]);

			if (furnaceItemStacks[2] == null) {
				furnaceItemStacks[2] = stack.copy();
			} else if (furnaceItemStacks[2].getItem() == stack.getItem()) {
				furnaceItemStacks[2].stackSize += stack.stackSize;
			}

			--furnaceItemStacks[0].stackSize;

			if (furnaceItemStacks[0].stackSize <= 0) {
				furnaceItemStacks[0] = null;
			}
		}
	}

	public int getItemBurnTime(ItemStack stack) {
		return TileEntityFurnace.getItemBurnTime(stack);
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
		return slot == 2 ? false : (slot == 1 ? TileEntityFurnace.isItemFuel(stack) : true);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? slotsBottom : (side == 1 ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		return isItemValidForSlot(p_102007_1_, p_102007_2_);
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return p_102008_3_ != 0 || p_102008_1_ != 1 || p_102008_2_.getItem() == Items.bucket;
	}
}