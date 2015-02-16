package T145.magistics.client.renderers.pillars;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ENABLE_BIT;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslated;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import T145.magistics.common.blocks.BlockChristmasPresent;
import T145.magistics.common.tiles.pillars.TileChristmasPresent;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChristmasPresent extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	private ResourceLocation texture = new ResourceLocation("craftingpillars:textures/models/present.png"), overlay = new ResourceLocation("craftingpillars:textures/models/presentOverlay.png");

	public static ModelBase model = new ModelBase() {};
	private ModelRenderer PresentTop, PresentBottom, PresentTop2, PresentBottom2;

	public RenderChristmasPresent() {
		model.textureWidth = 64;
		model.textureHeight = 64;

		PresentTop = new ModelRenderer(model, 0, 0);
		PresentTop.addBox(0F, 0F, 0F, 16, 5, 16);
		PresentTop.setRotationPoint(-8F, 8F, -8F);
		PresentTop.setTextureSize(64, 64);
		PresentTop.mirror = true;
		setRotation(PresentTop, 0F, 0F, 0F);
		PresentBottom = new ModelRenderer(model, 5, 34);
		PresentBottom.addBox(0F, 0F, 0F, 14, 11, 14);
		PresentBottom.setRotationPoint(-7F, 13F, -7F);
		PresentBottom.setTextureSize(64, 64);
		PresentBottom.mirror = true;
		setRotation(PresentBottom, 0F, 0F, 0F);

		PresentTop2 = new ModelRenderer(model, 0, 0);
		PresentTop2.addBox(0F, 0F, 0F, 16, 2, 16);
		PresentTop2.setRotationPoint(-8F, 17F, -8F);
		PresentTop2.setTextureSize(64, 64);
		PresentTop2.mirror = true;
		setRotation(PresentTop2, 0F, 0F, 0F);
		PresentBottom2 = new ModelRenderer(model, 5, 34);
		PresentBottom2.addBox(0F, 0F, 0F, 14, 5, 14);
		PresentBottom2.setRotationPoint(-7F, 19F, -7F);
		PresentBottom2.setTextureSize(64, 64);
		PresentBottom2.mirror = true;
		setRotation(PresentBottom2, 0F, 0F, 0F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void render(float f, Color color1, Color color2, boolean model) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		glColor3f(color1.getRed() / 255F, color1.getGreen() / 255F, color1.getBlue() / 255F);
		if (model) {
			PresentBottom2.render(f);
			PresentTop2.render(f);
		} else {
			PresentBottom.render(f);
			PresentTop.render(f);
		}

		Minecraft.getMinecraft().renderEngine.bindTexture(overlay);
		glColor3f(color2.getRed() / 255F, color2.getGreen() / 255F,
				color2.getBlue() / 255F);
		if (model) {
			PresentBottom2.render(f);
			PresentTop2.render(f);
		} else {
			PresentBottom.render(f);
			PresentTop.render(f);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		TileChristmasPresent present = (TileChristmasPresent) tile;
		glPushMatrix();
		glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		glRotatef(180F, 1F, 0F, 0F);

		render(0.0625F, new Color(TileChristmasPresent.colors[present.color * 2]), new Color(TileChristmasPresent.colors[present.color * 2 + 1]), present.getBlockMetadata() == 1);
		glPopMatrix();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);

		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		render(0.0625F, new Color(TileChristmasPresent.colors[0]), new Color(TileChristmasPresent.colors[1]), false);

		glPopAttrib();
		glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int i) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockChristmasPresent.renderID;
	}
}