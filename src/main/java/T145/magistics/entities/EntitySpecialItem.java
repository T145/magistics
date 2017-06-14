package T145.magistics.entities;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySpecialItem extends EntityItem {

	public EntitySpecialItem(World world, double x, double y, double z, ItemStack stack) {
		super(world);
		setSize(0.25F, 0.25F);
		setPosition(x, y, z);
		setItem(stack);
		rotationYaw = (float) (Math.random() * 360.0D);
		motionX = Math.random() * 0.2D - 0.1D;
		motionY = 0.2D;
		motionZ = Math.random() * 0.2D - 0.1D;
	}

	public EntitySpecialItem(World world) {
		super(world);
		setSize(0.25F, 0.25F);
	}

	@Override
	public void onUpdate() {
		if (ticksExisted > 1) {
			if (motionY > 0.0D) {
				motionY *= 0.9D;
			}

			motionY += 0.04D;

			super.onUpdate();
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage) {
		if (damageSource.isExplosion()) {
			return false;
		}

		return super.attackEntityFrom(damageSource, damage);
	}
}