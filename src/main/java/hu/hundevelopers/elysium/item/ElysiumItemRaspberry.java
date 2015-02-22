package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.entity.EntityPinkUnicorn;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ElysiumItemRaspberry extends ElysiumEdibleItem
{
	public ElysiumItemRaspberry(int heal)
	{
		super(heal);
	}

	/**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase entity)
    {
        return entity instanceof EntityPinkUnicorn;
    }
}
