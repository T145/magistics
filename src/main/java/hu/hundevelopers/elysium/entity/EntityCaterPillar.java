package hu.hundevelopers.elysium.entity;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.api.ElysiumApi;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityCaterPillar extends EntityAnimal
{

	private int eatingCooldown = 80;
	
    public EntityCaterPillar(World world)
    {
        super(world);
        this.setSize(0.9F, 0.5F);
        
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Elysium.blockPalestone), false));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }
    
    @Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}
    
    public float protationYaw = 0F;
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
    	if(this.worldObj.isRemote)
    	{
    		this.protationYaw = this.rotationYaw;
    	}
    	
        super.onUpdate();

    }

    @Override
    protected void updateAITasks()
    {
        super.updateAITasks();

//        if (this.eatingCooldown > 0)
//        {
//        	this.eatingCooldown--;
//            if (!this.worldObj.getBlock(MathHelper.floor_double(this.posX), (int)this.posY + 1, MathHelper.floor_double(this.posZ)).isNormalCube())
//            {
//                this.setIsBatHanging(false);
//                this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
//            }
//            else
//            {
//                if (this.rand.nextInt(200) == 0)
//                {
//                    this.rotationYawHead = (float)this.rand.nextInt(360);
//                }
//
//                if (this.worldObj.getClosestPlayerToEntity(this, 4.0D) != null)
//                {
//                    this.setIsBatHanging(false);
//                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
//                }
//            }
//        }
//        else
//        {
//            if (this.spawnPosition != null && (!this.worldObj.isAirBlock(this.spawnPosition.posX, this.spawnPosition.posY, this.spawnPosition.posZ) || this.spawnPosition.posY < 1))
//            {
//                this.spawnPosition = null;
//            }
//
//            if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F)
//            {
//                this.spawnPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
//            }
//
//            double d0 = (double)this.spawnPosition.posX + 0.5D - this.posX;
//            double d1 = (double)this.spawnPosition.posY + 0.1D - this.posY;
//            double d2 = (double)this.spawnPosition.posZ + 0.5D - this.posZ;
//            this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
////            this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
//            this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
//            float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
//            float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
//            this.moveForward = 0.5F;
//            this.rotationYaw += f1;
//
//            if (this.rand.nextInt(100) == 0 && this.worldObj.getBlock(MathHelper.floor_double(this.posX), (int)this.posY + 1, MathHelper.floor_double(this.posZ)).isNormalCube())
//            {
//                this.setIsBatHanging(true);
//            }
//        }
    }
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected Item getDropItem()
    {
        return Item.getItemFromBlock(Elysium.blockPalestone);
    }
    
    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);

        for (int k = 0; k < j; ++k)
        {
            this.dropItem(this.getDropItem(), 1);
        }
        this.dropItem(Item.getItemFromBlock(Elysium.blockEnergyCrystal), 1);
    }
    
    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }
    
    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);
        return this.worldObj.getBlock(i, j - 1, k) == Elysium.blockGrass && super.getCanSpawnHere();
    }

	@Override
	public EntityAgeable createChild(EntityAgeable var1)
	{
		return new EntityCaterPillar(worldObj);
	}
}