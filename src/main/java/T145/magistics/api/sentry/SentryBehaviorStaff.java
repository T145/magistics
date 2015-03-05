package T145.magistics.api.sentry;

import net.minecraft.block.Block;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import T145.magistics.api.ElysiumStaff;
import T145.magistics.common.entities.projectiles.EntityBlockProjectile;
import T145.magistics.common.entities.projectiles.EntityEnderRandomProjectile;
import T145.magistics.common.entities.projectiles.EntityFireballProjectile;
import T145.magistics.common.entities.projectiles.EntityIceProjectile;
import cpw.mods.fml.client.FMLClientHandler;

public class SentryBehaviorStaff extends SentryDefaultProjectile
{

	/**
	 * Return the projectile entity spawned by this Sentry behavior.
	 * @param target - the target of the Sentry
	 * @param owner - owner of the Sentry - use it only when the weapon needs to consume "energy" from the player
	 * @param blockSource - the Sentry block
	 * @param item - Weapon or projectile placed into the Sentry (this is registered to the 
	 */
	@Override
	protected IProjectile getProjectileEntity(EntityLivingBase target, EntityLivingBase owner, IBlockSource blockSource, ItemStack item) {

		World world = blockSource.getWorld();
		int x = blockSource.getXInt();
		int y = blockSource.getYInt();
		int z = blockSource.getZInt();

		int meta = item.getItemDamage();
		

		EntityThrowable entityammo;
		if(meta == 0)
		{
			Block block = getBlockToThrow(target.worldObj, blockSource);
			if(block != null && block != Blocks.air)
				entityammo = new EntityBlockProjectile(world, owner, block);
			else return null;
		}
		else if(meta == 1)
			entityammo = new EntityIceProjectile(world, owner);
		else if(meta == 2)
			entityammo = new EntityEnderRandomProjectile(world, owner);
		else
			entityammo = new EntityFireballProjectile(world, owner);
	
		entityammo.setPosition(x + 0.5F, y + 1.5F, z + 0.5F);

		entityammo.posY = y + 1.5F;
		double d0 = target.posX - x - 0.5F;
		double d1 = target.boundingBox.minY + target.height / 3.0F - entityammo.posY;
		double d2 = target.posZ - z - 0.5F;
		double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D)
		{
			float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			entityammo.setLocationAndAngles(x + 0.5F + d4, entityammo.posY, z + 0.5F + d5, f2, f3);
			entityammo.yOffset = 0.0F;
			float f4 = (float)d3 * 0.2F;
			entityammo.setThrowableHeading(d0, d1 + f4, d2, 1.6F, 3F);
		}
		
		return entityammo;
	}
	
	private static Block getBlockToThrow(World world, IBlockSource blockSource)
	{
		Block block = null;
		int x = blockSource.getXInt();
		int y = blockSource.getYInt() - 1;
		int z = blockSource.getZInt();
		
		for(int i = 0; i < 100; i++)
		{
			x = blockSource.getXInt() + world.rand.nextInt(16)-8;
			z = blockSource.getZInt() + world.rand.nextInt(16)-8;
			y = world.getTopSolidOrLiquidBlock(x, z) - 1;
			
			if(Math.abs(y - blockSource.getYInt()) > 16)
				continue;
			
			block = world.getBlock(x, y, z);
			
			float damage = ElysiumStaff.getDamageForBlock(block);

			if(damage != 0F)
			{

				world.setBlockToAir(x, y, z);
				if(world.isRemote)
				{
					//particles
					FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(x, y, z, block, world.getBlockMetadata(x, y, z));
				}
				return block;
			}
		}
		
		return null;
	}

	/**
	 * Spawns the entity.
	 * @param sourceblock - position info for the block
	 * @param target - the target the Sentry is shooting at
	 * @param item - the item placed into the pillar
	 * @return - modified itemstack after shooting
	 */
	@Override
	public ItemStack spawnEntity(IBlockSource sourceblock, EntityLivingBase target, EntityLivingBase owner, ItemStack item)
	{
		IProjectile iprojectile = this.getProjectileEntity(target, owner, sourceblock, item);
		if(iprojectile != null)
			sourceblock.getWorld().spawnEntityInWorld((Entity)iprojectile);
//		item.splitStack(1);
		return item;
	}
	
	@Override
	public int reloadSpeed(ItemStack itemstack)
	{
		return itemstack.getItemDamage() == 1 ? 20 : 40;
	}
}
