package hu.hundevelopers.elysium.entity.projectile;

import hu.hundevelopers.elysium.api.Staff;
import hu.hundevelopers.elysium.render.ElysiumEffectRenderer;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBlockProjectile extends EntityThrowable
{
	
	public EntityBlockProjectile(World world)
    {
    	super(world);
        this.setSize(1F, 1F);
    }
	Block block;
	
	public EntityBlockProjectile(World world, Block block)
	{
		super(world);
		this.block = block;
	}
    private EntityLivingBase thrower;

	public EntityBlockProjectile(World world, EntityLivingBase entity, Block block)
	{
		super(world, entity);
		this.block = block;
		this.thrower = entity;
	}
	
	public EntityBlockProjectile(World world, EntityLivingBase shootingEntity, EntityLivingBase target, float par4, float par5, Block block)
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
	
	public EntityBlockProjectile(World world, double x, double y, double z, Block block)
	{
		super(world, x, y, z);
		this.block = block;
	}

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition movingObjPos)
    {
        if (movingObjPos.entityHit != null)
        {
        	if(this.thrower instanceof EntityPlayer)
        		movingObjPos.entityHit.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) this.thrower), Staff.getDamageForBlock(block));
        	else
        		movingObjPos.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.thrower), Staff.getDamageForBlock(block));
        	
        	EntityItem entityitem = new EntityItem(this.worldObj);
            entityitem.setEntityItemStack(new ItemStack(block));
            entityitem.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            this.worldObj.spawnEntityInWorld(entityitem);
            
            if(worldObj.isRemote)
                ElysiumEffectRenderer.addBlockHitEffect(worldObj, this.posX, this.posY, this.posZ, block, 0);
			
        } else {
	
	        if (!this.worldObj.isRemote)
	        {
	        	if(worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ).isReplaceable(worldObj, movingObjPos.blockX, movingObjPos.blockY, movingObjPos.blockZ))
	        	{
	        		if (!this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ, block))
		        	{
		                EntityItem entityitem = new EntityItem(this.worldObj);
		                entityitem.setEntityItemStack(new ItemStack(block));
		                entityitem.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
		                this.worldObj.spawnEntityInWorld(entityitem);
		        	}
	        	} else {
	        		if (!this.worldObj.setBlock((int)this.posX, (int)this.posY+1, (int)this.posZ, block))
		        	{
		                EntityItem entityitem = new EntityItem(this.worldObj);
		                entityitem.setEntityItemStack(new ItemStack(block));
		                entityitem.setLocationAndAngles(this.posX, this.posY+1, this.posZ, this.rotationYaw, 0.0F);
		                this.worldObj.spawnEntityInWorld(entityitem);
		        	}
	        	}
	        }
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    
    public Block getBlock()
    {
    	return block;
    }
}
