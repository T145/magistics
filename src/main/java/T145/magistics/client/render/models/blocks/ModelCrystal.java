package T145.magistics.client.render.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCrystal extends ModelBase {

	ModelRenderer Crystal;

	public ModelCrystal() {
		textureWidth = 64;
		textureHeight = 32;

		Crystal = new ModelRenderer(this, 0, 0);
		Crystal.addBox(-16.0F, -16.0F, 0.0F, 16, 16, 16);
		Crystal.setRotationPoint(0.0F, 32.0F, 0.0F);
		Crystal.setTextureSize(64, 32);
		Crystal.mirror = true;
		setRotation(Crystal, 0.7071F, 0.0F, 0.7071F);
	}

	public void render() {
		Crystal.render(0.0625F);
	}

	public void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}