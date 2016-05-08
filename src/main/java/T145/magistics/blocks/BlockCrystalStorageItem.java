package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockCrystalStorageItem extends ItemBlock {
	private static String visTypes[] = { "air", "fire", "water", "earth", "order", "entropy" };

	public BlockCrystalStorageItem(Block block) {
		super(block);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + visTypes[stack.getItemDamage()];
	}
}