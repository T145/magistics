package T145.magistics.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelLargeJar extends ModelBase {
	ModelRenderer Core, Brine, Lid;

	public ModelLargeJar() {
		textureWidth = 64;
		textureHeight = 32;

		Core = new ModelRenderer(this, 0, 0);
		Core.addBox(-8F, -16.0F, -8F, 16, 16, 16);
		Core.setRotationPoint(0.0F, 0.0F, 0.0F);
		Core.setTextureSize(64, 32);
		Core.mirror = true;
		setRotation(Core, 0.0F, 0.0F, 0.0F);

		Brine = new ModelRenderer(this, 0, 0);
		Brine.addBox(-7.0F, -15.0F, -7.0F, 14, 14, 14);
		Brine.setRotationPoint(0.0F, 0.0F, 0.0F);
		Brine.setTextureSize(64, 32);
		Brine.mirror = true;
		setRotation(Brine, 0.0F, 0.0F, 0.0F);

		Lid = new ModelRenderer(this, 0, 24);
		Lid.addBox(-3.0F, -4.0F, -3.0F, 6, 2, 6);
		Lid.setRotationPoint(0.0F, -14.0F, 0.0F);
		Lid.setTextureSize(64, 32);
		Lid.mirror = true;
	}

	public void renderBrine() {
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		Brine.render(0.0625F);
		GL11.glDisable(3042);
	}

	public void renderAll() {
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		Core.render(0.0625F);
		GL11.glDisable(3042);
	}

	public void renderLid() {
		Lid.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}