package T145.magistics.client.renderers.pillars;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ENABLE_BIT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex3d;

import java.nio.FloatBuffer;
import java.util.Random;

//import me.dawars.CraftingPillars.api.CraftingPillarAPI;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import T145.magistics.client.lib.pillars.ExternalRenderer;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.pillars.BlockPillarTrash;
import T145.magistics.common.tiles.pillars.TilePillarTrash;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTrashPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	public ResourceLocation texture, frozenTexture;

	public static ModelBase model = new ModelBase() {};
	private ModelRenderer Icicle1A, Icicle1B, Icicle1C, Icicle2A, Icicle2B, Icicle2C, Icicle3A, Icicle3B, Icicle4A, Icicle4B, Icicle5A, Icicle5B, Icicle5C, Icicle6A, Icicle6B, Icicle6C, Icicle7A, Icicle7B, Icicle7C, Icicle8A, Icicle8B, Icicle8C, Icicle8D, Icicle9A, Icicle9B, Icicle10A, Icicle10B, Icicle10C, Icicle11A, Icicle11B, Icicle11C;

	public Random random = new Random();
	public ExternalRenderer itemRenderer = new ExternalRenderer(false, true), resultRenderer = new ExternalRenderer(true, true);
	public IModelCustom trash = AdvancedModelLoader.loadModel(new ResourceLocation("magistics:textures/models/pillars/pillar_trash.obj"));;

	public RenderTrashPillar(String modelTexture) {
		texture = new ResourceLocation("magistics:textures/models/pillars/" + modelTexture + ".png");
		renderPillar();
	}

	public RenderTrashPillar(String modelTexture, String frozenModelTexture) {
		texture = new ResourceLocation("magistics:textures/models/pillars/" + modelTexture + ".png");
		frozenTexture = new ResourceLocation("magistics:textures/models/pillars/" + frozenModelTexture + ".png");
		renderPillar();
	}

	public void renderPillar() {
		if (Magistics.proxy.winter && frozenTexture != null)
			texture = frozenTexture;

		model.textureWidth = 128;
		model.textureHeight = 64;

		if (Magistics.proxy.winter) {
			Icicle1A = new ModelRenderer(model, 122, 60);
			Icicle1A.addBox(0F, 0F, 0F, 1, 2, 2);
			Icicle1A.setRotationPoint(6F, 11F, -5F);
			Icicle1A.setTextureSize(128, 64);
			Icicle1A.mirror = true;
			setRotation(Icicle1A, 0F, 0F, 0F);
			Icicle1B = new ModelRenderer(model, 124, 58);
			Icicle1B.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle1B.setRotationPoint(6F, 11F, -3F);
			Icicle1B.setTextureSize(128, 64);
			Icicle1B.mirror = true;
			setRotation(Icicle1B, 0F, 0F, 0F);
			Icicle1C = new ModelRenderer(model, 124, 56);
			Icicle1C.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle1C.setRotationPoint(6F, 13F, -4F);
			Icicle1C.setTextureSize(128, 64);
			Icicle1C.mirror = true;
			setRotation(Icicle1C, 0F, 0F, 0F);
			Icicle2A = new ModelRenderer(model, 122, 50);
			Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
			Icicle2A.setRotationPoint(6F, 11F, 0F);
			Icicle2A.setTextureSize(128, 64);
			Icicle2A.mirror = true;
			setRotation(Icicle2A, 0F, 0F, 0F);
			Icicle2B = new ModelRenderer(model, 124, 47);
			Icicle2B.addBox(0F, 0F, 0F, 1, 2, 1);
			Icicle2B.setRotationPoint(6F, 13F, 0F);
			Icicle2B.setTextureSize(128, 64);
			Icicle2B.mirror = true;
			setRotation(Icicle2B, 0F, 0F, 0F);
			Icicle2C = new ModelRenderer(model, 124, 54);
			Icicle2C.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle2C.setRotationPoint(6F, 11F, -1F);
			Icicle2C.setTextureSize(128, 64);
			Icicle2C.mirror = true;
			setRotation(Icicle2C, 0F, 0F, 0F);
			Icicle3A = new ModelRenderer(model, 120, 43);
			Icicle3A.addBox(0F, 0F, 0F, 1, 1, 3);
			Icicle3A.setRotationPoint(6F, 11F, 3F);
			Icicle3A.setTextureSize(128, 64);
			Icicle3A.mirror = true;
			setRotation(Icicle3A, 0F, 0F, 0F);
			Icicle3B = new ModelRenderer(model, 124, 40);
			Icicle3B.addBox(0F, 0F, 0F, 1, 2, 1);
			Icicle3B.setRotationPoint(6F, 12F, 4F);
			Icicle3B.setTextureSize(128, 64);
			Icicle3B.mirror = true;
			setRotation(Icicle3B, 0F, 0F, 0F);
			Icicle4A = new ModelRenderer(model, 122, 38);
			Icicle4A.addBox(0F, 0F, 0F, 2, 1, 1);
			Icicle4A.setRotationPoint(3F, 11F, 6F);
			Icicle4A.setTextureSize(128, 64);
			Icicle4A.mirror = true;
			setRotation(Icicle4A, 0F, 0F, 0F);
			Icicle4B = new ModelRenderer(model, 124, 36);
			Icicle4B.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle4B.setRotationPoint(4F, 12F, 6F);
			Icicle4B.setTextureSize(128, 64);
			Icicle4B.mirror = true;
			setRotation(Icicle4B, 0F, 0F, 0F);
			Icicle5A = new ModelRenderer(model, 114, 61);
			Icicle5A.addBox(0F, 0F, 0F, 3, 2, 1);
			Icicle5A.setRotationPoint(-1F, 11F, 6F);
			Icicle5A.setTextureSize(128, 64);
			Icicle5A.mirror = true;
			setRotation(Icicle5A, 0F, 0F, 0F);
			Icicle5B = new ModelRenderer(model, 116, 59);
			Icicle5B.addBox(0F, 0F, 0F, 2, 1, 1);
			Icicle5B.setRotationPoint(-1F, 13F, 6F);
			Icicle5B.setTextureSize(128, 64);
			Icicle5B.mirror = true;
			setRotation(Icicle5B, 0F, 0F, 0F);
			Icicle5C = new ModelRenderer(model, 120, 56);
			Icicle5C.addBox(0F, 0F, 0F, 1, 2, 1);
			Icicle5C.setRotationPoint(0F, 14F, 6F);
			Icicle5C.setTextureSize(128, 64);
			Icicle5C.mirror = true;
			setRotation(Icicle5C, 0F, 0F, 0F);
			Icicle6A = new ModelRenderer(model, 114, 54);
			Icicle6A.addBox(0F, 0F, 0F, 4, 1, 1);
			Icicle6A.setRotationPoint(-5F, 11F, 6F);
			Icicle6A.setTextureSize(128, 64);
			Icicle6A.mirror = true;
			setRotation(Icicle6A, 0F, 0F, 0F);
			Icicle6B = new ModelRenderer(model, 116, 52);
			Icicle6B.addBox(0F, 0F, 0F, 2, 1, 1);
			Icicle6B.setRotationPoint(-4F, 12F, 6F);
			Icicle6B.setTextureSize(128, 64);
			Icicle6B.mirror = true;
			setRotation(Icicle6B, 0F, 0F, 0F);
			Icicle6C = new ModelRenderer(model, 118, 50);
			Icicle6C.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle6C.setRotationPoint(-4F, 13F, 6F);
			Icicle6C.setTextureSize(128, 64);
			Icicle6C.mirror = true;
			setRotation(Icicle6C, 0F, 0F, 0F);
			Icicle7A = new ModelRenderer(model, 104, 59);
			Icicle7A.addBox(0F, 0F, 0F, 1, 1, 4);
			Icicle7A.setRotationPoint(-7F, 11F, 1F);
			Icicle7A.setTextureSize(128, 64);
			Icicle7A.mirror = true;
			setRotation(Icicle7A, 0F, 0F, 0F);
			Icicle7B = new ModelRenderer(model, 114, 46);
			Icicle7B.addBox(0F, 0F, 0F, 1, 2, 2);
			Icicle7B.setRotationPoint(-7F, 12F, 2F);
			Icicle7B.setTextureSize(128, 64);
			Icicle7B.mirror = true;
			setRotation(Icicle7B, 0F, 0F, 0F);
			Icicle7C = new ModelRenderer(model, 116, 44);
			Icicle7C.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle7C.setRotationPoint(-7F, 14F, 2F);
			Icicle7C.setTextureSize(128, 64);
			Icicle7C.mirror = true;
			setRotation(Icicle7C, 0F, 0F, 0F);
			Icicle8A = new ModelRenderer(model, 104, 54);
			Icicle8A.addBox(0F, 0F, 0F, 1, 1, 4);
			Icicle8A.setRotationPoint(-7F, 11F, -5F);
			Icicle8A.setTextureSize(128, 64);
			Icicle8A.mirror = true;
			setRotation(Icicle8A, 0F, 0F, 0F);
			Icicle8B = new ModelRenderer(model, 106, 50);
			Icicle8B.addBox(0F, 0F, 0F, 1, 1, 3);
			Icicle8B.setRotationPoint(-7F, 12F, -4F);
			Icicle8B.setTextureSize(128, 64);
			Icicle8B.mirror = true;
			setRotation(Icicle8B, 0F, 0F, 0F);
			Icicle8C = new ModelRenderer(model, 108, 46);
			Icicle8C.addBox(0F, 0F, 0F, 1, 2, 2);
			Icicle8C.setRotationPoint(-7F, 13F, -4F);
			Icicle8C.setTextureSize(128, 64);
			Icicle8C.mirror = true;
			setRotation(Icicle8C, 0F, 0F, 0F);
			Icicle8D = new ModelRenderer(model, 112, 44);
			Icicle8D.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle8D.setRotationPoint(-7F, 15F, -3F);
			Icicle8D.setTextureSize(128, 64);
			Icicle8D.mirror = true;
			setRotation(Icicle8D, 0F, 0F, 0F);

			Icicle9A = new ModelRenderer(model, 122, 38);
			Icicle9A.addBox(0F, 0F, 0F, 2, 1, 1);
			Icicle9A.setRotationPoint(3F, 11F, -7F);
			Icicle9A.setTextureSize(128, 64);
			Icicle9A.mirror = true;
			setRotation(Icicle9A, 0F, 0F, 0F);
			Icicle9B = new ModelRenderer(model, 124, 36);
			Icicle9B.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle9B.setRotationPoint(4F, 12F, -7F);
			Icicle9B.setTextureSize(128, 64);
			Icicle9B.mirror = true;
			setRotation(Icicle9B, 0F, 0F, 0F);
			Icicle10A = new ModelRenderer(model, 114, 61);
			Icicle10A.addBox(0F, 0F, 0F, 3, 2, 1);
			Icicle10A.setRotationPoint(-1F, 11F, -7F);
			Icicle10A.setTextureSize(128, 64);
			Icicle10A.mirror = true;
			setRotation(Icicle10A, 0F, 0F, 0F);
			Icicle10B = new ModelRenderer(model, 116, 59);
			Icicle10B.addBox(0F, 0F, 0F, 2, 1, 1);
			Icicle10B.setRotationPoint(-1F, 13F, -7F);
			Icicle10B.setTextureSize(128, 64);
			Icicle10B.mirror = true;
			setRotation(Icicle10B, 0F, 0F, 0F);
			Icicle10C = new ModelRenderer(model, 120, 56);
			Icicle10C.addBox(0F, 0F, 0F, 1, 2, 1);
			Icicle10C.setRotationPoint(0F, 14F, -7F);
			Icicle10C.setTextureSize(128, 64);
			Icicle10C.mirror = true;
			setRotation(Icicle10C, 0F, 0F, 0F);
			Icicle11A = new ModelRenderer(model, 114, 54);
			Icicle11A.addBox(0F, 0F, 0F, 4, 1, 1);
			Icicle11A.setRotationPoint(-5F, 11F, -7F);
			Icicle11A.setTextureSize(128, 64);
			Icicle11A.mirror = true;
			setRotation(Icicle11A, 0F, 0F, 0F);
			Icicle11B = new ModelRenderer(model, 116, 52);
			Icicle11B.addBox(0F, 0F, 0F, 2, 1, 1);
			Icicle11B.setRotationPoint(-4F, 12F, -7F);
			Icicle11B.setTextureSize(128, 64);
			Icicle11B.mirror = true;
			setRotation(Icicle11B, 0F, 0F, 0F);
			Icicle11C = new ModelRenderer(model, 118, 50);
			Icicle11C.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle11C.setRotationPoint(-4F, 13F, -7F);
			Icicle11C.setTextureSize(128, 64);
			Icicle11C.mirror = true;
			setRotation(Icicle11C, 0F, 0F, 0F);
		}
	}

	public void render(float f) {
		if (Magistics.proxy.winter) {
			Icicle1A.render(f);
			Icicle1B.render(f);
			Icicle1C.render(f);
			Icicle2A.render(f);
			Icicle2B.render(f);
			Icicle2C.render(f);
			Icicle3A.render(f);
			Icicle3B.render(f);
			Icicle4A.render(f);
			Icicle4B.render(f);
			Icicle5A.render(f);
			Icicle5B.render(f);
			Icicle5C.render(f);
			Icicle6A.render(f);
			Icicle6B.render(f);
			Icicle6C.render(f);
			Icicle7A.render(f);
			Icicle7B.render(f);
			Icicle7C.render(f);
			Icicle8A.render(f);
			Icicle8B.render(f);
			Icicle8C.render(f);
			Icicle8D.render(f);

			Icicle9A.render(f);
			Icicle9B.render(f);
			Icicle10A.render(f);
			Icicle10B.render(f);
			Icicle10C.render(f);
			Icicle11A.render(f);
			Icicle11B.render(f);
			Icicle11C.render(f);
		}
	}

	public void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		glPushMatrix();
		TilePillarTrash workTile = (TilePillarTrash) tile;

		glTranslated(x, y + 1.5D, z);
		if (workTile.isOpen)
			renderEndPortal(0, -1.5F, 0);

		glTranslated(0.5D, 0, 0.5D);
		glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);
		glRotatef(180F, 1F, 0F, 0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		render(0.0625F);

		glPopMatrix();

		glPushMatrix();
		glTranslated(x + 0.5F, y, z + 0.5F);
		float scale = 0.0255F;
		glScalef(scale, scale, scale);
		FMLClientHandler.instance().getClient().renderEngine
		.bindTexture(texture);
		trash.renderPart("TrashPillar");
		glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

		if (workTile.isOpen)
			trash.renderPart("DoorsOpen");
		else
			trash.renderPart("DoorsClosed");

		glPopMatrix();
	}

	public static final ResourceLocation enderPortalEndSkyTextures = new ResourceLocation("textures/environment/end_sky.png"), endPortalTextures = new ResourceLocation("textures/entity/end_portal.png");
	public static final Random field_110644_e = new Random(31100L);
	FloatBuffer field_76908_a = GLAllocation.createDirectFloatBuffer(16);

	public void renderEndPortal(double x, double y, double z) {
		float f1 = (float) field_147501_a.field_147560_j, f2 = (float) field_147501_a.field_147561_k, f3 = (float) field_147501_a.field_147558_l;
		GL11.glDisable(GL11.GL_LIGHTING);
		field_110644_e.setSeed(31100L);
		float f4 = 11F / 16F;

		for (int i = 0; i < 16; i++) {
			GL11.glPushMatrix();
			float f5 = (float) (16 - i), f6 = 0.0625F, f7 = 1.0F / (f5 + 1.0F);

			if (i == 0) {
				bindTexture(enderPortalEndSkyTextures);
				f7 = 0.1F;
				f5 = 65.0F;
				f6 = 0.125F;
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}

			if (i == 1) {
				bindTexture(endPortalTextures);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				f6 = 0.5F;
			}

			float f8 = (float) (-(y + (double) f4));
			float f9 = f8 + ActiveRenderInfo.objectY;
			float f10 = f8 + f5 + ActiveRenderInfo.objectY;
			float f11 = f9 / f10;
			f11 += (float) (y + (double) f4);
			GL11.glTranslatef(f1, f11, f3);
			GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_Q, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
			GL11.glTexGen(GL11.GL_S, GL11.GL_OBJECT_PLANE, func_76907_a(1.0F, 0.0F, 0.0F, 0.0F));
			GL11.glTexGen(GL11.GL_T, GL11.GL_OBJECT_PLANE, func_76907_a(0.0F, 0.0F, 1.0F, 0.0F));
			GL11.glTexGen(GL11.GL_R, GL11.GL_OBJECT_PLANE, func_76907_a(0.0F, 0.0F, 0.0F, 1.0F));
			GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, func_76907_a(0.0F, 1.0F, 0.0F, 0.0F));
			GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_Q);
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			GL11.glTranslatef(0.0F, (float) (Minecraft.getSystemTime() % 700000L) / 700000.0F, 0.0F);
			GL11.glScalef(f6, f6, f6);
			GL11.glTranslatef(0.5F, 0.5F, 0.0F);
			GL11.glRotatef((float) (i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
			GL11.glTranslatef(-f1, -f3, -f2);
			f9 = f8 + ActiveRenderInfo.objectY;
			GL11.glTranslatef(ActiveRenderInfo.objectX * f5 / f9, ActiveRenderInfo.objectZ * f5 / f9, -f2);
			glBegin(GL_QUADS);
			f11 = field_110644_e.nextFloat() * 0.5F + 0.1F;
			float f12 = field_110644_e.nextFloat() * 0.5F + 0.4F;
			float f13 = field_110644_e.nextFloat() * 0.5F + 0.5F;

			if (i == 0) {
				f13 = 1.0F;
				f12 = 1.0F;
				f11 = 1.0F;
			}

			glColor4f(f11 * f7, f12 * f7, f13 * f7, 1.0F);
			glVertex3d(x + 3 / 16F, y + (double) f4, z + 3 / 16F);
			glVertex3d(x + 3 / 16F, y + (double) f4, z + 1.0D - 3 / 16F);
			glVertex3d(x + 1.0D - 3 / 16F, y + (double) f4, z + 1.0D - 3 / 16F);
			glVertex3d(x + 1.0D - 3 / 16F, y + (double) f4, z + 3 / 16F);

			glEnd();
			glColor4f(1, 1, 1, 1);
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_Q);
		GL11.glEnable(GL11.GL_LIGHTING);

	}

	public FloatBuffer func_76907_a(float par1, float par2, float par3, float par4) {
		field_76908_a.clear();
		field_76908_a.put(par1).put(par2).put(par3).put(par4);
		field_76908_a.flip();
		return field_76908_a;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		render(0.0625F);
		glRotatef(180F, 1F, 0F, 0F);

		glTranslated(0, -1.5F, 0);
		float scale = 0.0255F;
		glScalef(scale, scale, scale);

		trash.renderPart("TrashPillar");
		glPopAttrib();
		glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int i) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockPillarTrash.renderID;
	}
}