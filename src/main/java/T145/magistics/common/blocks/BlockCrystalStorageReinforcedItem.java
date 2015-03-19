package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class BlockCrystalStorageReinforcedItem extends BlockCrystalStorageItem {
	public BlockCrystalStorageReinforcedItem(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getName("plate", "shield", is.getItemDamage());
	}
}