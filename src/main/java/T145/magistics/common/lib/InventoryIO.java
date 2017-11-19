package T145.magistics.common.lib;

import javax.annotation.Nullable;

import T145.magistics.common.tiles.base.TileInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;

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
			ItemStack itemstack = inv.getHandle().getStackInSlot(slot);

			if (!itemstack.isEmpty()) {
				stackRatio += itemstack.getCount() / Math.min(64, itemstack.getMaxStackSize());
				++stackCount;
			}
		}

		stackRatio = stackRatio / inv.getInventorySize();
		return MathHelper.floor(stackRatio * 14.0F) + (stackCount > 0 ? 1 : 0);
	}
}