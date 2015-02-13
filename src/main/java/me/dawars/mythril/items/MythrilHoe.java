package me.dawars.mythril.items;

import net.minecraft.creativetab.*;
import java.util.*;
import me.dawars.mythril.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;
import cpw.mods.fml.relauncher.*;

public class MythrilHoe extends ItemHoe
{
    public MythrilHoe(final Item.ToolMaterial p_i45343_1_) {
        super(p_i45343_1_);
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item item, final CreativeTabs tab, final List list) {
        final ItemStack stack = new ItemStack(MythrilMod.itemMythrilHoe);
        stack.addEnchantment(Enchantment.unbreaking, 127);
        list.add(stack);
    }
}
