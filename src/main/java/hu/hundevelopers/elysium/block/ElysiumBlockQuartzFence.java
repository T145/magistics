package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ElysiumBlockQuartzFence extends BlockFence
{

	public ElysiumBlockQuartzFence(Block block)
	{
		super("", block.getMaterial());

        this.setHardness(2);
        this.setResistance(3.0F);
        this.setStepSound(block.stepSound);
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
    public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        return super.canConnectFenceTo(world, x, y, z) || block == Elysium.blockQuartzGate;
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        for(int i = 0; i < 2; i++)
        	list.add(new ItemStack(item, 1, i));
    }
}
