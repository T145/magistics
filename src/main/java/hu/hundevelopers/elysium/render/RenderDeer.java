package hu.hundevelopers.elysium.render;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.model.ModelDeer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDeer extends RenderLiving
{
	private ModelDeer model;

    public RenderDeer()
    {
        super(new ModelDeer(), 1F);
        this.model = (ModelDeer)super.mainModel;
        this.setRenderPassModel(this.model);
    }
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Elysium.ID + ":textures/mobs/Deer.png");
	}

}
