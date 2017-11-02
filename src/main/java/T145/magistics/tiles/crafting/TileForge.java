package T145.magistics.tiles.crafting;

import T145.magistics.api.logic.IFacing;
import T145.magistics.tiles.base.TileInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileForge extends TileInventory implements ITickable, IFacing {

	public TileForge() {
		super(3);
	}

	private EnumFacing facing = EnumFacing.NORTH;

	@Override
	public boolean isHorizontalFacing() {
		return true;
	}

	@Override
	public EnumFacing getFacing() {
		return facing;
	}

	@Override
	public void setFacing(EnumFacing facing) {
		this.facing = facing;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
		super.writeCustomNBT(nbt);
		nbt.setInteger("Facing", facing.getIndex());
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		super.readCustomNBT(nbt);
		facing = EnumFacing.getFront(nbt.getInteger("Facing"));
	}

	@Override
	public void update() {}
}