package me.dawars.mythril.items;

import net.minecraft.creativetab.*;
import java.util.*;
import me.dawars.mythril.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;
import cpw.mods.fml.relauncher.*;

public class MythrilAxe extends ItemAxe
{
    public MythrilAxe(final Item.ToolMaterial mat) {
        super(mat);
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item item, final CreativeTabs tab, final List list) {
        final ItemStack stack = new ItemStack(MythrilMod.itemMythrilAxe);
        stack.addEnchantment(Enchantment.sharpness, 2);
        stack.addEnchantment(Enchantment.fortune, 3);
        list.add(stack);
    }
}
