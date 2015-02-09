package T145.magistics.common.tiles.craftingpillars;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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

public class TileEntityFreezerPillar extends BaseTileEntity implements IInventory, ISidedInventory, IFluidHandler
{
	private ItemStack[] inventory = new ItemStack[this.getSizeInventory()];
	public final FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);

	public int freezingTime;
	public boolean showNum = false;
	public boolean isEmpty = true;

	public List<Blobs> blobs;

	public TileEntityFreezerPillar()
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
		//		else {
		if(this.canFreeze())
		{
			if(this.freezingTime > 0)
				this.freezingTime--;
			else
				this.freezeFluid();
		} else {
			this.freezingTime = 150;
		}
		//		}

		super.updateEntity();
	}

	public boolean canFreeze() {
		if(this.isEmpty)
			return false;
		if(this.tank.getFluid() == null) return false;
		if(this.tank.getFluidAmount() >= FluidContainerRegistry.BUCKET_VOLUME)
		{
			if(this.inventory[0] == null) return true;
			if(this.inventory[0].stackSize < this.getInventoryStackLimit())
			{
				ItemStack res = FreezerRecipes.getResultForFluid(this.tank.getFluid());
				if(res != null && this.inventory[0].isItemEqual(res))
				return true;
			}
		}
		return false;
	}


	private void freezeFluid() {
		this.freezingTime = 150;

		if(!this.worldObj.isRemote)
		{
			ItemStack itemstack = FreezerRecipes.getResultForFluid(this.tank.getFluid());
			if(this.inventory[0] == null)
				this.inventory[0] = itemstack.copy();
			else if(this.inventory[0].isItemEqual(itemstack))
				this.inventory[0].stackSize += itemstack.stackSize;

			this.drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.BUCKET_VOLUME, true);
			if(this.tank.getFluidAmount() <= 0)
				this.isEmpty = true;
			else
				this.isEmpty = false;
		}
		//		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.onInventoryChanged();
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		if(nbt.hasKey("tank"))
			this.tank.setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("tank")));
		this.inventory = new ItemStack[this.getSizeInventory()];
		NBTTagList nbtlist = nbt.getTagList("Items", 10);
		for(int i = 0; i < nbtlist.tagCount(); i++)
		{
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
			int j = nbtslot.getByte("Slot") & 255;

			if((j >= 0) && (j < this.getSizeInventory()))
				this.inventory[j] = ItemStack.loadItemStackFromNBT(nbtslot);
		}

		this.freezingTime = nbt.getInteger("freezingTime");
		this.showNum = nbt.getBoolean("showNum");
		this.isEmpty = nbt.getBoolean("isEmpty");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		if(this.tank.getFluid() != null)
			nbt.setTag("tank", this.tank.getFluid().writeToNBT(new NBTTagCompound()));
		NBTTagList nbtlist = new NBTTagList();
		for(int i = 0; i < this.getSizeInventory(); i++)
		{
			if(this.inventory[i] != null)
			{
				NBTTagCompound nbtslot = new NBTTagCompound();
				nbtslot.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(nbtslot);
				nbtlist.appendTag(nbtslot);
			}
		}
		nbt.setTag("Items", nbtlist);

		nbt.setInteger("freezingTime", this.freezingTime);
		nbt.setBoolean("showNum", this.showNum);
		nbt.setBoolean("isEmpty", this.isEmpty);
	}

	public void dropItemFromSlot(int slot, int amount, EntityPlayer player)
	{
		if(!this.worldObj.isRemote && this.getStackInSlot(slot) != null)
		{
			EntityItem itemEntity = new EntityItem(this.worldObj, player.posX, player.posY, player.posZ);
			itemEntity.setEntityItemStack(this.decrStackSize(slot, amount));
			this.worldObj.spawnEntityInWorld(itemEntity);

			this.onInventoryChanged();
		}
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		this.inventory[slot] = stack;

		if(stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.onInventoryChanged();
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		ItemStack stack = null;

		if(this.inventory[slot] != null)
		{
			if(this.inventory[slot].stackSize <= amount)
			{
				stack = this.inventory[slot];
				this.inventory[slot] = null;
				this.onInventoryChanged();
			}
			else
			{
				stack = this.inventory[slot].splitStack(amount);

				if(this.inventory[slot].stackSize == 0)
				{
					this.inventory[slot] = null;
				}

				this.onInventoryChanged();
			}
		}

		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = this.getStackInSlot(slot);
		if(stack != null)
		{
			this.setInventorySlotContents(slot, null);
		}

		return stack;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public String getInventoryName()
	{
		return "Freezer Pillar";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		return new int[]{0};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side)
	{
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side)
	{
		return true;
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
