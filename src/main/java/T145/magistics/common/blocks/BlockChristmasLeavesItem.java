package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockChristmasLeavesItem extends ItemBlock {
	private final static String[] subNames = { "spruce", "fostimber" };

	public BlockChristmasLeavesItem(Block id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + subNames[is.getItemDamage()];
	}
}