package T145.magistics.tiles;

import T145.magistics.api.tiles.IFacing;
import T145.magistics.api.tiles.IOwned;
import T145.magistics.api.tiles.TileChest;
import T145.magistics.lib.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import thaumcraft.common.lib.utils.InventoryUtils;

public class TileChestHungryEnder extends TileChest implements IFacing, IOwned {
	private int syncDelay = 33;

	public TileChestHungryEnder() {
		super(true, true);
	}

	@Override
	public EntityPlayer getOwner() {
		return PlayerUtils.findPlayerByUUID(ownerUUID);
	}

	@Override
	public void setOwner(EntityPlayer player) {
		ownerName = player.getCommandSenderName();
		ownerUUID = player.getUniqueID();
	}

	@Override
	public boolean isOwned() {
		return ownerName.length() > 0;
	}

	@Override
	public boolean isOwnedBy(EntityPlayer player) {
		return player.getUniqueID().equals(ownerUUID);
	}

	@Override
	public int getFacing() {
		return facing;
	}

	@Override
	public void setFacing(int dir) {
		facing = dir;
	}

	@Override
	public String getInventoryName() {
		return "Hungry Ender Chest";
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (hasWorldObj()) {
			--syncDelay;

			if (syncDelay == 0) {
				syncWithEnderChest();
				syncDelay = 33;
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int id, int data) {
		switch (id) {
		case 1:
			if (lidAngle < data / 10F) {
				lidAngle = data / 10F;
			}
			return true;
		default:
			return super.receiveClientEvent(id, data);
		}
	}

	private void syncWithEnderChest() {
		boolean movedSomething = false;

		for (int slot = 0; slot < inventoryStacks.length; ++slot) {
			movedSomething = moveStackFromSlotToEnderChest(slot);
		}

		if (movedSomething) {
			worldObj.playSoundEffect(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, "mob.endermen.portal", worldObj.rand.nextFloat() * 0.2F + 0.2F, worldObj.rand.nextFloat() * 1.0F + 0.5F);
		}
	}

	private boolean moveStackFromSlotToEnderChest(int slot) {
		if (inventoryStacks[slot] == null) {
			return false;
		}

		EntityPlayer player = getOwner();
		boolean movedSomething = false;

		if (player != null) {
			InventoryEnderChest invEnderChest = player.getInventoryEnderChest();
			ItemStack enderChestStack;

			int invSize = invEnderChest.getSizeInventory();
			int amount = 0;

			for (int i = 0; i < invSize; ++i) {
				enderChestStack = invEnderChest.getStackInSlot(i);

				if (enderChestStack == null) {
					continue;
				}

				if (InventoryUtils.areItemStacksEqualStrict(inventoryStacks[slot], enderChestStack)) {
					amount = Math.min(inventoryStacks[slot].stackSize, invEnderChest.getInventoryStackLimit() - enderChestStack.stackSize);
					amount = Math.min(amount, inventoryStacks[slot].getMaxStackSize());

					if (amount > 0) {
						enderChestStack.stackSize += amount;
						inventoryStacks[slot].stackSize -= amount;
						movedSomething = true;

						if (inventoryStacks[slot].stackSize <= 0) {
							inventoryStacks[slot] = null;
							return true;
						}
					}
				}
			}

			for (int i = 0; i < invSize; ++i) {
				enderChestStack = invEnderChest.getStackInSlot(i);

				if (enderChestStack != null) {
					continue;
				}

				amount = Math.min(inventoryStacks[slot].stackSize, invEnderChest.getInventoryStackLimit());
				amount = Math.min(amount, inventoryStacks[slot].getMaxStackSize());

				if (amount > 0) {
					ItemStack newStack = inventoryStacks[slot].copy();
					newStack.stackSize = amount;
					inventoryStacks[slot].stackSize -= amount;
					invEnderChest.setInventorySlotContents(i, newStack);
					movedSomething = true;

					if (inventoryStacks[slot].stackSize <= 0) {
						inventoryStacks[slot] = null;
						return true;
					}
				}
			}
		}

		return movedSomething;
	}
}