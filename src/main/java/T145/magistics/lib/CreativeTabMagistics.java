package T145.magistics.lib;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import thaumcraft.common.config.ConfigItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class CreativeTabMagistics extends CreativeTabs {
	public CreativeTabMagistics(String lable) {
		super(lable);
		setBackgroundImageName("magistics.png");
		setNoTitle();
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return ConfigItems.itemInkwell;
	}
}