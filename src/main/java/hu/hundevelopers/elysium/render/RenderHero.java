package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.entity.EntityHero;
import hu.hundevelopers.elysium.model.ModelHero;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderHero extends RenderBiped
{
    private static final ResourceLocation TEXTURE_MALE = new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_male.png");
	private ModelHero model;

    public RenderHero()
    {
    	super(new ModelHero(), 1F);
        this.model = (ModelHero)super.mainModel;
        this.setRenderPassModel(this.model);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return TEXTURE_MALE;
	}
	
	protected void renderEquippedItems(EntityHero entity, float par2)
    {
//		ItemStack itemstack = entity.getHeldItem();
      
//		if (itemstack != null && itemstack.getItem() != null)
//		{
//			if (itemstack.getItem() == Elysium.itemStaff)
//			{
////				GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
//
////              float f1 = 0.625F;
//              GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
////              GL11.glTranslatef(0.0F, -0.5F, -1.3F);
//////              GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
//////              GL11.glScalef(f1, -f1, f1);
//              GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
//              GL11.glRotatef(-0.0F, 0.0F, 1.0F, 0.0F);
////              
////              
//			}
//		}
		super.renderEquippedItems(entity, par2);
    }
	
	@Override
	protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.renderEquippedItems((EntityHero)par1EntityLivingBase, par2);
    }
}
