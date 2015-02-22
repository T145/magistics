package hu.hundevelopers.elysium.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ElysiumPlanksItemBlock extends ItemBlock
{
	public ElysiumPlanksItemBlock(Block block)
	{
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String name = "";
		switch (itemstack.getItemDamage())
		{
			case 0:
				name = "fostimber";
			break;
			case 1:
				name = "silver";
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