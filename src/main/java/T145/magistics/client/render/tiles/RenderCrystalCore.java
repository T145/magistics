package T145.magistics.client.render.tiles;

import java.awt.Color;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.renderers.models.ModelCrystal;
import thaumcraft.common.blocks.BlockCustomOreItem;
import T145.magistics.tiles.TileCrystalCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrystalCore extends TileEntitySpecialRenderer {
	public static final TileEntitySpecialRenderer INSTANCE = new RenderCrystalCore();
	private ModelCrystal model = new ModelCrystal();

	private void drawCrystal(float x, float y, float z, float a1, float a2, float a3, Random rand, int color, float size, float speed) {
		EntityPlayer p = (EntityPlayer) Minecraft.getMinecraft().thePlayer;
		float shade = MathHelper.sin((p.ticksExisted + rand.nextInt(10)) / (5F + rand.nextFloat())) * 0.075F + 0.925F;
		Color c = new Color(color);
		float r = c.getRed() / 220F;
		float g = c.getGreen() / 220F;
		float b = c.getBlue() / 220F;

		GL11.glPushMatrix();
		GL11.glEnable(2977);
		GL11.glEnable(3042);
		GL11.glEnable(32826);
		GL11.glBlendFunc(770, 771);
		GL11.glTranslatef(x + 0.5F, y + 0.5F, z + 0.5F);
		GL11.glRotatef(a3, 0F, 0F, 1F);
		GL11.glRotatef(a1, 0F, 1F, 0F);
		GL11.glRotatef(a2, 1F, 0F, 0F);
		GL11.glTranslatef(0F, -0.1F + speed / 4F, 0F);
		GL11.glScalef((0.15F + rand.nextFloat() * 0.075F) * size, (0.5F + rand.nextFloat() * 0.1F) * size, (0.15F + rand.nextFloat() * 0.05f) * size);

		int shading = (int) (210F * shade);
		int mapX = shading % 65536;
		int mapZ = shading / 65536;

		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, mapX / 1F, mapZ / 1F);
		GL11.glColor4f(r, g, b, 1F);
		model.render();
		GL11.glScalef(1F, 1F, 1F);
		GL11.glDisable(32826);
		GL11.glDisable(3042);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
		TileCrystalCore tco = (TileCrystalCore) te;

		GL11.glPushMatrix();
		bindTexture(new ResourceLocation("magistics", "textures/models/crystal.png"));

		Random rand = new Random(tco.xCoord + tco.yCoord * tco.zCoord);
		int col = 0;

		GL11.glPushMatrix();

		for (int a = 0; a < 20; ++a) {
			++col;

			if (a % 5 == 0) {
				++col;
			}

			if (col > 5) {
				col = 1;
			}

			int color = BlockCustomOreItem.colors[col];
			float angle1 = (tco.isActive() ? (f * tco.speed) : 0F) + tco.rotation + 18 * a;
			float angle2 = 30 * (1 + a % 5);

			drawCrystal((float) x, (float) y + tco.speed, (float) z, angle1, angle2, ((tco.isActive() ? (f * tco.speed) : 0F) + tco.rotation) * 2F, rand, color, 0.7f, tco.speed);
		}

		GL11.glPopMatrix();

		int age = Minecraft.getMinecraft().renderViewEntity.ticksExisted;

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 0.5 + tco.speed, z + 0.5);

		int q = Minecraft.getMinecraft().gameSettings.fancyGraphics ? 20 : 10;
		Tessellator tes = Tessellator.instance;

		RenderHelper.disableStandardItemLighting();

		float f2 = age / 500F;
		float f3 = 0.9f;
		float f4 = 0F;
		Random random = new Random(245L);

		GL11.glDisable(3553);
		GL11.glShadeModel(7425);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 1);
		GL11.glDisable(3008);
		GL11.glEnable(2884);
		GL11.glDepthMask(false);
		GL11.glPushMatrix();

		for (int i = 0; i < q; ++i) {
			GL11.glRotatef(random.nextFloat() * 360F, 1F, 0F, 0F);
			GL11.glRotatef(random.nextFloat() * 360F, 0F, 1F, 0F);
			GL11.glRotatef(random.nextFloat() * 360F, 0F, 0F, 1F);
			GL11.glRotatef(random.nextFloat() * 360F, 1F, 0F, 0F);
			GL11.glRotatef(random.nextFloat() * 360F, 0F, 1F, 0F);
			GL11.glRotatef(random.nextFloat() * 360F + f2 * 360F, 0F, 0F, 1F);
			tes.startDrawing(6);

			float fa = random.nextFloat() * 20F + 5F + f4 * 10F;
			float f5 = random.nextFloat() * 2F + 1F + f4 * 2F;

			fa /= 30F / (Math.min(age, 10) / 10F);
			f5 /= 30F / (Math.min(age, 10) / 10F);
			tes.setColorRGBA_I(16777215, (int) (255F * (1F - f4)));
			tes.addVertex(0.0, 0.0, 0.0);
			tes.setColorRGBA_I(16764159, 0);
			tes.addVertex(-0.866 * f5, fa, -0.5F * f5);
			tes.addVertex(0.866 * f5, fa, -0.5F * f5);
			tes.addVertex(0.0, fa, 1F * f5);
			tes.addVertex(-0.866 * f5, fa, -0.5F * f5);
			tes.draw();
		}

		GL11.glPopMatrix();
		GL11.glDepthMask(true);
		GL11.glDisable(2884);
		GL11.glDisable(3042);
		GL11.glShadeModel(7424);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glEnable(3553);
		GL11.glEnable(3008);
		RenderHelper.enableStandardItemLighting();
		GL11.glBlendFunc(770, 771);
		GL11.glPopMatrix();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glPopMatrix();
	}
}