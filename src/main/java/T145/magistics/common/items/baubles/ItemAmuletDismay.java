package T145.magistics.common.items.baubles;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import T145.magistics.api.items.ItemBauble;
import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAmuletDismay extends ItemBauble {
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("magistics:bauble_amulet_death");
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
	public void onWornTick(ItemStack is, EntityLivingBase user) {
		List mobs = user.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox(user.posX - 8.0D, user.posY - 3.0D, user.posZ - 8.0D, user.posX + 8.0D, user.posY + 3.0D, user.posZ + 8.0D));

		for (int mob = 0; mob < mobs.size(); mob++) {
			EntityLiving beast = (EntityLiving) mobs.get(mob);

			if (beast.isEntityUndead() && beast.getAttackTarget() instanceof EntityPlayer) {
				beast.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 40, 0, true));
				is.damageItem(1, user);
				break;
			}
		}
	}
}