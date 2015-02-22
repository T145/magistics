package hu.hundevelopers.elysium.thaumcraft.wand;

import hu.hundevelopers.elysium.Elysium;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWandCaps extends Item
{
	public final String[] types = {"pure", "corrupted"};
	public IIcon[] icon;

	public ItemWandCaps()
	{
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir)
	{
		icon = new IIcon[types.length];
		for(int x = 0; x < types.length; x++)
		{
			this.icon[x] = ir.registerIcon(Elysium.ID + ":wand_cap_" + types[x]);
			System.out.println("Icon loading: " + Elysium.ID + ":wand_cap_" + types[x]);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return this.icon[meta];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs xCreativeTabs, List list)
	{
		for(int x = 0; x < types.length; x++){
			list.add(new ItemStack(this, 1, x));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + types[stack.getItemDamage()];
	}
}