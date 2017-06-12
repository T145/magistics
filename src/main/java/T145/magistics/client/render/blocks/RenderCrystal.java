package T145.magistics.client.render.blocks;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import T145.magistics.Magistics;
import T145.magistics.api.variants.blocks.EnumCrystal;
import T145.magistics.client.render.models.blocks.ModelCrystal;
import T145.magistics.tiles.cosmetic.TileCrystal;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrystal extends TileEntitySpecialRenderer<TileCrystal> {

	private final ModelCrystal model = new ModelCrystal();

	private void translateFromOrientation(double x, double y, double z, int facing) {
		if (facing == 0) {
			GL11.glTranslated(x + 0.5F, y + 1.3F, z + 0.5F);
			GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		} else if (facing == 1) {
			GL11.glTranslated(x + 0.5F, y - 0.3F, z + 0.5F);
		} else if (facing == 2) {
			GL11.glTranslated(x + 0.5F, y + 0.5F, z + 1.3F);
			GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
		} else if (facing == 3) {
			GL11.glTranslated(x + 0.5F, y + 0.5F, z - 0.3F);
			GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
		} else if (facing == 4) {
			GL11.glTranslated(x + 1.3F, y + 0.5F, z + 0.5F);
			GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
		} else if (facing == 5) {
			GL11.glTranslated(x - 0.3F, y + 0.5F, z + 0.5F);
			GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
		}
	}

	private void drawCrystal(EnumFacing facing, double x, double y, double z, float var5, float var6, float var7, Random rand) {
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); // set brightness
		translateFromOrientation(x, y, z, facing.getIndex());
		GL11.glPushMatrix();
		GL11.glRotatef(var5, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(var6, 1.0F, 0.0F, 0.0F);
		GL11.glPushMatrix();
		GL11.glColor4f(var7, var7, var7, 1.0F);
		GL11.glScalef(0.15F + rand.nextFloat() * 0.075F, 0.5F + rand.nextFloat() * 0.1F, 0.15F + rand.nextFloat() * 0.05F);
		model.render();
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void renderTileEntityAt(TileCrystal crystal, double x, double y, double z, float partialTicks, int destroyStage) {
		float mod = 1F;

		switch (EnumCrystal.values()[crystal.getBlockMetadata()]) {
		case AIR:
			bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystaly.png"));
			break;
		case EARTH:
			bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystalg.png"));
			break;
		case FIRE:
			bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystalr.png"));
			break;
		case VOID:
			mod = 0.2F;
			break;
		case WATER:
			bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystalb.png"));
			break;
		default:
			bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystal.png"));
			break;
		}

		Random rand = new Random((long) (crystal.getBlockMetadata() + x + y * z));
		drawCrystal(crystal.getFacing(), x, y, z, (rand.nextFloat() - rand.nextFloat()) * 5.0F, (rand.nextFloat() - rand.nextFloat()) * 5.0F, mod, rand);

		for (int var12 = 1; var12 < crystal.crystals; ++var12) {
			int var13 = rand.nextInt(45) + 90 * var12;
			int var14 = 15 + rand.nextInt(15);
			drawCrystal(crystal.getFacing(), x, y, z, (float) var13, (float) var14, mod, rand);
		}
	}
}