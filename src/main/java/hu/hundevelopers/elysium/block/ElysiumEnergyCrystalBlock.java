package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;

import java.util.List;
import java.util.Random;

import coloredlightscore.src.api.CLApi;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumEnergyCrystalBlock extends BlockStainedGlass
{
	public static final String[] names = new String[] {"pure", "corrupted"/*, "depleted"*/};
	private IIcon[] icons;


	public ElysiumEnergyCrystalBlock(Material mat) 
	{
		super(mat);
//		this.setLightOpacity(1);
		this.setCreativeTab(Elysium.tabElysium);
//		if(Elysium.modLights)
//		{
//			System.out.println("setting light");
//			CLApi.makeRGBLightValue(1F, 1F, 0F);
//		}
	}
	
	@Override
    public Block setBlockTextureName(String texture)
    {
        this.textureName = Elysium.ID + ":" + texture;
        return this;
    }


    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return 1;
    }
    
//	/**
//     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
//     * coordinates.  Args: blockAccess, x, y, z, side
//     */
//    @SideOnly(Side.CLIENT)
//    @Override
//    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
//    {
//        Block block = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_);
//
//        if (this == Elysium.blockEnergyCrystal)
//        {
//            if (p_149646_1_.getBlockMetadata(p_149646_2_, p_149646_3_, p_149646_4_) != p_149646_1_.getBlockMetadata(p_149646_2_ - Facing.offsetsXForSide[p_149646_5_], p_149646_3_ - Facing.offsetsYForSide[p_149646_5_], p_149646_4_ - Facing.offsetsZForSide[p_149646_5_]))
//            {
//                return true;
//            }
//
//            if (block == this)
//            {
//                return false;
//            }
//        }
//
//        return block == this ? false : super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
//    }
	  /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 0 || meta >= this.icons.length)
        {
            meta = 0;
        }

        return this.icons[meta];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
    
    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
    	if(FMLCommonHandler.instance().getEffectiveSide().isClient())
    		return Elysium.modLights ? getColorLightValue(world.getBlockMetadata(x, y, z)) : (world.getBlockMetadata(x, y, z) == 1 ? 15 : 15);
		else
    		return 1;	
    }

    public int getColorLightValue(int meta)
    {
        if (meta == 0) {
            return CLApi.makeRGBLightValue(15, 15, 0);
        } else {
            return CLApi.makeRGBLightValue(10, 4, 12);
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        for(int i = 0; i < names.length; i++)
        	list.add(new ItemStack(item, 1, i));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.icons = new IIcon[names.length];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = iconRegister.registerIcon(this.getTextureName() + "_" + names[i]);
        }
    }
}
