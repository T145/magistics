package T145.magistics.client.renderers.pillars;

import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import T145.magistics.common.blocks.BlockChristmasLight;
import T145.magistics.common.tiles.pillars.TileChristmasLight;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChristmasLight extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	public static class Box {
		public float x, y, z, width, height, depth, tx, ty, tw, th;

		public Box(float xSize, float ySize, float zSize, float widthSize, float heightSize, float depthSize) {
			x = xSize;
			y = ySize;
			z = zSize;
			width = widthSize;
			height = heightSize;
			depth = depthSize;
			setTextureCoords(0F, 0F, 1F, 1F);
		}

		public void setTextureCoords(float xSize, float ySize, float widthSize, float heightSize) {
			tx = xSize;
			ty = ySize;
			tw = widthSize;
			th = heightSize;
		}

		public void render() {
			float[] texRows = new float[] { ty, ty + depth / (height + depth) * th, ty + th }, texColumns = new float[] { tx, tx + depth / (width * 2 + depth * 2) * tw, tx + (depth + width) / (width * 2 + depth * 2) * tw, tx + (depth + width * 2) / (width * 2 + depth * 2) * tw, tx + tw };

			glBegin(GL_QUADS);
			glTexCoord2f(texColumns[1], texRows[1]);
			glVertex3f(x, y, z);
			glTexCoord2f(texColumns[1], texRows[2]);
			glVertex3f(x, y + height, z);
			glTexCoord2f(texColumns[2], texRows[2]);
			glVertex3f(x + width, y + height, z);
			glTexCoord2f(texColumns[2], texRows[1]);
			glVertex3f(x + width, y, z);

			glTexCoord2f(texColumns[3], texRows[1]);
			glVertex3f(x + width, y, z + depth);
			glTexCoord2f(texColumns[3], texRows[2]);
			glVertex3f(x + width, y + height, z + depth);
			glTexCoord2f(texColumns[2], texRows[2]);
			glVertex3f(x, y + height, z + depth);
			glTexCoord2f(texColumns[2], texRows[1]);
			glVertex3f(x, y, z + depth);

			glTexCoord2f(texColumns[0], texRows[1]);
			glVertex3f(x, y, z + depth);
			glTexCoord2f(texColumns[0], texRows[2]);
			glVertex3f(x, y + height, z + depth);
			glTexCoord2f(texColumns[1], texRows[2]);
			glVertex3f(x, y + height, z);
			glTexCoord2f(texColumns[1], texRows[1]);
			glVertex3f(x, y, z);

			glTexCoord2f(texColumns[3], texRows[1]);
			glVertex3f(x + width, y, z);
			glTexCoord2f(texColumns[3], texRows[2]);
			glVertex3f(x + width, y + height, z);
			glTexCoord2f(texColumns[4], texRows[2]);
			glVertex3f(x + width, y + height, z + depth);
			glTexCoord2f(texColumns[4], texRows[1]);
			glVertex3f(x + width, y, z + depth);

			glTexCoord2f(texColumns[1], texRows[0]);
			glVertex3f(x, y + height, z);
			glTexCoord2f(texColumns[1], texRows[1]);
			glVertex3f(x, y + height, z + depth);
			glTexCoord2f(texColumns[2], texRows[1]);
			glVertex3f(x + width, y + height, z + depth);
			glTexCoord2f(texColumns[2], texRows[0]);
			glVertex3f(x + width, y + height, z);

			glTexCoord2f(texColumns[3], texRows[0]);
			glVertex3f(x + width, y, z);
			glTexCoord2f(texColumns[3], texRows[1]);
			glVertex3f(x + width, y, z + depth);
			glTexCoord2f(texColumns[2], texRows[1]);
			glVertex3f(x, y, z + depth);
			glTexCoord2f(texColumns[2], texRows[0]);
			glVertex3f(x, y, z);
			glEnd();
		}
	}

	ResourceLocation texture = new ResourceLocation("craftingpillars:textures/models/christmas_lights.png");
	Box b1, b2;

	public RenderChristmasLight() {
		b1 = new Box(6 / 16F, 14 / 16F, 6 / 16F, 4 / 16F, 2 / 16F, 4 / 16F);
		b1.setTextureCoords(0 / 32F, 0 / 32F, 16 / 32F, 6 / 32F);
		b2 = new Box(4 / 16F, 6 / 16F, 4 / 16F, 8 / 16F, 8 / 16F, 8 / 16F);
		b2.setTextureCoords(0 / 32F, 6 / 32F, 32 / 32F, 16 / 32F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int i) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockChristmasLight.renderID;
	}

	public void render(int meta, Color color) {
		glPushMatrix();
		glTranslatef(0.5F, 0.5F, 0.5F);
		if (meta == 1)
			glRotatef(180F, 1F, 0F, 0F);
		if (meta == 2)
			glRotatef(90F, 1F, 0F, 0F);
		if (meta == 3)
			glRotatef(-90F, 1F, 0F, 0F);
		if (meta == 4)
			glRotatef(-90F, 0F, 0F, 1F);
		if (meta == 5)
			glRotatef(90F, 0F, 0F, 1F);
		glTranslatef(-0.5F, -0.5F, -0.5F);

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		b1.render();

		glDisable(GL_LIGHTING);
		glColor3f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F);
		b2.render();
		glEnable(GL_LIGHTING);
		glPopMatrix();
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		glPushMatrix();
		glTranslatef(-0.5F, -0.5F, -0.5F);
		render(meta, Color.red);
		glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		glPushMatrix();
		glTranslated(x, y, z);
		render(tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord), new Color(TileChristmasLight.colors[((TileChristmasLight) tile).color]));
		glPopMatrix();
	}
}