package hu.hundevelopers.elysium.model;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSwan extends ModelBase
{
	// fields
	ModelRenderer lfoot2;
	ModelRenderer tail3;
	ModelRenderer lfoot1;
	ModelRenderer rfoot1;
	ModelRenderer rfoot2;
	ModelRenderer rwing5;
	ModelRenderer tail1;
	ModelRenderer tail2;
	ModelRenderer head2;
	ModelRenderer rwing1;
	ModelRenderer rwing2;
	ModelRenderer rwing3;
	ModelRenderer rwing4;
	ModelRenderer lwing1;
	ModelRenderer lwing2;
	ModelRenderer lwing3;
	ModelRenderer lwing4;
	ModelRenderer lwing5;
	ModelRenderer body;
	ModelRenderer neck1;
	ModelRenderer neck2;
	ModelRenderer neck3;
	ModelRenderer neck4;
	ModelRenderer head1;
	ModelRenderer Gem;

	public ModelSwan()
	{
		textureWidth = 64;
		textureHeight = 32;

		lfoot2 = new ModelRenderer(this, 30, 0);
		lfoot2.addBox(-0.5F, 2F, -1F, 2, 1, 2);
		lfoot2.setRotationPoint(2F, 21F, 3F);
		lfoot2.setTextureSize(64, 32);
		lfoot2.mirror = true;
		setRotation(lfoot2, 0F, 0F, 0F);
		tail3 = new ModelRenderer(this, 42, 21);
		tail3.addBox(-1F, 1F, -4F, 2, 1, 7);
		tail3.setRotationPoint(0F, 16.5F, 9.5F);
		tail3.setTextureSize(64, 32);
		tail3.mirror = true;
		setRotation(tail3, 0.8028515F, 0F, 0F);
		lfoot1 = new ModelRenderer(this, 26, 0);
		lfoot1.addBox(0F, 0F, 0F, 1, 2, 1);
		lfoot1.setRotationPoint(2F, 21F, 3F);
		lfoot1.setTextureSize(64, 32);
		lfoot1.mirror = true;
		setRotation(lfoot1, 0F, 0F, 0F);
		rfoot1 = new ModelRenderer(this, 26, 0);
		rfoot1.addBox(-1F, 0F, 0F, 1, 2, 1);
		rfoot1.setRotationPoint(-2F, 21F, 3F);
		rfoot1.setTextureSize(64, 32);
		rfoot1.mirror = true;
		setRotation(rfoot1, 0F, 0F, 0F);
		rfoot2 = new ModelRenderer(this, 30, 0);
		rfoot2.addBox(-1.5F, 2F, -1F, 2, 1, 2);
		rfoot2.setRotationPoint(-2F, 21F, 3F);
		rfoot2.setTextureSize(64, 32);
		rfoot2.mirror = true;
		setRotation(rfoot2, 0F, 0F, 0F);
		rwing5 = new ModelRenderer(this, 8, 23);
		rwing5.addBox(-1F, -4F, 0F, 1, 1, 7);
		rwing5.setRotationPoint(-4F, 18F, -4F);
		rwing5.setTextureSize(64, 32);
		rwing5.mirror = true;
		setRotation(rwing5, 0.3490659F, 0.0698132F, 0F);
		tail1 = new ModelRenderer(this, 38, 21);
		tail1.addBox(-3F, -3F, -6F, 6, 3, 7);
		tail1.setRotationPoint(0F, 16.5F, 9.5F);
		tail1.setTextureSize(64, 32);
		tail1.mirror = true;
		setRotation(tail1, 0.8028515F, 0F, 0F);
		tail2 = new ModelRenderer(this, 40, 21);
		tail2.addBox(-2F, -1F, -5F, 4, 2, 7);
		tail2.setRotationPoint(0F, 16.5F, 9.5F);
		tail2.setTextureSize(64, 32);
		tail2.mirror = true;
		setRotation(tail2, 0.8028515F, 0F, 0F);
		head2 = new ModelRenderer(this, 0, 7);
		head2.addBox(-1F, -13.6F, -5.5F, 2, 2, 2);
		head2.setRotationPoint(0F, 17F, -4F);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0F, 0F, 0F);
		rwing1 = new ModelRenderer(this, 0, 15);
		rwing1.addBox(-1F, 0F, 0F, 1, 2, 15);
		rwing1.setRotationPoint(-4F, 18F, -4F);
		rwing1.setTextureSize(64, 32);
		rwing1.mirror = true;
		setRotation(rwing1, 0.3490659F, 0.0698132F, 0F);
		rwing2 = new ModelRenderer(this, 13, 11);
		rwing2.addBox(-1F, -1F, 0F, 1, 1, 14);
		rwing2.setRotationPoint(-4F, 18F, -4F);
		rwing2.setTextureSize(64, 32);
		rwing2.mirror = true;
		setRotation(rwing2, 0.3490659F, 0.0698132F, 0F);
		rwing3 = new ModelRenderer(this, 3, 18);
		rwing3.addBox(-1F, -2F, 0F, 1, 1, 12);
		rwing3.setRotationPoint(-4F, 18F, -4F);
		rwing3.setTextureSize(64, 32);
		rwing3.mirror = true;
		setRotation(rwing3, 0.3490659F, 0.0698132F, 0F);
		rwing4 = new ModelRenderer(this, 16, 15);
		rwing4.addBox(-1F, -3F, 0F, 1, 1, 10);
		rwing4.setRotationPoint(-4F, 18F, -4F);
		rwing4.setTextureSize(64, 32);
		rwing4.mirror = true;
		setRotation(rwing4, 0.3490659F, 0.0698132F, 0F);
		lwing1 = new ModelRenderer(this, 0, 15);
		lwing1.addBox(0F, 0F, 0F, 1, 2, 15);
		lwing1.setRotationPoint(4F, 18F, -4F);
		lwing1.setTextureSize(64, 32);
		lwing1.mirror = true;
		setRotation(lwing1, 0.3490659F, -0.0698132F, 0F);
		lwing2 = new ModelRenderer(this, 13, 11);
		lwing2.addBox(0F, -1F, 0F, 1, 1, 14);
		lwing2.setRotationPoint(4F, 18F, -4F);
		lwing2.setTextureSize(64, 32);
		lwing2.mirror = true;
		setRotation(lwing2, 0.3490659F, -0.0698132F, 0F);
		lwing3 = new ModelRenderer(this, 3, 18);
		lwing3.addBox(0F, -2F, 0F, 1, 1, 12);
		lwing3.setRotationPoint(4F, 18F, -4F);
		lwing3.setTextureSize(64, 32);
		lwing3.mirror = true;
		setRotation(lwing3, 0.3490659F, -0.0698132F, 0F);
		lwing4 = new ModelRenderer(this, 16, 15);
		lwing4.addBox(0F, -3F, 0F, 1, 1, 10);
		lwing4.setRotationPoint(4F, 18F, -4F);
		lwing4.setTextureSize(64, 32);
		lwing4.mirror = true;
		setRotation(lwing4, 0.3490659F, -0.0698132F, 0F);
		lwing5 = new ModelRenderer(this, 8, 23);
		lwing5.addBox(0F, -4F, 0F, 1, 1, 7);
		lwing5.setRotationPoint(4F, 18F, -4F);
		lwing5.setTextureSize(64, 32);
		lwing5.mirror = true;
		setRotation(lwing5, 0.3490659F, -0.0698132F, 0F);
		body = new ModelRenderer(this, 34, 0);
		body.addBox(-4F, -9F, -7F, 8, 14, 7);
		body.setRotationPoint(0F, 14F, 3F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 1.570796F, 0F, 0F);
		neck1 = new ModelRenderer(this, 14, 0);
		neck1.addBox(-1.5F, -4F, -3F, 3, 5, 3);
		neck1.setRotationPoint(0F, 17F, -4F);
		neck1.setTextureSize(64, 32);
		neck1.mirror = true;
		setRotation(neck1, 0F, 0F, 0F);
		neck2 = new ModelRenderer(this, 0, 22);
		neck2.addBox(-1F, -7F, -4F, 2, 5, 2);
		neck2.setRotationPoint(0F, 17F, -4F);
		neck2.setTextureSize(64, 32);
		neck2.mirror = true;
		setRotation(neck2, -0.6108652F, 0F, 0F);
		neck3 = new ModelRenderer(this, 0, 16);
		neck3.addBox(-1F, -10.8F, 0.8F, 2, 4, 2);
		neck3.setRotationPoint(0F, 17F, -4F);
		neck3.setTextureSize(64, 32);
		neck3.mirror = true;
		setRotation(neck3, 0.0698132F, 0F, 0F);
		neck4 = new ModelRenderer(this, 0, 11);
		neck4.addBox(-1F, -11.6F, 5.2F, 2, 3, 2);
		neck4.setRotationPoint(0F, 17F, -4F);
		neck4.setTextureSize(64, 32);
		neck4.mirror = true;
		setRotation(neck4, 0.5235988F, 0F, 0F);
		head1 = new ModelRenderer(this, 0, 0);
		head1.addBox(-1.5F, -14.6F, -3.5F, 3, 3, 4);
		head1.setRotationPoint(0F, 17F, -4F);
		head1.setTextureSize(64, 32);
		head1.mirror = true;
		setRotation(head1, 0F, 0F, 0F);
		
		Gem = new ModelRenderer(this, 26, 3);
		Gem.addBox(-1F, -1F, -1F, 2, 2, 2);
		Gem.setRotationPoint(0F, 14F, 0F);
		Gem.setTextureSize(64, 32);
		Gem.mirror = true;
		setRotation(Gem, 0.7853982F, 0.7853982F, 0.7853982F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		if(this.isChild)
		{
			GL11.glPushMatrix();
	        float f6 = 2F;
	        GL11.glScalef(1.0F / f6 , 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 1.5F, 0);

		}
		lfoot2.render(f5);
		tail3.render(f5);
		lfoot1.render(f5);
		rfoot1.render(f5);
		rfoot2.render(f5);
		rwing5.render(f5);
		tail1.render(f5);
		tail2.render(f5);
		head2.render(f5);
		rwing1.render(f5);
		rwing2.render(f5);
		rwing3.render(f5);
		rwing4.render(f5);
		lwing1.render(f5);
		lwing2.render(f5);
		lwing3.render(f5);
		lwing4.render(f5);
		lwing5.render(f5);
		body.render(f5);
		neck1.render(f5);
		neck2.render(f5);
		neck3.render(f5);
		neck4.render(f5);
		head1.render(f5);

		glPushMatrix();
		glEnable(GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		Gem.render(f5);
		
		glDisable(GL_BLEND);
		glPopMatrix();
		
		
		if(this.isChild)
			GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	/**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
	@Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
//        this.head1.rotateAngleX = par5 / (180F / (float)Math.PI);
//        this.head1.rotateAngleY = par4 / (180F / (float)Math.PI);
//        this.bill.rotateAngleX = this.head1.rotateAngleX;
//        this.bill.rotateAngleY = this.head1.rotateAngleY;
//        this.chin.rotateAngleX = this.head1.rotateAngleX;
//        this.chin.rotateAngleY = this.head1.rotateAngleY;
//        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.rfoot1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.lfoot1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.rfoot2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.lfoot2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.rwing1.rotateAngleZ = this.rwing2.rotateAngleZ = this.rwing3.rotateAngleZ = this.rwing4.rotateAngleZ = this.rwing5.rotateAngleZ = -par3;
        this.lwing1.rotateAngleZ = this.lwing2.rotateAngleZ = this.lwing3.rotateAngleZ = this.lwing4.rotateAngleZ = this.lwing5.rotateAngleZ = par3;
    }

}
