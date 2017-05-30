package T145.magistics.lib.managers;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import T145.magistics.tiles.MTileInventory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.VanillaDoubleChestItemHandler;

public class InventoryManager {

	public static int calcRedstone(@Nullable TileEntity tile) {
		return tile instanceof MTileInventory ? calcRedstoneFromInventory((MTileInventory) tile) : 0;
	}

	public static int calcRedstoneFromInventory(@Nullable MTileInventory inv) {
		if (inv == null) {
			return 0;
		} else {
			int i = 0;
			float f = 0.0F;

			for (int j = 0; j < inv.getSizeInventory(); ++j) {
				ItemStack itemstack = inv.getItemHandler().getStackInSlot(j);

				if (!itemstack.isEmpty()) {
					f += itemstack.getCount() / Math.min(64, itemstack.getMaxStackSize());
					++i;
				}
			}

			f = f / inv.getSizeInventory();
			return MathHelper.floor(f * 14.0F) + (i > 0 ? 1 : 0);
		}
	}

	public static IItemHandler getInventory(World world, BlockPos pos, EnumFacing side) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile == null) {
			return null;
		}

		if (tile instanceof TileEntityChest) {
			IItemHandler doubleChest = VanillaDoubleChestItemHandler.get((TileEntityChest) tile);

			if (doubleChest != VanillaDoubleChestItemHandler.NO_ADJACENT_CHESTS_INSTANCE) {
				return doubleChest;
			}
		}

		IItemHandler handler = tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side) ? tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side) : null;

		if (handler == null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
			handler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		}

		return handler;
	}

	public static void dropInventory(MTileInventory inv, World world, IBlockState state, BlockPos pos) {
		if (inv != null) {
			for (int slot = 0; slot < inv.getSizeInventory(); ++slot) {
				ItemStack stack = inv.getItemHandler().getStackInSlot(slot);

				if (!stack.isEmpty()) {
					InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
				}
			}

			world.updateComparatorOutputLevel(pos, state.getBlock());
		}
	}

	public static void takeFromInventory(MTileInventory inv, EntityPlayer player) {
		for (int slot = inv.getSizeInventory() - 1; slot >= 0; slot--) {
			ItemStack stack = inv.getItemHandler().getStackInSlot(slot);

			if (!stack.isEmpty()) {
				ItemStack copy = stack.copy();
				ItemHandlerHelper.giveItemToPlayer(player, copy);
				inv.getItemHandler().setStackInSlot(slot, ItemStack.EMPTY);
				player.world.updateComparatorOutputLevel(inv.getPos(), null);
				break;
			}
		}
	}

	public static ItemStack tryInsertItemStackToInventory(IItemHandler inv, @Nonnull ItemStack stack) {
		return tryInsertItemStackToInventoryWithinSlotRange(inv, stack, new SlotRange(inv));
	}

	public static ItemStack tryInsertItemStackToInventoryWithinSlotRange(IItemHandler inv, @Nonnull ItemStack stack, SlotRange slotRange) {
		final int lastSlot = Math.min(slotRange.lastInc, inv.getSlots() - 1);

		for (int slot = slotRange.first; slot <= lastSlot; slot++) {
			if (inv.getStackInSlot(slot).isEmpty() == false) {
				stack = inv.insertItem(slot, stack, false);

				if (stack.isEmpty()) {
					return ItemStack.EMPTY;
				}
			}
		}

		for (int slot = slotRange.first; slot <= lastSlot; slot++) {
			stack = inv.insertItem(slot, stack, false);

			if (stack.isEmpty()) {
				return ItemStack.EMPTY;
			}
		}

		return stack;
	}
}