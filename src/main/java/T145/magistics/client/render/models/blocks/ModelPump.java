package T145.magistics.client.render.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPump extends ModelBase {

	public ModelRenderer Front;
	public ModelRenderer MoveBase;
	public ModelRenderer MoveFrill;
	public ModelRenderer Center;
	public ModelRenderer Back;

	public ModelPump() {
		textureWidth = 128;
		textureHeight = 64;
		Front = new ModelRenderer(this, 0, 0);
		Front.addBox(0.0F, 0.0F, 0.0F, 12, 6, 12);
		Front.setRotationPoint(-6.0F, 18.0F, -6.0F);
		Front.setTextureSize(128, 64);
		Front.mirror = true;
		setRotation(Front, 0.0F, 0.0F, 0.0F);
		MoveBase = new ModelRenderer(this, 0, 18);
		MoveBase.addBox(0.0F, 0.0F, 0.0F, 12, 2, 12);
		MoveBase.setRotationPoint(-6.0F, 8.0F, -6.0F);
		MoveBase.setTextureSize(128, 64);
		MoveBase.mirror = true;
		setRotation(MoveBase, 0.0F, 0.0F, 0.0F);
		MoveFrill = new ModelRenderer(this, 0, 32);
		MoveFrill.addBox(0.0F, 0.0F, 0.0F, 10, 4, 10);
		MoveFrill.setRotationPoint(-5.0F, 10.0F, -5.0F);
		MoveFrill.setTextureSize(128, 64);
		MoveFrill.mirror = true;
		setRotation(MoveFrill, 0.0F, 0.0F, 0.0F);
		Center = new ModelRenderer(this, 48, 0);
		Center.addBox(0.0F, 0.0F, 0.0F, 16, 4, 16);
		Center.setRotationPoint(-8.0F, 14.0F, -8.0F);
		Center.setTextureSize(128, 64);
		Center.mirror = true;
		setRotation(Center, 0.0F, 0.0F, 0.0F);
		Back = new ModelRenderer(this, 0, 46);
		Back.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6);
		Back.setRotationPoint(-3.0F, 8.0F, -3.0F);
		Back.setTextureSize(128, 64);
		Back.mirror = true;
		setRotation(Back, 0.0F, 0.0F, 0.0F);
	}

	public void render() {
		Front.render(0.0625F);
		Center.render(0.0625F);
		Back.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}