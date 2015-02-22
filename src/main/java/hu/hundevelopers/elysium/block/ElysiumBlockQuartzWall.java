package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumBlockQuartzWall extends BlockWall
{

	public ElysiumBlockQuartzWall(Block block)
	{
		super(block);
		this.setCreativeTab(Elysium.tabElysium);
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return meta == 1 ? Elysium.blockQuartzBlock.getBlockTextureFromSide(side) : Blocks.quartz_block.getBlockTextureFromSide(side);
    }
    
    /**
     * Return whether an adjacent block can connect to a wall.
     */
    @Override
    public boolean canConnectWallTo(IBlockAccess world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        return super.canConnectWallTo(world, x, y, z) || block == Elysium.blockQuartzGate;
    }

}
