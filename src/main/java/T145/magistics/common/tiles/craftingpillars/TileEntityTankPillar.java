package T145.magistics.common.tiles.craftingpillars;

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
import T145.magistics.common.lib.Blobs;
import cpw.mods.fml.client.FMLClientHandler;

public class TileEntityTankPillar extends BaseTileEntity implements IFluidHandler
{
	public final FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);

	public boolean showNum = false;
	public boolean isEmpty = true;

	public List<Blobs> blobs;

	public TileEntityTankPillar()
	{
		this.blobs = new ArrayList<Blobs>();
	}

	public void addBlob()
	{
		this.blobs.add(new Blobs(this.random.nextInt(12)+2.5F, this.random.nextInt(9)+4.5F, this.random.nextInt(12)+2.5F, 4));
	}

	public void removeBlob()
	{
		this.blobs.remove(this.random.nextInt(this.blobs.size()));
	}
	
	private int prevLightValue = 0;

	@Override
	public void updateEntity()
	{
		if(this.worldObj.isRemote)
		{
			int lightValue = getFluidLightLevel();
			if (prevLightValue != lightValue) {
				prevLightValue = lightValue;
				worldObj.updateLightByType(EnumSkyBlock.Block, xCoord, yCoord, zCoord);
			}
			
			EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;
			if((player.posX-this.xCoord) * (player.posX-this.xCoord) + (player.posY-this.yCoord) * (player.posY-this.yCoord) + (player.posZ-this.zCoord) * (player.posZ-this.zCoord) < 128)
			{
				while(this.blobs.size() < this.tank.getFluidAmount()/FluidContainerRegistry.BUCKET_VOLUME)
					this.addBlob();
				while(this.blobs.size() > this.tank.getFluidAmount()/FluidContainerRegistry.BUCKET_VOLUME)
					this.removeBlob();

				for(int i = 0; i < this.blobs.size(); i++)
					this.blobs.get(i).update(0.1F);
			}
		}

		super.updateEntity();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		if(nbt.hasKey("tank"))
			this.tank.setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("tank")));

		this.showNum = nbt.getBoolean("showNum");
		this.isEmpty = nbt.getBoolean("isEmpty");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		if(this.tank.getFluid() != null)
			nbt.setTag("tank", this.tank.getFluid().writeToNBT(new NBTTagCompound()));
		
		nbt.setBoolean("showNum", this.showNum);
		nbt.setBoolean("isEmpty", this.isEmpty);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		if(!this.canFill(from, resource.getFluid())) return 0;
		if(doFill && resource.amount > 0)
			this.isEmpty = false;
		int res = this.tank.fill(resource, doFill);
		this.onInventoryChanged();
		return res;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return this.drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		FluidStack res = this.tank.drain(maxDrain, doDrain);
		if(this.tank.getFluidAmount() <= 0)
			this.isEmpty = true;
		this.onInventoryChanged();
		return res;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		if(this.tank.getFluid() == null) return FreezerRecipes.getResultForFluid(fluid) != null;
		return this.tank.getFluid().isFluidEqual(new FluidStack(fluid, 1));
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { this.tank.getInfo() };
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return super.isUseableByPlayer(player);
	}
	
	public int getFluidLightLevel()
	{
		if(this.isEmpty) return 0;
		FluidStack tankFluid = tank.getFluid();
		return tankFluid == null ? 0 : tankFluid.getFluid().getLuminosity(tankFluid);
	}
}
