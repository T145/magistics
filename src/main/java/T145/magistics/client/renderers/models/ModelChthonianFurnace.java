package T145.magistics.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * chthonian furnace - wiiv Created using Tabula 4.1.1
 */
public class ModelChthonianFurnace extends ModelBase {
	public ModelRenderer base, side1, top, side2, back, bar1, bar2, bar3;

	public ModelChthonianFurnace() {
		textureWidth = 112;
		textureHeight = 48;
		back = new ModelRenderer(this, 64, 22);
		back.setRotationPoint(0.0F, 19.0F, 0.0F);
		back.addBox(-5.0F, -4.0F, 2.0F, 10, 6, 6, 0.0F);
		bar2 = new ModelRenderer(this, 69, 35);
		bar2.setRotationPoint(0.0F, 21.0F, 0.0F);
		bar2.addBox(-1.0F, -8.0F, -6.0F, 2, 6, 1, 0.0F);
		base = new ModelRenderer(this, 0, 23);
		base.setRotationPoint(0.0F, 21.0F, 0.0F);
		base.addBox(-8.0F, 0.0F, -8.0F, 16, 3, 16, 0.0F);
		bar3 = new ModelRenderer(this, 73, 34);
		bar3.setRotationPoint(0.0F, 21.0F, 0.0F);
		bar3.addBox(3.0F, -8.0F, -6.0F, 2, 6, 1, 0.0F);
		side1 = new ModelRenderer(this, 64, -16);
		side1.setRotationPoint(0.0F, 19.0F, 0.0F);
		side1.addBox(-8.0F, -4.0F, -8.0F, 3, 6, 16, 0.0F);
		top = new ModelRenderer(this, 0, 0);
		top.setRotationPoint(0.0F, 19.0F, 0.0F);
		top.addBox(-8.0F, -11.0F, -8.0F, 16, 7, 16, 0.0F);
		bar1 = new ModelRenderer(this, 64, 35);
		bar1.setRotationPoint(0.0F, 21.0F, 0.0F);
		bar1.addBox(-5.0F, -8.0F, -6.0F, 2, 6, 1, 0.0F);
		side2 = new ModelRenderer(this, 64, -16);
		side2.mirror = true;
		side2.setRotationPoint(0.0F, 19.0F, 0.0F);
		side2.addBox(5.0F, -4.0F, -8.0F, 3, 6, 16, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
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