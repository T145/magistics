package T145.magistics.common.tiles.craftingpillars;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import T145.magistics.common.Magistics;

public class TileEntityDiskPlayerPillar extends BaseTileEntity
{
	/** ID of record which is in Jukebox */
	private ItemStack record;
	public boolean showNum = false;
	public boolean isEmpty = true;

	public float rot = 0F;
	@Override
	public void updateEntity()
	{
		if(this.worldObj.isRemote)
		{
			this.rot  += 4F;
			if(this.rot >= 360F)
				this.rot -= 360F;
		}
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList nbtlist = nbt.getTagList("Items", 10);

		for(int i = 0; i < nbtlist.tagCount(); i++)
		{
			NBTTagCompound nbtslot = (NBTTagCompound) nbtlist.getCompoundTagAt(i);
			int j = nbtslot.getByte("Slot") & 255;

			if((j >= 0))
			{
				this.record = ItemStack.loadItemStackFromNBT(nbtslot);
				if(this.record != null) this.isEmpty = false;
				else this.isEmpty = true;
			}
		}
		this.isEmpty = nbt.getBoolean("isEmpty");
		this.showNum = nbt.getBoolean("showNum");
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		NBTTagList nbtlist = new NBTTagList();

		if(this.getDisk() != null)
		{
			NBTTagCompound nbtslot = new NBTTagCompound();
			nbtslot.setByte("Slot", (byte) 0);
			this.getDisk().writeToNBT(nbtslot);
			nbtlist.appendTag(nbtslot);
		}

		nbt.setTag("Items", nbtlist);
		nbt.setBoolean("isEmpty", this.isEmpty);
		nbt.setBoolean("showNum", this.showNum);
	}

	@Override
	public void onInventoryChanged()
	{
		super.onInventoryChanged();
		if(this.worldObj != null && !this.worldObj.isRemote)
			Magistics.proxy.sendToPlayers(this.getDescriptionPacket(),
					this.worldObj, this.xCoord, this.yCoord, this.zCoord, 64);
	}

	public ItemStack getDisk()
	{
		return this.isEmpty ? null : this.record;
	}

	public void setDisk(ItemStack item)
	{
		if(item == null)
			this.isEmpty = true;
		else
			this.isEmpty = false;
		this.record = item;

		this.onInventoryChanged();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return super.isUseableByPlayer(player);
	}
}
