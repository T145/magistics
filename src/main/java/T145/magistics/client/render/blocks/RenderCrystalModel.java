package T145.magistics.client.render.blocks;

import T145.magistics.client.lib.CustomBakedModel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.EnumFacing;

public class RenderCrystalModel extends CustomBakedModel {

	private final int meta;

	public RenderCrystalModel(IBakedModel model, int meta) {
		super(model);
		this.meta = meta;
	}

	@Override
	public void render(IBakedModel model) {
		GlStateManager.translate(0F, 0.5F, 0F);
		RenderCrystal.renderShards(meta, 5, EnumFacing.UP, 0, 0, 0);
	}
}