package T145.magistics.common.blocks.craftingpillars.sentry;

import T145.magistics.api.sentry.ISentryBehaviorItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;

public abstract class SentryDefaultProjectile implements ISentryBehaviorItem
{

	/**
	 * Returns the specified ItemStack remaining in the Sentry.
	 * @param sourceblock - position info for the block
	 * @param target - the target the Sentry is shooting at
	 * @param owner - owner of the Sentry - use {@link FakeSentryPlayer} instead - not 1.7.10 compatibile
	 * @param item - the item placed into the pillar
	 * @return itemstack remaining in the Sentry after a shoot
	 */
	@Override
	public final ItemStack dispense(IBlockSource blockSource,  EntityLivingBase target, EntityLivingBase owner, ItemStack item)
	{
		this.playSound(blockSource);
		this.spawnParticles(blockSource);
		this.spawnEntity(blockSource, target, owner, item);
		return item;
	}

	/**
	 * Spawns the entity.
	 * @param sourceblock - position info for the block
	 * @param target - the target the Sentry is shooting at
	 * @param item - the item placed into the pillar
	 * @return - modified itemstack after shooting
	 */
	public ItemStack spawnEntity(IBlockSource sourceblock, EntityLivingBase target, EntityLivingBase owner, ItemStack item)
	{
		IProjectile iprojectile = this.getProjectileEntity(target, owner, sourceblock, item);
		if(iprojectile != null)
			sourceblock.getWorld().spawnEntityInWorld((Entity)iprojectile);
		item.splitStack(1);
		return item;
	}

	/**
	 * Play the appropriate sound for the shooting.
	 */
	protected void playSound(IBlockSource blockSource)
	{
		blockSource.getWorld().playAuxSFX(1000, blockSource.getXInt(), blockSource.getYInt(), blockSource.getZInt(), 0);
	}

	/**
	 * Order clients to display particles for shooting.
	 */
	protected void spawnParticles(IBlockSource blockSource) {}

	/**
	 * Return the projectile entity spawned by this Sentry behavior.
	 * @param target - the target of the Sentry
	 * @param owner - owner of the Sentry - use it only when the weapon needs to consume "energy" from the player
	 * @param blockSource - the Sentry block
	 * @param item - Weapon or projectile placed into the Sentry (this is registered to the 
	 */
	protected abstract IProjectile getProjectileEntity(EntityLivingBase target, EntityLivingBase owner, IBlockSource blockSource, ItemStack item);
}
