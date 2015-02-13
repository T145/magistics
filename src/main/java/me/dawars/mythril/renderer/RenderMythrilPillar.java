package me.dawars.mythril.renderer;

import net.minecraft.client.renderer.tileentity.*;
import cpw.mods.fml.client.registry.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import net.minecraft.tileentity.*;
import org.lwjgl.opengl.*;
import me.dawars.mythril.*;
import net.minecraft.client.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import cpw.mods.fml.client.*;
import net.minecraft.world.*;

public class RenderMythrilPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
    private ResourceLocation TEXTURE_WORKPILLAR_MYTHRIL;
    private ResourceLocation TEXTURE_WORKPILLAR_CHISELT;
    public static ModelBase model;
    private ModelRenderer bottom;
    private ModelRenderer bottoms;
    private ModelRenderer tops;
    private ModelRenderer top;
    private ModelRenderer pillar;
    private ModelRenderer pillarTop;
    private ModelRenderer pillarBottom;
    private ModelRenderer pillarEast;
    private ModelRenderer pillarWest;
    private ModelRenderer pillarNorth;
    private ModelRenderer pillarSouth;
    
    public RenderMythrilPillar() {
        this.TEXTURE_WORKPILLAR_MYTHRIL = new ResourceLocation("mythril:textures/models/mithril_pillar.png");
        this.TEXTURE_WORKPILLAR_CHISELT = new ResourceLocation("mythril:textures/models/mithril_pillar_qartz.png");
        RenderMythrilPillar.model.textureWidth = 128;
        RenderMythrilPillar.model.textureHeight = 64;
        (this.bottom = new ModelRenderer(RenderMythrilPillar.model, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 2, 16);
        this.bottom.setRotationPoint(-8.0f, 22.0f, -8.0f);
        this.bottom.setTextureSize(128, 64);
        this.bottom.mirror = true;
        this.setRotation(this.bottom, 0.0f, 0.0f, 0.0f);
        (this.bottoms = new ModelRenderer(RenderMythrilPillar.model, 0, 18)).addBox(0.0f, 0.0f, 0.0f, 14, 1, 14);
        this.bottoms.setRotationPoint(-7.0f, 21.0f, -7.0f);
        this.bottoms.setTextureSize(128, 64);
        this.bottoms.mirror = true;
        this.setRotation(this.bottoms, 0.0f, 0.0f, 0.0f);
        (this.tops = new ModelRenderer(RenderMythrilPillar.model, 0, 18)).addBox(0.0f, 0.0f, 0.0f, 14, 1, 14);
        this.tops.setRotationPoint(-7.0f, 10.0f, -7.0f);
        this.tops.setTextureSize(128, 64);
        this.tops.mirror = true;
        this.setRotation(this.tops, 0.0f, 0.0f, 0.0f);
        (this.top = new ModelRenderer(RenderMythrilPillar.model, 64, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 2, 16);
        this.top.setRotationPoint(-8.0f, 8.0f, -8.0f);
        this.top.setTextureSize(128, 64);
        this.top.mirror = true;
        this.setRotation(this.top, 0.0f, 0.0f, 0.0f);
        (this.pillar = new ModelRenderer(RenderMythrilPillar.model, 0, 33)).addBox(0.0f, 0.0f, 0.0f, 12, 10, 12);
        this.pillar.setRotationPoint(-6.0f, 11.0f, 6.0f);
        this.pillar.setTextureSize(128, 64);
        this.pillar.mirror = true;
        this.setRotation(this.pillar, 0.0f, 1.570796f, 0.0f);
        (this.pillarTop = new ModelRenderer(RenderMythrilPillar.model, 0, 33)).addBox(0.0f, 0.0f, 0.0f, 12, 3, 12);
        this.pillarTop.setRotationPoint(-6.0f, 8.0f, 6.0f);
        this.pillarTop.setTextureSize(128, 64);
        this.pillarTop.mirror = true;
        this.setRotation(this.pillarTop, 0.0f, 1.570796f, 0.0f);
        (this.pillarBottom = new ModelRenderer(RenderMythrilPillar.model, 0, 33)).addBox(0.0f, 0.0f, 0.0f, 12, 3, 12);
        this.pillarBottom.setRotationPoint(-6.0f, 21.0f, 6.0f);
        this.pillarBottom.setTextureSize(128, 64);
        this.pillarBottom.mirror = true;
        this.setRotation(this.pillarBottom, 0.0f, 1.570796f, 0.0f);
        (this.pillarEast = new ModelRenderer(RenderMythrilPillar.model, 0, 33)).addBox(0.0f, 0.0f, 0.0f, 12, 2, 12);
        this.pillarEast.setRotationPoint(-8.0f, 22.0f, 6.0f);
        this.pillarEast.setTextureSize(128, 64);
        this.pillarEast.mirror = true;
        this.setRotation(this.pillarEast, 1.570796f, 1.570796f, 0.0f);
        (this.pillarWest = new ModelRenderer(RenderMythrilPillar.model, 0, 33)).addBox(0.0f, 0.0f, 0.0f, 12, 2, 12);
        this.pillarWest.setRotationPoint(8.0f, 10.0f, 6.0f);
        this.pillarWest.setTextureSize(128, 64);
        this.pillarWest.mirror = true;
        this.setRotation(this.pillarWest, -1.570796f, 1.570796f, 0.0f);
        (this.pillarNorth = new ModelRenderer(RenderMythrilPillar.model, 0, 33)).addBox(0.0f, 0.0f, 0.0f, 12, 2, 12);
        this.pillarNorth.setRotationPoint(-6.0f, 10.0f, -8.0f);
        this.pillarNorth.setTextureSize(128, 64);
        this.pillarNorth.mirror = true;
        this.setRotation(this.pillarNorth, 1.570796f, 0.0f, 1.570796f);
        (this.pillarSouth = new ModelRenderer(RenderMythrilPillar.model, 0, 33)).addBox(0.0f, 0.0f, 0.0f, 12, 2, 12);
        this.pillarSouth.setRotationPoint(-6.0f, 22.0f, 8.0f);
        this.pillarSouth.setTextureSize(128, 64);
        this.pillarSouth.mirror = true;
        this.setRotation(this.pillarSouth, -1.570796f, 0.0f, -1.570796f);
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void render(final float f, final boolean bt, final boolean bb, final boolean be, final boolean bw, final boolean bn, final boolean bs) {
        if (bt) {
            this.pillarTop.render(f);
        }
        else {
            this.top.render(f);
            this.tops.render(f);
        }
        this.pillar.render(f);
        if (bb) {
            this.pillarBottom.render(f);
        }
        else {
            this.bottom.render(f);
            this.bottoms.render(f);
        }
        if (bw) {
            this.pillarEast.render(f);
        }
        if (be) {
            this.pillarWest.render(f);
        }
        if (bs) {
            this.pillarNorth.render(f);
        }
        if (bn) {
            this.pillarSouth.render(f);
        }
    }
    
    public void renderTileEntityAt(final TileEntity tile, final double x, final double y, final double z, final float f) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        final int meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) >> 2;
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
        }
        else if (meta == 2) {
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            idTop = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord + 1);
            idBottom = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord - 1);
            idEast = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord, tile.zCoord);
            idWest = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord, tile.zCoord);
            idNorth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord);
            idSouth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord - 1, tile.zCoord);
        }
        else {
            GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
            idTop = tile.getWorldObj().getBlock(tile.xCoord - 1, tile.yCoord, tile.zCoord);
            idBottom = tile.getWorldObj().getBlock(tile.xCoord + 1, tile.yCoord, tile.zCoord);
            idEast = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord + 1, tile.zCoord);
            idWest = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord - 1, tile.zCoord);
            idNorth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord - 1);
            idSouth = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord + 1);
        }
        final boolean top = idTop == MythrilMod.blockMythrilPillar;
        final boolean bottom = idBottom == MythrilMod.blockMythrilPillar;
        final boolean east = idEast == MythrilMod.blockMythrilPillar;
        final boolean west = idWest == MythrilMod.blockMythrilPillar;
        final boolean north = idNorth == MythrilMod.blockMythrilPillar;
        final boolean south = idSouth == MythrilMod.blockMythrilPillar;
        if ((tile.getBlockMetadata() & 0x3) == 0x0) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_WORKPILLAR_CHISELT);
        }
        else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_WORKPILLAR_MYTHRIL);
        }
        GL11.glTranslatef(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        this.render(0.0625f, top, bottom, east, west, north, south);
        GL11.glPopMatrix();
    }
    
    public void renderInventoryBlock(final Block block, final int metadata, final int modelID, final RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslated(0.0, 1.0, 0.0);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        if (metadata == 1) {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_WORKPILLAR_MYTHRIL);
        }
        else {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE_WORKPILLAR_CHISELT);
        }
        GL11.glPushAttrib(8192);
        GL11.glEnable(2929);
        this.render(0.0625f, false, false, false, false, false, false);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
    
    public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block, final int modelId, final RenderBlocks renderer) {
        return false;
    }
    
    public boolean shouldRender3DInInventory(final int i) {
        return true;
    }
    
    public int getRenderId() {
        return MythrilMod.mythrilPillarRenderID;
    }
    
    static {
        RenderMythrilPillar.model = new ModelBase() {};
    }
}
