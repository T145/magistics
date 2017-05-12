package T145.magistics.client.render.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBrain extends ModelBase {

	public ModelRenderer Shape1;
	public ModelRenderer Shape2;
	public ModelRenderer Shape3;

	public ModelBrain() {
		textureWidth = 128;
		textureHeight = 64;
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0.0F, 0.0F, 0.0F, 12, 10, 16);
		Shape1.setRotationPoint(-6.0F, 8.0F, -8.0F);
		Shape1.setTextureSize(128, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0F, 0.0F, 0.0F);
		Shape2 = new ModelRenderer(this, 64, 0);
		Shape2.addBox(0.0F, 0.0F, 0.0F, 8, 3, 7);
		Shape2.setRotationPoint(-4.0F, 18.0F, 0.0F);
		Shape2.setTextureSize(128, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0F, 0.0F, 0.0F);
		Shape3 = new ModelRenderer(this, 0, 32);
		Shape3.addBox(0.0F, 0.0F, 0.0F, 2, 6, 2);
		Shape3.setRotationPoint(-1.0F, 18.0F, -2.0F);
		Shape3.setTextureSize(128, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0.4089647F, 0.0F, 0.0F);
	}

	public void render() {
		Shape1.render(0.0625F);
		Shape2.render(0.0625F);
		Shape3.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}