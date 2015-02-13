package me.dawars.mythril.items;

import net.minecraft.client.renderer.texture.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.creativetab.*;
import java.util.*;
import me.dawars.mythril.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;

public class MythrilSword extends ItemSword
{
    public MythrilSword(final Item.ToolMaterial mat) {
        super(mat);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister r) {
        itemIcon = r.registerIcon("mythril:" + this.getUnlocalizedName().substring(5));
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item item, final CreativeTabs tab, final List list) {
        final ItemStack stack = new ItemStack(MythrilMod.itemMythrilSword);
        stack.addEnchantment(Enchantment.knockback, 3);
        stack.addEnchantment(Enchantment.looting, 2);
        list.add(stack);
    }
}
