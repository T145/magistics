package T145.magistics.common.tiles.base;

import javax.annotation.Nonnull;

import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileInventory extends TileBase {

	protected SimpleItemStackHandler inventory = createItemHandler();

	@Override
	public void readPacketNBT(NBTTagCompound nbt) {
		inventory = createItemHandler();
		inventory.deserializeNBT(nbt);
	}

	@Override
	public void writePacketNBT(NBTTagCompound nbt) {
		nbt.merge(inventory.serializeNBT());
	}

	public abstract int getInventorySize();

	protected SimpleItemStackHandler createItemHandler() {
		return new SimpleItemStackHandler(this);
	}

	public IItemHandlerModifiable getInventory() {
		return inventory;
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing side) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, side);
	}

	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing side) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
		}

		return super.getCapability(capability, side);
	}

	public void onInventoryLoad() {}

	public void onContentsChanged(int slot) {}

	public int calcRedstone() {
		if (getInventorySize() == 0) {
			return 0;
		}

		int stackCount = 0;
		float stackRatio = 0.0F;

		for (int slot = 0; slot < getInventorySize(); ++slot) {
			ItemStack stack = inventory.getStackInSlot(slot);

			if (!stack.isEmpty()) {
				stackRatio += stack.getCount() / Math.min(64, stack.getMaxStackSize());
				++stackCount;
			}
		}

		stackRatio = stackRatio / getInventorySize();
		return MathHelper.floor(stackRatio * 14.0F) + (stackCount > 0 ? 1 : 0);
	}

	public void dropInventory() {
		if (getInventorySize() == 0) {
			return;
		}

		for (int slot = 0; slot < getInventorySize(); ++slot) {
			ItemStack stack = inventory.getStackInSlot(slot);

			if (!stack.isEmpty()) {
				InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			}
		}

		world.updateComparatorOutputLevel(pos, world.getBlockState(pos).getBlock());
	}

	protected static class SimpleItemStackHandler extends ItemStackHandler {

		private final TileInventory tile;

		public SimpleItemStackHandler(TileInventory tile) {
			super(tile.getInventorySize());
			this.tile = tile;
		}

		@Nonnull
		@Override
		public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
			return super.insertItem(slot, stack, simulate);
		}

		@Nonnull
		@Override
		public ItemStack extractItem(int slot, int amount, boolean simulate) {
			return super.extractItem(slot, amount, simulate);
		}

		@Override
		public void onLoad() {
			tile.onInventoryLoad();
		}

		@Override
		public void onContentsChanged(int slot) {
			tile.onContentsChanged(slot);
			tile.markDirty();
		}
	}
}