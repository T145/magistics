package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;

import java.util.List;

import net.minecraft.block.BlockQuartz;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumBlockQuartz extends BlockQuartz
{
    public static final String[] field_150191_a = new String[] {"default", "chiseled", "lines"};
    @SideOnly(Side.CLIENT)
    private IIcon iconChiseled;
    @SideOnly(Side.CLIENT)
    private IIcon iconChiseledTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconLinesTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconDefault;
    @SideOnly(Side.CLIENT)
    private IIcon iconLinesSide;

    public ElysiumBlockQuartz()
    {
        super();
        this.setCreativeTab(Elysium.tabElysium);
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 0)
        {
            return iconDefault;
        }
        else if(meta == 1)
        { 
        	if(side == 0 || side == 1)
        		return iconChiseledTop;
        	return iconChiseled;
        } else if(meta == 2 && (side == 1 || side == 0))
        {
    		return iconLinesTop;
        } else if(meta == 3 && (side == 5 || side == 4))
		{
    		return iconLinesTop;
    	} else if(meta == 4 && (side == 2 || side == 3))
		{
    		return iconLinesTop;
    	}
        else
        {
        	return iconLinesSide;
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int meta)
    {
        if (meta == 2)
        {
            switch (p_149660_5_)
            {
                case 0:
                case 1:
                    meta = 2;
                    break;
                case 2:
                case 3:
                    meta = 4;
                    break;
                case 4:
                case 5:
                    meta = 3;
            }
        }

        return meta;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int meta)
    {
        return meta != 3 && meta != 4 ? meta : 2;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int meta)
    {
        return meta != 3 && meta != 4 ? super.createStackedBlock(meta) : new ItemStack(Item.getItemFromBlock(this), 1, 2);
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 39;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 2));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.iconDefault = iconRegister.registerIcon(this.getTextureName() + "_top_mossy2");
        this.iconChiseled = iconRegister.registerIcon(this.getTextureName() + "_chiseled_mossy2");
        this.iconChiseledTop = iconRegister.registerIcon(this.getTextureName() + "_chiseled_top_mossy2");
        this.iconLinesTop = iconRegister.registerIcon(this.getTextureName() + "_lines_top_mossy2");
        this.iconLinesSide = iconRegister.registerIcon(this.getTextureName() + "_lines_mossy2");
    }

    public MapColor getMapColor(int p_149728_1_)
    {
        return MapColor.quartzColor;
    }
}