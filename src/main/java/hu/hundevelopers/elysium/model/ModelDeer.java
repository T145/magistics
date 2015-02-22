package hu.hundevelopers.elysium.model;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import hu.hundevelopers.elysium.entity.EntityDeer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDeer extends ModelBase
{
	ModelRenderer body3;
	ModelRenderer neck;
	ModelRenderer body1;
	ModelRenderer body2;
	ModelRenderer tail;
	ModelRenderer Gem;
	ModelRenderer earR;
	ModelRenderer head4;
	ModelRenderer head2;
	ModelRenderer head1;
	ModelRenderer nose;
	ModelRenderer head3;
	ModelRenderer earL;
	ModelRenderer antlerL;
	ModelRenderer antlerR;
	ModelRenderer leg1FL;
	ModelRenderer leg2FL;
	ModelRenderer leg3FL;
	ModelRenderer leg1BL;
	ModelRenderer leg2BL;
	ModelRenderer leg3BL;
	ModelRenderer leg1FR;
	ModelRenderer leg2FR;
	ModelRenderer leg3FR;
	ModelRenderer leg1BR;
	ModelRenderer leg2BR;
	ModelRenderer leg3BR;

	public ModelDeer()
	{
		textureWidth = 64;
		textureHeight = 64;

		body3 = new ModelRenderer(this, 34, 50);
		body3.addBox(-4.5F, -1F, 0F, 9, 6, 6);
		body3.setRotationPoint(0F, 6F, 0F);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.3665191F, 0F, 0F);
		neck = new ModelRenderer(this, 42, 0);
		neck.addBox(-2F, -9F, -3F, 4, 12, 5);
		neck.setRotationPoint(0F, 2F, -9F);
		neck.setTextureSize(64, 64);
		neck.mirror = true;
		setRotation(neck, 0.4363323F, 0F, 0F);
		body1 = new ModelRenderer(this, 28, 17);
		body1.addBox(-5F, -1F, -10F, 10, 8, 8);
		body1.setRotationPoint(0F, 6F, 0F);
		body1.setTextureSize(64, 64);
		body1.mirror = true;
		setRotation(body1, -0.418879F, 0F, 0F);
		body2 = new ModelRenderer(this, 26, 33);
		body2.addBox(-4F, -3F, -6F, 8, 6, 11);
		body2.setRotationPoint(0F, 6F, 0F);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		tail = new ModelRenderer(this, 31, 50);
		tail.addBox(-1F, -1F, 0F, 2, 4, 2);
		tail.setRotationPoint(0F, 4F, 5F);
		tail.setTextureSize(64, 64);
		tail.mirror = true;
		setRotation(tail, 0.7853982F, 0F, 0F);
		Gem = new ModelRenderer(this, 18, 20);
		Gem.addBox(-1F, -1F, -1F, 2, 2, 2);
		Gem.setRotationPoint(0F, -14F, -11F);
		Gem.setTextureSize(64, 64);
		Gem.mirror = true;
		setRotation(Gem, 0.7853982F, 0.7853982F, 0.7853982F);

		earR = new ModelRenderer(this, 25, 12);
		earR.mirror = true;
		earR.addBox(-5F, -6F, 1F, 3, 4, 1);
		earR.setRotationPoint(0F, -4F, -15F);
		earR.setTextureSize(64, 64);
		earR.mirror = true;
		setRotation(earR, 0F, 0F, 0F);
		earR.mirror = false;
		head4 = new ModelRenderer(this, 0, 24);
		head4.addBox(-2F, 1F, -5.9F, 4, 1, 4);
		head4.setRotationPoint(0F, -4F, -15F);
		head4.setTextureSize(64, 64);
		head4.mirror = true;
		setRotation(head4, 0F, 0F, 0F);
		head2 = new ModelRenderer(this, 0, 18);
		head2.addBox(-2F, -3F, -5F, 4, 2, 4);
		head2.setRotationPoint(0F, -4F, -15F);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.3665191F, 0F, 0F);
		head1 = new ModelRenderer(this, 0, 0);
		head1.addBox(-3F, -3F, -2F, 6, 5, 6);
		head1.setRotationPoint(0F, -4F, -15F);
		head1.setTextureSize(64, 64);
		head1.mirror = true;
		setRotation(head1, 0F, 0F, 0F);
		nose = new ModelRenderer(this, 18, 16);
		nose.addBox(-1F, -1.5F, -6.2F, 2, 1, 3);
		nose.setRotationPoint(0F, -4F, -15F);
		nose.setTextureSize(64, 64);
		nose.mirror = true;
		setRotation(nose, 0F, 0F, 0F);
		head3 = new ModelRenderer(this, 0, 11);
		head3.addBox(-2.5F, -1F, -6F, 5, 2, 4);
		head3.setRotationPoint(0F, -4F, -15F);
		head3.setTextureSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0F, 0F, 0F);
		earL = new ModelRenderer(this, 25, 12);
		earL.addBox(2F, -6F, 1F, 3, 4, 1);
		earL.setRotationPoint(0F, -4F, -15F);
		earL.setTextureSize(64, 64);
		earL.mirror = true;
		setRotation(earL, 0F, 0F, 0F);
		antlerL = new ModelRenderer(this, 24, -9);
		antlerL.addBox(-1F, -14F, 1F, 0, 12, 9);
		antlerL.setRotationPoint(0F, -4F, -15F);
		antlerL.setTextureSize(64, 64);
		antlerL.mirror = true;
		setRotation(antlerL, -0.2617994F, 0.7853982F, 0F);

		antlerR = new ModelRenderer(this, 24, -9);
		antlerR.mirror = true;
		antlerR.addBox(1F, -14F, 1F, 0, 12, 9);
		antlerR.setRotationPoint(0F, -4F, -15F);
		antlerR.setTextureSize(64, 64);
		antlerR.mirror = true;
		setRotation(antlerR, -0.2617994F, -0.7853982F, 0F);
		antlerR.mirror = false;
		leg1FL = new ModelRenderer(this, 0, 34);
		leg1FL.addBox(-1.5F, -1.5F, -2F, 3, 7, 4);
		leg1FL.setRotationPoint(5F, 7F, -8F);
		leg1FL.setTextureSize(64, 64);
		leg1FL.mirror = true;
		setRotation(leg1FL, 0.2617994F, 0F, 0F);
		leg2FL = new ModelRenderer(this, 14, 34);
		leg2FL.addBox(-1F, 4F, 0F, 2, 8, 3);
		leg2FL.setRotationPoint(5F, 7F, -8F);
		leg2FL.setTextureSize(64, 64);
		leg2FL.mirror = true;
		setRotation(leg2FL, -0.2617994F, 0F, 0F);
		leg3FL = new ModelRenderer(this, 24, 34);
		leg3FL.addBox(-0.5F, 11F, -4F, 1, 6, 2);
		leg3FL.setRotationPoint(5F, 7F, -8F);
		leg3FL.setTextureSize(64, 64);
		leg3FL.mirror = true;
		setRotation(leg3FL, 0.0872665F, 0F, 0F);
		leg1BL = new ModelRenderer(this, 0, 50);
		leg1BL.addBox(-1.5F, -2F, -2F, 3, 8, 4);
		leg1BL.setRotationPoint(5F, 7F, 4F);
		leg1BL.setTextureSize(64, 64);
		leg1BL.mirror = true;
		setRotation(leg1BL, -0.2617994F, 0F, 0F);
		leg2BL = new ModelRenderer(this, 14, 50);
		leg2BL.addBox(-1F, 3F, -5.5F, 2, 7, 3);
		leg2BL.setRotationPoint(5F, 7F, 4F);
		leg2BL.setTextureSize(64, 64);
		leg2BL.mirror = true;
		setRotation(leg2BL, 0.5235988F, 0F, 0F);
		leg3BL = new ModelRenderer(this, 24, 50);
		leg3BL.addBox(-0.5F, 10F, -0.5F, 1, 7, 2);
		leg3BL.setRotationPoint(5F, 7F, 4F);
		leg3BL.setTextureSize(64, 64);
		leg3BL.mirror = true;
		setRotation(leg3BL, 0F, 0F, 0F);

		leg1FR = new ModelRenderer(this, 0, 34);
		leg1FR.mirror = true;
		leg1FR.addBox(-1.5F, -1.5F, -2F, 3, 7, 4);
		leg1FR.setRotationPoint(-5F, 7F, -8F);
		leg1FR.setTextureSize(64, 64);
		leg1FR.mirror = true;
		setRotation(leg1FR, 0.2617994F, 0F, 0F);
		leg1FR.mirror = false;
		leg2FR = new ModelRenderer(this, 14, 34);
		leg2FR.mirror = true;
		leg2FR.addBox(-1F, 4F, 0F, 2, 8, 3);
		leg2FR.setRotationPoint(-5F, 7F, -8F);
		leg2FR.setTextureSize(64, 64);
		leg2FR.mirror = true;
		setRotation(leg2FR, -0.2617994F, 0F, 0F);
		leg2FR.mirror = false;
		leg3FR = new ModelRenderer(this, 24, 34);
		leg3FR.mirror = true;
		leg3FR.addBox(-0.5F, 11F, -4F, 1, 6, 2);
		leg3FR.setRotationPoint(-5F, 7F, -8F);
		leg3FR.setTextureSize(64, 64);
		leg3FR.mirror = true;
		setRotation(leg3FR, 0.0872665F, 0F, 0F);
		leg3FR.mirror = false;
		leg1BR = new ModelRenderer(this, 0, 50);
		leg1BR.mirror = true;
		leg1BR.addBox(-1.5F, -2F, -2F, 3, 8, 4);
		leg1BR.setRotationPoint(-5F, 8F, 4F);
		leg1BR.setTextureSize(64, 64);
		leg1BR.mirror = true;
		setRotation(leg1BR, -0.2617994F, 0F, 0F);
		leg1BR.mirror = false;
		leg2BR = new ModelRenderer(this, 14, 50);
		leg2BR.mirror = true;
		leg2BR.addBox(-1F, 3F, -5.5F, 2, 7, 3);
		leg2BR.setRotationPoint(-5F, 7F, 4F);
		leg2BR.setTextureSize(64, 64);
		leg2BR.mirror = true;
		setRotation(leg2BR, 0.5235988F, 0F, 0F);
		leg2BR.mirror = false;

		leg3BR = new ModelRenderer(this, 24, 50);
		leg3BR.mirror = true;
		leg3BR.addBox(-0.5F, 10F, -0.5F, 1, 7, 2);
		leg3BR.setRotationPoint(-5F, 7F, 4F);
		leg3BR.setTextureSize(64, 64);
		leg3BR.mirror = true;
		setRotation(leg3BR, 0F, 0F, 0F);
		leg3BR.mirror = false;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		body3.render(f5);
		neck.render(f5);
		body1.render(f5);
		body2.render(f5);
		tail.render(f5);
		
		glPushMatrix();
		glEnable(GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		Gem.render(f5);
		
		glDisable(GL_BLEND);
		glPopMatrix();
		
		
		earR.render(f5);
		head4.render(f5);
		head2.render(f5);
		head1.render(f5);
		nose.render(f5);
		head3.render(f5);
		earL.render(f5);
		antlerL.render(f5);
		antlerR.render(f5);
		leg1FL.render(f5);
		leg2FL.render(f5);
		leg3FL.render(f5);
		leg1BL.render(f5);
		leg2BL.render(f5);
		leg3BL.render(f5);
		leg1FR.render(f5);
		leg2FR.render(f5);
		leg3FR.render(f5);
		leg1BR.render(f5);
		leg2BR.render(f5);
		leg3BR.render(f5);
	}
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
		par2 /= 1.5F;

		EntityDeer deer = (EntityDeer)entity;
		this.leg1BL.rotateAngleX = (float) Math.toRadians(-15) + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leg2BL.rotateAngleX = (float) Math.toRadians(30) + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leg3BL.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;

		this.leg1FR.rotateAngleX = (float) Math.toRadians(15) + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leg2FR.rotateAngleX = (float) Math.toRadians(-15) + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leg3FR.rotateAngleX = (float) Math.toRadians(5) + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		
		this.leg1FL.rotateAngleX = (float) Math.toRadians(15) + MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.leg2FL.rotateAngleX = (float) Math.toRadians(-15) + MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.leg3FL.rotateAngleX = (float) Math.toRadians(5) + MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;

		this.leg1BR.rotateAngleX = (float) Math.toRadians(-15) + MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.leg2BR.rotateAngleX = (float) Math.toRadians(30) + MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.leg3BR.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;

//		this.head1.rotateAngleY = this.head2.rotateAngleY = this.head3.rotateAngleY = this.head4.rotateAngleY = deer.rotationYawHead;
	}
}
