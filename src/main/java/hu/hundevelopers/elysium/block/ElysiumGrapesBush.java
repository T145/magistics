package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumGrapesBush extends ElysiumBlockBush implements IGrowable
{
	public ElysiumGrapesBush()
	{
		super();
		float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1F, 0.5F + f);
        
	}
	
	public static final String[] names = new String[] {"bottom", "empty", "blue", "white"};
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		this.icons = new IIcon[names.length];
		
		for (int i = 0; i < this.names.length; ++i)
		{
            this.icons[i] = register.registerIcon(Elysium.ID + ":grapes_" + names[i]);
        }
	}
	
	 /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
    	if(!world.isRemote)
		{   
    		int meta = world.getBlockMetadata(x, y, z);

    		if(meta == 0)
    		{
    			Block top = world.getBlock(x, y+1, z);
    			if(top != null && top == this)
    			{
    				top.onBlockActivated(world, x, y+1, z, player, side, hitX, hitY, hitZ);
    			}
    		}
    		if(meta > 1 && player.getCurrentEquippedItem() == null)
    		{
	        	world.setBlockMetadataWithNotify(x, y, z, 1, 3);
	        	EntityItem entityitem = new EntityItem(world, x, y, z, new ItemStack(Elysium.itemGrapes, 1, meta == 2 ? 0 : 1));
				world.spawnEntityInWorld(entityitem);
    		}
    	}

    	return false;
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
    {
    	if(!world.isRemote && meta > 1)
    	{
    		world.setBlockMetadataWithNotify(x, y, z, 0, 3);
        	EntityItem entityitem = new EntityItem(world, x, y, z, new ItemStack(Elysium.itemGrapes, 1, meta == 2 ? 0 : 1));
			world.spawnEntityInWorld(entityitem);
    	}
    }
    
	/**
     * is the block grass, dirt or farmland
     */
	@Override
    public boolean canPlaceBlockOn(Block block)
    {
        return block == Elysium.blockGrass || block == Elysium.blockDirt;
    }
	
	/**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
	@Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
		int meta = world.getBlockMetadata(x, y, z);
		
        if(meta == 0)
        {
        	return canPlaceBlockOn(world.getBlock(x, y-1, z)) && world.getBlock(x, y+1, z) == this;
        } else
        {
        	return world.getBlock(x, y-1, z) == this && world.getBlockMetadata(x, y-1, z) == 0;
        }
    }
    

    /**
     * checks if the block can stay, if not drop as item
     */
    @Override
    protected void checkAndDropBlock(World world, int x, int y, int z)
    {
        if (!this.canBlockStay(world, x, y, z))
        {
        	int meta = world.getBlockMetadata(x, y, z);
        	if(meta == 0 && world.getBlock(x, y+1, z)==this)
                world.setBlock(x, y+1, z, getBlockById(0), 0, 2);
            this.dropBlockAsItem(world, x, y, z, meta, 0);
            world.setBlock(x, y, z, getBlockById(0), 0, 2);
            
        }
    }
	/**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
	@Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return world.getBlock(x, y, z).isReplaceable(world, x, y, z) && this.canPlaceBlockOn(world.getBlock(x, y-1, z));
    }
	
	/**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return icons[Math.min(meta, 3)];
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
    	list.add(new ItemStack(item, 1, 0));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	super.updateTick(world, x, y, z, rand);
    	
    	if(world.getBlockMetadata(x, y, z) == 1 && rand.nextInt(40) == 0)
    	{
			world.setBlockMetadataWithNotify(x, y, z, 1 + rand.nextInt(), 2);
    	}
    }

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
    @Override
	public int damageDropped(int meta)
	{
		return 0;
    }
    
    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return meta == 0 ? Item.getItemFromBlock(this) : Item.getItemById(0);
    }
    
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
	@Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta)
	{
		world.setBlock(x, y+1, z, this, 1, 2);
		world.setBlockMetadataWithNotify(x, y+1, z, 1, 2);
	}

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean isRemote)
    {
    	return world.getBlockMetadata(x, y, z) == 1;
    }

    @Override
    public boolean func_149852_a(World world, Random rand, int x, int y, int z)
    {
    	return rand.nextInt(3) == 0;
    }

    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z)
    {
		world.setBlockMetadataWithNotify(x, y, z, 1 + rand.nextInt(), 2);
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
}
