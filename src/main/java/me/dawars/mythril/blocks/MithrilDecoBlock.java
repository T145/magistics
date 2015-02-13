package me.dawars.mythril.blocks;

import net.minecraft.block.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.texture.*;

public class MithrilDecoBlock extends Block
{
    private static final String[] types;
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
    @SideOnly(Side.CLIENT)
    private IIcon icon_top;
    
    public MithrilDecoBlock(final Material mat) {
        super(mat);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        switch (meta) {
            case 2: {
                if (side == 0 || side == 1) {
                    return this.icon_top;
                }
                break;
            }
        }
        return this.icons[meta];
    }
    
    public int damageDropped(final int p_149692_1_) {
        return p_149692_1_;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(final Item item, final CreativeTabs tab, final List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
        list.add(new ItemStack(item, 1, 2));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister iconRegister) {
        this.icons = new IIcon[MithrilDecoBlock.types.length];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconRegister.registerIcon(this.getTextureName() + "_" + MithrilDecoBlock.types[i]);
        }
        this.icon_top = iconRegister.registerIcon(this.getTextureName() + "_top");
    }
    
    static {
        types = new String[] { "normal", "brick", "carved" };
    }
}
