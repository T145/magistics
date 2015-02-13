package T145.magistics.common.blocks.craftingpillars.sentry;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SentryBehaviorFireball extends SentryDefaultProjectile {
	@Override
	public IProjectile getProjectileEntity(EntityLivingBase target, EntityLivingBase owner, IBlockSource blockSource, ItemStack item) {
		World world = blockSource.getWorld();
		int x = blockSource.getXInt(), y = blockSource.getYInt(), z = blockSource.getZInt();

		double d0 = target.posX - x - 0.5F, d1 = target.posY + target.getEyeHeight() - 1.7D - y, d2 = target.posZ - z - 0.5F;

		EntitySmallFireball fireball = new EntitySmallFireball(world, new FakeSentryPlayer(world), d0, d1, d2);
		fireball.setPosition(x + 0.5F, y + 1.5F, z + 0.5F);

		world.spawnEntityInWorld(fireball);
		return null;
	}

	@Override
	public int reloadSpeed(ItemStack itemstack) {
		return 50;
	}
}