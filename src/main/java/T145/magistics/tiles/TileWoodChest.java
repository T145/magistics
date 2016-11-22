package T145.magistics.tiles;

import T145.magistics.api.tiles.TileMagisticsLockableLoot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class TileWoodChest extends TileMagisticsLockableLoot {

	public TileWoodChest() {
		super(27);
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
		return null;
	}

	@Override
	public String getGuiID() {
		return "magistics:chest_wood";
	}

	@Override
	public String getName() {
		return null;
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
}