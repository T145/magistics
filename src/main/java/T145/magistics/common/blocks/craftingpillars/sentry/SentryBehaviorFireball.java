package T145.magistics.common.blocks.craftingpillars.sentry;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SentryBehaviorFireball extends SentryDefaultProjectile
{
	/**
	 * Return the projectile entity spawned by this dispense behavior.
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

		double d0 = target.posX - x - 0.5F;
		double d1 = target.posY + target.getEyeHeight() - 1.7D - y;
		double d2 = target.posZ - z - 0.5F;

		EntitySmallFireball entityFireball = new EntitySmallFireball(world, new FakeSentryPlayer(world), d0, d1, d2);
		entityFireball.setPosition(x + 0.5F, y + 1.5F, z + 0.5F);
		
		world.spawnEntityInWorld(entityFireball);

		return null;
	}

	/**
	 * Returns the reload speed of the projectile
	 * @param itemstack - current weapon or projectile
	 * @return - time to reload in ticks - default: 20
	 */
	@Override
	public int reloadSpeed(ItemStack itemstack) {
		return 50;
	}
}
