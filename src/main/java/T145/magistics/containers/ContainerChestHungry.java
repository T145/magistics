package T145.magistics.containers;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import T145.magistics.tiles.devices.TileChestHungry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerChestHungry extends Container {

	private final TileChestHungry chest;
	private final IItemHandlerModifiable chestInventory;

	public ContainerChestHungry(InventoryPlayer playerInventory, TileChestHungry chest) {
		this.chest = chest;
		this.chestInventory = chest.getItemHandler();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new SlotItemHandler(chestInventory, j + i * 9, 8 + j * 18, 18 + i * 18));
			}
		}

		layoutPlayerInventory(playerInventory, 85);
		chest.openChest(playerInventory.player);
	}

	public void layoutPlayerInventory(InventoryPlayer playerInventory, int offsetY) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, offsetY + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, offsetY + 58));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack copyStack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			copyStack = slotStack.copy();

			if (index < chest.getInventorySize()) {
				if (!mergeItemStack(slotStack, chest.getInventorySize(), inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!mergeItemStack(slotStack, 0, chest.getInventorySize(), false)) {
				return ItemStack.EMPTY;
			}

			if (slotStack.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return copyStack;
	}

	@Override
	@OverridingMethodsMustInvokeSuper
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		chest.closeChest(player);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return chest.canInteractWith(player);
	}
}