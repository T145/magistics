package T145.magistics.api.sentry;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SentryBehaviorArrow extends SentryDefaultProjectile
{
	/**
	 * Return the projectile entity spawned by this Sentry behavior.
	 * @param target - the target of the Sentry
	 * @param owner - owner of the Sentry - use it only when the weapon needs to consume "energy" from the player
	 * @param blockSource - the Sentry block
	 * @param item - Weapon or projectile placed into the Sentry (this is registered to the 
	 */
	@Override
	protected IProjectile getProjectileEntity(EntityLivingBase target, EntityLivingBase owner, IBlockSource blockSource, ItemStack item) {

		World world = blockSource.getWorld();
		int x = blockSource.getXInt();
		int y = blockSource.getYInt();
		int z = blockSource.getZInt();


		//		EntityArrow entityammo = new EntityArrow(world, x + 0.5F, y + 1.5F, z + 0.5F);
		EntityArrow entityammo = new EntityArrow(world, new FakeSentryPlayer(world), target, 1.6F, 3F);
		entityammo.setDamage(entityammo.getDamage() + 1);

		entityammo.setPosition(x + 0.5F, y + 1.5F, z + 0.5F);

		entityammo.posY = y + 1.5F;
		double d0 = target.posX - x - 0.5F;
		double d1 = target.boundingBox.minY + target.height / 3.0F - entityammo.posY;
		double d2 = target.posZ - z - 0.5F;
		double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D)
		{
			float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			entityammo.setLocationAndAngles(x + 0.5F + d4, entityammo.posY, z + 0.5F + d5, f2, f3);
			entityammo.yOffset = 0.0F;
			float f4 = (float)d3 * 0.2F;
			entityammo.setThrowableHeading(d0, d1 + f4, d2, 1.6F, 3F);
		}
		return entityammo;
	}
	
	/**
	 * Returns the reload speed of the projectile
	 * @param itemstack - current weapon or projectile
	 * @return - time to reload in ticks - default: 20
	 */
	@Override
	public int reloadSpeed(ItemStack itemstack) {
		return 20;
	}
}
