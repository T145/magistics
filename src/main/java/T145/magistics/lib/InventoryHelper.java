package T145.magistics.lib;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.InventoryUtils;

public class InventoryHelper {
	private static final Random RAND = new Random();

	public static void emptyInventory(World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile != null && tile instanceof IInventory) {
			IInventory inv = (IInventory) tile;

			for (int slot = 0; slot < inv.getSizeInventory(); ++slot) {
				ItemStack stack = inv.getStackInSlot(slot);

				if (stack != null) {
					float f = RAND.nextFloat() * 0.8F + 0.1F;
					float f1 = RAND.nextFloat() * 0.8F + 0.1F;
					float f2 = RAND.nextFloat() * 0.8F + 0.1F;

					while (stack.stackSize > 0) {
						int j1 = RAND.nextInt(21) + 10;

						if (j1 > stack.stackSize) {
							j1 = stack.stackSize;
						}

						stack.stackSize -= j1;
						EntityItem item = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(stack.getItem(), j1, stack.getItemDamage()));

						if (stack.hasTagCompound()) {
							item.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						item.motionX = (double) ((float) RAND.nextGaussian() * f3);
						item.motionY = (double) ((float) RAND.nextGaussian() * f3 + 0.2F);
						item.motionZ = (double) ((float) RAND.nextGaussian() * f3);
						world.spawnEntityInWorld(item);
					}
				}
			}

			world.func_147453_f(x, y, z, tile.getBlockType());
		}
	}

	public static void absorbCollidingItemStackIntoInventory(Entity collidingEntity, IInventory inv, Block addEventTo, int eventID, int eventParameter, World world, int x, int y, int z, boolean playSoundEffect) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile != null && !world.isRemote && inv != null && collidingEntity instanceof EntityItem && !collidingEntity.isDead) {
			EntityItem item = (EntityItem) collidingEntity;
			ItemStack leftovers = placeItemStackIntoInventory(item.getEntityItem(), inv, 1, true);

			if (leftovers == null || leftovers.stackSize != item.getEntityItem().stackSize) {
				if (playSoundEffect) {
					world.playSoundAtEntity(collidingEntity, "random.eat", 0.25F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1F);
				}

				world.addBlockEvent(x, y, z, addEventTo, eventID, eventParameter);
			}

			if (leftovers != null) {
				item.setEntityItemStack(leftovers);
			} else {
				collidingEntity.setDead();
			}

			tile.markDirty();
		}
	}

	public static ItemStack placeItemStackIntoInventory(ItemStack stack, IInventory inv, int side, boolean doit) {
		ItemStack itemstack = stack.copy(), itemstack2 = insertStack(inv, itemstack, side, doit);

		if (itemstack2 == null || itemstack2.stackSize == 0) {
			if (doit) {
				inv.markDirty();
			}
			return null;
		}

		stack = itemstack2;
		return stack.copy();
	}

	public static ItemStack insertStack(IInventory inv, ItemStack stack1, int side, boolean doit) {
		if (inv instanceof ISidedInventory && side > -1) {
			ISidedInventory isidedinv = (ISidedInventory) inv;
			int[] aint = isidedinv.getAccessibleSlotsFromSide(side);

			if (aint != null) {
				for (int j = 0; j < aint.length && stack1 != null && stack1.stackSize > 0; ++j) {
					if (inv.getStackInSlot(aint[j]) != null && inv.getStackInSlot(aint[j]).isItemEqual(stack1)) {
						stack1 = attemptInsertion(inv, stack1, aint[j], side, doit);
					}

					if (stack1 == null || stack1.stackSize == 0) {
						break;
					}
				}

				if (stack1 != null && stack1.stackSize > 0) {
					for (int j = 0; j < aint.length && stack1 != null && stack1.stackSize > 0; ++j) {
						stack1 = attemptInsertion(inv, stack1, aint[j], side, doit);

						if (stack1 == null || stack1.stackSize == 0) {
							break;
						}
					}
				}
			}
		} else {
			int k = inv.getSizeInventory();

			for (int l = 0; l < k && stack1 != null && stack1.stackSize > 0; ++l) {
				if (inv.getStackInSlot(l) != null && inv.getStackInSlot(l).isItemEqual(stack1)) {
					stack1 = attemptInsertion(inv, stack1, l, side, doit);
				}

				if (stack1 == null || stack1.stackSize == 0) {
					break;
				}
			}

			if (stack1 != null && stack1.stackSize > 0) {
				if (stack1 != null && stack1.stackSize > 0) {
					for (int m = 0; m < k && stack1 != null && stack1.stackSize > 0; ++m) {
						stack1 = attemptInsertion(inv, stack1, m, side, doit);

						if (stack1 == null || stack1.stackSize == 0) {
							break;
						}
					}
				}
			}
		}

		if (stack1 != null && stack1.stackSize == 0) {
			stack1 = null;
		}

		return stack1;
	}

	public static ItemStack attemptInsertion(IInventory inv, ItemStack stack, int slot, int side, boolean doit) {
		ItemStack slotStack = inv.getStackInSlot(slot);

		if (InventoryUtils.canInsertItemToInventory(inv, stack, slot, side)) {
			boolean flag = false;

			if (slotStack == null) {
				if (inv.getInventoryStackLimit() < stack.stackSize) {
					ItemStack in = stack.splitStack(inv.getInventoryStackLimit());

					if (doit) {
						inv.setInventorySlotContents(slot, in);
					}
				} else {
					if (doit) {
						inv.setInventorySlotContents(slot, stack);
					}

					stack = null;
				}

				flag = true;
			} else if (InventoryUtils.areItemStacksEqualStrict(slotStack, stack)) {
				int k = Math.min(stack.stackSize, Math.min(inv.getInventoryStackLimit() - slotStack.stackSize, stack.getMaxStackSize() - slotStack.stackSize));
				ItemStack itemStack = stack;
				itemStack.stackSize -= k;

				if (doit) {
					ItemStack itemStack2 = slotStack;
					itemStack2.stackSize += k;
				}

				flag = (k > 0);
			}

			if (flag && doit) {
				if (inv instanceof TileEntityHopper) {
					((TileEntityHopper) inv).func_145896_c(8);
					inv.markDirty();
				}

				inv.markDirty();
			}
		}

		return stack;
	}
}