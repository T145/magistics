package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockInfuserItem extends ItemBlock {
	public BlockInfuserItem(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		String postfix = "";

		if (is.getItemDamage() == 1) {
			postfix = ".dark";
		}

		return super.getUnlocalizedName() + postfix;
	}
}