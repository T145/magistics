package me.dawars.mythril.items;

import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.biome.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.util.*;
import net.minecraft.entity.boss.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;

public class MithrilWhistle extends Item
{
    private static Random rand;
    private long lastPlay;
    
    public MithrilWhistle() {
        this.lastPlay = 0L;
        this.setMaxDamage(super.maxStackSize = 1);
    }
    
    public ItemStack onItemRightClick(final ItemStack itemStack, final World world, final EntityPlayer entity) {
        if (!world.isRemote && (System.currentTimeMillis() - this.lastPlay) / 1000L > this.getMaxItemUseDuration(itemStack) / 20) {
            this.lastPlay = System.currentTimeMillis();
            world.playSoundAtEntity((Entity)entity, "mythril:flute", 1.0f, 1.0f);
            if (world.getWorldChunkManager().getBiomeGenAt(0, 0) instanceof BiomeGenEnd) {
                if (this.isDragonAlive(world) >= 1) {
                    entity.addChatMessage((IChatComponent)new ChatComponentText(LanguageRegistry.instance().getStringLocalization("flute.dragonExist." + MithrilWhistle.rand.nextInt(4))));
                }
            }
            else {
                entity.addChatMessage((IChatComponent)new ChatComponentText(LanguageRegistry.instance().getStringLocalization("flute.tips." + MithrilWhistle.rand.nextInt(3))));
            }
        }
        entity.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }
    
    private int isDragonAlive(final World world) {
        final List list = world.loadedEntityList;
        int dragonNum = 0;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) instanceof EntityDragon) {
                ++dragonNum;
            }
        }
        return dragonNum;
    }
    
    public ItemStack onEaten(final ItemStack itemStack, final World world, final EntityPlayer entity) {
        if (!world.isRemote && world.getWorldChunkManager().getBiomeGenAt(0, 0) instanceof BiomeGenEnd && this.isDragonAlive(world) < 1) {
            itemStack.damageItem(2, (EntityLivingBase)entity);
            final EntityDragon entitydragon = new EntityDragon(world);
            entitydragon.setLocationAndAngles(0.0, 128.0, 0.0, MithrilWhistle.rand.nextFloat() * 360.0f, 0.0f);
            world.spawnEntityInWorld((Entity)entitydragon);
        }
        return itemStack;
    }
    
    public boolean isFull3D() {
        return true;
    }
    
    public int getMaxItemUseDuration(final ItemStack item) {
        return 200;
    }
    
    public EnumAction getItemUseAction(final ItemStack par1ItemStack) {
        return EnumAction.block;
    }
    
    static {
        MithrilWhistle.rand = new Random();
    }
}
