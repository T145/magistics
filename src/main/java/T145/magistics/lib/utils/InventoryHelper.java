package T145.magistics.lib.utils;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InventoryHelper {
	public static ItemStack decrStackSize(IInventory inv, int slot, int size) {
		ItemStack item = inv.getStackInSlot(slot);

		if (item != null) {
			if (item.stackSize <= size) {
				inv.setInventorySlotContents(slot, null);
				inv.markDirty();
				return item;
			}

			ItemStack newStack = item.splitStack(size);

			if (item.stackSize == 0) {
				inv.setInventorySlotContents(slot, null);
			} else {
				inv.setInventorySlotContents(slot, item);
			}

			inv.markDirty();
			return newStack;
		}

		return null;
	}

	public static ItemStack getStackInSlotOnClosing(IInventory inv, int slot) {
		ItemStack stack = inv.getStackInSlot(slot);
		inv.setInventorySlotContents(slot, null);
		return stack;
	}

	public static void setInventorySlotContents(IInventory inv, ItemStack[] contents, int slot, ItemStack stack) {
		contents[slot] = stack;

		if (stack != null && stack.stackSize > inv.getInventoryStackLimit()) {
			stack.stackSize = inv.getInventoryStackLimit();
		}
	}

	public static void removeItem(IInventory inv, ItemStack target) {
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) != null) {
				ItemStack j = inv.getStackInSlot(i);

				if (j.getItem() != null && j.getItem() == target.getItem()) {
					inv.setInventorySlotContents(i, null);
				}
			}
		}
	}

	public static void emptyInventory(World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		IInventory inv = (IInventory) tile;

		if (inv != null) {
			for (int slot = 0; slot < inv.getSizeInventory(); ++slot) {
				ItemStack stack = inv.getStackInSlot(slot);
				dropStack(stack, world, pos.getX(), pos.getY(), pos.getZ());
			}

			//world.func_147453_f(pos.getX(), pos.getY(), pos.getZ(), tile.getBlockType());
		}
	}

	public static void dropStack(ItemStack stack, World world, int x, int y, int z) {
		if (stack != null) {
			float f = world.rand.nextFloat() * 0.8F + 0.1F;
			float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
			float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

			while (stack.stackSize > 0) {
				int j1 = world.rand.nextInt(21) + 10;

				if (j1 > stack.stackSize) {
					j1 = stack.stackSize;
				}

				stack.stackSize -= j1;
				EntityItem item = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(stack.getItem(), j1, stack.getItemDamage()));

				if (stack.hasTagCompound()) {
					item.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
				}

				float f3 = 0.05F;
				item.motionX = (double) ((float) world.rand.nextGaussian() * f3);
				item.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
				item.motionZ = (double) ((float) world.rand.nextGaussian() * f3);
				world.spawnEntityInWorld(item);
			}
		}
	}
}