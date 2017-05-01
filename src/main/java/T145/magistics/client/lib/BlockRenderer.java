package T145.magistics.client.lib;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockRenderer {

	public static double W1 = 0.0625D;
	public static double W2 = 0.125D;
	public static double W3 = 0.1875D;
	public static double W4 = 0.25D;
	public static double W5 = 0.3125D;
	public static double W6 = 0.375D;
	public static double W7 = 0.4375D;
	public static double W8 = 0.5D;
	public static double W9 = 0.5625D;
	public static double W10 = 0.625D;
	public static double W11 = 0.6875D;
	public static double W12 = 0.75D;
	public static double W13 = 0.8125D;
	public static double W14 = 0.875D;
	public static double W15 = 0.9375D;
	public static double W16 = 0.9475D;

	public static int getFrontAngle(EnumFacing facing, boolean rotateNorthSouth, boolean rotateEastWest) {
		switch (facing.ordinal()) {
		case 2:
			return rotateEastWest ? -180 : 180;
		case 4:
			return rotateNorthSouth ? 90 : -90;
		case 5:
			return rotateNorthSouth ? -90 : 90;
		default:
			return 0;
		}
	}

	public static TextureAtlasSprite getTextureFromBlock(Block block, int meta) {
		IBlockState state = block.getStateFromMeta(meta);
		return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(state);
	}

	public static TextureAtlasSprite getTextureFromBlockstate(IBlockState state) {
		return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(state);
	}

	public static void renderBlock(TextureAtlasSprite texture, double minX, double minY, double minZ) {
		if (texture == null) {
			return;
		}

		double maxX = minX + 1;
		double maxY = minY + 1;
		double maxZ = minZ + 1;
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buffer = tessellator.getBuffer();
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

		float texMinU = texture.getMinU();
		float texMinV = texture.getMinV();
		float texMaxU = texture.getMaxU();
		float texMaxV = texture.getMaxV();

		buffer.pos(minX, minY, minZ).tex(texMinU, texMinV).endVertex();
		buffer.pos(maxX, minY, minZ).tex(texMaxU, texMinV).endVertex();
		buffer.pos(maxX, minY, maxZ).tex(texMaxU, texMaxV).endVertex();
		buffer.pos(minX, minY, maxZ).tex(texMinU, texMaxV).endVertex();

		buffer.pos(minX, maxY, maxZ).tex(texMinU, texMaxV).endVertex();
		buffer.pos(maxX, maxY, maxZ).tex(texMaxU, texMaxV).endVertex();
		buffer.pos(maxX, maxY, minZ).tex(texMaxU, texMinV).endVertex();
		buffer.pos(minX, maxY, minZ).tex(texMinU, texMinV).endVertex();

		buffer.pos(maxX, minY, minZ).tex(texMinU, texMaxV).endVertex();
		buffer.pos(minX, minY, minZ).tex(texMaxU, texMaxV).endVertex();
		buffer.pos(minX, maxY, minZ).tex(texMaxU, texMinV).endVertex();
		buffer.pos(maxX, maxY, minZ).tex(texMinU, texMinV).endVertex();

		buffer.pos(minX, minY, maxZ).tex(texMinU, texMaxV).endVertex();
		buffer.pos(maxX, minY, maxZ).tex(texMaxU, texMaxV).endVertex();
		buffer.pos(maxX, maxY, maxZ).tex(texMaxU, texMinV).endVertex();
		buffer.pos(minX, maxY, maxZ).tex(texMinU, texMinV).endVertex();

		buffer.pos(minX, minY, minZ).tex(texMinU, texMaxV).endVertex();
		buffer.pos(minX, minY, maxZ).tex(texMaxU, texMaxV).endVertex();
		buffer.pos(minX, maxY, maxZ).tex(texMaxU, texMinV).endVertex();
		buffer.pos(minX, maxY, minZ).tex(texMinU, texMinV).endVertex();

		buffer.pos(maxX, minY, maxZ).tex(texMinU, texMaxV).endVertex();
		buffer.pos(maxX, minY, minZ).tex(texMaxU, texMaxV).endVertex();
		buffer.pos(maxX, maxY, minZ).tex(texMaxU, texMinV).endVertex();
		buffer.pos(maxX, maxY, maxZ).tex(texMinU, texMinV).endVertex();

		tessellator.draw();
	}

	public static void renderItem(World world, ItemStack stack, float partialTicks) {
		RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();

		if (stack != null) {
			GlStateManager.translate(0.5D, 1.5D, 0.5D);
			EntityItem entityitem = new EntityItem(world, 0D, 0D, 0D, stack);
			entityitem.getEntityItem().setCount(1);
			entityitem.hoverStart = 0F;
			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();

			float rotation = (float) (720 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

			GlStateManager.rotate(rotation, 0F, 1F, 0);
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.pushAttrib();
			RenderHelper.enableStandardItemLighting();
			itemRenderer.renderItem(entityitem.getEntityItem(), ItemCameraTransforms.TransformType.FIXED);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.popAttrib();

			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
	}
}