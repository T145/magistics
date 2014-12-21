package T145.magistics.common.tiles;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import T145.magistics.common.lib.InventoryHelper;

public class TileChestHungry extends TileEntityChest implements ISidedInventory {
	public TileChestHungry() {
		func_145976_a("Hungry Chest");
	}

	public TileChestHungry(int type) {
		super(type);
	}

	@Override
	public boolean receiveClientEvent(int id, int data) {
		switch (id) {
		case 1:
			numPlayersUsing = data;
			return true;
		case 2:
			if (lidAngle < data / 10F)
				lidAngle = data / 10F;
			return true;
		default:
			return super.receiveClientEvent(id, data);
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return InventoryHelper.createSlotArray(0, 10);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return true;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return true;
	}
}