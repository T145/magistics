package T145.magistics.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemResources extends Item {
	public static enum Types {
		animated_piston(0),
		arcane_singularity(1),
		dust_faint(2),
		eldritch_keystone_inert(3),
		eldritch_keystone_tlhutlh(4),
		eldritch_mechansim(5),
		soul_fragment(6),
		void_ingot(7);

		private Types(int metadata) {}
	}

	@SideOnly(Side.CLIENT)
	public static IIcon icon[];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		for (Types type : Types.values())
			icon[type.ordinal()] = r.registerIcon("magistics:" + type.name());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item i, CreativeTabs t, List l) {
		for (Types type : Types.values())
			l.add(new ItemStack(i, 1, type.ordinal()));
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + Types.values()[is.getItemDamage()];
	}
}