package T145.magistics.client.render.blocks;

import java.nio.FloatBuffer;
import java.util.Random;

import T145.magistics.tiles.devices.TileVoidBorder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVoidBorder extends TileEntitySpecialRenderer<TileVoidBorder> {

	private static final ResourceLocation END_SKY_TEXTURE = new ResourceLocation("textures/environment/end_sky.png");
	private static final ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation("textures/entity/end_portal.png");
	private static final ResourceLocation END_GATEWAY_BEAM_TEXTURE = new ResourceLocation("textures/entity/end_gateway_beam.png");
	private static final Random RANDOM = new Random(31100L);
	private static final FloatBuffer MODELVIEW = GLAllocation.createDirectFloatBuffer(16);
	private static final FloatBuffer PROJECTION = GLAllocation.createDirectFloatBuffer(16);
	private FloatBuffer buffer = GLAllocation.createDirectFloatBuffer(16);

	@Override
	public void renderTileEntityAt(TileVoidBorder te, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.disableFog();
		GlStateManager.disableLighting();
		RANDOM.setSeed(31100L);
		GlStateManager.getFloat(2982, MODELVIEW);
		GlStateManager.getFloat(2983, PROJECTION);

		double d1 = x * x + y * y + z * z;
		int i;

		if (d1 > 36864.0D) {
			i = 2;
		} else {
			if (d1 > 25600.0D) {
				i = 4;
			} else {
				if (d1 > 16384.0D) {
					i = 6;
				} else {
					if (d1 > 9216.0D) {
						i = 8;
					} else {
						if (d1 > 4096.0D) {
							i = 10;
						} else {
							if (d1 > 1024.0D) {
								i = 12;
							} else {
								if (d1 > 576.0D) {
									i = 14;
								} else {
									if (d1 > 256.0D) {
										i = 15;
									} else {
										i = 16;
									}
								}
							}
						}
					}
				}
			}
		}

		for (int k = 0; k < i; k++) {
			GlStateManager.pushMatrix();
			float f5 = 2.0F / (18 - k);

			if (k == 0) {
				bindTexture(END_SKY_TEXTURE);
				f5 = 0.15F;
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			}

			if (k >= 1) {
				bindTexture(END_PORTAL_TEXTURE);
			}

			if (k == 1) {
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			}

			GlStateManager.texGen(GlStateManager.TexGen.S, 9216);
			GlStateManager.texGen(GlStateManager.TexGen.T, 9216);
			GlStateManager.texGen(GlStateManager.TexGen.R, 9216);
			GlStateManager.texGen(GlStateManager.TexGen.S, 9474, getBuffer(1.0F, 0.0F, 0.0F, 0.0F));
			GlStateManager.texGen(GlStateManager.TexGen.T, 9474, getBuffer(0.0F, 1.0F, 0.0F, 0.0F));
			GlStateManager.texGen(GlStateManager.TexGen.R, 9474, getBuffer(0.0F, 0.0F, 1.0F, 0.0F));
			GlStateManager.enableTexGenCoord(GlStateManager.TexGen.S);
			GlStateManager.enableTexGenCoord(GlStateManager.TexGen.T);
			GlStateManager.enableTexGenCoord(GlStateManager.TexGen.R);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.loadIdentity();
			GlStateManager.translate(0.5F, 0.5F, 0.0F);
			GlStateManager.scale(0.5F, 0.5F, 1.0F);
			float f1 = k + 1;
			GlStateManager.translate(17.0F / f1, (2.0F + f1 / 1.5F) * (Minecraft.getSystemTime() % 800000.0F / 800000.0F), 0.0F);
			GlStateManager.rotate((f1 * f1 * 4321.0F + f1 * 9.0F) * 2.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.scale(4.5F - f1 / 4.0F, 4.5F - f1 / 4.0F, 1.0F);
			GlStateManager.multMatrix(PROJECTION);
			GlStateManager.multMatrix(MODELVIEW);
			Tessellator tessellator = Tessellator.getInstance();
			VertexBuffer vertexbuffer = tessellator.getBuffer();
			vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
			float f2 = (RANDOM.nextFloat() * 0.5F + 0.1F) * f5;
			float f3 = (RANDOM.nextFloat() * 0.5F + 0.4F) * f5;
			float f4 = (RANDOM.nextFloat() * 0.5F + 0.5F) * f5;

			if (te.shouldRenderFace(EnumFacing.SOUTH)) {
				vertexbuffer.pos(x, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x + 1.0D, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x + 1.0D, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
			}

			if (te.shouldRenderFace(EnumFacing.NORTH)) {
				vertexbuffer.pos(x, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x + 1.0D, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x + 1.0D, y, z).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x, y, z).color(f2, f3, f4, 1.0F).endVertex();
			}

			if (te.shouldRenderFace(EnumFacing.EAST)) {
				vertexbuffer.pos(x + 1.0D, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x + 1.0D, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x + 1.0D, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x + 1.0D, y, z).color(f2, f3, f4, 1.0F).endVertex();
			}

			if (te.shouldRenderFace(EnumFacing.WEST)) {
				vertexbuffer.pos(x, y, z).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
			}

			if (te.shouldRenderFace(EnumFacing.DOWN)) {
				vertexbuffer.pos(x, y, z).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x + 1.0D, y, z).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x + 1.0D, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x, y, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
			}

			if (te.shouldRenderFace(EnumFacing.UP)) {
				vertexbuffer.pos(x, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x + 1.0D, y + 1.0D, z + 1.0D).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x + 1.0D, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
				vertexbuffer.pos(x, y + 1.0D, z).color(f2, f3, f4, 1.0F).endVertex();
			}

			tessellator.draw();
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
			bindTexture(END_SKY_TEXTURE);
		}

		GlStateManager.disableBlend();
		GlStateManager.disableTexGenCoord(GlStateManager.TexGen.S);
		GlStateManager.disableTexGenCoord(GlStateManager.TexGen.T);
		GlStateManager.disableTexGenCoord(GlStateManager.TexGen.R);
		GlStateManager.enableLighting();
		GlStateManager.enableFog();
	}

	private FloatBuffer getBuffer(float minU, float minV, float maxU, float maxV) {
		buffer.clear();
		buffer.put(minU).put(minV).put(maxU).put(maxV);
		buffer.flip();
		return buffer;
	}

	@Override
	public boolean isGlobalRenderer(TileVoidBorder te) {
		return false;
	}
}