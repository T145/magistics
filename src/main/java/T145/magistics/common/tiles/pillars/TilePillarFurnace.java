package T145.magistics.common.tiles.pillars;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.EnumSkyBlock;
import T145.magistics.common.Magistics;

public class TilePillarFurnace extends TileBase implements IInventory, ISidedInventory {
	private ItemStack[] inventory = new ItemStack[getSizeInventory()];
	public int burnTime, cookTime, xp;

	public float rot = 0F;
	public boolean showNum = false;

	private int prevLightValue = 0;

	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			rot += 0.1F;
			if (rot >= 360F)
				rot -= 360F;

			int lightValue = getLightLevel();
			if (prevLightValue != lightValue) {
				prevLightValue = lightValue;
				worldObj.updateLightByType(EnumSkyBlock.Block, xCoord, yCoord, zCoord);
			}
		}

		if (burnTime > 0)
			burnTime--;
		else if (canBurn())
			burnItem();

		if (burnTime > 0 && canSmelt())
			if (cookTime > 0)
				cookTime--;
			else
				smeltItem();

		super.updateEntity();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		inventory = new ItemStack[getSizeInventory()];
		NBTTagList nbtlist = nbt.getTagList("Items", 10);
		for (int i = 0; i < nbtlist.tagCount(); i++) {
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
			int j = nbtslot.getByte("Slot") & 255;

			if ((j >= 0) && (j < getSizeInventory()))
				inventory[j] = ItemStack.loadItemStackFromNBT(nbtslot);
		}

		burnTime = nbt.getInteger("BurnTime");
		cookTime = nbt.getInteger("CookTime");
		showNum = nbt.getBoolean("showNum");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setInteger("BurnTime", burnTime);
		nbt.setInteger("CookTime", cookTime);

		NBTTagList nbtlist = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); i++) {
			if (inventory[i] != null) {
				NBTTagCompound nbtslot = new NBTTagCompound();
				nbtslot.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbtslot);
				nbtlist.appendTag(nbtslot);
			}
		}
		nbt.setTag("Items", nbtlist);
		nbt.setBoolean("showNum", showNum);
	}

	@Override
	public void onInventoryChanged() {
		super.onInventoryChanged();

		if (!worldObj.isRemote)
			Magistics.proxy.sendToPlayers(getDescriptionPacket(), worldObj, xCoord, yCoord, zCoord, 64);
	}

	public void dropItemFromSlot(int slot, int amount, EntityPlayer player) {
		if (!worldObj.isRemote && getStackInSlot(slot) != null) {
			EntityItem itemEntity = new EntityItem(worldObj, player.posX, player.posY, player.posZ);
			itemEntity.setEntityItemStack(decrStackSize(slot, amount));
			worldObj.spawnEntityInWorld(itemEntity);

			onInventoryChanged();
		}
	}

	@Override
	public int getSizeInventory() {
		return 3;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();

		onInventoryChanged();
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = null;

		if (inventory[slot] != null) {
			if (inventory[slot].stackSize <= amount) {
				stack = inventory[slot];
				inventory[slot] = null;
				onInventoryChanged();
			} else {
				stack = inventory[slot].splitStack(amount);

				if (inventory[slot].stackSize == 0)
					inventory[slot] = null;

				onInventoryChanged();
			}
		}

		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
			setInventorySlotContents(slot, null);

		return stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public String getInventoryName() {
		return "Furnace Pillar";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] { 0, 1, 2 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		if (slot == 0 && FurnaceRecipes.smelting().getSmeltingResult(itemstack) != null && (inventory[slot] == null || inventory[slot].isItemEqual(itemstack)))
			return true;
		if (slot == 1 && TileEntityFurnace.isItemFuel(itemstack) && (inventory[slot] == null || inventory[slot].isItemEqual(itemstack)))
			return true;
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		return slot == 2;
	}

	public boolean canSmelt() {
		if (inventory[0] == null)
			return false;
		ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]);
		if (result == null)
			return false;
		if (inventory[2] != null) {
			if (!inventory[2].isItemEqual(result))
				return false;
			if (inventory[2].stackSize + result.stackSize >= result.getMaxStackSize())
				return false;
			if (inventory[2].stackSize + result.stackSize >= getInventoryStackLimit())
				return false;
		}
		if (result.stackSize >= getInventoryStackLimit())
			return false;
		return true;
	}

	public void smeltItem() {
		cookTime = 150;
		if (worldObj.isRemote)
			return;

		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]);
		xp += FurnaceRecipes.smelting().func_151398_b(inventory[0]);

		if (inventory[2] == null)
			inventory[2] = itemstack.copy();
		else if (inventory[2].isItemEqual(itemstack))
			inventory[2].stackSize += itemstack.stackSize;

		inventory[0].stackSize--;

		if (inventory[0].stackSize <= 0)
			inventory[0] = null;

		onInventoryChanged();
	}

	public boolean canBurn() {
		if (inventory[1] == null)
			return false;
		if (TileEntityFurnace.getItemBurnTime(inventory[1]) <= 0)
			return false;
		return canSmelt();
	}

	public void burnItem() {
		if (worldObj.isRemote)
			return;

		if (cookTime == 0)
			cookTime = 150;
		burnTime = TileEntityFurnace.getItemBurnTime(inventory[1]);
		inventory[1].stackSize--;
		if (inventory[1].stackSize <= 0)
			inventory[1] = null;
		onInventoryChanged();
	}

	public int getLightLevel() {
		return burnTime > 0 ? 14 : 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return super.isUseableByPlayer(player);
	}
}