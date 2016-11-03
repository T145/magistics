package T145.magistics.tiles;

import T145.magistics.api.crafting.InfuserRecipes;
import T145.magistics.api.crafting.recipes.InfuserRecipe;
import T145.magistics.api.tiles.TileVisManager;
import T145.magistics.containers.ContainerInfuser;
import T145.magistics.lib.sounds.SoundHandler;
import T145.magistics.lib.utils.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileInfuser extends TileVisManager implements IInteractionObject, ISidedInventory, ILockableContainer {

	protected ItemStack[] inventoryStacks = new ItemStack[8];
	private LockCode code = LockCode.EMPTY_CODE;

	private boolean active = false;
	private boolean crafting = false;

	public float cookCost;
	public float cookTime;

	protected int angle;
	protected int soundDelay;

	private int facing;
	private int boost;
	private int boostDelay = 20;

	public boolean isDark() {
		return false;
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

	@Override
	public String getName() {
		return isDark() ? "tile.infuser.dark.name" : "tile.infuser.light.name";
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation(getName(), new Object[0]);
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
		InventoryUtils.setInventorySlotContents(inventoryStacks, stack, getInventoryStackLimit(), index);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(pos) != this ? false : player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64D;
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
			return (int) cookCost;
		case 1:
			return (int) cookTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			cookCost = value;
			break;
		case 1:
			cookTime = value;
			break;
		default:
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 2;
	}

	@Override
	public void clear() {
		for (int i = 0; i < getSizeInventory(); ++i) {
			inventoryStacks[i] = null;
		}
	}

	@Override
	public boolean isLocked() {
		return code != null && !code.isEmpty();
	}

	@Override
	public LockCode getLockCode() {
		return code;
	}

	@Override
	public void setLockCode(LockCode code) {
		this.code = code;
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
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return new ContainerInfuser(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return "magistics:infuser";
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		code = LockCode.fromNBT(tag);

		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		inventoryStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot");

			if (j >= 0 && j < getSizeInventory()) {
				inventoryStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}

		facing = tag.getInteger("Facing");
		cookCost = tag.getFloat("CookCost");
		cookTime = tag.getFloat("CookTime");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		if (code != null) {
			code.toNBT(tag);
		}

		tag.setInteger("Facing", facing);
		tag.setFloat("CookCost", cookCost);
		tag.setFloat("cookTime", cookTime);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); ++i) {
			if (inventoryStacks[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				inventoryStacks[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		tag.setTag("Items", nbttaglist);

		return tag;
	}

	@Override
	public boolean getConnectable(EnumFacing face) {
		return face != EnumFacing.UP;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int time) {
		return Math.round(cookTime / cookCost * time);
	}

	@SideOnly(Side.CLIENT)
	public int getDarkCookProgressScaled(int time) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getBoostScaled() {
		return Math.round(0.1F + (float) boost / 2F) * 6;
	}

	@Override
	public void update() {
		super.update();

		if (soundDelay > 0) {
			--soundDelay;
		}

		InfuserRecipe recipe = InfuserRecipes.getMatchingInfuserRecipe(inventoryStacks, isDark());

		if (isDormant()) {
			angle = facing;
		} else if (crafting) {
			angle = getCookProgressScaled(360);
		}

		if (active = hasWorldObj() && recipe != null && !isPowered()) {
			cookCost = recipe.getCost();

			if (crafting = drainAvailablePureVis(cookCost, false) > 0F) {
				cookTime += drainAvailablePureVis(Math.min(0.5F + 0.05F * boost, cookCost - cookTime + 0.01F), true);

				if (soundDelay == 0) {
					worldObj.playSound(null, new BlockPos(getPos().getX() + 0.5F, getPos().getY() + 0.5F, getPos().getZ() + 0.5F), SoundHandler.infuser, SoundCategory.BLOCKS, 0.2F, 1F);
					soundDelay = 62;
				}

				if (cookTime >= cookCost) {
					addProcessedItem(recipe.getResult(), recipe.getComponents());
					reset();
					markDirty();
				}
			} else {
				// pause infusing
			}
		} else {
			if (crafting) {
				worldObj.playSound(null, new BlockPos(getPos().getX() + 0.5F, getPos().getY() + 0.5F, getPos().getZ() + 0.5F), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1F, 1.6F);
			}

			reset();
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