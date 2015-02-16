package T145.magistics.common.items.equipment;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MithrilShovel extends ItemSpade {
	public MithrilShovel(Item.ToolMaterial mat) {
		super(mat);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(ConfigObjects.itemMithrilSpade);
		stack.addEnchantment(Enchantment.silkTouch, 1);
		stack.addEnchantment(Enchantment.unbreaking, 3);
		list.add(stack);
	}
}