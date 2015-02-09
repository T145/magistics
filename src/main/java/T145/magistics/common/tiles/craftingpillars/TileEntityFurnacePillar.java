package T145.magistics.common.tiles.craftingpillars;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.EnumSkyBlock;
import T145.magistics.common.Magistics;

public class TileEntityFurnacePillar extends BaseTileEntity implements IInventory, ISidedInventory
{
	private ItemStack[] inventory = new ItemStack[this.getSizeInventory()];
	public int burnTime, cookTime, xp;

	// @SideOnly(Side.CLIENT)
	public float rot = 0F;
	public boolean showNum = false;

	private int prevLightValue = 0;

	@Override
	public void updateEntity()
	{
		//		System.out.println((this.worldObj.isRemote ? "Client: " : "Server: ")+this.cookTime+" "+this.burnTime);

		if(this.worldObj.isRemote)
		{
			this.rot += 0.1F;
			if(this.rot >= 360F)
				this.rot -= 360F;

			int lightValue = this.getLightLevel();
			if (this.prevLightValue != lightValue) {
				this.prevLightValue = lightValue;
				this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if(this.burnTime > 0)
			this.burnTime--;
		else if(this.canBurn())
			this.burnItem();

		if(this.burnTime > 0 && this.canSmelt())
			if(this.cookTime > 0)
				this.cookTime--;
			else
				this.smeltItem();

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

		this.burnTime = nbt.getInteger("BurnTime");
		this.cookTime = nbt.getInteger("CookTime");
		this.showNum = nbt.getBoolean("showNum");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setInteger("BurnTime", this.burnTime);
		nbt.setInteger("CookTime", this.cookTime);

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

			//player.dropPlayerItem(this.decrStackSize(slot, amount));

			/*
			EntityItem droppedItem = new EntityItem(this.worldObj, this.xCoord + 0.5D, this.yCoord + 1.5D, this.zCoord + 0.5D);
			droppedItem.setEntityItemStack(this.decrStackSize(slot, amount));
			droppedItem.motionX = random.nextDouble() / 4 - 0.125D;
			droppedItem.motionZ = random.nextDouble() / 4 - 0.125D;
			droppedItem.motionY = random.nextDouble() / 4;
			this.worldObj.spawnEntityInWorld(droppedItem);
			 */

			this.onInventoryChanged();
		}
	}

	@Override
	public int getSizeInventory()
	{
		return 3;
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
		return "Furnace Pillar";
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
		return new int[]{0, 1, 2};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side)
	{
		if(slot == 0 && FurnaceRecipes.smelting().getSmeltingResult(itemstack) != null && (this.inventory[slot] == null || this.inventory[slot].isItemEqual(itemstack)))//Input
			return true;
		if(slot == 1 && TileEntityFurnace.isItemFuel(itemstack) && (this.inventory[slot] == null || this.inventory[slot].isItemEqual(itemstack)))//Fuel
			return true;
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side)
	{
		return slot == 2;
	}

	public boolean canSmelt()
	{
		if(this.inventory[0] == null)
			return false;
		ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
		if(result == null)
			return false;
		if(this.inventory[2] != null)
		{
			if(!this.inventory[2].isItemEqual(result))
				return false;
			if(this.inventory[2].stackSize + result.stackSize >= result.getMaxStackSize())
				return false;
			if(this.inventory[2].stackSize + result.stackSize >= this.getInventoryStackLimit())
				return false;
		}
		if(result.stackSize >= this.getInventoryStackLimit())
			return false;
		return true;
	}

	public void smeltItem()
	{
		this.cookTime = 150;
		if(this.worldObj.isRemote)
			return;

		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
		this.xp += FurnaceRecipes.smelting().func_151398_b(this.inventory[0]);

		if(this.inventory[2] == null)
			this.inventory[2] = itemstack.copy();
		else if(this.inventory[2].isItemEqual(itemstack))
			this.inventory[2].stackSize += itemstack.stackSize;

		this.inventory[0].stackSize--;

		if(this.inventory[0].stackSize <= 0)
			this.inventory[0] = null;

		this.onInventoryChanged();
	}

	public boolean canBurn()
	{
		if(this.inventory[1] == null)
			return false;
		if(TileEntityFurnace.getItemBurnTime(this.inventory[1]) <= 0)
			return false;
		return this.canSmelt();
	}

	public void burnItem()
	{
		if(this.worldObj.isRemote)
			return;

		if(this.cookTime == 0)
			this.cookTime = 150;
		this.burnTime = TileEntityFurnace.getItemBurnTime(this.inventory[1]);
		this.inventory[1].stackSize--;
		if(this.inventory[1].stackSize <= 0)
			this.inventory[1] = null;
		this.onInventoryChanged();
	}

	public int getLightLevel() {
		return this.burnTime > 0 ? 14 : 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return super.isUseableByPlayer(player);
	}
}
