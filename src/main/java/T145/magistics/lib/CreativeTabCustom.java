package T145.magistics.lib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabCustom extends CreativeTabs {
	private ItemStack stack;

	public CreativeTabCustom(String lable, ItemStack iconStack) {
		super(lable);
		stack = iconStack;
	}

	public CreativeTabCustom(String lable, Item iconStack) {
		this(lable, new ItemStack(iconStack));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return getIconItemStack().getItem();
	}
}