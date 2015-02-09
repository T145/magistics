package T145.magistics.common.tiles.craftingpillars;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import T145.magistics.api.items.baubles.Baubles;
import T145.magistics.common.Magistics;
import T145.magistics.common.items.craftingpillars.ItemRingBase;

public class TileEntityAnvilPillar extends BaseTileEntity implements IInventory, ISidedInventory
{
	private ItemStack[] inventory = new ItemStack[this.getSizeInventory()];

	public boolean showNum = false;

	@Override
	public void updateEntity()
	{

		//this.onInventoryChanged();
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
	}

	@Override
	public void onInventoryChanged()
	{
		super.onInventoryChanged();

		if(!this.worldObj.isRemote)
			Magistics.proxy.sendToPlayers(this.getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 64);
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
		return 5;
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
		return 1;
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
		return "Anvil Pillar";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if(i == 0 && itemstack.getItem() instanceof ItemRingBase)
			return true;
		else if(i > 0 && Baubles.getModifier(itemstack.getItem()) != null)
			return true;
		
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		if(side == 1 || side == 0) return new int[]{0};
		return new int[]{1, 2, 3, 4};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side)
	{
		return isItemValidForSlot(slot, itemstack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side)
	{
		System.out.println(side);
		return (slot == 0 && (side == 0 || side == 1)) || (slot > 0);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return super.isUseableByPlayer(player);
	}
}
