package hu.hundevelopers.elysium.entity;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityEvolvedOyster extends EntityAnimal
{
    public EntityEvolvedOyster(World world)
    {
        super(world);
        
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Elysium.blockEnergyCrystal), false));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.setSize(0.3F, 0.3F);
    }
    
    @Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected Item getDropItem()
    {
        return Item.getItemFromBlock(Elysium.blockEnergyCrystal);
    }
    
    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(boolean par1, int par2)
    {

    	this.dropItem(Item.getItemFromBlock(Elysium.blockEnergyCrystal), 3);
        this.dropItem(Item.getItemFromBlock(this.rand.nextBoolean() ? Elysium.blockFloatingShell : Elysium.blockFloatingConch), 1);
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
        return (this.worldObj.getBlock(i, j - 1, k) == Elysium.blockSand || this.worldObj.getBlock(i, j - 1, k) == Elysium.blockGrass) && super.getCanSpawnHere();
    }

	@Override
	public EntityAgeable createChild(EntityAgeable var1)
	{
		return new EntityEvolvedOyster(worldObj);
	}
}