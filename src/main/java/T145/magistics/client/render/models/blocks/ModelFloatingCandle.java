package T145.magistics.client.render.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelFloatingCandle extends ModelBase {

	public ModelRenderer candleBase;
	public ModelRenderer candleWick;

	public ModelFloatingCandle() {
		candleBase = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 96);
		candleBase.addBox(6.0F, 4.0F, 6.0F, 4, 8, 4);
		candleWick = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
		candleWick.addBox(7.5F, 12.0F, 7.5F, 1, 2, 1);
	}

	public void renderAll(int x) {
		candleBase.setTextureOffset(0, (x % 8) * 12);
		candleBase.render(0.0625F);
	}

	public void renderWick() {
		candleWick.render(0.0625F);
	}
}