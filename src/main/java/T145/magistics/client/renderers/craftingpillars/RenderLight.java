package T145.magistics.client.renderers.craftingpillars;

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
import T145.magistics.common.blocks.craftingpillars.BlockChristmasLight;
import T145.magistics.common.tiles.craftingpillars.TileChristmasLight;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderLight extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	public static class Box
	{
		public float x, y, z, width, height, depth;
		public float tx, ty, tw, th;

		public Box(float x, float y, float z, float width, float height, float depth)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.width = width;
			this.height = height;
			this.depth = depth;
			this.setTextureCoords(0F, 0F, 1F, 1F);
		}

		public void setTextureCoords(float x, float y, float width, float height)
		{
			this.tx = x;
			this.ty = y;
			this.tw = width;
			this.th = height;
		}

		public void render()
		{
			float[] texRows = new float[]{this.ty, this.ty+this.depth/(this.height+this.depth)*this.th, this.ty+this.th};
			float[] texColumns = new float[]{this.tx, this.tx+this.depth/(this.width*2+this.depth*2)*this.tw, this.tx+(this.depth+this.width)/(this.width*2+this.depth*2)*this.tw, this.tx+(this.depth+this.width*2)/(this.width*2+this.depth*2)*this.tw, this.tx+this.tw};

			glBegin(GL_QUADS);
			glTexCoord2f(texColumns[1], texRows[1]);
			glVertex3f(this.x, this.y, this.z);
			glTexCoord2f(texColumns[1], texRows[2]);
			glVertex3f(this.x, this.y+this.height, this.z);
			glTexCoord2f(texColumns[2], texRows[2]);
			glVertex3f(this.x+this.width, this.y+this.height, this.z);
			glTexCoord2f(texColumns[2], texRows[1]);
			glVertex3f(this.x+this.width, this.y, this.z);

			glTexCoord2f(texColumns[3], texRows[1]);
			glVertex3f(this.x+this.width, this.y, this.z+this.depth);
			glTexCoord2f(texColumns[3], texRows[2]);
			glVertex3f(this.x+this.width, this.y+this.height, this.z+this.depth);
			glTexCoord2f(texColumns[2], texRows[2]);
			glVertex3f(this.x, this.y+this.height, this.z+this.depth);
			glTexCoord2f(texColumns[2], texRows[1]);
			glVertex3f(this.x, this.y, this.z+this.depth);

			glTexCoord2f(texColumns[0], texRows[1]);
			glVertex3f(this.x, this.y, this.z+this.depth);
			glTexCoord2f(texColumns[0], texRows[2]);
			glVertex3f(this.x, this.y+this.height, this.z+this.depth);
			glTexCoord2f(texColumns[1], texRows[2]);
			glVertex3f(this.x, this.y+this.height, this.z);
			glTexCoord2f(texColumns[1], texRows[1]);
			glVertex3f(this.x, this.y, this.z);

			glTexCoord2f(texColumns[3], texRows[1]);
			glVertex3f(this.x+this.width, this.y, this.z);
			glTexCoord2f(texColumns[3], texRows[2]);
			glVertex3f(this.x+this.width, this.y+this.height, this.z);
			glTexCoord2f(texColumns[4], texRows[2]);
			glVertex3f(this.x+this.width, this.y+this.height, this.z+this.depth);
			glTexCoord2f(texColumns[4], texRows[1]);
			glVertex3f(this.x+this.width, this.y, this.z+this.depth);

			glTexCoord2f(texColumns[1], texRows[0]);
			glVertex3f(this.x, this.y+this.height, this.z);
			glTexCoord2f(texColumns[1], texRows[1]);
			glVertex3f(this.x, this.y+this.height, this.z+this.depth);
			glTexCoord2f(texColumns[2], texRows[1]);
			glVertex3f(this.x+this.width, this.y+this.height, this.z+this.depth);
			glTexCoord2f(texColumns[2], texRows[0]);
			glVertex3f(this.x+this.width, this.y+this.height, this.z);

			glTexCoord2f(texColumns[3], texRows[0]);
			glVertex3f(this.x+this.width, this.y, this.z);
			glTexCoord2f(texColumns[3], texRows[1]);
			glVertex3f(this.x+this.width, this.y, this.z+this.depth);
			glTexCoord2f(texColumns[2], texRows[1]);
			glVertex3f(this.x, this.y, this.z+this.depth);
			glTexCoord2f(texColumns[2], texRows[0]);
			glVertex3f(this.x, this.y, this.z);
			glEnd();
		}
	}

	ResourceLocation texture = new ResourceLocation("craftingpillars:textures/models/christmas_lights.png");
	Box b1, b2;

	public RenderLight()
	{
		this.b1 = new Box(6/16F, 14/16F, 6/16F, 4/16F, 2/16F, 4/16F);
		this.b1.setTextureCoords(0/32F, 0/32F, 16/32F, 6/32F);
		this.b2 = new Box(4/16F, 6/16F, 4/16F, 8/16F, 8/16F, 8/16F);
		this.b2.setTextureCoords(0/32F, 6/32F, 32/32F, 16/32F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int i)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return BlockChristmasLight.renderID;
	}

	public void render(int meta, Color color)
	{
		glPushMatrix();
		glTranslatef(0.5F, 0.5F, 0.5F);
		if(meta == 1)
			glRotatef(180F, 1F, 0F, 0F);
		if(meta == 2)
			glRotatef(90F, 1F, 0F, 0F);
		if(meta == 3)
			glRotatef(-90F, 1F, 0F, 0F);
		if(meta == 4)
			glRotatef(-90F, 0F, 0F, 1F);
		if(meta == 5)
			glRotatef(90F, 0F, 0F, 1F);
		glTranslatef(-0.5F, -0.5F, -0.5F);

		Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
		this.b1.render();

		glDisable(GL_LIGHTING);
		glColor3f(color.getRed()/255F, color.getGreen()/255F, color.getBlue()/255F);
		this.b2.render();
		glEnable(GL_LIGHTING);
		glPopMatrix();
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer)
	{
		glPushMatrix();
		glTranslatef(-0.5F, -0.5F, -0.5F);
		this.render(meta, Color.red);
		glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick)
	{
		glPushMatrix();
		glTranslated(x, y, z);
		this.render(tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord), new Color(TileChristmasLight.colors[((TileChristmasLight)tile).color]));
		glPopMatrix();
	}
}
