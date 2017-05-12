package T145.magistics.client.render.models.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelFlyingCarpet extends ModelBase {

	public ModelRenderer[] carpetSections = new ModelRenderer[7];
	public ModelRenderer[] carpetTassle = new ModelRenderer[4];

	public ModelFlyingCarpet() {
		textureWidth = 256;
		textureHeight = 128;
		carpetSections[0] = new ModelRenderer(this, 0, 0);
		carpetSections[0].addBox(-20.0F, 0.0F, 0.0F, 40, 2, 8);
		carpetSections[0].setRotationPoint(0.0F, 0.0F, 20.0F);
		carpetSections[0].setTextureSize(256, 128);
		carpetSections[0].mirror = true;
		carpetSections[1] = new ModelRenderer(this, 0, 10);
		carpetSections[1].addBox(-20.0F, 0.0F, 0.0F, 40, 2, 8);
		carpetSections[1].setRotationPoint(0.0F, 0.0F, 12.0F);
		carpetSections[1].setTextureSize(256, 128);
		carpetSections[1].mirror = true;
		carpetSections[2] = new ModelRenderer(this, 0, 20);
		carpetSections[2].addBox(-20.0F, 0.0F, 0.0F, 40, 2, 8);
		carpetSections[2].setRotationPoint(0.0F, 0.0F, 4.0F);
		carpetSections[2].setTextureSize(256, 128);
		carpetSections[2].mirror = true;
		carpetSections[3] = new ModelRenderer(this, 0, 30);
		carpetSections[3].addBox(-20.0F, 0.0F, 0.0F, 40, 2, 8);
		carpetSections[3].setRotationPoint(0.0F, 0.0F, -4.0F);
		carpetSections[3].setTextureSize(256, 128);
		carpetSections[3].mirror = true;
		carpetSections[4] = new ModelRenderer(this, 0, 40);
		carpetSections[4].addBox(-20.0F, 0.0F, 0.0F, 40, 2, 8);
		carpetSections[4].setRotationPoint(0.0F, 0.0F, -12.0F);
		carpetSections[4].setTextureSize(256, 128);
		carpetSections[4].mirror = true;
		carpetSections[5] = new ModelRenderer(this, 0, 50);
		carpetSections[5].addBox(-20.0F, 0.0F, 0.0F, 40, 2, 8);
		carpetSections[5].setRotationPoint(0.0F, 0.0F, -20.0F);
		carpetSections[5].setTextureSize(256, 128);
		carpetSections[5].mirror = true;
		carpetSections[6] = new ModelRenderer(this, 0, 60);
		carpetSections[6].addBox(-20.0F, 0.0F, 0.0F, 40, 2, 8);
		carpetSections[6].setRotationPoint(0.0F, 0.0F, -28.0F);
		carpetSections[6].setTextureSize(256, 128);
		carpetSections[6].mirror = true;
		carpetTassle[0] = new ModelRenderer(this, 0, 70);
		carpetTassle[0].addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		carpetTassle[0].setRotationPoint(20.0F, 1.0F, -28.0F);
		carpetTassle[0].setTextureSize(256, 128);
		carpetTassle[0].mirror = true;
		setRotation(carpetTassle[0], -0.7071F, 0.0F, 0.7071F);
		carpetTassle[1] = new ModelRenderer(this, 0, 70);
		carpetTassle[1].addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		carpetTassle[1].setRotationPoint(-20.0F, 1.0F, -28.0F);
		carpetTassle[1].setTextureSize(256, 128);
		carpetTassle[1].mirror = true;
		setRotation(carpetTassle[1], -0.7071F, 0.0F, 0.7071F);
		carpetTassle[2] = new ModelRenderer(this, 0, 70);
		carpetTassle[2].addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		carpetTassle[2].setRotationPoint(20.0F, 1.0F, 28.0F);
		carpetTassle[2].setTextureSize(256, 128);
		carpetTassle[2].mirror = true;
		setRotation(carpetTassle[2], -0.7071F, 0.0F, 0.7071F);
		carpetTassle[3] = new ModelRenderer(this, 0, 70);
		carpetTassle[3].addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		carpetTassle[3].setRotationPoint(-20.0F, 1.0F, 28.0F);
		carpetTassle[3].setTextureSize(256, 128);
		carpetTassle[3].mirror = true;
		setRotation(carpetTassle[3], -0.7071F, 0.0F, 0.7071F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void render() {
		float waveMod = Minecraft.getMinecraft().player.ticksExisted;

		for (int waveCount = 0; waveCount < 7; ++waveCount) {
			float wavePos = MathHelper.sin(waveMod / 4.0F) * 0.25F + 0.25F;

			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, wavePos, 0.0F);
			carpetSections[waveCount].render(0.0625F);
			GL11.glTranslatef(0.0F, -0.25F, 0.0F);
			GL11.glScalef(1.0F, 5.0F, 1.0F);

			if (waveCount == 6) {
				carpetTassle[0].render(0.0625F);
				carpetTassle[1].render(0.0625F);
			} else if (waveCount == 0) {
				carpetTassle[2].render(0.0625F);
				carpetTassle[3].render(0.0625F);
			}

			GL11.glPopMatrix();
			++waveMod;
		}
	}
}