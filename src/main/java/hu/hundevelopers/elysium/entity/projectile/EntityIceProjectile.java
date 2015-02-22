package hu.hundevelopers.elysium.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityIceProjectile extends EntityThrowable
{
	public EntityIceProjectile(World world)
    {
        super(world);
        this.setSize(0.5F, 0.5F);
    }
	
	public EntityIceProjectile(World world, EntityLivingBase entity)
	{
		super(world, entity);
	}
	
	public EntityIceProjectile(World world, EntityLivingBase shootingEntity, EntityLivingBase target, float par4, float par5)
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
	 /**
     * Called when this EntityThrowable hits a block or entity.
     */
	@Override
    protected void onImpact(MovingObjectPosition movingObjPos)
    {
        if (movingObjPos.entityHit != null)
        {
            float b0 = 0.5F;

            if (movingObjPos.entityHit instanceof EntityBlaze)
            {
                b0 = 3;
            }

            movingObjPos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), b0);
        }

        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        
        if(worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) == Blocks.fire)
        	worldObj.setBlock((int) this.posX, (int) this.posY, (int) this.posZ, Block.getBlockById(0));
        
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}
