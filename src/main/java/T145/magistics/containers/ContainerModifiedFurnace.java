package T145.magistics.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import T145.magistics.tiles.TileModifiedFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerModifiedFurnace extends Container {
	private TileModifiedFurnace tileFurnace;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;

	public ContainerModifiedFurnace(InventoryPlayer player, TileModifiedFurnace tile) {
		tileFurnace = tile;
		addSlotToContainer(new Slot(tile, 0, 56, 17));
		addSlotToContainer(new Slot(tile, 1, 56, 53));
		addSlotToContainer(new SlotFurnace(player.player, tile, 2, 116, 35));
		int i;

		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i) {
			addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting p_75132_1_) {
		super.addCraftingToCrafters(p_75132_1_);
		p_75132_1_.sendProgressBarUpdate(this, 0, tileFurnace.furnaceCookTime);
		p_75132_1_.sendProgressBarUpdate(this, 1, tileFurnace.furnaceBurnTime);
		p_75132_1_.sendProgressBarUpdate(this, 2, tileFurnace.currentItemBurnTime);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) crafters.get(i);

			if (lastCookTime != tileFurnace.furnaceCookTime) {
				icrafting.sendProgressBarUpdate(this, 0, tileFurnace.furnaceCookTime);
			}

			if (lastBurnTime != tileFurnace.furnaceBurnTime) {
				icrafting.sendProgressBarUpdate(this, 1, tileFurnace.furnaceBurnTime);
			}

			if (lastItemBurnTime != tileFurnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2, tileFurnace.currentItemBurnTime);
			}
		}

		lastCookTime = tileFurnace.furnaceCookTime;
		lastBurnTime = tileFurnace.furnaceBurnTime;
		lastItemBurnTime = tileFurnace.currentItemBurnTime;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
		if (p_75137_1_ == 0) {
			tileFurnace.furnaceCookTime = p_75137_2_;
		}

		if (p_75137_1_ == 1) {
			tileFurnace.furnaceBurnTime = p_75137_2_;
		}

		if (p_75137_1_ == 2) {
			tileFurnace.currentItemBurnTime = p_75137_2_;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return tileFurnace.isUseableByPlayer(p_75145_1_);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotTo) {
		ItemStack itemstack = null;
		Slot slotFrom = (Slot) inventorySlots.get(slotTo);

		if (slotFrom != null && slotFrom.getHasStack()) {
			ItemStack itemstack1 = slotFrom.getStack();
			itemstack = itemstack1.copy();

			if (slotTo == 2) {
				if (!mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}

				slotFrom.onSlotChange(itemstack1, itemstack);
			} else if (slotTo != 1 && slotTo != 0) {
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
					if (!mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (TileModifiedFurnace.isItemFuel(itemstack1)) {
					if (!mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if (slotTo >= 3 && slotTo < 30) {
					if (!mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (slotTo >= 30 && slotTo < 39 && !mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!mergeItemStack(itemstack1, 3, 39, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slotFrom.putStack((ItemStack) null);
			} else {
				slotFrom.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slotFrom.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}
}