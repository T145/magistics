package T145.magistics.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.events.EssentiaHandler;
import thaumcraft.common.lib.utils.InventoryUtils;
import T145.magistics.Magistics;
import T145.magistics.lib.InventoryHelper;
import T145.magistics.lib.crafting.InfuserRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileInfuser extends TileMagistics implements ISidedInventory, IAspectContainer {
	protected ItemStack[] inventoryStacks = new ItemStack[8];
	protected AspectList recipeEssentia = new AspectList();

	private boolean active = false;
	private boolean crafting = false;

	public int infuserCookTime;
	public int infuserBurnTime;

	private int boostDelay = 20;
	protected int boost = 0;
	protected int angle = getFacing();
	protected int soundDelay = 0;

	public boolean isActive() {
		return active;
	}

	public boolean isCrafting() {
		return crafting;
	}

	public int getBoost() {
		return boost;
	}

	public void setBoost(int amount) {
		boost = amount;
	}

	public int getDiskAngle() {
		return angle;
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		active = tag.getBoolean("active");
		crafting = tag.getBoolean("crafting");
		recipeEssentia.readFromNBT(tag);
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setBoolean("active", active);
		tag.setBoolean("crafting", crafting);
		recipeEssentia.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		NBTTagList items = tag.getTagList("Items", 10);
		inventoryStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < items.tagCount(); ++i) {
			NBTTagCompound slot = items.getCompoundTagAt(i);
			byte pos = slot.getByte("Slot");

			if (pos >= 0 && pos < inventoryStacks.length) {
				inventoryStacks[pos] = ItemStack.loadItemStackFromNBT(slot);
			}
		}

		infuserBurnTime = tag.getShort("BurnTime");
		infuserCookTime = tag.getShort("CookTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setShort("BurnTime", (short) infuserBurnTime);
		tag.setShort("CookTime", (short) infuserCookTime);

		NBTTagList items = new NBTTagList();

		for (int i = 0; i < inventoryStacks.length; ++i) {
			if (inventoryStacks[i] != null) {
				NBTTagCompound slot = new NBTTagCompound();
				slot.setByte("Slot", (byte) i);
				inventoryStacks[i].writeToNBT(slot);
				items.appendTag(slot);
			}
		}

		tag.setTag("Items", items);
	}

	@Override
	public int getSizeInventory() {
		return inventoryStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventoryStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		return InventoryHelper.decrStackSize(this, slot, size);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return InventoryHelper.getStackInSlotOnClosing(this, slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		InventoryHelper.setInventorySlotContents(this, inventoryStacks, slot, stack);
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	private int cookTimeRatio(int multiplier) {
		if (infuserBurnTime == 0) {
			return 0;
		} else {
			return infuserCookTime / (infuserBurnTime * multiplier);
		}
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int time) {
		return Math.round(cookTimeRatio(time));
	}

	@SideOnly(Side.CLIENT)
	public int getBoostScaled() {
		return Math.round(0.1F + (boost / 2)) * 6;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (soundDelay > 0) {
			--soundDelay;
		}

		if (active) {
			angle = cookTimeRatio(360);

			if (angle == 0) {
				angle = getFacing();
			}
			
			Magistics.logger.info("Angle: " + angle);
		}

		if (hasWorldObj()) {
			boolean dirty = false;
			boolean cooked = false;
			boolean isCooking = infuserCookTime > 0;

			if (canProcess() && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
				if (!active && !crafting) {
					setAspects(getRecipeEssentia());
				}

				active = true;

				if (crafting) {
					if (infuserCookTime == 0) {
						infuserBurnTime = getRecipeBurnTime();
						dirty = true;
					}

					++infuserCookTime;

					if (infuserCookTime >= infuserBurnTime && infuserCookTime > 0) {
						addProcessedItem();
						infuserBurnTime = 0;
						infuserCookTime = 0;
						crafting = false;
						active = false;
					}
				} else {
					// drain essentia
					for (Aspect aspect : recipeEssentia.getAspects()) {
						if (recipeEssentia.getAmount(aspect) != 0) {
							if (EssentiaHandler.drainEssentia(this, aspect, ForgeDirection.UP, 6)) {
								recipeEssentia.reduce(aspect, 1);
								worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
								markDirty();
								return;
							}
						}
					}

					// begin crafting
					if (recipeEssentia.visSize() == 0) {
						recipeEssentia = new AspectList();
						crafting = true;
					}
				}

				// check for cancel all activity
				if (recipeEssentia.visSize() != 0 && recipeEssentia != getRecipeEssentia()) {
					infuserBurnTime = 0;
					infuserCookTime = 0;
					recipeEssentia.aspects.clear();
					worldObj.playSoundEffect(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, "random.fizz", 1F, 1.6F);
				}

				if (infuserCookTime > 0 != isCooking) {
					dirty = true;
				}

				if (boostDelay <= 0 || boostDelay == 10) {
					// special negative effects to the player and/or environment here?
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

			if (dirty) {
				markDirty();
			}
		}
	}

	protected void consumeIngredients() {
		for (int slot = 2; slot < getSizeInventory(); ++slot) {
			if (inventoryStacks[slot] != null) {
				--inventoryStacks[slot].stackSize;

				if (inventoryStacks[slot].stackSize == 0) {
					inventoryStacks[slot] = inventoryStacks[slot].getItem().getContainerItem(inventoryStacks[slot]);
				}
			}
		}
	}

	protected void addProcessedItem() {
		ItemStack result = getResultStack();

		if (inventoryStacks[0] == null) {
			inventoryStacks[0] = result.copy();
		} else if (inventoryStacks[0].isItemEqual(result) && inventoryStacks[0].stackSize < result.getMaxStackSize()) {
			inventoryStacks[0].stackSize += result.stackSize;
		}

		for (int slot = 2; slot < inventoryStacks.length; ++slot) {
			if (inventoryStacks[slot] != null) {
				if (inventoryStacks[slot].getItem() == ConfigItems.itemShard && inventoryStacks[slot].getItemDamage() < 5 && worldObj.rand.nextBoolean()) {
					if (inventoryStacks[1] == null) {
						inventoryStacks[1] = new ItemStack(ConfigItems.itemShard, 1, 6);
					} else if (inventoryStacks[1].isItemEqual(new ItemStack(ConfigItems.itemShard, 1, 6)) && inventoryStacks[1].stackSize < 64) {
						++inventoryStacks[1].stackSize;
					} else {
						InventoryUtils.dropItems(worldObj, xCoord, yCoord, zCoord);
					}
				}

				--inventoryStacks[slot].stackSize;

				if (inventoryStacks[slot].stackSize <= 0) {
					inventoryStacks[slot] = null;
				}
			}
		}
	}

	protected boolean canProcess() {
		ItemStack stack = getResultStack();

		if (stack == null) {
			return false;
		} else if (inventoryStacks[0] == null) {
			return true;
		} else if (!inventoryStacks[0].isItemEqual(stack)) {
			return false;
		} else {
			int stackSize = inventoryStacks[0].stackSize + stack.stackSize;
			return stackSize <= getInventoryStackLimit() && stackSize <= stack.getMaxStackSize();
		}
	}

	protected ItemStack getResultStack() {
		return InfuserRecipes.infusing().getInfusingResult(inventoryStacks);
	}

	protected AspectList getRecipeEssentia() {
		return InfuserRecipes.infusing().getInfusingCost(inventoryStacks);
	}

	protected int getRecipeBurnTime() {
		return InfuserRecipes.infusing().getInfusingTime(inventoryStacks);
	}

	@Override
	public String getInventoryName() {
		return "Infuser";
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
		return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot > 1 && !active;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch (side) {
		case 0:
			return new int[] { 0, 1 };
		case 1:
			return null;
		default:
			return new int[] { 2, 3, 4, 5, 6, 7 };
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot < 2;
	}

	public boolean hasConnectedSide(int side) {
		return false;
	}

	@Override
	public int addToContainer(Aspect aspect, int amount) {
		if (doesContainerAccept(aspect)) {
			recipeEssentia.add(aspect, amount);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int containerContains(Aspect aspect) {
		return recipeEssentia.getAmount(aspect) > 0 ? 1 : 0;
	}

	@Override
	public boolean doesContainerAccept(Aspect aspect) {
		return true;
	}

	@Override
	@Deprecated
	public boolean doesContainerContain(AspectList aspects) {
		return false;
	}

	public boolean hasEssentia() {
		return recipeEssentia.size() > 0;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect aspect, int amount) {
		return recipeEssentia.getAmount(aspect) == amount;
	}

	@Override
	public AspectList getAspects() {
		return recipeEssentia;
	}

	@Override
	public void setAspects(AspectList aspects) {
		recipeEssentia = aspects;
	}

	@Override
	@Deprecated
	public boolean takeFromContainer(AspectList aspects) {
		return false;
	}

	@Override
	public boolean takeFromContainer(Aspect aspect, int amount) {
		return recipeEssentia.reduce(aspect, amount);
	}
}