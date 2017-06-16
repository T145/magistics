package T145.magistics.tiles;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public abstract class MTileInventory extends MTile {

	protected SimpleItemStackHandler itemHandler = createItemHandler();

	public boolean isUsableByPlayer(EntityPlayer player) {
		return world.getTileEntity(pos) != this ? false : player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64D;
	}

	@Override
	@OverridingMethodsMustInvokeSuper
	public void readPacketNBT(NBTTagCompound compound) {
		itemHandler = createItemHandler();
		itemHandler.deserializeNBT(compound);
	}

	@Override
	@OverridingMethodsMustInvokeSuper
	public void writePacketNBT(NBTTagCompound compound) {
		compound.merge(itemHandler.serializeNBT());
	}

	public abstract int getSizeInventory();

	public abstract boolean canInsertItem(int slot, @Nonnull ItemStack stack, boolean simulate);

	public abstract boolean canExtractItem(int slot, int amount, boolean simulate);

	protected SimpleItemStackHandler createItemHandler() {
		return new SimpleItemStackHandler(this);
	}

	public IItemHandlerModifiable getItemHandler() {
		return itemHandler;
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> cap, EnumFacing side) {
		return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, side);
	}

	@Override
	public <T> T getCapability(@Nonnull Capability<T> cap, EnumFacing side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemHandler);
		}

		return super.getCapability(cap, side);
	}

	protected static class SimpleItemStackHandler extends ItemStackHandler {

		private final MTileInventory inv;

		public SimpleItemStackHandler(MTileInventory inv) {
			super(inv.getSizeInventory());
			this.inv = inv;
		}

		public NonNullList<ItemStack> getStacks() {
			return stacks;
		}

		@Nonnull
		@Override
		public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
			if (inv.canInsertItem(slot, stack, simulate)) {
				return super.insertItem(slot, stack, simulate);
			} else {
				return stack;
			}
		}

		@Nonnull
		@Override
		public ItemStack extractItem(int slot, int amount, boolean simulate) {
			if (inv.canExtractItem(slot, amount, simulate)) {
				return super.extractItem(slot, amount, simulate);
			} else {
				return ItemStack.EMPTY;
			}
		}

		@Override
		public void onContentsChanged(int slot) {
			inv.markDirty();
		}
	}
}