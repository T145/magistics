package T145.magistics.client.renderers.craftingpillars;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ENABLE_BIT;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import T145.magistics.client.lib.craftingpillars.Blobs;
import T145.magistics.client.lib.craftingpillars.RenderingHelper;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.craftingpillars.BlockPillarTank;
import T145.magistics.common.tiles.craftingpillars.TilePillarTank;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTankPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	private ResourceLocation texture, frozenTexture;

	public static ModelBase model = new ModelBase() {};
	private ModelRenderer pillarbottom, pillartop, Corner1, Corner2, Corner3, Corner4, BottomTank, TopTank, GlassPane1, GlassPane2, GlassPane4, GlassPane3, Valve1, Valve2, Valve3, Valve4, Valve, BunnyTail1, BunnyTail2, BunnyTail3, BunnyEar1, BunnyEar2, Icicle1A, Icicle1B, Icicle1C, Icicle2A, Icicle2C, Icicle2B, Icicle3A, Icicle3B, Icicle3C, Icicle3D, Icicle4A, Icicle4B;
	private RenderingHelper.ItemRender resultRenderer = new RenderingHelper.ItemRender(false, false);

	public RenderTankPillar(String modelTexture) {
		texture = new ResourceLocation("craftingpillars:textures/models/" + modelTexture + ".png");
		renderPillar();
	}

	public RenderTankPillar(String modelTexture, String frozenModelTexture) {
		texture = new ResourceLocation("craftingpillars:textures/models/" + modelTexture + ".png");
		frozenTexture = new ResourceLocation("craftingpillars:textures/models/" + frozenModelTexture + ".png");
		renderPillar();
	}

	public void renderPillar() {
		if (Magistics.proxy.winter && frozenTexture != null)
			texture = frozenTexture;

		model.textureWidth = 128;
		model.textureHeight = 64;

		pillarbottom = new ModelRenderer(model, 0, 18);
		pillarbottom.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillarbottom.setRotationPoint(0F, 21F, 0F);
		pillarbottom.setTextureSize(128, 64);
		pillarbottom.mirror = true;
		setRotation(pillarbottom, 0F, 0F, 0F);
		pillartop = new ModelRenderer(model, 0, 18);
		pillartop.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillartop.setRotationPoint(0F, 10F, 0F);
		pillartop.setTextureSize(128, 64);
		pillartop.mirror = true;
		setRotation(pillartop, 0F, 0F, 0F);

		Corner1 = new ModelRenderer(model, 64, -2);
		Corner1.addBox(5F, 0F, 5F, 2, 10, 2);
		Corner1.setRotationPoint(0F, 11F, 0F);
		Corner1.setTextureSize(128, 64);
		Corner1.mirror = false;
		setRotation(Corner1, 0F, 0F, 0F);
		Corner2 = new ModelRenderer(model, 48, 8);
		Corner2.addBox(-7F, 0F, -7F, 2, 10, 2);
		Corner2.setRotationPoint(0F, 11F, 0F);
		Corner2.setTextureSize(128, 64);
		setRotation(Corner2, 0F, 0F, 0F);
		Corner2.mirror = false;
		Corner3 = new ModelRenderer(model, 56, -2);
		Corner3.addBox(5F, 0F, -7F, 2, 10, 2);
		Corner3.setRotationPoint(0F, 11F, 0F);
		Corner3.setTextureSize(128, 64);
		Corner3.mirror = false;
		setRotation(Corner3, 0F, 0F, 0F);
		Corner4 = new ModelRenderer(model, 48, -2);
		Corner4.addBox(-7F, 0F, 5F, 2, 10, 2);
		Corner4.setRotationPoint(0F, 11F, 0F);
		Corner4.setTextureSize(128, 64);
		Corner4.mirror = false;
		setRotation(Corner4, 0F, 0F, 0F);
		BottomTank = new ModelRenderer(model, 64, 36);
		BottomTank.addBox(-8F, 0F, -8F, 16, 2, 16);
		BottomTank.setRotationPoint(0F, 22F, 0F);
		BottomTank.setTextureSize(128, 64);
		setRotation(BottomTank, 0F, 0F, 0F);
		BottomTank.mirror = false;
		TopTank = new ModelRenderer(model, 64, 18);
		TopTank.addBox(-8F, 0F, -8F, 16, 2, 16);
		TopTank.setRotationPoint(0F, 8F, 0F);
		TopTank.setTextureSize(128, 64);
		TopTank.mirror = true;
		setRotation(TopTank, 0F, 0F, 0F);
		GlassPane1 = new ModelRenderer(model, 108, 54);
		GlassPane1.addBox(-5F, -4F, -6F, 10, 10, 0);
		GlassPane1.setRotationPoint(0F, 15F, 0F);
		GlassPane1.setTextureSize(128, 64);
		GlassPane1.mirror = true;
		setRotation(GlassPane1, 0F, 0F, 0F);
		GlassPane2 = new ModelRenderer(model, 108, 54);
		GlassPane2.addBox(-5F, -4F, 6F, 10, 10, 0);
		GlassPane2.setRotationPoint(0F, 15F, 0F);
		GlassPane2.setTextureSize(128, 64);
		GlassPane2.mirror = true;
		setRotation(GlassPane2, 0F, 0F, 0F);
		GlassPane4 = new ModelRenderer(model, 108, 54);
		GlassPane4.addBox(-5F, -4F, 6F, 10, 10, 0);
		GlassPane4.setRotationPoint(0F, 15F, 0F);
		GlassPane4.setTextureSize(128, 64);
		GlassPane4.mirror = true;
		setRotation(GlassPane4, 0F, 1.570796F, 0F);
		GlassPane3 = new ModelRenderer(model, 108, 54);
		GlassPane3.addBox(-5F, -4F, -6F, 10, 10, 0);
		GlassPane3.setRotationPoint(0F, 15F, 0F);
		GlassPane3.setTextureSize(128, 64);
		GlassPane3.mirror = true;
		setRotation(GlassPane3, 0F, 1.570796F, 0F);

		if (Magistics.proxy.easter) {
			BunnyTail1 = new ModelRenderer(model, 0, 35);
			BunnyTail1.addBox(0F, 0F, 0F, 2, 4, 2);
			BunnyTail1.setRotationPoint(-1F, 18F, 7F);
			BunnyTail1.setTextureSize(128, 64);
			BunnyTail1.mirror = true;
			setRotation(BunnyTail1, 0F, 0F, 0F);
			BunnyTail2 = new ModelRenderer(model, 0, 33);
			BunnyTail2.addBox(0F, 0F, 0F, 4, 2, 2);
			BunnyTail2.setRotationPoint(-2F, 19F, 7F);
			BunnyTail2.setTextureSize(128, 64);
			BunnyTail2.mirror = true;
			setRotation(BunnyTail2, 0F, 0F, 0F);
			BunnyTail3 = new ModelRenderer(model, 0, 36);
			BunnyTail3.addBox(0F, 0F, 0F, 2, 2, 4);
			BunnyTail3.setRotationPoint(-1F, 19F, 6F);
			BunnyTail3.setTextureSize(128, 64);
			BunnyTail3.mirror = true;
			setRotation(BunnyTail3, 0F, 0F, 0F);
			BunnyEar1 = new ModelRenderer(model, 1, 18);
			BunnyEar1.addBox(-1.5F, -9F, -1F, 3, 10, 1);
			BunnyEar1.setRotationPoint(3.5F, 9F, 9F);
			BunnyEar1.setTextureSize(128, 64);
			BunnyEar1.mirror = true;
			setRotation(BunnyEar1, 0F, 0F, 0F);
			BunnyEar2 = new ModelRenderer(model, 1, 18);
			BunnyEar2.addBox(-1.5F, -9F, -1F, 3, 10, 1);
			BunnyEar2.setRotationPoint(-3.5F, 9F, 9F);
			BunnyEar2.setTextureSize(128, 64);
			BunnyEar2.mirror = true;
			setRotation(BunnyEar2, 0F, 0F, 0F);
		}

		if (Magistics.proxy.winter) {
			Icicle1A = new ModelRenderer(model, 122, 38);
			Icicle1A.addBox(0F, 0F, 0F, 2, 1, 1);
			Icicle1A.setRotationPoint(6F, 10F, 7F);
			Icicle1A.setTextureSize(128, 64);
			Icicle1A.mirror = true;
			setRotation(Icicle1A, 0F, 0F, 0F);
			Icicle1B = new ModelRenderer(model, 122, 40);
			Icicle1B.addBox(0F, 0F, 0F, 1, 2, 1);
			Icicle1B.setRotationPoint(7F, 11F, 7F);
			Icicle1B.setTextureSize(128, 64);
			Icicle1B.mirror = true;
			setRotation(Icicle1B, 0F, 0F, 0F);
			Icicle1C = new ModelRenderer(model, 116, 52);
			Icicle1C.addBox(0F, 0F, 0F, 1, 1, 2);
			Icicle1C.setRotationPoint(7F, 10F, 5F);
			Icicle1C.setTextureSize(128, 64);
			Icicle1C.mirror = true;
			setRotation(Icicle1C, 0F, 0F, 0F);
			Icicle2A = new ModelRenderer(model, 122, 60);
			Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
			Icicle2A.setRotationPoint(7F, 10F, -7F);
			Icicle2A.setTextureSize(128, 64);
			Icicle2A.mirror = true;
			setRotation(Icicle2A, 0F, 0F, 0F);
			Icicle2B = new ModelRenderer(model, 122, 38);
			Icicle2B.addBox(0F, 0F, 0F, 2, 1, 1);
			Icicle2B.setRotationPoint(6F, 10F, -8F);
			Icicle2B.setTextureSize(128, 64);
			Icicle2B.mirror = true;
			setRotation(Icicle2B, 0F, 0F, 0F);
			Icicle2C = new ModelRenderer(model, 122, 44);
			Icicle2C.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle2C.setRotationPoint(7F, 12F, -7F);
			Icicle2C.setTextureSize(128, 64);
			Icicle2C.mirror = true;
			setRotation(Icicle2C, 0F, 0F, 0F);
			Icicle3A = new ModelRenderer(model, 106, 50);
			Icicle3A.addBox(0F, 0F, 0F, 1, 1, 3);
			Icicle3A.setRotationPoint(-8F, 10F, -8F);
			Icicle3A.setTextureSize(128, 64);
			Icicle3A.mirror = true;
			setRotation(Icicle3A, 0F, 0F, 0F);
			Icicle3B = new ModelRenderer(model, 101, 50);
			Icicle3B.addBox(0F, 0F, 0F, 1, 1, 2);
			Icicle3B.setRotationPoint(-8F, 11F, -8F);
			Icicle3B.setTextureSize(128, 64);
			Icicle3B.mirror = true;
			setRotation(Icicle3B, 0F, 0F, 0F);
			Icicle3C = new ModelRenderer(model, 106, 50);
			Icicle3C.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle3C.setRotationPoint(-7F, 10F, -8F);
			Icicle3C.setTextureSize(128, 64);
			Icicle3C.mirror = true;
			setRotation(Icicle3C, 0F, 0F, 0F);
			Icicle3D = new ModelRenderer(model, 106, 46);
			Icicle3D.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle3D.setRotationPoint(-8F, 12F, -8F);
			Icicle3D.setTextureSize(128, 64);
			Icicle3D.mirror = true;
			setRotation(Icicle3D, 0F, 0F, 0F);
			Icicle4A = new ModelRenderer(model, 122, 35);
			Icicle4A.addBox(0F, 0F, 0F, 1, 1, 1);
			Icicle4A.setRotationPoint(-8F, 10F, 7F);
			Icicle4A.setTextureSize(128, 64);
			Icicle4A.mirror = true;
			setRotation(Icicle4A, 0F, 0F, 0F);
			Icicle4B = new ModelRenderer(model, 117, 43);
			Icicle4B.addBox(0F, 0F, 0F, 1, 2, 1);
			Icicle4B.setRotationPoint(-7F, 10F, 7F);
			Icicle4B.setTextureSize(128, 64);
			Icicle4B.mirror = true;
			setRotation(Icicle4B, 0F, 0F, 0F);
		}
	}

	public void render(float f) {
		if (Magistics.proxy.winter) {
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
			Icicle1A.render(f);
			Icicle1B.render(f);
			Icicle1C.render(f);
			Icicle2A.render(f);
			Icicle2C.render(f);
			Icicle2B.render(f);
			Icicle3A.render(f);
			Icicle3B.render(f);
			Icicle3C.render(f);
			Icicle3D.render(f);
			Icicle4A.render(f);
			Icicle4B.render(f);
		}

		pillarbottom.render(f);
		pillartop.render(f);
		Corner1.render(f);
		Corner2.render(f);
		Corner3.render(f);
		Corner4.render(f);
		BottomTank.render(f);
		TopTank.render(f);

		GlassPane1.render(f);
		GlassPane2.render(f);
		GlassPane4.render(f);
		GlassPane3.render(f);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		glPushMatrix();
		glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		glRotatef(180F, 1F, 0F, 0F);

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		render(0.0625F);

		if (Magistics.proxy.easter) {
			glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

			f = 0.0625F;
			BunnyTail1.render(f);
			BunnyTail2.render(f);
			BunnyTail3.render(f);
			BunnyEar1.render(f);
			BunnyEar2.render(f);
		}
		glPopMatrix();

		TilePillarTank tank = ((TilePillarTank) tile);

		if (tank.showNum && !tank.empty) {
			glPushMatrix();
			glTranslated(x + 0.5D, y + 1, z + 0.5D);

			glDisable(GL_LIGHTING);
			RenderingHelper.renderFloatingTextWithBackground(0, 0.35F, 0, 0.3F, tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.getFluid().getName(), Color.WHITE.getRGB(), new Color(0F, 0F, 0F, 0.5F));
			RenderingHelper.renderFloatingTextWithBackground(0, 0.2F, 0, 0.2F, tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.amount + " Unit", Color.WHITE.getRGB(), new Color(0F, 0F, 0F, 0.5F));
			glEnable(GL_LIGHTING);
			glPopMatrix();

		}

		if (tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid == null || tank.empty)
			return;
		EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;
		if (player.getDistanceSq(tile.xCoord, tile.yCoord, tile.zCoord) < 128) {
			glPushMatrix();
			glTranslated(x, y, z);
			glPushAttrib(GL_ENABLE_BIT);
			glDisable(GL_LIGHTING);
			glTranslatef(0.005F, 0.005F, 0.005F);
			glScalef(0.99F, 0.99F, 0.99F);

			float[][][] field = Blobs.fieldStrength(tank.blobs);
			for (int i = 0; i < 16; i++)
				for (int j = 0; j < 16; j++)
					for (int k = 0; k < 16; k++)
						if ((int) field[i][j][k] > 0 && (i != 0 && (int) field[i - 1][j][k] != 0 && i != 15 && (int) field[i + 1][j][k] != 0 && j != 0 && (int) field[i][j - 1][k] != 0 && j != 15 && (int) field[i][j + 1][k] != 0 && k != 0 && (int) field[i][j][k - 1] != 0 && k != 15 && (int) field[i][j][k + 1] != 0))
							field[i][j][k] = 0F;

			FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationBlocksTexture);

			IIcon icon = tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.getFluid().getIcon();
			float xMin = icon.getMinU(), xMax = icon.getMaxU(), yMin = icon.getMinV(), yMax = icon.getMaxV(), iconSize = (xMax - xMin) / 16;

			glBegin(GL_QUADS);

			for (int i = 0; i < 16; i++)
				for (int j = 0; j < 16; j++)
					for (int k = 0; k < 16; k++)
						if ((int) field[i][j][k] > 0) {
							if (j == 15 || (int) field[i][j + 1][k] == 0) {
								glTexCoord2f(xMin + i * iconSize, yMin + k * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k) / 16F);
								glTexCoord2f(xMin + i * iconSize, yMin + (k + 1) * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (i + 1) * iconSize, yMin + (k + 1) * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (i + 1) * iconSize, yMin + k * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k) / 16F);
							}

							if (j == 0 || (int) field[i][j - 1][k] == 0) {
								glTexCoord2f(xMin + i * iconSize, yMin + k * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + i * iconSize, yMin + (k + 1) * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (i + 1) * iconSize, yMin + (k + 1) * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (i + 1) * iconSize, yMin + k * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k + 1) / 16F);
							}

							if (k == 15 || (int) field[i][j][k + 1] == 0) {
								glTexCoord2f(xMin + i * iconSize, yMin + j * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + i * iconSize, yMin + (j + 1) * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (i + 1) * iconSize, yMin + (j + 1) * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (i + 1) * iconSize, yMin + j * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k + 1) / 16F);
							}

							if (k == 0 || (int) field[i][j][k - 1] == 0) {
								glTexCoord2f(xMin + i * iconSize, yMin + j * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k) / 16F);
								glTexCoord2f(xMin + i * iconSize, yMin + (j + 1) * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (i + 1) * iconSize, yMin + (j + 1) * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (i + 1) * iconSize, yMin + j * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k) / 16F);
							}

							if (i == 15 || (int) field[i + 1][j][k] == 0) {
								glTexCoord2f(xMin + k * iconSize, yMin + j * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + k * iconSize, yMin + (j + 1) * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (k + 1) * iconSize, yMin + (j + 1) * iconSize);
								glVertex3f((i + 1) / 16F, (j) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (k + 1) * iconSize, yMin + j * iconSize);
								glVertex3f((i + 1) / 16F, (j + 1) / 16F, (k) / 16F);
							}

							if (i == 0 || (int) field[i - 1][j][k] == 0) {
								glTexCoord2f(xMin + j * iconSize, yMin + k * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + j * iconSize, yMin + (k + 1) * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k + 1) / 16F);
								glTexCoord2f(xMin + (j + 1) * iconSize, yMin + (k + 1) * iconSize);
								glVertex3f((i) / 16F, (j + 1) / 16F, (k) / 16F);
								glTexCoord2f(xMin + (j + 1) * iconSize, yMin + k * iconSize);
								glVertex3f((i) / 16F, (j) / 16F, (k) / 16F);
							}
						}

			glEnd();
			glPopAttrib();
			glPopMatrix();
		}
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		glRotatef(90F, 0F, 1F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		render(0.0625F);
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
		return BlockPillarTank.renderID;
	}
}