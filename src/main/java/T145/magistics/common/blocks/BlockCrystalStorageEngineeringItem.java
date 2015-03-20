package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class BlockCrystalStorageEngineeringItem extends BlockCrystalStorageItem {
	public BlockCrystalStorageEngineeringItem(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getName("light", "dark", is.getItemDamage());
	}
}