package T145.magistics.tiles;

import net.minecraft.item.ItemStack;

public class TileInfuserDark extends TileInfuser {

	public TileInfuserDark() {
		inventoryStacks = new ItemStack[6];
	}

	@Override
	public boolean isDark() {
		return true;
	}
}