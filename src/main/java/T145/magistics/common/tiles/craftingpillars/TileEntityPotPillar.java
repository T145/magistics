package T145.magistics.common.tiles.craftingpillars;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.IPlantable;
import T145.magistics.common.Magistics;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.world.gen.WorldGenChristmasTree;

public class TileEntityPotPillar extends BaseTileEntity implements IInventory, ISidedInventory
{
	private ItemStack[] inventory = new ItemStack[this.getSizeInventory()];

	public boolean showNum = false;

	public void onBlockUpdate(Random rand)
	{
		if(rand.nextInt(3) == 0&& this.getStackInSlot(0) != null && this.getStackInSlot(0).getItem() == Item.getItemFromBlock(ConfigObjects.blockChristmasTreeSapling))
		{
			System.out.println("block update");
			new WorldGenChristmasTree(false).generate(this.worldObj, rand, this.xCoord, this.yCoord, this.zCoord);
			this.decrStackSize(0, 1);
		}
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
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		super.onDataPacket(net, pkt);

		if(this.worldObj.isRemote)
		{
			Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
			//updateAllLightType...
			this.worldObj.func_147451_t(this.xCoord, this.yCoord, this.zCoord);
		}
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
		return "Pot Pillar";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if (itemstack.getItem() instanceof ItemBlock)
		{
			Block block = Block.getBlockFromItem(itemstack.getItem());

			return block instanceof IPlantable;
		}

		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		return new int[] {0};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side)
	{
		return (this.inventory[0] == null || this.inventory[0].stackSize == 0) && this.isItemValidForSlot(slot, itemstack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side)
	{
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return super.isUseableByPlayer(player);
	}
}
