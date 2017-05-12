package T145.magistics.client.render.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelDuplicator extends ModelBase {

	public ModelRenderer press1;
	public ModelRenderer piston1;

	public ModelDuplicator() {
		textureWidth = 64;
		textureHeight = 32;
		press1 = new ModelRenderer(this, 0, 0);
		press1.addBox(-4.0F, 0.0F, -4.0F, 8, 4, 8);
		press1.setRotationPoint(0.0F, 12.0F, 0.0F);
		press1.setTextureSize(64, 32);
		press1.mirror = true;
		setRotation(press1, 0.0F, 0.0F, 0.0F);
		piston1 = new ModelRenderer(this, 0, 12);
		piston1.addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4);
		piston1.setRotationPoint(0.0F, 8.0F, 0.0F);
		piston1.setTextureSize(64, 32);
		piston1.mirror = true;
		setRotation(piston1, 0.0F, 0.0F, 0.0F);
	}

	public void render() {
		press1.render(0.0625F);
		piston1.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}