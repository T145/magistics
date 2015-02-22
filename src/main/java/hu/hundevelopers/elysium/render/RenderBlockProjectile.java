package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.entity.projectile.EntityBlockProjectile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class RenderBlockProjectile extends Render
{
	 private RenderBlocks field_76988_d = new RenderBlocks();

	  public RenderBlockProjectile()
	  {
	    this.shadowSize = 0.5F;
	  }

	  private final RenderBlocks blockRenderer = new RenderBlocks();

	  @Override
	  public void doRender(Entity entity, double p_147918_2_, double p_147918_4_, double p_147918_6_, float p_147918_8_, float p_147918_9_)
	    {
	        World world = entity.worldObj;
	        Block block = ((EntityBlockProjectile)entity).getBlock();
	        int i = MathHelper.floor_double(entity.posX);
	        int j = MathHelper.floor_double(entity.posY);
	        int k = MathHelper.floor_double(entity.posZ);

	        if (block != null)
	        {
	            GL11.glPushMatrix();
	            GL11.glTranslatef((float)p_147918_2_, (float)p_147918_4_, (float)p_147918_6_);
	            this.bindEntityTexture(entity);
	            GL11.glDisable(GL11.GL_LIGHTING);
	            Tessellator tessellator;

                this.blockRenderer.setRenderBoundsFromBlock(block);
                this.blockRenderer.renderBlockSandFalling(block, world, i, j, k, 0);

	            GL11.glEnable(GL11.GL_LIGHTING);
	            GL11.glPopMatrix();
	        }
	    }

	  protected ResourceLocation getEntityTexture(Entity par1Entity)
	  {
	    return TextureMap.locationBlocksTexture;
	  }
}
