package T145.magistics.tiles.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;

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
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return null;
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
}