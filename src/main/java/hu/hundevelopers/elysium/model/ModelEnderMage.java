package hu.hundevelopers.elysium.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEnderMage extends ModelBiped
{
	ModelRenderer body1;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer shoulderR;
	ModelRenderer shoulderL;
	ModelRenderer finger1L;
	ModelRenderer handL;
	ModelRenderer leftarm1;
	ModelRenderer leftarm2;
	ModelRenderer thumbL;
	ModelRenderer finger2L;
	ModelRenderer handR;
	ModelRenderer finger2R;
	ModelRenderer thumbR;
	ModelRenderer rightarm1;
	ModelRenderer rightarm2;
	ModelRenderer finger1R;
	ModelRenderer head1;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer leftleg1;
	ModelRenderer leftleg2;
	ModelRenderer rightleg1;
	ModelRenderer rightleg2;

	public ModelEnderMage()
	{
		textureWidth = 64;
		textureHeight = 128;

		body1 = new ModelRenderer(this, 23, 16);
		body1.addBox(-6F, -12F, -3F, 12, 6, 8);
		body1.setRotationPoint(0F, 0F, -1F);
		body1.setTextureSize(64, 128);
		body1.mirror = true;
		setRotation(body1, 0F, 0F, 0F);
		body2 = new ModelRenderer(this, 26, 30);
		body2.addBox(-5F, -6F, -2F, 10, 5, 6);
		body2.setRotationPoint(0F, 0F, -1F);
		body2.setTextureSize(64, 128);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		body3 = new ModelRenderer(this, 26, 41);
		body3.addBox(-4F, -1F, -2F, 8, 5, 6);
		body3.setRotationPoint(0F, 0F, -1F);
		body3.setTextureSize(64, 128);
		body3.mirror = true;
		setRotation(body3, 0F, 0F, 0F);
		body4 = new ModelRenderer(this, 22, 72);
		body4.addBox(-3F, -2F, -4F, 6, 12, 8);
		body4.setRotationPoint(0F, -4F, 0F);
		body4.setTextureSize(64, 128);
		body4.mirror = true;
		setRotation(body4, 0F, 0F, 0F);
		shoulderR = new ModelRenderer(this, 40, 58);
		shoulderR.addBox(-4.5F, -14F, -2.5F, 5, 7, 7);
		shoulderR.setRotationPoint(0F, 0F, -1F);
		shoulderR.setTextureSize(64, 128);
		shoulderR.mirror = true;
		setRotation(shoulderR, 0F, 0F, -0.5235988F);
		shoulderL = new ModelRenderer(this, 40, 58);
		shoulderL.mirror = true;
		shoulderL.addBox(-0.5F, -14F, -2.5F, 5, 7, 7);
		shoulderL.setRotationPoint(0F, 0F, -1F);
		shoulderL.setTextureSize(64, 128);
		shoulderL.mirror = true;
		setRotation(shoulderL, 0F, 0F, 0.5235988F);
		shoulderL.mirror = false;
		finger1L = new ModelRenderer(this, 14, 85);
		finger1L.mirror = true;
		finger1L.addBox(1F, 13.2F, -0.7F, 2, 4, 2);
		finger1L.setRotationPoint(0F, 0F, 0F);
		finger1L.setTextureSize(64, 128);
		finger1L.mirror = true;
		setRotation(finger1L, -0.2617994F, 0F, 0F);
		finger1L.mirror = false;
		handL = new ModelRenderer(this, 0, 80);
		handL.mirror = true;
		handL.addBox(-0.5F, 13F, -0.5F, 3, 2, 4);
		handL.setRotationPoint(0F, 0F, 0F);
		handL.setTextureSize(64, 128);
		handL.mirror = true;
		setRotation(handL, -0.2617994F, 0F, 0F);
		handL.mirror = false;
		leftarm1 = new ModelRenderer(this, 0, 55);
		leftarm1.mirror = true;
		leftarm1.addBox(-1F, -2F, -2F, 4, 9, 4);
		leftarm1.setRotationPoint(8F, -7F, 0F);
		leftarm1.setTextureSize(64, 128);
		leftarm1.mirror = true;
		setRotation(leftarm1, 0F, 0F, 0F);
		leftarm1.mirror = false;
		leftarm2 = new ModelRenderer(this, 0, 68);
		leftarm2.mirror = true;
		leftarm2.addBox(-1.5F, 6F, -1F, 5, 7, 5);
		leftarm2.setRotationPoint(0F, 0F, 0F);
		leftarm2.setTextureSize(64, 128);
		leftarm2.mirror = true;
		setRotation(leftarm2, -0.2617994F, 0F, 0F);
		leftarm2.mirror = false;
		thumbL = new ModelRenderer(this, 14, 80);
		thumbL.mirror = true;
		thumbL.addBox(-1F, 13.2F, -1F, 1, 3, 2);
		thumbL.setRotationPoint(0F, 0F, 0F);
		thumbL.setTextureSize(64, 128);
		thumbL.mirror = true;
		setRotation(thumbL, -0.2617994F, 0F, 0F);
		thumbL.mirror = false;
		finger2L = new ModelRenderer(this, 14, 85);
		finger2L.mirror = true;
		finger2L.addBox(1F, 13.2F, 1.6F, 2, 4, 2);
		finger2L.setRotationPoint(0F, 0F, 0F);
		finger2L.setTextureSize(64, 128);
		finger2L.mirror = true;
		setRotation(finger2L, -0.2617994F, 0F, 0F);
		finger2L.mirror = false;
		handR = new ModelRenderer(this, 0, 80);
		handR.addBox(-2.5F, 13F, -0.5F, 3, 2, 4);
		handR.setRotationPoint(0F, 0F, 0F);
		handR.setTextureSize(64, 128);
		handR.mirror = true;
		setRotation(handR, -0.2617994F, 0F, 0F);
		finger2R = new ModelRenderer(this, 14, 85);
		finger2R.addBox(-3F, 13.2F, 1.6F, 2, 4, 2);
		finger2R.setRotationPoint(0F, 0F, 0F);
		finger2R.setTextureSize(64, 128);
		finger2R.mirror = true;
		setRotation(finger2R, -0.2617994F, 0F, 0F);
		thumbR = new ModelRenderer(this, 14, 80);
		thumbR.addBox(0F, 13.2F, -1F, 1, 3, 2);
		thumbR.setRotationPoint(0F,0F, 0F);
		thumbR.setTextureSize(64, 128);
		thumbR.mirror = true;
		setRotation(thumbR, -0.2617994F, 0F, 0F);
		rightarm1 = new ModelRenderer(this, 0, 55);
		rightarm1.addBox(-3F, -2F, -2F, 4, 9, 4);
		rightarm1.setRotationPoint(-8F, -7F, 0F);
		rightarm1.setTextureSize(64, 128);
		rightarm1.mirror = true;
		setRotation(rightarm1, -0.8F, 0F, 0F);
		rightarm2 = new ModelRenderer(this, 0, 68);
		rightarm2.addBox(-3.5F, 6F, -1F, 5, 7, 5);
		rightarm2.setRotationPoint(0F, 0F, 0F);
		rightarm2.setTextureSize(64, 128);
		rightarm2.mirror = true;
		setRotation(rightarm2, -0.244F, 0F, 0F);
		finger1R = new ModelRenderer(this, 14, 85);
		finger1R.addBox(-3F, 13.2F, -0.7F, 2, 4, 2);
		finger1R.setRotationPoint(0F, 0F, 0F);
		finger1R.setTextureSize(64, 128);
		finger1R.mirror = true;
		setRotation(finger1R, -0.2617994F, 0F, 0F);
		head1 = new ModelRenderer(this, 0, 0);
		head1.addBox(-3F, -8F, -4.5F, 6, 10, 8);
		head1.setRotationPoint(0F, -11F, -2F);
		head1.setTextureSize(64, 128);
		head1.mirror = true;
		setRotation(head1, 0F, 0F, 0F);
		head2 = new ModelRenderer(this, 28, 0);
		head2.addBox(-4F, -6F, -4F, 8, 6, 8);
		head2.setRotationPoint(0F, -11F, -2F);
		head2.setTextureSize(64, 128);
		head2.mirror = true;
		setRotation(head2, 0F, 0F, 0F);
		head3 = new ModelRenderer(this, 52, 3);
		head3.addBox(-2F, 2F, -4.5F, 4, 2, 2);
		head3.setRotationPoint(0F, -11F, -2F);
		head3.setTextureSize(64, 128);
		head3.mirror = true;
		setRotation(head3, 0F, 0F, 0F);
		leftleg1 = new ModelRenderer(this, 0, 91);
		leftleg1.mirror = true;
		leftleg1.addBox(-2F, -2F, -2.5F, 4, 20, 4);
		leftleg1.setRotationPoint(3.5F, 6F, 0.5F);
		leftleg1.setTextureSize(64, 128);
		leftleg1.mirror = true;
		setRotation(leftleg1, 0F, 0F, 0F);
		leftleg1.mirror = false;
		leftleg2 = new ModelRenderer(this, 0, 33);
		leftleg2.mirror = true;
		leftleg2.addBox(-3F, 8F, -3.5F, 5, 6, 6);
		leftleg2.setRotationPoint(4F, 7F, 0.5F);
		leftleg2.setTextureSize(64, 128);
		leftleg2.mirror = true;
		setRotation(leftleg2, 0F, 0F, 0F);
		leftleg2.mirror = false;
		rightleg1 = new ModelRenderer(this, 0, 91);
		rightleg1.addBox(-2F, -2F, -2.5F, 4, 20, 4);
		rightleg1.setRotationPoint(-3.5F, 6F, 0.5F);
		rightleg1.setTextureSize(64, 128);
		rightleg1.mirror = true;
		setRotation(rightleg1, 0F, 0F, 0F);
		rightleg2 = new ModelRenderer(this, 0, 33);
		rightleg2.addBox(-3F, 8F, -3.5F, 5, 6, 6);
		rightleg2.setRotationPoint(-3F, 7F, 0.5F);
		rightleg2.setTextureSize(64, 128);
		rightleg2.mirror = true;
		setRotation(rightleg2, 0F, 0F, 0F);
		
		this.aimedBow = true;

		this.bipedBody.cubeList.clear();
		this.bipedCloak.cubeList.clear();
		this.bipedEars.cubeList.clear();
		this.bipedHead.cubeList.clear();
		this.bipedHeadwear.cubeList.clear();
		this.bipedLeftArm.cubeList.clear();
		this.bipedLeftLeg.cubeList.clear();
		this.bipedRightArm.cubeList.clear();
		this.bipedRightLeg.cubeList.clear();

		leftarm1.addChild(leftarm2);
		leftarm1.addChild(handL);
		leftarm1.addChild(finger1L);
		leftarm1.addChild(finger2L);
		leftarm1.addChild(thumbL);
		
		rightarm1.addChild(rightarm2);
		rightarm1.addChild(handR);
		rightarm1.addChild(finger1R);
		rightarm1.addChild(finger2R);
		rightarm1.addChild(thumbR);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		body1.render(f5);
		body2.render(f5);
		body3.render(f5);
		body4.render(f5);
		shoulderR.render(f5);
		shoulderL.render(f5);
		leftarm1.render(f5);
		rightarm1.render(f5);
		head1.render(f5);
		head2.render(f5);
		head3.render(f5);
		leftleg1.render(f5);
		leftleg2.render(f5);
		rightleg1.render(f5);
		rightleg2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	/**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.head1.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.head1.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.head2.rotateAngleY = this.head1.rotateAngleY;
        this.head2.rotateAngleX = this.head1.rotateAngleX;
        this.head3.rotateAngleY = this.head1.rotateAngleY;
        this.head3.rotateAngleX = this.head1.rotateAngleX;
        
        this.rightarm1.rotateAngleX = -0.8F + MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        
        this.leftarm1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        
        
        this.rightleg1.rotateAngleX = this.rightleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.rightleg1.rotateAngleY = this.rightleg2.rotateAngleY = 0.0F;
        
        this.leftleg1.rotateAngleX = this.leftleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.leftleg1.rotateAngleY = this.leftleg2.rotateAngleY = 0.0F;

        if (this.aimedBow)
        {
            float f6 = 0.0F;
            float f7 = 0.0F;
            this.rightarm1.rotateAngleZ = 0.0F;
            this.leftarm1.rotateAngleZ = 0.0F;
            this.rightarm1.rotateAngleY = -(0.1F - f6 * 0.6F) + this.bipedHead.rotateAngleY;
            this.leftarm1.rotateAngleY = 0.1F - f6 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
            this.rightarm1.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.leftarm1.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.rightarm1.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.leftarm1.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.rightarm1.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.leftarm1.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.rightarm1.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
            this.leftarm1.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        }
    }
}
