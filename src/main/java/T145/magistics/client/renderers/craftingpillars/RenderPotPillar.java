package T145.magistics.client.renderers.craftingpillars;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ENABLE_BIT;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslated;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import T145.magistics.client.lib.craftingpillars.RenderingHelper;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.craftingpillars.BlockPillarPot;
import T145.magistics.common.tiles.craftingpillars.TilePillarPot;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPotPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	private ResourceLocation texture, frozenTexture;

	public static ModelBase model = new ModelBase() {};
	private ModelRenderer bottom, pillarbottom, pillar, pillartop, top, pot, pillarBottom, BunnyTail1, BunnyTail2, BunnyTail3, BunnyEar1, BunnyEar2, Icicle1A, Icicle1B, Icicle1C, Icicle2A, Icicle2B, Icicle2C, Icicle3A, Icicle3B, Icicle4A, Icicle4B, Icicle5A, Icicle5B, Icicle5C, Icicle6A, Icicle6B, Icicle6C, Icicle7A, Icicle7B, Icicle7C, Icicle8A, Icicle8B, Icicle8C, Icicle8D, Icicle9A, Icicle9B, Icicle10A, Icicle10B, Icicle10C, Icicle11A, Icicle11B, Icicle11C;

	private Random random = new Random();
	private RenderingHelper.ItemRender itemRenderer = new RenderingHelper.ItemRender(false, true);
	private RenderingHelper.ItemRender resultRenderer = new RenderingHelper.ItemRender(true, true);

	public RenderPotPillar(String modelTexture) {
		texture = new ResourceLocation("craftingpillars:textures/models/" + modelTexture + ".png");
		renderPillar();
	}

	public RenderPotPillar(String modelTexture, String frozenModelTexture) {
		texture = new ResourceLocation("craftingpillars:textures/models/" + modelTexture + ".png");
		frozenTexture = new ResourceLocation("craftingpillars:textures/models/" + frozenModelTexture + ".png");
		renderPillar();
	}

	public void renderPillar() {
		if (Magistics.proxy.winter && frozenTexture != null)
			texture = frozenTexture;

		model.textureWidth = 128;
		model.textureHeight = 64;

		pillarBottom = new ModelRenderer(model, 0, 33);
		pillarBottom.addBox(0F, 0F, 0F, 12, 3, 12);
		pillarBottom.setRotationPoint(-6F, 21F, 6F);
		pillarBottom.setTextureSize(128, 64);
		pillarBottom.mirror = true;
		setRotation(pillarBottom, 0F, 1.570796F, 0F);

		bottom = new ModelRenderer(model, 0, 0);
		bottom.addBox(-8F, -1F, -8F, 16, 2, 16);
		bottom.setRotationPoint(0F, 23F, 0F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		pillarbottom = new ModelRenderer(model, 0, 18);
		pillarbottom.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillarbottom.setRotationPoint(0F, 21F, 0F);
		pillarbottom.setTextureSize(128, 64);
		pillarbottom.mirror = true;
		setRotation(pillarbottom, 0F, 0F, 0F);
		pillar = new ModelRenderer(model, 0, 33);
		pillar.addBox(-6F, 0F, -6F, 12, 10, 12);
		pillar.setRotationPoint(0F, 11F, 0F);
		pillar.setTextureSize(128, 64);
		pillar.mirror = true;
		setRotation(pillar, 0F, 0F, 0F);
		pillartop = new ModelRenderer(model, 0, 18);
		pillartop.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillartop.setRotationPoint(0F, 10F, 0F);
		pillartop.setTextureSize(128, 64);
		pillartop.mirror = true;
		setRotation(pillartop, 0F, 0F, 0F);
		top = new ModelRenderer(model, 64, 0);
		top.addBox(-8F, -1F, -8F, 16, 2, 16);
		top.setRotationPoint(0F, 9F, 0F);
		top.setTextureSize(128, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);

		pot = new ModelRenderer(model, 64, 18);
		pot.addBox(-3F, 0F, -3F, 6, 5, 6);
		pot.setRotationPoint(0F, 3F, 0F);
		pot.setTextureSize(128, 64);
		pot.mirror = true;
		setRotation(pot, 0F, 0F, 0F);

		if (Magistics.proxy.easter) {
			BunnyTail1 = new ModelRenderer(model, 0, 35);
			BunnyTail1.addBox(0F, 0F, 0F, 2, 4, 2);
			BunnyTail1.setRotationPoint(-1F, 18F, 7F);
			BunnyTail1.setTextureSize(128, 64);
			BunnyTail1.mirror = true;
			setRotation(BunnyTail1, 0F, 0F, 0F);
			BunnyTail2 = new ModelRenderer(model, 0, 33);
			BunnyTail2.addBox(0F, 0F, 0F, 4, 2, 2);
			BunnyTail2.setRotationPoint(-2F, 19F, 7F);
			BunnyTail2.setTextureSize(128, 64);
			BunnyTail2.mirror = true;
			setRotation(BunnyTail2, 0F, 0F, 0F);
			BunnyTail3 = new ModelRenderer(model, 0, 36);
			BunnyTail3.addBox(0F, 0F, 0F, 2, 2, 4);
			BunnyTail3.setRotationPoint(-1F, 19F, 6F);
			BunnyTail3.setTextureSize(128, 64);
			BunnyTail3.mirror = true;
			setRotation(BunnyTail3, 0F, 0F, 0F);
			BunnyEar1 = new ModelRenderer(model, 1, 18);
			BunnyEar1.addBox(-1.5F, -9F, -1F, 3, 10, 1);
			BunnyEar1.setRotationPoint(3.5F, 9F, 9F);
			BunnyEar1.setTextureSize(128, 64);
			BunnyEar1.mirror = true;
			setRotation(BunnyEar1, 0F, 0F, 0F);
			BunnyEar2 = new ModelRenderer(model, 1, 18);
			BunnyEar2.addBox(-1.5F, -9F, -1F, 3, 10, 1);
			BunnyEar2.setRotationPoint(-3.5F, 9F, 9F);
			BunnyEar2.setTextureSize(128, 64);
			BunnyEar2.mirror = true;
			setRotation(BunnyEar2, 0F, 0F, 0F);
		}

		// Winter
		Icicle1A = new ModelRenderer(model, 122, 60);
		Icicle1A.addBox(0F, 0F, 0F, 1, 2, 2);
		Icicle1A.setRotationPoint(6F, 11F, -5F);
		Icicle1A.setTextureSize(128, 64);
		Icicle1A.mirror = true;
		setRotation(Icicle1A, 0F, 0F, 0F);
		Icicle1B = new ModelRenderer(model, 124, 58);
		Icicle1B.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle1B.setRotationPoint(6F, 11F, -3F);
		Icicle1B.setTextureSize(128, 64);
		Icicle1B.mirror = true;
		setRotation(Icicle1B, 0F, 0F, 0F);
		Icicle1C = new ModelRenderer(model, 124, 56);
		Icicle1C.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle1C.setRotationPoint(6F, 13F, -4F);
		Icicle1C.setTextureSize(128, 64);
		Icicle1C.mirror = true;
		setRotation(Icicle1C, 0F, 0F, 0F);
		Icicle2A = new ModelRenderer(model, 122, 50);
		Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
		Icicle2A.setRotationPoint(6F, 11F, 0F);
		Icicle2A.setTextureSize(128, 64);
		Icicle2A.mirror = true;
		setRotation(Icicle2A, 0F, 0F, 0F);
		Icicle2B = new ModelRenderer(model, 124, 47);
		Icicle2B.addBox(0F, 0F, 0F, 1, 2, 1);
		Icicle2B.setRotationPoint(6F, 13F, 0F);
		Icicle2B.setTextureSize(128, 64);
		Icicle2B.mirror = true;
		setRotation(Icicle2B, 0F, 0F, 0F);
		Icicle2C = new ModelRenderer(model, 124, 54);
		Icicle2C.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle2C.setRotationPoint(6F, 11F, -1F);
		Icicle2C.setTextureSize(128, 64);
		Icicle2C.mirror = true;
		setRotation(Icicle2C, 0F, 0F, 0F);
		Icicle3A = new ModelRenderer(model, 120, 43);
		Icicle3A.addBox(0F, 0F, 0F, 1, 1, 3);
		Icicle3A.setRotationPoint(6F, 11F, 3F);
		Icicle3A.setTextureSize(128, 64);
		Icicle3A.mirror = true;
		setRotation(Icicle3A, 0F, 0F, 0F);
		Icicle3B = new ModelRenderer(model, 124, 40);
		Icicle3B.addBox(0F, 0F, 0F, 1, 2, 1);
		Icicle3B.setRotationPoint(6F, 12F, 4F);
		Icicle3B.setTextureSize(128, 64);
		Icicle3B.mirror = true;
		setRotation(Icicle3B, 0F, 0F, 0F);
		Icicle4A = new ModelRenderer(model, 122, 38);
		Icicle4A.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle4A.setRotationPoint(3F, 11F, 6F);
		Icicle4A.setTextureSize(128, 64);
		Icicle4A.mirror = true;
		setRotation(Icicle4A, 0F, 0F, 0F);
		Icicle4B = new ModelRenderer(model, 124, 36);
		Icicle4B.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle4B.setRotationPoint(4F, 12F, 6F);
		Icicle4B.setTextureSize(128, 64);
		Icicle4B.mirror = true;
		setRotation(Icicle4B, 0F, 0F, 0F);
		Icicle5A = new ModelRenderer(model, 114, 61);
		Icicle5A.addBox(0F, 0F, 0F, 3, 2, 1);
		Icicle5A.setRotationPoint(-1F, 11F, 6F);
		Icicle5A.setTextureSize(128, 64);
		Icicle5A.mirror = true;
		setRotation(Icicle5A, 0F, 0F, 0F);
		Icicle5B = new ModelRenderer(model, 116, 59);
		Icicle5B.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle5B.setRotationPoint(-1F, 13F, 6F);
		Icicle5B.setTextureSize(128, 64);
		Icicle5B.mirror = true;
		setRotation(Icicle5B, 0F, 0F, 0F);
		Icicle5C = new ModelRenderer(model, 120, 56);
		Icicle5C.addBox(0F, 0F, 0F, 1, 2, 1);
		Icicle5C.setRotationPoint(0F, 14F, 6F);
		Icicle5C.setTextureSize(128, 64);
		Icicle5C.mirror = true;
		setRotation(Icicle5C, 0F, 0F, 0F);
		Icicle6A = new ModelRenderer(model, 114, 54);
		Icicle6A.addBox(0F, 0F, 0F, 4, 1, 1);
		Icicle6A.setRotationPoint(-5F, 11F, 6F);
		Icicle6A.setTextureSize(128, 64);
		Icicle6A.mirror = true;
		setRotation(Icicle6A, 0F, 0F, 0F);
		Icicle6B = new ModelRenderer(model, 116, 52);
		Icicle6B.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle6B.setRotationPoint(-4F, 12F, 6F);
		Icicle6B.setTextureSize(128, 64);
		Icicle6B.mirror = true;
		setRotation(Icicle6B, 0F, 0F, 0F);
		Icicle6C = new ModelRenderer(model, 118, 50);
		Icicle6C.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle6C.setRotationPoint(-4F, 13F, 6F);
		Icicle6C.setTextureSize(128, 64);
		Icicle6C.mirror = true;
		setRotation(Icicle6C, 0F, 0F, 0F);
		Icicle7A = new ModelRenderer(model, 104, 59);
		Icicle7A.addBox(0F, 0F, 0F, 1, 1, 4);
		Icicle7A.setRotationPoint(-7F, 11F, 1F);
		Icicle7A.setTextureSize(128, 64);
		Icicle7A.mirror = true;
		setRotation(Icicle7A, 0F, 0F, 0F);
		Icicle7B = new ModelRenderer(model, 114, 46);
		Icicle7B.addBox(0F, 0F, 0F, 1, 2, 2);
		Icicle7B.setRotationPoint(-7F, 12F, 2F);
		Icicle7B.setTextureSize(128, 64);
		Icicle7B.mirror = true;
		setRotation(Icicle7B, 0F, 0F, 0F);
		Icicle7C = new ModelRenderer(model, 116, 44);
		Icicle7C.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle7C.setRotationPoint(-7F, 14F, 2F);
		Icicle7C.setTextureSize(128, 64);
		Icicle7C.mirror = true;
		setRotation(Icicle7C, 0F, 0F, 0F);
		Icicle8A = new ModelRenderer(model, 104, 54);
		Icicle8A.addBox(0F, 0F, 0F, 1, 1, 4);
		Icicle8A.setRotationPoint(-7F, 11F, -5F);
		Icicle8A.setTextureSize(128, 64);
		Icicle8A.mirror = true;
		setRotation(Icicle8A, 0F, 0F, 0F);
		Icicle8B = new ModelRenderer(model, 106, 50);
		Icicle8B.addBox(0F, 0F, 0F, 1, 1, 3);
		Icicle8B.setRotationPoint(-7F, 12F, -4F);
		Icicle8B.setTextureSize(128, 64);
		Icicle8B.mirror = true;
		setRotation(Icicle8B, 0F, 0F, 0F);
		Icicle8C = new ModelRenderer(model, 108, 46);
		Icicle8C.addBox(0F, 0F, 0F, 1, 2, 2);
		Icicle8C.setRotationPoint(-7F, 13F, -4F);
		Icicle8C.setTextureSize(128, 64);
		Icicle8C.mirror = true;
		setRotation(Icicle8C, 0F, 0F, 0F);
		Icicle8D = new ModelRenderer(model, 112, 44);
		Icicle8D.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle8D.setRotationPoint(-7F, 15F, -3F);
		Icicle8D.setTextureSize(128, 64);
		Icicle8D.mirror = true;
		setRotation(Icicle8D, 0F, 0F, 0F);

		Icicle9A = new ModelRenderer(model, 122, 38);
		Icicle9A.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle9A.setRotationPoint(3F, 11F, -7F);
		Icicle9A.setTextureSize(128, 64);
		Icicle9A.mirror = true;
		setRotation(Icicle9A, 0F, 0F, 0F);
		Icicle9B = new ModelRenderer(model, 124, 36);
		Icicle9B.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle9B.setRotationPoint(4F, 12F, -7F);
		Icicle9B.setTextureSize(128, 64);
		Icicle9B.mirror = true;
		setRotation(Icicle9B, 0F, 0F, 0F);
		Icicle10A = new ModelRenderer(model, 114, 61);
		Icicle10A.addBox(0F, 0F, 0F, 3, 2, 1);
		Icicle10A.setRotationPoint(-1F, 11F, -7F);
		Icicle10A.setTextureSize(128, 64);
		Icicle10A.mirror = true;
		setRotation(Icicle10A, 0F, 0F, 0F);
		Icicle10B = new ModelRenderer(model, 116, 59);
		Icicle10B.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle10B.setRotationPoint(-1F, 13F, -7F);
		Icicle10B.setTextureSize(128, 64);
		Icicle10B.mirror = true;
		setRotation(Icicle10B, 0F, 0F, 0F);
		Icicle10C = new ModelRenderer(model, 120, 56);
		Icicle10C.addBox(0F, 0F, 0F, 1, 2, 1);
		Icicle10C.setRotationPoint(0F, 14F, -7F);
		Icicle10C.setTextureSize(128, 64);
		Icicle10C.mirror = true;
		setRotation(Icicle10C, 0F, 0F, 0F);
		Icicle11A = new ModelRenderer(model, 114, 54);
		Icicle11A.addBox(0F, 0F, 0F, 4, 1, 1);
		Icicle11A.setRotationPoint(-5F, 11F, -7F);
		Icicle11A.setTextureSize(128, 64);
		Icicle11A.mirror = true;
		setRotation(Icicle11A, 0F, 0F, 0F);
		Icicle11B = new ModelRenderer(model, 116, 52);
		Icicle11B.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle11B.setRotationPoint(-4F, 12F, -7F);
		Icicle11B.setTextureSize(128, 64);
		Icicle11B.mirror = true;
		setRotation(Icicle11B, 0F, 0F, 0F);
		Icicle11C = new ModelRenderer(model, 118, 50);
		Icicle11C.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle11C.setRotationPoint(-4F, 13F, -7F);
		Icicle11C.setTextureSize(128, 64);
		Icicle11C.mirror = true;
		setRotation(Icicle11C, 0F, 0F, 0F);
	}

	public void render(float f) {
		if (Magistics.proxy.winter) {
			Icicle1A.render(f);
			Icicle1B.render(f);
			Icicle1C.render(f);
			Icicle2A.render(f);
			Icicle2B.render(f);
			Icicle2C.render(f);
			Icicle3A.render(f);
			Icicle3B.render(f);
			Icicle4A.render(f);
			Icicle4B.render(f);
			Icicle5A.render(f);
			Icicle5B.render(f);
			Icicle5C.render(f);
			Icicle6A.render(f);
			Icicle6B.render(f);
			Icicle6C.render(f);
			Icicle7A.render(f);
			Icicle7B.render(f);
			Icicle7C.render(f);
			Icicle8A.render(f);
			Icicle8B.render(f);
			Icicle8C.render(f);
			Icicle8D.render(f);

			Icicle9A.render(f);
			Icicle9B.render(f);
			Icicle10A.render(f);
			Icicle10B.render(f);
			Icicle10C.render(f);
			Icicle11A.render(f);
			Icicle11B.render(f);
			Icicle11C.render(f);
		}

		bottom.render(f);
		pillarbottom.render(f);
		pillar.render(f);
		pillartop.render(f);
		top.render(f);
		pot.render(f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		glPushMatrix();
		glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		glRotatef(180F, 1F, 0F, 0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		render(0.0625F);

		if (Magistics.proxy.easter) {
			glRotatef(90F * (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);

			f = 0.0625F;
			BunnyTail1.render(f);
			BunnyTail2.render(f);
			BunnyTail3.render(f);
			BunnyEar1.render(f);
			BunnyEar2.render(f);
		}
		glPopMatrix();

		TilePillarPot pillarTile = (TilePillarPot) tile;

		if (pillarTile.getStackInSlot(0) != null) {
			if (pillarTile.showNum) {
				glPushMatrix();
				glTranslated(x + 0.5D, y + 1, z + 0.5D);

				glDisable(GL_LIGHTING);
				RenderingHelper.renderFloatingTextWithBackground(0, 0.9F, 0, 0.2F, pillarTile.getStackInSlot(0).getDisplayName(), Color.WHITE.getRGB(), new Color(0F, 0F, 0F, 0.5F));
				glEnable(GL_LIGHTING);

				glPopMatrix();
			}
		}
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		render(0.0625F);
		glPopAttrib();
		glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		TilePillarPot tile = (TilePillarPot) blockAccess.getTileEntity(x, y, z);
		renderer.renderAllFaces = true;

		if (tile.getStackInSlot(0) != null) {
			Block plantBlock = Block.getBlockFromItem(tile.getStackInSlot(0).getItem());
			int metadata = tile.getStackInSlot(0).getItemDamage();

			Tessellator tessellator = Tessellator.instance;
			tessellator.setBrightness(plantBlock.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));

			int hexColor = plantBlock.colorMultiplier(renderer.blockAccess, x, y, z);
			float red = (hexColor >> 16 & 255) / 255F, green = (hexColor >> 8 & 255) / 255F, blue = (hexColor & 255) / 255F;
			tessellator.setColorOpaque_F(red, green, blue);

			float renderScale = 0.75F;

			if (plantBlock == Blocks.red_flower || plantBlock == Blocks.yellow_flower || plantBlock == Blocks.red_mushroom || plantBlock == Blocks.brown_mushroom)
				renderScale = 1F;

			renderer.drawCrossedSquares(plantBlock.getIcon(0, metadata), x, y + 1.2, z, renderScale);
		}

		renderer.renderAllFaces = false;

		return true;
	}

	private int setBrightness(IBlockAccess world, int x, int y, int z, Block block) {
		int brightness = block.getMixedBrightnessForBlock(world, x, y, z);

		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));

		float f = 1F;

		int l = block.colorMultiplier(world, x, y, z);
		float f1 = (l >> 16 & 255) / 255F, f2 = (l >> 8 & 255) / 255F, f3 = (l & 255) / 255F, f4;

		if (EntityRenderer.anaglyphEnable) {
			float f5 = (f1 * 30F + f2 * 59F + f3 * 11F) / 100F, f6 = (f1 * 30F + f3 * 70F) / 100F;
			f4 = (f1 * 30F + f2 * 70F) / 100F;
			f1 = f5;
			f2 = f4;
			f3 = f6;
		}

		tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
		return brightness;
	}

	@Override
	public boolean shouldRender3DInInventory(int i) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockPillarPot.renderID;
	}
}