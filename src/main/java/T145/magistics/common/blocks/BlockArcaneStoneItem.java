package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockArcaneStoneItem extends ItemBlock {
	public BlockArcaneStoneItem(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return super.getUnlocalizedName() + "." + BlockArcaneStone.Types.values()[is.getItemDamage()];
	}
}