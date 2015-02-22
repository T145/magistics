package hu.hundevelopers.elysium.entity;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHero extends EntityAgeable implements IRangedAttackMob
{
	public int colorHair;
	public int colorEye;
	public int colorShirt;
	public int colorJeans;
	public int colorSkin;
	public int colorMetal;//belt, bracelet

	private static final ItemStack heroWeapons[] = {
		new ItemStack(Elysium.itemSwordFostimber),
		new ItemStack(Elysium.itemSwordPalestone),
		new ItemStack(Items.bow)
	};

	private static final int maxHairType = 3;
	private static final int maxHeadType = 3;
	private static final int maxJeansType = 1;
	private static final int maxShirtType = 1;
	private static final int maxAccessoriesType = 2;
	
	
	protected static final int skinColors[] = {
		0x77372c, 0xf3ccb8, 0xedb494, 0x7f4a3a, 0xe4afa6, 0xc8876f,
		0xf5c3bb, 0xe6c3a7, 0xedb9a9, 0xdfa98b, 0xd29b85, 0xce7f5e,
		0xec9f94, 0xdf8185, 0xe5a389, 0xf2bc9e, 0xcc9373, 0xeac5b9,
		0xecaba6, 0xf3c2b2, 0xe5b8b1, 0xd78b7c, 0xdba99f, 0xd1947d
	};
	protected static final int eyeColors[] = {
		0x4278a2, 0x7f9d7d, 0x774241, 0x7b836f, 0x927b35
	};
	protected static final int hairColors[] = {
		0xd9ba84, 0x8e6a54, 0xdec092, 0xdeb77f, 0xe2bd74, 0xb99263, 0x7f5c44, 0xc99557,
		0xa36844, 0xb6633f, 0x8f5b30, 0x80553c, 0x833b2d, 0x63412c, 0x403029, 0x573b23,
		0x4a2629, 0x5c2824, 0x422c2f, 0x4e302d, 0x7f5c4b, 0x2e1e1f, 0x604444, 0x151413,
		0xffffff
	};
	protected static final int metalColors[] = {
		0xe5e5e3, 0xb0b0af, 0xe8e2ca, 0xbbbbb9,
		0xe7c86d, 0xf0daa9, 0xcea282, 0xc1c69a
	};
	
	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1D, 20, 60, 15.0F);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityMob.class, 1D, false);
    
	public EntityHero(World world)
	{
		super(world);
		
		this.setSize(0.7F, 2.1F);
        this.isImmuneToFire = true;

        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));

        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityHero.class, 5.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.mobSelector));
        
//        if (worldObj != null && !worldObj.isRemote)
//        {
//            this.setCombatTask();
//        }
	}
	
	/**
     * sets this entity's combat AI.
     */
    public void setCombatTask()
    {
        this.tasks.removeTask(this.aiAttackOnCollide);
        this.tasks.removeTask(this.aiArrowAttack);
        ItemStack itemstack = this.getHeldItem();

        if (itemstack != null && itemstack.getItem() == Items.bow)
        {
            this.tasks.addTask(4, this.aiArrowAttack);
        }
        else
        {
            this.tasks.addTask(4, this.aiAttackOnCollide);
        }
    }
	
	@Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
        this.dataWatcher.addObject(17, Integer.valueOf(0));//skin
        this.dataWatcher.addObject(18, Integer.valueOf(0));//hair
        this.dataWatcher.addObject(19, Integer.valueOf(0));//eye
        this.dataWatcher.addObject(20, Integer.valueOf(0));//shirt
        this.dataWatcher.addObject(21, Integer.valueOf(0));//jeans
        this.dataWatcher.addObject(22, Integer.valueOf(0));//metal

        this.dataWatcher.addObject(23, Integer.valueOf(0));//skin, hair, head, shirt, jeans, metal type
    }
	 
	@Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
    {
        data = super.onSpawnWithEgg(data);

        this.setIsMale(this.getRNG().nextBoolean());
        this.setHasBeard(this.getRNG().nextBoolean());
        this.setHasBelt(this.getRNG().nextBoolean());
        this.setHasBracletLeft(this.getRNG().nextBoolean());
        this.setHasBracletRight(this.getRNG().nextBoolean());
        this.setHasShoulderArmor(this.getRNG().nextBoolean());
        

        this.setSkinColor(skinColors[this.getRNG().nextInt(skinColors.length)]);
        this.setHairColor(hairColors[this.getRNG().nextInt(hairColors.length)]);
        this.setEyeColor(eyeColors[this.getRNG().nextInt(eyeColors.length)]);
        this.setShirtColor(this.getRNG().nextInt(0xffffff));
        this.setJeansColor(this.getRNG().nextInt(0xffffff));
        this.setMetalColor(metalColors[this.getRNG().nextInt(metalColors.length)]);
        
        this.setTypeHair(this.getRNG().nextInt(maxHairType));
        this.setTypeHead(this.getRNG().nextInt(maxHeadType));
        this.setTypeShirt(this.getRNG().nextInt(maxShirtType));
        this.setTypeJeans(this.getRNG().nextInt(maxJeansType));
        this.setTypeMetal(this.getRNG().nextInt(maxAccessoriesType));
        
        this.setCurrentItemOrArmor(0, heroWeapons[rand.nextInt(heroWeapons.length)]);
        if(rand.nextBoolean())
        	this.addRandomArmor();
        
      if (worldObj != null && !worldObj.isRemote)
      {
          this.setCombatTask();
      }
        
        return data;
    }
	
	/**
     * Makes entity wear random armor based on difficulty
     */
	@Override
    protected void addRandomArmor()
    {
        super.addRandomArmor();
        if(rand.nextBoolean())
        	this.setCurrentItemOrArmor(1, new ItemStack(Elysium.itemArmorToothHelmet));
    	if(rand.nextBoolean())
	    	this.setCurrentItemOrArmor(2, new ItemStack(Elysium.itemArmorToothChestplate));
	    if(rand.nextBoolean())
	    	this.setCurrentItemOrArmor(3, new ItemStack(Elysium.itemArmorToothLeggings));
	    if(rand.nextBoolean())
	    	this.setCurrentItemOrArmor(4, new ItemStack(Elysium.itemArmorToothBoots));
    }

	protected void setWatchableBoolean(int par1, boolean par2)
    {
        int j = this.dataWatcher.getWatchableObjectInt(16);

        if (par2)
        {
            this.dataWatcher.updateObject(16, Integer.valueOf(j | par1));
        }
        else
        {
            this.dataWatcher.updateObject(16, Integer.valueOf(j & ~par1));
        }
    }
	
	protected boolean getWatchableBoolean(int par1)
    {
        return (this.dataWatcher.getWatchableObjectInt(16) & par1) != 0;
    }
	
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
    }
	

	protected void setIsMale(boolean flag)
	{
        this.setWatchableBoolean(2, flag);
	}
	
	protected void setHasShoulderArmor(boolean flag)
	{
        this.setWatchableBoolean(4, flag);
	}

	protected void setHasBracletRight(boolean flag)
	{
        this.setWatchableBoolean(8, flag);
	}

    protected void setHasBracletLeft(boolean flag)
    {
        this.setWatchableBoolean(16, flag);
	}
    
	protected void setHasBelt(boolean flag)
	{
        this.setWatchableBoolean(32, flag);
	}

	protected void setHasBeard(boolean flag) 
	{
        this.setWatchableBoolean(64, flag);
	}

	public boolean getIsMale()
	{
        return this.getWatchableBoolean(2);
	}
	
	public boolean getHasShoulderArmor()
	{
        return this.getWatchableBoolean(4);
	}

	public boolean getHasBracletRight()
	{
        return this.getWatchableBoolean(8);
	}

	public boolean getHasBracletLeft()
    {
        return this.getWatchableBoolean(16);
	}
    
    public boolean getHasBelt()
	{
        return this.getWatchableBoolean(32);
	}

	public boolean getHasBeard() 
	{
        return this.getWatchableBoolean(64);
	}
	
	//Color
	public int getSkinColor()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}
	
	public int getHairColor()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	public int getEyeColor()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	public int getShirtColor()
	{
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	public int getJeansColor()
	{
		return this.dataWatcher.getWatchableObjectInt(21);
	}
	
	public int getMetalColor()
	{
		return this.dataWatcher.getWatchableObjectInt(22);
	}
	

	public void setSkinColor(int color)
	{
		this.dataWatcher.updateObject(17, color);
	}
	
	public void setHairColor(int color)
	{
		this.dataWatcher.updateObject(18, color);
	}

	public void setEyeColor(int color)
	{
		this.dataWatcher.updateObject(19, color);
	}

	public void setShirtColor(int color)
	{
		this.dataWatcher.updateObject(20, color);
	}

	public void setJeansColor(int color)
	{
		this.dataWatcher.updateObject(21, color);
	}
	
	public void setMetalColor(int color)
	{
		this.dataWatcher.updateObject(22, color);
	}
	
	//texture type

	/**
	 * 
	 * @param type 0-8
	 */
	public void setTypeHair(int type)
	{
		int base = getTypeInt();
		base = base & (~0xFFFF);
		this.dataWatcher.updateObject(23, base | (type & 0xFFFF));
	}

	public void setTypeHead(int type)
	{
		int base = getTypeInt();
		base &= ~(0xFFFF << 4);
		this.dataWatcher.updateObject(23, base | (type & 0xFFFF) << 4);
	}
	
	public void setTypeShirt(int type)
	{
		int base = getTypeInt();
		base = base & (~(0xFFFF << 8));
		this.dataWatcher.updateObject(23, base | (type & 0xFFFF) << 8);
	}
	
	public void setTypeJeans(int type)
	{
		int base = getTypeInt();
		base = base & (~(0xFFFF << 12));
		this.dataWatcher.updateObject(23, base | (type & 0xFFFF) << 12);
	}

	public void setTypeMetal(int type)
	{
		int base = getTypeInt();
		base = base & (~(0xFFFF << 6));
		this.dataWatcher.updateObject(23, base | (type & 0xFFFF) << 16);
	}
	
	private void setTypeInt(int type)
	{
		this.dataWatcher.updateObject(23, type);
	}
	

	public int getTypeHair()
	{
		int res = this.dataWatcher.getWatchableObjectInt(23) & 15;
//		System.out.println("getHairType() " + res + " raw: " + Integer.toBinaryString(res) + " all: " + Integer.toBinaryString(getTypeInt()));
		return res;
	}

	public int getTypeHead()
	{
		int res = (this.dataWatcher.getWatchableObjectInt(23) >> 4) & 15;
//		System.out.println("getHeadType() " + res + " raw: " + Integer.toBinaryString(res) + " all: " + Integer.toBinaryString(getTypeInt()));
		return res;
	}
	
	public int getTypeShirt()
	{
		int res = (this.dataWatcher.getWatchableObjectInt(23) >> 8) & 15;
//		System.out.println("getShirtType() " + res + " raw: " + Integer.toBinaryString(res) + " all: " + Integer.toBinaryString(getTypeInt()));
		return res;
	}
	
	public int getTypeJeans()
	{
		int res = (this.dataWatcher.getWatchableObjectInt(23) >> 12) & 15;
//		System.out.println("getJeansType() " + res + " raw: " + Integer.toBinaryString(res) + " all: " + Integer.toBinaryString(getTypeInt()));
		return res;
	}

	public int getTypeMetal()
	{
		int res = (this.dataWatcher.getWatchableObjectInt(23) >> 16) & 15;
//		System.out.println("getMetalType() " + res + " raw: " + Integer.toBinaryString(res) + " all: " + Integer.toBinaryString(getTypeInt()));
		return res;
	}
	
	private int getTypeInt()
	{
		return this.dataWatcher.getWatchableObjectInt(23);
	}
	
	/**
     * Returns true if the newer Entity AI code should be run
     */
	@Override
    public boolean isAIEnabled()
    {
        return true;
    }
	
	@Override
	public EntityAgeable createChild(EntityAgeable var1)
	{
		return null;
	}
	
	 /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
    	this.setIsMale(nbt.getBoolean("isMale"));
        this.setHasBeard(nbt.getBoolean("hasBeard"));
        this.setHasBelt(nbt.getBoolean("hasBelt"));
        this.setHasBracletLeft(nbt.getBoolean("hasBracletLeft"));
        this.setHasBracletRight(nbt.getBoolean("hasBracletRight"));
        this.setHasShoulderArmor(nbt.getBoolean("hasShoulderArmor"));
        

    	this.setSkinColor(nbt.getInteger("skinColor"));
    	this.setHairColor(nbt.getInteger("hairColor"));
    	this.setEyeColor(nbt.getInteger("eyeColor"));
    	this.setShirtColor(nbt.getInteger("shirtColor"));
    	this.setJeansColor(nbt.getInteger("jeansColor"));
    	this.setMetalColor(nbt.getInteger("metalColor"));

    	this.setTypeInt(nbt.getInteger("typeInt"));
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("isMale", this.getIsMale());
        nbt.setBoolean("hasBeard", this.getHasBeard());
        nbt.setBoolean("hasBelt", this.getHasBelt());
        nbt.setBoolean("hasBracletLeft", this.getHasBracletLeft());
        nbt.setBoolean("hasBracletRight", this.getHasBracletRight());
        nbt.setBoolean("hasShoulderArmor", this.getHasShoulderArmor());
        

        nbt.setInteger("skinColor", this.getSkinColor());
        nbt.setInteger("hairColor", this.getHairColor());
        nbt.setInteger("eyeColor", this.getEyeColor());
        nbt.setInteger("shirtColor", this.getShirtColor());
        nbt.setInteger("jeansColor", this.getJeansColor());
        nbt.setInteger("metalColor", this.getMetalColor());
        
        nbt.setInteger("typeInt", this.getTypeInt());
    }

	/**
     * Attack the specified entity using a ranged attack.
     */
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float var2)
	{
//		if(!worldObj.isRemote)
//        	System.out.println("Hero shooting " + entity.getCommandSenderName());

        EntityArrow entityarrow = new EntityArrow(this.worldObj, this, entity, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityarrow.setDamage((double)(var2 * 5.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));

        if (i > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
            entityarrow.setKnockbackStrength(j);
        }

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
	}
	
	public boolean attackEntityAsMob(Entity par1Entity)
    {
		if(!worldObj.isRemote)
		{
//        	System.out.println("Hero attacking " + par1Entity.getCommandSenderName() + " by " + this.getEquipmentInSlot(0).getDisplayName());
        	
        	if(!this.getHeldItem().isItemEqual(new ItemStack(Items.bow)))
        		par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + this.rand.nextInt(15)));
		}
		
        return super.attackEntityAsMob(par1Entity);
    }
	
	protected void collideWithEntity(Entity par1Entity)
    {
        if (par1Entity instanceof IMob && this.getRNG().nextInt(20) == 0)
        {
            this.setAttackTarget((EntityLivingBase)par1Entity);
        }

        super.collideWithEntity(par1Entity);
    }
	
	@Override
    public boolean getCanSpawnHere()
    {
        return /*rand.nextInt(10) == 0 &&*/ super.getCanSpawnHere();
    }
}
