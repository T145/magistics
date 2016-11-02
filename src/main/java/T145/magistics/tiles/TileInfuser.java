package T145.magistics.tiles;

import T145.magistics.api.crafting.InfuserRecipe;
import T145.magistics.api.crafting.InfuserRecipes;
import T145.magistics.api.tiles.TileVisUser;
import T145.magistics.lib.utils.InventoryUtils;
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

public class TileInfuser extends TileVisUser implements ITickable, ISidedInventory {

	protected ItemStack[] inventoryStacks;

	private boolean active = false;
	private boolean crafting = false;

	public float cookCost;
	public float cookTime;

	protected float angle;
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

	public float getDiskAngle() {
		return angle;
	}

	public int getBoost() {
		return boost;
	}

	public void setFacing(int dir) {
		facing = dir;
	}

	public int getFacing() {
		return facing;
	}

	public void setTier(int meta) {
		switch (meta) {
		case 1:
			inventoryStacks = new ItemStack[6];
			break;
		default:
			inventoryStacks = new ItemStack[8];
			break;
		}
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

		facing = tag.getInteger("Facing");
		cookCost = tag.getFloat("CookCost");
		cookTime = tag.getFloat("CookTime");
		active = tag.getBoolean("Active");
		crafting = tag.getBoolean("Crafting");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setInteger("Facing", facing);
		tag.setFloat("CookCost", cookCost);
		tag.setFloat("CookTime", cookTime);
		tag.setBoolean("Active", active);
		tag.setBoolean("Crafting", crafting);

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
		return tag;
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
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventoryStacks[index] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
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
	public boolean getConnectable(EnumFacing face) {
		switch (face) {
		case NORTH: case SOUTH: case EAST: case WEST:
			return true;
		default:
			return false;
		}
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int time) {
		return Math.round(cookTime / cookCost * time);
	}

	@SideOnly(Side.CLIENT)
	public int getDarkCookProgressScaled(int time) {
		// TODO Implement
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getBoostScaled() {
		return Math.round(0.1F + boost / 2F) * 6;
	}

	@Override
	public void update() {
		super.update();

		if (soundDelay > 0) {
			--soundDelay;
		}

		angle = crafting ? cookTime * 360F / cookCost : facing;

		InfuserRecipe recipe = InfuserRecipes.getMatchingInfuserRecipe(inventoryStacks, isDark());

		if (this.isDormant()) {
			// preparation time?
		}

		if (active = hasWorldObj() && recipe != null && !isPowered()) {
			cookCost = recipe.getCost();

			if (crafting = getAvailablePureVis(cookCost) > 0F) {
				this.cookTime += this.getAvailablePureVis(Math.min(0.5F + 0.05F * this.boost, this.cookCost - this.cookTime + 0.01F));

				if (this.soundDelay == 0) {
					// play infusing sound
					soundDelay = 62;
				}

				if (this.cookTime >= this.cookCost) {
					this.addProcessedItem(recipe.getResult(), recipe.getComponents());
					this.reset();
					this.markDirtyClient();
				}
			} else {
				// pause infusing
			}
		} else {
			// stop infusing
		}
	}

	private void reset() {
		cookCost = 0F;
		cookTime = 0F;
		crafting = false;
		active = false;
	}

	protected boolean canProcess(InfuserRecipe recipe) {
		if (recipe == null) {
			return false;
		} else {
			ItemStack result = recipe.getResult();

			if (result == null) {
				return false;
			} else if (inventoryStacks[0] == null) {
				return true;
			} else if (!inventoryStacks[0].isItemEqual(result)) {
				return false;
			} else {
				int stackSize = inventoryStacks[0].stackSize + result.stackSize;
				return stackSize <= getInventoryStackLimit() && stackSize <= result.getMaxStackSize();
			}
		}
	}

	private void addProcessedItem(ItemStack result, ItemStack[] components) {
		if (inventoryStacks[0] == null) {
			inventoryStacks[0] = result.copy();
		} else if (inventoryStacks[0].isItemEqual(result) && inventoryStacks[0].stackSize < result.getMaxStackSize()) {
			inventoryStacks[0].stackSize += result.stackSize;
		}

		for (int slot = isDark() ? 1 : 2; slot < getSizeInventory(); ++slot) {
			if (inventoryStacks[slot] != null) {
				for (ItemStack component : components) {
					if (InventoryUtils.areStacksEqual(component, inventoryStacks[slot])) {

						// add dull shard if light infuser

						--inventoryStacks[slot].stackSize;

						if (inventoryStacks[slot].stackSize == 0) {
							inventoryStacks[slot] = null;
						}
					}
				}
			}
		}
	}
}