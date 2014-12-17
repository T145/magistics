package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockAestheticItem extends ItemBlock {
	public BlockAestheticItem(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + is.getItemDamage();
	}
}