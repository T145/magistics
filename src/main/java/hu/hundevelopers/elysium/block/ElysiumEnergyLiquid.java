package hu.hundevelopers.elysium.block;

import java.util.Random;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.render.EntityDropParticleFX;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class ElysiumEnergyLiquid extends BlockFluidClassic
{

	public ElysiumEnergyLiquid(Fluid fluid, Material material)
	{
		super(fluid, material);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		this.blockIcon = register.registerIcon(Elysium.ID + ":energy_liquid");
	}
     
     @Override
     public boolean canDisplace(IBlockAccess world, int x, int y, int z)
     {
         if (world.getBlock(x,  y,  z).getMaterial().isLiquid())
        	 return false;
         
         return super.canDisplace(world, x, y, z);
     }
     /**
      * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
      */
     @Override
     public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
     {
    	 entity.setFire(1);
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

    		 EntityFX fx = new EntityDropParticleFX(world, px, py, pz, 248, 255, 123);//Yellow
    		 FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
    	 }
    	 if (rand.nextInt(2000) == 0)
    	 {
    		 System.out.println("Sound");
    		 world.playSound((double)x, (double)y, (double)z, Elysium.ID + ":liquidCrystal", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
    	 }
     }
}
