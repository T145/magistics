package T145.magistics.api.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public abstract class TileFurnace extends TileMagisticsInventory implements ISidedInventory {
	private static final int[] slotsTop = new int[] { 0 };
	private static final int[] slotsBottom = new int[] { 2, 1 };
	private static final int[] slotsSides = new int[] { 1 };

	public int furnaceBurnTime = 0;
	public int currentItemBurnTime = 0;
	public int furnaceCookTime = 0;

	public TileFurnace(boolean canFace, boolean canOwn) {
		super(3, canFace, canOwn);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		furnaceBurnTime = tag.getShort("BurnTime");
		furnaceCookTime = tag.getShort("CookTime");
		currentItemBurnTime = TileEntityFurnace.getItemBurnTime(inventoryStacks[1]);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setShort("BurnTime", (short) furnaceBurnTime);
		tag.setShort("CookTime", (short) furnaceCookTime);
	}

	@Override
	public abstract String getInventoryName();

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
		boolean active = furnaceBurnTime > 0;
		boolean dirty = false;

		if (furnaceBurnTime > 0) {
			--furnaceBurnTime;
		}

		if (!worldObj.isRemote) {
			if (furnaceBurnTime != 0 || inventoryStacks[1] != null && inventoryStacks[0] != null) {
				if (furnaceBurnTime == 0 && canSmelt()) {
					currentItemBurnTime = furnaceBurnTime = TileEntityFurnace.getItemBurnTime(inventoryStacks[1]);

					if (furnaceBurnTime > 0) {
						dirty = true;

						if (inventoryStacks[1] != null) {
							--inventoryStacks[1].stackSize;

							if (inventoryStacks[1].stackSize == 0) {
								inventoryStacks[1] = inventoryStacks[1].getItem().getContainerItem(inventoryStacks[1]);
							}
						}
					}
				}

				if (isBurning() && canSmelt()) {
					++furnaceCookTime;

					if (furnaceCookTime == 200) {
						furnaceCookTime = 0;
						smeltItem();
						dirty = true;
					}
				} else {
					furnaceCookTime = 0;
				}
			}

			if (active != furnaceBurnTime > 0) {
				dirty = true;
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (dirty) {
			markDirty();
		}
	}

	public abstract void updateFurnaceBlockState(boolean isActive, World world, int x, int y, int z);

	private boolean canSmelt() {
		if (inventoryStacks[0] == null) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(inventoryStacks[0]);

			if (itemstack == null) {
				return false;
			}

			if (inventoryStacks[2] == null) {
				return true;
			}

			if (!inventoryStacks[2].isItemEqual(itemstack)) {
				return false;
			}

			int result = inventoryStacks[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= inventoryStacks[2].getMaxStackSize();
		}
	}

	public void smeltItem() {
		if (canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(inventoryStacks[0]);

			if (inventoryStacks[2] == null) {
				inventoryStacks[2] = itemstack.copy();
			} else if (inventoryStacks[2].getItem() == itemstack.getItem()) {
				inventoryStacks[2].stackSize += itemstack.stackSize;
			}

			--inventoryStacks[0].stackSize;

			if (inventoryStacks[0].stackSize <= 0) {
				inventoryStacks[0] = null;
			}
		}
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 2 ? false : (slot == 1 ? TileEntityFurnace.isItemFuel(stack) : true);
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
		return side != 0 || slot != 1 || stack.getItem() == Items.bucket;
	}
}