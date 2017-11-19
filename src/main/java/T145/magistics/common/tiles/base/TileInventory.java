package T145.magistics.common.tiles.base;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;


public class TileInventory extends TileBase {

	private int inventorySize;
	private ItemHandlerTile handle;
	private List<EnumFacing> applicableSides;

	public TileInventory(int inventorySize, EnumFacing... applicableSides) {
		this.inventorySize = inventorySize;
		this.handle = createItemHandler();
		this.applicableSides = Arrays.asList(applicableSides);
	}

	public TileInventory(int inventorySize) {
		this(inventorySize, EnumFacing.VALUES);
	}

	public boolean canInteractWith(EntityPlayer player) {
		return world.getTileEntity(pos) != this ? false : player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64D;
	}

	protected ItemHandlerTile createItemHandler() {
		return new ItemHandlerTile(this);
	}

	private boolean hasHandlerForSide(EnumFacing facing) {
		return facing == null || applicableSides.contains(facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return hasHandlerForSide(facing) ? capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY : super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (hasHandlerForSide(facing)) {
			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(handle);
			}
		}
		return super.getCapability(capability, facing);
	}

	public ItemStack copyStackWithSize(@Nonnull ItemStack stack, int amount) {
		if (stack.isEmpty() || amount <= 0) {
			return ItemStack.EMPTY;
		}

		ItemStack s = stack.copy();
		s.setCount(amount);
		return s;
	}

	@Override
	@OverridingMethodsMustInvokeSuper
	public void readCustomNBT(NBTTagCompound tag) {
		super.readCustomNBT(tag);

		handle = createItemHandler();
		handle.deserializeNBT(tag.getCompoundTag("inventory"));

		if (handle.getSlots() != inventorySize) {
			ItemHandlerTile newInv = createItemHandler();

			for (int i = 0; i < Math.min(handle.getSlots(), inventorySize); i++) {
				ItemStack old = handle.getStackInSlot(i);
				old = copyStackWithSize(old, old.getCount());
				newInv.setStackInSlot(i, old);
			}

			handle = newInv;
		}
	}

	@Override
	@OverridingMethodsMustInvokeSuper
	public void writeCustomNBT(NBTTagCompound tag) {
		super.writeCustomNBT(tag);
		tag.setTag("inventory", handle.serializeNBT());
	}

	public int getInventorySize() {
		return inventorySize;
	}

	public void onInventoryChanged(int slot) {}

	public ItemHandlerTile getHandle() {
		return handle;
	}

	public boolean canInsertItem(int slot, ItemStack stack, @Nonnull ItemStack existing) {
		return true;
	}

	public static class ItemHandlerTile extends ItemStackHandler {

		protected final TileInventory tile;

		public ItemHandlerTile(TileInventory inv) {
			super(inv.inventorySize);
			tile = inv;
		}

		@Override
		public void onContentsChanged(int slot) {
			tile.onInventoryChanged(slot);
			tile.markDirty();
		}

		@Override
		public int getStackLimit(int slot, ItemStack stack) {
			return super.getStackLimit(slot, stack);
		}

		public void clear() {
			for (int slot = 0; slot < getSlots(); ++slot) {
				setStackInSlot(slot, ItemStack.EMPTY);
				onContentsChanged(slot);
			}
		}

		public NonNullList<ItemStack> getStacks() {
			return stacks;
		}

		@Nonnull
		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			if (!tile.canInsertItem(slot, stack, getStackInSlot(slot))) {
				return stack;
			}
			return super.insertItem(slot, stack, simulate);
		}
	}
}