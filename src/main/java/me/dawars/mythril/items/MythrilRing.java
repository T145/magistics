package me.dawars.mythril.items;

import me.dawars.mythril.MythrilMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

public class MythrilRing extends Item implements IBauble
{
    public MythrilRing() {
        super.maxStackSize = 1;
    }
    
    public ItemStack onItemRightClick(final ItemStack par1ItemStack, final World par2World, final EntityPlayer par3EntityPlayer) {
        if (!MythrilMod.isBaubles) {
            return par1ItemStack;
        }
        if (!par2World.isRemote) {
            final InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(par3EntityPlayer);
            for (int i = 0; i < baubles.getSizeInventory(); ++i) {
                if (baubles.getStackInSlot(i) == null && baubles.isItemValidForSlot(i, par1ItemStack)) {
                    baubles.setInventorySlotContents(i, par1ItemStack.copy());
                    if (!par3EntityPlayer.capabilities.isCreativeMode) {
                        par3EntityPlayer.inventory.setInventorySlotContents(par3EntityPlayer.inventory.currentItem, (ItemStack)null);
                    }
                    this.onEquipped(par1ItemStack, (EntityLivingBase)par3EntityPlayer);
                    break;
                }
            }
        }
        return par1ItemStack;
    }
    
    public EnumRarity getRarity(final ItemStack par1ItemStack) {
        return EnumRarity.uncommon;
    }
    
    public BaubleType getBaubleType(final ItemStack itemstack) {
        return BaubleType.RING;
    }
    
    public void onWornTick(final ItemStack itemstack, final EntityLivingBase player) {
        if (itemstack.getItemDamage() == 0) {
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 20, 0));
            player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 20, 4));
        }
    }
    
    public void onEquipped(final ItemStack itemstack, final EntityLivingBase player) {
        if (!((Entity)player).worldObj.isRemote) {
            ((Entity)player).worldObj.playSoundAtEntity((Entity)player, "random.orb", 0.1f, 1.3f);
        }
    }
    
    public void onUnequipped(final ItemStack itemstack, final EntityLivingBase player) {
    }
    
    public boolean canEquip(final ItemStack itemstack, final EntityLivingBase player) {
        return true;
    }
    
    public boolean canUnequip(final ItemStack itemstack, final EntityLivingBase player) {
        return true;
    }
}
