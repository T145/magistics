package T145.magistics.client.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCubes {

	public boolean flipTexture;
	public boolean field_152631_f;
	public boolean renderAllFaces;
	public boolean useInventoryTint = true;
	public boolean renderFromInside = false;
	public double renderMinX;
	public double renderMaxX;
	public double renderMinY;
	public double renderMaxY;
	public double renderMinZ;
	public double renderMaxZ;
	public boolean lockBlockBounds;
	public boolean partialRenderBounds;
	public int uvRotateEast;
	public int uvRotateWest;
	public int uvRotateSouth;
	public int uvRotateNorth;
	public int uvRotateTop;
	public int uvRotateBottom;

	public RenderCubes() {
		field_152631_f = false;
		flipTexture = false;
	}

	private void setBlockBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		renderMinX = minX;
		renderMaxX = maxX;
		renderMinY = minY;
		renderMaxY = maxY;
		renderMinZ = minZ;
		renderMaxZ = maxZ;
		partialRenderBounds = Minecraft.getMinecraft().gameSettings.ambientOcclusion >= 2 && (renderMinX > 0.0D || renderMaxX < 1.0D || renderMinY > 0.0D || renderMaxY < 1.0D || renderMinZ > 0.0D || renderMaxZ < 1.0D);
	}

	public void setRenderBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		if (!lockBlockBounds) {
			setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
	}

	public void setRenderBounds(AxisAlignedBB box) {
		setRenderBounds(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
	}

	public void overrideBlockBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		lockBlockBounds = true;
		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}

	public void renderFaces(double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int brightness) {
		renderFaceYNeg(x, y, z, icon, red, green, blue, brightness);
		renderFaceYPos(x, y, z, icon, red, green, blue, brightness);
		renderFaceZNeg(x, y, z, icon, red, green, blue, brightness);
		renderFaceZPos(x, y, z, icon, red, green, blue, brightness);
		renderFaceXNeg(x, y, z, icon, red, green, blue, brightness);
		renderFaceXPos(x, y, z, icon, red, green, blue, brightness);
	}

	public void renderFaceYNeg(double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int brightness) {
		Tessellator tessellator = Tessellator.getInstance();

		double minU = icon.getInterpolatedU(renderMinX * 16.0D);
		double maxU = icon.getInterpolatedU(renderMaxX * 16.0D);
		double minV = icon.getInterpolatedV(renderMinZ * 16.0D);
		double maxV = icon.getInterpolatedV(renderMaxZ * 16.0D);

		if (renderMinX < 0.0D || renderMaxX > 1.0D) {
			minU = icon.getMinU();
			maxU = icon.getMaxU();
		}

		if (renderMinZ < 0.0D || renderMaxZ > 1.0D) {
			minV = icon.getMinV();
			maxV = icon.getMaxV();
		}

		double d7 = maxU;
		double d8 = minU;
		double d9 = minV;
		double d10 = maxV;

		if (uvRotateBottom == 2) {
			minU = icon.getInterpolatedU(renderMinZ * 16.0D);
			minV = icon.getInterpolatedV(16.0D - renderMaxX * 16.0D);
			maxU = icon.getInterpolatedU(renderMaxZ * 16.0D);
			maxV = icon.getInterpolatedV(16.0D - renderMinX * 16.0D);
			d9 = minV;
			d10 = maxV;
			d7 = minU;
			d8 = maxU;
			minV = maxV;
			maxV = d9;
		} else if (uvRotateBottom == 1) {
			minU = icon.getInterpolatedU(16.0D - renderMaxZ * 16.0D);
			minV = icon.getInterpolatedV(renderMinX * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMinZ * 16.0D);
			maxV = icon.getInterpolatedV(renderMaxX * 16.0D);
			d7 = maxU;
			d8 = minU;
			minU = maxU;
			maxU = d8;
			d9 = maxV;
			d10 = minV;
		} else if (uvRotateBottom == 3) {
			minU = icon.getInterpolatedU(16.0D - renderMinX * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMaxX * 16.0D);
			minV = icon.getInterpolatedV(16.0D - renderMinZ * 16.0D);
			maxV = icon.getInterpolatedV(16.0D - renderMaxZ * 16.0D);
			d7 = maxU;
			d8 = minU;
			d9 = minV;
			d10 = maxV;
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

		int i = brightness;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d14).tex(minU, minV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d14).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d15).tex(maxU, maxV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceYPos(double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int brightness) {
		Tessellator tessellator = Tessellator.getInstance();

		double minU = icon.getInterpolatedU(renderMinX * 16.0D);
		double maxU = icon.getInterpolatedU(renderMaxX * 16.0D);
		double minV = icon.getInterpolatedV(renderMinZ * 16.0D);
		double maxV = icon.getInterpolatedV(renderMaxZ * 16.0D);

		if (renderMinX < 0.0D || renderMaxX > 1.0D) {
			minU = icon.getMinU();
			maxU = icon.getMaxU();
		}

		if (renderMinZ < 0.0D || renderMaxZ > 1.0D) {
			minV = icon.getMinV();
			maxV = icon.getMaxV();
		}

		double d7 = maxU;
		double d8 = minU;
		double d9 = minV;
		double d10 = maxV;

		if (uvRotateTop == 1) {
			minU = icon.getInterpolatedU(renderMinZ * 16.0D);
			minV = icon.getInterpolatedV(16.0D - renderMaxX * 16.0D);
			maxU = icon.getInterpolatedU(renderMaxZ * 16.0D);
			maxV = icon.getInterpolatedV(16.0D - renderMinX * 16.0D);
			d9 = minV;
			d10 = maxV;
			d7 = minU;
			d8 = maxU;
			minV = maxV;
			maxV = d9;
		} else if (uvRotateTop == 2) {
			minU = icon.getInterpolatedU(16.0D - renderMaxZ * 16.0D);
			minV = icon.getInterpolatedV(renderMinX * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMinZ * 16.0D);
			maxV = icon.getInterpolatedV(renderMaxX * 16.0D);
			d7 = maxU;
			d8 = minU;
			minU = maxU;
			maxU = d8;
			d9 = maxV;
			d10 = minV;
		} else if (uvRotateTop == 3) {
			minU = icon.getInterpolatedU(16.0D - renderMinX * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMaxX * 16.0D);
			minV = icon.getInterpolatedV(16.0D - renderMinZ * 16.0D);
			maxV = icon.getInterpolatedV(16.0D - renderMaxZ * 16.0D);
			d7 = maxU;
			d8 = minU;
			d9 = minV;
			d10 = maxV;
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

		int i = brightness;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d12, d13, d15).tex(maxU, maxV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d14).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d14).tex(minU, minV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceZNeg(double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int brightness) {
		Tessellator tessellator = Tessellator.getInstance();

		double minU = icon.getInterpolatedU(renderMinX * 16.0D);
		double maxU = icon.getInterpolatedU(renderMaxX * 16.0D);

		if (field_152631_f) {
			maxU = icon.getInterpolatedU((1.0D - renderMinX) * 16.0D);
			minU = icon.getInterpolatedU((1.0D - renderMaxX) * 16.0D);
		}

		double minV = icon.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double maxV = icon.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (flipTexture) {
			double d7 = minU;
			minU = maxU;
			maxU = d7;
		}

		if (renderMinX < 0.0D || renderMaxX > 1.0D) {
			minU = icon.getMinU();
			maxU = icon.getMaxU();
		}

		if (renderMinY < 0.0D || renderMaxY > 1.0D) {
			minV = icon.getMinV();
			maxV = icon.getMaxV();
		}

		double d7 = maxU;
		double d8 = minU;
		double d9 = minV;
		double d10 = maxV;

		if (uvRotateEast == 2) {
			minU = icon.getInterpolatedU(renderMinY * 16.0D);
			maxU = icon.getInterpolatedU(renderMaxY * 16.0D);
			minV = icon.getInterpolatedV(16.0D - renderMinX * 16.0D);
			maxV = icon.getInterpolatedV(16.0D - renderMaxX * 16.0D);
			d9 = minV;
			d10 = maxV;
			d7 = minU;
			d8 = maxU;
			minV = maxV;
			maxV = d9;
		} else if (uvRotateEast == 1) {
			minU = icon.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMinY * 16.0D);
			minV = icon.getInterpolatedV(renderMaxX * 16.0D);
			maxV = icon.getInterpolatedV(renderMinX * 16.0D);
			d7 = maxU;
			d8 = minU;
			minU = maxU;
			maxU = d8;
			d9 = maxV;
			d10 = minV;
		} else if (uvRotateEast == 3) {
			minU = icon.getInterpolatedU(16.0D - renderMinX * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMaxX * 16.0D);
			minV = icon.getInterpolatedV(renderMaxY * 16.0D);
			maxV = icon.getInterpolatedV(renderMinY * 16.0D);
			d7 = maxU;
			d8 = minU;
			d9 = minV;
			d10 = maxV;
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

		int i = brightness;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d14, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d14, d15).tex(minU, minV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d15).tex(maxU, maxV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceZPos(double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int brightness) {
		Tessellator tessellator = Tessellator.getInstance();

		double minU = icon.getInterpolatedU(renderMinX * 16.0D);
		double maxU = icon.getInterpolatedU(renderMaxX * 16.0D);
		double minV = icon.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double maxV = icon.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (flipTexture) {
			double d7 = minU;
			minU = maxU;
			maxU = d7;
		}

		if (renderMinX < 0.0D || renderMaxX > 1.0D) {
			minU = icon.getMinU();
			maxU = icon.getMaxU();
		}

		if (renderMinY < 0.0D || renderMaxY > 1.0D) {
			minV = icon.getMinV();
			maxV = icon.getMaxV();
		}

		double d7 = maxU;
		double d8 = minU;
		double d9 = minV;
		double d10 = maxV;

		if (uvRotateWest == 1) {
			minU = icon.getInterpolatedU(renderMinY * 16.0D);
			maxV = icon.getInterpolatedV(16.0D - renderMinX * 16.0D);
			maxU = icon.getInterpolatedU(renderMaxY * 16.0D);
			minV = icon.getInterpolatedV(16.0D - renderMaxX * 16.0D);
			d9 = minV;
			d10 = maxV;
			d7 = minU;
			d8 = maxU;
			minV = maxV;
			maxV = d9;
		} else if (uvRotateWest == 2) {
			minU = icon.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			minV = icon.getInterpolatedV(renderMinX * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMinY * 16.0D);
			maxV = icon.getInterpolatedV(renderMaxX * 16.0D);
			d7 = maxU;
			d8 = minU;
			minU = maxU;
			maxU = d8;
			d9 = maxV;
			d10 = minV;
		} else if (uvRotateWest == 3) {
			minU = icon.getInterpolatedU(16.0D - renderMinX * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMaxX * 16.0D);
			minV = icon.getInterpolatedV(renderMaxY * 16.0D);
			maxV = icon.getInterpolatedV(renderMinY * 16.0D);
			d7 = maxU;
			d8 = minU;
			d9 = minV;
			d10 = maxV;
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

		int i = brightness;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d14, d15).tex(minU, minV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d13, d15).tex(maxU, maxV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d12, d14, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceXNeg(double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int brightness) {
		Tessellator tessellator = Tessellator.getInstance();

		double minU = icon.getInterpolatedU(renderMinZ * 16.0D);
		double maxU = icon.getInterpolatedU(renderMaxZ * 16.0D);
		double minV = icon.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double maxV = icon.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (flipTexture) {
			double d7 = minU;
			minU = maxU;
			maxU = d7;
		}

		if (renderMinZ < 0.0D || renderMaxZ > 1.0D) {
			minU = icon.getMinU();
			maxU = icon.getMaxU();
		}

		if (renderMinY < 0.0D || renderMaxY > 1.0D) {
			minV = icon.getMinV();
			maxV = icon.getMaxV();
		}

		double d7 = maxU;
		double d8 = minU;
		double d9 = minV;
		double d10 = maxV;

		if (uvRotateNorth == 1) {
			minU = icon.getInterpolatedU(renderMinY * 16.0D);
			minV = icon.getInterpolatedV(16.0D - renderMaxZ * 16.0D);
			maxU = icon.getInterpolatedU(renderMaxY * 16.0D);
			maxV = icon.getInterpolatedV(16.0D - renderMinZ * 16.0D);
			d9 = minV;
			d10 = maxV;
			d7 = minU;
			d8 = maxU;
			minV = maxV;
			maxV = d9;
		} else if (uvRotateNorth == 2) {
			minU = icon.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			minV = icon.getInterpolatedV(renderMinZ * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMinY * 16.0D);
			maxV = icon.getInterpolatedV(renderMaxZ * 16.0D);
			d7 = maxU;
			d8 = minU;
			minU = maxU;
			maxU = d8;
			d9 = maxV;
			d10 = minV;
		} else if (uvRotateNorth == 3) {
			minU = icon.getInterpolatedU(16.0D - renderMinZ * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMaxZ * 16.0D);
			minV = icon.getInterpolatedV(renderMaxY * 16.0D);
			maxV = icon.getInterpolatedV(renderMinY * 16.0D);
			d7 = maxU;
			d8 = minU;
			d9 = minV;
			d10 = maxV;
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

		int i = brightness;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d13, d15).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d14).tex(minU, minV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d12, d14).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d12, d15).tex(maxU, maxV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}

	public void renderFaceXPos(double x, double y, double z, TextureAtlasSprite icon, float red, float green, float blue, int brightness) {
		Tessellator tessellator = Tessellator.getInstance();

		double minU = icon.getInterpolatedU(renderMinZ * 16.0D);
		double maxU = icon.getInterpolatedU(renderMaxZ * 16.0D);

		if (field_152631_f) {
			maxU = icon.getInterpolatedU((1.0D - renderMinZ) * 16.0D);
			minU = icon.getInterpolatedU((1.0D - renderMaxZ) * 16.0D);
		}

		double minV = icon.getInterpolatedV(16.0D - renderMaxY * 16.0D);
		double maxV = icon.getInterpolatedV(16.0D - renderMinY * 16.0D);

		if (flipTexture) {
			double d7 = minU;
			minU = maxU;
			maxU = d7;
		}

		if (renderMinZ < 0.0D || renderMaxZ > 1.0D) {
			minU = icon.getMinU();
			maxU = icon.getMaxU();
		}

		if (renderMinY < 0.0D || renderMaxY > 1.0D) {
			minV = icon.getMinV();
			maxV = icon.getMaxV();
		}

		double d7 = maxU;
		double d8 = minU;
		double d9 = minV;
		double d10 = maxV;

		if (uvRotateSouth == 2) {
			minU = icon.getInterpolatedU(renderMinY * 16.0D);
			minV = icon.getInterpolatedV(16.0D - renderMinZ * 16.0D);
			maxU = icon.getInterpolatedU(renderMaxY * 16.0D);
			maxV = icon.getInterpolatedV(16.0D - renderMaxZ * 16.0D);
			d9 = minV;
			d10 = maxV;
			d7 = minU;
			d8 = maxU;
			minV = maxV;
			maxV = d9;
		} else if (uvRotateSouth == 1) {
			minU = icon.getInterpolatedU(16.0D - renderMaxY * 16.0D);
			minV = icon.getInterpolatedV(renderMaxZ * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMinY * 16.0D);
			maxV = icon.getInterpolatedV(renderMinZ * 16.0D);
			d7 = maxU;
			d8 = minU;
			minU = maxU;
			maxU = d8;
			d9 = maxV;
			d10 = minV;
		} else if (uvRotateSouth == 3) {
			minU = icon.getInterpolatedU(16.0D - renderMinZ * 16.0D);
			maxU = icon.getInterpolatedU(16.0D - renderMaxZ * 16.0D);
			minV = icon.getInterpolatedV(renderMaxY * 16.0D);
			maxV = icon.getInterpolatedV(renderMinY * 16.0D);
			d7 = maxU;
			d8 = minU;
			d9 = minV;
			d10 = maxV;
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

		int i = brightness;
		int j = i >> 16 & 0xFFFF;
		int k = i & 0xFFFF;

		tessellator.getBuffer().pos(d11, d12, d15).tex(d8, d10).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d12, d14).tex(maxU, maxV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d14).tex(d7, d9).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
		tessellator.getBuffer().pos(d11, d13, d15).tex(minU, minV).lightmap(j, k).color(red, green, blue, 1.0F).endVertex();
	}
}