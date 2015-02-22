package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class ElysiumFenceItemBlock extends ItemMultiTexture
{

	public ElysiumFenceItemBlock(Block block)
	{
		super(block, Elysium.blockQuartzFence, new String[]{"clean", "mossy"});
	}


}
