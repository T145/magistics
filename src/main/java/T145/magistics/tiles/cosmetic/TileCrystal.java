package T145.magistics.tiles.cosmetic;

import T145.magistics.api.logic.IFacing;
import T145.magistics.tiles.MTileBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileCrystal extends MTileBase implements IFacing {

	public int crystalCount = 2;
	private EnumFacing facing = EnumFacing.NORTH;

	@Override
	public boolean isHorizontalFacing() {
		return false;
	}

	@Override
	public EnumFacing getFacing() {
		return facing;
	}

	@Override
	public void setFacing(EnumFacing side) {
		facing = side;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setInteger("Crystals", crystalCount);
		compound.setInteger("Facing", facing.getIndex());
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		crystalCount = compound.getInteger("Crystals");
		facing = EnumFacing.getFront(compound.getInteger("Facing"));
	}
}