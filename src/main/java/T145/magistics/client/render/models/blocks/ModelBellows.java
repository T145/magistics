package T145.magistics.client.render.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBellows extends ModelBase {

	public ModelRenderer BottomPlank;
	public ModelRenderer MiddlePlank;
	public ModelRenderer TopPlank;
	public ModelRenderer Bag;
	public ModelRenderer Nozzle;

	public ModelBellows() {
		textureWidth = 128;
		textureHeight = 64;
		BottomPlank = new ModelRenderer(this, 0, 0);
		BottomPlank.addBox(-6.0F, 0.0F, -6.0F, 12, 2, 12);
		BottomPlank.setRotationPoint(0.0F, 22.0F, 0.0F);
		BottomPlank.setTextureSize(128, 64);
		BottomPlank.mirror = true;
		setRotation(BottomPlank, 0.0F, 0.0F, 0.0F);
		MiddlePlank = new ModelRenderer(this, 0, 0);
		MiddlePlank.addBox(-6.0F, -1.0F, -6.0F, 12, 2, 12);
		MiddlePlank.setRotationPoint(0.0F, 16.0F, 0.0F);
		MiddlePlank.setTextureSize(128, 64);
		MiddlePlank.mirror = true;
		setRotation(MiddlePlank, 0.0F, 0.0F, 0.0F);
		TopPlank = new ModelRenderer(this, 0, 0);
		TopPlank.addBox(-6.0F, 0.0F, -6.0F, 12, 2, 12);
		TopPlank.setRotationPoint(0.0F, 8.0F, 0.0F);
		TopPlank.setTextureSize(128, 64);
		TopPlank.mirror = true;
		setRotation(TopPlank, 0.0F, 0.0F, 0.0F);
		Bag = new ModelRenderer(this, 48, 0);
		Bag.addBox(-10.0F, -12.03333F, -10.0F, 20, 24, 20);
		Bag.setRotationPoint(0.0F, 16.0F, 0.0F);
		Bag.setTextureSize(64, 32);
		Bag.mirror = true;
		setRotation(Bag, 0.0F, 0.0F, 0.0F);
		Nozzle = new ModelRenderer(this, 0, 36);
		Nozzle.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 2);
		Nozzle.setRotationPoint(0.0F, 16.0F, 6.0F);
		Nozzle.setTextureSize(128, 64);
		Nozzle.mirror = true;
		setRotation(Nozzle, 0.0F, 0.0F, 0.0F);
	}

	public void render() {
		MiddlePlank.render(0.0625F);
		Nozzle.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}