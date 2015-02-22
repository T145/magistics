package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumRaspberryBush extends ElysiumBlockBush  implements IGrowable
{
	public ElysiumRaspberryBush()
	{
		super();
	}
	
	IIcon[] icons = new IIcon[2]; 

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
    	icons[0] = register.registerIcon(Elysium.ID + ":raspberrybushwithoutberries");
    	icons[1] = register.registerIcon(Elysium.ID + ":raspberrybushwithberries");
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        super.updateTick(world, x, y, z, random);
        
        if(world.getBlockMetadata(x, y, z) == 0 && random.nextInt(20) == 0)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 1, 3);
        }
    }
    
    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
    	if(!world.isRemote && world.getBlockMetadata(x, y, z) == 1 && player.getCurrentEquippedItem() == null)
    	{
        	world.setBlockMetadataWithNotify(x, y, z, 0, 3);
        	EntityItem entityitem = new EntityItem(world, x, y, z, new ItemStack(Elysium.itemRaspberry));
			world.spawnEntityInWorld(entityitem);
    	}

    	return false;
    }
    
    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
    {
    	if(!world.isRemote && meta == 1)
    	{
    		world.setBlockMetadataWithNotify(x, y, z, 0, 3);
        	EntityItem entityitem = new EntityItem(world, x, y, z, new ItemStack(Elysium.itemRaspberry));
			world.spawnEntityInWorld(entityitem);
    	}
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return icons[meta];
    }
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return AxisAlignedBB.getBoundingBox((double)p_149668_2_ + this.minX, (double)p_149668_3_ + this.minY, (double)p_149668_4_ + this.minZ, (double)p_149668_2_ + this.maxX, (double)p_149668_3_ + this.maxY, (double)p_149668_4_ + this.maxZ);
    }

    /**
     * is the block grass, dirt or farmland
     */
	@Override
    public boolean canPlaceBlockOn(Block block)
    {
        return block == Elysium.blockGrass || block == Elysium.blockDirt;
    }
    
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
    	return EnumPlantType.Plains;
    }
    
    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
    {
		return 0;
    }

    //isPossible
	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean isRemote)
	{
		return world.getBlockMetadata(x, y, z) != 1;
	}

	//chance
	@Override
	public boolean func_149852_a(World world, Random random, int x, int y, int z)
	{
		return random.nextInt(3) == 0;
	}

	//grow
	@Override
	public void func_149853_b(World world, Random random, int x, int y, int z)
	{
		world.setBlockMetadataWithNotify(x, y, z, 1, 3);
	}
}
