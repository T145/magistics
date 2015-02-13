package me.dawars.mythril.blocks;

import java.util.Random;

import me.dawars.mythril.MythrilMod;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MithrilChunk extends BaseBlock
{
    private Random rand = new Random();
    
    public MithrilChunk(final Material mat) {
        super(mat);
        this.setHarvestLevel("pickaxe", 2);
        this.setTickRandomly(true);
    }
    
    public boolean canEntityDestroy(final IBlockAccess world, final int x, final int y, final int z, final Entity entity) {
        return false;
    }
    
    public int getExpDrop(final IBlockAccess p_149690_1_, final int p_149690_5_, final int p_149690_7_) {
        return MathHelper.getRandomIntegerInRange(this.rand, 1, 4);
    }
    
    public Item getItemDropped(final int p_149650_1_, final Random rand, final int p_149650_3_) {
        return MythrilMod.itemMythrilChunk;
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World world, final int x, final int y, final int z, final Random rand) {
        if ((!world.getBlock(x, y + 1, z).isBlockNormalCube() || !world.getBlock(x, y - 1, z).isBlockNormalCube() || !world.getBlock(x, y, z + 1).isBlockNormalCube() || !world.getBlock(x, y, z - 1).isBlockNormalCube() || !world.getBlock(x + 1, y, z).isBlockNormalCube() || !world.getBlock(x - 1, y, z).isBlockNormalCube()) && FMLClientHandler.instance().getClientPlayerEntity().getDistanceSq((double)x, (double)y, (double)z) < 576.0) {
            world.updateLightByType(EnumSkyBlock.Block, x, y, z);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public int getLightValue(final IBlockAccess world, final int x, final int y, final int z) {
        int seed = x + y + z;
        seed %= 4;
        final double light = Math.max(0.0, Math.min(15.0, (Math.sin(System.currentTimeMillis() + seed * 3.1415) + 1.0) * 7.0));
        return (int)light;
    }
}
