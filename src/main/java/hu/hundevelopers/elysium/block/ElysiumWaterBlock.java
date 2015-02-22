package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.render.EntityDropParticleFX;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumWaterBlock extends BlockFluidClassic
{
	 @SideOnly(Side.CLIENT)
     protected IIcon stillIcon;
     @SideOnly(Side.CLIENT)
     protected IIcon flowingIcon;
     
	public ElysiumWaterBlock(Fluid fluid, Material material)
	{
		super(fluid, material);
		this.setCreativeTab(Elysium.tabElysium);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if(!world.isRemote && entity instanceof EntityPlayer)
		{
			((EntityPlayer) entity).curePotionEffects(new ItemStack(Items.milk_bucket));
		}
	}
		
	
     @Override
     public IIcon getIcon(int side, int meta)
     {
         return (side == 0 || side == 1) ? stillIcon : flowingIcon;
     }
     
     @SideOnly(Side.CLIENT)
     @Override
     public void registerBlockIcons(IIconRegister register)
     {
         stillIcon = register.registerIcon(Elysium.ID + ":elysian_water_still");
         flowingIcon = register.registerIcon(Elysium.ID + ":elysian_water_flow");
     }
     
     @Override
     public boolean canDisplace(IBlockAccess world, int x, int y, int z)
     {
         if (world.getBlock(x,  y,  z).getMaterial().isLiquid())
        	 return false;
         
         return super.canDisplace(world, x, y, z);
     }
     
     @Override
     public boolean displaceIfPossible(World world, int x, int y, int z)
     {
         if (world.getBlock(x,  y,  z).getMaterial().isLiquid())
        	 return false;
         return super.displaceIfPossible(world, x, y, z);
     }

     /**
      * A randomly called display update to be able to add particles or other items for display
      */
     @SideOnly(Side.CLIENT)
     @Override
     public void randomDisplayTick(World world, int x, int y, int z, Random rand)
     {
    	 super.randomDisplayTick(world, x, y, z, rand);
    	 
    	 if (rand.nextInt(10) == 0 	&& World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)
				&& !world.getBlock(x, y - 2, z).getMaterial().blocksMovement()) {

    		 double px = x + rand.nextFloat();
    		 double py = y - 1.05D;
    		 double pz = z + rand.nextFloat();

    		 EntityFX fx = new EntityDropParticleFX(world, px, py, pz, 70, 204, 234);//light blue
    		 FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
    	 }
     }
}
