package T145.magistics.client.render.tiles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCrystal;
import T145.magistics.tiles.TileCrystalizer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrystalizer extends TileEntitySpecialRenderer {
	public static final TileEntitySpecialRenderer INSTANCE = new RenderCrystalizer();
	private ModelCrystal model = new ModelCrystal();

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		renderCrystalizer((TileCrystalizer) tile, x, y, z);
	}

	private void drawCrystal(float x, float y, float z, float rotationY, float rotationX, float tint) {
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Tessellator var7 = Tessellator.instance;
		var7.setBrightness(220);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(rotationY, 0F, 1F, 0F);
		GL11.glRotatef(rotationX, 1F, 0F, 0F);
		GL11.glPushMatrix();
		GL11.glColor4f(tint, tint, tint, 1F);
		GL11.glScalef(0.15F, 0.45F, 0.15F);
		model.render();
		GL11.glScalef(1F, 1F, 1F);
		GL11.glPopMatrix();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1F, 1F, 1F, 1F);
	}

	public void renderCrystalizer(TileCrystalizer tile, double x, double y, double z) {
		float tint = 0F;
		float facing = 32F;
		float angle = 72F;
		float mod = Minecraft.getMinecraft().thePlayer.ticksExisted;

		if (tile.isCooking()) {
			facing += (float) (mod % 360);
			tint = MathHelper.sin((float) mod / 5F) * 0.15F + 0.15F;
		}

		UtilsFX.bindTexture("magistics", "textures/models/crystals/air.png");
		drawCrystal((float) x + 0.5F, (float) y + 0.25F, (float) z + 0.5F, facing, 0F, 1F - tint);
		UtilsFX.bindTexture("magistics", "textures/models/crystals/fire.png");
		drawCrystal((float) x + 0.5F, (float) y + 0.25F, (float) z + 0.5F, facing, 25F, 1F - tint);
		facing += angle;
		UtilsFX.bindTexture("magistics", "textures/models/crystals/water.png");
		drawCrystal((float) x + 0.5F, (float) y + 0.25F, (float) z + 0.5F, facing, 25F, 1F - tint);
		facing += angle;
		UtilsFX.bindTexture("magistics", "textures/models/crystals/earth.png");
		drawCrystal((float) x + 0.5F, (float) y + 0.25F, (float) z + 0.5F, facing, 25F, 1F - tint);
		facing += angle;
		UtilsFX.bindTexture("magistics", "textures/models/crystals/order.png");
		drawCrystal((float) x + 0.5F, (float) y + 0.25F, (float) z + 0.5F, facing, 25F, 1F - tint);
		facing += angle;
		UtilsFX.bindTexture("magistics", "textures/models/crystals/entropy.png");
		drawCrystal((float) x + 0.5F, (float) y + 0.25F, (float) z + 0.5F, facing, 25F, 0.4F - tint);
	}
}