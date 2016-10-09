package T145.magistics.blocks;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockCrucibleItem extends ItemBlock {

	public BlockCrucibleItem(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	public String getNamePrefix(int meta) {
		switch (meta) {
		case 1:
			return "eyes";
		case 2:
			return "thaumium";
		case 3:
			return "souls";
		default:
			return StringUtils.EMPTY;
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + getNamePrefix(stack.getItemDamage());
	}
}