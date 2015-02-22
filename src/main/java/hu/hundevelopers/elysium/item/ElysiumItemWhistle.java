package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.Elysium;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenEnd;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ElysiumItemWhistle extends ElysiumItem
{
	private static Random rand = new Random();
	
	private long lastPlay = 0;

	public ElysiumItemWhistle()
	{
		super();
		this.maxStackSize = 1;
        this.setMaxDamage(1);
	}
	
	@Override
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entity)
    {
    	if(!world.isRemote)
    	{
    		if((System.currentTimeMillis() - lastPlay) / 1000 > this.getMaxItemUseDuration(itemStack)/20)
    		{
	    		lastPlay = System.currentTimeMillis();
    			
				world.playSoundAtEntity(entity, Elysium.ID + ":flute", 1F, 1F);
		    	if(world.getWorldChunkManager().getBiomeGenAt(0, 0) instanceof BiomeGenEnd )
		    	{
		    		if(isDragonAlive(world) >= 1/*Elysium.MaxDragon*/)
		    			entity.addChatMessage(new ChatComponentText(LanguageRegistry.instance().getStringLocalization("flute.dragonExist." + rand.nextInt(4))));
		    	}
		    	else
		    	{
				    entity.addChatMessage(new ChatComponentText(LanguageRegistry.instance().getStringLocalization("flute.tips." + rand.nextInt(3))));
		    	}
    		}
		}
    	entity.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }
    
    private int isDragonAlive(World world)
    {
    	List list = world.loadedEntityList;
    	int dragonNum = 0;
    	
    	for (int i = 0; i < list.size(); i++)
    	{
    		if(list.get(i) instanceof EntityDragon)
    			dragonNum++;
    	}
		
    	return dragonNum;
	}
    
    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer entity)
    {
	    if(!world.isRemote)
	    {
    		if(world.getWorldChunkManager().getBiomeGenAt(0, 0) instanceof BiomeGenEnd )
	    	{
    			if(isDragonAlive(world) < 1/*Elysium.MaxDragon*/)
    			{
					itemStack.damageItem(2, entity);
		    		
		    		EntityDragon entitydragon = new EntityDragon(world);
					entitydragon.setLocationAndAngles(0.0D, 128.0D, 0.0D, rand.nextFloat() * 360.0F, 0.0F);
					world.spawnEntityInWorld(entitydragon);
    			}
	    	}
	    }
		return itemStack;
    }
    
    @Override
    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }
    
    @Override
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack item)
    {
        return 10*20;
    }
    
    @Override
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }
}
