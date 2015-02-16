package T145.magistics.common.tiles.pillars;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import T145.magistics.api.FreezerRecipes;
import T145.magistics.client.lib.pillars.Blobs;
import cpw.mods.fml.client.FMLClientHandler;

public class TilePillarTank extends TileBase implements IFluidHandler {
	public final FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);

	public boolean showNum = false, empty = true;
	private int prevLightValue = 0;

	public List<Blobs> blobs;

	public TilePillarTank() {
		blobs = new ArrayList<Blobs>();
	}

	public void addBlob() {
		blobs.add(new Blobs(rand.nextInt(12) + 2.5F, rand.nextInt(9) + 4.5F, rand.nextInt(12) + 2.5F, 4));
	}

	public void removeBlob() {
		blobs.remove(rand.nextInt(blobs.size()));
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			int lightValue = getFluidLightLevel();
			if (prevLightValue != lightValue) {
				prevLightValue = lightValue;
				worldObj.updateLightByType(EnumSkyBlock.Block, xCoord, yCoord, zCoord);
			}

			EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;
			if ((player.posX - xCoord) * (player.posX - xCoord) + (player.posY - yCoord) * (player.posY - yCoord) + (player.posZ - zCoord) * (player.posZ - zCoord) < 128) {
				while (blobs.size() < tank.getFluidAmount() / FluidContainerRegistry.BUCKET_VOLUME)
					addBlob();
				while (blobs.size() > tank.getFluidAmount() / FluidContainerRegistry.BUCKET_VOLUME)
					removeBlob();

				for (int i = 0; i < blobs.size(); i++)
					blobs.get(i).update(0.1F);
			}
		}

		super.updateEntity();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey("tank"))
			tank.setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("tank")));

		showNum = nbt.getBoolean("showNum");
		empty = nbt.getBoolean("isEmpty");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (tank.getFluid() != null)
			nbt.setTag("tank", tank.getFluid().writeToNBT(new NBTTagCompound()));

		nbt.setBoolean("showNum", showNum);
		nbt.setBoolean("isEmpty", empty);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (!canFill(from, resource.getFluid()))
			return 0;
		if (doFill && resource.amount > 0)
			empty = false;
		int res = tank.fill(resource, doFill);
		onInventoryChanged();
		return res;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		FluidStack res = tank.drain(maxDrain, doDrain);
		if (tank.getFluidAmount() <= 0)
			empty = true;
		onInventoryChanged();
		return res;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if (tank.getFluid() == null)
			return FreezerRecipes.getResultForFluid(fluid) != null;
		return tank.getFluid().isFluidEqual(new FluidStack(fluid, 1));
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { tank.getInfo() };
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return super.isUseableByPlayer(player);
	}

	public int getFluidLightLevel() {
		if (empty)
			return 0;
		FluidStack tankFluid = tank.getFluid();
		return tankFluid == null ? 0 : tankFluid.getFluid().getLuminosity(tankFluid);
	}
}