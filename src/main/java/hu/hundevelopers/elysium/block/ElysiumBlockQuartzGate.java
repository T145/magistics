package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumBlockQuartzGate extends BlockFenceGate
{
	public ElysiumBlockQuartzGate()
	{
		super();
        this.setHardness(2);
        this.setResistance(3.0F);
        this.setStepSound(Block.soundTypePiston);
		this.setCreativeTab(Elysium.tabElysium);
	}
	
	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
	    return Blocks.quartz_block.getBlockTextureFromSide(side);
	}
}
