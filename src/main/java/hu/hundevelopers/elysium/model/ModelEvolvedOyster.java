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

public class ModelEvolvedOyster extends ModelBase {
	// fields
	ModelRenderer legright;
	ModelRenderer shellup1;
	ModelRenderer shellup2;
	ModelRenderer shellup3;
	ModelRenderer shellbottom1;
	ModelRenderer shellbottom2;
	ModelRenderer shellbottom3;
	ModelRenderer legleft;
	ModelRenderer head;
	ModelRenderer pearl1;
	ModelRenderer pearl2;
	ModelRenderer pearl3;

	public ModelEvolvedOyster() {
		this.textureWidth = 64;
		this.textureHeight = 32;

		legright = new ModelRenderer(this, 52, 0);
		legright.addBox(0F, 0F, 0F, 1, 3, 1);
		legright.setRotationPoint(1F, 21F, 0F);
		legright.setTextureSize(64, 32);
		legright.mirror = true;
		setRotation(legright, 0F, 0F, 0F);
		pearl3 = new ModelRenderer(this, 14, 0);
		pearl3.addBox(0F, 0F, 0F, 1, 1, 1);
		pearl3.setRotationPoint(-2F, 20.3F, -2F);
		pearl3.setTextureSize(64, 32);
		pearl3.mirror = true;
		setRotation(pearl3, -0.2602503F, -0.1115358F, 0F);
		shellup1 = new ModelRenderer(this, 31, 24);
		shellup1.addBox(0F, 0F, -4F, 2, 1, 7);
		shellup1.setRotationPoint(0F, 19.5F, 0F);
		shellup1.setTextureSize(64, 32);
		shellup1.mirror = true;
		setRotation(shellup1, -0.669215F, -0.5948578F, -0.0371786F);
		shellup2 = new ModelRenderer(this, 10, 16);
		shellup2.addBox(-1.5F, 0F, -5F, 3, 1, 7);
		shellup2.setRotationPoint(0F, 19F, 1F);
		shellup2.setTextureSize(64, 32);
		shellup2.mirror = true;
		setRotation(shellup2, -0.7063936F, -0.1115358F, 0F);
		shellup3 = new ModelRenderer(this, 31, 0);
		shellup3.addBox(-2F, 0F, -5F, 2, 1, 6);
		shellup3.setRotationPoint(0F, 19.5F, 1F);
		shellup3.setTextureSize(64, 32);
		shellup3.mirror = true;
		setRotation(shellup3, -0.5948578F, 0.1115358F, -0.0743572F);
		shellbottom1 = new ModelRenderer(this, 31, 16);
		shellbottom1.addBox(0F, 0F, 0F, 3, 1, 6);
		shellbottom1.setRotationPoint(-1F, 22F, -3F);
		shellbottom1.setTextureSize(64, 32);
		shellbottom1.mirror = true;
		setRotation(shellbottom1, 0.3346075F, -0.1115358F, 0F);
		shellbottom2 = new ModelRenderer(this, 31, 0);
		shellbottom2.addBox(0F, 0F, 0F, 2, 1, 6);
		shellbottom2.setRotationPoint(-3F, 21F, -3F);
		shellbottom2.setTextureSize(64, 32);
		shellbottom2.mirror = true;
		setRotation(shellbottom2, 0.1487144F, 0.2230717F, 0.2974289F);
		shellbottom3 = new ModelRenderer(this, 31, 0);
		shellbottom3.addBox(0F, 0F, 0F, 2, 1, 6);
		shellbottom3.setRotationPoint(2F, 21.9F, -3F);
		shellbottom3.setTextureSize(64, 32);
		shellbottom3.mirror = true;
		setRotation(shellbottom3, 0.1858931F, -0.4461433F, -0.4461433F);
		legleft = new ModelRenderer(this, 52, 0);
		legleft.addBox(0F, 0F, 0F, 1, 3, 1);
		legleft.setRotationPoint(-2F, 21F, 0F);
		legleft.setTextureSize(64, 32);
		legleft.mirror = true;
		setRotation(legleft, 0F, 0F, 0F);
		head = new ModelRenderer(this, 2, 0);
		head.addBox(0F, 0F, 0F, 2, 2, 2);
		head.setRotationPoint(-1F, 19.5F, -1F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, -0.1858931F, -0.1115358F, 0F);
		pearl2 = new ModelRenderer(this, 14, 0);
		pearl2.addBox(0F, 0F, 0F, 1, 1, 1);
		pearl2.setRotationPoint(0F, 21F, -3F);
		pearl2.setTextureSize(64, 32);
		pearl2.mirror = true;
		setRotation(pearl2, -0.0743572F, -0.1115358F, 0F);
		pearl1 = new ModelRenderer(this, 14, 0);
		pearl1.addBox(0F, 0F, 0F, 1, 1, 1);
		pearl1.setRotationPoint(2F, 20.5F, -2F);
		pearl1.setTextureSize(64, 32);
		pearl1.mirror = true;
		setRotation(pearl1, 0.0743572F, -0.1115358F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		legright.render(f5);
		shellup1.render(f5);
		shellup2.render(f5);
		shellup3.render(f5);
		shellbottom1.render(f5);
		shellbottom2.render(f5);
		shellbottom3.render(f5);
		legleft.render(f5);
		head.render(f5);
		
		glPushMatrix();
		glEnable(GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		pearl1.render(f5);
		pearl2.render(f5);
		pearl3.render(f5);
		
		glDisable(GL_BLEND);
		glPopMatrix();
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
		this.legleft.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.legright.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;

		this.shellup1.rotateAngleX = -0.669215F + MathHelper.cos(par1 * 0.6662F/4F) * 0.5F * par2;
		this.shellup2.rotateAngleX = -0.7063936F + MathHelper.cos(par1 * 0.6662F/4F) * 0.5F * par2;
		this.shellup3.rotateAngleX = -0.5948578F + MathHelper.cos(par1 * 0.6662F/4F) * 0.5F * par2;
	}

}
