package T145.magistics.common.tiles.craftingpillars;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import T145.magistics.common.Magistics;

public abstract class BaseTileEntityPillar extends BaseTileEntity implements IInventory, ISidedInventory
{
	protected ItemStack[] inventory = new ItemStack[this.getSizeInventory()];

	public abstract boolean isOnlyDisplaySlot(int i);

	@Override
	public abstract int getSizeInventory();

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
	public void onInventoryChanged()
	{
		super.onInventoryChanged();
		if(!this.worldObj.isRemote)
			Magistics.proxy.sendToPlayers(this.getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 64);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		this.inventory = new ItemStack[this.getSizeInventory()];
		NBTTagList nbtlist = nbt.getTagList("Items", 10);
		for(int i = 0; i < nbtlist.tagCount(); i++)
		{
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
			int j = nbtslot.getByte("Slot") & 255;

			if((j >= 0) && (j < this.getSizeInventory()))
				this.inventory[j] = ItemStack.loadItemStackFromNBT(nbtslot);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

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
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		return null;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return !this.isOnlyDisplaySlot(i) && (this.inventory[i] == null || this.inventory[i].isItemEqual(itemstack));
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int side)
	{
		return this.isItemValidForSlot(i, itemstack) && (this.inventory[i] == null ? itemstack.stackSize : itemstack.stackSize+this.inventory[i].stackSize) <= Math.min(this.getInventoryStackLimit(), itemstack.getMaxStackSize());
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int side)
	{
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return this.inventory[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack)
	{
		this.inventory[i] = stack;
		if(stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();
		this.onInventoryChanged();
	}

	public ItemStack incrStackSize(int i, int amount)
	{
		if(this.inventory[i] != null)
		{
			int j;
			if(this.inventory[i].stackSize+amount <= Math.min(this.getInventoryStackLimit(), this.inventory[i].getMaxStackSize()))
				j = amount;
			else
				j = Math.min(this.getInventoryStackLimit(), this.inventory[i].getMaxStackSize())-this.inventory[i].stackSize;
			this.inventory[i].stackSize += j;
			this.onInventoryChanged();
			return new ItemStack(this.inventory[i].getItem(), j);
		}
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int amount)
	{
		ItemStack stack = null;
		if(this.inventory[i] != null)
		{
			if(this.inventory[i].stackSize <= amount)
			{
				stack = this.inventory[i];
				this.inventory[i] = null;
			}
			else
			{
				stack = this.inventory[i].splitStack(amount);
				if(this.inventory[i].stackSize == 0)
					this.inventory[i] = null;
			}
			this.onInventoryChanged();
		}
		return stack;
	}

	public boolean insertStack(int i, ItemStack stack, int side)
	{
		if(this.canInsertItem(i, stack, side))
		{
			if(this.inventory[i] == null)
				this.setInventorySlotContents(i, stack);
			else
				this.incrStackSize(i, stack.stackSize);
			return true;
		}
		return false;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return this.getStackInSlot(i);
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
}
