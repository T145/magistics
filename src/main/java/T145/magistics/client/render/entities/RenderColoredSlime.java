package T145.magistics.client.render.entities;

import javax.annotation.Nonnull;

import T145.magistics.Magistics;
import T145.magistics.client.lib.utils.UtilsFX;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderColoredSlime extends RenderSlime {

	public static class RenderColoredSlimeFactory implements IRenderFactory<EntitySlime> {

		private final int color;

		public RenderColoredSlimeFactory(int color) {
			this.color = color;
		}

		@Override
		public Render<? super EntitySlime> createRenderFor(RenderManager manager) {
			return new RenderColoredSlime(manager, color);
		}
	}

	@SideOnly(Side.CLIENT)
	protected class LayerSlimeGelColored implements LayerRenderer<EntitySlime> {
		private final RenderSlime slimeRenderer;
		private final ModelBase slimeModel = new ModelSlime(0);

		public LayerSlimeGelColored(RenderSlime slimeRenderer) {
			this.slimeRenderer = slimeRenderer;
		}

		@Override
		public void doRenderLayer(EntitySlime entitylivingbase, float limbSwing, float limbSwingAmount, float partialTicks, float ageTicks, float netHeadYaw, float headPitch, float scale) {
			if (!entitylivingbase.isInvisible()) {
				UtilsFX.setColorRGBA(getColorMultiplier());
				GlStateManager.enableNormalize();
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
				slimeModel.setModelAttributes(slimeRenderer.getMainModel());
				slimeModel.render(entitylivingbase, limbSwing, limbSwingAmount, ageTicks, netHeadYaw, headPitch, scale);
				GlStateManager.disableBlend();
				GlStateManager.disableNormalize();
			}
		}

		@Override
		public boolean shouldCombineTextures() {
			return true;
		}
	}

	private final int color;

	public RenderColoredSlime(RenderManager renderManager, int color) {
		super(renderManager, new ModelSlime(16), 0.25F);
		this.color = color;
		layerRenderers.clear();
		addLayer(new LayerSlimeGelColored(this));
	}

	@Nonnull
	@Override
	protected ResourceLocation getEntityTexture(EntitySlime entity) {
		return new ResourceLocation(Magistics.MODID, "textures/models/slime.png");
	}

	@Nonnull
	protected int getColorMultiplier() {
		return color;
	}

	@Override
	protected int getColorMultiplier(EntitySlime entitylivingbase, float lightBrightness, float partialTickTime) {
		return getColorMultiplier();
	}

	@Override
	public void doRender(EntitySlime entity, double x, double y, double z, float entityYaw, float partialTicks) {
		UtilsFX.setColorRGBA(getColorMultiplier());
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
}