package T145.magistics.lib;

import T145.magistics.Magistics;
import T145.magistics.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabMagistics extends CreativeTabs {

	public CreativeTabMagistics() {
		super(Magistics.MODID.toLowerCase());
		setBackgroundImageName("magistics.png");
		setNoTitle();
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return new ItemStack(ModBlocks.INFUSER, 1, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return getIconItemStack();
	}
}