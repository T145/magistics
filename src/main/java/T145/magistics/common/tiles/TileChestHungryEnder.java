package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import thaumcraft.common.tiles.TileOwned;

public class TileChestHungryEnder extends TileOwned implements IInventory {
	public int numUsingPlayers;
	public float prevLidAngle, lidAngle;

	public void setOwner(String name) {
		owner = name;
	}

	public boolean isOwnedBy(EntityPlayer player) {
		return player.getCommandSenderName().equals(owner);
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
		return isOwnedBy(player) ? true : false;
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		prevLidAngle = lidAngle;

		if (numUsingPlayers > 0 && lidAngle == 0.0F)
			worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);

		if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F) {
			float oldAngle = lidAngle;

			if (numUsingPlayers > 0)
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
			numUsingPlayers = data;
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
		if (numUsingPlayers < 0)
			numUsingPlayers = 0;
		numUsingPlayers++;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 0, numUsingPlayers);
	}

	@Override
	public void closeInventory() {
		numUsingPlayers--;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 0, numUsingPlayers);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return getEnderInventory().isItemValidForSlot(slot, is);
	}
}