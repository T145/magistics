package T145.magistics.lib.managers;

import T145.magistics.tiles.MTileInventory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.VanillaDoubleChestItemHandler;

public class InventoryManager {

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
}