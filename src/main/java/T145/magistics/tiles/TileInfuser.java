package T145.magistics.tiles;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.common.config.ConfigItems;
import T145.magistics.lib.crafting.InfuserRecipes;

public class TileInfuser extends TileRotatable implements ISidedInventory {
	protected ItemStack[] infuserItemStacks = new ItemStack[8];

	public float infuserCookTime = 0.0F;
	public float currentItemCookCost;
	public float sucked = 0.0F;

	public int angle = -1;
	public int boost = 0;
	protected int soundDelay = 0;
	private int boostDelay = 20;

	@Override
	public int getSizeInventory() {
		return infuserItemStacks.length;
	}

	public ItemStack getStackInSlot(int slot) {
		return infuserItemStacks[slot];
	}

	public ItemStack decrStackSize(int slotFrom, int slotTo) {
		if (infuserItemStacks[slotFrom] != null) {
			ItemStack stack;

			if (infuserItemStacks[slotFrom].stackSize <= slotTo) {
				stack = infuserItemStacks[slotFrom];
				infuserItemStacks[slotFrom] = null;
				return stack;
			} else {
				stack = infuserItemStacks[slotFrom].splitStack(slotTo);

				if (infuserItemStacks[slotFrom].stackSize == 0) {
					infuserItemStacks[slotFrom] = null;
				}

				return stack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (infuserItemStacks[slot] != null) {
			ItemStack var2 = infuserItemStacks[slot];
			infuserItemStacks[slot] = null;
			return var2;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		infuserItemStacks[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		infuserItemStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < infuserItemStacks.length) {
				infuserItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		infuserCookTime = tag.getFloat("CookTime");
		currentItemCookCost = tag.getFloat("CookCost");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setFloat("CookTime", infuserCookTime);
		tag.setFloat("CookCost", currentItemCookCost);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < infuserItemStacks.length; ++i) {
			if (infuserItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				infuserItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tag.setTag("Items", nbttaglist);
	}

	public int getCookProgressScaled(int var1) {
		return Math.round(infuserCookTime / currentItemCookCost * (float) var1);
	}

	public int getBoostScaled() {
		return Math.round(0.1F + (float) boost / 2.0F) * 6;
	}

	public boolean isCooking() {
		return infuserCookTime > 0.0F;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (soundDelay > 0) {
			--soundDelay;
		}

		angle = (int) (infuserCookTime / currentItemCookCost * 360.0F);

		if (!worldObj.isRemote) {
			boolean var1 = false;
			boolean var2 = infuserCookTime > 0.0F;
			int var4;

			if (canProcess() && currentItemCookCost > 0.0F && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
				float var3 = Math.min(0.5F + 0.05F * (float) boost, currentItemCookCost - infuserCookTime + 0.01F);
				// sucked = getAvailablePureVis(var3);
				infuserCookTime += sucked;

				if (soundDelay == 0 && sucked >= 0.025F) {
					worldObj.playSoundEffect(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, "thaumcraft.infuser", 0.2F, 1.0F);
					soundDelay = 62;
					var4 = xCoord >> 4;
					int var5 = zCoord >> 4;

					// spread an eerie biome at the expense of performance
				}
			} else {
				sucked = 0.0F;
			}

			if (infuserCookTime >= currentItemCookCost && var2) {
				addProcessedItem();
				infuserCookTime = 0.0F;
				currentItemCookCost = 0.0F;
				markDirty();
			}

			if (currentItemCookCost != 0.0F && currentItemCookCost != getCookCost()) {
				infuserCookTime = 0.0F;
				currentItemCookCost = 0.0F;
				worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.fizz", 1.0F, 1.6F);
			}

			if (infuserCookTime == 0.0F && canProcess()) {
				currentItemCookCost = getCookCost();
				markDirty();
			}

			if (var2 != infuserCookTime > 0.0F) {
				var1 = true;
			}

			if (boostDelay <= 0 || boostDelay == 10) {
				// spread an eerie biome at the expense of machine boost
			}

			if (boostDelay <= 0) {
				if (boost > 0) {
					--boost;
				}

				boostDelay = 20;
			} else {
				--boostDelay;
			}
		}
	}

	protected float getCookCost() {
		ArrayList var1 = new ArrayList();

		for (int var2 = 2; var2 <= 7; ++var2) {
			if (infuserItemStacks[var2] != null) {
				ItemStack var3 = new ItemStack(infuserItemStacks[var2].getItem(), 1, infuserItemStacks[var2].getItemDamage());
				var1.add(var3);
			}
		}

		if (var1.size() > 0) {
			float var4 = (float) InfuserRecipes.infusing().getInfusingCost(var1.toArray());
			return var4;
		} else {
			return 0.0F;
		}
	}

	protected ItemStack getResultStack() {
		ArrayList var1 = new ArrayList();

		for (int var2 = 2; var2 <= 7; ++var2) {
			if (infuserItemStacks[var2] != null) {
				ItemStack var3 = new ItemStack(infuserItemStacks[var2].getItem(), 1, infuserItemStacks[var2].getItemDamage());
				var1.add(var3);
			}
		}

		if (var1.size() > 0) {
			return InfuserRecipes.infusing().getInfusingResult(var1.toArray());
		} else {
			return null;
		}
	}

	protected boolean canProcess() {
		ItemStack var1 = getResultStack();

		if (var1 == null) {
			return false;
		} else if (infuserItemStacks[0] == null) {
			return true;
		} else if (!infuserItemStacks[0].isItemEqual(var1)) {
			return false;
		} else {
			int var2 = infuserItemStacks[0].stackSize + var1.stackSize;
			return var2 <= getInventoryStackLimit() && var2 <= var1.getMaxStackSize();
		}
	}

	protected void addProcessedItem() {
		if (canProcess()) {
			ItemStack var1 = getResultStack();

			if (infuserItemStacks[0] == null) {
				infuserItemStacks[0] = var1.copy();
			} else if (infuserItemStacks[0].isItemEqual(var1) && infuserItemStacks[0].stackSize < var1.getMaxStackSize()) {
				infuserItemStacks[0].stackSize += var1.stackSize;
			}

			for (int var2 = 2; var2 <= 7; ++var2) {
				if (infuserItemStacks[var2] != null) {
					if (infuserItemStacks[var2].getItem() == ConfigItems.itemShard && infuserItemStacks[var2].getItemDamage() < 5 && worldObj.rand.nextBoolean()) {
						if (infuserItemStacks[1] == null) {
							infuserItemStacks[1] = new ItemStack(ConfigItems.itemShard, 1, 6);
						} else if (infuserItemStacks[1].isItemEqual(new ItemStack(ConfigItems.itemShard, 1, 6)) && infuserItemStacks[1].stackSize < 64) {
							++infuserItemStacks[1].stackSize;
						} else {
							// throw items into the world
						}
					}

					--infuserItemStacks[var2].stackSize;

					if (infuserItemStacks[var2].stackSize <= 0) {
						infuserItemStacks[var2] = null;
					}
				}
			}
		}
	}

	@Override
	public String getInventoryName() {
		return "Thaumic Infuser";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch (side) {
		case 0:
			return new int[] { 0 };
		case 1:
			return null;
		default:
			return new int[] { 1, 2, 3, 4, 5 };
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return canInsertItem(slot, stack, side);
	}
}