package hu.hundevelopers.elysium.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFireballProjectile extends EntityThrowable
{

	public EntityFireballProjectile(World world)
	{
		super(world);
        this.setSize(0.5F, 0.5F);
	}
	
	public EntityFireballProjectile(World world, EntityLivingBase entity)
	{
		super(world, entity);
	}
	public EntityFireballProjectile(World world, EntityLivingBase shootingEntity, EntityLivingBase target, float par4, float par5)
    {
        super(world);
        this.renderDistanceWeight = 10.0D;

        this.posY = shootingEntity.posY + (double)shootingEntity.getEyeHeight() - 0.10000000149011612D;
        double d0 = target.posX - shootingEntity.posX;
        double d1 = target.boundingBox.minY + (double)(target.height / 3.0F) - this.posY;
        double d2 = target.posZ - shootingEntity.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D)
        {
            float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(shootingEntity.posX + d4, this.posY, shootingEntity.posZ + d5, f2, f3);
            this.yOffset = 0.0F;
            float f4 = (float)d3 * 0.2F;
            this.setThrowableHeading(d0, d1 + (double)f4, d2, par4, par5);
        }
    }

	@Override
	protected void onImpact(MovingObjectPosition movingObjPos)
	{
		if (!this.worldObj.isRemote)
        {
            if (movingObjPos.entityHit != null)
            {
                if (!movingObjPos.entityHit.isImmuneToFire() && movingObjPos.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(new EntitySmallFireball(worldObj, this.getThrower(), 0, 0, 0), this.getThrower()), 5.0F))
                {
                    movingObjPos.entityHit.setFire(1);
                }
            }
            else
            {
                int i = movingObjPos.blockX;
                int j = movingObjPos.blockY;
                int k = movingObjPos.blockZ;

                switch (movingObjPos.sideHit)
                {
                    case 0:
                        --j;
                        break;
                    case 1:
                        ++j;
                        break;
                    case 2:
                        --k;
                        break;
                    case 3:
                        ++k;
                        break;
                    case 4:
                        --i;
                        break;
                    case 5:
                        ++i;
                }

                if (this.worldObj.isAirBlock(i, j, k))
                {
                    this.worldObj.setBlock(i, j, k, Blocks.fire);
                }
            }

            this.setDead();
        }
	}
   
	 /**
     * Called to update the entity's position/logic.
     */
	@Override
    public void onUpdate()
    {
    	super.onUpdate();

        this.setFire(1);
        this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);

    }
}