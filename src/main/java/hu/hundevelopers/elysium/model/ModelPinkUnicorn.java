package hu.hundevelopers.elysium.model;

import hu.hundevelopers.elysium.entity.EntityPinkUnicorn;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPinkUnicorn extends ModelBase
{
	private ModelRenderer body;
	private ModelRenderer mouthBottom;
	private ModelRenderer mouthTop;
	private ModelRenderer horseRightEar;
	private ModelRenderer horseLeftEar;
	private ModelRenderer mane;
	private ModelRenderer horn;
	private ModelRenderer horn2;
	private ModelRenderer neck;
	private ModelRenderer head;
	private ModelRenderer tailBase;
	private ModelRenderer tailTip;
	private ModelRenderer tailMiddle;
	private ModelRenderer backRightHoof;
	private ModelRenderer backRightLeg;
	private ModelRenderer backRightShin;
	private ModelRenderer backLeftShin;
	private ModelRenderer backLeftHoof;
	private ModelRenderer backLeftLeg;
	private ModelRenderer frontRightHoof;
	private ModelRenderer frontRightLeg;
	private ModelRenderer frontRightShin;
	private ModelRenderer frontLeftHoof;
	private ModelRenderer frontLeftLeg;
	private ModelRenderer frontLeftShin;
	private ModelRenderer horseLeftFaceMetal;
	private ModelRenderer horseRightFaceMetal;
	private ModelRenderer horseLeftRein;
	private ModelRenderer horseRightRein;
	private ModelRenderer horseSaddleBottom;
	private ModelRenderer horseSaddleFront;
	private ModelRenderer horseSaddleBack;
	private ModelRenderer horseFaceRopes;
	private ModelRenderer horseLeftSaddleRope;
	private ModelRenderer horseLeftSaddleMetal;
	private ModelRenderer horseRightSaddleMetal;
	private ModelRenderer horseRightSaddleRope;
	private ModelRenderer Gem;

    public ModelPinkUnicorn()
    {
        this.textureWidth = 128;
        this.textureHeight = 128;
        
        this.body = new ModelRenderer(this, 0, 34);
        this.body.addBox(-5.0F, -8.0F, -19.0F, 10, 10, 24);
        this.body.setRotationPoint(0.0F, 11.0F, 9.0F);
        this.tailBase = new ModelRenderer(this, 44, 0);
        this.tailBase.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3);
        this.tailBase.setRotationPoint(0.0F, 3.0F, 14.0F);
        this.setBoxRotation(this.tailBase, -1.134464F, 0.0F, 0.0F);
        this.tailMiddle = new ModelRenderer(this, 38, 7);
        this.tailMiddle.addBox(-1.5F, -2.0F, 3.0F, 3, 4, 7);
        this.tailMiddle.setRotationPoint(0.0F, 3.0F, 14.0F);
        this.setBoxRotation(this.tailMiddle, -1.134464F, 0.0F, 0.0F);
        this.tailTip = new ModelRenderer(this, 24, 3);
        this.tailTip.addBox(-1.5F, -4.5F, 9.0F, 3, 4, 7);
        this.tailTip.setRotationPoint(0.0F, 3.0F, 14.0F);
        this.setBoxRotation(this.tailTip, -1.40215F, 0.0F, 0.0F);
        this.backLeftLeg = new ModelRenderer(this, 78, 29);
        this.backLeftLeg.addBox(-2.5F, -2.0F, -2.5F, 4, 9, 5);
        this.backLeftLeg.setRotationPoint(4.0F, 9.0F, 11.0F);
        this.backLeftShin = new ModelRenderer(this, 78, 43);
        this.backLeftShin.addBox(-2.0F, 0.0F, -1.5F, 3, 5, 3);
        this.backLeftShin.setRotationPoint(4.0F, 16.0F, 11.0F);
        this.backLeftHoof = new ModelRenderer(this, 78, 51);
        this.backLeftHoof.addBox(-2.5F, 5.1F, -2.0F, 4, 3, 4);
        this.backLeftHoof.setRotationPoint(4.0F, 16.0F, 11.0F);
        this.backRightLeg = new ModelRenderer(this, 96, 29);
        this.backRightLeg.addBox(-1.5F, -2.0F, -2.5F, 4, 9, 5);
        this.backRightLeg.setRotationPoint(-4.0F, 9.0F, 11.0F);
        this.backRightShin = new ModelRenderer(this, 96, 43);
        this.backRightShin.addBox(-1.0F, 0.0F, -1.5F, 3, 5, 3);
        this.backRightShin.setRotationPoint(-4.0F, 16.0F, 11.0F);
        this.backRightHoof = new ModelRenderer(this, 96, 51);
        this.backRightHoof.addBox(-1.5F, 5.1F, -2.0F, 4, 3, 4);
        this.backRightHoof.setRotationPoint(-4.0F, 16.0F, 11.0F);
        this.frontLeftLeg = new ModelRenderer(this, 44, 29);
        this.frontLeftLeg.addBox(-1.9F, -1.0F, -2.1F, 3, 8, 4);
        this.frontLeftLeg.setRotationPoint(4.0F, 9.0F, -8.0F);
        this.frontLeftShin = new ModelRenderer(this, 44, 41);
        this.frontLeftShin.addBox(-1.9F, 0.0F, -1.6F, 3, 5, 3);
        this.frontLeftShin.setRotationPoint(4.0F, 16.0F, -8.0F);
        this.frontLeftHoof = new ModelRenderer(this, 44, 51);
        this.frontLeftHoof.addBox(-2.4F, 5.1F, -2.1F, 4, 3, 4);
        this.frontLeftHoof.setRotationPoint(4.0F, 16.0F, -8.0F);
        this.frontRightLeg = new ModelRenderer(this, 60, 29);
        this.frontRightLeg.addBox(-1.1F, -1.0F, -2.1F, 3, 8, 4);
        this.frontRightLeg.setRotationPoint(-4.0F, 9.0F, -8.0F);
        this.frontRightShin = new ModelRenderer(this, 60, 41);
        this.frontRightShin.addBox(-1.1F, 0.0F, -1.6F, 3, 5, 3);
        this.frontRightShin.setRotationPoint(-4.0F, 16.0F, -8.0F);
        this.frontRightHoof = new ModelRenderer(this, 60, 51);
        this.frontRightHoof.addBox(-1.6F, 5.1F, -2.1F, 4, 3, 4);
        this.frontRightHoof.setRotationPoint(-4.0F, 16.0F, -8.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-2.5F, -10.0F, -1.5F, 5, 5, 7);
        this.head.setRotationPoint(0.0F, 4.0F, -10.0F);
        this.setBoxRotation(this.head, 0.5235988F, 0.0F, 0.0F);
        
        
        this.Gem = new ModelRenderer(this, 17, 0);
        this.Gem.addBox(-1F, -26F, 11F, 2, 2, 2);
        this.Gem.setRotationPoint(0F, 4F, -10F);
//        this.setBoxRotation(this.Gem, 0.5235988F, 0F, 0F);
              
        this.head.addChild(this.Gem);

        this.horn = new ModelRenderer(this, 0, 68);
        this.horn.addBox(-1F, -19F, 11F, 2, 5, 2);
        this.horn.setRotationPoint(0F, 4F, -10F);
//        this.setBoxRotation(this.horn, 0.5235988F, 0F, 0F);
        
        this.horn2 = new ModelRenderer(this, 0, 75);
        this.horn2.addBox(-0.5F, -24F, 11.5F, 1, 5, 1);
        this.horn2.setRotationPoint(0F, 3.95F, -10F);
//        this.setBoxRotation(horn2, 0.5235988F, 0F, 0F);
        
        this.head.addChild(this.horn);
        this.head.addChild(this.horn2);

        this.mouthBottom = new ModelRenderer(this, 24, 27);
        this.mouthBottom.addBox(-2.0F, -7.0F, -6.5F, 4, 2, 5);
        this.mouthBottom.setRotationPoint(0.0F, 4.0F, -10.0F);
        this.setBoxRotation(this.mouthBottom, 0.5235988F, 0.0F, 0.0F);
        
        
        this.mouthTop = new ModelRenderer(this, 24, 18);
        this.mouthTop.addBox(-2.0F, -10.0F, -7.0F, 4, 3, 6);
        this.mouthTop.setRotationPoint(0.0F, 3.95F, -10.0F);
        this.setBoxRotation(this.mouthTop, 0.5235988F, 0.0F, 0.0F);
        
        this.head.addChild(this.mouthTop);
        this.head.addChild(this.mouthBottom);
        
        this.horseLeftEar = new ModelRenderer(this, 0, 0);
        this.horseLeftEar.addBox(0.45F, -12.0F, 4.0F, 2, 3, 1);
        this.horseLeftEar.setRotationPoint(0.0F, 4.0F, -10.0F);
        this.setBoxRotation(this.horseLeftEar, 0.5235988F, 0.0F, 0.0F);
        
        this.horseRightEar = new ModelRenderer(this, 0, 0);
        this.horseRightEar.addBox(-2.45F, -12.0F, 4.0F, 2, 3, 1);
        this.horseRightEar.setRotationPoint(0.0F, 4.0F, -10.0F);
        this.setBoxRotation(this.horseRightEar, 0.5235988F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 0, 12);
        this.neck.addBox(-2.05F, -9.8F, -2.0F, 4, 14, 8);
        this.neck.setRotationPoint(0.0F, 4.0F, -10.0F);
        this.setBoxRotation(this.neck, 0.5235988F, 0.0F, 0.0F);
        this.horseSaddleBottom = new ModelRenderer(this, 80, 0);
        this.horseSaddleBottom.addBox(-5.0F, 0.0F, -3.0F, 10, 1, 8);
        this.horseSaddleBottom.setRotationPoint(0.0F, 2.0F, 2.0F);
        this.horseSaddleFront = new ModelRenderer(this, 106, 9);
        this.horseSaddleFront.addBox(-1.5F, -1.0F, -3.0F, 3, 1, 2);
        this.horseSaddleFront.setRotationPoint(0.0F, 2.0F, 2.0F);
        this.horseSaddleBack = new ModelRenderer(this, 80, 9);
        this.horseSaddleBack.addBox(-4.0F, -1.0F, 3.0F, 8, 1, 2);
        this.horseSaddleBack.setRotationPoint(0.0F, 2.0F, 2.0F);
        this.horseLeftSaddleMetal = new ModelRenderer(this, 74, 0);
        this.horseLeftSaddleMetal.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
        this.horseLeftSaddleMetal.setRotationPoint(5.0F, 3.0F, 2.0F);
        this.horseLeftSaddleRope = new ModelRenderer(this, 70, 0);
        this.horseLeftSaddleRope.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
        this.horseLeftSaddleRope.setRotationPoint(5.0F, 3.0F, 2.0F);
        this.horseRightSaddleMetal = new ModelRenderer(this, 74, 4);
        this.horseRightSaddleMetal.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
        this.horseRightSaddleMetal.setRotationPoint(-5.0F, 3.0F, 2.0F);
        this.horseRightSaddleRope = new ModelRenderer(this, 80, 0);
        this.horseRightSaddleRope.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
        this.horseRightSaddleRope.setRotationPoint(-5.0F, 3.0F, 2.0F);
        this.horseLeftFaceMetal = new ModelRenderer(this, 74, 13);
        this.horseLeftFaceMetal.addBox(1.5F, -8.0F, -4.0F, 1, 2, 2);
        this.horseLeftFaceMetal.setRotationPoint(0.0F, 4.0F, -10.0F);
        this.setBoxRotation(this.horseLeftFaceMetal, 0.5235988F, 0.0F, 0.0F);
        this.horseRightFaceMetal = new ModelRenderer(this, 74, 13);
        this.horseRightFaceMetal.addBox(-2.5F, -8.0F, -4.0F, 1, 2, 2);
        this.horseRightFaceMetal.setRotationPoint(0.0F, 4.0F, -10.0F);
        this.setBoxRotation(this.horseRightFaceMetal, 0.5235988F, 0.0F, 0.0F);
        this.horseLeftRein = new ModelRenderer(this, 44, 10);
        this.horseLeftRein.addBox(2.6F, -6.0F, -6.0F, 0, 3, 16);
        this.horseLeftRein.setRotationPoint(0.0F, 4.0F, -10.0F);
        this.horseRightRein = new ModelRenderer(this, 44, 5);
        this.horseRightRein.addBox(-2.6F, -6.0F, -6.0F, 0, 3, 16);
        this.horseRightRein.setRotationPoint(0.0F, 4.0F, -10.0F);
        this.mane = new ModelRenderer(this, 58, 0);
        this.mane.addBox(-1.0F, -11.5F, 5.0F, 2, 16, 4);
        this.mane.setRotationPoint(0.0F, 4.0F, -10.0F);
        this.setBoxRotation(this.mane, 0.5235988F, 0.0F, 0.0F);
        this.horseFaceRopes = new ModelRenderer(this, 80, 12);
        this.horseFaceRopes.addBox(-2.5F, -10.1F, -7.0F, 5, 5, 12, 0.2F);
        this.horseFaceRopes.setRotationPoint(0.0F, 4.0F, -10.0F);
        this.setBoxRotation(this.horseFaceRopes, 0.5235988F, 0.0F, 0.0F);
    }
    
    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float f5)
    {
    	EntityPinkUnicorn entityhorse = (EntityPinkUnicorn)par1Entity;
        float f6 = entityhorse.getGrassEatingAmount(0.0F);
        boolean flag = entityhorse.isAdultHorse();
        boolean flag1 = flag && entityhorse.isHorseSaddled();
        float f7 = entityhorse.getHorseSize();
        boolean flag4 = entityhorse.riddenByEntity != null;

        if (flag1)
        {
            this.horseFaceRopes.render(f5);
            this.horseSaddleBottom.render(f5);
            this.horseSaddleFront.render(f5);
            this.horseSaddleBack.render(f5);
            this.horseLeftSaddleRope.render(f5);
            this.horseLeftSaddleMetal.render(f5);
            this.horseRightSaddleRope.render(f5);
            this.horseRightSaddleMetal.render(f5);
            this.horseLeftFaceMetal.render(f5);
            this.horseRightFaceMetal.render(f5);

            if (flag4)
            {
                this.horseLeftRein.render(f5);
                this.horseRightRein.render(f5);
            }
        }

        if (!flag)
        {
            GL11.glPushMatrix();
            GL11.glScalef(f7, 0.5F + f7 * 0.5F, f7);
            GL11.glTranslatef(0.0F, 0.95F * (1.0F - f7), 0.0F);
        }

        this.backLeftLeg.render(f5);
        this.backLeftShin.render(f5);
        this.backLeftHoof.render(f5);
        this.backRightLeg.render(f5);
        this.backRightShin.render(f5);
        this.backRightHoof.render(f5);
        this.frontLeftLeg.render(f5);
        this.frontLeftShin.render(f5);
        this.frontLeftHoof.render(f5);
        this.frontRightLeg.render(f5);
        this.frontRightShin.render(f5);
        this.frontRightHoof.render(f5);

        if (!flag)
        {
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(f7, f7, f7);
            GL11.glTranslatef(0.0F, 1.35F * (1.0F - f7), 0.0F);
        }

        this.body.render(f5);
        this.tailBase.render(f5);
        this.tailMiddle.render(f5);
        this.tailTip.render(f5);
        this.neck.render(f5);
        this.mane.render(f5);

        if (!flag)
        {
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            float f8 = 0.5F + f7 * f7 * 0.5F;
            GL11.glScalef(f8, f8, f8);

            if (f6 <= 0.0F)
            {
                GL11.glTranslatef(0.0F, 1.35F * (1.0F - f7), 0.0F);
            }
            else
            {
                GL11.glTranslatef(0.0F, 0.9F * (1.0F - f7) * f6 + 1.35F * (1.0F - f7) * (1.0F - f6), 0.15F * (1.0F - f7) * f6);
            }
        }
        
        this.horseLeftEar.render(f5);
        this.horseRightEar.render(f5);

//        glPushMatrix();
//		glEnable(GL_BLEND);
//		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
//		Gem.render(f5);
		
//        this.horn.render(f5);
//        this.horn2.render(f5);
        this.head.render(f5);

//		glDisable(GL_BLEND);
//		glPopMatrix();
        
        if (!flag)
        {
            GL11.glPopMatrix();
        }
    }

    /**
     * Sets the rotations for a ModelRenderer in the ModelHorse class.
     */
    private void setBoxRotation(ModelRenderer par1ModelRenderer, float par2, float par3, float par4)
    {
        par1ModelRenderer.rotateAngleX = par2;
        par1ModelRenderer.rotateAngleY = par3;
        par1ModelRenderer.rotateAngleZ = par4;
    }

    /**
     * Fixes and offsets a rotation in the ModelHorse class.
     */
    private float updateHorseRotation(float par1, float par2, float par3)
    {
        float f3;

        for (f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F)
        {
            ;
        }

        while (f3 >= 180.0F)
        {
            f3 -= 360.0F;
        }

        return par1 + par3 * f3;
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        super.setLivingAnimations(par1EntityLivingBase, par2, par3, par4);
        float f3 = this.updateHorseRotation(par1EntityLivingBase.prevRenderYawOffset, par1EntityLivingBase.renderYawOffset, par4);
        float f4 = this.updateHorseRotation(par1EntityLivingBase.prevRotationYawHead, par1EntityLivingBase.rotationYawHead, par4);
        float f5 = par1EntityLivingBase.prevRotationPitch + (par1EntityLivingBase.rotationPitch - par1EntityLivingBase.prevRotationPitch) * par4;
        float f6 = f4 - f3;
        float f7 = f5 / (180F / (float)Math.PI);

        if (f6 > 20.0F)
        {
            f6 = 20.0F;
        }

        if (f6 < -20.0F)
        {
            f6 = -20.0F;
        }

        if (par3 > 0.2F)
        {
            f7 += MathHelper.cos(par2 * 0.4F) * 0.15F * par3;
        }

        EntityPinkUnicorn entityhorse = (EntityPinkUnicorn)par1EntityLivingBase;
        float f8 = entityhorse.getGrassEatingAmount(par4);
        float f9 = entityhorse.getRearingAmount(par4);
        float f10 = 1.0F - f9;
        float f11 = entityhorse.getMouthOpennessAmount(par4);
        boolean flag = entityhorse.field_110278_bp != 0;
        boolean flag1 = entityhorse.isHorseSaddled();
        boolean flag2 = entityhorse.riddenByEntity != null;
        float f12 = (float)par1EntityLivingBase.ticksExisted + par4;
        float f13 = MathHelper.cos(par2 * 0.6662F + (float)Math.PI);
        float f14 = f13 * 0.8F * par3;
        this.head.rotationPointY = 4.0F;
        this.head.rotationPointZ = -10.0F;
        this.tailBase.rotationPointY = 3.0F;
        this.tailMiddle.rotationPointZ = 14.0F;
//        this.muleRightChest.rotationPointY = 3.0F;
//        this.muleRightChest.rotationPointZ = 10.0F;
        this.body.rotateAngleX = 0.0F;
        this.head.rotateAngleX = 0.5235988F + f7;
        this.head.rotateAngleY = f6 / (180F / (float)Math.PI);
        this.head.rotateAngleX = f9 * (0.2617994F + f7) + f8 * 2.18166F + (1.0F - Math.max(f9, f8)) * this.head.rotateAngleX;
        this.head.rotateAngleY = f9 * (f6 / (180F / (float)Math.PI)) + (1.0F - Math.max(f9, f8)) * this.head.rotateAngleY;
        this.head.rotationPointY = f9 * -6.0F + f8 * 11.0F + (1.0F - Math.max(f9, f8)) * this.head.rotationPointY;
        this.head.rotationPointZ = f9 * -1.0F + f8 * -10.0F + (1.0F - Math.max(f9, f8)) * this.head.rotationPointZ;
        this.tailBase.rotationPointY = f9 * 9.0F + f10 * this.tailBase.rotationPointY;
        this.tailMiddle.rotationPointZ = f9 * 18.0F + f10 * this.tailMiddle.rotationPointZ;
//        this.muleRightChest.rotationPointY = f9 * 5.5F + f10 * this.muleRightChest.rotationPointY;
//        this.muleRightChest.rotationPointZ = f9 * 15.0F + f10 * this.muleRightChest.rotationPointZ;
        this.body.rotateAngleX = f9 * -((float)Math.PI / 4F) + f10 * this.body.rotateAngleX;
        

//        this.Gem.rotateAngleX = this.head.rotateAngleX;
//        this.Gem.rotateAngleY = this.head.rotateAngleY;
//        this.Gem.rotateAngleZ = this.head.rotateAngleZ;
//
//        this.horn.rotateAngleX = this.head.rotateAngleX;
//        this.horn.rotateAngleY = this.head.rotateAngleY;
//        this.horn.rotateAngleZ = this.head.rotateAngleZ;
//
//        this.horn2.rotateAngleX = this.head.rotateAngleX;
//        this.horn2.rotateAngleY = this.head.rotateAngleY;
//        this.horn2.rotateAngleZ = this.head.rotateAngleZ;
        
        
        this.horseLeftEar.rotationPointY = this.head.rotationPointY;
        this.horseRightEar.rotationPointY = this.head.rotationPointY;
        this.neck.rotationPointY = this.head.rotationPointY;
        this.mouthTop.rotationPointY = 0.02F;
        this.mouthBottom.rotationPointY = 0.0F;
        this.mane.rotationPointY = this.head.rotationPointY;
        this.horseLeftEar.rotationPointZ = this.head.rotationPointZ;
        this.horseRightEar.rotationPointZ = this.head.rotationPointZ;
        this.neck.rotationPointZ = this.head.rotationPointZ;
        this.mouthTop.rotationPointZ = 0.02F - f11 * 1.0F;
        this.mouthBottom.rotationPointZ = 0.0F + f11 * 1.0F;
        this.mane.rotationPointZ = this.head.rotationPointZ;
        this.horseLeftEar.rotateAngleX = this.head.rotateAngleX;
        this.horseRightEar.rotateAngleX = this.head.rotateAngleX;
        this.neck.rotateAngleX = this.head.rotateAngleX;
        this.mouthTop.rotateAngleX = 0.0F - 0.09424778F * f11;
        this.mouthBottom.rotateAngleX = 0.0F + 0.15707964F * f11;
        this.mane.rotateAngleX = this.head.rotateAngleX;
        this.horseLeftEar.rotateAngleY = this.head.rotateAngleY;
        this.horseRightEar.rotateAngleY = this.head.rotateAngleY;
        this.neck.rotateAngleY = this.head.rotateAngleY;
        this.mouthTop.rotateAngleY = 0.0F;
        this.mouthBottom.rotateAngleY = 0.0F;
        this.mane.rotateAngleY = this.head.rotateAngleY;
//        this.muleLeftChest.rotateAngleX = f14 / 5.0F;
//        this.muleRightChest.rotateAngleX = -f14 / 5.0F;
        float f15 = ((float)Math.PI / 2F);
        float f16 = ((float)Math.PI * 3F / 2F);
        float f17 = -1.0471976F;
        float f18 = 0.2617994F * f9;
        float f19 = MathHelper.cos(f12 * 0.6F + (float)Math.PI);
        this.frontLeftLeg.rotationPointY = -2.0F * f9 + 9.0F * f10;
        this.frontLeftLeg.rotationPointZ = -2.0F * f9 + -8.0F * f10;
        this.frontRightLeg.rotationPointY = this.frontLeftLeg.rotationPointY;
        this.frontRightLeg.rotationPointZ = this.frontLeftLeg.rotationPointZ;
        this.backLeftShin.rotationPointY = this.backLeftLeg.rotationPointY + MathHelper.sin(((float)Math.PI / 2F) + f18 + f10 * -f13 * 0.5F * par3) * 7.0F;
        this.backLeftShin.rotationPointZ = this.backLeftLeg.rotationPointZ + MathHelper.cos(((float)Math.PI * 3F / 2F) + f18 + f10 * -f13 * 0.5F * par3) * 7.0F;
        this.backRightShin.rotationPointY = this.backRightLeg.rotationPointY + MathHelper.sin(((float)Math.PI / 2F) + f18 + f10 * f13 * 0.5F * par3) * 7.0F;
        this.backRightShin.rotationPointZ = this.backRightLeg.rotationPointZ + MathHelper.cos(((float)Math.PI * 3F / 2F) + f18 + f10 * f13 * 0.5F * par3) * 7.0F;
        float f20 = (-1.0471976F + f19) * f9 + f14 * f10;
        float f21 = (-1.0471976F + -f19) * f9 + -f14 * f10;
        this.frontLeftShin.rotationPointY = this.frontLeftLeg.rotationPointY + MathHelper.sin(((float)Math.PI / 2F) + f20) * 7.0F;
        this.frontLeftShin.rotationPointZ = this.frontLeftLeg.rotationPointZ + MathHelper.cos(((float)Math.PI * 3F / 2F) + f20) * 7.0F;
        this.frontRightShin.rotationPointY = this.frontRightLeg.rotationPointY + MathHelper.sin(((float)Math.PI / 2F) + f21) * 7.0F;
        this.frontRightShin.rotationPointZ = this.frontRightLeg.rotationPointZ + MathHelper.cos(((float)Math.PI * 3F / 2F) + f21) * 7.0F;
        this.backLeftLeg.rotateAngleX = f18 + -f13 * 0.5F * par3 * f10;
        this.backLeftShin.rotateAngleX = -0.08726646F * f9 + (-f13 * 0.5F * par3 - Math.max(0.0F, f13 * 0.5F * par3)) * f10;
        this.backLeftHoof.rotateAngleX = this.backLeftShin.rotateAngleX;
        this.backRightLeg.rotateAngleX = f18 + f13 * 0.5F * par3 * f10;
        this.backRightShin.rotateAngleX = -0.08726646F * f9 + (f13 * 0.5F * par3 - Math.max(0.0F, -f13 * 0.5F * par3)) * f10;
        this.backRightHoof.rotateAngleX = this.backRightShin.rotateAngleX;
        this.frontLeftLeg.rotateAngleX = f20;
        this.frontLeftShin.rotateAngleX = (this.frontLeftLeg.rotateAngleX + (float)Math.PI * Math.max(0.0F, 0.2F + f19 * 0.2F)) * f9 + (f14 + Math.max(0.0F, f13 * 0.5F * par3)) * f10;
        this.frontLeftHoof.rotateAngleX = this.frontLeftShin.rotateAngleX;
        this.frontRightLeg.rotateAngleX = f21;
        this.frontRightShin.rotateAngleX = (this.frontRightLeg.rotateAngleX + (float)Math.PI * Math.max(0.0F, 0.2F - f19 * 0.2F)) * f9 + (-f14 + Math.max(0.0F, -f13 * 0.5F * par3)) * f10;
        this.frontRightHoof.rotateAngleX = this.frontRightShin.rotateAngleX;
        this.backLeftHoof.rotationPointY = this.backLeftShin.rotationPointY;
        this.backLeftHoof.rotationPointZ = this.backLeftShin.rotationPointZ;
        this.backRightHoof.rotationPointY = this.backRightShin.rotationPointY;
        this.backRightHoof.rotationPointZ = this.backRightShin.rotationPointZ;
        this.frontLeftHoof.rotationPointY = this.frontLeftShin.rotationPointY;
        this.frontLeftHoof.rotationPointZ = this.frontLeftShin.rotationPointZ;
        this.frontRightHoof.rotationPointY = this.frontRightShin.rotationPointY;
        this.frontRightHoof.rotationPointZ = this.frontRightShin.rotationPointZ;

        if (flag1)
        {
            this.horseSaddleBottom.rotationPointY = f9 * 0.5F + f10 * 2.0F;
            this.horseSaddleBottom.rotationPointZ = f9 * 11.0F + f10 * 2.0F;
            this.horseSaddleFront.rotationPointY = this.horseSaddleBottom.rotationPointY;
            this.horseSaddleBack.rotationPointY = this.horseSaddleBottom.rotationPointY;
            this.horseLeftSaddleRope.rotationPointY = this.horseSaddleBottom.rotationPointY;
            this.horseRightSaddleRope.rotationPointY = this.horseSaddleBottom.rotationPointY;
            this.horseLeftSaddleMetal.rotationPointY = this.horseSaddleBottom.rotationPointY;
            this.horseRightSaddleMetal.rotationPointY = this.horseSaddleBottom.rotationPointY;
//            this.muleLeftChest.rotationPointY = this.muleRightChest.rotationPointY;
            this.horseSaddleFront.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
            this.horseSaddleBack.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
            this.horseLeftSaddleRope.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
            this.horseRightSaddleRope.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
            this.horseLeftSaddleMetal.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
            this.horseRightSaddleMetal.rotationPointZ = this.horseSaddleBottom.rotationPointZ;
//            this.muleLeftChest.rotationPointZ = this.muleRightChest.rotationPointZ;
            this.horseSaddleBottom.rotateAngleX = this.body.rotateAngleX;
            this.horseSaddleFront.rotateAngleX = this.body.rotateAngleX;
            this.horseSaddleBack.rotateAngleX = this.body.rotateAngleX;
            this.horseLeftRein.rotationPointY = this.head.rotationPointY;
            this.horseRightRein.rotationPointY = this.head.rotationPointY;
            this.horseFaceRopes.rotationPointY = this.head.rotationPointY;
            this.horseLeftFaceMetal.rotationPointY = this.head.rotationPointY;
            this.horseRightFaceMetal.rotationPointY = this.head.rotationPointY;
            this.horseLeftRein.rotationPointZ = this.head.rotationPointZ;
            this.horseRightRein.rotationPointZ = this.head.rotationPointZ;
            this.horseFaceRopes.rotationPointZ = this.head.rotationPointZ;
            this.horseLeftFaceMetal.rotationPointZ = this.head.rotationPointZ;
            this.horseRightFaceMetal.rotationPointZ = this.head.rotationPointZ;
            this.horseLeftRein.rotateAngleX = f7;
            this.horseRightRein.rotateAngleX = f7;
            this.horseFaceRopes.rotateAngleX = this.head.rotateAngleX;
            this.horseLeftFaceMetal.rotateAngleX = this.head.rotateAngleX;
            this.horseRightFaceMetal.rotateAngleX = this.head.rotateAngleX;
            this.horseFaceRopes.rotateAngleY = this.head.rotateAngleY;
            this.horseLeftFaceMetal.rotateAngleY = this.head.rotateAngleY;
            this.horseLeftRein.rotateAngleY = this.head.rotateAngleY;
            this.horseRightFaceMetal.rotateAngleY = this.head.rotateAngleY;
            this.horseRightRein.rotateAngleY = this.head.rotateAngleY;

            if (flag2)
            {
                this.horseLeftSaddleRope.rotateAngleX = -1.0471976F;
                this.horseLeftSaddleMetal.rotateAngleX = -1.0471976F;
                this.horseRightSaddleRope.rotateAngleX = -1.0471976F;
                this.horseRightSaddleMetal.rotateAngleX = -1.0471976F;
                this.horseLeftSaddleRope.rotateAngleZ = 0.0F;
                this.horseLeftSaddleMetal.rotateAngleZ = 0.0F;
                this.horseRightSaddleRope.rotateAngleZ = 0.0F;
                this.horseRightSaddleMetal.rotateAngleZ = 0.0F;
            }
            else
            {
                this.horseLeftSaddleRope.rotateAngleX = f14 / 3.0F;
                this.horseLeftSaddleMetal.rotateAngleX = f14 / 3.0F;
                this.horseRightSaddleRope.rotateAngleX = f14 / 3.0F;
                this.horseRightSaddleMetal.rotateAngleX = f14 / 3.0F;
                this.horseLeftSaddleRope.rotateAngleZ = f14 / 5.0F;
                this.horseLeftSaddleMetal.rotateAngleZ = f14 / 5.0F;
                this.horseRightSaddleRope.rotateAngleZ = -f14 / 5.0F;
                this.horseRightSaddleMetal.rotateAngleZ = -f14 / 5.0F;
            }
        }

        f15 = -1.3089F + par3 * 1.5F;

        if (f15 > 0.0F)
        {
            f15 = 0.0F;
        }

        if (flag)
        {
            this.tailBase.rotateAngleY = MathHelper.cos(f12 * 0.7F);
            f15 = 0.0F;
        }
        else
        {
            this.tailBase.rotateAngleY = 0.0F;
        }

        this.tailMiddle.rotateAngleY = this.tailBase.rotateAngleY;
        this.tailTip.rotateAngleY = this.tailBase.rotateAngleY;
        this.tailMiddle.rotationPointY = this.tailBase.rotationPointY;
        this.tailTip.rotationPointY = this.tailBase.rotationPointY;
        this.tailMiddle.rotationPointZ = this.tailBase.rotationPointZ;
        this.tailTip.rotationPointZ = this.tailBase.rotationPointZ;
        this.tailBase.rotateAngleX = f15;
        this.tailMiddle.rotateAngleX = f15;
        this.tailTip.rotateAngleX = -0.2618F + f15;
    }
}