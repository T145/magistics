package T145.magistics.api.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockMeta extends ItemBlock {
	public BlockMeta(Block block) {
		super(block);
		setHasSubtypes(true);
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