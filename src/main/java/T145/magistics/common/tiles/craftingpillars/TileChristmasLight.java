package T145.magistics.common.tiles.craftingpillars;

import java.awt.Color;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import T145.magistics.common.Magistics;

public class TileChristmasLight extends TileBase {
	public static int[] colors = new int[] { Color.red.getRGB(), Color.green.getRGB(), Color.blue.getRGB(), Color.orange.getRGB(), Color.pink.getRGB(), Color.cyan.getRGB(), Color.magenta.getRGB(), Color.yellow.getRGB(), };
	public int color = new Random(System.currentTimeMillis()).nextInt(colors.length);

	public void incrColorIndex(int i) {
		color += i;
		if (color < 0)
			color = colors.length - 1;
		if (color >= colors.length)
			color = 0;
		if (!worldObj.isRemote)
			Magistics.proxy.sendToPlayers(getDescriptionPacket(), worldObj, xCoord, yCoord, zCoord, 64);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		color = nbt.getInteger("color");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("color", color);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return super.isUseableByPlayer(player);
	}
}