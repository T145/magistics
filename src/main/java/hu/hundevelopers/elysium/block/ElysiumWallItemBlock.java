package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class ElysiumWallItemBlock extends ItemMultiTexture
{

	public ElysiumWallItemBlock(Block block)
	{
		super(block, Elysium.blockQuartzWall, new String[]{"clean", "mossy"});
	}


}
