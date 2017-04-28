package T145.magistics.tiles.machines;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileInfuserDark extends TileInfuser {

	@Override
	public boolean isDark() {
		return true;
	}

	@Override
	public String getName() {
		return "tile.infuser.dark.name";
	}

	@Override
	public String getGuiID() {
		return "magistics:infuserDark";
	}

	@Override
	public int getSizeInventory() {
		return 6;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		super.writePacketNBT(compound);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		super.readPacketNBT(compound);
	}

	@SideOnly(Side.CLIENT)
	public int getDarkCookProgressScaled(int pixels) {
		return 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
}