package T145.magistics.common.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import T145.magistics.common.blocks.BlockChestHungryMod;

public class ContainerChestHungryMod extends Container {
	private BlockChestHungryMod.Types types;
	private IInventory chest, player;

	public ContainerChestHungryMod(IInventory playerInventory, IInventory chestInventory, BlockChestHungryMod.Types chestType, int xSize, int ySize) {
		chest = chestInventory;
		player = playerInventory;
		types = chestType;
		chestInventory.openInventory();

		if (types == types.dirt)
			addSlotToContainer(new Slot(chestInventory, 0, 12 + 4 * 18, 8 + 2 * 18));
		else
			for (int chestRow = 0; chestRow < types.getRowCount(); chestRow++)
				for (int chestCol = 0; chestCol < types.rowLength; chestCol++)
					addSlotToContainer(new Slot(chestInventory, chestCol + chestRow * types.rowLength, 12 + chestCol * 18, 8 + chestRow * 18));

		int leftCol = (xSize - 162) / 2 + 1;
		for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++)
			for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++)
				addSlotToContainer(new Slot(playerInventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, ySize - (4 - playerInvRow) * 18 - 10));
		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++)
			addSlotToContainer(new Slot(playerInventory, hotbarSlot, leftCol + hotbarSlot * 18, ySize - 24));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return chest.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack newStack = null;
		Slot designateSlot = (Slot) inventorySlots.get(slot);
		if (designateSlot != null && designateSlot.getHasStack()) {
			ItemStack oldStack = designateSlot.getStack();
			newStack = oldStack.copy();
			if (slot < types.size) {
				if (!mergeItemStack(oldStack, types.size, inventorySlots.size(), true))
					return null;
			} else if (!mergeItemStack(oldStack, 0, types.size, false) || !chest.isItemValidForSlot(slot, oldStack))
				return null;
			if (oldStack.stackSize == 0)
				designateSlot.putStack(null);
			else
				designateSlot.onSlotChanged();
		}
		return newStack;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		chest.closeInventory();
	}
}