package T145.magistics.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import thaumcraft.api.TileThaumcraft;

public class TileEverfullUrn extends TileThaumcraft implements IFluidHandler {
	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return doDrain ? new FluidStack(FluidRegistry.WATER, resource.amount) : null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return doDrain ? new FluidStack(FluidRegistry.WATER, maxDrain) : null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {
				new FluidTankInfo(new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME), FluidContainerRegistry.BUCKET_VOLUME)
		};
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote && worldObj.getTotalWorldTime() % 20 == 0) {
			update();
		}
	}

	public void update() {
		TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);

		if (tile instanceof IFluidHandler) {
			IFluidHandler fillable = (IFluidHandler) tile;
			fillable.fill(ForgeDirection.UP, new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME), true);
		}
	}
}