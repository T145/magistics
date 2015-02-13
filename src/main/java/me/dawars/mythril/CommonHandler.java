package me.dawars.mythril;

import java.util.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.entity.player.*;
import cpw.mods.fml.common.*;
import baubles.api.*;
import net.minecraft.potion.*;
import net.minecraft.item.*;
import cpw.mods.fml.common.eventhandler.*;
import net.minecraftforge.event.entity.player.*;

public class CommonHandler
{
    Random random;
    
    public CommonHandler() {
        this.random = new Random();
    }
    
    @SubscribeEvent
    public void onPlayerGetHurt(final LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer && FMLCommonHandler.instance().getSide().isServer()) {
            final ItemStack slot = BaublesApi.getBaubles((EntityPlayer)event.entityLiving).getStackInSlot(0);
            if (MythrilMod.isBaubles && slot != null && slot.getItem() == MythrilMod.itemEnderNecklace) {
                event.entityLiving.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), 300, 2));
            }
        }
    }
    
    @SubscribeEvent
    public void onPlayerAttacked(final AttackEntityEvent event) {
        if (event.target instanceof EntityPlayer) {
            System.out.println("got hurt" + FMLCommonHandler.instance().getSide());
            final ItemStack slot1 = BaublesApi.getBaubles(event.entityPlayer).getStackInSlot(1);
            final ItemStack slot2 = BaublesApi.getBaubles(event.entityPlayer).getStackInSlot(2);
            if (((slot1 != null && slot1.getItem() == MythrilMod.itemWitherRing) || (slot2 != null && slot2.getItem() == MythrilMod.itemWitherRing)) && this.random.nextInt(3) == 0) {
                ((EntityPlayer)event.target).addPotionEffect(new PotionEffect(Potion.wither.getId(), 200, 2));
            }
        }
    }
}
