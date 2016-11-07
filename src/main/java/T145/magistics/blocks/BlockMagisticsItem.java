package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockMagisticsItem extends ItemBlock {

	private final Class<? extends Enum> blockTypes;

	public BlockMagisticsItem(Block block, Class<? extends Enum> blockTypes) {
		super(block);
		setHasSubtypes(true);
		this.blockTypes = blockTypes;
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + blockTypes.getEnumConstants()[stack.getMetadata()].name().toLowerCase();
	}
}