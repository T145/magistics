package T145.magistics.common.items.craftingpillars;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WinterFood2013 extends BaseItemEatable
{
//	public static String[] itemNames = { "Christmas Candy", "Candy Cane", "Lollipop", "Gingerbread Man", "Star Biscuit", "Tree Biscuit", "Blue Szaloncukor", "Gold Szaloncukor", "Red Szaloncukor"};
	private static String[] iconNames = { "ChristmasCandy", "CandyCane", "Lollipop", "GingerbreadMan", "StarBiscuit", "TreeBiscuit", "BlueSzaloncukor", "GoldSzaloncukor", "RedSzaloncukor"};
	private static IIcon[] iconArray = new IIcon[iconNames.length];
	
	public WinterFood2013(int heal, float saturation)
	{
		super(heal, saturation);
		this.setHasSubtypes(true); // This allows the item to be marked as a metadata item.
		this.setMaxDamage(0); // This makes it so your item doesn't have the damage bar at the bottom of its icon, when "damaged" similar to the Tools.
	}

	public WinterFood2013(int heal)
	{
		this(heal, 0.6F);
	}

	@Override
	public IIcon getIconFromDamage(int i){
		return WinterFood2013.iconArray[i];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconReg)
	{
		for(int i = 0; i < iconArray.length; i++)
		{
			iconArray[i] = iconReg.registerIcon("craftingpillars:" + iconNames[i]);
		}
	}

	/**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < iconArray.length; i++)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}
    
    
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
		return super.getUnlocalizedName() + "." + i;
	}
}
