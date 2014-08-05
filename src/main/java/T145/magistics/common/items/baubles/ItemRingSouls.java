package T145.magistics.common.items.baubles;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import thaumcraft.client.fx.FXWisp;
import T145.magistics.common.config.Config;
import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRingSouls extends ItemBauble {
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("magistics:bauble_ring_souls");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return itemIcon;
	}

	@Override
	public BaubleType getBaubleType(ItemStack is) {
		return BaubleType.RING;
	}

	@Override
	public void onWornTick(ItemStack is, EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
			int damage = is.getItemDamage();
			List xpOrbs = player.worldObj.getEntitiesWithinAABB(EntityXPOrb.class, AxisAlignedBB.getBoundingBox(player.posX - 2.0D, player.posY - 2.0D, player.posZ - 2.0D, player.posX + 2.0D, player.posY + 2.0D, player.posZ + 2.0D));

			EntityXPOrb xpOrb = (EntityXPOrb) xpOrbs.get(0);

			if (!xpOrb.isDead) {
				damage -= xpOrb.getXpValue();
				FXWisp fx = new FXWisp(player.worldObj, xpOrb.posX, xpOrb.posY, xpOrb.posZ, 0.5F, 5);
				fx.shrink = true;
				Minecraft.getMinecraft().effectRenderer.addEffect(fx);
				xpOrb.setDead();

				if (damage <= 0) {
					((EntityPlayer) player).inventory.addItemStackToInventory(new ItemStack(Config.item[0], 1, 6));
					damage += 50;
				}
			}
			is.damageItem(damage, player);
		}
	}
}