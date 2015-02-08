package T145.magistics.common.tiles.aesthetic;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class TileSpring extends TileEntity {
	@Override
	public void updateEntity() {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
			if (tile != null && tile instanceof IFluidHandler){
				IFluidHandler fillable = (IFluidHandler) tile;
				fillable.fill(dir, new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME / 2), true);
			}
		}
	}
}