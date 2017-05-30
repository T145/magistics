package T145.magistics.tiles.crafting;

import T145.magistics.api.logic.IFacing;
import T145.magistics.tiles.MTileInventory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileForge extends MTileInventory implements IFacing {

	private EnumFacing facing = EnumFacing.NORTH;

	@Override
	public boolean isHorizontalFacing() {
		return true;
	}

	@Override
	public EnumFacing getFacing(IBlockState state) {
		return facing;
	}

	@Override
	public void setFacing(IBlockState state, EnumFacing facing) {
		this.facing = facing;
		markDirty();
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, boolean simulate) {
		return true;
	}

	@Override
	public boolean canExtractItem(int slot, int amount, boolean simulate) {
		return true;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		super.writePacketNBT(compound);
		compound.setInteger("Facing", facing.getIndex());
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		super.readPacketNBT(compound);
		facing = EnumFacing.getFront(compound.getInteger("Facing"));
	}

	@Override
	public void update() {}
}