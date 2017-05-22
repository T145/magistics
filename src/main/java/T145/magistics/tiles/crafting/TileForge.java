package T145.magistics.tiles.crafting;

import T145.magistics.api.logic.IFacing;
import T145.magistics.tiles.MTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileForge extends MTile implements IFacing {

	private EnumFacing facing = EnumFacing.NORTH;

	@Override
	public EnumFacing getFacing() {
		return facing;
	}

	@Override
	public void setFacing(EnumFacing facing) {
		this.facing = facing;
		markDirty();
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setInteger("Facing", facing.getIndex());
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		facing = EnumFacing.getFront(compound.getInteger("Facing"));
	}

	@Override
	public void update() {}
}