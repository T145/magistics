package T145.magistics.common.items.armor;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MithrilArmor extends ItemArmor {
	public MithrilArmor(ArmorMaterial mat, int renderIndex, int armorType) {
		super(mat, renderIndex, armorType);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (stack.getItem() == ConfigObjects.itemMithrilHelmet || stack.getItem() == ConfigObjects.itemMithrilChest || stack.getItem() == ConfigObjects.itemMithrilBoots)
			return "mithril:textures/models/armor/mithril_layer_1.png";
		return "mithril:textures/models/armor/mithril_layer_2.png";
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(item);
		if (item == ConfigObjects.itemMithrilHelmet) {
			stack.addEnchantment(Enchantment.respiration, 3);
			stack.addEnchantment(Enchantment.aquaAffinity, 1);
		} else if (item == ConfigObjects.itemMithrilChest)
			stack.addEnchantment(Enchantment.thorns, 4);
		else if (item == ConfigObjects.itemMithrilBoots)
			stack.addEnchantment(Enchantment.featherFalling, 127);
		list.add(stack);
	}
}