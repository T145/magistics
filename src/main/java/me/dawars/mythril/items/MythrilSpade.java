package me.dawars.mythril.items;

import net.minecraft.creativetab.*;
import java.util.*;
import me.dawars.mythril.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;
import cpw.mods.fml.relauncher.*;

public class MythrilSpade extends ItemSpade
{
    public MythrilSpade(final Item.ToolMaterial mat) {
        super(mat);
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item item, final CreativeTabs tab, final List list) {
        final ItemStack stack = new ItemStack(MythrilMod.itemMythrilSpade);
        stack.addEnchantment(Enchantment.silkTouch, 1);
        stack.addEnchantment(Enchantment.unbreaking, 3);
        list.add(stack);
    }
}
