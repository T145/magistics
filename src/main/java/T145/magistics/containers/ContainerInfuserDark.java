package T145.magistics.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.container.SlotOutput;
import T145.magistics.tiles.TileInfuserDark;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerInfuserDark extends Container {
	private TileInfuserDark infuser;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;

	public ContainerInfuserDark(InventoryPlayer var1, TileInfuserDark var2) {
		infuser = var2;
		addSlotToContainer(new Slot(var2, 1, 80, 16));
		addSlotToContainer(new Slot(var2, 2, 132, 54));
		addSlotToContainer(new Slot(var2, 3, 111, 118));
		addSlotToContainer(new Slot(var2, 4, 48, 118));
		addSlotToContainer(new Slot(var2, 5, 25, 54));
		addSlotToContainer(new SlotOutput(var2, 0, 80, 72));
		int var3;

		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				addSlotToContainer(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 158 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3) {
			addSlotToContainer(new Slot(var1, var3, 8 + var3 * 18, 216));
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting p_75132_1_) {
		super.addCraftingToCrafters(p_75132_1_);
		p_75132_1_.sendProgressBarUpdate(this, 0, infuser.infuserCookTime);
		p_75132_1_.sendProgressBarUpdate(this, 1, infuser.infuserBurnTime);
		p_75132_1_.sendProgressBarUpdate(this, 2, infuser.currentItemBurnTime);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) crafters.get(i);

			if (lastCookTime != infuser.infuserCookTime) {
				icrafting.sendProgressBarUpdate(this, 0, infuser.infuserCookTime);
			}

			if (lastBurnTime != infuser.infuserBurnTime) {
				icrafting.sendProgressBarUpdate(this, 1, infuser.infuserBurnTime);
			}

			if (lastItemBurnTime != infuser.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2, infuser.currentItemBurnTime);
			}
		}

		lastCookTime = infuser.infuserCookTime;
		lastBurnTime = infuser.infuserBurnTime;
		lastItemBurnTime = infuser.currentItemBurnTime;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
		if (p_75137_1_ == 0) {
			infuser.infuserCookTime = p_75137_2_;
		}

		if (p_75137_1_ == 1) {
			infuser.infuserBurnTime = p_75137_2_;
		}

		if (p_75137_1_ == 2) {
			infuser.currentItemBurnTime = p_75137_2_;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return infuser.isUseableByPlayer(var1);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int var1) {
		ItemStack var2 = null;
		Slot var3 = (Slot) inventorySlots.get(var1);

		if (var3 != null && var3.getHasStack()) {
			ItemStack var4 = var3.getStack();
			var2 = var4.copy();

			if (var1 < 8) {
				if (!mergeItemStack(var4, 8, 35, true)) {
					return null;
				}
			} else if (var1 >= 8 && var1 <= 35) {
				if (!mergeItemStack(var4, 0, 6, false)) {
					return null;
				}
			} else if (var1 > 35 && var1 <= 44) {
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