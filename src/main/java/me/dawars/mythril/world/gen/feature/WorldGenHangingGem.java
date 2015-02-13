package me.dawars.mythril.world.gen.feature;

import net.minecraft.world.gen.feature.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import java.util.*;

public class WorldGenHangingGem extends WorldGenerator
{
    private Block stoneBlock;
    private Block gemBlock;
    float radius;
    int maxLength;
    int minLength;
    
    public WorldGenHangingGem(final Block gemBlock, final Block stoneBlock) {
        this.radius = 2.0f;
        this.maxLength = 5;
        this.minLength = 3;
        this.gemBlock = gemBlock;
        this.stoneBlock = stoneBlock;
    }
    
    public boolean generate(final World world, final Random rand, final int x, final int y, final int z) {
        if (!world.isAirBlock(x, y, z)) {
            return false;
        }
        if (world.getBlock(x, y + 1, z) != this.stoneBlock) {
            return false;
        }
        world.setBlock(x, y, z, this.gemBlock, 0, 2);
        final int length = rand.nextInt(this.maxLength - this.minLength) + this.minLength;
        for (int i = -2; i < 2; ++i) {
            for (int j = -2; j < 2; ++j) {
                for (int k = -2; k <= (int)(1.0f / Math.max(0.5f, i * i + j * j) * length + rand.nextInt(2)); ++k) {
                    if (i * i + j * j < this.radius * this.radius) {
                        world.setBlock(x + i, y - k, z + j, this.gemBlock);
                    }
                }
            }
        }
        return true;
    }
}
