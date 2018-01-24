package T145.magistics.common.lib;

import javax.annotation.Nullable;

import T145.magistics.common.tiles.base.TileInventory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class InventoryIO {

	private InventoryIO() {}

	public static int calcRedstone(@Nullable TileEntity tile) {
		return tile instanceof TileInventory ? calcRedstoneFromInventory((TileInventory) tile) : 0;
	}

	public static int calcRedstoneFromInventory(@Nullable TileInventory inv) {
		if (inv == null) {
			return 0;
		}

		int stackCount = 0;
		float stackRatio = 0.0F;

		for (int slot = 0; slot < inv.getInventorySize(); ++slot) {
			ItemStack stack = inv.getInventory().getStackInSlot(slot);

			if (!stack.isEmpty()) {
				stackRatio += stack.getCount() / Math.min(64, stack.getMaxStackSize());
				++stackCount;
			}
		}

		stackRatio = stackRatio / inv.getInventorySize();
		return MathHelper.floor(stackRatio * 14.0F) + (stackCount > 0 ? 1 : 0);
	}

	public static void dropInventory(TileInventory inv, IBlockState state) {
		if (inv != null) {
			World world = inv.getWorld();
			BlockPos pos = inv.getPos();

			for (int slot = 0; slot < inv.getInventorySize(); ++slot) {
				ItemStack stack = inv.getInventory().getStackInSlot(slot);

				if (!stack.isEmpty()) {
					InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
				}
			}

			world.updateComparatorOutputLevel(pos, state.getBlock());
		}
	}

	public static void spawnEntityItem(World world, ItemStack stack, BlockPos pos) {
		spawnEntityItem(world, stack, pos.getX(), pos.getY(), pos.getZ());
	}

	public static void spawnEntityItem(World world, ItemStack stack, double x, double y, double z) {
		float f = world.rand.nextFloat() * 0.8F + 0.1F;
		float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
		float f2 = world.rand.nextFloat() * 0.8F + 0.1F;
		EntityItem item = new EntityItem(world, x + f, y + f1, z + f2, stack.copy());
		item.motionX = world.rand.nextGaussian() * 0.05;
		item.motionY = world.rand.nextGaussian() * 0.05 + 0.2;
		item.motionZ = world.rand.nextGaussian() * 0.05;
		world.spawnEntity(item);
	}

	/*public static void dropItemsAtEntity(World world, BlockPos pos, Entity entity) {
		TileEntity tile = world.getTileEntity(pos);

		if (!(tile instanceof TileInventory) || world.isRemote) {
			return;
		}

		TileInventory inv = (TileInventory) tile;

		for (int slot = 0; slot < inv.getInventorySize(); ++slot) {
			ItemStack stack = inv.getInventory().getStackInSlot(slot);

			if (!stack.isEmpty()) {
				EntityItem entityItem = new EntityItem(world, entity.posX, entity.posY + entity.getEyeHeight() / 2F, entity.posZ, stack.copy());
				world.spawnEntity(entityItem);
				inv.getInventory().setStackInSlot(slot, ItemStack.EMPTY);
			}
		}
	}*/
}