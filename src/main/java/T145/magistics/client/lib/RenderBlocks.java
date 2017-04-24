package T145.magistics.client.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlocks {

	public IBlockAccess blockAccess;
	public boolean flipTexture;
	public boolean inventoryRender;
	public boolean renderFromInside = false;
	public double renderMinX;
	public double renderMaxX;
	public double renderMinY;
	public double renderMaxY;
	public double renderMinZ;
	public double renderMaxZ;
	public boolean lockBlockBounds;
	public boolean partialRenderBounds;
	public final Minecraft mc;
	public int uvRotateEast;
	public int uvRotateWest;
	public int uvRotateSouth;
	public int uvRotateNorth;
	public int uvRotateTop;
	public int uvRotateBottom;
	private static RenderBlocks instance;

	public RenderBlocks() {
		mc = Minecraft.getMinecraft();
	}

	public RenderBlocks(IBlockAccess world) {
		this();
		blockAccess = world;
		inventoryRender = false;
		flipTexture = false;
	}

	public static RenderBlocks getInstance() {
		if (instance == null) {
			instance = new RenderBlocks();
		}
		return instance;
	}

	public static RenderBlocks getWorldInstance(IBlockAccess world) {
		if (instance == null) {
			instance = new RenderBlocks(world);
		}
		return instance;
	}

	public void setRenderBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		if (!lockBlockBounds) {
			renderMinX = minX;
			renderMaxX = maxX;
			renderMinY = minY;
			renderMaxY = maxY;
			renderMinZ = minZ;
			renderMaxZ = maxZ;
			partialRenderBounds = ((mc.gameSettings.ambientOcclusion >= 2) && ((renderMinX > 0.0D) || (renderMaxX < 1.0D) || (renderMinY > 0.0D) || (renderMaxY < 1.0D) || (renderMinZ > 0.0D) || (renderMaxZ < 1.0D)));
		}
	}

	public void overrideBlockBounds(double p_147770_1_, double p_147770_3_, double p_147770_5_, double p_147770_7_, double p_147770_9_, double p_147770_11_) {
		renderMinX = p_147770_1_;
		renderMaxX = p_147770_7_;
		renderMinY = p_147770_3_;
		renderMaxY = p_147770_9_;
		renderMinZ = p_147770_5_;
		renderMaxZ = p_147770_11_;
		lockBlockBounds = true;
		partialRenderBounds = ((mc.gameSettings.ambientOcclusion >= 2) && ((renderMinX > 0.0D) || (renderMaxX < 1.0D) || (renderMinY > 0.0D) || (renderMaxY < 1.0D) || (renderMinZ > 0.0D) || (renderMaxZ < 1.0D)));
	}

	public void renderFaceYNeg(double p_147768_2_, double p_147768_4_, double p_147768_6_, TextureAtlasSprite p_147768_8_, float red, float green, float blue, int bright) {
		Tessellator tessellator = Tessellator.getInstance();

		double d3 = p_147768_8_.getInterpolatedU(renderMinX * 16.0D);
		double d4 = p_147768_8_.getInterpolatedU(renderMaxX * 16.0D);
		double d5 = p_147768_8_.getInterpolatedV(renderMinZ * 16.0D);
		double d6 = p_147768_8_.getInterpolatedV(renderMaxZ * 16.0D);

		if ((renderMinX < 0.0D) || (renderMaxX > 1.0D)) {
			d3 = p_147768_8_.getMinU();
			d4 = p_147768_8_.getMaxU();
		}

		if ((renderMinZ < 0.0D) || (renderMaxZ > 1.0D)) {
			d5 = p_147768_8_.getMinV();
			d6 = p_147768_8_.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (uvRotateBottom == 2) {
			d3 = p_147768_8_.getInterpolatedU(renderMinZ * 16.0D);
			d5 = p_147768_8_.getInterpolatedV(16.0D - renderMaxX * 16.0D);
			d4 = p_147768_8_.getInterpolatedU(renderMaxZ * 16.0D);
			d6 = p_147768_8_.getInterpolatedV(16.0D - renderMinX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (uvRotateBottom == 1) {
			d3 = p_147768_8_.getInterpolatedU(16.0D - renderMaxZ * 16.0D);
			d5 = p_147768_8_.getInterpolatedV(renderMinX * 16.0D);
			d4 = p_147768_8_.getInterpolatedU(16.0D - renderMinZ * 16.0D);
			d6 = p_147768_8_.getInterpolatedV(renderMaxX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (uvRotateBottom == 3) {
			d3 = p_147768_8_.getInterpolatedU(16.0D - renderMinX * 16.0D);
			d4 = p_147768_8_.getInterpolatedU(16.0D - renderMaxX * 16.0D);
			d5 = p_147768_8_.getInterpolatedV(16.0D - renderMinZ * 16.0D);
			d6 = p_147768_8_.getInterpolatedV(16.0D - renderMaxZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = p_147768_2_ + renderMinX;
		double d12 = p_147768_2_ + renderMaxX;
		double d13 = p_147768_4_ + renderMinY;
		double d14 = p_147768_6_ + renderMinZ;
		double d15 = p_147768_6_ + renderMaxZ;

		if (renderFromInside) {
			d11 = p_147768_2_ + renderMaxX;
			d12 = p_147768_2_ + renderMinX;
		}

		int i = bright;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d14).tex(d3, d5).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d14).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceYPos(double p_147806_2_, double p_147806_4_, double p_147806_6_, TextureAtlasSprite p_147806_8_, float red, float green, float blue, int bright) {
		Tessellator tessellator = Tessellator.getInstance();

		double d3 = p_147806_8_.getInterpolatedU(renderMinX * 16.0D);
		double d4 = p_147806_8_.getInterpolatedU(renderMaxX * 16.0D);
		double d5 = p_147806_8_.getInterpolatedV(renderMinZ * 16.0D);
		double d6 = p_147806_8_.getInterpolatedV(renderMaxZ * 16.0D);

		if ((renderMinX < 0.0D) || (renderMaxX > 1.0D)) {
			d3 = p_147806_8_.getMinU();
			d4 = p_147806_8_.getMaxU();
		}

		if ((renderMinZ < 0.0D) || (renderMaxZ > 1.0D)) {
			d5 = p_147806_8_.getMinV();
			d6 = p_147806_8_.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (uvRotateTop == 1) {
			d3 = p_147806_8_.getInterpolatedU(renderMinZ * 16.0D);
			d5 = p_147806_8_.getInterpolatedV(16.0D - renderMaxX * 16.0D);
			d4 = p_147806_8_.getInterpolatedU(renderMaxZ * 16.0D);
			d6 = p_147806_8_.getInterpolatedV(16.0D - renderMinX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (uvRotateTop == 2) {
			d3 = p_147806_8_.getInterpolatedU(16.0D - renderMaxZ * 16.0D);
			d5 = p_147806_8_.getInterpolatedV(renderMinX * 16.0D);
			d4 = p_147806_8_.getInterpolatedU(16.0D - renderMinZ * 16.0D);
			d6 = p_147806_8_.getInterpolatedV(renderMaxX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (uvRotateTop == 3) {
			d3 = p_147806_8_.getInterpolatedU(16.0D - renderMinX * 16.0D);
			d4 = p_147806_8_.getInterpolatedU(16.0D - renderMaxX * 16.0D);
			d5 = p_147806_8_.getInterpolatedV(16.0D - renderMinZ * 16.0D);
			d6 = p_147806_8_.getInterpolatedV(16.0D - renderMaxZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = p_147806_2_ + renderMinX;
		double d12 = p_147806_2_ + renderMaxX;
		double d13 = p_147806_4_ + renderMaxY;
		double d14 = p_147806_6_ + renderMinZ;
		double d15 = p_147806_6_ + renderMaxZ;

		if (renderFromInside) {
			d11 = p_147806_2_ + renderMaxX;
			d12 = p_147806_2_ + renderMinX;
		}

		int i = bright;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d12, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d14).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d14).tex(d3, d5).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceZNeg(double p_147761_2_, double p_147761_4_, double p_147761_6_, TextureAtlasSprite p_147761_8_, float red, float green, float blue, int bright) {
		Tessellator tessellator = Tessellator.getInstance();

		double d3 = p_147761_8_.getInterpolatedU(renderMinX * 16.0D);
		double d4 = p_147761_8_.getInterpolatedU(renderMaxX * 16.0D);

		if (inventoryRender) {
			d4 = p_147761_8_.getInterpolatedU((1.0D - renderMinX) * 16.0D);
			d3 = p_147761_8_.getInterpolatedU((1.0D - renderMaxX) * 16.0D);
		}

		double d5 = p_147761_8_.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double d6 = p_147761_8_.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (flipTexture) {
			double d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if ((renderMinX < 0.0D) || (renderMaxX > 1.0D)) {
			d3 = p_147761_8_.getMinU();
			d4 = p_147761_8_.getMaxU();
		}

		if ((renderMinY < 0.0D) || (renderMaxY > 1.0D)) {
			d5 = p_147761_8_.getMinV();
			d6 = p_147761_8_.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (uvRotateEast == 2) {
			d3 = p_147761_8_.getInterpolatedU(renderMinY * 16.0D);
			d4 = p_147761_8_.getInterpolatedU(renderMaxY * 16.0D);
			d5 = p_147761_8_.getInterpolatedV(16.0D - renderMinX * 16.0D);
			d6 = p_147761_8_.getInterpolatedV(16.0D - renderMaxX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (uvRotateEast == 1) {
			d3 = p_147761_8_.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			d4 = p_147761_8_.getInterpolatedU(16.0D - renderMinY * 16.0D);
			d5 = p_147761_8_.getInterpolatedV(renderMaxX * 16.0D);
			d6 = p_147761_8_.getInterpolatedV(renderMinX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (uvRotateEast == 3) {
			d3 = p_147761_8_.getInterpolatedU(16.0D - renderMinX * 16.0D);
			d4 = p_147761_8_.getInterpolatedU(16.0D - renderMaxX * 16.0D);
			d5 = p_147761_8_.getInterpolatedV(renderMaxY * 16.0D);
			d6 = p_147761_8_.getInterpolatedV(renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = p_147761_2_ + renderMinX;
		double d12 = p_147761_2_ + renderMaxX;
		double d13 = p_147761_4_ + renderMinY;
		double d14 = p_147761_4_ + renderMaxY;
		double d15 = p_147761_6_ + renderMinZ;

		if (renderFromInside) {
			d11 = p_147761_2_ + renderMaxX;
			d12 = p_147761_2_ + renderMinX;
		}

		int i = bright;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d14, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d14, d15).tex(d3, d5).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceZPos(double p_147734_2_, double p_147734_4_, double p_147734_6_, TextureAtlasSprite p_147734_8_, float red, float green, float blue, int bright) {
		Tessellator tessellator = Tessellator.getInstance();

		double d3 = p_147734_8_.getInterpolatedU(renderMinX * 16.0D);
		double d4 = p_147734_8_.getInterpolatedU(renderMaxX * 16.0D);
		double d5 = p_147734_8_.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double d6 = p_147734_8_.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (flipTexture) {
			double d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if ((renderMinX < 0.0D) || (renderMaxX > 1.0D)) {
			d3 = p_147734_8_.getMinU();
			d4 = p_147734_8_.getMaxU();
		}

		if ((renderMinY < 0.0D) || (renderMaxY > 1.0D)) {
			d5 = p_147734_8_.getMinV();
			d6 = p_147734_8_.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (uvRotateWest == 1) {
			d3 = p_147734_8_.getInterpolatedU(renderMinY * 16.0D);
			d6 = p_147734_8_.getInterpolatedV(16.0D - renderMinX * 16.0D);
			d4 = p_147734_8_.getInterpolatedU(renderMaxY * 16.0D);
			d5 = p_147734_8_.getInterpolatedV(16.0D - renderMaxX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (uvRotateWest == 2) {
			d3 = p_147734_8_.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			d5 = p_147734_8_.getInterpolatedV(renderMinX * 16.0D);
			d4 = p_147734_8_.getInterpolatedU(16.0D - renderMinY * 16.0D);
			d6 = p_147734_8_.getInterpolatedV(renderMaxX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (uvRotateWest == 3) {
			d3 = p_147734_8_.getInterpolatedU(16.0D - renderMinX * 16.0D);
			d4 = p_147734_8_.getInterpolatedU(16.0D - renderMaxX * 16.0D);
			d5 = p_147734_8_.getInterpolatedV(renderMaxY * 16.0D);
			d6 = p_147734_8_.getInterpolatedV(renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = p_147734_2_ + renderMinX;
		double d12 = p_147734_2_ + renderMaxX;
		double d13 = p_147734_4_ + renderMinY;
		double d14 = p_147734_4_ + renderMaxY;
		double d15 = p_147734_6_ + renderMaxZ;

		if (renderFromInside) {
			d11 = p_147734_2_ + renderMaxX;
			d12 = p_147734_2_ + renderMinX;
		}

		int i = bright;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d14, d15).tex(d3, d5).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d14, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceXNeg(double p_147798_2_, double p_147798_4_, double p_147798_6_, TextureAtlasSprite p_147798_8_, float red, float green, float blue, int bright) {
		Tessellator tessellator = Tessellator.getInstance();

		double d3 = p_147798_8_.getInterpolatedU(renderMinZ * 16.0D);
		double d4 = p_147798_8_.getInterpolatedU(renderMaxZ * 16.0D);
		double d5 = p_147798_8_.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double d6 = p_147798_8_.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (flipTexture) {
			double d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if ((renderMinZ < 0.0D) || (renderMaxZ > 1.0D)) {
			d3 = p_147798_8_.getMinU();
			d4 = p_147798_8_.getMaxU();
		}

		if ((renderMinY < 0.0D) || (renderMaxY > 1.0D)) {
			d5 = p_147798_8_.getMinV();
			d6 = p_147798_8_.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (uvRotateNorth == 1) {
			d3 = p_147798_8_.getInterpolatedU(renderMinY * 16.0D);
			d5 = p_147798_8_.getInterpolatedV(16.0D - renderMaxZ * 16.0D);
			d4 = p_147798_8_.getInterpolatedU(renderMaxY * 16.0D);
			d6 = p_147798_8_.getInterpolatedV(16.0D - renderMinZ * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (uvRotateNorth == 2) {
			d3 = p_147798_8_.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			d5 = p_147798_8_.getInterpolatedV(renderMinZ * 16.0D);
			d4 = p_147798_8_.getInterpolatedU(16.0D - renderMinY * 16.0D);
			d6 = p_147798_8_.getInterpolatedV(renderMaxZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (uvRotateNorth == 3) {
			d3 = p_147798_8_.getInterpolatedU(16.0D - renderMinZ * 16.0D);
			d4 = p_147798_8_.getInterpolatedU(16.0D - renderMaxZ * 16.0D);
			d5 = p_147798_8_.getInterpolatedV(renderMaxY * 16.0D);
			d6 = p_147798_8_.getInterpolatedV(renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = p_147798_2_ + renderMinX;
		double d12 = p_147798_4_ + renderMinY;
		double d13 = p_147798_4_ + renderMaxY;
		double d14 = p_147798_6_ + renderMinZ;
		double d15 = p_147798_6_ + renderMaxZ;

		if (renderFromInside) {
			d14 = p_147798_6_ + renderMaxZ;
			d15 = p_147798_6_ + renderMinZ;
		}

		int i = bright;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d13, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d14).tex(d3, d5).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d12, d14).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d12, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceXPos(double p_147764_2_, double p_147764_4_, double p_147764_6_, TextureAtlasSprite p_147764_8_, float red, float green, float blue, int bright) {
		Tessellator tessellator = Tessellator.getInstance();

		double d3 = p_147764_8_.getInterpolatedU(renderMinZ * 16.0D);
		double d4 = p_147764_8_.getInterpolatedU(renderMaxZ * 16.0D);

		if (inventoryRender) {
			d4 = p_147764_8_.getInterpolatedU((1.0D - renderMinZ) * 16.0D);
			d3 = p_147764_8_.getInterpolatedU((1.0D - renderMaxZ) * 16.0D);
		}

		double d5 = p_147764_8_.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double d6 = p_147764_8_.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (flipTexture) {
			double d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if ((renderMinZ < 0.0D) || (renderMaxZ > 1.0D)) {
			d3 = p_147764_8_.getMinU();
			d4 = p_147764_8_.getMaxU();
		}

		if ((renderMinY < 0.0D) || (renderMaxY > 1.0D)) {
			d5 = p_147764_8_.getMinV();
			d6 = p_147764_8_.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (uvRotateSouth == 2) {
			d3 = p_147764_8_.getInterpolatedU(renderMinY * 16.0D);
			d5 = p_147764_8_.getInterpolatedV(16.0D - renderMinZ * 16.0D);
			d4 = p_147764_8_.getInterpolatedU(renderMaxY * 16.0D);
			d6 = p_147764_8_.getInterpolatedV(16.0D - renderMaxZ * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (uvRotateSouth == 1) {
			d3 = p_147764_8_.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			d5 = p_147764_8_.getInterpolatedV(renderMaxZ * 16.0D);
			d4 = p_147764_8_.getInterpolatedU(16.0D - renderMinY * 16.0D);
			d6 = p_147764_8_.getInterpolatedV(renderMinZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (uvRotateSouth == 3) {
			d3 = p_147764_8_.getInterpolatedU(16.0D - renderMinZ * 16.0D);
			d4 = p_147764_8_.getInterpolatedU(16.0D - renderMaxZ * 16.0D);
			d5 = p_147764_8_.getInterpolatedV(renderMaxY * 16.0D);
			d6 = p_147764_8_.getInterpolatedV(renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = p_147764_2_ + renderMaxX;
		double d12 = p_147764_4_ + renderMinY;
		double d13 = p_147764_4_ + renderMaxY;
		double d14 = p_147764_6_ + renderMinZ;
		double d15 = p_147764_6_ + renderMaxZ;

		if (renderFromInside) {
			d14 = p_147764_6_ + renderMaxZ;
			d15 = p_147764_6_ + renderMinZ;
		}

		int i = bright;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d12, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d12, d14).tex(d4, d6).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d14).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d15).tex(d3, d5).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}
}