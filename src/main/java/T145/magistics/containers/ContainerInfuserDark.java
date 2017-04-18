package T145.magistics.containers;

import T145.magistics.tiles.machines.TileInfuserDark;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerInfuserDark extends Container {

	private final TileInfuserDark infuser;
	private final IItemHandlerModifiable infuserInventory;
	private float cookCost;
	private float cookTime;

	public ContainerInfuserDark(InventoryPlayer playerInventory, TileInfuserDark infuser) {
		this.infuser = infuser;
		this.infuserInventory = infuser.getItemHandler();

		addSlotToContainer(new SlotItemHandler(infuserInventory, 1, 80, 16));
		addSlotToContainer(new SlotItemHandler(infuserInventory, 2, 132, 54));
		addSlotToContainer(new SlotItemHandler(infuserInventory, 3, 111, 118));
		addSlotToContainer(new SlotItemHandler(infuserInventory, 4, 48, 118));
		addSlotToContainer(new SlotItemHandler(infuserInventory, 5, 25, 54));
		addSlotToContainer(new SlotItemOutput(infuserInventory, 0, 80, 72));

		for (int slot = 0; slot < 3; ++slot) {
			for (int offset = 0; slot < 9; ++slot) {
				addSlotToContainer(new Slot(playerInventory, slot + slot * 9 + 9, 8 + slot * 18, 158 + slot * 18));
			}
		}

		for (int slot = 0; slot < 9; ++slot) {
			addSlotToContainer(new Slot(playerInventory, slot, 8 + slot * 18, 216));
		}
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < listeners.size(); ++i) {
			IContainerListener listener = listeners.get(i);

			if (cookCost != infuser.cookCost) {
				listener.sendProgressBarUpdate(this, 0, (int) infuser.cookCost);
			}

			if (cookTime != infuser.cookTime) {
				listener.sendProgressBarUpdate(this, 1, (int) infuser.cookTime);
			}
		}

		cookCost = infuser.cookCost;
		cookTime = infuser.cookTime;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		switch (id) {
		case 0:
			cookCost = data;
			break;
		case 1:
			cookTime = data;
			break;
		default:
			break;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack copyStack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			copyStack = slotStack.copy();

			if (index < 6) {
				if (!mergeItemStack(slotStack, 6, 33, true)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= 6 && index <= 33) {
				if (!mergeItemStack(slotStack, 0, 5, false)) {
					return ItemStack.EMPTY;
				}
			} else if (index > 33 && index <= 42) {
				if (!mergeItemStack(slotStack, 6, 33, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!mergeItemStack(slotStack, 6, 42, false)) {
				return ItemStack.EMPTY;
			}

			if (slotStack.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (slotStack.getCount() == copyStack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, slotStack);
		}

		return copyStack;
	}
}