package T145.magistics.client.renderers.pillars;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ENABLE_BIT;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.pillars.BlockPillarExtend;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderExtendPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	private ResourceLocation texture, frozenTexture;

	public static ModelBase model = new ModelBase() {};
	private ModelRenderer bottom, bottoms, tops,top, pillar, pillarTop, pillarBottom, pillarEast, pillarWest, pillarNorth, pillarSouth, Icicle1A, Icicle1B, Icicle1C, Icicle2A, Icicle2B, Icicle2C, Icicle3A, Icicle3B, Icicle4A, Icicle4B, Icicle5A, Icicle5B, Icicle5C, Icicle6A, Icicle6B, Icicle6C, Icicle7A, Icicle7B, Icicle7C, Icicle8A, Icicle8B, Icicle8C, Icicle8D, Icicle9A, Icicle9B, Icicle10A, Icicle10B, Icicle10C,  Icicle11A, Icicle11B, Icicle11C;

	public RenderExtendPillar(String modelTexture) {
		texture = new ResourceLocation("magistics:textures/models/pillars/" + modelTexture + ".png");
		renderPillar();
	}

	public RenderExtendPillar(String modelTexture, String frozenModelTexture) {
		texture = new ResourceLocation("magistics:textures/models/pillars/" + modelTexture + ".png");
		frozenTexture = new ResourceLocation("magistics:textures/models/pillars/" + frozenModelTexture + ".png");
		renderPillar();
	}

	public void renderPillar() {
		if (Magistics.proxy.winter && frozenTexture != null)
			texture = frozenTexture;

		model.textureWidth = 128;
		model.textureHeight = 64;

		bottom = new ModelRenderer(model, 0, 0);
		bottom.addBox(0F, 0F, 0F, 16, 2, 16);
		bottom.setRotationPoint(-8F, 22F, -8F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		bottoms = new ModelRenderer(model, 0, 18);
		bottoms.addBox(0F, 0F, 0F, 14, 1, 14);
		bottoms.setRotationPoint(-7F, 21F, -7F);
		bottoms.setTextureSize(128, 64);
		bottoms.mirror = true;
		setRotation(bottoms, 0F, 0F, 0F);
		tops = new ModelRenderer(model, 0, 18);
		tops.addBox(0F, 0F, 0F, 14, 1, 14);
		tops.setRotationPoint(-7F, 10F, -7F);
		tops.setTextureSize(128, 64);
		tops.mirror = true;
		setRotation(tops, 0F, 0F, 0F);
		top = new ModelRenderer(model, 64, 0);
		top.addBox(0F, 0F, 0F, 16, 2, 16);
		top.setRotationPoint(-8F, 8F, -8F);
		top.setTextureSize(128, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		pillar = new ModelRenderer(model, 0, 33);
		pillar.addBox(0F, 0F, 0F, 12, 10, 12);
		pillar.setRotationPoint(-6F, 11F, 6F);
		pillar.setTextureSize(128, 64);
		pillar.mirror = true;
		setRotation(pillar, 0F, 1.570796F, 0F);
		pillarTop = new ModelRenderer(model, 0, 33);
		pillarTop.addBox(0F, 0F, 0F, 12, 3, 12);
		pillarTop.setRotationPoint(-6F, 8F, 6F);
		pillarTop.setTextureSize(128, 64);
		pillarTop.mirror = true;
		setRotation(pillarTop, 0F, 1.570796F, 0F);
		pillarBottom = new ModelRenderer(model, 0, 33);
		pillarBottom.addBox(0F, 0F, 0F, 12, 3, 12);
		pillarBottom.setRotationPoint(-6F, 21F, 6F);
		pillarBottom.setTextureSize(128, 64);
		pillarBottom.mirror = true;
		setRotation(pillarBottom, 0F, 1.570796F, 0F);
		pillarEast = new ModelRenderer(model, 0, 33);
		pillarEast.addBox(0F, 0F, 0F, 12, 2, 12);
		pillarEast.setRotationPoint(-8F, 22F, 6F);
		pillarEast.setTextureSize(128, 64);
		pillarEast.mirror = true;
		setRotation(pillarEast, 1.570796F, 1.570796F, 0F);
		pillarWest = new ModelRenderer(model, 0, 33);
		pillarWest.addBox(0F, 0F, 0F, 12, 2, 12);
		pillarWest.setRotationPoint(8F, 10F, 6F);
		pillarWest.setTextureSize(128, 64);
		pillarWest.mirror = true;
		setRotation(pillarWest, -1.570796F, 1.570796F, 0F);
		pillarNorth = new ModelRenderer(model, 0, 33);
		pillarNorth.addBox(0F, 0F, 0F, 12, 2, 12);
		pillarNorth.setRotationPoint(-6F, 10F, -8F);
		pillarNorth.setTextureSize(128, 64);
		pillarNorth.mirror = true;
		setRotation(pillarNorth, 1.570796F, 0F, 1.570796F);
		pillarSouth = new ModelRenderer(model, 0, 33);
		pillarSouth.addBox(0F, 0F, 0F, 12, 2, 12);
		pillarSouth.setRotationPoint(-6F, 22F, 8F);
		pillarSouth.setTextureSize(128, 64);
		pillarSouth.mirror = true;
		setRotation(pillarSouth, -1.570796F, 0F, -1.570796F);

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

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void render(float f, boolean bt, boolean bb, boolean be, boolean bw, boolean bn, boolean bs) {
		if (bb)
			pillarBottom.render(f);
		else {
			bottom.render(f);
			bottoms.render(f);
		}

		pillar.render(f);

		if (bw)
			pillarEast.render(f);
		if (be)
			pillarWest.render(f);
		if (bs)
			pillarNorth.render(f);
		if (bn)
			pillarSouth.render(f);
		if (bt) {
			pillarTop.render(f);
		} else {
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

			tops.render(f);
			top.render(f);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		glPushMatrix();
		glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
		Block idTop, idBottom, idEast, idWest, idNorth, idSouth;
		int meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
		if (meta == 0) {
			idTop = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord);
			idBottom = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord - 1, tile.zCoord);
			idEast = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord, tile.zCoord);
			idWest = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord, tile.zCoord);
			idNorth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord - 1);
			idSouth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord + 1);
		} else if (meta == 1) {
			glRotatef(90F, 1F, 0F, 0F);
			idTop = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord + 1);
			idBottom = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord - 1);
			idEast = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord, tile.zCoord);
			idWest = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord, tile.zCoord);
			idNorth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord);
			idSouth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord - 1, tile.zCoord);
		} else {
			glRotatef(90F, 0F, 0F, 1F);
			idTop = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord, tile.zCoord);
			idBottom = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord, tile.zCoord);
			idEast = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord);
			idWest = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord - 1, tile.zCoord);
			idNorth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord - 1);
			idSouth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord + 1);
		}
		boolean top = (idTop == ConfigObjects.blockBasePillar), bottom = (idBottom == ConfigObjects.blockBasePillar), east = (idEast == ConfigObjects.blockBasePillar),  west = (idWest == ConfigObjects.blockBasePillar), north = (idNorth == ConfigObjects.blockBasePillar), south = (idSouth == ConfigObjects.blockBasePillar);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		glTranslatef(0.0F, 1.0F, 0.0F);
		glRotatef(180F, 1F, 0F, 0F);
		render(0.0625F, top, bottom, east, west, north, south);
		glPopMatrix();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		glPushMatrix();
		glPushAttrib(GL_ENABLE_BIT);
		glEnable(GL_DEPTH_TEST);
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		render(0.0625F, false, false, false, false, false, false);
		glPopAttrib();
		glPopMatrix();
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
		return BlockPillarExtend.renderID;
	}
}