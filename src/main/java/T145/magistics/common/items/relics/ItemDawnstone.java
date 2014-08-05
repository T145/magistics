package T145.magistics.common.items.relics;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDawnstone extends Item {
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("magistics:dawnstone");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return itemIcon;
	}
}