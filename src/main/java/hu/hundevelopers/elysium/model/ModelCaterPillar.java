package hu.hundevelopers.elysium.model;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCaterPillar extends ModelBase
{
	ModelRenderer Segment1;
	ModelRenderer Segment2;
	ModelRenderer Segment3;
	ModelRenderer TailPiece;
	ModelRenderer TailSlab;
	ModelRenderer PawL5;
	ModelRenderer PawL4;
	ModelRenderer PawL3;
	ModelRenderer PawL6;
	ModelRenderer PawL2;
	ModelRenderer PawL7;
	ModelRenderer PawL8;
	ModelRenderer PawL1;
	ModelRenderer PawR1;
	ModelRenderer PawR2;
	ModelRenderer PawR3;
	ModelRenderer PawR4;
	ModelRenderer PawR5;
	ModelRenderer PawR6;
	ModelRenderer PawR7;
	ModelRenderer PawR8;
	ModelRenderer Head;
	ModelRenderer Antenna1;
	ModelRenderer Antenna2;
	ModelRenderer Gem;

	public ModelCaterPillar()
	{
		textureWidth = 128;
		textureHeight = 64;

		Segment1 = new ModelRenderer(this, 0, 0);
		Segment1.addBox(-4F, -4F, 0F, 8, 8, 8);
		Segment1.setRotationPoint(0F, 18F, 4F);
		Segment1.setTextureSize(128, 64);
		Segment1.mirror = true;
		setRotation(Segment1, 0F, 0F, 0F);
		Segment2 = new ModelRenderer(this, 0, 0);
		Segment2.addBox(-4F, -4F, 0F, 8, 8, 8);
		Segment2.setRotationPoint(0F, 18F, -4F);
		Segment2.setTextureSize(128, 64);
		Segment2.mirror = true;
		setRotation(Segment2, 0F, 0F, 0F);
		Segment3 = new ModelRenderer(this, 0, 0);
		Segment3.addBox(-4F, -4F, 0F, 8, 8, 8);
		Segment3.setRotationPoint(0F, 18F, -12F);
		Segment3.setTextureSize(128, 64);
		Segment3.mirror = true;
		setRotation(Segment3, 0F, 0F, 0F);
		TailPiece = new ModelRenderer(this, 0, 24);
		TailPiece.addBox(-5F, -4F, 0F, 9, 9, 1);
		TailPiece.setRotationPoint(0.5F, 17.5F, 12F);
		TailPiece.setTextureSize(128, 64);
		TailPiece.mirror = true;
		setRotation(TailPiece, 0F, 0F, 0F);
		TailSlab = new ModelRenderer(this, 0, 36);
		TailSlab.addBox(-5F, -5F, 0F, 10, 10, 2);
		TailSlab.setRotationPoint(0F, 18F, 13F);
		TailSlab.setTextureSize(128, 64);
		TailSlab.mirror = true;
		setRotation(TailSlab, 0F, 0F, 0F);
		PawL5 = new ModelRenderer(this, 0, 0);
		PawL5.addBox(0F, 0F, 0F, 1, 3, 1);
		PawL5.setRotationPoint(3F, 21F, 1F);
		PawL5.setTextureSize(128, 64);
		PawL5.mirror = true;
		setRotation(PawL5, 0F, 0F, -0.3346075F);
		PawL4 = new ModelRenderer(this, 0, 0);
		PawL4.addBox(0F, 0F, 0F, 1, 3, 1);
		PawL4.setRotationPoint(3F, 21F, -2F);
		PawL4.setTextureSize(128, 64);
		PawL4.mirror = true;
		setRotation(PawL4, 0F, 0F, -0.3346075F);
		PawL3 = new ModelRenderer(this, 0, 0);
		PawL3.addBox(0F, 0F, 0F, 1, 3, 1);
		PawL3.setRotationPoint(3F, 21F, -5F);
		PawL3.setTextureSize(128, 64);
		PawL3.mirror = true;
		setRotation(PawL3, 0F, 0F, -0.3346075F);
		PawL6 = new ModelRenderer(this, 0, 0);
		PawL6.addBox(0F, 0F, 0F, 1, 3, 1);
		PawL6.setRotationPoint(3F, 21F, 4F);
		PawL6.setTextureSize(128, 64);
		PawL6.mirror = true;
		setRotation(PawL6, 0F, 0F, -0.3346075F);
		PawL2 = new ModelRenderer(this, 0, 0);
		PawL2.addBox(0F, 0F, 0F, 1, 3, 1);
		PawL2.setRotationPoint(3F, 21F, -8F);
		PawL2.setTextureSize(128, 64);
		PawL2.mirror = true;
		setRotation(PawL2, 0F, 0F, -0.3346075F);
		PawL7 = new ModelRenderer(this, 0, 0);
		PawL7.addBox(0F, 0F, 0F, 1, 3, 1);
		PawL7.setRotationPoint(3F, 21F, 7F);
		PawL7.setTextureSize(128, 64);
		PawL7.mirror = true;
		setRotation(PawL7, 0F, 0F, -0.3346075F);
		PawL8 = new ModelRenderer(this, 0, 0);
		PawL8.addBox(0F, 0F, 0F, 1, 3, 1);
		PawL8.setRotationPoint(3F, 21F, 10F);
		PawL8.setTextureSize(128, 64);
		PawL8.mirror = true;
		setRotation(PawL8, 0F, 0F, -0.3346075F);
		PawL1 = new ModelRenderer(this, 0, 0);
		PawL1.addBox(0F, 0F, 0F, 1, 3, 1);
		PawL1.setRotationPoint(3F, 21F, -11F);
		PawL1.setTextureSize(128, 64);
		PawL1.mirror = true;
		setRotation(PawL1, 0F, 0F, -0.3346075F);
		PawR1 = new ModelRenderer(this, 0, 0);
		PawR1.addBox(0F, 0F, 0F, 1, 3, 1);
		PawR1.setRotationPoint(-4F, 21F, -11F);
		PawR1.setTextureSize(128, 64);
		PawR1.mirror = true;
		setRotation(PawR1, 0F, 0F, 0.3346145F);
		PawR2 = new ModelRenderer(this, 0, 0);
		PawR2.addBox(0F, 0F, 0F, 1, 3, 1);
		PawR2.setRotationPoint(-4F, 21F, -8F);
		PawR2.setTextureSize(128, 64);
		PawR2.mirror = true;
		setRotation(PawR2, 0F, 0F, 0.3346145F);
		PawR3 = new ModelRenderer(this, 0, 0);
		PawR3.addBox(0F, 0F, 0F, 1, 3, 1);
		PawR3.setRotationPoint(-4F, 21F, -5F);
		PawR3.setTextureSize(128, 64);
		PawR3.mirror = true;
		setRotation(PawR3, 0F, 0F, 0.3346145F);
		PawR4 = new ModelRenderer(this, 0, 0);
		PawR4.addBox(0F, 0F, 0F, 1, 3, 1);
		PawR4.setRotationPoint(-4F, 21F, -2F);
		PawR4.setTextureSize(128, 64);
		PawR4.mirror = true;
		setRotation(PawR4, 0F, 0F, 0.3346145F);
		PawR5 = new ModelRenderer(this, 0, 0);
		PawR5.addBox(0F, 0F, 0F, 1, 3, 1);
		PawR5.setRotationPoint(-4F, 21F, 1F);
		PawR5.setTextureSize(128, 64);
		PawR5.mirror = true;
		setRotation(PawR5, 0F, 0F, 0.3346145F);
		PawR6 = new ModelRenderer(this, 0, 0);
		PawR6.addBox(0F, 0F, 0F, 1, 3, 1);
		PawR6.setRotationPoint(-4F, 21F, 4F);
		PawR6.setTextureSize(128, 64);
		PawR6.mirror = true;
		setRotation(PawR6, 0F, 0F, 0.3346145F);
		PawR7 = new ModelRenderer(this, 0, 0);
		PawR7.addBox(0F, 0F, 0F, 1, 3, 1);
		PawR7.setRotationPoint(-4F, 21F, 7F);
		PawR7.setTextureSize(128, 64);
		PawR7.mirror = true;
		setRotation(PawR7, 0F, 0F, 0.3346145F);
		PawR8 = new ModelRenderer(this, 0, 0);
		PawR8.addBox(0F, 0F, 0F, 1, 3, 1);
		PawR8.setRotationPoint(-4F, 21F, 10F);
		PawR8.setTextureSize(128, 64);
		PawR8.mirror = true;
		setRotation(PawR8, 0F, 0F, 0.3346145F);
		Head = new ModelRenderer(this, 36, 0);
		Head.addBox(-4F, 0F, 0F, 7, 6, 6);
		Head.setRotationPoint(0.5F, 18F, -16.4F);
		Head.setTextureSize(128, 64);
		Head.mirror = true;
		setRotation(Head, 0.7853982F, 0F, 0F);
		Antenna1 = new ModelRenderer(this, 0, 8);
		Antenna1.addBox(0F, -4F, 0F, 1, 4, 1);
		Antenna1.setRotationPoint(0F, 16F, -13F);
		Antenna1.setTextureSize(128, 64);
		Antenna1.mirror = true;
		setRotation(Antenna1, 0.6320364F, 0F, 0.496121F);
		Antenna2 = new ModelRenderer(this, 0, 8);
		Antenna2.addBox(0F, -4F, 0F, 1, 4, 1);
		Antenna2.setRotationPoint(-1F, 16F, -13F);
		Antenna2.setTextureSize(128, 64);
		Antenna2.mirror = true;
		setRotation(Antenna2, 0.6320361F, 0F, -0.4961273F);
		
		Gem = new ModelRenderer(this, 24, 0);
		Gem.addBox(-1F, -1F, -1F, 2, 2, 2);
		Gem.setRotationPoint(0F, 22F, -5F);
		Gem.setTextureSize(128, 64);
		Gem.mirror = true;
		setRotation(Gem, 0.7853982F, 0.7853982F, 0.7853982F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Segment1.render(f5);
		Segment2.render(f5);
		Segment3.render(f5);
		TailPiece.render(f5);
		TailSlab.render(f5);
		PawL5.render(f5);
		PawL4.render(f5);
		PawL3.render(f5);
		PawL6.render(f5);
		PawL2.render(f5);
		PawL7.render(f5);
		PawL8.render(f5);
		PawL1.render(f5);
		PawR1.render(f5);
		PawR2.render(f5);
		PawR3.render(f5);
		PawR4.render(f5);
		PawR5.render(f5);
		PawR6.render(f5);
		PawR7.render(f5);
		PawR8.render(f5);
		Head.render(f5);
		Antenna1.render(f5);
		Antenna2.render(f5);
		
		glPushMatrix();
		glEnable(GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		Gem.render(f5);
		
		glDisable(GL_BLEND);
		glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
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
		this.PawR1.rotateAngleX = this.PawR3.rotateAngleX = this.PawR5.rotateAngleX = this.PawR7.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.PawR2.rotateAngleX = this.PawR4.rotateAngleX = this.PawR6.rotateAngleX = this.PawR8.rotateAngleX = -MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		
		this.PawL1.rotateAngleX = this.PawL3.rotateAngleX = this.PawL5.rotateAngleX = this.PawL7.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.PawL2.rotateAngleX = this.PawL4.rotateAngleX = this.PawL6.rotateAngleX = this.PawL8.rotateAngleX = -MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
    }

}