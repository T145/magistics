package T145.magistics.client.lib;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlocks {

	private final boolean inventoryRender;

	public boolean renderFromInside;
	public double renderMinX;
	public double renderMaxX;
	public double renderMinY;
	public double renderMaxY;
	public double renderMinZ;
	public double renderMaxZ;
	public boolean lockBlockBounds;
	public int uvRotateEast;
	public int uvRotateWest;
	public int uvRotateSouth;
	public int uvRotateNorth;
	public int uvRotateTop;
	public int uvRotateBottom;

	public RenderBlocks(boolean inventoryRender) {
		this.inventoryRender = inventoryRender;
	}

	public RenderBlocks() {
		this(false);
	}

	public void setRenderBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		if (!lockBlockBounds) {
			renderMinX = minX;
			renderMaxX = maxX;
			renderMinY = minY;
			renderMaxY = maxY;
			renderMinZ = minZ;
			renderMaxZ = maxZ;
		}
	}

	public void overrideBlockBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		renderMinX = minX;
		renderMaxX = maxX;
		renderMinY = minY;
		renderMaxY = maxY;
		renderMinZ = minZ;
		renderMaxZ = maxZ;
		lockBlockBounds = true;
	}

	public void renderFaces(Tessellator tessellator, double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int bright) {
		renderFaceYNeg(tessellator, x, y, z, icon, red, green, blue, bright);
		renderFaceYPos(tessellator, x, y, z, icon, red, green, blue, bright);
		renderFaceZNeg(tessellator, x, y, z, icon, red, green, blue, bright);
		renderFaceZPos(tessellator, x, y, z, icon, red, green, blue, bright);
		renderFaceXNeg(tessellator, x, y, z, icon, red, green, blue, bright);
		renderFaceXPos(tessellator, x, y, z, icon, red, green, blue, bright);
	}

	public void renderFaceYNeg(Tessellator tessellator, double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int bright) {
		double d3 = icon.getInterpolatedU(renderMinX * 16.0D);
		double d4 = icon.getInterpolatedU(renderMaxX * 16.0D);
		double d5 = icon.getInterpolatedV(renderMinZ * 16.0D);
		double d6 = icon.getInterpolatedV(renderMaxZ * 16.0D);

		if ((renderMinX < 0.0D) || (renderMaxX > 1.0D)) {
			d3 = icon.getMinU();
			d4 = icon.getMaxU();
		}

		if ((renderMinZ < 0.0D) || (renderMaxZ > 1.0D)) {
			d5 = icon.getMinV();
			d6 = icon.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (uvRotateBottom == 2) {
			d3 = icon.getInterpolatedU(renderMinZ * 16.0D);
			d5 = icon.getInterpolatedV(16.0D - renderMaxX * 16.0D);
			d4 = icon.getInterpolatedU(renderMaxZ * 16.0D);
			d6 = icon.getInterpolatedV(16.0D - renderMinX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (uvRotateBottom == 1) {
			d3 = icon.getInterpolatedU(16.0D - renderMaxZ * 16.0D);
			d5 = icon.getInterpolatedV(renderMinX * 16.0D);
			d4 = icon.getInterpolatedU(16.0D - renderMinZ * 16.0D);
			d6 = icon.getInterpolatedV(renderMaxX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (uvRotateBottom == 3) {
			d3 = icon.getInterpolatedU(16.0D - renderMinX * 16.0D);
			d4 = icon.getInterpolatedU(16.0D - renderMaxX * 16.0D);
			d5 = icon.getInterpolatedV(16.0D - renderMinZ * 16.0D);
			d6 = icon.getInterpolatedV(16.0D - renderMaxZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = x + renderMinX;
		double d12 = x + renderMaxX;
		double d13 = y + renderMinY;
		double d14 = z + renderMinZ;
		double d15 = z + renderMaxZ;

		if (renderFromInside) {
			d11 = x + renderMaxX;
			d12 = x + renderMinX;
		}

		int i = bright;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d14).tex(d3, d5).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d14).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceYPos(Tessellator tessellator, double x, double y, double z, TextureAtlasSprite p_147806_8_, float red, float green, float blue, int bright) {
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

		double d11 = x + renderMinX;
		double d12 = x + renderMaxX;
		double d13 = y + renderMaxY;
		double d14 = z + renderMinZ;
		double d15 = z + renderMaxZ;

		if (renderFromInside) {
			d11 = x + renderMaxX;
			d12 = x + renderMinX;
		}

		int i = bright;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d12, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d14).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d14).tex(d3, d5).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceZNeg(Tessellator tessellator, double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int bright) {
		double d3 = icon.getInterpolatedU(renderMinX * 16.0D);
		double d4 = icon.getInterpolatedU(renderMaxX * 16.0D);

		if (inventoryRender) {
			d4 = icon.getInterpolatedU((1.0D - renderMinX) * 16.0D);
			d3 = icon.getInterpolatedU((1.0D - renderMaxX) * 16.0D);
		}

		double d5 = icon.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double d6 = icon.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (inventoryRender) {
			double d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if ((renderMinX < 0.0D) || (renderMaxX > 1.0D)) {
			d3 = icon.getMinU();
			d4 = icon.getMaxU();
		}

		if ((renderMinY < 0.0D) || (renderMaxY > 1.0D)) {
			d5 = icon.getMinV();
			d6 = icon.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (uvRotateEast == 2) {
			d3 = icon.getInterpolatedU(renderMinY * 16.0D);
			d4 = icon.getInterpolatedU(renderMaxY * 16.0D);
			d5 = icon.getInterpolatedV(16.0D - renderMinX * 16.0D);
			d6 = icon.getInterpolatedV(16.0D - renderMaxX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (uvRotateEast == 1) {
			d3 = icon.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			d4 = icon.getInterpolatedU(16.0D - renderMinY * 16.0D);
			d5 = icon.getInterpolatedV(renderMaxX * 16.0D);
			d6 = icon.getInterpolatedV(renderMinX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (uvRotateEast == 3) {
			d3 = icon.getInterpolatedU(16.0D - renderMinX * 16.0D);
			d4 = icon.getInterpolatedU(16.0D - renderMaxX * 16.0D);
			d5 = icon.getInterpolatedV(renderMaxY * 16.0D);
			d6 = icon.getInterpolatedV(renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = x + renderMinX;
		double d12 = x + renderMaxX;
		double d13 = y + renderMinY;
		double d14 = y + renderMaxY;
		double d15 = z + renderMinZ;

		if (renderFromInside) {
			d11 = x + renderMaxX;
			d12 = x + renderMinX;
		}

		int i = bright;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d14, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d14, d15).tex(d3, d5).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceZPos(Tessellator tessellator, double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int bright) {
		double d3 = icon.getInterpolatedU(renderMinX * 16.0D);
		double d4 = icon.getInterpolatedU(renderMaxX * 16.0D);
		double d5 = icon.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double d6 = icon.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (inventoryRender) {
			double d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if ((renderMinX < 0.0D) || (renderMaxX > 1.0D)) {
			d3 = icon.getMinU();
			d4 = icon.getMaxU();
		}

		if ((renderMinY < 0.0D) || (renderMaxY > 1.0D)) {
			d5 = icon.getMinV();
			d6 = icon.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (uvRotateWest == 1) {
			d3 = icon.getInterpolatedU(renderMinY * 16.0D);
			d6 = icon.getInterpolatedV(16.0D - renderMinX * 16.0D);
			d4 = icon.getInterpolatedU(renderMaxY * 16.0D);
			d5 = icon.getInterpolatedV(16.0D - renderMaxX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (uvRotateWest == 2) {
			d3 = icon.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			d5 = icon.getInterpolatedV(renderMinX * 16.0D);
			d4 = icon.getInterpolatedU(16.0D - renderMinY * 16.0D);
			d6 = icon.getInterpolatedV(renderMaxX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (uvRotateWest == 3) {
			d3 = icon.getInterpolatedU(16.0D - renderMinX * 16.0D);
			d4 = icon.getInterpolatedU(16.0D - renderMaxX * 16.0D);
			d5 = icon.getInterpolatedV(renderMaxY * 16.0D);
			d6 = icon.getInterpolatedV(renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = x + renderMinX;
		double d12 = x + renderMaxX;
		double d13 = y + renderMinY;
		double d14 = y + renderMaxY;
		double d15 = z + renderMaxZ;

		if (renderFromInside) {
			d11 = x + renderMaxX;
			d12 = x + renderMinX;
		}

		int i = bright;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d14, d15).tex(d3, d5).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d14, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceXNeg(Tessellator tessellator, double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int bright) {
		double d3 = icon.getInterpolatedU(renderMinZ * 16.0D);
		double d4 = icon.getInterpolatedU(renderMaxZ * 16.0D);
		double d5 = icon.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double d6 = icon.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (inventoryRender) {
			double d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if ((renderMinZ < 0.0D) || (renderMaxZ > 1.0D)) {
			d3 = icon.getMinU();
			d4 = icon.getMaxU();
		}

		if ((renderMinY < 0.0D) || (renderMaxY > 1.0D)) {
			d5 = icon.getMinV();
			d6 = icon.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (uvRotateNorth == 1) {
			d3 = icon.getInterpolatedU(renderMinY * 16.0D);
			d5 = icon.getInterpolatedV(16.0D - renderMaxZ * 16.0D);
			d4 = icon.getInterpolatedU(renderMaxY * 16.0D);
			d6 = icon.getInterpolatedV(16.0D - renderMinZ * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (uvRotateNorth == 2) {
			d3 = icon.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			d5 = icon.getInterpolatedV(renderMinZ * 16.0D);
			d4 = icon.getInterpolatedU(16.0D - renderMinY * 16.0D);
			d6 = icon.getInterpolatedV(renderMaxZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (uvRotateNorth == 3) {
			d3 = icon.getInterpolatedU(16.0D - renderMinZ * 16.0D);
			d4 = icon.getInterpolatedU(16.0D - renderMaxZ * 16.0D);
			d5 = icon.getInterpolatedV(renderMaxY * 16.0D);
			d6 = icon.getInterpolatedV(renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = x + renderMinX;
		double d12 = y + renderMinY;
		double d13 = y + renderMaxY;
		double d14 = z + renderMinZ;
		double d15 = z + renderMaxZ;

		if (renderFromInside) {
			d14 = z + renderMaxZ;
			d15 = z + renderMinZ;
		}

		int i = bright;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d13, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d14).tex(d3, d5).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d12, d14).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d12, d15).tex(d4, d6).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceXPos(Tessellator tessellator, double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int bright) {
		double d3 = icon.getInterpolatedU(renderMinZ * 16.0D);
		double d4 = icon.getInterpolatedU(renderMaxZ * 16.0D);

		if (inventoryRender) {
			d4 = icon.getInterpolatedU((1.0D - renderMinZ) * 16.0D);
			d3 = icon.getInterpolatedU((1.0D - renderMaxZ) * 16.0D);
		}

		double d5 = icon.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double d6 = icon.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (inventoryRender) {
			double d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if ((renderMinZ < 0.0D) || (renderMaxZ > 1.0D)) {
			d3 = icon.getMinU();
			d4 = icon.getMaxU();
		}

		if ((renderMinY < 0.0D) || (renderMaxY > 1.0D)) {
			d5 = icon.getMinV();
			d6 = icon.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (uvRotateSouth == 2) {
			d3 = icon.getInterpolatedU(renderMinY * 16.0D);
			d5 = icon.getInterpolatedV(16.0D - renderMinZ * 16.0D);
			d4 = icon.getInterpolatedU(renderMaxY * 16.0D);
			d6 = icon.getInterpolatedV(16.0D - renderMaxZ * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		} else if (uvRotateSouth == 1) {
			d3 = icon.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			d5 = icon.getInterpolatedV(renderMaxZ * 16.0D);
			d4 = icon.getInterpolatedU(16.0D - renderMinY * 16.0D);
			d6 = icon.getInterpolatedV(renderMinZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		} else if (uvRotateSouth == 3) {
			d3 = icon.getInterpolatedU(16.0D - renderMinZ * 16.0D);
			d4 = icon.getInterpolatedU(16.0D - renderMaxZ * 16.0D);
			d5 = icon.getInterpolatedV(renderMaxY * 16.0D);
			d6 = icon.getInterpolatedV(renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		double d11 = x + renderMaxX;
		double d12 = y + renderMinY;
		double d13 = y + renderMaxY;
		double d14 = z + renderMinZ;
		double d15 = z + renderMaxZ;

		if (renderFromInside) {
			d14 = z + renderMaxZ;
			d15 = z + renderMinZ;
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