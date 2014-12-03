package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import thaumcraft.common.tiles.TileOwned;

public class TileChestHungryEnder extends TileOwned implements ISidedInventory {
	public int playersUsing;
	public float prevLidAngle, lidAngle;

	public void setOwner(String name) {
		owner = name;
	}

	public boolean isOwned() {
		return StringUtils.isNullOrEmpty(owner) ? false : worldObj.getPlayerEntityByName(owner) != null;
	}

	public InventoryEnderChest getEnderInventory() {
		EntityPlayer player = worldObj.getPlayerEntityByName(owner);
		return player == null ? null : player.getInventoryEnderChest();
	}

	@Override
	public int getSizeInventory() {
		return getEnderInventory().getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return getEnderInventory().getStackInSlot(slot);
	}

	@Override
	public ItemStack decrStackSize(int slotFrom, int slotTo) {
		return getEnderInventory().decrStackSize(slotFrom, slotTo);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return getEnderInventory().getStackInSlotOnClosing(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack is) {
		getEnderInventory().setInventorySlotContents(slot, is);
	}

	@Override
	public String getInventoryName() {
		return getEnderInventory().getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName() {
		return getEnderInventory().hasCustomInventoryName();
	}

	@Override
	public int getInventoryStackLimit() {
		return getEnderInventory().getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player == worldObj.getPlayerEntityByName(owner) ? true : false;
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		prevLidAngle = lidAngle;

		if (playersUsing > 0 && lidAngle == 0.0F)
			worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);

		if (playersUsing == 0 && lidAngle > 0.0F || playersUsing > 0 && lidAngle < 1.0F) {
			float oldAngle = lidAngle;

			if (playersUsing > 0)
				lidAngle += 0.1F;
			else
				lidAngle -= 0.1F;

			if (lidAngle > 1.0F)
				lidAngle = 1.0F;

			if (lidAngle < 0.5F && oldAngle >= 0.5F)
				worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);

			if (lidAngle < 0.0F)
				lidAngle = 0.0F;
		}
	}

	@Override
	public boolean receiveClientEvent(int id, int data) {
		switch (id) {
		case 0:
			playersUsing = data;
			return true;
		case 1:
			if (lidAngle < data / 10F)
				lidAngle = data / 10F;
			return true;
		default:
			return false;
		}
	}

	@Override
	public void openInventory() {
		if (playersUsing < 0)
			playersUsing = 0;
		playersUsing++;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 0, playersUsing);
	}

	@Override
	public void closeInventory() {
		playersUsing--;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 0, playersUsing);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return getEnderInventory().isItemValidForSlot(slot, is);
	}

	public static int[] createSlotArray(int first, int count) {
		int[] slots = new int[count];
		for (int k = first; k < first + count; k++)
			slots[k - first] = k;
		return slots;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return createSlotArray(0, 10);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return true;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return true;
	}
}