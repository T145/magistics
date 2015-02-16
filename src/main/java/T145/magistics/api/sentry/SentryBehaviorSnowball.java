package T145.magistics.api.sentry;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SentryBehaviorSnowball extends SentryDefaultProjectile {
	@Override
	public IProjectile getProjectileEntity(EntityLivingBase target, EntityLivingBase owner, IBlockSource block, ItemStack item) {
		World world = block.getWorld();
		int x = block.getXInt(), y = block.getYInt(), z = block.getZInt();

		EntitySnowball snowball = new EntitySnowball(world, new FakeSentryPlayer(world));

		snowball.setPosition(x + 0.5F, y + 1.5F, z + 0.5F);
		double d0 = target.posX - x - 0.5F, d1 = target.posY + target.getEyeHeight() - 1.100000023841858D - snowball.posY, d2 = target.posZ - z - 0.5F;
		snowball.setThrowableHeading(d0, d1 + MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 0.2F, d2, 3F, 1);

		return snowball;
	}

	@Override
	public int reloadSpeed(ItemStack itemstack) {
		return 20;
	}
}