package T145.magistics.common.items.equipment;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MithrilHoe extends ItemHoe
{
    public MithrilHoe(final Item.ToolMaterial p_i45343_1_) {
        super(p_i45343_1_);
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item item, final CreativeTabs tab, final List list) {
        final ItemStack stack = new ItemStack(ConfigObjects.itemMithrilHoe);
        stack.addEnchantment(Enchantment.unbreaking, 127);
        list.add(stack);
    }
}
