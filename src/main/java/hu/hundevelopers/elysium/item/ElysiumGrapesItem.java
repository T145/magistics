package hu.hundevelopers.elysium.item;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.entity.EntityPinkUnicorn;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumGrapesItem extends ElysiumEdibleItem
{
	public ElysiumGrapesItem()
	{
		super(3);
		this.setMaxStackSize(16);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
 
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
        icons = new IIcon[2];
 
        for (int i = 0; i < icons.length; i++)
        {
            icons[i] = par1IconRegister.registerIcon(this.iconString + "_" + names[i]);
        }
    }
    
    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase entity)
    {
        return entity instanceof EntityPinkUnicorn;
    }
    
    public static final String[] names = new String[] { "blue", "white"};
    
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
 
    @Override
    public IIcon getIconFromDamage(int par1)
    {
        return icons[par1];
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
