package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import T145.magistics.common.config.MagisticsConfig;

public class BlockApparatusItem extends ItemBlock {
	public BlockApparatusItem(Block b) {
		super(b);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public EnumRarity getRarity(ItemStack is) {
		return MagisticsConfig.colored_names ? EnumRarity.uncommon : EnumRarity.common;
	}
}