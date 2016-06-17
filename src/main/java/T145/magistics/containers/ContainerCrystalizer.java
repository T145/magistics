package T145.magistics.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.container.SlotOutput;
import T145.magistics.tiles.TileCrystalizer;

public class ContainerCrystalizer extends Container {
	private TileCrystalizer crystalizer;
	private int lastCookTime = 0;

	public ContainerCrystalizer(InventoryPlayer player, TileCrystalizer tile) {
		crystalizer = tile;
		addSlotToContainer(new Slot(tile, 6, 80, 70));
		addSlotToContainer(new SlotOutput(tile, 0, 80, 12));
		addSlotToContainer(new SlotOutput(tile, 1, 131, 41));
		addSlotToContainer(new SlotOutput(tile, 2, 30, 41));
		addSlotToContainer(new SlotOutput(tile, 3, 30, 100));
		addSlotToContainer(new SlotOutput(tile, 4, 131, 100));
		addSlotToContainer(new SlotOutput(tile, 5, 80, 129));

		for (int posXZ = 0; posXZ < 3; ++posXZ) {
			for (int posY = 0; posY < 9; ++posY) {
				addSlotToContainer(new Slot(player, posY + posXZ * 9 + 9, 8 + posY * 18, 158 + posXZ * 18));
			}
		}

		for (int posXZ = 0; posXZ < 9; ++posXZ) {
			addSlotToContainer(new Slot(player, posXZ, 8 + posXZ * 18, 216));
		}
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) crafters.get(i);

			if (lastCookTime != crystalizer.crystalTime) {
				icrafting.sendProgressBarUpdate(this, 0, Math.round(crystalizer.crystalTime));
			}
		}

		lastCookTime = Math.round(crystalizer.crystalTime);
	}

	@Override
	public void updateProgressBar(int eventID, int data) {
		if (eventID == 0) {
			crystalizer.crystalTime = (float) data;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return crystalizer.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot matchingSlot = (Slot) inventorySlots.get(slot);

		if (matchingSlot != null && matchingSlot.getHasStack()) {
			ItemStack stackInSlot = matchingSlot.getStack();
			stack = stackInSlot.copy();

			if (slot < 7) {
				if (!mergeItemStack(stackInSlot, 7, 34, true)) {
					return null;
				}
			} else if (slot >= 7 && slot <= 34) {
				if (!mergeItemStack(stackInSlot, 0, 1, false)) {
					return null;
				}
			} else if (slot > 34 && slot <= 43) {
				if (!mergeItemStack(stackInSlot, 7, 34, false)) {
					return null;
				}
			} else if (!mergeItemStack(stackInSlot, 7, 43, false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				matchingSlot.putStack((ItemStack) null);
			} else {
				matchingSlot.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}

			matchingSlot.onPickupFromSlot(player, stackInSlot);
		}

		return stack;
	}
}