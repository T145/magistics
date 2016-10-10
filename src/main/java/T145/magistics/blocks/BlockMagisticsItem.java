package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockMagisticsItem extends ItemBlock {

	public BlockMagisticsItem(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		BlockMagistics block = (BlockMagistics) getBlock();

		if (block.hasTypes()) {
			return super.getUnlocalizedName() + "." + block.getTypeName(block.getStateFromMeta(stack.getMetadata()));
		}

		return super.getUnlocalizedName();
	}
}
