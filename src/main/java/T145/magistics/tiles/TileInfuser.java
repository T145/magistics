package T145.magistics.tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.common.config.ConfigItems;
import T145.magistics.lib.InventoryHelper;
import T145.magistics.lib.crafting.InfuserRecipes;

public class TileInfuser extends TileRotatable implements ISidedInventory {
	protected ItemStack[] infuserItemStacks = new ItemStack[8];

	public float infuserCookTime = 0F;
	public float currentItemCookCost;
	public float sucked = 0F;

	public int angle = -1;
	public int boost = 0;
	protected int soundDelay = 0;
	private int boostDelay = 20;

	@Override
	public int getSizeInventory() {
		return infuserItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return infuserItemStacks[slot];
	}

	@Override
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
			ItemStack stack = infuserItemStacks[slot];
			infuserItemStacks[slot] = null;
			return stack;
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
			NBTTagCompound tagslot = nbttaglist.getCompoundTagAt(i);
			byte slot = tagslot.getByte("Slot");

			if (slot >= 0 && slot < infuserItemStacks.length) {
				infuserItemStacks[slot] = ItemStack.loadItemStackFromNBT(tagslot);
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
		NBTTagList items = new NBTTagList();

		for (int slot = 0; slot < infuserItemStacks.length; ++slot) {
			if (infuserItemStacks[slot] != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) slot);
				infuserItemStacks[slot].writeToNBT(item);
				items.appendTag(item);
			}
		}

		tag.setTag("Items", items);
	}

	public int getCookProgressScaled(int var1) {
		return Math.round(infuserCookTime / currentItemCookCost * (float) var1);
	}

	public int getBoostScaled() {
		return Math.round(0.1F + (float) boost / 2F) * 6;
	}

	public boolean isCooking() {
		return infuserCookTime > 0F;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (soundDelay > 0) {
			--soundDelay;
		}

		angle = (int) (infuserCookTime / currentItemCookCost * 360F);

		if (!worldObj.isRemote) {
			boolean done = false;
			boolean cooked = isCooking();
			int var4;

			if (canProcess() && currentItemCookCost > 0F && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
				float var3 = Math.min(0.5F + 0.05F * (float) boost, currentItemCookCost - infuserCookTime + 0.01F);
				// sucked = getAvailablePureVis(var3);
				infuserCookTime += sucked;

				if (soundDelay == 0 && sucked >= 0.025F) {
					worldObj.playSoundEffect(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, "magistics.infuser", 0.2F, 1F);
					soundDelay = 62;
					var4 = xCoord >> 4;
					int var5 = zCoord >> 4;

					// spread an eerie biome at the expense of performance
				}
			} else {
				sucked = 0F;
			}

			if (infuserCookTime >= currentItemCookCost && cooked) {
				addProcessedItem();
				infuserCookTime = 0F;
				currentItemCookCost = 0F;
				markDirty();
			}

			if (currentItemCookCost != 0F && currentItemCookCost != getCookCost()) {
				infuserCookTime = 0F;
				currentItemCookCost = 0F;
				worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.fizz", 1F, 1.6F);
			}

			if (infuserCookTime == 0F && canProcess()) {
				currentItemCookCost = getCookCost();
				markDirty();
			}

			if (cooked != isCooking()) {
				done = true;
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

	protected List<ItemStack> getInventoryContents() {
		List<ItemStack> contents = new ArrayList<ItemStack>();

		for (int slot = 2; slot < infuserItemStacks.length; ++slot) {
			ItemStack stackInSlot = infuserItemStacks[slot];

			if (stackInSlot != null) {
				ItemStack ingredient = new ItemStack(stackInSlot.getItem(), 1, stackInSlot.getItemDamage());
				contents.add(ingredient);
			}
		}

		return contents;
	}

	protected float getCookCost() {
		List<ItemStack> contents = getInventoryContents();

		if (contents.isEmpty()) {
			return 0F;
		} else {
			return InfuserRecipes.infusing().getInfusingCost(contents.toArray());
		}
	}

	protected ItemStack getResultStack() {
		List<ItemStack> contents = getInventoryContents();

		if (contents.isEmpty()) {
			return null;
		} else {
			return InfuserRecipes.infusing().getInfusingResult(contents.toArray());
		}
	}

	protected boolean canProcess() {
		ItemStack stack = getResultStack();

		if (stack == null) {
			return false;
		} else if (infuserItemStacks[0] == null) {
			return true;
		} else if (!infuserItemStacks[0].isItemEqual(stack)) {
			return false;
		} else {
			int stackSize = infuserItemStacks[0].stackSize + stack.stackSize;
			return stackSize <= getInventoryStackLimit() && stackSize <= stack.getMaxStackSize();
		}
	}

	protected void addProcessedItem() {
		if (canProcess()) {
			ItemStack result = getResultStack();

			if (infuserItemStacks[0] == null) {
				infuserItemStacks[0] = result.copy();
			} else if (infuserItemStacks[0].isItemEqual(result) && infuserItemStacks[0].stackSize < result.getMaxStackSize()) {
				infuserItemStacks[0].stackSize += result.stackSize;
			}

			for (int slot = 2; slot < infuserItemStacks.length; ++slot) {
				if (infuserItemStacks[slot] != null) {
					if (infuserItemStacks[slot].getItem() == ConfigItems.itemShard && infuserItemStacks[slot].getItemDamage() < 5 && worldObj.rand.nextBoolean()) {
						if (infuserItemStacks[1] == null) {
							infuserItemStacks[1] = new ItemStack(ConfigItems.itemShard, 1, 6);
						} else if (infuserItemStacks[1].isItemEqual(new ItemStack(ConfigItems.itemShard, 1, 6)) && infuserItemStacks[1].stackSize < 64) {
							++infuserItemStacks[1].stackSize;
						} else {
							InventoryHelper.emptyInventory(worldObj, xCoord, yCoord, zCoord);
						}
					}

					--infuserItemStacks[slot].stackSize;

					if (infuserItemStacks[slot].stackSize <= 0) {
						infuserItemStacks[slot] = null;
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
	public void openInventory() {}

	@Override
	public void closeInventory() {}

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

	public boolean hasConnectedSide(int side) {
		return false;
	}
}