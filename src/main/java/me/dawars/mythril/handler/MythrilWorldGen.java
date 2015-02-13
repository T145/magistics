package me.dawars.mythril.handler;

import cpw.mods.fml.common.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.init.*;
import me.dawars.mythril.*;
import me.dawars.mythril.world.gen.feature.*;
import net.minecraft.block.material.*;
import net.minecraft.world.chunk.*;
import net.minecraft.block.*;

public class MythrilWorldGen implements IWorldGenerator
{
    private WorldGenMythrilSpikes spikeGen;
    
    public void generate(final Random random, final int chunkX, final int chunkZ, final World world, final IChunkProvider chunkGenerator, final IChunkProvider chunkProvider) {
        this.spikeGen = new WorldGenMythrilSpikes(Blocks.end_stone);
        switch (world.provider.dimensionId) {
            case 1: {
                this.generateEnd(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
        }
    }
    
    private void generateEnd(final World world, final Random rand, final int chunk_X, final int chunk_Z) {
        if (rand.nextInt(5) == 2) {
            final int x = chunk_X + rand.nextInt(16) + 8;
            final int z = chunk_Z + rand.nextInt(16) + 8;
            final int y = world.getTopSolidOrLiquidBlock(x, z);
            this.spikeGen.generate(world, rand, x, y, z);
        }
        if (rand.nextInt(3) == 0) {
            final int x = chunk_X + rand.nextInt(16) + 8;
            final int z = chunk_Z + rand.nextInt(16) + 8;
            final int y = this.getBottomSolidBlock(world, x, z);
            if (y != -1) {
                new WorldGenHangingGem(MythrilMod.blockMythrilChunk, Blocks.end_stone).generate(world, rand, x, y - 1, z);
            }
        }
    }
    
    public int getBottomSolidBlock(final World world, final int par1, final int par2) {
        final Chunk chunk = world.getChunkFromBlockCoords(par1, par2);
        for (int i = 2; i < world.getActualHeight() / 2; ++i) {
            final Block block = world.getBlock(par1, i, par2);
            if (block.getMaterial().blocksMovement() && block.getMaterial() != Material.leaves) {
                return i;
            }
        }
        return -1;
    }
}
