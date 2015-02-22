package hu.hundevelopers.elysium.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ElysiumItemOverkill extends ElysiumItem
{
	@Override
	/**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase attacker)
    {
    	if(target instanceof EntityPlayer)
    	{
    		if(!(((EntityPlayer)target).getCommandSenderName().equals("dawars") || ((EntityPlayer)target).getCommandSenderName().equals("FBalazs379") ||
    				((EntityPlayer)target).getCommandSenderName().equals("Notch")))
    		{
    			target.setHealth(0);
    		}
    		else
    		{
//    	TODO		target.setEntityHealth(((EntityPlayer) target).maxHealth*10);
    		}
    	}
    	else
    	{
    		target.setHealth(0);
    	}
    	
        return true;
    }
}
