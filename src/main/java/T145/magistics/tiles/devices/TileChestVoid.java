package T145.magistics.tiles.devices;

import T145.magistics.api.logic.IFacing;
import T145.magistics.tiles.MTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;

public class TileChestVoid extends MTile implements IFacing {

	public float lidAngle;
	public float prevLidAngle;
	public int numPlayersUsing;
	public int id = -1;

	private int ticksSinceSync;
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
	public void setFacing(IBlockState state, EnumFacing side) {
		facing = side;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setInteger("NumPlayersUsing", numPlayersUsing);
		compound.setInteger("ID", id);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		numPlayersUsing = compound.getInteger("NumPlayersUsing");
		id = compound.getInteger("ID");
	}

	@Override
	public void update() {
		if (++ticksSinceSync % 20 * 4 == 0) {
			world.addBlockEvent(pos, blockType, 1, numPlayersUsing);
		}

		prevLidAngle = lidAngle;
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		float f = 0.1F;

		if (numPlayersUsing > 0 && lidAngle == 0.0F) {
			double d0 = i + 0.5D;
			double d1 = k + 0.5D;
			world.playSound(null, d0, j + 0.5D, d1, SoundEvents.BLOCK_ENDERCHEST_OPEN, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (numPlayersUsing == 0 && lidAngle > 0.0F || numPlayersUsing > 0 && lidAngle < 1.0F) {
			float f2 = lidAngle;

			if (numPlayersUsing > 0) {
				lidAngle += 0.1F;
			} else {
				lidAngle -= 0.1F;
			}

			if (lidAngle > 1.0F) {
				lidAngle = 1.0F;
			}

			float f1 = 0.5F;

			if (lidAngle < 0.5F && f2 >= 0.5F) {
				double d3 = i + 0.5D;
				double d2 = k + 0.5D;
				world.playSound(null, d3, j + 0.5D, d2, SoundEvents.BLOCK_ENDERCHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (lidAngle < 0.0F) {
				lidAngle = 0.0F;
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		if (id == 1) {
			numPlayersUsing = type;
			return true;
		}
		return false;
	}

	@Override
	public void invalidate() {
		updateContainingBlockInfo();
		super.invalidate();
	}

	public boolean isOpen() {
		return numPlayersUsing > 0;
	}

	public void openChest() {
		if (numPlayersUsing == 0) {
			++numPlayersUsing;
			world.addBlockEvent(pos, blockType, 1, numPlayersUsing);
		}
	}

	public void closeChest() {
		if (numPlayersUsing == 1) {
			--numPlayersUsing;
			world.addBlockEvent(pos, blockType, 1, numPlayersUsing);
		}
	}
}