package T145.magistics.common.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import T145.magistics.common.tiles.TileThinkTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerThinkTank extends Container {
	private TileThinkTank furnace;
	private int lastCookTime, lastBurnTime, lastItemBurnTime;

	public ContainerThinkTank(InventoryPlayer playerInv, TileThinkTank par2TileEntityThinkTank) {
		furnace = par2TileEntityThinkTank;

		addSlotToContainer(new Slot(par2TileEntityThinkTank, 0, 64, 16));
		addSlotToContainer(new SlotFurnace(playerInv.player, par2TileEntityThinkTank, 1, 64, 48));
		int i;

		for (i = 0; i < 3; ++i)
			for (int j = 0; j < 9; ++j)
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

		for (i = 0; i < 9; ++i)
			addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
	}

	public void addCraftingToCrafters(ICrafting slot) {
		super.addCraftingToCrafters(slot);
		slot.sendProgressBarUpdate(this, 0, furnace.cookTime);
		slot.sendProgressBarUpdate(this, 1, furnace.burnTime);
		slot.sendProgressBarUpdate(this, 2, furnace.currentItemBurnTime);
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) crafters.get(i);

			if (lastCookTime != furnace.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0, furnace.cookTime);
			}

			if (lastBurnTime != furnace.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1, furnace.burnTime);
			}

			if (lastItemBurnTime != furnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2, furnace.currentItemBurnTime);
			}
		}

		lastCookTime = furnace.cookTime;
		lastBurnTime = furnace.burnTime;
		lastItemBurnTime = furnace.currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			furnace.cookTime = par2;
		}

		if (par1 == 1) {
			furnace.burnTime = par2;
		}

		if (par1 == 2) {
			furnace.currentItemBurnTime = par2;
		}
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return furnace.isUseableByPlayer(par1EntityPlayer);
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 2) {

				return null;
			} else if (par2 != 1 && par2 != 0) {
				if (itemstack1.getItem() == Items.book || itemstack1.getItem() == Items.enchanted_book) {
					if (!mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				}

				else if (par2 >= 2 && par2 < 30) {
					if (!mergeItemStack(itemstack1, 30, 38, false)) {
						return null;
					}
				} else if (par2 >= 30 && par2 < 39 && !mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!mergeItemStack(itemstack1, 3, 38, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
				return null;

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}
}