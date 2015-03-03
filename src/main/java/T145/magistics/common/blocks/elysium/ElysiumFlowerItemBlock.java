package T145.magistics.common.blocks.elysium;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ElysiumFlowerItemBlock extends ItemBlockWithMetadata
{
	public ElysiumFlowerItemBlock(Block block)
	{
		super(block, block);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String name = "";
		switch (itemstack.getItemDamage())
		{
			case 0:
				name = "asphodel";
			break;
			case 1:
				name = "midas";
			break;
			default:
				name = "error";
		}
		return getUnlocalizedName() + "." + name;
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
}