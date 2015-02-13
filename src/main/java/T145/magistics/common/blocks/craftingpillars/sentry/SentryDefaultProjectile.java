package T145.magistics.common.blocks.craftingpillars.sentry;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import T145.magistics.api.sentry.ISentryBehaviorItem;

public abstract class SentryDefaultProjectile implements ISentryBehaviorItem {
	@Override
	public final ItemStack dispense(IBlockSource block, EntityLivingBase target, EntityLivingBase owner, ItemStack item) {
		playSound(block);
		spawnParticles(block);
		spawnEntity(block, target, owner, item);
		return item;
	}

	public ItemStack spawnEntity(IBlockSource sourceblock, EntityLivingBase target, EntityLivingBase owner, ItemStack item) {
		IProjectile projectile = getProjectileEntity(target, owner, sourceblock, item);
		if (projectile != null)
			sourceblock.getWorld().spawnEntityInWorld((Entity) projectile);
		item.splitStack(1);
		return item;
	}

	protected void playSound(IBlockSource block) {
		block.getWorld().playAuxSFX(1000, block.getXInt(), block.getYInt(), block.getZInt(), 0);
	}

	protected void spawnParticles(IBlockSource block) {}

	protected abstract IProjectile getProjectileEntity(EntityLivingBase target, EntityLivingBase owner, IBlockSource block, ItemStack item);
}