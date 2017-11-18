package T145.magistics.common.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class BlockItemBase extends ItemBlock {

	private final Class<? extends Enum<? extends IStringSerializable>> blockTypes;

	public BlockItemBase(Block block, Class<? extends Enum<? extends IStringSerializable>> blockTypes) {
		super(block);
		this.blockTypes = blockTypes;
		setHasSubtypes(blockTypes != null);
	}

	public BlockItemBase(Block block) {
		this(block, null);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		StringBuilder name = new StringBuilder(super.getUnlocalizedName());

		if (hasSubtypes) {
			name.append('.').append(blockTypes.getEnumConstants()[stack.getMetadata()].name().toLowerCase());
		}

		return name.toString();
	}
}