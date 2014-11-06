package T145.magistics.common.lib;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.InventoryUtils;

public class MagisticsUtils {
	public static void absorbCollidingItemStackIntoInventory(Entity collidingEntity, TileEntity tile, IInventory inventory, Block addEventTo, int eventID, int eventParameter, World world, int i, int j, int k, boolean playSoundEffect) {
		if (tile != null && !world.isRemote && inventory != null && collidingEntity instanceof EntityItem && !collidingEntity.isDead) {
			EntityItem item = (EntityItem) collidingEntity;
			ItemStack leftovers = placeItemStackIntoInventory(item.getEntityItem(), inventory, 1, true);

			if (leftovers == null || leftovers.stackSize != item.getEntityItem().stackSize) {
				if (playSoundEffect)
					world.playSoundAtEntity(collidingEntity, "random.eat", 0.25F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
				world.addBlockEvent(i, j, k, addEventTo, eventID, eventParameter);
			}
			if (leftovers != null)
				item.setEntityItemStack(leftovers);
			else
				collidingEntity.setDead();
			tile.markDirty();
		}
	}

	public static ItemStack placeItemStackIntoInventory(ItemStack stack, IInventory inventory, int side, boolean doit) {
		ItemStack itemstack = stack.copy();
		ItemStack itemstack2 = insertStack(inventory, itemstack, side, doit);
		if (itemstack2 == null || itemstack2.stackSize == 0) {
			if (doit)
				inventory.markDirty();
			return null;
		}
		stack = itemstack2;
		return stack.copy();
	}

	public static ItemStack insertStack(IInventory inventory, ItemStack stack1, int side, boolean doit) {
		if (inventory instanceof ISidedInventory && side > -1) {
			ISidedInventory isidedinventory = (ISidedInventory) inventory;
			int[] aint = isidedinventory.getAccessibleSlotsFromSide(side);
			if (aint != null) {
				for (int j = 0; j < aint.length && stack1 != null && stack1.stackSize > 0; ++j) {
					if (inventory.getStackInSlot(aint[j]) != null && inventory.getStackInSlot(aint[j]).isItemEqual(stack1))
						stack1 = attemptInsertion(inventory, stack1, aint[j], side, doit);
					if (stack1 == null || stack1.stackSize == 0)
						break;
				}
			}
			if (aint != null && stack1 != null && stack1.stackSize > 0) {
				for (int j = 0; j < aint.length && stack1 != null && stack1.stackSize > 0; ++j) {
					stack1 = attemptInsertion(inventory, stack1, aint[j], side, doit);
					if (stack1 == null || stack1.stackSize == 0)
						break;
				}
			}
		} else {
			int k = inventory.getSizeInventory();
			for (int l = 0; l < k && stack1 != null && stack1.stackSize > 0; ++l) {
				if (inventory.getStackInSlot(l) != null && inventory.getStackInSlot(l).isItemEqual(stack1))
					stack1 = attemptInsertion(inventory, stack1, l, side, doit);
				if (stack1 == null || stack1.stackSize == 0)
					break;
			}
			if (stack1 != null && stack1.stackSize > 0) {
				if (stack1 != null && stack1.stackSize > 0) {
					for (int m = 0; m < k && stack1 != null && stack1.stackSize > 0; ++m) {
						stack1 = attemptInsertion(inventory, stack1, m, side, doit);
						if (stack1 == null || stack1.stackSize == 0)
							break;
					}
				}
			}
		}
		if (stack1 != null && stack1.stackSize == 0)
			stack1 = null;
		return stack1;
	}

	public static ItemStack attemptInsertion(IInventory inventory, ItemStack stack, int slot, int side, boolean doit) {
		ItemStack slotStack = inventory.getStackInSlot(slot);
		if (InventoryUtils.canInsertItemToInventory(inventory, stack, slot, side)) {
			boolean flag = false;
			if (slotStack == null) {
				if (inventory.getInventoryStackLimit() < stack.stackSize) {
					ItemStack in = stack.splitStack(inventory.getInventoryStackLimit());
					if (doit)
						inventory.setInventorySlotContents(slot, in);
				} else {
					if (doit)
						inventory.setInventorySlotContents(slot, stack);
					stack = null;
				}
				flag = true;
			} else if (InventoryUtils.areItemStacksEqualStrict(slotStack, stack)) {
				int k = Math.min(stack.stackSize, Math.min(inventory.getInventoryStackLimit() - slotStack.stackSize, stack.getMaxStackSize() - slotStack.stackSize));
				ItemStack itemStack = stack;
				itemStack.stackSize -= k;
				if (doit) {
					ItemStack itemStack2 = slotStack;
					itemStack2.stackSize += k;
				}
				flag = (k > 0);
			}
			if (flag && doit) {
				if (inventory instanceof TileEntityHopper) {
					((TileEntityHopper) inventory).func_145896_c(8);
					inventory.markDirty();
				}
				inventory.markDirty();
			}
		}
		return stack;
	}
}