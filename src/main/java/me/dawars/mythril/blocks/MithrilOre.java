package me.dawars.mythril.blocks;

import java.util.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;

public class MithrilOre extends BaseBlock
{
    private Random rand;
    
    public MithrilOre(final Material mat) {
        super(mat);
        this.rand = new Random();
        this.setHarvestLevel("pickaxe", 3);
    }
    
    public boolean canEntityDestroy(final IBlockAccess world, final int x, final int y, final int z, final Entity entity) {
        return false;
    }
    
    public int getExpDrop(final IBlockAccess p_149690_1_, final int p_149690_5_, final int p_149690_7_) {
        return MathHelper.getRandomIntegerInRange(this.rand, 3, 7);
    }
}
