package T145.magistics.common.items.baubles;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import thaumcraft.common.Thaumcraft;
import T145.magistics.api.items.ItemBauble;
import T145.magistics.common.config.MagisticsConfig;
import T145.magistics.common.items.ItemResources;
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
	public void onWornTick(ItemStack is, EntityLivingBase user) {
		if (user instanceof EntityPlayer) {
			int damage = is.getItemDamage();
			List<EntityXPOrb> xpOrbs = user.worldObj.getEntitiesWithinAABB(EntityXPOrb.class, AxisAlignedBB.getBoundingBox(user.posX - 2.0D, user.posY - 2.0D, user.posZ - 2.0D, user.posX + 2.0D, user.posY + 2.0D, user.posZ + 2.0D));
			EntityXPOrb xpOrb = null;

			if (xpOrbs.size() > 0)
				xpOrb = xpOrbs.get(0);

			if (xpOrb != null && !xpOrb.isDead) {
				damage -= xpOrb.getXpValue();
				Thaumcraft.proxy.bolt(user.worldObj, xpOrb, user);
				xpOrb.setDead();

				if (damage <= 0) {
					((EntityPlayer) user).inventory.addItemStackToInventory(new ItemStack(MagisticsConfig.items[0], 1, ItemResources.Types.soul_fragment.ordinal()));
					damage += 50;
				}
				is.damageItem(damage, user);
			}
		}
	}
}