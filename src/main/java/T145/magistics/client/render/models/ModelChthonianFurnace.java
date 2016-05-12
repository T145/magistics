package T145.magistics.client.render.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * chthonian furnace - wiiv
 * Created using Tabula 4.1.1
 */
public class ModelChthonianFurnace extends ModelBase {
	public ModelRenderer base, side1, top, side2, back, bar1, bar2, bar3;

	public ModelChthonianFurnace() {
		textureWidth = 112;
		textureHeight = 48;
		back = new ModelRenderer(this, 64, 22);
		back.setRotationPoint(0F, 19F, 0F);
		back.addBox(-5F, -4F, 2F, 10, 6, 6, 0F);
		bar2 = new ModelRenderer(this, 69, 35);
		bar2.setRotationPoint(0F, 21F, 0F);
		bar2.addBox(-1F, -8F, -6F, 2, 6, 1, 0F);
		base = new ModelRenderer(this, 0, 23);
		base.setRotationPoint(0F, 21F, 0F);
		base.addBox(-8F, 0F, -8F, 16, 3, 16, 0F);
		bar3 = new ModelRenderer(this, 73, 34);
		bar3.setRotationPoint(0F, 21F, 0F);
		bar3.addBox(3F, -8F, -6F, 2, 6, 1, 0F);
		side1 = new ModelRenderer(this, 64, -16);
		side1.setRotationPoint(0F, 19F, 0F);
		side1.addBox(-8F, -4F, -8F, 3, 6, 16, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.setRotationPoint(0F, 19F, 0F);
		top.addBox(-8F, -11F, -8F, 16, 7, 16, 0F);
		bar1 = new ModelRenderer(this, 64, 35);
		bar1.setRotationPoint(0F, 21F, 0F);
		bar1.addBox(-5F, -8F, -6F, 2, 6, 1, 0F);
		side2 = new ModelRenderer(this, 64, -16);
		side2.mirror = true;
		side2.setRotationPoint(0F, 19F, 0F);
		side2.addBox(5F, -4F, -8F, 3, 6, 16, 0F);
	}

	public void renderAll() {
		float f5 = 0.0625F;
		back.render(f5);
		bar2.render(f5);
		base.render(f5);
		bar3.render(f5);
		side1.render(f5);
		top.render(f5);
		bar1.render(f5);
		side2.render(f5);
	}
}