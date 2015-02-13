package T145.magistics.common.blocks.craftingpillars.sentry;

import java.util.Iterator;
import java.util.List;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SentryBehaviorPotion extends SentryDefaultProjectile {
	@Override
	public IProjectile getProjectileEntity(EntityLivingBase target, EntityLivingBase owner, IBlockSource blockSource, ItemStack item) {
		if (!ItemPotion.isSplash(item.getItemDamage()))
			return null;
		World world = blockSource.getWorld();
		int x = blockSource.getXInt(), y = blockSource.getYInt(), z = blockSource.getZInt();

		boolean hasPotionEffect = false;

		List<?> list = Items.potionitem.getEffects(item);

		if (list != null && !list.isEmpty()) {
			Iterator<?> iterator1 = list.iterator();

			while (iterator1.hasNext()) {
				PotionEffect potioneffect = (PotionEffect) iterator1.next();
				int id = potioneffect.getPotionID();

				if (target.getActivePotionEffect(Potion.potionTypes[id]) == null) {
					hasPotionEffect = false;
					break;
				} else
					hasPotionEffect = true;
			}
		}

		if (!hasPotionEffect) {
			EntityPotion potion = new EntityPotion(world, new FakeSentryPlayer(world), item.copy());
			potion.setPosition(x + 0.5F, y + 1.5F, z + 0.5F);

			potion.posY = y + 1.5F;
			double d0 = target.posX - x - 0.5F,  d1 = target.boundingBox.minY + target.height / 3.0F - potion.posY,  d2 = target.posZ - z - 0.5F,  d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

			if (d3 >= 1.0E-7D) {
				float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F, f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI)), f4 = (float) d3 * 0.2F;
				double d4 = d0 / d3, d5 = d2 / d3;
				potion.setLocationAndAngles(x + 0.5F + d4, potion.posY, z + 0.5F + d5, f2, f3);
				potion.yOffset = 0.0F;
				potion.setThrowableHeading(d0, d1 + f4, d2, 1.6F, 3F);
			}
			return potion;
		}
		return null;
	}

	@Override
	public ItemStack spawnEntity(IBlockSource sourceblock, EntityLivingBase target, EntityLivingBase owner, ItemStack item) {
		IProjectile iprojectile = getProjectileEntity(target, owner, sourceblock, item);
		if (iprojectile != null) {
			sourceblock.getWorld().spawnEntityInWorld((Entity) iprojectile);
			item.splitStack(1);
		}
		return item;
	}

	@Override
	public int reloadSpeed(ItemStack itemstack) {
		return 20;
	}
}