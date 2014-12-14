package T145.magistics.common.items.baubles;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import T145.magistics.api.items.baubles.ItemBauble;
import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAmuletLife extends ItemBauble {
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("magistics:bauble_amulet_life");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return itemIcon;
	}

	@Override
	public BaubleType getBaubleType(ItemStack is) {
		return BaubleType.AMULET;
	}

	@Override
	public void onUpdate(ItemStack is, World world, Entity user, int par4, boolean par5) {
		Random rand = new Random();
		if (rand.nextInt(40) == 0 && user instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) user;
			if (player.getHealth() < player.getMaxHealth()) {
				player.heal(0.5F);
				player.worldObj.playSoundAtEntity(player, "magistics.heal", 1F, 1F);
			}
		}
	}

	@Override
	public void onWornTick(ItemStack is, EntityLivingBase player) {
		Random rand = new Random();
		if (rand.nextInt(11000) == 1) {
			player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 12000, 2));
			player.worldObj.playSoundAtEntity(player, "magistics.heal", 1F, 1F);
		}
	}
}