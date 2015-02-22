package hu.hundevelopers.elysium.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import T145.magistics.common.config.ConfigObjects;

public class ElysiumWallItemBlock extends ItemMultiTexture
{

	public ElysiumWallItemBlock(Block block)
	{
		super(block, ConfigObjects.blockQuartzWall, new String[]{"clean", "mossy"});
	}


}
