package T145.magistics.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.tiles.TileOwned;
import T145.magistics.common.lib.InventoryHelper;

public class TileChestHungryEnder extends TileOwned implements ISidedInventory, IWandable {
	public int numPlayersUsing;
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

		if (numPlayersUsing > 0 && lidAngle == 0.0F)
			worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);

		if (numPlayersUsing == 0 && lidAngle > 0.0F || numPlayersUsing > 0 && lidAngle < 1.0F) {
			float oldAngle = lidAngle;

			if (numPlayersUsing > 0)
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
			numPlayersUsing = data;
			return true;
		case 1:
			if (lidAngle < data / 10F)
				lidAngle = data / 10F;
			return true;
		default:
			return super.receiveClientEvent(id, data);
		}
	}

	@Override
	public void openInventory() {
		if (numPlayersUsing < 0)
			numPlayersUsing = 0;
		numPlayersUsing++;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 0, numPlayersUsing);
	}

	@Override
	public void closeInventory() {
		numPlayersUsing--;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 0, numPlayersUsing);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return getEnderInventory().isItemValidForSlot(slot, is);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return InventoryHelper.createSlotArray(0, 10);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return true;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return true;
	}

	public boolean onWanded(EntityPlayer player, World world, int i, int j, int k, int side) {
		if (player.isSneaking() && isOwnedBy(player)) {
			world.setBlockMetadataWithNotify(i, j, k, side, 2);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			player.worldObj.playSound(i + 0.5, j + 0.5, k + 0.5, "thaumcraft:tool", 0.3F, 1.9F + player.worldObj.rand.nextFloat() * 0.2F, false);
			player.swingItem();
			markDirty();
		}
		return true;
	}

	@Override
	public int onWandRightClick(World world, ItemStack wand, EntityPlayer player, int i, int j, int k, int side, int meta) {
		onWanded(player, world, i, j, k, side);
		return 0;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wand, EntityPlayer player) {
		return null;
	}

	@Override
	public void onUsingWandTick(ItemStack wand, EntityPlayer player, int count) {}

	@Override
	public void onWandStoppedUsing(ItemStack wand, World world, EntityPlayer player, int count) {}
}