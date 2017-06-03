package T145.magistics.client.lib;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

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

	public static TextureAtlasSprite getTextureFromBlock(Block block, int meta) {
		IBlockState state = block.getStateFromMeta(meta);
		return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(state);
	}

	public static TextureAtlasSprite getTextureFromBlockstate(IBlockState state) {
		return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(state);
	}
}