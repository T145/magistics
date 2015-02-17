package T145.magistics.common.items.equipment;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MithrilSword extends ItemSword {
	public MithrilSword(ToolMaterial mat) {
		super(mat);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(ConfigObjects.itemMithrilSword);
		stack.addEnchantment(Enchantment.knockback, 3);
		stack.addEnchantment(Enchantment.looting, 2);
		list.add(stack);
	}
}