package T145.magistics.client.render.blocks;

import T145.magistics.client.lib.CustomBakedModel;
import net.minecraft.client.renderer.block.model.IBakedModel;

public class RenderVoidBorderModel extends CustomBakedModel {

	public RenderVoidBorderModel(IBakedModel model) {
		super(model);
	}

	@Override
	public void render(IBakedModel model) {
		RenderVoidBorder.renderBlock(0, 0, 0);
	}
}