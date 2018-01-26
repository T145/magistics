package T145.magistics.client.lib;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public class Render {

	private Render() {}

	public static final double W1 = 0.0625D;
	public static final double W2 = 0.125D;
	public static final double W3 = 0.1875D;
	public static final double W4 = 0.25D;
	public static final double W5 = 0.3125D;
	public static final double W6 = 0.375D;
	public static final double W7 = 0.4375D;
	public static final double W8 = 0.5D;
	public static final double W9 = 0.5625D;
	public static final double W10 = 0.625D;
	public static final double W11 = 0.6875D;
	public static final double W12 = 0.75D;
	public static final double W13 = 0.8125D;
	public static final double W14 = 0.875D;
	public static final double W15 = 0.9375D;
	public static final double W16 = 0.9475D;

	public static final ResourceLocation[] DESTROY_STAGES = new ResourceLocation[] {
			new ResourceLocation("textures/blocks/destroy_stage_0.png"),
			new ResourceLocation("textures/blocks/destroy_stage_1.png"),
			new ResourceLocation("textures/blocks/destroy_stage_2.png"),
			new ResourceLocation("textures/blocks/destroy_stage_3.png"),
			new ResourceLocation("textures/blocks/destroy_stage_4.png"),
			new ResourceLocation("textures/blocks/destroy_stage_5.png"),
			new ResourceLocation("textures/blocks/destroy_stage_6.png"),
			new ResourceLocation("textures/blocks/destroy_stage_7.png"),
			new ResourceLocation("textures/blocks/destroy_stage_8.png"),
			new ResourceLocation("textures/blocks/destroy_stage_9.png")
	};

	private static final ModelChest CHEST_MODEL = new ModelChest();

	public static void bindTexture(ResourceLocation texture) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
	}

	public static void rotate(EnumFacing facing) {
		switch (facing) {
		case NORTH:
			GlStateManager.rotate(180F, 0F, 1F, 0F);
			break;
		case SOUTH:
			GlStateManager.rotate(0F, 0F, 1F, 0F);
			break;
		case WEST:
			GlStateManager.rotate(90F, 0F, 1F, 0F);
			break;
		case EAST:
			GlStateManager.rotate(270F, 0F, 1F, 0F);
			break;
		default:
			GlStateManager.rotate(0F, 0F, 1F, 0F);
			break;
		}
	}

	public static void chest(ResourceLocation modelTexture, EnumFacing facing, float lidAngle, float prevLidAngle, double x, double y, double z, float partialTicks, int destroyStage) {
		if (destroyStage >= 0) {
			bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(GL11.GL_TEXTURE);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4F, 4F, 1F);
			GlStateManager.translate(W1, W1, W1);
			GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		} else {
			bindTexture(modelTexture);
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.translate(x, y + 1F, z + 1F);
		GlStateManager.scale(1F, -1F, -1F);
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		rotate(facing);
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		float f = prevLidAngle + (lidAngle - prevLidAngle) * partialTicks;
		f = 1F - f;
		f = 1F - f * f * f;
		CHEST_MODEL.chestLid.rotateAngleX = -(f * ((float) Math.PI / 2F));
		CHEST_MODEL.renderAll();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		GlStateManager.color(1F, 1F, 1F, 1F);

		if (destroyStage >= 0) {
			GlStateManager.matrixMode(GL11.GL_MODELVIEW);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}

	public static TextureAtlasSprite getTextureFromBlock(Block block, int meta) {
		return getTextureFromBlockstate(block.getStateFromMeta(meta));
	}

	public static TextureAtlasSprite getTextureFromBlockstate(IBlockState state) {
		return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(state);
	}

	public static void cube(TextureAtlasSprite icon, AxisAlignedBB box) {
		for (EnumFacing facing : EnumFacing.VALUES) {
			face(facing, icon, box);
		}
	}

	public static void cube(TextureAtlasSprite icon, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		for (EnumFacing facing : EnumFacing.VALUES) {
			face(facing, icon, minX, minY, minZ, maxX, maxY, maxZ);
		}
	}

	public static void face(EnumFacing facing, TextureAtlasSprite icon, AxisAlignedBB box) {
		face(facing, icon, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
	}

	public static void face(EnumFacing facing, TextureAtlasSprite icon, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		Tessellator tess = Tessellator.getInstance();
		BufferBuilder buffer = tess.getBuffer();

		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		renderFace(buffer, minX, minY, minZ, maxX, maxY, maxZ, facing.getIndex(), icon.getMinU(), icon.getMaxU(), icon.getMinV(), icon.getMaxV());
		tess.draw();
	}

	private static void renderFace(BufferBuilder r, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, int side, double tx1, double tx2, double ty1, double ty2) {
		switch (side) {
		case 0:
			r.pos(minX, minY, minZ).tex(tx1, ty1).endVertex();
			r.pos(maxX, minY, minZ).tex(tx2, ty1).endVertex();
			r.pos(maxX, minY, maxZ).tex(tx2, ty2).endVertex();
			r.pos(minX, minY, maxZ).tex(tx1, ty2).endVertex();
			break;
		case 1:
			r.pos(maxX, maxY, minZ).tex(tx2, ty1).endVertex();
			r.pos(minX, maxY, minZ).tex(tx1, ty1).endVertex();
			r.pos(minX, maxY, maxZ).tex(tx1, ty2).endVertex();
			r.pos(maxX, maxY, maxZ).tex(tx2, ty2).endVertex();
			break;
		case 2:
			r.pos(minX, maxY, minZ).tex(tx2, ty1).endVertex();
			r.pos(maxX, maxY, minZ).tex(tx1, ty1).endVertex();
			r.pos(maxX, minY, minZ).tex(tx1, ty2).endVertex();
			r.pos(minX, minY, minZ).tex(tx2, ty2).endVertex();
			break;
		case 3:
			r.pos(maxX, maxY, maxZ).tex(tx2, ty1).endVertex();
			r.pos(minX, maxY, maxZ).tex(tx1, ty1).endVertex();
			r.pos(minX, minY, maxZ).tex(tx1, ty2).endVertex();
			r.pos(maxX, minY, maxZ).tex(tx2, ty2).endVertex();
			break;
		case 4:
			r.pos(minX, maxY, maxZ).tex(tx2, ty1).endVertex();
			r.pos(minX, maxY, minZ).tex(tx1, ty1).endVertex();
			r.pos(minX, minY, minZ).tex(tx1, ty2).endVertex();
			r.pos(minX, minY, maxZ).tex(tx2, ty2).endVertex();
			break;
		case 5:
			r.pos(maxX, maxY, minZ).tex(tx2, ty1).endVertex();
			r.pos(maxX, maxY, maxZ).tex(tx1, ty1).endVertex();
			r.pos(maxX, minY, maxZ).tex(tx1, ty2).endVertex();
			r.pos(maxX, minY, minZ).tex(tx2, ty2).endVertex();
			break;
		}
	}
}