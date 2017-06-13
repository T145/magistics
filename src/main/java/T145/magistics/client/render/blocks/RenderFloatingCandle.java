package T145.magistics.client.render.blocks;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import T145.magistics.Magistics;
import T145.magistics.blocks.cosmetic.BlockFloatingCandle;
import T145.magistics.client.render.models.blocks.ModelFloatingCandle;
import T145.magistics.tiles.cosmetic.TileFloatingCandle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFloatingCandle extends TileEntitySpecialRenderer<TileFloatingCandle> {

	private final ModelFloatingCandle model = new ModelFloatingCandle();

	@Override
	public void renderTileEntityAt(TileFloatingCandle te, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();

		int ticks = Minecraft.getMinecraft().player.ticksExisted;
		int offset = (int) (te.getPos().getX() + te.getPos().getY() + te.getPos().getZ());
		float move = 0.2F * MathHelper.sin(((offset * 10) + ticks) / 30.0F);
		IBlockState state = te.getWorld().getBlockState(te.getPos());

		GlStateManager.translate(x, y + move, z);
		bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/floating_candle_stub.png"));
		model.renderWick();
		bindTexture(new ResourceLocation(Magistics.MODID, "textures/models/floating_candle.png"));
		Color color = new Color(((EnumDyeColor) state.getValue(((BlockFloatingCandle) state.getBlock()).variant)).getMapColor().colorValue);
		GL11.glColor3ub((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
		model.renderAll(ticks);
		GlStateManager.popMatrix();
	}
}