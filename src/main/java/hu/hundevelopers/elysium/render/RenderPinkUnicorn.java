package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.entity.EntityPinkUnicorn;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPinkUnicorn extends RenderLiving
{
    private static final ResourceLocation pinkUnicornTexture = new ResourceLocation(Elysium.ID + ":textures/mobs/Unicorn.png");

    public RenderPinkUnicorn(ModelBase par1ModelBase)
    {
        super(par1ModelBase, 1F);
    }

    /**
     * Renders the model in RenderLiving
     */
    protected void renderModel(EntityPinkUnicorn par1EntityPinkUnicorn, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        if (par1EntityPinkUnicorn.isInvisible())
        {
            this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityPinkUnicorn);
        }
        else
        {
            this.bindEntityTexture(par1EntityPinkUnicorn);
            this.mainModel.render(par1EntityPinkUnicorn, par2, par3, par4, par5, par6, par7);
        }
    }

    /**
     * Renders the model in RenderLiving
     */
    @Override
	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.renderModel((EntityPinkUnicorn)par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return pinkUnicornTexture;
	}

}
