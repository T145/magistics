package hu.hundevelopers.elysium.model;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.entity.EntityHero;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelHero extends ModelBiped
{
	// fields
	ModelRenderer head;
	ModelRenderer rightarmskinny;
	ModelRenderer leftarmskinny;
	ModelRenderer leftarm;
	ModelRenderer rightarm;

	ModelRenderer leftarm_accessory;
	ModelRenderer rightarm_accessory;
	
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body_male;
	ModelRenderer body_femaletop;
	ModelRenderer body_femalemid;
	ModelRenderer body_femalelower;
	ModelRenderer breasts;
	ModelRenderer belt;
	ModelRenderer belt_buckle;
	ModelRenderer leftarmshoulder;
	ModelRenderer rightarmshoulder;
	ModelRenderer hair;
	ModelRenderer beards;

	public ModelHero() {
		textureWidth = 64;
		textureHeight = 128;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(64, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		rightarmskinny = new ModelRenderer(this, 40, 32);
		rightarmskinny.addBox(-3F, -2F, -1.5F, 3, 12, 3);
		rightarmskinny.setRotationPoint(-4F, 2F, 0F);
		rightarmskinny.setTextureSize(64, 128);
		rightarmskinny.mirror = true;
		setRotation(rightarmskinny, 0F, 0F, 0F);
		leftarmskinny = new ModelRenderer(this, 40, 32);
		leftarmskinny.mirror = true;
		leftarmskinny.addBox(-1F, -2F, -1.5F, 3, 12, 3);
		leftarmskinny.setRotationPoint(5F, 2F, 0F);
		leftarmskinny.setTextureSize(64, 128);
		leftarmskinny.mirror = true;
		setRotation(leftarmskinny, 0F, 0F, 0F);
		leftarmskinny.mirror = false;
		
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.mirror = true;
		leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
		leftarm.setRotationPoint(5F, 2F, 0F);
		leftarm.setTextureSize(64, 128);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		leftarm.mirror = false;
		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
		rightarm.setRotationPoint(-5F, 2F, 0F);
		rightarm.setTextureSize(64, 128);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);

		
		leftarm_accessory = new ModelRenderer(this, 40, 16);
		leftarm_accessory.mirror = true;
		leftarm_accessory.addBox(-1F, -2F, -2F, 4, 12, 4);
		leftarm_accessory.setRotationPoint(5F, 2F, 0F);
		leftarm_accessory.setTextureSize(64, 128);
		leftarm_accessory.mirror = true;
		setRotation(leftarm_accessory, 0F, 0F, 0F);
		leftarm_accessory.mirror = false;
		rightarm_accessory = new ModelRenderer(this, 40, 16);
		rightarm_accessory.addBox(-3F, -2F, -2F, 4, 12, 4);
		rightarm_accessory.setRotationPoint(-5F, 2F, 0F);
		rightarm_accessory.setTextureSize(64, 128);
		rightarm_accessory.mirror = true;
		setRotation(rightarm_accessory, 0F, 0F, 0F);
		
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(-2F, 12F, 0F);
		rightleg.setTextureSize(64, 128);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.mirror = true;
		leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(2F, 12F, 0F);
		leftleg.setTextureSize(64, 128);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
		leftleg.mirror = false;
		body_male = new ModelRenderer(this, 16, 16);
		body_male.addBox(-4F, 0F, -2F, 8, 12, 4);
		body_male.setRotationPoint(0F, 0F, 0F);
		body_male.setTextureSize(64, 128);
		body_male.mirror = true;
		setRotation(body_male, 0F, 0F, 0F);
		body_femaletop = new ModelRenderer(this, 16, 32);
		body_femaletop.addBox(-4F, 0F, -2F, 8, 5, 4);
		body_femaletop.setRotationPoint(0F, 0F, 0F);
		body_femaletop.setTextureSize(64, 128);
		body_femaletop.mirror = true;
		setRotation(body_femaletop, 0F, 0F, 0F);
		body_femalemid = new ModelRenderer(this, 16, 41);
		body_femalemid.addBox(-3.5F, 5F, -1.5F, 7, 4, 3);
		body_femalemid.setRotationPoint(0F, 0F, 0F);
		body_femalemid.setTextureSize(64, 128);
		body_femalemid.mirror = true;
		setRotation(body_femalemid, 0F, 0F, 0F);
		body_femalelower = new ModelRenderer(this, 16, 48);
		body_femalelower.addBox(-4F, 9F, -2F, 8, 3, 4);
		body_femalelower.setRotationPoint(0F, 0F, 0F);
		body_femalelower.setTextureSize(64, 128);
		body_femalelower.mirror = true;
		setRotation(body_femalelower, 0F, 0F, 0F);
		breasts = new ModelRenderer(this, 16, 55);
		breasts.addBox(-3.5F, 0F, -6F, 7, 3, 3);
		breasts.setRotationPoint(0F, 0F, 0F);
		breasts.setTextureSize(64, 128);
		breasts.mirror = true;
		setRotation(breasts, 0.8726646F, 0F, 0F);
		belt = new ModelRenderer(this, 14, 61);
		belt.addBox(-4.5F, 9.5F, -2.5F, 9, 2, 5);
		belt.setRotationPoint(0F, 0F, 0F);
		belt.setTextureSize(64, 128);
		belt.mirror = true;
		setRotation(belt, 0F, 0F, 0F);
		belt_buckle = new ModelRenderer(this, 16, 68);
		belt_buckle.addBox(-1.5F, 9F, -3F, 3, 3, 1);
		belt_buckle.setRotationPoint(0F, 0F, 0F);
		belt_buckle.setTextureSize(64, 128);
		belt_buckle.mirror = true;
		setRotation(belt_buckle, 0F, 0F, 0F);
		leftarmshoulder = new ModelRenderer(this, 40, 8);
		leftarmshoulder.mirror = true;
		leftarmshoulder.addBox(-1.5F, -2.5F, -2.5F, 5, 3, 5);
		leftarmshoulder.setRotationPoint(5F, 2F, 0F);
		leftarmshoulder.setTextureSize(64, 128);
		leftarmshoulder.mirror = true;
		setRotation(leftarmshoulder, 0F, 0F, 0F);
		leftarmshoulder.mirror = false;
		rightarmshoulder = new ModelRenderer(this, 40, 8);
		rightarmshoulder.addBox(-3.5F, -2.5F, -2.5F, 5, 3, 5);
		rightarmshoulder.setRotationPoint(-5F, 2F, 0F);
		rightarmshoulder.setTextureSize(64, 128);
		rightarmshoulder.mirror = true;
		setRotation(rightarmshoulder, 0F, 0F, 0F);
		hair = new ModelRenderer(this, 0, 72);
		hair.addBox(-4.5F, -8.5F, -4.5F, 9, 14, 9);
		hair.setRotationPoint(0F, 0F, 0F);
		hair.setTextureSize(64, 128);
		hair.mirror = true;
		setRotation(hair, 0F, 0F, 0F);
		beards = new ModelRenderer(this, 0, 95);
		beards.addBox(-4.5F, -8.5F, -4.5F, 9, 14, 9);
		beards.setRotationPoint(0F, 0F, 0F);
		beards.setTextureSize(64, 128);
		beards.mirror = true;
		setRotation(beards, 0F, 0F, 0F);
		

		this.bipedBody.cubeList.clear();
		this.bipedCloak.cubeList.clear();
		this.bipedEars.cubeList.clear();
		this.bipedHead.cubeList.clear();
		this.bipedHeadwear.cubeList.clear();
		this.bipedLeftArm.cubeList.clear();
		this.bipedLeftLeg.cubeList.clear();
		this.bipedRightArm.cubeList.clear();
		this.bipedRightLeg.cubeList.clear();
		
	}
	
	private static final ResourceLocation TEXTURE_EYE[] = {
			new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_eye_1.png"),
			new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_eye_2.png"),
			new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_eye_3.png")
		};
	
	private static final ResourceLocation TEXTURE_HEAD[] = {
		new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_head_1.png"),
		new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_head_2.png"),
		new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_head_3.png")
	};
	private static final ResourceLocation TEXTURE_HAIR[] = {
		new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_hair_1.png"),
		new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_hair_2.png"),
		new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_hair_3.png")
	};
	private static final ResourceLocation TEXTURE_SKIN[] ={
		new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_skin_1.png"),
	};
	private static final ResourceLocation TEXTURE_SHIRT = new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_shirt.png");
	private static final ResourceLocation TEXTURE_JEANS = new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_jeans.png");
	private static final ResourceLocation TEXTURE_ACCESSORIES[] ={
		new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_accessories_1.png"),
		new ResourceLocation(Elysium.ID + ":textures/mobs/heroes/hero_accessories_2.png"),
	};

	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		EntityHero hero = (EntityHero) entity;
		
		renderSkin(hero, f5);
		renderHead(hero, f5);
		renderClothes(hero, f5);
		renderAccessories(hero, f5);
	}

    private void renderAccessories(EntityHero hero, float f5) {

    	glPushMatrix();
			glScalef(1.05F, 1.05F, 1.05F);
			glTranslatef(0, -0.0025F, 0);
	
			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_ACCESSORIES[hero.getTypeMetal()]);
			glColor(hero.getMetalColor());
			
			if(hero.getHasBracletLeft())
			{
				leftarm_accessory.render(f5);
			}
	
			if(hero.getHasBracletRight())
			{
				rightarm_accessory.render(f5);
			}
			
			if(hero.getHasBelt())
			{
				belt.render(f5);
				belt_buckle.render(f5);
			}
			if(hero.getHasShoulderArmor())
			{
				leftarmshoulder.render(f5);
				rightarmshoulder.render(f5);
			}
			
			glColor3f(1, 1, 1);
		glPopMatrix();
	}

	private void renderClothes(EntityHero hero, float f5)
    {
    	glPushMatrix();
			glScalef(1.05F, 1.05F, 1.05F);
			glTranslatef(0, -0.0025F, 0);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_SHIRT);
			glColor(hero.getShirtColor());
			
			if(hero.getIsMale())
			{
				body_male.render(f5);

				leftarm.render(f5);
				rightarm.render(f5);
			} else 
			{
				body_femaletop.render(f5);
				body_femalemid.render(f5);
				body_femalelower.render(f5);
				if(hero.getEquipmentInSlot(3) == null)
				{
					glPushMatrix();
					glScalef(1, 0.8F, 1);
						breasts.render(f5);
					glPopMatrix();
				}
				rightarmskinny.render(f5);
				leftarmskinny.render(f5);
			}
			
			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_JEANS);
			glColor(hero.getJeansColor());
			
			rightleg.render(f5);
			leftleg.render(f5);
			
			glColor3f(1, 1, 1);

		glPopMatrix();
	}

	private void renderHead(EntityHero hero, float f5)
    {
    	glPushMatrix();

			glScalef(1.05F, 1.05F, 1.05F);
//			glTranslatef(0, -0.0025F, 0);

			
			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_HEAD[hero.getTypeHead()]);
			glColor3f(1, 1, 1);
			head.render(f5);

			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_EYE[hero.getTypeHead()]);
			glColor(hero.getEyeColor());
			head.render(f5);

    		if(hero.getEquipmentInSlot(4) == null)
    		{

				Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_HAIR[hero.getTypeHair()]);
				glColor(hero.getHairColor());
				hair.render(f5);
				
				if(hero.getIsMale())
				{
					if(hero.getHasBeard())
					{
						glColor(hero.getHairColor());
						beards.render(f5);
					}
				}
    		}
			glColor3f(1, 1, 1);
		glPopMatrix();
	}

	private void renderSkin(EntityHero hero, float f5)
	{
		glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_SKIN[0]);
			
			glColor(hero.getSkinColor());

			head.render(f5);
	
			if(hero.getIsMale())
			{	
				body_male.render(f5);
				leftarm.render(f5);
				rightarm.render(f5);
			} else 
			{
				body_femaletop.render(f5);
				body_femalemid.render(f5);
				body_femalelower.render(f5);
			
				if(hero.getEquipmentInSlot(3) == null)
				{
					glPushMatrix();
					glScalef(1, 0.8F, 1);
						breasts.render(f5);
					glPopMatrix();
				}
				rightarmskinny.render(f5);
				leftarmskinny.render(f5);
			}
			
			rightleg.render(f5);
			leftleg.render(f5);

			glColor3f(1, 1, 1);

		glPopMatrix();
		
	}

	private void glColor(int color)
	{
		int red = (color >> 16) & 0xFF;
		int green = (color >> 8) & 0xFF;
		int blue = color & 0xFF;
		glColor3f(red/255F, green/255F, blue/255F);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		this.head.rotateAngleX = this.bipedHead.rotateAngleX;
		this.head.rotateAngleY = this.bipedHead.rotateAngleY;
		this.head.rotateAngleZ = this.bipedHead.rotateAngleZ;
		
		this.beards.rotateAngleX = this.bipedHead.rotateAngleX;
		this.beards.rotateAngleY = this.bipedHead.rotateAngleY;
		this.beards.rotateAngleZ = this.bipedHead.rotateAngleZ;
		
		this.hair.rotateAngleX = this.bipedHead.rotateAngleX;
		this.hair.rotateAngleY = this.bipedHead.rotateAngleY;
		this.hair.rotateAngleZ = this.bipedHead.rotateAngleZ;
		

		this.leftarm.rotateAngleX = this.leftarmskinny.rotateAngleX = this.leftarm_accessory.rotateAngleX = this.leftarm_accessory.rotateAngleX = this.bipedLeftArm.rotateAngleX;
		this.leftarm.rotateAngleY = this.leftarmskinny.rotateAngleY = this.leftarm_accessory.rotateAngleY = this.leftarm_accessory.rotateAngleY = this.bipedLeftArm.rotateAngleY;
		this.leftarm.rotateAngleZ = this.leftarmskinny.rotateAngleZ = this.leftarm_accessory.rotateAngleZ = this.leftarm_accessory.rotateAngleZ = this.bipedLeftArm.rotateAngleZ;

		this.rightarm.rotateAngleX = this.rightarmskinny.rotateAngleX = this.rightarm_accessory.rotateAngleX = this.rightarm_accessory.rotateAngleX = this.bipedRightArm.rotateAngleX;
		this.rightarm.rotateAngleY = this.rightarmskinny.rotateAngleY = this.rightarm_accessory.rotateAngleY = this.rightarm_accessory.rotateAngleY = this.bipedRightArm.rotateAngleY;
		this.rightarm.rotateAngleZ = this.rightarmskinny.rotateAngleZ = this.rightarm_accessory.rotateAngleZ = this.rightarm_accessory.rotateAngleZ = this.bipedRightArm.rotateAngleZ;

		this.body_femalelower.rotateAngleX = this.body_femalemid.rotateAngleX = this.body_femaletop.rotateAngleX = this.body_male.rotateAngleX = /*this.breasts.rotateAngleX =*/ this.bipedBody.rotateAngleX;
		this.body_femalelower.rotateAngleY = this.body_femalemid.rotateAngleY = this.body_femaletop.rotateAngleY = this.body_male.rotateAngleY = this.breasts.rotateAngleY = this.bipedBody.rotateAngleY;
		this.body_femalelower.rotateAngleZ = this.body_femalemid.rotateAngleZ = this.body_femaletop.rotateAngleZ = this.body_male.rotateAngleZ = this.breasts.rotateAngleZ = this.bipedBody.rotateAngleZ;

		this.leftleg.rotateAngleX = this.bipedLeftLeg.rotateAngleX;
		this.leftleg.rotateAngleY = this.bipedLeftLeg.rotateAngleY;
		this.leftleg.rotateAngleZ = this.bipedLeftLeg.rotateAngleZ;
		
		this.rightleg.rotateAngleX = this.bipedRightLeg.rotateAngleX;
		this.rightleg.rotateAngleY = this.bipedRightLeg.rotateAngleY;
		this.rightleg.rotateAngleZ = this.bipedRightLeg.rotateAngleZ;
		
	}
}
