package hu.hundevelopers.elysium.thaumcraft.wand;

import hu.hundevelopers.elysium.Elysium;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.ItemApi;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWandCasting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWandCores extends Item {

	public final String[] types = { "horn"};
	public IIcon[] icon;

	public ItemWandCores() {
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir) {
		icon = new IIcon[types.length];
		for (int x = 0; x < types.length; x++)
			this.icon[x] = ir.registerIcon(Elysium.ID + ":wand_rod_" + types[x]);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int meta) {
		return this.icon[meta];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs xCreativeTabs, List list) {
		for (int x = 0; x < types.length; x++) {
			list.add(new ItemStack(this, 1, x));
		}
		
		try
		{
			ItemStack wand = ItemApi.getItem("itemWandCasting", 72);
			((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("pure"));
			((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("horn"));
			list.add(wand);
			wand = ItemApi.getItem("itemWandCasting", 72);
			((ItemWandCasting) wand.getItem()).setCap(wand, (WandCap) WandCap.caps.get("corrupted"));
			((ItemWandCasting) wand.getItem()).setRod(wand, (WandRod) WandRod.rods.get("horn"));
			list.add(wand);

		} catch(Exception e)
		{ }
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + types[stack.getItemDamage()];
	}
}