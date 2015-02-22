package hu.hundevelopers.elysium.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ElysiumQuartzItemBlock extends ItemBlock
{

	public ElysiumQuartzItemBlock(Block block)
	{
		super(block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String name = "";
		switch (itemstack.getItemDamage())
		{
			case 0:
				name = "default";
			break;
			case 1:
				name = "chiseled";
			break;
			default:
				name = "lines";
		}
		return getUnlocalizedName() + "." + name;
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
}
