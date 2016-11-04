package T145.magistics.client.render.entities;

import T145.magistics.Magistics;
import T145.magistics.items.ItemShard;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVisSlime extends RenderSlime {

	private static class RenderFactory implements IRenderFactory<EntitySlime> {

		@Override
		public Render<? super EntitySlime> createRenderFor(RenderManager manager) {
			return new RenderVisSlime(manager);
		}
	}

	public static final RenderFactory RENDER_FACTORY = new RenderFactory();

	public RenderVisSlime(RenderManager renderManager) {
		super(renderManager, new ModelSlime(16), 0.25F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySlime entity) {
		return new ResourceLocation(Magistics.MODID, "textures/models/vis_slime.png");
	}

	@Override
	protected int getColorMultiplier(EntitySlime entitylivingbase, float lightBrightness, float partialTick) {
		return ItemShard.COLORS[5];
	}
}