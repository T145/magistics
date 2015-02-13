package T145.magistics.common.tiles.craftingpillars;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;

public class TileChristmasPresent extends TileBase {
	public int color;
	public static int[] colors = new int[] { 0x186a1b, 0xc04340, 0x105793, 0xdbdb24 };

	public TileChristmasPresent() {
		color = (new Random()).nextInt(colors.length / 2);
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
}