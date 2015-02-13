package me.dawars.mythril.blocks;

import net.minecraft.block.*;
import net.minecraft.item.*;

public class PillarItemBlock extends ItemBlock
{
    public PillarItemBlock(final Block block) {
        super(block);
        this.setHasSubtypes(true);
    }
    
    public String getUnlocalizedName(final ItemStack itemstack) {
        String name = "";
        switch (itemstack.getItemDamage()) {
            case 0: {
                name = "quartz";
                break;
            }
            case 1: {
                name = "base";
                break;
            }
            default: {
                name = "broken";
                break;
            }
        }
        return this.getUnlocalizedName() + "." + name;
    }
    
    public int getMetadata(final int par1) {
        return par1;
    }
}
