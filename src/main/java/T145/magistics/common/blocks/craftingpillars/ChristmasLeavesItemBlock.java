package T145.magistics.common.blocks.craftingpillars;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ChristmasLeavesItemBlock extends ItemBlock
{

	private final static String[] subNames = {
		"spruce", "fostimber"
	};

	public ChristmasLeavesItemBlock(Block id) {
		super(id);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata (int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}

}
