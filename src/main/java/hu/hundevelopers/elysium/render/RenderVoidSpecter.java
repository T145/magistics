package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.entity.EntityVoidSpecter;
import hu.hundevelopers.elysium.model.ModelVoidSpecter;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderVoidSpecter extends RenderLiving
{
	private ModelVoidSpecter model;

    public RenderVoidSpecter()
    {
        super(new ModelVoidSpecter(), 1F);
        this.model = (ModelVoidSpecter)super.mainModel;
        this.setRenderPassModel(this.model);
    }
    
    private static final ResourceLocation enderDragonCrystalBeamTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityVoidSpecter entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender((EntityLiving)entity, par2, par4, par6, par8, par9);

//        if (entity.targetedEntity != null && entity.attackTime > 10 && entity.attackTime <= 20)
//        {
//            float f4 = (float)(entity.spawnX - entity.posX - (entity.prevPosX - entity.posX) * (double)(1.0F - par9));
//            float f5 = (float)(entity.spawnY - 1.0D - entity.posY - (entity.prevPosY - entity.posY) * (double)(1.0F - par9));
//            float f6 = (float)(entity.spawnZ - entity.posZ - (entity.prevPosZ - entity.posZ) * (double)(1.0F - par9));
//            float f7 = MathHelper.sqrt_float(f4 * f4 + f6 * f6);
//            float f8 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6);
//            GL11.glPushMatrix();
//            GL11.glTranslatef((float)par2, (float)par4 + 2.0F, (float)par6);
//            GL11.glRotatef((float)(-Math.atan2((double)f6, (double)f4)) * 180.0F / (float)Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
//            GL11.glRotatef((float)(-Math.atan2((double)f7, (double)f5)) * 180.0F / (float)Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
//            Tessellator tessellator = Tessellator.instance;
//            RenderHelper.disableStandardItemLighting();
//            GL11.glDisable(GL11.GL_CULL_FACE);
//            this.bindTexture(enderDragonCrystalBeamTextures);
//            GL11.glShadeModel(GL11.GL_SMOOTH);
//            float f9 = 0.0F - ((float)entity.ticksExisted + par9) * 0.01F;
//            float f10 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6) / 32.0F - ((float)entity.ticksExisted + par9) * 0.01F;
//            tessellator.startDrawing(5);
//            byte b0 = 8;
//
//            for (int i = 0; i <= b0; ++i)
//            {
//                float f11 = MathHelper.sin((float)(i % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F;
//                float f12 = MathHelper.cos((float)(i % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F;
//                float f13 = (float)(i % b0) * 1.0F / (float)b0;
//                tessellator.setColorOpaque_I(0);
//                tessellator.addVertexWithUV((double)(f11 * 0.2F), (double)(f12 * 0.2F), 0.0D, (double)f13, (double)f10);
//                tessellator.setColorOpaque_I(16777215);
//                tessellator.addVertexWithUV((double)f11, (double)f12, (double)f8, (double)f13, (double)f9);
//            }
//
//            tessellator.draw();
//            GL11.glEnable(GL11.GL_CULL_FACE);
//            GL11.glShadeModel(GL11.GL_FLAT);
//            RenderHelper.enableStandardItemLighting();
//            GL11.glPopMatrix();
//        }
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityLivingBase par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRender((EntityVoidSpecter)par1Entity, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRender((EntityVoidSpecter)par1Entity, par2, par4, par6, par8, par9);
    }
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Elysium.ID + ":textures/mobs/VoidSpecter.png");
	}

}
