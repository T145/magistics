package T145.magistics.api.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public abstract class TileMagisticsLockable extends TileMagisticsInventory implements IInteractionObject, ILockableContainer {

	public TileMagisticsLockable(int inventorySize) {
		super(inventorySize);
	}

	private LockCode code = LockCode.EMPTY_CODE;

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		code = LockCode.fromNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		if (code != null) {
			code.toNBT(tag);
		}

		return tag;
	}

	@Override
	public boolean isLocked() {
		return code != null && !code.isEmpty();
	}

	@Override
	public LockCode getLockCode() {
		return code;
	}

	@Override
	public void setLockCode(LockCode code) {
		this.code = code;
	}

	@Override
	public ITextComponent getDisplayName() {
		return hasCustomName() ? new TextComponentString(getName()) : new TextComponentTranslation(getName(), new Object[0]);
	}

	private IItemHandler itemHandler;

	protected IItemHandler createUnSidedHandler() {
		return new net.minecraftforge.items.wrapper.InvWrapper(this);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return (T) (itemHandler == null ? (itemHandler = createUnSidedHandler()) : itemHandler);
		}

		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
}