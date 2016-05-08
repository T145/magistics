package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

public class BlockCrystalStorageStructureItem extends ItemBlock {
	public BlockCrystalStorageStructureItem(Block block) {
		super(block);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + ItemDye.field_150921_b[stack.getItemDamage()];
	}
}