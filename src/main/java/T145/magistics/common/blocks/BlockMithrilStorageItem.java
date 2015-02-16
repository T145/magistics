package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockMithrilStorageItem extends ItemBlock {
	public BlockMithrilStorageItem(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		String name;
		switch (is.getItemDamage()) {
		case 0:
			name = "normal";
			break;
		case 1:
			name = "brick";
			break;
		default:
			name = "carved";
			break;
		}
		return getUnlocalizedName() + "." + name;
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}