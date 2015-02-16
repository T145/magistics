package T145.magistics.common.items.equipment;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MithrilAxe extends ItemAxe {
	public MithrilAxe(ToolMaterial mat) {
		super(mat);
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(ConfigObjects.itemMithrilAxe);
		stack.addEnchantment(Enchantment.sharpness, 2);
		stack.addEnchantment(Enchantment.fortune, 3);
		list.add(stack);
	}
}