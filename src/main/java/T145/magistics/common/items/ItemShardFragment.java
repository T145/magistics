package T145.magistics.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShardFragment extends Item {
	public static enum Types {
		aer, aqua, ignis, ordo, perdito, terra;
	}

	public static IIcon icon[] = new IIcon[Types.values().length];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		for (Types type : Types.values())
			icon[type.ordinal()] = r.registerIcon("magistics:shard_fragment/" + type.name());
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