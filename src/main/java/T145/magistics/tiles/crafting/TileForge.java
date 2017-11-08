package T145.magistics.tiles.crafting;

import T145.magistics.tiles.base.TileInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileForge extends TileInventory implements ITickable {

	private EnumFacing front;

	public TileForge() {
		super(3);
		setFront(EnumFacing.SOUTH);
	}

	public EnumFacing getFront() {
		return front;
	}

	public void setFront(EnumFacing front) {
		this.front = front;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
		super.writeCustomNBT(nbt);
		nbt.setInteger("Front", front.getIndex());
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		super.readCustomNBT(nbt);
		front = EnumFacing.getFront(nbt.getInteger("Front"));
	}

	@Override
	public void update() {}
}