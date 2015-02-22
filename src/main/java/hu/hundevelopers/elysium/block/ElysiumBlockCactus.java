package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class ElysiumBlockCactus extends ElysiumBlockBush
{
	public ElysiumBlockCactus()
	{
		float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
		this.setCreativeTab(Elysium.tabElysium);
	}
	@Override
    public Block setBlockTextureName(String texture)
    {
        this.textureName = Elysium.ID + ":" + texture;
        return this;
    }
	
	/**
     * is the block grass, dirt or farmland
     */
	@Override
    public boolean canPlaceBlockOn(Block block)
    {
        return block == Elysium.blockSand;
    }
    
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
    	return EnumPlantType.Desert;
    }
    
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
    	if(world.isRemote) return;
    	
    	if(entity instanceof EntityLiving)
    	{
    		((EntityLiving) entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 200, 4));
    	} else if(entity instanceof EntityPlayer)
    	{
    		((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 200, 2));
    	}
    }

}
