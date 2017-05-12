package T145.magistics.client.render.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelGear extends ModelBase {

	public ModelRenderer Shape1;
	public ModelRenderer Shape2;
	public ModelRenderer Shape3;

	public ModelGear() {
		textureWidth = 64;
		textureHeight = 32;
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-2.0F, -6.0F, -6.0F, 4, 12, 12);
		Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0F, 0.0F, 0.0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(-2.0F, -6.0F, -6.0F, 4, 12, 12);
		Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.5235988F, 0.0F, 0.0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(-2.0F, -6.0F, -6.0F, 4, 12, 12);
		Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, -0.5235988F, 0.0F, 0.0F);
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