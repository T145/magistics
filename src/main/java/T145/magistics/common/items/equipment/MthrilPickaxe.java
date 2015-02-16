package T145.magistics.common.items.equipment;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MthrilPickaxe extends ItemPickaxe {
	public MthrilPickaxe(Item.ToolMaterial mat) {
		super(mat);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("mithril:" + getUnlocalizedName().substring(5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(ConfigObjects.itemMithrilPickaxe);
		stack.addEnchantment(Enchantment.fortune, 2);
		stack.addEnchantment(Enchantment.unbreaking, 1);
		list.add(stack);
	}
}