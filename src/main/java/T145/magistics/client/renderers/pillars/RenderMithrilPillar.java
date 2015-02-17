package T145.magistics.client.renderers.pillars;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import T145.magistics.common.blocks.pillars.BlockMithrilPillar;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderMithrilPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	private ResourceLocation texture, texture_chisel;
	public static ModelBase model = new ModelBase() {};
	private ModelRenderer bottom, bottoms, tops, top, pillar, pillarTop, pillarBottom, pillarEast, pillarWest, pillarNorth, pillarSouth;

	public RenderMithrilPillar() {
		texture = new ResourceLocation("magistics:textures/models/pillars/pillar_mithril.png");
		texture_chisel = new ResourceLocation("magistics:textures/models/pillars/pillar_mithril_qartz.png");
		RenderMithrilPillar.model.textureWidth = 128;
		RenderMithrilPillar.model.textureHeight = 64;
		(bottom = new ModelRenderer(RenderMithrilPillar.model, 0, 0)).addBox(0F, 0F, 0F, 16, 2, 16);
		bottom.setRotationPoint(-8F, 22F, -8F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		(bottoms = new ModelRenderer(RenderMithrilPillar.model, 0, 18)).addBox(0F, 0F, 0F, 14, 1, 14);
		bottoms.setRotationPoint(-7F, 21F, -7F);
		bottoms.setTextureSize(128, 64);
		bottoms.mirror = true;
		setRotation(bottoms, 0F, 0F, 0F);
		(tops = new ModelRenderer(RenderMithrilPillar.model, 0, 18)).addBox(0F, 0F, 0F, 14, 1, 14);
		tops.setRotationPoint(-7F, 10F, -7F);
		tops.setTextureSize(128, 64);
		tops.mirror = true;
		setRotation(tops, 0F, 0F, 0F);
		(top = new ModelRenderer(RenderMithrilPillar.model, 64, 0)).addBox(0F, 0F, 0F, 16, 2, 16);
		top.setRotationPoint(-8F, 8F, -8F);
		top.setTextureSize(128, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		(pillar = new ModelRenderer(RenderMithrilPillar.model, 0, 33)).addBox(0F, 0F, 0F, 12, 10, 12);
		pillar.setRotationPoint(-6F, 11F, 6F);
		pillar.setTextureSize(128, 64);
		pillar.mirror = true;
		setRotation(pillar, 0F, 1.570796f, 0F);
		(pillarTop = new ModelRenderer(RenderMithrilPillar.model, 0, 33)).addBox(0F, 0F, 0F, 12, 3, 12);
		pillarTop.setRotationPoint(-6F, 8F, 6F);
		pillarTop.setTextureSize(128, 64);
		pillarTop.mirror = true;
		setRotation(pillarTop, 0F, 1.570796f, 0F);
		(pillarBottom = new ModelRenderer(RenderMithrilPillar.model, 0, 33)).addBox(0F, 0F, 0F, 12, 3, 12);
		pillarBottom.setRotationPoint(-6F, 21F, 6F);
		pillarBottom.setTextureSize(128, 64);
		pillarBottom.mirror = true;
		setRotation(pillarBottom, 0F, 1.570796f, 0F);
		(pillarEast = new ModelRenderer(RenderMithrilPillar.model, 0, 33)).addBox(0F, 0F, 0F, 12, 2, 12);
		pillarEast.setRotationPoint(-8F, 22F, 6F);
		pillarEast.setTextureSize(128, 64);
		pillarEast.mirror = true;
		setRotation(pillarEast, 1.570796f, 1.570796f, 0F);
		(pillarWest = new ModelRenderer(RenderMithrilPillar.model, 0, 33)).addBox(0F, 0F, 0F, 12, 2, 12);
		pillarWest.setRotationPoint(8F, 10F, 6F);
		pillarWest.setTextureSize(128, 64);
		pillarWest.mirror = true;
		setRotation(pillarWest, -1.570796f, 1.570796f, 0F);
		(pillarNorth = new ModelRenderer(RenderMithrilPillar.model, 0, 33)).addBox(0F, 0F, 0F, 12, 2, 12);
		pillarNorth.setRotationPoint(-6F, 10F, -8F);
		pillarNorth.setTextureSize(128, 64);
		pillarNorth.mirror = true;
		setRotation(pillarNorth, 1.570796f, 0F, 1.570796f);
		(pillarSouth = new ModelRenderer(RenderMithrilPillar.model, 0, 33)).addBox(0F, 0F, 0F, 12, 2, 12);
		pillarSouth.setRotationPoint(-6F, 22F, 8F);
		pillarSouth.setTextureSize(128, 64);
		pillarSouth.mirror = true;
		setRotation(pillarSouth, -1.570796f, 0F, -1.570796f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void render(float f, boolean bt, boolean bb, boolean be, boolean bw, boolean bn, boolean bs) {
		if (bt)
			pillarTop.render(f);
		else {
			top.render(f);
			tops.render(f);
		}
		pillar.render(f);
		if (bb)
			pillarBottom.render(f);
		else {
			bottom.render(f);
			bottoms.render(f);
		}
		if (bw)
			pillarEast.render(f);
		if (be)
			pillarWest.render(f);
		if (bs)
			pillarNorth.render(f);
		if (bn)
			pillarSouth.render(f);
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		int meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) >> 2;
		Block idTop;
		Block idBottom;
		Block idEast;
		Block idWest;
		Block idNorth;
		Block idSouth;
		if (meta == 0) {
			idTop = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord);
			idBottom = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord - 1, tile.zCoord);
			idEast = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord, tile.zCoord);
			idWest = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord, tile.zCoord);
			idNorth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord - 1);
			idSouth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord + 1);
		} else if (meta == 2) {
			GL11.glRotatef(90F, 1F, 0F, 0F);
			idTop = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord + 1);
			idBottom = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord - 1);
			idEast = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord, tile.zCoord);
			idWest = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord, tile.zCoord);
			idNorth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord);
			idSouth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord - 1, tile.zCoord);
		} else {
			GL11.glRotatef(90F, 0F, 0F, 1F);
			idTop = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord, tile.zCoord);
			idBottom = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord, tile.zCoord);
			idEast = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord);
			idWest = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord - 1, tile.zCoord);
			idNorth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord - 1);
			idSouth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord + 1);
		}
		boolean top = idTop == ConfigObjects.blockMithrilPillar;
		boolean bottom = idBottom == ConfigObjects.blockMithrilPillar;
		boolean east = idEast == ConfigObjects.blockMithrilPillar;
		boolean west = idWest == ConfigObjects.blockMithrilPillar;
		boolean north = idNorth == ConfigObjects.blockMithrilPillar;
		boolean south = idSouth == ConfigObjects.blockMithrilPillar;
		if ((tile.getBlockMetadata() & 0x3) == 0x0)
			Minecraft.getMinecraft().renderEngine.bindTexture(texture_chisel);
		else
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glTranslatef(0F, 1F, 0F);
		GL11.glRotatef(180F, 1F, 0F, 0F);
		render(0.0625f, top, bottom, east, west, north, south);
		GL11.glPopMatrix();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glTranslated(0.0, 1.0, 0.0);
		GL11.glRotatef(180F, 1F, 0F, 0F);
		if (metadata == 1)
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		else
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture_chisel);
		GL11.glPushAttrib(8192);
		GL11.glEnable(2929);
		render(0.0625f, false, false, false, false, false, false);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
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
		return BlockMithrilPillar.renderID;
	}
}