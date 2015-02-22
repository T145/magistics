package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.api.Staff;
import hu.hundevelopers.elysium.entity.projectile.EntityBlockProjectile;
import hu.hundevelopers.elysium.entity.projectile.EntityEnderRandomProjectile;
import hu.hundevelopers.elysium.entity.projectile.EntityFireballProjectile;
import hu.hundevelopers.elysium.entity.projectile.EntityIceProjectile;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumStaffItem extends ElysiumItem
{
	public ElysiumStaffItem()
	{
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
    public static final String[] names = new String[] { "earth", "ice", "ender", "fire" };
    
    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
    {
        itemStack.stackTagCompound = new NBTTagCompound();
    }
    
    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + names[i];
    }

    /**
     * This is called when the item is used, before the block is activated.
     * @param stack The Item Stack
     * @param player The Player that used the item
     * @param world The Current World
     * @param x Target X Position
     * @param y Target Y Position
     * @param z Target Z Position
     * @param side The side of the target hit
     * @return Return true to prevent any further processing.
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
    	if(stack.getItemDamage() == 0)
    	{
	    	if(Staff.getDamageForBlock(world.getBlock(x, y, z)) != 0 && Staff.getBlockHolding(stack) == null)
	    	{
	    		Block block = world.getBlock(x, y, z);
	    		Staff.setBlockHolding(stack, block);
	    		if(world.isRemote)
	    			FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(x, y, z, block, world.getBlockMetadata(x, y, z));
	    		world.setBlock(x, y, z, Block.getBlockById(0));
	    		return true;
	    	}
	    	return false;
    	} else if(stack.getItemDamage() == 3)
    	{
    		if (side == 0)
            {
                --y;
            }

            if (side == 1)
            {
                ++y;
            }

            if (side == 2)
            {
                --z;
            }

            if (side == 3)
            {
                ++z;
            }

            if (side == 4)
            {
                --x;
            }

            if (side == 5)
            {
                ++x;
            }

            if (!player.canPlayerEdit(x, y, z, side, stack))
            {
                return false;
            }
            else
            {
                if (world.isAirBlock(x, y, z))
                {
                    world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                    world.setBlock(x, y, z, Blocks.fire);
                }

                return false;
            }
    	} else 
    		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);

    }
    
    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase)
    {
        return false;
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	int meta = stack.getItemDamage();
    	if(meta == 0)
    	{
	    	Block block = Staff.getBlockHolding(stack);
	
	    	if(block != null)
			{
				EntityBlockProjectile entityprojectile = new EntityBlockProjectile(world, player, block);
	            world.spawnEntityInWorld(entityprojectile);
				
				Staff.setBlockHolding(stack, null);
			}
    	} else if(meta == 1)
    	{
				EntityIceProjectile entityprojectile = new EntityIceProjectile(world, player);
			    world.spawnEntityInWorld(entityprojectile);
    	} else if(meta == 2)
    	{
//    		if(!player.isSneaking())
//			{
    			EntityEnderRandomProjectile entityprojectile = new EntityEnderRandomProjectile(world, player);
    			world.spawnEntityInWorld(entityprojectile);
//			} else {
//				
//			}
    	} else
    	{
//    		if(!world.isRemote)
//    		{
    			EntityFireballProjectile entityFireball = new EntityFireballProjectile(world, player);
	    		world.spawnEntityInWorld(entityFireball);
//    		}
    	}
        return stack;
    }
    
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int x = 0; x < names.length; x++)
        {
            list.add(new ItemStack(this, 1, x));
        }
    }
}
