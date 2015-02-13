package me.dawars.mythril.items;

import net.minecraft.entity.*;
import me.dawars.mythril.*;
import net.minecraft.item.*;
import net.minecraft.creativetab.*;
import java.util.*;
import net.minecraft.enchantment.*;
import cpw.mods.fml.relauncher.*;

public class MythrilArmor extends ItemArmor
{
    public MythrilArmor(final ItemArmor.ArmorMaterial mat, final int renderIndex, final int armorType) {
        super(mat, renderIndex, armorType);
    }
    
    public String getArmorTexture(final ItemStack stack, final Entity entity, final int slot, final String type) {
        if (stack.getItem() == MythrilMod.itemMythrilHelmet || stack.getItem() == MythrilMod.itemMythrilChest || stack.getItem() == MythrilMod.itemMythrilBoots) {
            return "mythril:textures/models/armor/mythril_layer_1.png";
        }
        return "mythril:textures/models/armor/mythril_layer_2.png";
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item item, final CreativeTabs tab, final List list) {
        final ItemStack stack = new ItemStack(item);
        if (item == MythrilMod.itemMythrilHelmet) {
            stack.addEnchantment(Enchantment.respiration, 3);
            stack.addEnchantment(Enchantment.aquaAffinity, 1);
        }
        else if (item == MythrilMod.itemMythrilChest) {
            stack.addEnchantment(Enchantment.thorns, 4);
        }
        else if (item == MythrilMod.itemMythrilBoots) {
            stack.addEnchantment(Enchantment.featherFalling, 127);
        }
        list.add(stack);
    }
}
