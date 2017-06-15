package T145.magistics.client.render.blocks;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import T145.magistics.Magistics;
import T145.magistics.api.variants.blocks.EnumCrystal;
import T145.magistics.client.lib.Render;
import T145.magistics.client.render.models.blocks.ModelCrystal;
import T145.magistics.tiles.cosmetic.TileCrystal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrystal extends TileEntitySpecialRenderer<TileCrystal> {

	private static final ModelCrystal MODEL = new ModelCrystal();

	private static void translateFromOrientation(double x, double y, double z, int orientation) {
		if (orientation == 0) {
			GlStateManager.translate(x + 0.5F, y + 1.3F, z + 0.5F);
			GlStateManager.rotate(180F, 1F, 0F, 0F);
		} else if (orientation == 1) {
			GlStateManager.translate(x + 0.5F, y - 0.3F, z + 0.5F);
		} else if (orientation == 2) {
			GlStateManager.translate(x + 0.5F, y + 0.5F, z + 1.3F);
			GlStateManager.rotate(-90F, 1F, 0F, 0F);
		} else if (orientation == 3) {
			GlStateManager.translate(x + 0.5F, y + 0.5F, z - 0.3F);
			GlStateManager.rotate(90F, 1F, 0F, 0F);
		} else if (orientation == 4) {
			GlStateManager.translate(x + 1.3F, y + 0.5F, z + 0.5F);
			GlStateManager.rotate(90F, 0F, 0F, 1F);
		} else if (orientation == 5) {
			GlStateManager.translate(x - 0.3F, y + 0.5F, z + 0.5F);
			GlStateManager.rotate(-90F, 0F, 0F, 1F);
		}
	}

	private static void drawCrystal(EnumFacing facing, double x, double y, double z, float a1, float a2, Random rand, /*int color,*/ float size) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		float shade = MathHelper.sin((player.ticksExisted + rand.nextInt(10)) / (5.0F + rand.nextFloat())) * 0.075F + 0.925F;

		/*Color c = new Color(color);
		float r = c.getRed() / 220F;
		float g = c.getGreen() / 220F;
		float b = c.getBlue() / 220F;*/

		GlStateManager.pushMatrix();
		GL11.glEnable(2977);
		GL11.glEnable(3042);
		GL11.glEnable(32826);
		GlStateManager.blendFunc(770, 771);

		translateFromOrientation(x, y, z, facing.getIndex());
		GlStateManager.rotate(a1, 0F, 1F, 0F);
		GlStateManager.rotate(a2, 1F, 0F, 0F);

		GlStateManager.scale((0.15F + rand.nextFloat() * 0.075F) * size, (0.5F + rand.nextFloat() * 0.1F) * size, (0.15F + rand.nextFloat() * 0.05F) * size);

		int var19 = (int) (210F * shade);
		int var20 = var19 % 65536;
		int var21 = var19 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var20 / 1F, var21 / 1F);
		//GlStateManager.color(r, g, b, 1F);

		MODEL.render();
		GlStateManager.scale(1F, 1F, 1F);

		GL11.glDisable(32826);
		GL11.glDisable(3042);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GlStateManager.popMatrix();
	}

	@Override
	public void renderTileEntityAt(TileCrystal crystal, double x, double y, double z, float partialTicks, int destroyStage) {
		renderShards(crystal.getBlockMetadata(), crystal.crystalCount, crystal.getFacing(), x, y, z);
	}

	// TODO: Add coloring; I have to figure out the main color value of the models though
	public static void renderShards(int meta, int crystalCount, EnumFacing facing, double x, double y, double z) {
		GlStateManager.pushMatrix();

		/*int color = colors[5];

		if (md != 6) {
			color = colors[(md + 1)];
		}*/

		//Render.bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystal.png");

		switch (EnumCrystal.values()[meta]) {
		case AIR:
			Render.bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystaly.png"));
			break;
		case EARTH:
			Render.bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystalg.png"));
			break;
		case FIRE:
			Render.bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystalr.png"));
			break;
		case VOID:
			Render.bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystal.png"));
			break;
		case WATER:
			Render.bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystalb.png"));
			break;
		case MAGIC:
			Render.bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystal.png"));
			break;
		}

		Random rand = new Random((long) (meta + x + y * z));

		drawCrystal(facing, x, y, z, (rand.nextFloat() - rand.nextFloat()) * 5.0F, (rand.nextFloat() - rand.nextFloat()) * 5.0F, rand, /*color,*/ 1.1F);

		for (int a = 1; a < 6; a++) {
			/*if (md == 6) {
				color = thaumcraft.common.blocks.BlockCustomOreItem.colors[a];
			}*/

			int angle1 = rand.nextInt(36) + 72 * a;
			int angle2 = 15 + rand.nextInt(15);
			drawCrystal(facing, x, y, z, angle1, angle2, rand, /*color,*/ 0.8F);
		}

		GlStateManager.popMatrix();
	}
}