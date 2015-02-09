package T145.magistics.common.tiles.craftingpillars;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityChristmasPresent extends BaseTileEntity
{
	public TileEntityChristmasPresent()
	{
		this.color = (new Random()).nextInt(colors.length/2);
	}
	
	public int color;
	//public boolean model;

	public static int[] colors = new int[]{
		0x186a1b,
		0xc04340,
		0x105793,
		0xdbdb24
	};

	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.color = nbt.getInteger("color");
		//this.model = nbt.getBoolean("model");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("color", this.color);
		//nbt.setBoolean("model", this.model);
	}
}
