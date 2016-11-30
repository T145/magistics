package T145.magistics.tiles;

import T145.magistics.api.tiles.TileMagisticsLockableLoot;
import T145.magistics.blocks.BlockLogs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

public class TileWoodChest extends TileMagisticsLockableLoot implements ITickable {

	public float lidAngle;
	public float prevLidAngle;
	public int numPlayersUsing;
	private int ticksSinceSync;

	public TileWoodChest() {
		super(27);
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return new ContainerChest(playerInventory, this, player);
	}

	@Override
	public String getGuiID() {
		return "magistics:chest_wood";
	}

	@Override
	public String getName() {
		return "tile.chest_wood." + BlockLogs.BlockType.byMetadata(getBlockMetadata()).getName() + ".name";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void update() {
		if (++ticksSinceSync % 20 * 4 == 0) {
			worldObj.addBlockEvent(pos, getBlockType(), 1, numPlayersUsing);
		}

		prevLidAngle = lidAngle;
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		float f = 0.1F;

		if (numPlayersUsing > 0 && lidAngle == 0F) {
			double d0 = i + 0.5D;
			double d1 = k + 0.5D;
			worldObj.playSound(null, d0, j + 0.5D, d1, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (numPlayersUsing == 0 && lidAngle > 0F || numPlayersUsing > 0 && lidAngle < 1F) {
			float f2 = lidAngle;

			if (numPlayersUsing > 0) {
				lidAngle += 0.1F;
			} else {
				lidAngle -= 0.1F;
			}

			if (lidAngle > 1F) {
				lidAngle = 1F;
			}

			float f1 = 0.5F;

			if (lidAngle < 0.5F && f2 >= 0.5F) {
				double d3 = i + 0.5D;
				double d2 = k + 0.5D;
				worldObj.playSound(null, d3, j + 0.5D, d2, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (lidAngle < 0F) {
				lidAngle = 0F;
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		if (id == 1) {
			numPlayersUsing = type;
			return true;
		} else {
			return super.receiveClientEvent(id, type);
		}
	}

	@Override
	public void invalidate() {
		updateContainingBlockInfo();
		super.invalidate();
	}

	@Override
	public void openInventory(EntityPlayer player) {
		++numPlayersUsing;
		worldObj.addBlockEvent(pos, blockType, 1, numPlayersUsing);
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		--numPlayersUsing;
		worldObj.addBlockEvent(pos, blockType, 1, numPlayersUsing);
	}
}