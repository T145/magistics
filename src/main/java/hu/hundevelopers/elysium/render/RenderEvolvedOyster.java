package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.model.ModelEvolvedOyster;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEvolvedOyster extends RenderLiving
{
    private static final ResourceLocation TEXTURE_OYSTER = new ResourceLocation(Elysium.ID + ":textures/mobs/EvolvedOyster.png");
	private ModelEvolvedOyster model;

    public RenderEvolvedOyster()
    {
        super(new ModelEvolvedOyster(), 1F);
        this.model = (ModelEvolvedOyster)super.mainModel;
        this.setRenderPassModel(this.model);
        this.shadowSize = 0.25F;
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE_OYSTER;
	}
}