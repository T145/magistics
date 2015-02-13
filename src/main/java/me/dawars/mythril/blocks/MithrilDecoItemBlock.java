package me.dawars.mythril.blocks;

import net.minecraft.block.*;
import net.minecraft.item.*;

public class MithrilDecoItemBlock extends ItemBlock
{
    public MithrilDecoItemBlock(final Block block) {
        super(block);
        this.setHasSubtypes(true);
    }
    
    public String getUnlocalizedName(final ItemStack itemstack) {
        String name = "";
        switch (itemstack.getItemDamage()) {
            case 0: {
                name = "normal";
                break;
            }
            case 1: {
                name = "brick";
                break;
            }
            default: {
                name = "carved";
                break;
            }
        }
        return this.getUnlocalizedName() + "." + name;
    }
    
    public int getMetadata(final int meta) {
        return meta;
    }
}
