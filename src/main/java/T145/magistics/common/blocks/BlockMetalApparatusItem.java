package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import T145.magistics.common.blocks.BlockMetalApparatus.Types;

public class BlockMetalApparatusItem extends ItemBlock {
	public BlockMetalApparatusItem(Block b) {
		super(b);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + Types.values()[is.getItemDamage()];
	}
}