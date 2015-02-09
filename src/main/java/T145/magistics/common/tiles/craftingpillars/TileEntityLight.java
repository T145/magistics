package T145.magistics.common.tiles.craftingpillars;

import java.awt.Color;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import T145.magistics.common.Magistics;

public class TileEntityLight extends BaseTileEntity
{
	public int color;

	public static int[] colors = new int[]{
		Color.red.getRGB(),
		Color.green.getRGB(),
		Color.blue.getRGB(),
		Color.orange.getRGB(),
		Color.pink.getRGB(),
		Color.cyan.getRGB(),
		Color.magenta.getRGB(),
		Color.yellow.getRGB(),
	};

	public TileEntityLight()
	{
		this.color = new Random(System.currentTimeMillis()).nextInt(colors.length);
	}

	public void incrColorIndex(int i)
	{
		this.color += i;
		if(this.color < 0)
			this.color = colors.length-1;
		if(this.color >= colors.length)
			this.color = 0;
		if(!this.worldObj.isRemote)
			Magistics.proxy.sendToPlayers(this.getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 64);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.color = nbt.getInteger("color");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("color", this.color);
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return super.isUseableByPlayer(player);
	}
}
