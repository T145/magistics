package T145.magistics.common.tiles.craftingpillars;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.common.Magistics;

public abstract class BaseTileEntity extends TileEntity
{
	protected Random random;

	@Override
	public void setWorldObj(World world)
	{
		this.worldObj = world;
		this.random = new Random(this.worldObj.getSeed());
	}

	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) < 6;
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		NBTTagCompound nbt = pkt.func_148857_g();
		this.readFromNBT(nbt);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, nbttagcompound);
	}

	public void onInventoryChanged()
	{
		if(!this.worldObj.isRemote)
		{
			Magistics.proxy.sendToPlayers(this.getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 64);
		}
	}
}