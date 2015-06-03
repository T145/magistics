package T145.magistics.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;

public abstract class TileCrystalMachine extends TileThaumcraft {
	private int facing = 2;
	private ForgeDirection facingDir = null;

	public abstract boolean canRotate();

	public abstract void update();

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		facing = nbt.getByte("facing");
		facingDir = ForgeDirection.getOrientation(facing);
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setByte("facing", (byte) facing);
	}

	public int getFacing() {
		return facing;
	}

	public ForgeDirection getFacingDirection() {
		return facingDir;
	}

	public void setFacing(int dir) {
		facing = dir;
	}

	public void setFacingDirection(ForgeDirection dir) {
		facingDir = dir;
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		update();
	}
}