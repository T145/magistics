package T145.magistics.containers;

import T145.magistics.tiles.TileInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerInfuser extends Container {
	private TileInfuser infuser;
	private int burnTime;
	private int itemBurnTime;
	private int cookTime;
	private int totalCookTime;

	public ContainerInfuser(InventoryPlayer inv, TileInfuser tile) {
		infuser = tile;

		if (infuser.isDark()) {
			addSlotToContainer(new Slot(tile, 1, 80, 16));
			addSlotToContainer(new Slot(tile, 2, 132, 54));
			addSlotToContainer(new Slot(tile, 3, 111, 118));
			addSlotToContainer(new Slot(tile, 4, 48, 118));
			addSlotToContainer(new Slot(tile, 5, 25, 54));
			addSlotToContainer(new SlotOutput(tile, 0, 80, 72));
			int slot;

			for (slot = 0; slot < 3; ++slot) {
				for (int offset = 0; offset < 9; ++offset) {
					addSlotToContainer(new Slot(inv, offset + slot * 9 + 9, 8 + offset * 18, 158 + slot * 18));
				}
			}

			for (slot = 0; slot < 9; ++slot) {
				addSlotToContainer(new Slot(inv, slot, 8 + slot * 18, 216));
			}
		} else {
			addSlotToContainer(new Slot(tile, 2, 80, 11));
			addSlotToContainer(new Slot(tile, 3, 28, 102));
			addSlotToContainer(new Slot(tile, 4, 132, 102));
			addSlotToContainer(new Slot(tile, 5, 50, 55));
			addSlotToContainer(new Slot(tile, 6, 110, 55));
			addSlotToContainer(new Slot(tile, 7, 80, 106));
			addSlotToContainer(new SlotOutput(tile, 0, 80, 72));
			addSlotToContainer(new SlotOutput(tile, 1, 80, 135));
			int slot;

			for (slot = 0; slot < 3; ++slot) {
				for (int offset = 0; offset < 9; ++offset) {
					addSlotToContainer(new Slot(inv, offset + slot * 9 + 9, 8 + offset * 18, 158 + slot * 18));
				}
			}

			for (slot = 0; slot < 9; ++slot) {
				addSlotToContainer(new Slot(inv, slot, 8 + slot * 18, 216));
			}
		}
	}

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, infuser);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < listeners.size(); ++i) {
			IContainerListener icontainerlistener = (IContainerListener) listeners.get(i);

			if (cookTime != infuser.getField(2)) {
				icontainerlistener.sendProgressBarUpdate(this, 2, infuser.getField(2));
			}

			if (burnTime != infuser.getField(0)) {
				icontainerlistener.sendProgressBarUpdate(this, 0, infuser.getField(0));
			}

			if (itemBurnTime != infuser.getField(1)) {
				icontainerlistener.sendProgressBarUpdate(this, 1, infuser.getField(1));
			}

			if (totalCookTime != infuser.getField(3)) {
				icontainerlistener.sendProgressBarUpdate(this, 3, infuser.getField(3));
			}
		}

		cookTime = infuser.getField(2);
		burnTime = infuser.getField(0);
		itemBurnTime = infuser.getField(1);
		totalCookTime = infuser.getField(3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		infuser.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return infuser.isUseableByPlayer(var1);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack var2 = null;
		Slot var3 = (Slot) inventorySlots.get(slot);

		if (var3 != null && var3.getHasStack()) {
			ItemStack var4 = var3.getStack();
			var2 = var4.copy();

			if (slot < 8) {
				if (!mergeItemStack(var4, 8, 35, true)) {
					return null;
				}
			} else if (slot >= 8 && slot <= 35) {
				if (!mergeItemStack(var4, 0, 6, false)) {
					return null;
				}
			} else if (slot > 35 && slot <= 44) {
				if (!mergeItemStack(var4, 8, 35, false)) {
					return null;
				}
			} else if (!mergeItemStack(var4, 8, 44, false)) {
				return null;
			}

			if (var4.stackSize == 0) {
				var3.putStack((ItemStack) null);
			} else {
				var3.onSlotChanged();
			}

			if (var4.stackSize == var2.stackSize) {
				return null;
			}

			var3.onPickupFromSlot(player, var4);
		}

		return var2;
	}
}