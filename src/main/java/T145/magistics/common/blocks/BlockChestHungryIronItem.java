package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import T145.magistics.common.blocks.BlockChestHungryIron.Types;

public class BlockChestHungryIronItem extends ItemBlock {
	public BlockChestHungryIronItem(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + Types.values()[is.getItemDamage()];
	}
}