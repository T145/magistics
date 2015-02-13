package me.dawars.mythril.items;

import java.util.List;

import me.dawars.mythril.MythrilMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MythrilPickaxe extends ItemPickaxe
{
    public MythrilPickaxe(final Item.ToolMaterial mat) {
        super(mat);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister r) {
        itemIcon = r.registerIcon("mythril:" + this.getUnlocalizedName().substring(5));
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item item, final CreativeTabs tab, final List list) {
        final ItemStack stack = new ItemStack(MythrilMod.itemMythrilPickaxe);
        stack.addEnchantment(Enchantment.fortune, 2);
        stack.addEnchantment(Enchantment.unbreaking, 1);
        list.add(stack);
    }
}
