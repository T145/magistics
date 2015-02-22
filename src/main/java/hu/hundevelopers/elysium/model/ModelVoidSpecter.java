package hu.hundevelopers.elysium.model;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelVoidSpecter extends ModelBase
{
	// fields
	ModelRenderer leg2R;
	ModelRenderer leg1R;
	ModelRenderer leg1L;
	ModelRenderer leg2L;
	ModelRenderer eye;
	ModelRenderer eyelidBottom;
	ModelRenderer eyelidTop;
	ModelRenderer body7;
	ModelRenderer body3;
	ModelRenderer body6;
	ModelRenderer body5;
	ModelRenderer body8;
	ModelRenderer body4;
	ModelRenderer body1;
	ModelRenderer body2;
	ModelRenderer handL1;
	ModelRenderer handL2;
	ModelRenderer handL3;
	ModelRenderer handL4;
	ModelRenderer handL5;
	ModelRenderer wingL;
	ModelRenderer handR1;
	ModelRenderer handR2;
	ModelRenderer handR3;
	ModelRenderer handR4;
	ModelRenderer handR5;
	ModelRenderer wingR;

	public ModelVoidSpecter()
	{
		textureWidth = 64;
		textureHeight = 128;

		leg2R = new ModelRenderer(this, 0, 92);
	      leg2R.mirror = true;
	      leg2R.addBox(-2.5F, -0.5F, 5F, 2, 2, 7);
	      leg2R.setRotationPoint(-3F, 0F, 16F);
	      leg2R.setTextureSize(64, 128);
	      leg2R.mirror = true;
	      setRotation(leg2R, -0.5235988F, -0.3141593F, 0F);
	      leg2R.mirror = false;
	      leg1R = new ModelRenderer(this, 0, 82);
	      leg1R.mirror = true;
	      leg1R.addBox(-2F, -1F, -1F, 3, 3, 7);
	      leg1R.setRotationPoint(-3F, 0F, 16F);
	      leg1R.setTextureSize(64, 128);
	      leg1R.mirror = true;
	      setRotation(leg1R, -0.5235988F, -0.6108652F, 0F);
	      leg1R.mirror = false;
	      leg1L = new ModelRenderer(this, 0, 82);
	      leg1L.addBox(-1F, -1F, -1F, 3, 3, 7);
	      leg1L.setRotationPoint(3F, 0F, 16F);
	      leg1L.setTextureSize(64, 128);
	      leg1L.mirror = true;
	      setRotation(leg1L, -0.5235988F, 0.6108652F, 0F);
	      leg2L = new ModelRenderer(this, 0, 92);
	      leg2L.addBox(0.5F, -0.5F, 5F, 2, 2, 7);
	      leg2L.setRotationPoint(3F, 0F, 16F);
	      leg2L.setTextureSize(64, 128);
	      leg2L.mirror = true;
	      setRotation(leg2L, -0.5235988F, 0.3141593F, 0F);
	      eye = new ModelRenderer(this, 0, 14);
	      eye.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5);
	      eye.setRotationPoint(0F, 0F, -13F);
	      eye.setTextureSize(64, 128);
	      eye.mirror = true;
	      setRotation(eye, 0F, 0F, 0F);
	      eyelidBottom = new ModelRenderer(this, 0, 24);
	      eyelidBottom.addBox(-3F, -1.5F, -2F, 6, 6, 6);
	      eyelidBottom.setRotationPoint(0F, 0F, -13F);
	      eyelidBottom.setTextureSize(64, 128);
	      eyelidBottom.mirror = true;
	      setRotation(eyelidBottom, -0.3490659F, 0F, 0F);
	      eyelidTop = new ModelRenderer(this, 0, 0);
	      eyelidTop.addBox(-3.5F, -3.5F, -3F, 7, 7, 7);
	      eyelidTop.setRotationPoint(0F, 0F, -13F);
	      eyelidTop.setTextureSize(64, 128);
	      eyelidTop.mirror = true;
	      setRotation(eyelidTop, 0.4886922F, 0F, 0F);
	      body7 = new ModelRenderer(this, 30, 75);
	      body7.addBox(-1F, -1F, 0F, 2, 2, 8);
	      body7.setRotationPoint(0F, 0F, 32F);
	      body7.setTextureSize(64, 128);
	      body7.mirror = true;
	      setRotation(body7, 0F, 0F, 0F);
	      body3 = new ModelRenderer(this, 30, 23);
	      body3.addBox(-3F, -3F, -5F, 6, 6, 10);
	      body3.setRotationPoint(0F, 0F, 7F);
	      body3.setTextureSize(64, 128);
	      body3.mirror = true;
	      setRotation(body3, 0F, 0F, 0F);
	      body6 = new ModelRenderer(this, 30, 64);
	      body6.addBox(-1.5F, -1.5F, 0F, 3, 3, 8);
	      body6.setRotationPoint(0F, 0F, 25F);
	      body6.setTextureSize(64, 128);
	      body6.mirror = true;
	      setRotation(body6, 0F, 0F, 0F);
	      body5 = new ModelRenderer(this, 30, 52);
	      body5.addBox(-2F, -2F, 0F, 4, 4, 8);
	      body5.setRotationPoint(0F, 0F, 18F);
	      body5.setTextureSize(64, 128);
	      body5.mirror = true;
	      setRotation(body5, 0F, 0F, 0F);
	      body8 = new ModelRenderer(this, 30, 85);
	      body8.addBox(-0.5F, -0.5F, 0F, 1, 1, 8);
	      body8.setRotationPoint(0F, 0F, 39F);
	      body8.setTextureSize(64, 128);
	      body8.mirror = true;
	      setRotation(body8, 0F, 0F, 0F);
	      body4 = new ModelRenderer(this, 30, 39);
	      body4.addBox(-2.5F, -2.5F, 0F, 5, 5, 8);
	      body4.setRotationPoint(0F, 0F, 11F);
	      body4.setTextureSize(64, 128);
	      body4.mirror = true;
	      setRotation(body4, 0F, 0F, 0F);
	      body1 = new ModelRenderer(this, 30, 0);
	      body1.addBox(-2F, -2F, -6F, 4, 4, 6);
	      body1.setRotationPoint(0F, 0F, -4F);
	      body1.setTextureSize(64, 128);
	      body1.mirror = true;
	      setRotation(body1, 0F, 0F, 0F);
	      body2 = new ModelRenderer(this, 30, 10);
	      body2.addBox(-2.5F, -2.5F, -8F, 5, 5, 8);
	      body2.setRotationPoint(0F, 0F, 3F);
	      body2.setTextureSize(64, 128);
	      body2.mirror = true;
	      setRotation(body2, 0F, 0F, 0F);
	      handL1 = new ModelRenderer(this, 12, 46);
	      handL1.addBox(-2F, -6F, -8F, 1, 1, 4);
	      handL1.setRotationPoint(11F, 5F, -1F);
	      handL1.setTextureSize(64, 128);
	      handL1.mirror = true;
	      setRotation(handL1, 1.047198F, -0.2617994F, 0F);
	      handL2 = new ModelRenderer(this, 0, 46);
	      handL2.addBox(-2.5F, -2F, -7F, 2, 1, 4);
	      handL2.setRotationPoint(11F, 5F, -1F);
	      handL2.setTextureSize(64, 128);
	      handL2.mirror = true;
	      setRotation(handL2, 0.3490659F, -0.2617994F, 0F);
	      handL3 = new ModelRenderer(this, 0, 46);
	      handL3.addBox(0.5F, -2F, -7F, 2, 1, 4);
	      handL3.setRotationPoint(11F, 5F, -1F);
	      handL3.setTextureSize(64, 128);
	      handL3.mirror = true;
	      setRotation(handL3, 0.3490659F, -0.2617994F, 0F);
	      handL4 = new ModelRenderer(this, 12, 46);
	      handL4.addBox(1F, -6F, -8F, 1, 1, 4);
	      handL4.setRotationPoint(11F, 5F, -1F);
	      handL4.setTextureSize(64, 128);
	      handL4.mirror = true;
	      setRotation(handL4, 1.047198F, -0.2617994F, 0F);
	      handL5 = new ModelRenderer(this, 0, 40);
	      handL5.addBox(-2F, -1F, -4F, 4, 2, 4);
	      handL5.setRotationPoint(11F, 5F, -1F);
	      handL5.setTextureSize(64, 128);
	      handL5.mirror = true;
	      setRotation(handL5, 0F, -0.2617994F, 0F);
	      handR1 = new ModelRenderer(this, 12, 46);
	      handR1.mirror = true;
	      handR1.addBox(1F, -6F, -8F, 1, 1, 4);
	      handR1.setRotationPoint(-11F, 5F, -1F);
	      handR1.setTextureSize(64, 128);
	      handR1.mirror = true;
	      setRotation(handR1, 1.047198F, 0.2617994F, 0F);
	      handR1.mirror = false;
	      handR2 = new ModelRenderer(this, 0, 46);
	      handR2.mirror = true;
	      handR2.addBox(0.5F, -2F, -7F, 2, 1, 4);
	      handR2.setRotationPoint(-11F, 5F, -1F);
	      handR2.setTextureSize(64, 128);
	      handR2.mirror = true;
	      setRotation(handR2, 0.3490659F, 0.2617994F, 0F);
	      handR2.mirror = false;
	      handR3 = new ModelRenderer(this, 0, 46);
	      handR3.mirror = true;
	      handR3.addBox(-2.5F, -2F, -7F, 2, 1, 4);
	      handR3.setRotationPoint(-11F, 5F, -1F);
	      handR3.setTextureSize(64, 128);
	      handR3.mirror = true;
	      setRotation(handR3, 0.3490659F, 0.2617994F, 0F);
	      handR3.mirror = false;
	      handR4 = new ModelRenderer(this, 12, 46);
	      handR4.mirror = true;
	      handR4.addBox(-2F, -6F, -8F, 1, 1, 4);
	      handR4.setRotationPoint(-11F, 5F, -1F);
	      handR4.setTextureSize(64, 128);
	      handR4.mirror = true;
	      setRotation(handR4, 1.047198F, 0.2617994F, 0F);
	      handR4.mirror = false;
	      handR5 = new ModelRenderer(this, 0, 40);
	      handR5.mirror = true;
	      handR5.addBox(-2F, -1F, -4F, 4, 2, 4);
	      handR5.setRotationPoint(-11F, 5F, -1F);
	      handR5.setTextureSize(64, 128);
	      handR5.mirror = true;
	      setRotation(handR5, 0F, 0.2617994F, 0F);
	      handR5.mirror = false;
	      
	      wingR = new ModelRenderer(this, -1, 97);
	      wingR.mirror = true;
	      wingR.addBox(-3F, 0F, 1F, 17, 0, 31);
	      wingR.setRotationPoint(-11F, 5F, -1F);
	      wingR.setTextureSize(64, 128);
	      wingR.mirror = true;
	      setRotation(wingR, 0F, -1.22173F, 0F);
	      wingR.mirror = false;
	      
	      wingL = new ModelRenderer(this, -1, 97);
	      wingL.addBox(-14F, 0F, 1F, 17, 0, 31);
	      wingL.setRotationPoint(11F, 5F, -1F);
	      wingL.setTextureSize(64, 128);
	      wingL.mirror = true;
	      setRotation(wingL, 0F, 1.22173F, 0F);
	      
     }

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		glPushMatrix();
		
			GL11.glTranslatef(0, 1, 0);
			
			leg2R.render(f5);
			leg1R.render(f5);
			leg1L.render(f5);
			leg2L.render(f5);
			eye.render(f5);
			eyelidBottom.render(f5);
			eyelidTop.render(f5);
			body7.render(f5);
			body3.render(f5);
			body6.render(f5);
			body5.render(f5);
			body8.render(f5);
			body4.render(f5);
			body1.render(f5);
			body2.render(f5);
			handL1.render(f5);
			handL2.render(f5);
			handL3.render(f5);
			handL4.render(f5);
			handL5.render(f5);
			wingL.render(f5);
			handR1.render(f5);
			handR2.render(f5);
			handR3.render(f5);
			handR4.render(f5);
			handR5.render(f5);
			wingR.render(f5);

		glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
