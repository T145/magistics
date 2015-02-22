package hu.hundevelopers.elysium.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ElysiumLogItemBlock extends ItemBlock
{
	public ElysiumLogItemBlock(Block block)
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
			case 2:
				name = "corrupt";
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