package hu.hundevelopers.elysium.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import T145.magistics.common.config.ConfigObjects;

public class ElysiumFenceItemBlock extends ItemMultiTexture
{

	public ElysiumFenceItemBlock(Block block)
	{
		super(block, ConfigObjects.blockQuartzFence, new String[]{"clean", "mossy"});
	}


}
