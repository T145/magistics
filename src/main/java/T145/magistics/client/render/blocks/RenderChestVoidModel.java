package T145.magistics.client.render.blocks;

import T145.magistics.client.lib.CustomBakedModel;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.EnumFacing;

public class RenderChestVoidModel extends CustomBakedModel {

	public RenderChestVoidModel(IBakedModel model) {
		super(model);
	}

	@Override
	public void render(IBakedModel model) {
		RenderChestVoid.renderChest(EnumFacing.NORTH, 0, 0, 0, 0, 0, 0, -1);
	}
}