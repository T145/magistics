package T145.magistics.common.tiles.craftingpillars;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import T145.magistics.common.Magistics;

public class TileEntityTrashPillar extends BaseTileEntity implements IInventory, ISidedInventory
{
	private ItemStack[] inventory = new ItemStack[this.getSizeInventory()];

	public boolean showNum = false;
	public boolean isOpen = false;

	@Override
	public void updateEntity()
	{
		super.updateEntity();
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

		this.showNum = nbt.getBoolean("showNum");
		this.isOpen = nbt.getBoolean("isOpen");
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
		nbt.setBoolean("showNum", this.showNum);
		nbt.setBoolean("isOpen", this.isOpen);
	}

	@Override
	public void onInventoryChanged()
	{
		super.onInventoryChanged();

		if(!this.worldObj.isRemote)
		{
			Magistics.proxy.sendToPlayers(this.getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 64);
		}
	}

	public void dropItemFromSlot(int slot, int amount, EntityPlayer player)
	{
		if(this.worldObj.isRemote)
			return;

		if(this.getStackInSlot(slot) != null)
		{
			EntityItem itemEntity = new EntityItem(this.worldObj, player.posX, player.posY, player.posZ);
			itemEntity.setEntityItemStack(this.decrStackSize(slot, amount));
			this.worldObj.spawnEntityInWorld(itemEntity);

			//player.dropPlayerItem(this.decrStackSize(slot, amount));

			/*
			EntityItem droppedItem = new EntityItem(this.worldObj, this.xCoord + 0.5D, this.yCoord + 1.5D, this.zCoord + 0.5D);
			droppedItem.setEntityItemStack(this.decrStackSize(slot, amount));
			droppedItem.motionX = random.nextDouble() / 4 - 0.125D;
			droppedItem.motionZ = random.nextDouble() / 4 - 0.125D;
			droppedItem.motionY = random.nextDouble() / 4;
			this.worldObj.spawnEntityInWorld(droppedItem);*/

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
		return "Trash Pillar";
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
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return super.isUseableByPlayer(player);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		return new int[] {0};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side)
	{
		return true;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side)
	{
		return false;
	}

}
