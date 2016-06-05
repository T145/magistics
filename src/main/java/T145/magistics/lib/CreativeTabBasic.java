package T145.magistics.lib;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabBasic extends CreativeTabs {
	private ItemStack icon;

	public CreativeTabBasic(String lable, ItemStack iconStack) {
		super(lable);
		icon = iconStack;
	}

	public CreativeTabBasic(String lable, Item iconStack) {
		this(lable, new ItemStack(iconStack));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return getIconItemStack().getItem();
	}
}