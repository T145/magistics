package T145.magistics.common.blocks.craftingpillars.sentry;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SentryBehaviorArrow extends SentryDefaultProjectile {
	@Override
	public IProjectile getProjectileEntity(EntityLivingBase target, EntityLivingBase owner, IBlockSource blockSource, ItemStack item) {
		World world = blockSource.getWorld();
		int x = blockSource.getXInt(), y = blockSource.getYInt(), z = blockSource.getZInt();

		EntityArrow arrow = new EntityArrow(world, new FakeSentryPlayer(world), target, 1.6F, 3F);
		arrow.setDamage(arrow.getDamage() + 1);
		arrow.setPosition(x + 0.5F, y + 1.5F, z + 0.5F);

		arrow.posY = y + 1.5F;
		double d0 = target.posX - x - 0.5F, d1 = target.boundingBox.minY + target.height / 3.0F - arrow.posY, d2 = target.posZ - z - 0.5F, d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D) {
			float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F, f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI)), f4 = (float) d3 * 0.2F;
			double d4 = d0 / d3, d5 = d2 / d3;
			arrow.setLocationAndAngles(x + 0.5F + d4, arrow.posY, z + 0.5F + d5, f2, f3);
			arrow.yOffset = 0.0F;
			arrow.setThrowableHeading(d0, d1 + f4, d2, 1.6F, 3F);
		}
		return arrow;
	}

	@Override
	public int reloadSpeed(ItemStack itemstack) {
		return 20;
	}
}